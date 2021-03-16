package com.mercadolibre.federico_rivarola_pf.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.QueryPartUnitDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.QueryPartsDTO;
import com.mercadolibre.federico_rivarola_pf.model.PartRecord;
import com.mercadolibre.federico_rivarola_pf.model.Provider;
import com.mercadolibre.federico_rivarola_pf.model.Stock;
import com.mercadolibre.federico_rivarola_pf.repositories.IPartsRecordRepository;
import com.mercadolibre.federico_rivarola_pf.repositories.IPartsRepository;
import com.mercadolibre.federico_rivarola_pf.repositories.IProviderRepository;
import com.mercadolibre.federico_rivarola_pf.repositories.IStockRepository;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IPartManagementService;
import com.mercadolibre.federico_rivarola_pf.util.enums.OrderType;
import com.mercadolibre.federico_rivarola_pf.util.enums.Querytype;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * service for Part management.
 *
 * @author frivarola
 */
@Service
public class PartManagementService implements IPartManagementService {
    private final IPartsRepository partsRepository;
    private final IPartsRecordRepository partsRecordRepository;
    private final IStockRepository stockRepository;
    private final IProviderRepository providerRepository;
    private final ObjectMapper objectMapper;
    private final String datePattern = "yyyy-MM-dd";
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

    public PartManagementService(IPartsRepository partsRepository, IPartsRecordRepository partsRecordRepository, IStockRepository stockRepository, IProviderRepository providerRepository, ObjectMapper objectMapper) {
        this.partsRepository = partsRepository;
        this.partsRecordRepository = partsRecordRepository;
        this.stockRepository = stockRepository;
        this.providerRepository = providerRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Function to get all parts records by subsidiary
     *
     * @return
     * @throws ResponseStatusException
     */
    @Override
    public QueryPartsDTO getAll() throws ResponseStatusException {
        List<QueryPartUnitDTO> result = new ArrayList<>();
        String subsidiary = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Stock> ss = stockRepository.findByIdSubsidiary(subsidiary);

        if (ss != null || !ss.isEmpty()) {


            List<String> idsParts = ss.stream().map(s -> s.getPart().getId()).collect(Collectors.toList());
            List<PartRecord> records = partsRecordRepository.findByListIdParts(idsParts);

            if (records != null || !records.isEmpty()) {
                result = makeListQueryPartUnitDTO(records);

                if (result.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }

            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay registros para las partes asignadas al stock de " + subsidiary);
            }

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay stock para la subsidiaria " + subsidiary);
        }

        return new QueryPartsDTO(result);
    }

    @Override
    public QueryPartsDTO getAllByQueryType(Querytype querytype) {

        return null;
    }

    /**
     * Function to get all part records since date
     *
     * @param queryType
     * @param date
     * @return
     * @throws ResponseStatusException
     */
    @Override
    public QueryPartsDTO getAllByQueryTypeAndDate(Querytype queryType, String date) throws ResponseStatusException {

        if (queryType.equals(Querytype.C)) {
            return getAll();
        } else if (queryType.equals(Querytype.V) || queryType.equals(Querytype.P)) {

            List<QueryPartUnitDTO> result = new ArrayList<>();
            LocalDate lDate = null;

            try {
                lDate = LocalDate.parse(date, dateFormatter);
            } catch (DateTimeParseException de) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El formato de la fecha es incorrecto, debe ser YYYY-MM-DD");
            }

            String subsidiary = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Stock> ss = stockRepository.findByIdSubsidiary(subsidiary);

            if (ss != null || !ss.isEmpty()) {


                List<String> idsParts = ss.stream().map(s -> s.getPart().getId()).collect(Collectors.toList());
                List<PartRecord> records = partsRecordRepository.findByLastModificationAndIdsParts(lDate, idsParts);

                if (records != null || !records.isEmpty()) {
                    result = makeListQueryPartUnitDTO(records);

                    if (result.isEmpty()) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                    }

                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay registros para las partes asignadas al stock de " + subsidiary);
                }

            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay stock para la subsidiaria " + subsidiary);
            }

            return new QueryPartsDTO(result);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid querytype");
    }

    @Override
    public QueryPartsDTO getAllByQueryTypeAndDateSorter(Querytype queryType, String date, OrderType orderType) throws ResponseStatusException{
        //OrderType.ASC; codigo de parte ascendente
        List<QueryPartUnitDTO> result = getAllByQueryTypeAndDate(queryType, date).getParts();

        if(OrderType.ASC.equals(orderType)){
            Collections.sort(result, Comparator.comparing(QueryPartUnitDTO::getPartCode));
        }
        //OrderType.DESC; codigo de parte descendente
        if(OrderType.DESC.equals(orderType)){
            Collections.sort(result, (a,b) -> b.getPartCode().compareTo(a.getPartCode()));
        }
        //OrderType.FECHA_VARIACION; fecha desc
        if(OrderType.FECHA_VARIACION.equals(orderType)){
            Collections.sort(result, Comparator.comparing(a -> LocalDate.parse(a.getLastModification(), dateFormatter)));
        }

        return new QueryPartsDTO(result);
    }

    /**
     * Function for create List<QueryPartUnitDTO> from List<PartRecord>
     *
     * @param records
     * @return
     */
    private List<QueryPartUnitDTO> makeListQueryPartUnitDTO(List<PartRecord> records) {
        List<QueryPartUnitDTO> result = new ArrayList<>();

        for (PartRecord pr : records) {

            QueryPartUnitDTO u = objectMapper.convertValue(pr.getPart(), QueryPartUnitDTO.class);

            u.setNormalPrice(pr.getNormalPrice());
            u.setSalePrice(pr.getSalePrice());
            u.setUrgentPrice(pr.getUrgentPrice());
            u.setLastModification(pr.getLastModification());

            result.add(u);
        }

        return result;
    }
}

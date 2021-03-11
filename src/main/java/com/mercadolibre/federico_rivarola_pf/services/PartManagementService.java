package com.mercadolibre.federico_rivarola_pf.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.federico_rivarola_pf.dtos.PartDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.QueryPartUnitDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.QueryPartsDTO;
import com.mercadolibre.federico_rivarola_pf.model.Part;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.management.Query;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * service for Part management.
 * @author frivarola
 */
@Service
public class PartManagementService implements IPartManagementService {
    private final IPartsRepository partsRepository;
    private final IPartsRecordRepository partsRecordRepository;
    private final IStockRepository stockRepository;
    private final IProviderRepository providerRepository;
    private final ObjectMapper objectMapper;

    public PartManagementService(IPartsRepository partsRepository, IPartsRecordRepository partsRecordRepository, IStockRepository stockRepository, IProviderRepository providerRepository, ObjectMapper objectMapper) {
        this.partsRepository = partsRepository;
        this.partsRecordRepository = partsRecordRepository;
        this.stockRepository = stockRepository;
        this.providerRepository = providerRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Function to get all parts records by subsidiary
     * @return
     * @throws ResponseStatusException
     */
    @Override
    public QueryPartsDTO getAll() throws ResponseStatusException {
        List<QueryPartUnitDTO> result = new ArrayList<>();
        List<PartRecord> records = partsRecordRepository.findAll();

        result = makeListQueryPartUnitDTO(records);

        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new QueryPartsDTO(result);
    }

    @Override
    public QueryPartsDTO getAllByQueryType(Querytype querytype) {

        return null;
    }

    /**
     * Function to get all part records since date
     * @param queryType
     * @param date
     * @return
     * @throws ResponseStatusException
     */
    @Override
    public QueryPartsDTO getAllByQueryTypeAndDate(Querytype queryType, String date) throws ResponseStatusException {

        if(queryType.equals(Querytype.C)){
            return getAll();
        }else if(queryType.equals(Querytype.V) || queryType.equals(Querytype.P)){

            List<QueryPartUnitDTO> result = new ArrayList<>();
            LocalDate lDate = null;

            try {
                lDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException de) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El formato de la fecha es incorrecto, debe ser YYYY-MM-DD");
            }

            result = makeListQueryPartUnitDTO(partsRecordRepository.findByLastModification(lDate));

            if (result.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            return new QueryPartsDTO(result);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid querytype");
    }

    @Override
    public QueryPartsDTO getAllByQueryTypeAndDateSorter(Querytype queryType, String date, OrderType orderType) {
        return null;
    }

    /**
     * Function for create List<QueryPartUnitDTO> from List<PartRecord>
     * @param records
     * @return
     */
    private List<QueryPartUnitDTO> makeListQueryPartUnitDTO(List<PartRecord> records) {
        List<QueryPartUnitDTO> result = new ArrayList<>();
        String subsidiary = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for (PartRecord pr : records) {
            Stock s = stockRepository.findByIdPartAndIdSubsidiary(pr.getPart().getId(), subsidiary);
            Provider provider = providerRepository.findById(pr.getPart().getIdProvider().getId());

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

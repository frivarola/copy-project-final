package com.mercadolibre.federico_rivarola_pf.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.federico_rivarola_pf.dtos.requests.NewStockDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.NewStockResponseDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.QueryPartUnitDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.QueryPartsDTO;
import com.mercadolibre.federico_rivarola_pf.exceptions.ApiException;
import com.mercadolibre.federico_rivarola_pf.model.*;
import com.mercadolibre.federico_rivarola_pf.repositories.*;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IPartManagementService;
import com.mercadolibre.federico_rivarola_pf.util.enums.OrderType;
import com.mercadolibre.federico_rivarola_pf.util.enums.Querytype;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    private final ISubsidiaryRepository subsidiaryRepository;
    private final ObjectMapper objectMapper;
    private final String datePattern = "yyyy-MM-dd";
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

    public PartManagementService(IPartsRepository partsRepository, IPartsRecordRepository partsRecordRepository, IStockRepository stockRepository, IProviderRepository providerRepository, ISubsidiaryRepository subsidiaryRepository, ObjectMapper objectMapper) {
        this.partsRepository = partsRepository;
        this.partsRecordRepository = partsRecordRepository;
        this.stockRepository = stockRepository;
        this.providerRepository = providerRepository;
        this.subsidiaryRepository = subsidiaryRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Function to get all parts records by subsidiary
     *
     * @return
     * @throws ApiException
     */
    @Override
    public QueryPartsDTO getAll() throws ApiException {
        List<QueryPartUnitDTO> result = new ArrayList<>();
        String subsidiary = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Stock> ss = stockRepository.findByIdSubsidiary(subsidiary);

        if (ss != null || !ss.isEmpty()) {


            List<String> idsParts = ss.stream().map(s -> s.getPart().getId()).collect(Collectors.toList());
            List<PartRecord> records = partsRecordRepository.findByListIdParts(idsParts);

            if (records != null || !records.isEmpty()) {
                result = makeListQueryPartUnitDTO(records);

                if (result.isEmpty()) {
                    throw new ApiException("Not found", "Result is empty", HttpStatus.NOT_FOUND.value());
                }

            } else {
                throw new ApiException("Not found", "There are no records for the parts assigned to the stock of ".concat(subsidiary), HttpStatus.NOT_FOUND.value());
            }

        } else {
            throw new ApiException("Not found", "Not found stock to the subsidiary ".concat(subsidiary), HttpStatus.NOT_FOUND.value());
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
     * @throws ApiException
     */
    @Override
    public QueryPartsDTO getAllByQueryTypeAndDate(Querytype queryType, String date) throws ApiException {

        if (queryType.equals(Querytype.C)) {
            return getAll();
        } else if (queryType.equals(Querytype.V) || queryType.equals(Querytype.P)) {

            List<QueryPartUnitDTO> result = new ArrayList<>();
            LocalDate lDate = null;

            try {
                lDate = LocalDate.parse(date, dateFormatter);
            } catch (DateTimeParseException de) {

                throw new ApiException("Invalid format date", "date format is wrong, it must be YYYY-MM-DD", HttpStatus.BAD_REQUEST.value());
            }

            String subsidiary = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Stock> ss = stockRepository.findByIdSubsidiary(subsidiary);

            if (ss != null || !ss.isEmpty()) {


                List<String> idsParts = ss.stream().map(s -> s.getPart().getId()).collect(Collectors.toList());
                List<PartRecord> records = partsRecordRepository.findByLastModificationAndIdsParts(lDate, idsParts);

                if (records != null || !records.isEmpty()) {
                    result = makeListQueryPartUnitDTO(records);

                    if (result.isEmpty()) {
                        throw new ApiException("Not found", "Result is empty", HttpStatus.NOT_FOUND.value());
                    }

                } else {
                    throw new ApiException("Not found", "There are no records for the parts assigned to the stock of ".concat(subsidiary), HttpStatus.NOT_FOUND.value());
                }

            } else {
                throw new ApiException("Not found", "Not found stock to the subsidiary ".concat(subsidiary), HttpStatus.NOT_FOUND.value());
            }

            return new QueryPartsDTO(result);
        }
        
        throw new ApiException("Error", "Invalid querytype", HttpStatus.BAD_REQUEST.value());
    }

    @Override
    public QueryPartsDTO getAllByQueryTypeAndDateSorter(Querytype queryType, String date, OrderType orderType) throws ApiException{
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

    /**
     * Function for save stock of subsidiary
     * @param stockDTO newStock
     * @return NewStockResponseDTO
     */
    public NewStockResponseDTO saveStock(NewStockDTO stockDTO){
        String subsidiary = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Part p = partsRepository.findByIdPart(stockDTO.getIdPart());

        if(p != null ){
            Subsidiary s = subsidiaryRepository.findById(subsidiary);
            Integer quantity = stockDTO.getQuantity();
            if(quantity != null){
                if(quantity.compareTo(0) >= 0){

                    Stock stock = new Stock();
                    StockCompositeID stockID = new StockCompositeID(p, s);
                    stock.setId(stockID);
                    stock.setQuantity(quantity);

                    stockRepository.save(stock);

                    return new NewStockResponseDTO("201 Created", "Stock has been updated.", "");
                }
            }
            throw new ApiException("Invalid quantity", "Quantity must be equals or greater than 0", HttpStatus.BAD_REQUEST.value());
        }
        throw new ApiException("Invalid Part", stockDTO.getIdPart().concat(" not exist"),HttpStatus.NOT_FOUND.value());

    }
}

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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public QueryPartsDTO getAll() throws ResponseStatusException {
        //List<PartDTO> result = new ArrayList<>();
        List<QueryPartUnitDTO> result = new ArrayList<>();
        List<PartRecord> records = partsRecordRepository.findAll();

        for (PartRecord pr : records) {
            Stock s = stockRepository.findByIdPartAndIdSubsidiary(pr.getPart().getId(), "01");
            Provider provider = providerRepository.findById(pr.getPart().getIdProvider().getId());

            QueryPartUnitDTO u = objectMapper.convertValue(pr.getPart(), QueryPartUnitDTO.class);

            u.setNormalPrice(pr.getNormalPrice());
            u.setSalePrice(pr.getSalePrice());
            u.setUrgentPrice(pr.getUrgentPrice());
            u.setLastModification(pr.getLastModification());

            result.add(u);
        }

        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new QueryPartsDTO(result);
    }

    @Override
    public QueryPartsDTO getAllByQueryType(Querytype querytype) {

        return null;
    }

    @Override
    public QueryPartsDTO getAllByQueryTypeAndDate(Querytype queryType, String date) throws ResponseStatusException {
        List<QueryPartUnitDTO> result = new ArrayList<>();
        try{
            LocalDate lDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
        } catch(DateTimeParseException de){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El formato de la fecha es incorrecto, debe ser YYYY-MM-DD");
        }

        List<PartRecord> records = partsRecordRepository.findByLastModification(lDate);

        for (PartRecord pr: records) {
            System.out.println(pr.getLastModification());
        }

        return null;
    }

    @Override
    public QueryPartsDTO getAllByQueryTypeAndDateSorter(Querytype queryType, String date, OrderType orderType) {
        return null;
    }
}

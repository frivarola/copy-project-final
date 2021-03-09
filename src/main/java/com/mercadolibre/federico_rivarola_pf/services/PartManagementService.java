package com.mercadolibre.federico_rivarola_pf.services;

import com.mercadolibre.federico_rivarola_pf.dtos.PartDTO;
import com.mercadolibre.federico_rivarola_pf.model.Part;
import com.mercadolibre.federico_rivarola_pf.repositories.IPartsRepository;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IPartManagementService;
import com.mercadolibre.federico_rivarola_pf.util.enums.OrderType;
import com.mercadolibre.federico_rivarola_pf.util.enums.Querytype;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartManagementService implements IPartManagementService {
    private final IPartsRepository partsRepository;

    public PartManagementService(IPartsRepository partsRepository) {
        this.partsRepository = partsRepository;
    }

    @Override
    public List<PartDTO> getAll() {
        List<PartDTO> result = new ArrayList<>();

        for (Part p: partsRepository.findAll()) {
            result.add(new PartDTO(p.getId(),p.getDescription(),p.getWidthDimension(),p.getTallDimension(),p.getLongDimension(),p.getNetWeight(),Integer.parseInt(p.getIdProvider().getId())));
        }

        return result;
    }

    @Override
    public List<PartDTO> getAllByQueryType() {
        return null;
    }

    @Override
    public List<PartDTO> getAllByQueryTypeAndDate(Querytype queryType, LocalDate date) {
        return null;
    }

    @Override
    public List<PartDTO> getAllByQueryTypeAndDateSorter(Querytype queryType, LocalDate date, OrderType orderType) {
        return null;
    }
}

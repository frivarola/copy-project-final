package com.mercadolibre.federico_rivarola_pf.services;

import com.mercadolibre.federico_rivarola_pf.dtos.PartDTO;
import com.mercadolibre.federico_rivarola_pf.repositories.PartsRepository;
import com.mercadolibre.federico_rivarola_pf.repositories.StockRepository;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IPartManagementService;
import com.mercadolibre.federico_rivarola_pf.util.enums.OrderType;
import com.mercadolibre.federico_rivarola_pf.util.enums.Querytype;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PartManagmentService implements IPartManagementService {
    PartsRepository partsRepository;
    StockRepository stockRepository;

    public PartManagmentService(PartsRepository partsRepository, StockRepository stockRepository) {
        this.partsRepository = partsRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public List<PartDTO> getAll() {
        return null;
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

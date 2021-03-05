package com.mercadolibre.federico_rivarola_pf.services.interfaces;

import com.mercadolibre.federico_rivarola_pf.dtos.PartDTO;
import com.mercadolibre.federico_rivarola_pf.util.enums.OrderType;
import com.mercadolibre.federico_rivarola_pf.util.enums.Querytype;

import java.time.LocalDate;
import java.util.List;

public interface IPartManagementService {
    List<PartDTO> getAll();
    List<PartDTO> getAllByQueryType();
    List<PartDTO> getAllByQueryTypeAndDate(Querytype queryType, LocalDate date);
    List<PartDTO> getAllByQueryTypeAndDateSorter(Querytype queryType, LocalDate date,OrderType orderType);
}

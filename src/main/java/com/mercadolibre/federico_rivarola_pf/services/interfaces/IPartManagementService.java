package com.mercadolibre.federico_rivarola_pf.services.interfaces;

import com.mercadolibre.federico_rivarola_pf.dtos.PartDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.requests.NewStockDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.NewStockResponseDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.QueryPartsDTO;
import com.mercadolibre.federico_rivarola_pf.util.enums.OrderType;
import com.mercadolibre.federico_rivarola_pf.util.enums.Querytype;

import java.time.LocalDate;
import java.util.List;

public interface IPartManagementService {
    QueryPartsDTO getAll();
    QueryPartsDTO getAllByQueryType(Querytype querytype);
    QueryPartsDTO getAllByQueryTypeAndDate(Querytype queryType, String date);
    QueryPartsDTO getAllByQueryTypeAndDateSorter(Querytype queryType, String date,OrderType orderType);
    NewStockResponseDTO saveStock(NewStockDTO stockDTO);
}

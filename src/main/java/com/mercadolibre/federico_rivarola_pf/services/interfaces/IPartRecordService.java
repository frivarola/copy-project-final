package com.mercadolibre.federico_rivarola_pf.services.interfaces;

import com.mercadolibre.federico_rivarola_pf.dtos.PartDTO;
import com.mercadolibre.federico_rivarola_pf.model.PartRecord;
import com.mercadolibre.federico_rivarola_pf.util.enums.OrderType;
import com.mercadolibre.federico_rivarola_pf.util.enums.Querytype;

import java.time.LocalDate;
import java.util.List;

public interface IPartRecordService {
    List<PartRecord> getAll();
    List<PartRecord> getAllByIdPart(String idPart);
}

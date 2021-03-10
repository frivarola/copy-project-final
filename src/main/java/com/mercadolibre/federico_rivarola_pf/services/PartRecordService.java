package com.mercadolibre.federico_rivarola_pf.services;

import com.mercadolibre.federico_rivarola_pf.model.PartRecord;
import com.mercadolibre.federico_rivarola_pf.repositories.IPartsRecordRepository;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IPartRecordService;

import java.util.ArrayList;
import java.util.List;

public class PartRecordService implements IPartRecordService {
    private final IPartsRecordRepository partsRecordRepository;

    public PartRecordService(IPartsRecordRepository partsRecordRepository) {
        this.partsRecordRepository = partsRecordRepository;
    }

    @Override
    public List<PartRecord> getAll() {
        return (ArrayList<PartRecord>) partsRecordRepository.findAll();
    }

    @Override
    public List<PartRecord> getAllByIdPart(String idPart) {
        return (ArrayList<PartRecord>) partsRecordRepository.findByIdPart(idPart);
    }
}

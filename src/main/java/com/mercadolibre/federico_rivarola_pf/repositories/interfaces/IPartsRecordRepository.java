package com.mercadolibre.federico_rivarola_pf.repositories.interfaces;

import com.mercadolibre.federico_rivarola_pf.model.PartRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPartsRecordRepository extends JpaRepository {
    @Query("SELECT pr FROM PartRecord pr")
    List<PartRecord> findAll();
    @Query("SELECT pr FROM PartRecord pr WHERE pr.part.id = :idPart")
    List<PartRecord> findByIdPart(@Param("idPart") String idPart);

}

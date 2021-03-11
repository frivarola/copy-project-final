package com.mercadolibre.federico_rivarola_pf.repositories;

import com.mercadolibre.federico_rivarola_pf.model.PartRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPartsRecordRepository extends CrudRepository<PartRecord, Integer> {
    @Query("SELECT pr FROM PartRecord pr")
    List<PartRecord> findAll();

    @Query("SELECT pr FROM PartRecord pr JOIN Part p ON pr.part = p WHERE p.id = :idPart")
    List<PartRecord> findByIdPart(@Param("idPart") String idPart);

    @Query("SELECT pr FROM PartRecord pr JOIN Part p ON pr.part = p WHERE p.id in :idParts and to_date(pr.lastModification, 'yyyy-MM-dd') >= :date")
    List<PartRecord> findByLastModificationAndIdsParts(@Param("date") LocalDate date, @Param("idParts") List<String> idParts);

    @Query("SELECT pr FROM PartRecord pr JOIN Part p ON pr.part = p WHERE p.id in :idParts")
    List<PartRecord> findByListIdParts(@Param("idParts") List<String> idParts);

}

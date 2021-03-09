package com.mercadolibre.federico_rivarola_pf.repositories.interfaces;

import com.mercadolibre.federico_rivarola_pf.model.Part;
import com.mercadolibre.federico_rivarola_pf.model.PartRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPartsRepository {

    @Query("SELECT p FROM Part p")
    List<Part> findAll();
    @Query("SELECT p FROM Part p WHERE p.part.id = :idPart")
    List<Part> findByIdPart(@Param("idPart") String idPart);
}

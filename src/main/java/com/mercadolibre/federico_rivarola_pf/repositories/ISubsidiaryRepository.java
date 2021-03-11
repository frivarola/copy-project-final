package com.mercadolibre.federico_rivarola_pf.repositories;

import com.mercadolibre.federico_rivarola_pf.model.Subsidiary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubsidiaryRepository extends CrudRepository<Subsidiary, Integer> {
    @Query("SELECT s FROM Subsidiary s")
    List<Subsidiary> findAll();
    @Query("SELECT s FROM Subsidiary s WHERE s.id =:id")
    Subsidiary findById(@Param("id") String id);

}

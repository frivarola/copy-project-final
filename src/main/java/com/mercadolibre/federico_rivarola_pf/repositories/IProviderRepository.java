package com.mercadolibre.federico_rivarola_pf.repositories;

import com.mercadolibre.federico_rivarola_pf.model.Provider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProviderRepository extends CrudRepository<Provider, Integer> {

    @Query("SELECT p FROM Provider p")
    List<Provider> findAll();
    @Query("SELECT p FROM Provider p where p.id = :idProvider")
    Optional<Provider> findById(@Param("idProvider") Integer idProvider);
}

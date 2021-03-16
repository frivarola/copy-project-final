package com.mercadolibre.federico_rivarola_pf.repositories;

import com.mercadolibre.federico_rivarola_pf.model.Dealer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDealerRepository extends CrudRepository<Dealer, Integer> {
    @Query("SELECT d FROM Dealer d WHERE d.subsidiary.id = :idSubsidiary AND d.dealerNumber = :dealerNumber")
    Dealer findDealerByIdSubsidiary(@Param("idSubsidiary") String idSubsidiary, @Param("dealerNumber") String dealerNumber);
}

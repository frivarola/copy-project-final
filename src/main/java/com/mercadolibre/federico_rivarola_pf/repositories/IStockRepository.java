package com.mercadolibre.federico_rivarola_pf.repositories;

import com.mercadolibre.federico_rivarola_pf.model.Part;
import com.mercadolibre.federico_rivarola_pf.model.Stock;
import com.mercadolibre.federico_rivarola_pf.model.StockCompositeID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStockRepository extends CrudRepository<Stock, StockCompositeID> {

    @Query("SELECT s FROM Stock s")
    List<Stock> findAll();

    @Query("SELECT s FROM Stock s WHERE s.part.id = :idPart")
    List<Stock> findByIdPart(@Param("idPart") String idPart);

    @Query("SELECT s FROM Stock s WHERE s.subsidiary.id = :idSubsidiary")
    List<Stock> findByIdSubsidiary(@Param("idSubsidiary") String idSubsidiary);

    @Query("SELECT s FROM Stock s WHERE s.part.id = :idPart AND  s.subsidiary.id = :idSubsidiary")
    Stock findByIdPartAndIdSubsidiary(@Param("idPart") String idPart, @Param("idSubsidiary") String idSubsidiary);

}

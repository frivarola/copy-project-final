package com.mercadolibre.federico_rivarola_pf.repositories;

import com.mercadolibre.federico_rivarola_pf.model.OrderDetailCM;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailsRepository extends CrudRepository<OrderDetailCM, Integer> {
    @Query("SELECT od FROM OrderDetailCM od WHERE od.orderCM.orderNumberCM = :numberOrderCM")
    List<OrderDetailCM> findByNumberOrderCM(@Param("numberOrderCM")String numberOrderCM);
}

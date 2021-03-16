package com.mercadolibre.federico_rivarola_pf.repositories;

import com.mercadolibre.federico_rivarola_pf.model.OrderCM;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersRepository extends CrudRepository<OrderCM, Integer> {
    @Query("SELECT o FROM OrderCM o WHERE o.dealer.dealerNumber = :dealerNumber ")
    List<OrderCM> findByDealerNumber(@Param("dealerNumber") String dealerNumber);
    @Query("SELECT o FROM OrderCM o WHERE o.dealer.dealerNumber = :dealerNumber AND o.deliveryStatus.code = :deliveryStatus")
    List<OrderCM> findByDealerNumberAndDeliveryStatus(@Param("dealerNumber") String dealerNumber, @Param("deliveryStatus") String deliveryStatus);
}

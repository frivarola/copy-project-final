package com.mercadolibre.federico_rivarola_pf.services.interfaces;

import com.mercadolibre.federico_rivarola_pf.dtos.responses.OrderResponseDTO;
import com.mercadolibre.federico_rivarola_pf.model.OrderCM;
import com.mercadolibre.federico_rivarola_pf.util.enums.OrderType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersManagementService {
    OrderResponseDTO getAll();
    OrderResponseDTO getByDealerNumber(String dealerNumber);
    OrderResponseDTO getByDealerNumberAndDeliveryStatus(String dealerNumber, String deliveryStatus);
    OrderResponseDTO getByDealerNumberSorter(String dealerNumber, OrderType orderType);
    OrderResponseDTO getByDealerNumberAndDeliveryStatusSorter(String dealerNumber, String deliveryStatus, OrderType orderType);

}

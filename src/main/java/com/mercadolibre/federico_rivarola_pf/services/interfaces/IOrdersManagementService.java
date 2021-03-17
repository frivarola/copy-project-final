package com.mercadolibre.federico_rivarola_pf.services.interfaces;

import com.mercadolibre.federico_rivarola_pf.dtos.OrderDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.requests.NewOrderRequestDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.NewOrderResponseDTO;
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
    OrderResponseDTO getByDealerNumberSorter(String dealerNumber, Integer orderType);
    OrderResponseDTO getByDealerNumberAndDeliveryStatusSorter(String dealerNumber, String deliveryStatus, Integer orderType);
    OrderDTO getByOrderNumberCM(String orderNumberCM);
    NewOrderResponseDTO createOrder(NewOrderRequestDTO orderDTO);

}

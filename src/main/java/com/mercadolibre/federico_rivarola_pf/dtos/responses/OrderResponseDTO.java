package com.mercadolibre.federico_rivarola_pf.dtos.responses;

import com.mercadolibre.federico_rivarola_pf.dtos.OrderDTO;
import com.mercadolibre.federico_rivarola_pf.model.OrderDetailCM;

import java.util.List;

public class OrderResponseDTO {
    private String dealerNumber;
    private List<OrderDTO> orders;

    public OrderResponseDTO() {
    }

    public String getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(String dealerNumber) {
        this.dealerNumber = dealerNumber;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}

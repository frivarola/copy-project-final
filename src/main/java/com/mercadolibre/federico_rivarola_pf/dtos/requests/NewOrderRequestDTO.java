package com.mercadolibre.federico_rivarola_pf.dtos.requests;

import com.mercadolibre.federico_rivarola_pf.dtos.OrderDetailDTO;

import java.util.List;

public class NewOrderRequestDTO {
    private String dealerNumber;
    private List<OrderDetailDTO> orderDetails;

    public NewOrderRequestDTO() {
    }

    public NewOrderRequestDTO(String dealerNumber, List<OrderDetailDTO> orderDetails) {
        this.dealerNumber = dealerNumber;
        this.orderDetails = orderDetails;
    }

    public String getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(String dealerNumber) {
        this.dealerNumber = dealerNumber;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}

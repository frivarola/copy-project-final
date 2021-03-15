package com.mercadolibre.federico_rivarola_pf.dtos;

import com.mercadolibre.federico_rivarola_pf.model.OrderDetailCM;

import java.util.List;

public class OrderDTO {
    private String orderNumber;
    private String orderDate;
    private Integer daysDelay;
    private String deliveryStatus;
    private List<OrderDetailCM> orderDetails;

}

package com.mercadolibre.federico_rivarola_pf.dtos;

import com.mercadolibre.federico_rivarola_pf.model.OrderDetailCM;

import java.util.List;

public class OrderDTO {
    private String orderNumber;
    private String orderDate;
    private Integer daysDelay;
    private String deliveryStatus;
    private List<OrderDetailCM> orderDetails;

    public OrderDTO() {
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getDaysDelay() {
        return daysDelay;
    }

    public void setDaysDelay(Integer daysDelay) {
        this.daysDelay = daysDelay;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<OrderDetailCM> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailCM> orderDetails) {
        this.orderDetails = orderDetails;
    }
}

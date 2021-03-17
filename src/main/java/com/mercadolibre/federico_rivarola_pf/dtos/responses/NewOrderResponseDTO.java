package com.mercadolibre.federico_rivarola_pf.dtos.responses;

public class NewOrderResponseDTO {
    String orderNumberCE;
    String msg;

    public NewOrderResponseDTO() {
    }

    public NewOrderResponseDTO(String orderNumberCE, String msg) {
        this.orderNumberCE = orderNumberCE;
        this.msg = msg;
    }

    public String getOrderNumberCE() {
        return orderNumberCE;
    }

    public void setOrderNumberCE(String orderNumberCE) {
        this.orderNumberCE = orderNumberCE;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

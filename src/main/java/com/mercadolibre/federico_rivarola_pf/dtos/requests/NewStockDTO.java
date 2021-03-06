package com.mercadolibre.federico_rivarola_pf.dtos.requests;

public class NewStockDTO {
    private String idPart;
    private Integer quantity;

    public NewStockDTO() {
    }

    public NewStockDTO(String idPart, Integer quantity) {
        this.idPart = idPart;
        this.quantity = quantity;
    }

    public String getIdPart() {
        return idPart;
    }

    public void setIdPart(String idPart) {
        this.idPart = idPart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

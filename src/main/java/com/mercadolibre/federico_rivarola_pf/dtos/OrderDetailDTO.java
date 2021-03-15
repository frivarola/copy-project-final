package com.mercadolibre.federico_rivarola_pf.dtos;

public class OrderDetailDTO {
    private String partCode;
    private String description;
    private Integer quantity;
    private String accountType;
    private String reason;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String partCode, String description, Integer quantity, String accountType, String reason) {
        this.partCode = partCode;
        this.description = description;
        this.quantity = quantity;
        this.accountType = accountType;
        this.reason = reason;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

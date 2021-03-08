package com.mercadolibre.federico_rivarola_pf.model;

public class PartRecord {
    private String id;
    private String lastModification;
    private Double normalPrice;
    private Double salePrice;
    private Double urgentPrice;
    private String idDiscountRate;
    private String idPart;

    public PartRecord() {
    }

    public PartRecord(String id, String lastModification, Double normalPrice, Double salePrice, Double urgentPrice, String idDiscountRate, String idPart) {
        this.id = id;
        this.lastModification = lastModification;
        this.normalPrice = normalPrice;
        this.salePrice = salePrice;
        this.urgentPrice = urgentPrice;
        this.idDiscountRate = idDiscountRate;
        this.idPart = idPart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastModification() {
        return lastModification;
    }

    public void setLastModification(String lastModification) {
        this.lastModification = lastModification;
    }

    public Double getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(Double normalPrice) {
        this.normalPrice = normalPrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getUrgentPrice() {
        return urgentPrice;
    }

    public void setUrgentPrice(Double urgentPrice) {
        this.urgentPrice = urgentPrice;
    }

    public String getIdDiscountRate() {
        return idDiscountRate;
    }

    public void setIdDiscountRate(String idDiscountRate) {
        this.idDiscountRate = idDiscountRate;
    }

    public String getIdPart() {
        return idPart;
    }

    public void setIdPart(String idPart) {
        this.idPart = idPart;
    }
}

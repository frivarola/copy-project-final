package com.mercadolibre.federico_rivarola_pf.dtos;

import com.mercadolibre.federico_rivarola_pf.model.Part;

public class PartRecordDTO {
    private String id;
    private String lastModification;
    private Double normalPrice;
    private Double salePrice;
    private Double urgentPrice;
    private Part part;

    public PartRecordDTO(String id, String lastModification, Double normalPrice, Double salePrice, Double urgentPrice, Part part) {
        this.id = id;
        this.lastModification = lastModification;
        this.normalPrice = normalPrice;
        this.salePrice = salePrice;
        this.urgentPrice = urgentPrice;
        this.part = part;
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

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}

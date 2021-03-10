package com.mercadolibre.federico_rivarola_pf.dtos.responses;

public class QueryPartUnitDTO {
    private String partCode;
    private String description;
    private String maker;
    private Integer quantity;
    private String discountType;
    private Double normalPrice;
    private Double salePrice;
    private Double urgentPrice;
    private Integer longDimension;
    private Integer widthDimension;
    private Integer tallDimension;
    private String lastModification;

    public QueryPartUnitDTO() {
    }

    public QueryPartUnitDTO(String partCode, String description, String maker,
                            Integer quantity, String discountType, Double normalPrice,
                            Double salePrice, Double urgentPrice, Integer longDimension,
                            Integer widthDimension, Integer tallDimension, String lastModification) {
        this.partCode = partCode;
        this.description = description;
        this.maker = maker;
        this.quantity = quantity;
        this.discountType = discountType;
        this.normalPrice = normalPrice;
        this.salePrice = salePrice;
        this.urgentPrice = urgentPrice;
        this.longDimension = longDimension;
        this.widthDimension = widthDimension;
        this.tallDimension = tallDimension;
        this.lastModification = lastModification;
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

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
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

    public Integer getLongDimension() {
        return longDimension;
    }

    public void setLongDimension(Integer longDimension) {
        this.longDimension = longDimension;
    }

    public Integer getWidthDimension() {
        return widthDimension;
    }

    public void setWidthDimension(Integer widthDimension) {
        this.widthDimension = widthDimension;
    }

    public Integer getTallDimension() {
        return tallDimension;
    }

    public void setTallDimension(Integer tallDimension) {
        this.tallDimension = tallDimension;
    }

    public String getLastModification() {
        return lastModification;
    }

    public void setLastModification(String lastModification) {
        this.lastModification = lastModification;
    }
}

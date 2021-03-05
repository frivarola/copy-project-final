package com.mercadolibre.federico_rivarola_pf.dtos;

public class PartDTO {
    private String code;
    private String description;
    private Double widthDimension;
    private Double tallDimension;
    private Double longDimension;
    private Double netWeight;
    private Integer idProvider;

    public PartDTO() {
    }

    public PartDTO(String code, String description, Double widthDimension, Double tallDimension, Double longDimension, Double netWeight, Integer idProvider) {
        this.code = code;
        this.description = description;
        this.widthDimension = widthDimension;
        this.tallDimension = tallDimension;
        this.longDimension = longDimension;
        this.netWeight = netWeight;
        this.idProvider = idProvider;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWidthDimension() {
        return widthDimension;
    }

    public void setWidthDimension(Double widthDimension) {
        this.widthDimension = widthDimension;
    }

    public Double getTallDimension() {
        return tallDimension;
    }

    public void setTallDimension(Double tallDimension) {
        this.tallDimension = tallDimension;
    }

    public Double getLongDimension() {
        return longDimension;
    }

    public void setLongDimension(Double longDimension) {
        this.longDimension = longDimension;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public Integer getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Integer idProvider) {
        this.idProvider = idProvider;
    }
}

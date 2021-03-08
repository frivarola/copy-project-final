package com.mercadolibre.federico_rivarola_pf.model;

public class Part {
    private String code;
    private String description;
    private Integer widthDimension;
    private Integer tallDimension;
    private Integer longDimension;
    private Integer netWeight;
    private Integer idProvider;

    public Part(String code, String description, Integer widthDimension, Integer tallDimension, Integer longDimension, Integer netWeight, Integer idProvider) {
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

    public Integer getLongDimension() {
        return longDimension;
    }

    public void setLongDimension(Integer longDimension) {
        this.longDimension = longDimension;
    }

    public Integer getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Integer netWeight) {
        this.netWeight = netWeight;
    }

    public Integer getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Integer idProvider) {
        this.idProvider = idProvider;
    }
}

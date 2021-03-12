package com.mercadolibre.federico_rivarola_pf.dtos;

public class SearchLastPartDTO {
    private String partId;
    private String lastModification;
    private String partRecordId;

    public SearchLastPartDTO() {
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getLastModification() {
        return lastModification;
    }

    public void setLastModification(String lastModification) {
        this.lastModification = lastModification;
    }

    public String getPartRecordId() {
        return partRecordId;
    }

    public void setPartRecordId(String partRecordId) {
        this.partRecordId = partRecordId;
    }
}

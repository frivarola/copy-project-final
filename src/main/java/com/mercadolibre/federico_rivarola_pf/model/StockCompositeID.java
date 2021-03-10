package com.mercadolibre.federico_rivarola_pf.model;

import java.io.Serializable;

public class StockCompositeID implements Serializable {
    private Part part;
    private Subsidiary subsidiary;

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }
}

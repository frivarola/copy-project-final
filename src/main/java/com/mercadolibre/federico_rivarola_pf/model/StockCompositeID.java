package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class StockCompositeID implements Serializable {

    @ManyToOne()
    @JoinColumn(name = "id_part_fk",referencedColumnName = "id_part", nullable = false)
    private Part part;

    @ManyToOne()
    @JoinColumn(name = "id_subsidiary_fk", referencedColumnName = "id_subsidiary", nullable = false)
    private Subsidiary subsidiary;


    public StockCompositeID() {
    }

    public StockCompositeID(Part part, Subsidiary subsidiary) {
        this.part = part;
        this.subsidiary = subsidiary;
    }

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

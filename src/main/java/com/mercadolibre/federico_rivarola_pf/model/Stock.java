package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stock")
public class Stock implements Serializable {
    @EmbeddedId
    private StockCompositeID id;

    @Column(name = "quantity")
    private Integer quantity;

    public Stock() {
    }

    public Stock(StockCompositeID id, Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Part getPart() {
        return id.getPart();
    }

    public void setPart(Part part) {
        this.id.setPart(part);
    }

    public Subsidiary getSubsidiary() {
        return id.getSubsidiary();
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.id.setSubsidiary(subsidiary);
    }

    public StockCompositeID getId() {
        return id;
    }

    public void setId(StockCompositeID id) {
        this.id = id;
    }
}

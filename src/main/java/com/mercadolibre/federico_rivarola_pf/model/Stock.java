package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stock")
@IdClass(StockCompositeID.class)
public class Stock implements Serializable {
    @Id
    @ManyToOne()
    @JoinColumn(name = "id_part", nullable = false)
    private Part part;
    @Id
    @ManyToOne()
    @JoinColumn(name = "id_subsidiary", nullable = false)
    private Subsidiary subsidiary;
    @Column(name = "quantity")
    private Integer quantity;

    public Stock() {
    }

    public Stock(Part part, Subsidiary subsidiary, Integer quantity) {
        this.part = part;
        this.subsidiary = subsidiary;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

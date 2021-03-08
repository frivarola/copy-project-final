package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {
    @ManyToOne()
    @JoinColumn(name = "id_part", nullable = false)
    private Part part;
    @Column(name = "quantity")
    private Integer quantity;
    //@Column(name = "id_subsidiary")

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

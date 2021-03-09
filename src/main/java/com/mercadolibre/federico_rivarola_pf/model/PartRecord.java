package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.*;

@Entity
@Table(name = "part_records")
public class PartRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_part_record")
    private String id;
    @Column(name = "lastModification")
    private String lastModification;
    @Column(name = "normal_price")
    private Double normalPrice;
    @Column(name = "sale_price")
    private Double salePrice;
    @Column(name = "urgent_price")
    private Double urgentPrice;
    @ManyToOne()
    @JoinColumn(name = "id_part")
    private Part part;

    public PartRecord() {
    }

    public PartRecord(String id, String lastModification, Double normalPrice, Double salePrice, Double urgentPrice, Part part) {
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

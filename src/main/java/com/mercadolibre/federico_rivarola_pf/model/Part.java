package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_part", length = 8)
    private String id;
    @Column(name = "description")
    private String description;
    @Column(name = "widthDimension")
    private Integer widthDimension;
    @Column(name = "tallDimension")
    private Integer tallDimension;
    @Column(name = "longDimension")
    private Integer longDimension;
    @Column(name = "netWeight")
    private Integer netWeight;
    @ManyToOne()
    private Provider provider;
    @OneToMany(mappedBy = "part")
    private List<PartRecord> partRecords;

    public Part() {
    }

    public Part(String id, String description, Integer widthDimension, Integer tallDimension, Integer longDimension, Integer netWeight, Provider provider, List<PartRecord> partRecords) {
        this.id = id;
        this.description = description;
        this.widthDimension = widthDimension;
        this.tallDimension = tallDimension;
        this.longDimension = longDimension;
        this.netWeight = netWeight;
        this.provider = provider;
        this.partRecords = partRecords;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Provider getIdProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}

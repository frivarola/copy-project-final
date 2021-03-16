package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.*;

@Entity
@Table(name = "dealers")
public class Dealer extends Company{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="serial_number", length = 15)
    private String dealerNumber;
    @ManyToOne
    @JoinColumn(name="subsidiary_id")
    private Subsidiary subsidiary;

    public Dealer() {
        super();
    }

    public Dealer(String dealerNumber, Subsidiary subsidiary) {
        this.dealerNumber = dealerNumber;
        this.subsidiary = subsidiary;
    }

    public Dealer(String name, String address, String phone, String country, String dealerNumber, Subsidiary subsidiary) {
        super(name, address, phone, country);
        this.dealerNumber = dealerNumber;
        this.subsidiary = subsidiary;
    }

    public Dealer(String name, String address, String phone, String country) {
        super(name, address, phone, country);
    }

    public String getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(String dealerNumber) {
        this.dealerNumber = dealerNumber;
    }

    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }
}

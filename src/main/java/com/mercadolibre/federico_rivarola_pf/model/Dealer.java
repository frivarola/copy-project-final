package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.*;

@Entity
@Table(name = "dealers")
public class Dealer extends Company{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String serialNumber;

    public Dealer() {
        super();
    }

    public Dealer(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Dealer(String name, String address, String phone, String country, String serialNumber) {
        super(name, address, phone, country);
        this.serialNumber = serialNumber;
    }

    public Dealer(String name, String address, String phone, String country) {
        super(name, address, phone, country);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}

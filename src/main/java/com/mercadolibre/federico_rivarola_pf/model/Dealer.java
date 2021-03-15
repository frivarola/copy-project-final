package com.mercadolibre.federico_rivarola_pf.model;

public class Dealer extends Company{
    private String serialNumber;

    public Dealer() {
        super();
    }

    public Dealer(String name, String address, String phone, String country, String serialNumber) {
        super(name, address, phone, country);
        this.serialNumber = serialNumber;
    }

    public Dealer(String name, String address, String phone, String country) {
        super(name, address, phone, country);
    }
}

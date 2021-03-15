package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.Column;

public abstract class Company {

    private String name;
    private String address;
    private String phone;
    private String country;

    public Company() {
    }

    public Company(String name, String address, String phone, String country) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

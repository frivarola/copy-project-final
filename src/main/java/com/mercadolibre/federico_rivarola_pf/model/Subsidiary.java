package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.*;

@Entity
@Table(name = "subsidiary")
public class Subsidiary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_subsidiary")
    private String id;
    @Column(name = "accessToken")
    private String accessToken;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "country")
    private String country;
    @OneToOne(mappedBy = "subsidiary")
    private User user;

    public Subsidiary() {
    }

    public Subsidiary(String id, String accessToken, String name, String address, String phone, String country) {
        this.id = id;
        this.accessToken = accessToken;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

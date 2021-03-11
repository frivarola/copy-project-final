package com.mercadolibre.federico_rivarola_pf.dtos.requests;

public class NewUserDTO {
    private String user;
    private String password;
    private String idSubsidiary;

    public NewUserDTO() {
    }

    public NewUserDTO(String user, String password, String idSubsidiary) {
        this.user = user;
        this.password = password;
        this.idSubsidiary = idSubsidiary;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdSubsidiary() {
        return idSubsidiary;
    }

    public void setIdSubsidiary(String idSubsidiary) {
        this.idSubsidiary = idSubsidiary;
    }
}

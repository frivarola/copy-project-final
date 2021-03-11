package com.mercadolibre.federico_rivarola_pf.dtos.requests;

public class CredentialsDTO {
    private String username;
    private String pwd;

    public CredentialsDTO() {
    }

    public CredentialsDTO(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

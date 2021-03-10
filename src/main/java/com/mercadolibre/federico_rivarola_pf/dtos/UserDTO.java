package com.mercadolibre.federico_rivarola_pf.dtos;

public class UserDTO {
    String username;
    String token;

    public UserDTO(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public UserDTO() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

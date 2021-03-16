package com.mercadolibre.federico_rivarola_pf.dtos.responses;

public class NewStockResponseDTO {
    private String code;
    private String msg;
    private String uri;

    public NewStockResponseDTO() {
    }

    public NewStockResponseDTO(String code, String msg, String uri) {
        this.code = code;
        this.msg = msg;
        this.uri = uri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

package com.mercadolibre.federico_rivarola_pf.dtos.responses;

import java.util.List;

public class QueryPartsDTO {
    List<QueryPartUnitDTO> parts;

    public QueryPartsDTO() {
    }

    public QueryPartsDTO(List<QueryPartUnitDTO> parts) {
        this.parts = parts;
    }

    public List<QueryPartUnitDTO> getParts() {
        return parts;
    }

    public void setParts(List<QueryPartUnitDTO> parts) {
        this.parts = parts;
    }
}

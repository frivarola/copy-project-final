package com.mercadolibre.federico_rivarola_pf.dtos.responses;

import com.mercadolibre.federico_rivarola_pf.dtos.OrderDTO;
import com.mercadolibre.federico_rivarola_pf.model.OrderDetailCM;

import java.util.List;

public class OrderResponseDTO {
    private String dealerNumber;
    private List<OrderDTO> orders;
}

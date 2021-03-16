package com.mercadolibre.federico_rivarola_pf.controller;

import com.mercadolibre.federico_rivarola_pf.dtos.responses.OrderResponseDTO;
import com.mercadolibre.federico_rivarola_pf.services.OrdersManagementService;
import com.mercadolibre.federico_rivarola_pf.util.enums.OrderType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "api/v1/parts/orders")
public class OrdersController {

    private final OrdersManagementService ordersService;

    public OrdersController(OrdersManagementService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping(params = {"dealerNumber", "deliveryStatus"})
    public OrderResponseDTO getOrder(@RequestParam String dealerNumber, @RequestParam String deliveryStatus){

        return ordersService.getByDealerNumberAndDeliveryStatus(dealerNumber, deliveryStatus);
    }

    @GetMapping(params = {"dealerNumber", "deliveryStatus", "order"})
    public OrderResponseDTO getOrder(@RequestParam String dealerNumber,
                                   @RequestParam String deliveryStatus, @RequestParam Integer order){

        return ordersService.getByDealerNumberAndDeliveryStatusSorter(dealerNumber, deliveryStatus, order);
    }

    @GetMapping(params = {"dealerNumber"})
    public OrderResponseDTO getOrder(@RequestParam String dealerNumber){

        return ordersService.getByDealerNumber(dealerNumber);
    }

    @GetMapping(params = {"dealerNumber", "order"})
    public OrderResponseDTO getOrder(@RequestParam String dealerNumber,
                                   @RequestParam Integer order){

        return ordersService.getByDealerNumberSorter(dealerNumber, order);
    }
}

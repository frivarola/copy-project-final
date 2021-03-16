package com.mercadolibre.federico_rivarola_pf.controller;

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
    public ResponseEntity getOrder(@RequestParam String dealerNumber, @RequestParam String deliveryStatus){

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(params = {"dealerNumber", "deliveryStatus", "order"})
    public ResponseEntity getOrder(@RequestParam String dealerNumber,
                                   @RequestParam String deliveryStatus, @RequestParam OrderType order){

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(params = {"dealerNumber"})
    public ResponseEntity getOrder(@RequestParam String dealerNumber){

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(params = {"dealerNumber", "order"})
    public ResponseEntity getOrder(@RequestParam String dealerNumber,
                                   @RequestParam OrderType order){

        return new ResponseEntity(HttpStatus.OK);
    }
}

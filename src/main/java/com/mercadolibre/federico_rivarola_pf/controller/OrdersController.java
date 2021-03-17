package com.mercadolibre.federico_rivarola_pf.controller;

import com.mercadolibre.federico_rivarola_pf.dtos.OrderDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.requests.NewOrderRequestDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.NewOrderResponseDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.OrderResponseDTO;
import com.mercadolibre.federico_rivarola_pf.services.OrdersManagementService;
import com.mercadolibre.federico_rivarola_pf.util.enums.OrderType;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "api/v1/parts/orders")
public class OrdersController {

    private final OrdersManagementService ordersService;

    public OrdersController(OrdersManagementService ordersService) {
        this.ordersService = ordersService;
    }

    @RequestMapping(value="/{orderNumber}", method = RequestMethod.GET)
    public OrderDTO getOrderUnit(@PathVariable(value = "orderNumber") String orderNumber){
        return ordersService.getByOrderNumberCM(orderNumber);
    }

    @GetMapping(params = {"dealerNumber", "deliveryStatus"})
    public OrderResponseDTO getByDealerNumberAndDeliveryStatus(@RequestParam String dealerNumber, @RequestParam String deliveryStatus){

        return ordersService.getByDealerNumberAndDeliveryStatus(dealerNumber, deliveryStatus);
    }

    @GetMapping(params = {"dealerNumber", "deliveryStatus", "order"})
    public OrderResponseDTO getByDealerNumberAndDeliveryStatusSorter(@RequestParam String dealerNumber,
                                   @RequestParam String deliveryStatus, @RequestParam Integer order){

        return ordersService.getByDealerNumberAndDeliveryStatusSorter(dealerNumber, deliveryStatus, order);
    }

    @GetMapping(params = {"dealerNumber"})
    public OrderResponseDTO getByDealerNumber(@RequestParam String dealerNumber){

        return ordersService.getByDealerNumber(dealerNumber);
    }

    @GetMapping(params = {"dealerNumber", "order"})
    public OrderResponseDTO getByDealerNumberSorter(@RequestParam String dealerNumber,
                                   @RequestParam Integer order){

        return ordersService.getByDealerNumberSorter(dealerNumber, order);
    }

    @PostMapping()
    public NewOrderResponseDTO createOrder(@RequestBody NewOrderRequestDTO newOrder){
        return ordersService.createOrder(newOrder);
    }

}

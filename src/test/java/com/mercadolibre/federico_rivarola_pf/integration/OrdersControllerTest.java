package com.mercadolibre.federico_rivarola_pf.integration;

import com.mercadolibre.federico_rivarola_pf.controller.OrdersController;
import com.mercadolibre.federico_rivarola_pf.model.Dealer;
import com.mercadolibre.federico_rivarola_pf.model.OrderCM;
import com.mercadolibre.federico_rivarola_pf.repositories.IOrdersRepository;
import com.mercadolibre.federico_rivarola_pf.services.OrdersManagementService;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IOrdersManagementService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(OrdersController.class)
public class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private OrdersManagementService service;

    @MockBean
    private IOrdersRepository ordersRepository;

    private String url = "/api/v1/parts/orders";

    @Test
    void findWithInvalidDealer_shouldFail() throws Exception {
        List<OrderCM> orders = new ArrayList<>();
        when(ordersRepository.findByDealerNumber(anyString())).thenReturn(orders);
        this.mockMvc.perform((get(url + "?dealerNumber=001"))).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void findWithValidDealer_shouldWork() throws Exception {
        List<OrderCM> orders = new ArrayList<>();
        when(ordersRepository.findByDealerNumber(anyString())).thenReturn(orders);
        this.mockMvc.perform((get(url + "?dealerNumber=001"))).andDo(print()).andExpect(status().isOk());
    }


    List<OrderCM> createOrders(){
        List<OrderCM> result = new ArrayList<>();
        OrderCM o = new OrderCM();

        result.add(o);

        return result;
    }
}

package com.mercadolibre.federico_rivarola_pf.integration;

import com.mercadolibre.federico_rivarola_pf.Application;
import com.mercadolibre.federico_rivarola_pf.controller.AuthenticationController;
import com.mercadolibre.federico_rivarola_pf.controller.OrdersController;
import com.mercadolibre.federico_rivarola_pf.model.Dealer;
import com.mercadolibre.federico_rivarola_pf.model.OrderCM;
import com.mercadolibre.federico_rivarola_pf.repositories.IOrdersRepository;
import com.mercadolibre.federico_rivarola_pf.services.OrdersManagementService;
import com.mercadolibre.federico_rivarola_pf.services.UserService;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IOrdersManagementService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes=Application.class)
@WebMvcTest(OrdersController.class)
@ActiveProfiles("test")
public class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private String url = "/api/v1/parts/orders";
    private String token;



    @Test
    void shouldReturnUnauthorizedTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/parts/orders")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldFailOnMissingDealerTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/parts/orders")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldFailOnWrongDeliveryStatusTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/parts/orders")
                .header("Authorization", this.token)
                .queryParam("dealer", "0001")
                .queryParam("deliveryStatus", "X")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNotFoundTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/parts/orders")
                .header("Authorization", this.token)
                .queryParam("dealer", "1111")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnOkTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/parts/orders")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("dealer", "0001"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkDeliveryStatusTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/parts/orders")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("dealer", "0001")
                .queryParam("deliveryStatus", "P"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldFailOnInvalidNumberTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/parts/orders/0001-123123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldWorkOnValidNumberTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/parts/orders/0001-0001-00000001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}

package com.mercadolibre.federico_rivarola_pf.integration;

import com.mercadolibre.federico_rivarola_pf.controller.PartManagementController;
import com.mercadolibre.federico_rivarola_pf.repositories.*;
import com.mercadolibre.federico_rivarola_pf.services.PartManagementService;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IPartManagementService;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IPartRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebMvcTest(PartManagementController.class)
public class PartManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPartsRepository partsRepository;
    @MockBean
    private IStockRepository stockRepository;
    @MockBean
    private IPartsRecordRepository partsRecordRepository;
    @MockBean
    private ISubsidiaryRepository subsidiaryRepository;

    @SpyBean
    private PartManagementService service;
    @InjectMocks
    private PartManagementController controller;

    @Test
    void findWithValidUser(){

    }

    @Test
    void findWithInvalidUser(){

    }

    @Test
    void findWithAfterDate(){

    }

    @Test
    void InvalidQuerytype(){

    }

    @Test
    void ValidQuerytype(){

    }

    @Test
    void shouldFailSaveStock_InvalidPart() throws Exception {
        //when(parts ).thenReturn();
        this.mockMvc.perform(post("/api/v1/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idPart\": \"AAaa10\", \"quantity\": 100}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldFailSaveStock_InvalidQuantity() throws Exception {
        //when(parts ).thenReturn();
        this.mockMvc.perform(post("/api/v1/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idPart\": \"AA10\", \"quantity\": -100}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldWorkSaveStock() throws Exception {
        //when(parts ).thenReturn();
        this.mockMvc.perform(post("/api/v1/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idPart\": \"AA10\", \"quantity\": 100}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}

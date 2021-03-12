package com.mercadolibre.federico_rivarola_pf.integration;

import com.mercadolibre.federico_rivarola_pf.controller.PartManagementController;
import com.mercadolibre.federico_rivarola_pf.repositories.IPartsRecordRepository;
import com.mercadolibre.federico_rivarola_pf.repositories.IPartsRepository;
import com.mercadolibre.federico_rivarola_pf.repositories.IProviderRepository;
import com.mercadolibre.federico_rivarola_pf.repositories.IStockRepository;
import com.mercadolibre.federico_rivarola_pf.services.PartManagementService;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IPartManagementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@WebMvcTest(PartManagementController.class)
public class PartManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPartManagementService service;

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


}

package com.mercadolibre.federico_rivarola_pf.integration;

import com.mercadolibre.federico_rivarola_pf.controller.PartManagementController;
import com.mercadolibre.federico_rivarola_pf.repositories.IPartsRecordRepository;
import com.mercadolibre.federico_rivarola_pf.repositories.IPartsRepository;
import com.mercadolibre.federico_rivarola_pf.repositories.IProviderRepository;
import com.mercadolibre.federico_rivarola_pf.repositories.IStockRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PartManagementController.class)
public class PartManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IPartsRecordRepository mockPartsRecordRepository;
    @MockBean
    private IStockRepository mockStockRepository;
    @MockBean
    private IProviderRepository providerRepository;
    @MockBean
    private IPartsRepository partsRepository;
}

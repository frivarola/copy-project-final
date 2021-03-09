package com.mercadolibre.federico_rivarola_pf.controller;

import com.mercadolibre.federico_rivarola_pf.services.PartManagementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/parts")
public class PartManagementController {
    PartManagementService partManagementService;

    public PartManagementController(PartManagementService partManagementService) {
        this.partManagementService = partManagementService;
    }


}

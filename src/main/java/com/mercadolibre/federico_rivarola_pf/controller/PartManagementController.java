package com.mercadolibre.federico_rivarola_pf.controller;

import com.mercadolibre.federico_rivarola_pf.services.PartManagmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/parts")
public class PartManagementController {
    PartManagmentService partManagmentService;

    public PartManagementController(PartManagmentService partManagmentService) {
        this.partManagmentService = partManagmentService;
    }


}

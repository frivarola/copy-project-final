package com.mercadolibre.federico_rivarola_pf.controller;

import com.mercadolibre.federico_rivarola_pf.dtos.PartDTO;
import com.mercadolibre.federico_rivarola_pf.services.PartManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/parts")
public class PartManagementController {
    private PartManagementService partManagementService;

    public PartManagementController(PartManagementService partManagementService) {
        this.partManagementService = partManagementService;
    }

    @GetMapping()
    List<PartDTO> getAll(){
        return partManagementService.getAll();
    }

}

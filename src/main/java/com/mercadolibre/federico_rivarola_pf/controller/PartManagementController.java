package com.mercadolibre.federico_rivarola_pf.controller;

import com.mercadolibre.federico_rivarola_pf.dtos.PartDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.QueryPartsDTO;
import com.mercadolibre.federico_rivarola_pf.services.PartManagementService;
import com.mercadolibre.federico_rivarola_pf.util.enums.Querytype;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/parts")
public class PartManagementController {
    private PartManagementService partManagementService;

    public PartManagementController(PartManagementService partManagementService) {
        this.partManagementService = partManagementService;
    }

    @GetMapping()
    QueryPartsDTO getAll(){
        return partManagementService.getAll();
    }

    @GetMapping(path = "/list")
    QueryPartsDTO getByQuerytypeAndDate(@RequestParam Querytype querytype, @RequestParam String date){
        return partManagementService.getAllByQueryTypeAndDate(querytype, date);
    }

}

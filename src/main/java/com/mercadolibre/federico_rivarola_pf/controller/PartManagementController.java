package com.mercadolibre.federico_rivarola_pf.controller;

import com.mercadolibre.federico_rivarola_pf.dtos.PartDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.requests.NewStockDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.NewStockResponseDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.QueryPartsDTO;
import com.mercadolibre.federico_rivarola_pf.services.PartManagementService;
import com.mercadolibre.federico_rivarola_pf.util.enums.OrderType;
import com.mercadolibre.federico_rivarola_pf.util.enums.Querytype;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/list", params = {"querytype", "date", "order"})
    QueryPartsDTO getByQuerytypeAndDateSorter(@RequestParam Querytype querytype, @RequestParam String date, @RequestParam OrderType order){
        return partManagementService.getAllByQueryTypeAndDateSorter(querytype, date, order);
    }

    @PostMapping()
    NewStockResponseDTO updateStockBySubsidiaryAndIdPart(@RequestBody NewStockDTO newStockDTO){
        return partManagementService.saveStock(newStockDTO);
    }

}

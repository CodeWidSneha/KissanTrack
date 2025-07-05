package com.agri.kissanTrack.controller;

import com.agri.kissanTrack.dto.*;
import com.agri.kissanTrack.service.SupplierManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierManagementService supplierManagementService;


    @GetMapping
    public GetAllSuppliersRespDTO getSuppliers(){
        return supplierManagementService.getEntities();

    }

    @PostMapping
    public SupplierDTO saveSupplier(@RequestBody SaveSupplierReq supplier){

        return supplierManagementService.saveEntity(supplier);
    }
    @GetMapping("/{id}")
    public SupplierDTO getSupplier(@PathVariable Long id){
        return supplierManagementService.getSupplierById(id);

    }
}

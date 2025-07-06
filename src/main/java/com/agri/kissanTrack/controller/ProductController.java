package com.agri.kissanTrack.controller;

import com.agri.kissanTrack.dto.GetAllProductsRespDTO;
import com.agri.kissanTrack.dto.GetAllSuppliersRespDTO;
import com.agri.kissanTrack.dto.ProductDTO;
import com.agri.kissanTrack.entities.Product;
import com.agri.kissanTrack.dto.SaveProductReq;
import com.agri.kissanTrack.service.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductManagementService productManagementService;


    @GetMapping
    public GetAllProductsRespDTO getProducts(){
        return productManagementService.getEntities();

    }

    @PostMapping
    public Product saveProduct(@RequestBody SaveProductReq product){

        return productManagementService.saveEntity(product);
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id){
        return productManagementService.getProductById(id);

    }

    @GetMapping("/greaterThan/{price}")
    public GetAllProductsRespDTO getProductsGreaterThanPrice(@PathVariable double price){
        return productManagementService.getProductsGreaterThan(price);

    }

    @GetMapping("/startsWith/{prefix}")
    public GetAllProductsRespDTO getProductsStartingWithPrefix(@PathVariable String prefix){
        return productManagementService.getProductsStartsWith(prefix);
    }

}


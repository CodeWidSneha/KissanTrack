package com.agri.kissanTrack.dto;

import java.util.List;

public class GetAllProductsRespDTO implements ResponseDTO{

    private String message;
    private List<ProductDTO> products;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}

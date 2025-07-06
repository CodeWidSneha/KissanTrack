package com.agri.kissanTrack.utility;

import com.agri.kissanTrack.dto.GetAllProductsRespDTO;
import com.agri.kissanTrack.dto.ProductDTO;
import com.agri.kissanTrack.entities.Product;

import java.util.ArrayList;
import java.util.List;

public final class ProductConverter {

    public static ProductDTO getProductDTOFromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setSupplierId(product.getSupplier().getSupplierId());
        return productDTO;
    }

    public static void getProductResponseFromEntities(GetAllProductsRespDTO getAllProductsRespDTO, List<Product> products, String message) {
        getAllProductsRespDTO.setMessage(message);
        List<ProductDTO> productDTOS = new ArrayList<>();

        products.forEach(product -> {

            ProductDTO productDTO = ProductConverter.getProductDTOFromProduct(product);
            productDTOS.add(productDTO);
        });
        getAllProductsRespDTO.setProducts(productDTOS);
    }
}

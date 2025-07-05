package com.agri.kissanTrack.utility;

import com.agri.kissanTrack.dto.ProductDTO;
import com.agri.kissanTrack.entities.Product;

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
}

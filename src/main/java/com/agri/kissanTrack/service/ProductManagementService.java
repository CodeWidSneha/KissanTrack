package com.agri.kissanTrack.service;

import com.agri.kissanTrack.dto.GetAllProductsRespDTO;
import com.agri.kissanTrack.dto.ProductDTO;
import com.agri.kissanTrack.entities.Product;
import com.agri.kissanTrack.dto.SaveProductReq;
import com.agri.kissanTrack.entities.Supplier;
import com.agri.kissanTrack.exception.DatabaseInteractionException;
import com.agri.kissanTrack.exception.ProductNotFoundException;
import com.agri.kissanTrack.repository.ProductRepository;
import com.agri.kissanTrack.repository.SupplierRepository;
import com.agri.kissanTrack.utility.ProductConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductManagementService {

    private static Logger LOG = LoggerFactory.getLogger(ProductManagementService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public GetAllProductsRespDTO getEntities(){

        GetAllProductsRespDTO getAllProductsRespDTO = new GetAllProductsRespDTO();
        List<Product> products = new ArrayList<>();
        try{
            products = productRepository.findAll();


            getAllProductsRespDTO.setMessage("Fetched product successfully");
            List<ProductDTO> productDTOS = new ArrayList<>();

            products.forEach(product -> {

                ProductDTO productDTO = ProductConverter.getProductDTOFromProduct(product);
                productDTOS.add(productDTO);
            });
            getAllProductsRespDTO.setProducts(productDTOS);



        }
        catch (Exception e){
            LOG.error("Exception occurred while fetching Products from DB ", e);
            throw new DatabaseInteractionException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());

        }
        return getAllProductsRespDTO;
    }



    public Product saveEntity(SaveProductReq product){


        Long supplierId= product.getSupplierId();
        AtomicReference<Product> productSaved = new AtomicReference<>();
        try{
            Optional<Supplier> supplier = supplierRepository.findById(supplierId);
            supplier.ifPresent(sup ->{

                Product product1 = new Product();
                product1.setName(product.getName());
                product1.setPrice(product.getPrice());
                product1.setQuantity(product.getQuantity());
                product1.setSupplier(sup);
                productSaved.set(productRepository.save(product1));

            });
            if(supplier.isEmpty())
            {
                LOG.error("No supplier found, data not saved");
                return null;
            }

        }
        catch (Exception e){
            LOG.error("Exception occurred while saving Product from DB ", e);

        }
        return productSaved.get();

    }

    public ProductDTO getProductById(Long id){

        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return ProductConverter.getProductDTOFromProduct(optionalProduct.get());

        }
        else{
            LOG.error("Exception occurred as no product exists with the requested id : {}", id);
            throw new ProductNotFoundException(404, "No product exists with the requested id");

        }

    }

}

package com.agri.kissanTrack.service;

import com.agri.kissanTrack.dto.*;
import com.agri.kissanTrack.entities.Product;
import com.agri.kissanTrack.entities.Supplier;
import com.agri.kissanTrack.exception.DatabaseInteractionException;
import com.agri.kissanTrack.exception.ProductNotFoundException;
import com.agri.kissanTrack.exception.SupplierNotFoundException;
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
        List<Product> products;
        try{
            products = productRepository.findAll();
            String message;
            if(!products.isEmpty()){
                message="Fetched product successfully";

            }
            else{
                message = "No products in DB";
            }
            ProductConverter.getProductResponseFromEntities(getAllProductsRespDTO, products, message);

        }
        catch (Exception e){
            LOG.error("Exception occurred while fetching Products from DB ", e);
            throw new DatabaseInteractionException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());

        }
        return getAllProductsRespDTO;
    }




    public Product saveEntity(ProductReq product){

        return saveProductInDb(product, -1L);

    }

    private Product saveProductInDb(ProductReq product, Long id) {

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
                if (id !=-1){
                    product1.setId(id);
                }
                productSaved.set(productRepository.save(product1));

            });
            handleWhenSupplierNotFound(supplier);

        }
        catch (Exception e){
            LOG.error("Exception occurred while saving Product from DB ", e);
            throw new DatabaseInteractionException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exception occurred while saving Product from DB");

        }
        return productSaved.get();
    }

    private static void handleWhenSupplierNotFound(Optional<Supplier> supplier) {
        if(supplier.isEmpty())
        {
            LOG.error("No supplier found, data not saved");
            throw new SupplierNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Supplier does not exist with the requested id");
        }
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

    public GetAllProductsRespDTO getProductsGreaterThan(double price){
        GetAllProductsRespDTO getAllProductsRespDTO = new GetAllProductsRespDTO();
        List<Product> products = productRepository.findByPriceGreaterThan(price);
        if (products.isEmpty()){
            LOG.error("Exception occurred as no product exists which is greater than the requested price : {}",price);
            throw new ProductNotFoundException(404,"No product exists which is greater than the requested price");
        }
        else{

            ProductConverter.getProductResponseFromEntities(getAllProductsRespDTO, products,"Fetched products whose price is greater than "+price);

        }
        return getAllProductsRespDTO;

    }

    public GetAllProductsRespDTO getProductsStartsWith(String prefix){
        GetAllProductsRespDTO getAllProductsRespDTO = new GetAllProductsRespDTO();
        List<Product> products = productRepository.fetchProductsWithNamesStartWith(prefix);
        if (products.isEmpty()){
            LOG.error("Exception occurred as no product exists with the requested prefix : {}",prefix);
            throw new ProductNotFoundException(404,"No product exists with the requested prefix");
        }
        else{

            ProductConverter.getProductResponseFromEntities(getAllProductsRespDTO, products,"Fetched products whose name starts with "+prefix);

        }
        return getAllProductsRespDTO;

    }

    public ProductDTO updateProduct(Long id, ProductReq productReq){

        Optional<Product> optionalProduct;
        try{
            optionalProduct = productRepository.findById(id);

        }
        catch (Exception e){
            LOG.error("Exception occurred while updating Product", e);
            throw new DatabaseInteractionException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Exception occurred while updating Product");

        }

        if(optionalProduct.isPresent()){

            Optional<Supplier> supplier = supplierRepository.findById(productReq.getSupplierId());
            if(supplier.isPresent())
            {
                Product updatedProduct = saveProductInDb(productReq, id);
                return  ProductConverter.getProductDTOFromProduct(updatedProduct);
            }
            else {
                handleWhenSupplierNotFound(supplier);
                return null;
            }
        }
        else{
            LOG.error("Exception occurred as no product exists with the requested id : {}", id);
            throw new ProductNotFoundException(404, "No product exists with the requested id");

        }

    }
    public ResponseDTO deleteProduct(Long id){
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                productRepository.deleteById(id);
                return new DeleteSuccessResponse("Product deleted successfully");

            }
            else{
                LOG.error("Exception occurred as no product exists with the requested id : {}", id);
                throw new ProductNotFoundException(404, "No product exists with the requested id");

            }

        }

        catch (Exception e){
                LOG.error("Exception occurred while deleting Product", e);
                throw new DatabaseInteractionException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Exception occurred while deleting Product");

            }


    }

}

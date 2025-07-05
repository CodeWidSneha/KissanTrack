package com.agri.kissanTrack.service;

import com.agri.kissanTrack.dto.*;
import com.agri.kissanTrack.entities.Product;
import com.agri.kissanTrack.entities.Supplier;
import com.agri.kissanTrack.exception.DatabaseInteractionException;
import com.agri.kissanTrack.exception.ProductNotFoundException;
import com.agri.kissanTrack.exception.SupplierNotFoundException;
import com.agri.kissanTrack.repository.SupplierRepository;
import com.agri.kissanTrack.utility.ProductConverter;
import com.agri.kissanTrack.utility.SupplierConverter;
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
public class SupplierManagementService {

    private static Logger LOG = LoggerFactory.getLogger(SupplierManagementService.class);

    @Autowired
    private SupplierRepository supplierRepository;

    public GetAllSuppliersRespDTO getEntities() {

        GetAllSuppliersRespDTO getAllSuppliersRespDTO = new GetAllSuppliersRespDTO();
        List<Supplier> suppliers = new ArrayList<>();
        try {
            suppliers = supplierRepository.findAll();

            getAllSuppliersRespDTO.setMessage("Fetched supplier successfully");
            List<SupplierDTO> supplierDTOS = new ArrayList<>();

            suppliers.forEach(supplier -> {

                SupplierDTO supplierDTO = new SupplierDTO();
                supplierDTO.setSupplierName(supplier.getSupplierName());
                supplierDTO.setSupplierLocation(supplier.getSupplierLocation());
                supplierDTO.setSupplierContact(supplier.getSupplierContact());
                supplierDTO.setSupplierEmail(supplier.getSupplierEmail());
                supplierDTO.setSupplierId(supplier.getSupplierId());
                supplierDTOS.add(supplierDTO);
            });
            getAllSuppliersRespDTO.setSuppliers(supplierDTOS);
            return getAllSuppliersRespDTO;
        } catch (Exception e) {
            LOG.error("Exception occurred while fetching Suppliers from DB ", e);
            throw new DatabaseInteractionException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());

        }
    }

    public SupplierDTO saveEntity(SaveSupplierReq supplierReq) {

        //validation on request data


        Supplier supplier = new Supplier();
        supplier.setSupplierName(supplierReq.getSupplierName());
        supplier.setSupplierLocation(supplierReq.getSupplierLocation());
        supplier.setSupplierContact(supplier.getSupplierContact());
        supplier.setSupplierEmail(supplier.getSupplierEmail());

        Supplier savedSupplier = supplierRepository.save(supplier);

        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierEmail(savedSupplier.getSupplierEmail());
        supplierDTO.setSupplierName(savedSupplier.getSupplierName());
        supplierDTO.setSupplierLocation(savedSupplier.getSupplierLocation());
        supplierDTO.setSupplierId(savedSupplier.getSupplierId());
        supplierDTO.setSupplierContact(savedSupplier.getSupplierContact());

        return supplierDTO;


    }
    public SupplierDTO getSupplierById(Long id) {

        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            return SupplierConverter.getSupplierDTOFromSupplier(optionalSupplier.get());

        } else {
            LOG.error("Exception occurred as no supplier exists with the requested id : {}", id);
            throw new SupplierNotFoundException(404, "No supplier exists with the requested id");

        }

    }
}





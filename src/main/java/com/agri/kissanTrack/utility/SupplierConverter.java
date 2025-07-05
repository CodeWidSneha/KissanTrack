package com.agri.kissanTrack.utility;

import com.agri.kissanTrack.dto.ProductDTO;
import com.agri.kissanTrack.dto.SupplierDTO;
import com.agri.kissanTrack.entities.Product;
import com.agri.kissanTrack.entities.Supplier;

public final class SupplierConverter {
    public static SupplierDTO getSupplierDTOFromSupplier(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId(supplier.getSupplierId());
        supplierDTO.setSupplierName(supplier.getSupplierName());
        supplierDTO.setSupplierLocation(supplier.getSupplierLocation());
        supplierDTO.setSupplierContact(supplier.getSupplierContact());
        supplierDTO.setSupplierEmail(supplier.getSupplierEmail());
        return supplierDTO;
    }


}

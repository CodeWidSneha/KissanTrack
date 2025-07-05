package com.agri.kissanTrack.dto;

import java.util.List;

public class GetAllSuppliersRespDTO implements ResponseDTO {

        private String message;
        private List<SupplierDTO> suppliers;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<SupplierDTO> getSuppliers() {
            return suppliers;
        }

        public void setSuppliers(List<SupplierDTO> suppliers) {
            this.suppliers = suppliers;
        }
}

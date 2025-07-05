package com.agri.kissanTrack.dto;

public class SupplierDTO {

    private Long supplierId;

    private String supplierName;
    private String supplierLocation;
    private long supplierContact;
    private String supplierEmail;

    public String getSupplierName() {
        return supplierName;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierLocation() {
        return supplierLocation;
    }

    public void setSupplierLocation(String supplierLocation) {
        this.supplierLocation = supplierLocation;
    }

    public long getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(long supplierContact) {
        this.supplierContact = supplierContact;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }
}

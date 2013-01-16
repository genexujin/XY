package com.xy.scpms.view.flow.backing;


import com.xy.scpms.model.utils.Codes;
import com.xy.scpms.view.Constants;

import java.io.Serializable;

import oracle.jbo.domain.Number;


public class CreationFlowBean implements Serializable {


    private String creationType;
    private Number parentContractId;
    private String searchCustomerType;
    private String customerId;
    private String customerName;
    private String contactId;
    private String contactName;
    
    
    //below three constants are used in editline page
    //to determine if respective field should be disabled.
    private String customerTypePlant = Codes.AUTH_TYPE_PLANT;
    private String customerTypeBroker = Codes.AUTH_TYPE_BROKER;
    private String customerTypeOwner = Codes.AUTH_TYPE_OWNER;

    public boolean isDuplicate() {
        return this.creationType.equals(Constants.FLOW_CREATION_TYPE_DUPLICATED);
    }

    public boolean isAgreement() {
        return this.creationType.equals(Constants.FLOW_CREATION_TYPE_AGREEMENT);
    }

    public void setCreationType(String creationType) {
        this.creationType = creationType;
    }

    public String getCreationType() {
        return creationType;
    }

    public void setParentContractId(Number parentContractId) {
        this.parentContractId = parentContractId;
    }

    public Number getParentContractId() {
        return parentContractId;
    }

    public void setCustomerTypePlant(String customerTypePlant) {
        this.customerTypePlant = customerTypePlant;
    }

    public String getCustomerTypePlant() {
        return customerTypePlant;
    }

    public void setCustomerTypeBroker(String customerTypeBroker) {
        this.customerTypeBroker = customerTypeBroker;
    }

    public String getCustomerTypeBroker() {
        return customerTypeBroker;
    }

    public void setCustomerTypeOwner(String customerTypeOwner) {
        this.customerTypeOwner = customerTypeOwner;
    }

    public String getCustomerTypeOwner() {
        return customerTypeOwner;
    }

    public void setSearchCustomerType(String searchCustomerType) {
        this.searchCustomerType = searchCustomerType;
    }

    public String getSearchCustomerType() {
        return searchCustomerType;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactName() {
        return contactName;
    }

 
}

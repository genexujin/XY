package com.xy.scpms.view.page;


import com.xy.view.utils.ADFUtils;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.Row;


public class LovBean {
    public LovBean() {
    }
    
    private void createLov(String lookupCode, String iteratorName){
        try {
            DCIteratorBinding it = ADFUtils.findIterator(iteratorName);
            NavigatableRowIterator iterator = it.getNavigatableRowIterator();
            Row row = iterator.createRow();
            row.setAttribute("LookupCode",lookupCode);
            row.setAttribute("LookupValue","");
            iterator.insertRow(row);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
    }
    
    public String createDesignPhase() {
        createLov("DESIGN_PHASE","DesignPhaseIterator");
        
        return null;
    }

    public String createAuthType() {
        createLov("AUTH_TYPE","AuthTypeIterator");
        
        return null;
    }

    public String createShipType() {
        createLov("SHIP_TYPE","ShipTypeIterator");
        return null;
    }

    public String createPaymentName() {
        createLov("PAYMENT_NAME","PaymentNameIterator");
        return null;
    }

    public String createOwnerType() {
        createLov("OWNER_TYPE","OwnerTypeIterator");
        return null;
    }

    public String createPlantType() {
        createLov("PLANT_TYPE","PlantTypeIterator");
        return null;
    }

    public String createBrokerType() {
        createLov("BROKER_TYPE","BrokerTypeIterator");
        return null;
    }
}

package com.xy.scpms.view.backing;


import com.xy.view.utils.ADFUtils;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.event.QueryEvent;
import oracle.adf.view.rich.render.ClientEvent;


public class CustomerBean {
    
    //三个输入/输出参数
    private String custType;
    private String custId;
    private String custName;
    private String contactName;
    private String contactId;
    
    public CustomerBean() {
    }
    
    public String selectContact() {
        contactId = (String)ADFUtils.getBoundAttributeValue("Id");
        contactName = (String)ADFUtils.getBoundAttributeValue("Name");
        return "return";
    }
    public void selectContact(ClientEvent clientEvent) {
        selectContact();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,
                                                                              null,
                                                                              "return");
        
    }
    
    public String selectCustomer() {
        custId = (String)ADFUtils.getBoundAttributeValue("Id");
        custName = (String)ADFUtils.getBoundAttributeValue("CustomerName");
        return "return";
    }

    public void selectCustomer(ClientEvent clientEvent) {
        
        custId = (String)ADFUtils.getBoundAttributeValue("Id");
        custName = (String)ADFUtils.getBoundAttributeValue("CustomerName");
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,
                                                                              null,
                                                                              "return");
    }
    
    public void onQuery(QueryEvent queryEvent) {
       
//        ADFUtils.findOperation("setQueryType").execute();
        //执行query
        invokeQueryEventMethodExpression("#{bindings.ImplicitViewCriteriaQuery.processQuery}",
                                         queryEvent);

    }
    
    private void invokeQueryEventMethodExpression(String expression,
                                                  QueryEvent queryEvent) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ELContext elctx = fctx.getELContext();
        ExpressionFactory efactory =
            fctx.getApplication().getExpressionFactory();
        MethodExpression me =
            efactory.createMethodExpression(elctx, expression, Object.class,
                                            new Class[] { QueryEvent.class });
        me.invoke(elctx, new Object[] { queryEvent });
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustName() {
        return custName;
    }


    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactId() {
        return contactId;
    }
}

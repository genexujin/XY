package edu.hp.view.bean.po;

import edu.hp.view.utils.ADFUtils;

import java.sql.Date;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

public class MyPoBean {
    private String orderReadableId;
    private Date submitDateFrom;
    private Date submitDateTo;
    private RichTable resultTable;

    public MyPoBean() {
    }

    public String doQuery() {
        String poStateId = getLovAttrValue("PoState", "FlexCol1");
        System.out.println("PoState Id is: " + poStateId);
        String itemCategoryId = getLovAttrValue("ItemCategory", "Id");
        System.out.println("ItemCategory Id is: " + itemCategoryId);
//        String submitterId = getLovAttrValue("SubmitterId", "Id");
//        System.out.println("submitterId is: " + submitterId);
        
        System.out.println("OrderReadableId is: " + orderReadableId);
        System.out.println("SubmitDateFrom is: " + submitDateFrom);
        System.out.println("SubmitDateTo is: " + submitDateTo);
        
        OperationBinding binding = ADFUtils.findOperation("doQuery");
        binding.getParamsMap().put("oRdId", orderReadableId);
        binding.getParamsMap().put("state", poStateId);
        binding.getParamsMap().put("category", itemCategoryId);
        binding.getParamsMap().put("submitDateFrom", submitDateFrom);
        binding.getParamsMap().put("submitDateTo", submitDateTo);
        binding.execute();
        
        ADFUtils.partialRefreshComponenet(resultTable);
        
        return null;
    }
    
    private String getLovAttrValue(String lovBindingName, String attrName) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        JUCtrlListBinding binding = (JUCtrlListBinding)bindings.get(lovBindingName);
        Row row = binding.getCurrentRow();
        Object value = row.getAttribute(attrName);
        if (value instanceof DBSequence) {
            return ((DBSequence)value).getValue() + "";
        }
        return value.toString();
    }

    public void setOrderReadableId(String orderReadableId) {
        this.orderReadableId = orderReadableId;
    }

    public String getOrderReadableId() {
        return orderReadableId;
    }

    public void setSubmitDateFrom(Date submitDateFrom) {
        this.submitDateFrom = submitDateFrom;
    }

    public Date getSubmitDateFrom() {
        return submitDateFrom;
    }

    public void setSubmitDateTo(Date submitDateTo) {
        this.submitDateTo = submitDateTo;
    }

    public Date getSubmitDateTo() {
        return submitDateTo;
    }

    public void setResultTable(RichTable resultTable) {
        this.resultTable = resultTable;
    }

    public RichTable getResultTable() {
        return resultTable;
    }

    public void submitPo(ActionEvent actionEvent) {
        toState("2");
    }

    public void verifyPo(ActionEvent actionEvent) {
        toState("3");
    }

    public void approvePo(ActionEvent actionEvent) {
        toState("5");
    }
    
    public void rejectPo(ActionEvent actionEvent) {
        toState("4");
    }
    
    private void toState(String state) {
        //DCIteratorBinding binding = ADFUtils.findIterator("HelpdeskCallsViewIterator");
        //Row row = binding.getCurrentRow();
        //row.setAttribute("State", state);
        ADFUtils.setBoundAttributeValue("State", state);
    //        ADFUtils.setBoundAttributeValue("CallId", 100);
        OperationBinding oper = ADFUtils.findOperation("Commit");
        oper.execute();
    }

    public void cancelPo(ActionEvent actionEvent) {
        toState("7");
    }

    public void itemCategoryChanged(ValueChangeEvent valueChangeEvent) {
        DBSequence newValue = (DBSequence)valueChangeEvent.getNewValue();
        System.out.println("autoSubmit alone is not working");
        System.out.println("setBinding is also not working. Probably a bug for ADF, because on page, the values for option are alwasy 0, 1, 2......");
//        long categoryId = newValue.getValue();
//        DCIteratorBinding binding = ADFUtils.findIterator("PurchaseOrdersViewIterator");
//        binding.getCurrentRow().setAttribute("ItemCategoryId", categoryId);
    }
}

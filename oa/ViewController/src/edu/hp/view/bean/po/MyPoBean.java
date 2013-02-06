package edu.hp.view.bean.po;

import edu.hp.view.utils.ADFUtils;

import edu.hp.view.utils.JSFUtils;

import java.math.BigDecimal;

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
        Object expression = JSFUtils.resolveExpression("#{bindings.EmployeesViewForLOV.inputValue}");
        System.out.println("Expression value is: " + expression);
        String poStateId = getLovAttrValue("PoState", "FlexCol1");
        System.out.println("PoState Id is: " + poStateId);
        String itemCategoryId = getLovAttrValue("ItemCategory", "Id");
        System.out.println("ItemCategory Id is: " + itemCategoryId);
        String submitterId = getLovAttrValue("EmployeesViewForLOV", "Id");
        System.out.println("submitterId is: " + submitterId);
        
        System.out.println("OrderReadableId is: " + orderReadableId);
        System.out.println("SubmitDateFrom is: " + submitDateFrom);
        System.out.println("SubmitDateTo is: " + submitDateTo);
        
        OperationBinding binding = ADFUtils.findOperation("doQuery");
        binding.getParamsMap().put("oRdId", orderReadableId);
        binding.getParamsMap().put("state", poStateId);
        binding.getParamsMap().put("category", itemCategoryId);
        binding.getParamsMap().put("submitDateFrom", submitDateFrom);
        binding.getParamsMap().put("submitDateTo", submitDateTo);
        binding.getParamsMap().put("submitterId", submitterId);
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
        //First need to compute submit total for lines and for whole order
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        Row[] rows = lineIt.getAllRowsInRange();
        double masterTotal = 0;
        if (rows != null && rows.length > 0) {
            for (Row row : rows) {
                double price = 0;
                BigDecimal p = (BigDecimal)row.getAttribute("SubmitPrice");
                if (p != null) {
                    price = p.doubleValue();
                }
                long quantity = 0;
                BigDecimal q = (BigDecimal)row.getAttribute("SubmitQuantity");
                if (q != null) {
                    quantity = q.longValue();
                }
                System.out.println("SubmitPrice is: " + price + " SubmitQuantity is: " + quantity);
                row.setAttribute("SubmitTotal", price * quantity);
                masterTotal += price * quantity;
            }
        }
        
        System.out.println("Master SubmitTotal is: " + masterTotal);
        DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrdersViewIterator");
        Row masterRow = it.getCurrentRow();
        masterRow.setAttribute("SubmitTotal", masterTotal);

        masterRow.setAttribute("State", "2");        
//        toState("2");
    }

    public void verifyPo(ActionEvent actionEvent) {
        //First need to compute verify total for whole order
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        Row[] rows = lineIt.getAllRowsInRange();
        double masterTotal = 0;
        if (rows != null && rows.length > 0) {
            for (Row row : rows) {
                double price = 0;
                BigDecimal p = (BigDecimal)row.getAttribute("SubmitPrice");
                if (p != null) {
                    price = p.doubleValue();
                }
                long quantity = 0;
                BigDecimal q = (BigDecimal)row.getAttribute("PurchaseQuantity");
                if (q != null) {
                    quantity = q.longValue();
                }
                System.out.println("SubmitPrice is: " + price + " PurchaseQuantity is: " + quantity);
                masterTotal += price * quantity;
            }
        }
        
        System.out.println("Master VerifyTotal is: " + masterTotal);
        DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrdersViewIterator");
        Row masterRow = it.getCurrentRow();
        masterRow.setAttribute("VerifyTotal", masterTotal);
        
        masterRow.setAttribute("State", "3");
        
//        toState("3");
    }

    public void approvePo(ActionEvent actionEvent) {
        toState("5");
    }
    
    public void finishPo(ActionEvent actionEvent) {
        toState("6");
    }
    
    public void rejectPo(ActionEvent actionEvent) {
        toState("4");
    }
    
    public void cancelPo(ActionEvent actionEvent) {
        toState("7");
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

    public void itemCategoryChanged(ValueChangeEvent valueChangeEvent) {
        DCIteratorBinding lbinding = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lbinding.executeQuery();
//        int newValue = (Integer)valueChangeEvent.getNewValue();
//        System.out.println("Item category changed to: " + newValue);
////        long categoryId = newValue.getValue();
//        DCIteratorBinding binding = ADFUtils.findIterator("PurchaseOrdersViewIterator");
//        binding.setCurrentRowIndexInRange(newValue);
////        binding.getCurrentRow().setAttribute("ItemCategoryId", categoryId);
    }

    public void submitterChanged(ValueChangeEvent valueChangeEvent) {
//        DBSequence newValue = (DBSequence)valueChangeEvent.getNewValue();
//        System.out.println("Submitter changed to: " + newValue.getValue());
//        BindingContainer bindings = ADFUtils.getBindingContainer();
//        JUCtrlListBinding binding = (JUCtrlListBinding)bindings.get("SubmitterId");
//        Row row = binding.getCurrentRow();
//        String[] names = row.getAttributeNames();
//        for (String name : names) {
//            System.out.println(name + ":" + row.getAttribute(name));
//        }
//        System.out.println(":::::::::::::::");
//        Row atRangeIndex = binding.getRowAtRangeIndex((int)newValue.getValue());
//        String[] names2 = atRangeIndex.getAttributeNames();
//        for (String name: names2) {
//            System.out.println(name + ":" + atRangeIndex.getAttribute(name));
//        }
//        DCIteratorBinding itBinding = ADFUtils.findIterator("PurchaseOrdersViewIterator");
//        System.out.println("The value got from the list is: " + row.getAttribute("Id"));
//        itBinding.getCurrentRow().setAttribute("SubmitterId", row.getAttribute("Id"));
    }

    public void submitPriceChanged(ValueChangeEvent valueChangeEvent) {
        double newPrice = ((BigDecimal)valueChangeEvent.getNewValue()).doubleValue();
        System.out.println("newPrice is: " + newPrice);
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        Row row = lineIt.getCurrentRow();
        long quantity = 0;
        BigDecimal q = (BigDecimal)row.getAttribute("SubmitQuantity");
        if (q != null) {
            quantity = q.longValue();
        }
        System.out.println("Quantity is: " + quantity);
        row.setAttribute("SubmitTotal", newPrice * quantity);
        
        computePoSubmitTotal(lineIt);
    }

    public void submitQuantityChanged(ValueChangeEvent valueChangeEvent) {
        long newQuantity = ((BigDecimal)valueChangeEvent.getNewValue()).longValue();
        System.out.println("newQuantity is: " + newQuantity);
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        Row row = lineIt.getCurrentRow();
        double price = 0;
        BigDecimal p = (BigDecimal)row.getAttribute("SubmitPrice");
        if (p != null) {
            price = p.doubleValue();
        }
        System.out.println("Price is: " + price);
        row.setAttribute("SubmitTotal", price * newQuantity);
        
        computePoSubmitTotal(lineIt);
    }

    public void purchaseQuantityChanged(ValueChangeEvent valueChangeEvent) {
        long newQuantity = ((BigDecimal)valueChangeEvent.getNewValue()).longValue();
        System.out.println("newQuantity is: " + newQuantity);
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        Row row = lineIt.getCurrentRow();
        double price = 0;
        BigDecimal p = (BigDecimal)row.getAttribute("ActualPrice");
        if (p != null) {
            price = p.doubleValue();
        }
        System.out.println("Actual Price is: " + price);
        row.setAttribute("ActualTotal", price * newQuantity);
        //Set the PurchaseQuantity to the new value, so that the following compute method can use it.
        row.setAttribute("PurchaseQuantity", newQuantity);
        
        computePoVerifyTotal(lineIt);
    }

    public void actualPriceChanged(ValueChangeEvent valueChangeEvent) {
        double newPrice = ((BigDecimal)valueChangeEvent.getNewValue()).doubleValue();
        System.out.println("newPrice is: " + newPrice);
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        Row row = lineIt.getCurrentRow();
        long quantity = 0;
        BigDecimal q = (BigDecimal)row.getAttribute("PurchaseQuantity");
        if (q != null) {
            quantity = q.longValue();
        }
        System.out.println("Purchase Quantity is: " + quantity);
        row.setAttribute("ActualTotal", newPrice * quantity);
    }
    
    private void computePoSubmitTotal(DCIteratorBinding lineIt) {
        Row[] rows = lineIt.getAllRowsInRange();
        System.out.println("Will RangeSize be large enough? RangeSize is:" + lineIt.getRangeSize());
        double masterTotal = 0;
        for (Row r : rows) {
            double submitTotal = 0;
            BigDecimal t = (BigDecimal)r.getAttribute("SubmitTotal");
            if (t != null) {
                submitTotal = t.doubleValue();
            }
            masterTotal += submitTotal;
        }
        System.out.println("Master SubmitTotal is: " + masterTotal);
        DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrdersViewIterator");
        Row masterRow = it.getCurrentRow();
        masterRow.setAttribute("SubmitTotal", masterTotal);
    }
    


    private void computePoVerifyTotal(DCIteratorBinding lineIt) {
        Row[] rows = lineIt.getAllRowsInRange();
        System.out.println("Will RangeSize be large enough? RangeSize is:" + lineIt.getRangeSize());
        double masterTotal = 0;
        for (Row r : rows) {
            double submitPrice = 0;
            BigDecimal p = (BigDecimal)r.getAttribute("SubmitPrice");
            if (p != null) {
                submitPrice = p.doubleValue();
            }
            long quantity = 0;
            BigDecimal q = (BigDecimal)r.getAttribute("PurchaseQuantity");
            if (q != null) {
                quantity = q.longValue();
            }
            masterTotal += submitPrice * quantity;
        }
        System.out.println("Master VerifyTotal is: " + masterTotal);
        DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrdersViewIterator");
        Row masterRow = it.getCurrentRow();
        masterRow.setAttribute("VerifyTotal", masterTotal);
    }
}

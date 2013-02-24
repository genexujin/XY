package edu.hp.view.bean.po;

import edu.hp.model.common.Constants;
import edu.hp.view.bean.BaseBean;
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
import oracle.jbo.domain.Timestamp;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

public class MyPoBean extends BaseBean {
    private String orderReadableId;
    private Date submitDateFrom;
    private Date submitDateTo;
    private RichTable resultTable;

    public MyPoBean() {
    }

    public String doQuery() {
        String poStateId = getLovAttrValue("PoStateWithEmpty", "FlexCol1");
        System.out.println("PoState Id is: " + poStateId);
//        String itemCategoryId = getLovAttrValue("ItemCategory", "Id");
//        System.out.println("ItemCategory Id is: " + itemCategoryId);
        String submitterId = getLovAttrValue("EmpWithEmpty", "Id");
        System.out.println("submitterId is: " + submitterId);
        String newItemCategory = getLovAttrValue("ItemCategoryWithEmpty", "FlexCol1");
        System.out.println("ItemCategory is: " + newItemCategory);
        
        System.out.println("OrderReadableId is: " + orderReadableId);
        System.out.println("SubmitDateFrom is: " + submitDateFrom);
        System.out.println("SubmitDateTo is: " + submitDateTo);
        
        OperationBinding binding = ADFUtils.findOperation("doQuery");
        binding.getParamsMap().put("oRdId", orderReadableId);
        binding.getParamsMap().put("state", poStateId);
        binding.getParamsMap().put("category", newItemCategory);
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
    
    public void savePo(ActionEvent actionEvent) {
        computeTotal("SubmitPrice", "SubmitQuantity", "SubmitTotal", "SubmitTotal");
        
        commit();
    }

    public void submitPo(ActionEvent actionEvent) {
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lineIt.setRangeSize(-1);
        if (lineIt.getAllRowsInRange().length == 0) {
            JSFUtils.addFacesErrorMessage("必须有订单行才能提交订单！");
        } else {
            String state = (String)ADFUtils.getBoundAttributeValue("State");
            if (state != null && state.equals(Constants.PO_STATE_INITIAL)) {
                computeTotal("SubmitPrice", "SubmitQuantity", "SubmitTotal", "SubmitTotal");
                //If no need to verify for this category, then go to "待审批3"
                changeState(Constants.PO_STATE_PENDING_REVIEW);
                changeLineState(Constants.PO_LINE_STATE_PENDING_REVIEW);
                setSubmitDate();
                boolean success = ADFUtils.commit("采购订单已提交！", "采购订单提交失败，请核对输入的信息或联系管理员！");
                if (success) {
                    String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
                    String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
                    String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
                    
                    createTask(id, Constants.CONTEXT_TYPE_PO, "有新的采购订单等待审核", Constants.ROLE_PO_VERIFIER, readableId);
                    
                    sendNotification("有新的采购订单等待审核", "有新的采购订单等待审核", null, Constants.ROLE_PO_VERIFIER);
                    
                    //有一种情况下需要completeTask，就是在订单被拒绝后，会为提交者创建一个新task。用户可以再次提交该订单，这时候需要complete之前的task
                    //（后来发现这种情况下只会发通知，所以不需要了）
//                    completeTaskForUser(Constants.CONTEXT_TYPE_PO, id, submitterId);
                    
                    ADFUtils.findOperation("Commit").execute();
                } else {
                    ADFUtils.setBoundAttributeValue("State", state);
                }
            }
        }
    }

    public void verifyPo(ActionEvent actionEvent) {
        double verifyTotal = computeTotal("SubmitPrice", "PurchaseQuantity", null, "VerifyTotal");
        if (verifyTotal == 0) {
            System.out.println("VerifyTotal is 0. No need to approve.");
            changeState(Constants.PO_STATE_FINISHED);
            changeLineState(Constants.PO_LINE_STATE_FINISHED);
        } else {
            changeState(Constants.PO_STATE_PENDING_APPROVAL);
            changeLineState(Constants.PO_STATE_PENDING_APPROVAL);
        }
        
        boolean success = ADFUtils.commit("采购订单已审核！", "采购订单审核失败，请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            if (verifyTotal != 0) { //If verifyTotal is 0, then no need to approve.
                //Create task for approver
                createTask(id, Constants.CONTEXT_TYPE_PO, "有新的采购订单等待审批", Constants.ROLE_PO_APPROVER, readableId);
                
                sendNotification("有新的采购订单等待审批", "有新的采购订单等待审批", null, Constants.ROLE_PO_APPROVER);
            }
            
            //Complete task for verifier
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_VERIFIER);
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            //Change back the state if commit fails
            changeState(Constants.PO_STATE_PENDING_REVIEW);
            changeLineState(Constants.PO_LINE_STATE_PENDING_REVIEW);
        }
    }

    public void approvePo(ActionEvent actionEvent) {
        BigDecimal verifyTotal = (BigDecimal)ADFUtils.getBoundAttributeValue("VerifyTotal");
        Integer catgId = (Integer)ADFUtils.getBoundAttributeValue("ItemCategoryId1");
        BigDecimal limit = getApprovalLimit("IC_" + catgId.toString());
        
        System.out.println("Apprval Limit for Item category \"" + catgId + "\" is: " + limit);
        if (verifyTotal.compareTo(limit) <= 0) { //No need for 2nd level approval
            changeState(Constants.PO_STATE_EXECUTING);
            changeLineState(Constants.PO_LINE_STATE_EXECUTING);
        }
        
        boolean success = ADFUtils.commit("采购订单已审批！", "采购订单审批失败，请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            
            if (verifyTotal.compareTo(limit) <= 0) {
                sendNotification("有新的采购订单等待采购及收货", "有新的采购订单等待采购及收货", null, Constants.ROLE_PO_BUYER);
                sendNotification("您的采购订单已审批", "您的采购订单已审批", submitterId, null);
            } else {
                //Should create task for 2nd level approver
                createTask(id, Constants.CONTEXT_TYPE_PO, "有新的采购订单等待审批", Constants.ROLE_PO_2ND_APPROVER, readableId);
                
                sendNotification("有新的采购订单等待审批", "有新的采购订单等待审批", null, Constants.ROLE_PO_2ND_APPROVER);
            }
            
            //Complete task for approver
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_APPROVER);
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_PENDING_APPROVAL);
            changeLineState(Constants.PO_LINE_STATE_PENDING_APPROVAL);
        }
    }
    
    public void approvePo2nd(ActionEvent actionEvent) {
        changeState(Constants.PO_STATE_EXECUTING);
        changeLineState(Constants.PO_LINE_STATE_EXECUTING);
        
        boolean success = ADFUtils.commit("采购订单已审批！", "采购订单审批失败，请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            
            sendNotification("有新的采购订单等待采购", "有新的采购订单等待采购", null, Constants.ROLE_PO_BUYER);
            sendNotification("您的采购订单已审批", "您的采购订单已审批", submitterId, null);
            
            //Complete task for 2nd level approver
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_2ND_APPROVER);
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_PENDING_APPROVAL);
            changeLineState(Constants.PO_LINE_STATE_PENDING_APPROVAL);
        }
    }
    
    public void executePo(ActionEvent actionEvent) {
        boolean success = ADFUtils.commit("采购订单执行完成！", "采购订单提交失败，请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            
            sendNotification("有新的采购订单等待收货", "有新的采购订单等待收货", null, Constants.ROLE_PO_RECEIVER);
            
            ADFUtils.findOperation("Commit").execute();
        }
    }
    
    public void finishPo(ActionEvent actionEvent) {
        computeTotal("ActualPrice", "PurchaseQuantity", "ActualTotal", null);
        changeState(Constants.PO_STATE_FINISHED);
        changeLineState(Constants.PO_LINE_STATE_FINISHED);
        boolean success = ADFUtils.commit("采购订单收货完成！", "采购订单收货失败，请核对输入的信息或联系管理员！");
        if (success) {            
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            
            //Only notification sent to receiver, no task created. So no task to complete
//            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_RECEIVER);
            
            //send notification to submitter
            sendNotification("您的采购订单已完成", "您的采购订单已完成", submitterId, null);
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_EXECUTING);
            changeLineState(Constants.PO_LINE_STATE_EXECUTING);
        }
    }
    
    public void rejectPo(ActionEvent actionEvent) {
        changeState(Constants.PO_STATE_REJECTED);
        changeLineStateForAll(Constants.PO_LINE_STATE_INITIAL);
        boolean success = ADFUtils.commit("采购订单已拒绝！", "采购订单拒绝失败！请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            //No task created after rejection. Only notification
//            createTaskForUser(id, Constants.CONTEXT_TYPE_PO, "您的采购订单被拒绝，请取消或者重新提交订单。", submitterId, readableId);
            sendNotification("您的采购订单被拒绝", "请取消或者重新提交订单。", submitterId, null);
            //Complete task for approver
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_APPROVER);
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_PENDING_APPROVAL);
            changeLineState(Constants.PO_LINE_STATE_PENDING_APPROVAL);
        }
        
    }
    
    public void cancelPo(ActionEvent actionEvent) {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        
        changeState(Constants.PO_STATE_CANCELLED);
        changeLineState(Constants.PO_LINE_STATE_CANCELLED);
        
        boolean success = ADFUtils.commit("采购订单已取消！", "采购订单取消失败！请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_VERIFIER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_APPROVER);
            completeTaskForUser(Constants.CONTEXT_TYPE_PO, id, submitterId);
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(state);
            //How to easily change back po lines state?
        }
    }
    
    private double computeTotal(String priceAttr, String quantityAttr, String lineTotalAttr, String masterTotalAttr) {
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lineIt.setRangeSize(-1);
        Row[] rows = lineIt.getAllRowsInRange();
        double masterTotal = 0;
        if (rows == null || rows.length == 0) {
            System.err.println("There is no row at all!");
        } else {
            System.out.println("The PO line rows count is: " + rows.length);
            for (Row row : rows) {
                String lineState = (String)row.getAttribute("State");
                System.out.println("The PO line state is: " + lineState);
                //已完成的行和已取消的行不计算在总金额中！
                if ( !lineState.equals(Constants.PO_LINE_STATE_CANCELLED) && !lineState.equals(Constants.PO_LINE_STATE_FINISHED)) {
                    double price = 0;
                    BigDecimal p = (BigDecimal)row.getAttribute(priceAttr);
                    if (p != null) {
                        price = p.doubleValue();
                    }
                    long quantity = 0;
                    BigDecimal q = (BigDecimal)row.getAttribute(quantityAttr);
                    if (q != null) {
                        quantity = q.longValue();
                    }
                    System.out.println(priceAttr + " is: " + price + " " + quantityAttr +" is: " + quantity);
                    if (lineTotalAttr != null) {
                        row.setAttribute(lineTotalAttr, price * quantity);
                    }
                    masterTotal += price * quantity;
                }
                
                
                String rowState = (String)row.getAttribute("State");
                System.out.println("The state value before commit is: " + rowState);
            }

            System.out.println("Master " + masterTotalAttr + " is: " + masterTotal);
            if (masterTotalAttr != null) {
                DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrdersViewIterator");
                Row masterRow = it.getCurrentRow();
                masterRow.setAttribute(masterTotalAttr, masterTotal);
            }
        }
        
        return masterTotal;
    }

    private void changeState(String state) {
        DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrdersViewIterator");
        Row row = it.getCurrentRow();
        row.setAttribute("State", state);
    }

    private void changeLineState(String lineState) {
        DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        it.setRangeSize(-1);
        for (Row line : it.getAllRowsInRange()) {
            String currState = line.getAttribute("State").toString();
            if (!currState.equals(Constants.PO_LINE_STATE_CANCELLED) && !currState.equals(Constants.PO_LINE_STATE_FINISHED)) {
                line.setAttribute("State", lineState);
            }
        }
    }
    
    private void changeLineStateForAll(String lineState) {
        DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        it.setRangeSize(-1);
        for (Row line : it.getAllRowsInRange()) {            
            line.setAttribute("State", lineState);
        }
    }
    
    private void commit() {
        OperationBinding oper = ADFUtils.findOperation("Commit");
        oper.execute();
    }

    public void itemCategoryChanged(ValueChangeEvent valueChangeEvent) {
        DCIteratorBinding lbinding = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lbinding.executeQuery();
    }

    public void submitterChanged(ValueChangeEvent valueChangeEvent) {
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

    private void setSubmitDate() {
        DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrdersViewIterator");
        Row row = it.getCurrentRow();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        System.out.println("Current time is: " + now);
        row.setAttribute("CreateAt", now);
    }

    public void newPoLine(ActionEvent actionEvent) {
        OperationBinding oper = ADFUtils.findOperation("newRow");
        oper.execute();
    }

    private BigDecimal getApprovalLimit(final String catgId) {
        OperationBinding oper = ADFUtils.findOperation("getApprovalLimitForCategoryId");
        oper.getParamsMap().put("categoryId", catgId);
        oper.execute();
        BigDecimal result = (BigDecimal)oper.getResult();
        if (result != null) {
            return result;
        } 
        
        return new BigDecimal(0);
    }
}

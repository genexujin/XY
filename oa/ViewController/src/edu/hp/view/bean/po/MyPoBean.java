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

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.render.ClientEvent;

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
    private RichInputText submitTotalComp;
    private String action;
    
    private RichTable poLinesTable;
    private RichTable poHistoryTable;
    private RichPanelFormLayout poForm;
    
    private boolean cancelButtonRendered;

    public MyPoBean() {
    }

    public String doQuery() {
        String poStateId = getLovAttrValue("PoStateWithEmpty", "FlexCol1");
        System.out.println("PoState Id is: " + poStateId);
//        String itemCategoryId = getLovAttrValue("ItemCategory", "Id");
//        System.out.println("ItemCategory Id is: " + itemCategoryId);
        String submitterId = getLovAttrValue("EmpWithEmpty", "Id");
        System.out.println("submitterId is: " + submitterId);
        String newItemCategory = getLovAttrValue("ItemCategoryWithEmpty", "Value");
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
        String fromMenu = JSFUtils.resolveExpressionAsString("#{pageFlowScope.fromMenu}");
        if ("buyer".equals(fromMenu)) {
            computeTotal("ActualPrice", "PurchaseQuantity", "ActualTotal", null);
        }
        
        ADFUtils.commit("采购订单已保存！", "采购订单保存失败，请核对输入的信息或联系管理员！");
    }

    public void submitPo(ActionEvent actionEvent) {
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lineIt.setRangeSize(-1);
        if (lineIt.getAllRowsInRange().length == 0) {
            JSFUtils.addFacesErrorMessage("必须有订单行才能提交订单！");
        } else {
            String state = (String)ADFUtils.getBoundAttributeValue("State");
            if (state != null && (state.equals(Constants.PO_STATE_INITIAL) || state.equals(Constants.PO_STATE_REJECTED))) {
                if (state.equals(Constants.PO_STATE_INITIAL)) {
                    computeTotal("SubmitPrice", "SubmitQuantity", "SubmitTotal", "SubmitTotal");
                    setSubmitDate();
                }
                
//                ADFUtils.setBoundAttributeValue("State", Constants.PO_STATE_PENDING_REVIEW);
                ADFUtils.setBoundAttributeValue("State", Constants.PO_STATE_DEPT_REVIEW);
                
                boolean success = ADFUtils.commit("采购订单已提交！", "采购订单提交失败，请核对输入的信息或联系管理员！");
                if (success) {
                    String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
                    String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
                    String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
                    String submitTotal = ADFUtils.getBoundAttributeValue("SubmitTotal").toString();
                    
                    insertPoHistory(id, submitterId, "提交了该订单");
                    String supervisorId = getDeptSupervisorId(submitterId);
                    ADFUtils.setBoundAttributeValue("DeptVerifier", supervisorId);

                    createTaskForUser(id, Constants.CONTEXT_TYPE_PO, "有新的采购订单等待审核，订单号：" + readableId + "，预算总金额：" + submitTotal
                                      , supervisorId, readableId);
                    
                    sendNotification("有新的采购订单等待审核", "有新的采购订单等待审核，订单号：" + readableId + "，预算总金额：" + submitTotal, supervisorId, null);
                    
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
        if (!checkAllRowsHasPurchaseQuantity()) {
            JSFUtils.addFacesErrorMessage("所有未取消的行都必须有采购数量");
        } else {
            double verifyTotal = computeTotal("SubmitPrice", "PurchaseQuantity", null, "VerifyTotal");
            if (verifyTotal == 0) {
                System.out.println("VerifyTotal is 0. No need to approve.");
                changeState(Constants.PO_STATE_FINISHED);
            } else {
                changeState(Constants.PO_STATE_PENDING_APPROVAL);
            }
            
            boolean success = ADFUtils.commit("采购订单已审核！", "采购订单审核失败，请核对输入的信息或联系管理员！");
            if (success) {
                String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
                String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
                String verifier = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
                insertPoHistory(id, verifier, "审核了该订单");
                
                if (verifyTotal != 0) { //If verifyTotal is 0, then no need to approve.
                    //Set the current pprover for the po
                    ADFUtils.setBoundAttributeValue("CurrentApprover", Constants.ROLE_PO_APPROVER);
                    
                    //Create task for approver
                    createTask(id, Constants.CONTEXT_TYPE_PO, "有新的采购订单等待审批，订单号：" + readableId + "，审核总金额：" + verifyTotal, Constants.ROLE_PO_APPROVER, readableId);
                    sendNotificationForVerify(verifyTotal);
                    sendNotification("有新的采购订单等待审批", "有新的采购订单等待审批，订单号：" + readableId, null, Constants.ROLE_PO_APPROVER);
                }else{
                    sendNotificationForFinish();
                }
                
                //Complete task for verifier
                completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_VERIFIER);
                
                ADFUtils.findOperation("Commit").execute();
            } else {
                //Change back the state if commit fails
                changeState(Constants.PO_STATE_PENDING_REVIEW);
            }
        }

    }
    
    public void verifyPoInDept(ActionEvent actionEvent) {
        changeState(Constants.PO_STATE_PENDING_REVIEW);
        
        boolean success = ADFUtils.commit("采购订单部门审核通过！", "采购订单审核失败，请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String verifier = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            String submitTotal = ADFUtils.getBoundAttributeValue("SubmitTotal").toString();
            
            insertPoHistory(id, verifier, "完成了部门审核");
            
            //Create task for approver
            createTask(id, Constants.CONTEXT_TYPE_PO, "有新的采购订单等待审核，订单号：" + readableId + "，预算总金额：" + submitTotal, Constants.ROLE_PO_VERIFIER, readableId);
            sendNotification("您的采购订单已完成部门审核", "您的采购订单已完成部门审核,等待采购审核中！ 订单号： " + readableId, submitterId, null);
            sendNotification("有新的采购订单等待审核", "有新的采购订单等待审核，订单号：" + readableId + "，预算总金额：" + submitTotal, null, Constants.ROLE_PO_VERIFIER);
            
            //Complete task for dept verifier
            completeTaskForUser(Constants.CONTEXT_TYPE_PO, id, verifier);
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            //Change back the state if commit fails
            changeState(Constants.PO_STATE_DEPT_REVIEW);
        }
    }

    private void sendNotificationForFinish() {
        String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
        String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
        sendNotification("您的采购订单已完成", "您的采购订单已完成, 订单号： " + readableId, submitterId, null);
    }
    
    private void sendNotificationForVerify(double verifyTotal) {
        String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
        String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
        sendNotification("您的采购订单已完成审核", "您的采购订单已完成审核,等待审批中！ 订单号：" + readableId + "，审核总金额：" + verifyTotal, submitterId, null);
    }

    public void approvePo(ActionEvent actionEvent) {
        
        BigDecimal verifyTotal = new BigDecimal((String)ADFUtils.getBoundAttributeValue("VerifyTotal"));
        String category = (String)ADFUtils.getBoundAttributeValue("ItemCategoryId1");
        System.out.println("Item category id is: " + category);
        BigDecimal limit = getApprovalLimit(category);
        
        System.out.println("Apprval Limit for Item category \"" + category + "\" is: " + limit);
        boolean success = false;
        if (verifyTotal.compareTo(limit) <= 0) { //No need for 2nd level approval
            changeState(Constants.PO_STATE_EXECUTING);
            success = ADFUtils.commit("采购订单已审批，正在执行中", "采购订单审批失败，请核对输入的信息或联系管理员！");
        } else {
            success = ADFUtils.commit("采购订单已审批，需要下一级审批", "采购订单审批失败，请核对输入的信息或联系管理员！");
        }
        
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            
            String approver = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");            
            insertPoHistory(id, approver, "审批了该订单");
            
            if (verifyTotal.compareTo(limit) <= 0) {
                ADFUtils.setBoundAttributeValue("CurrentApprover", "");
                ADFUtils.setBoundAttributeValue("CurrentExecutor", Constants.ROLE_PO_BUYER);

                sendNotification("有新的采购订单等待采购及收货", "有新的采购订单等待采购及收货, 订单号：" + readableId + "，总金额：" + verifyTotal, null, Constants.ROLE_PO_BUYER);
                sendNotification("您的采购订单已审批", "您的采购订单已审批，订单号： " + readableId, submitterId, null);
            } else {
                //Set the current verifier for the po
                ADFUtils.setBoundAttributeValue("CurrentApprover", Constants.ROLE_PO_2ND_APPROVER);
                
                //Should create task for 2nd level approver
                createTask(id, Constants.CONTEXT_TYPE_PO, "有新的采购订单等待审批，订单号：" + readableId + "，总金额：" + verifyTotal, Constants.ROLE_PO_2ND_APPROVER, readableId);
                
                sendNotification("有新的采购订单等待审批", "有新的采购订单等待审批，订单号：" + readableId + "，总金额：" + verifyTotal, null, Constants.ROLE_PO_2ND_APPROVER);
            }
            
            //Complete task for approver
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_APPROVER);
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_PENDING_APPROVAL);
        }
    }
    
    public void approvePo2nd(ActionEvent actionEvent) {
        changeState(Constants.PO_STATE_EXECUTING);
        
        boolean success = ADFUtils.commit("采购订单已审批！", "采购订单审批失败，请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            
            String approver = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");            
            insertPoHistory(id, approver, "审批了该订单");
            
            //Clear the current approver
            ADFUtils.setBoundAttributeValue("CurrentApprover", "");
            ADFUtils.setBoundAttributeValue("CurrentExecutor", Constants.ROLE_PO_BUYER);
            
            sendNotification("有新的采购订单等待采购", "有新的采购订单等待采购，订单号： " + readableId, null, Constants.ROLE_PO_BUYER);
            sendNotification("有新的采购订单等待收货", "有新的采购订单等待收货，订单号： " + readableId, null, Constants.ROLE_PO_RECEIVER);
            sendNotification("您的采购订单已审批", "您的采购订单已审批，订单号： " + readableId, submitterId, null);
            
            //Complete task for 2nd level approver
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_2ND_APPROVER);
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_PENDING_APPROVAL);
        }
    }
    
    public void executePo(ActionEvent actionEvent) {
        //First check all lines have "ActualPrice"
        if (!checkAllRowsHasActualPrice()) {
            JSFUtils.addFacesErrorMessage("所有未取消的订单行都必须输入实际单价！");
        } else {
            changeState(Constants.PO_STATE_FINISHED);
            
            computeTotal("ActualPrice", "PurchaseQuantity", "ActualTotal", null);
            boolean success = ADFUtils.commit("采购订单已完成！", "采购订单提交失败，请核对输入的信息或联系管理员！");
            if (success) {
                String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
                
                String buyer = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");            
                insertPoHistory(id, buyer, "完成了该订单");
                
                //ADFUtils.setBoundAttributeValue("CurrentExecutor", Constants.ROLE_PO_RECEIVER);
                
                //send notification to submitter
                sendNotificationForFinish();
                
                ADFUtils.findOperation("Commit").execute();
            }
        }
    }
    
    public void finishPo(ActionEvent actionEvent) {
        
        boolean success = ADFUtils.commit("采购订单收货完成！", "采购订单收货失败，请核对输入的信息或联系管理员！");
        if (success) {            
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
//            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
//            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
//            
            String receiver = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");            
            insertPoHistory(id, receiver, "对该订单进行了收货");            
//            ADFUtils.setBoundAttributeValue("CurrentExecutor", "");
            
            //Only notification sent to receiver, no task created. So no task to complete
//            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_RECEIVER);
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_EXECUTING);
        }
    }
    
    public void rejectPo(ActionEvent actionEvent) {
        changeState(Constants.PO_STATE_REJECTED);
        boolean success = ADFUtils.commit("采购订单已拒绝！", "采购订单拒绝失败！请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            
            String approver = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");            
            insertPoHistory(id, approver, "拒绝了该订单");
            
            //Clear the current approver
            ADFUtils.setBoundAttributeValue("CurrentApprover", "");
            
            //No task created after rejection. Only notification
//            createTaskForUser(id, Constants.CONTEXT_TYPE_PO, "您的采购订单被拒绝，请取消或者重新提交订单。", submitterId, readableId);
            sendNotification("您的采购订单被拒绝", "您的采购订单被拒绝,请取消或者重新提交订单，订单号： " + readableId, submitterId, null);
            //Complete task for approver or verifier
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_VERIFIER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_APPROVER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_2ND_APPROVER);
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_PENDING_APPROVAL);
        }
        
    }
    
    public void rejectPoInDept(ActionEvent actionEvent) {
        changeState(Constants.PO_STATE_REJECTED);
        boolean success = ADFUtils.commit("采购订单已拒绝！", "采购订单拒绝失败！请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            
            String verifier = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");            
            insertPoHistory(id, verifier, "拒绝了该订单");
            
            //No task created after rejection. Only notification
    //            createTaskForUser(id, Constants.CONTEXT_TYPE_PO, "您的采购订单被拒绝，请取消或者重新提交订单。", submitterId, readableId);
            sendNotification("您的采购订单被拒绝", "您的采购订单被拒绝,请取消或者重新提交订单，订单号： " + readableId, submitterId, null);
            
            completeTaskForUser(Constants.CONTEXT_TYPE_PO, id, verifier);
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_DEPT_REVIEW);
        }
        
    }
    
    public void cancelPo(ActionEvent actionEvent) {
        
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        
        if(state.equals(Constants.PO_STATE_CANCELLED)){
            JSFUtils.addFacesErrorMessage("订单已经被取消，无法重复取消！");
            return;
        }
        
        changeState(Constants.PO_STATE_CANCELLED);
        
        boolean success = ADFUtils.commit("采购订单已取消！", "采购订单取消失败！请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            String deptVerifier = ADFUtils.getBoundAttributeValue("DeptVerifier").toString();
            
            String operator = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");            
            insertPoHistory(id, operator, "取消了该订单");
            
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_VERIFIER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_APPROVER);
            completeTaskForUser(Constants.CONTEXT_TYPE_PO, id, deptVerifier);
            completeTaskForUser(Constants.CONTEXT_TYPE_PO, id, submitterId);
            
            String fromMenu = JSFUtils.resolveExpressionAsString("#{pageFlowScope.fromMenu}");
            if (!"normal".equals(fromMenu)) {
                String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
                sendNotification("您的采购订单已经取消", "您的采购订单已经取消，订单号： " + readableId, submitterId, null);
            }
            
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(state);
        }
    }
    
    public void reopenPo(ActionEvent actionEvent) {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
        String verifyTotal = (String)ADFUtils.getBoundAttributeValue("VerifyTotal");
        if (Double.parseDouble(verifyTotal) == 0.0) {
            JSFUtils.addFacesInformationMessage("审核总金额为0，无法重新打开");
        } else {
            ADFUtils.setBoundAttributeValue("State", Constants.PO_STATE_EXECUTING);
            boolean success = ADFUtils.commit("采购订单已重新打开！", "采购订单重新打开失败！请核对输入的信息或联系管理员！");
            if (success) {
                String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
                String operator = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");            
                insertPoHistory(id, operator, "重新打开了该订单");
            
                sendNotification("有采购订单重新打开", "有采购订单重新打开，订单号： " + readableId, null, Constants.ROLE_PO_BUYER);
                
                ADFUtils.findOperation("Commit").execute();
            } else {
                changeState(state);
            }
        }
    }
    
    public void backToVerifyPo(ActionEvent actionEvent) {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        
        ADFUtils.setBoundAttributeValue("State", Constants.PO_STATE_PENDING_REVIEW);
        
        boolean success = ADFUtils.commit("采购订单已重新打开！", "采购订单重新打开失败！请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            
            String operator = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");            
            insertPoHistory(id, operator, "将该订单重新变为待审核");
            
            //need to complete task which is opened for other roles
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_APPROVER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_2ND_APPROVER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_BUYER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_RECEIVER);
            
            ADFUtils.setBoundAttributeValue("CurrentApprover", "");
            ADFUtils.setBoundAttributeValue("CurrentExecutor", "");
            
            createTask(id, Constants.CONTEXT_TYPE_PO, "有新的采购订单等待审核，订单号： " + readableId, Constants.ROLE_PO_VERIFIER, readableId);
            
            //Need to clear the following values
            ADFUtils.setBoundAttributeValue("CurrentApprover", "");
            ADFUtils.setBoundAttributeValue("CurrentExecutor", "");
            
            if (state != null && state.equals(Constants.PO_STATE_EXECUTING)) {                
                sendNotification("采购订单需重新审核，暂停采购", "采购订单需重新审核，暂停采购，订单号： " + readableId, null, Constants.ROLE_PO_BUYER);
            }
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(state);
        
        }
    }
    
    private boolean checkAllRowsHasActualPrice() {
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lineIt.setRangeSize(-1);
        Row[] rows = lineIt.getAllRowsInRange();
        if (rows == null || rows.length == 0) {
            System.err.println("There is no row at all!");
            return false;
        } else {
            for (Row row : rows) {
                String isCancelled = (String)row.getAttribute("Cancelled");
                //已取消的行不需要设置ActualPrice
                if (!"Y".equals(isCancelled)) {
                    double price = 0;
                    BigDecimal p = (BigDecimal)row.getAttribute("ActualPrice");
                     if (p == null) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
    
    private boolean checkAllRowsHasPurchaseQuantity() {
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lineIt.setRangeSize(-1);
        Row[] rows = lineIt.getAllRowsInRange();
        if (rows == null || rows.length == 0) {
            System.err.println("There is no row at all!");
            return false;
        } else {
            for (Row row : rows) {
                String isCancelled = (String)row.getAttribute("Cancelled");
                //已取消的行不需要设置ActualPrice
                if (!"Y".equals(isCancelled)) {
                    double price = 0;
                    BigDecimal p = (BigDecimal)row.getAttribute("PurchaseQuantity");
                     if (p == null) {
                        return false;
                    }
                }
            }
            return true;
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
                String isCancelled = (String)row.getAttribute("Cancelled");
                System.out.println("Is the PO line cancelled? " + isCancelled);
                //已取消的行不计算在总金额中
                if (!"Y".equals(isCancelled)) {
                    double price = 0;
                    BigDecimal p = (BigDecimal)row.getAttribute(priceAttr);
                     if (p != null) {
                        price = p.doubleValue();
                    }
                    double quantity = 0;
                    BigDecimal q = (BigDecimal)row.getAttribute(quantityAttr);
                    if (q != null) {
                        quantity = q.doubleValue();
                    }
                    System.out.println(priceAttr + " is: " + price + " " + quantityAttr +" is: " + quantity);
                    if (lineTotalAttr != null) {
                        row.setAttribute(lineTotalAttr, price * quantity);
                    }
                    masterTotal += price * quantity;
                }
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
        String newPriceStr = (String)valueChangeEvent.getNewValue();
        System.out.println("newPrice is: " + newPriceStr);
        
        computeTotal("SubmitPrice", "SubmitQuantity", "SubmitTotal", "SubmitTotal");
        ADFUtils.partialRefreshComponenet(submitTotalComp);
    }

    public void submitQuantityChanged(ValueChangeEvent valueChangeEvent) {
        long newQuantity = ((BigDecimal)valueChangeEvent.getNewValue()).longValue();
        System.out.println("newQuantity is: " + newQuantity);
        
        computeTotal("SubmitPrice", "SubmitQuantity", "SubmitTotal", "SubmitTotal");
        ADFUtils.partialRefreshComponenet(submitTotalComp);
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
        row.setAttribute("SubmitAt", now);
    }

    public void newPoLine(ActionEvent actionEvent) {
        String catg = (String)ADFUtils.getBoundAttributeValue("ItemCategoryId1");
        if (catg == null || catg.isEmpty()) {
            JSFUtils.addFacesErrorMessage("必须先选择采购类别");
        } else {
            OperationBinding oper = ADFUtils.findOperation("newRow");
            oper.execute();
        }
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
    
    private String getDeptSupervisorId(final String submitterId) {
        OperationBinding oper = ADFUtils.findOperation("getDeptSupervisorId");
        oper.getParamsMap().put("submitterId", submitterId);
        oper.execute();
        String result = (String)oper.getResult();
        if (result != null) {
            return result;
        } 
        
        return "";
    }
    
    public String returnClicked() {
        DCIteratorBinding state = ADFUtils.findIterator("PoStateWithEmptyIterator");
        state.executeQuery();
        
        DCIteratorBinding category = ADFUtils.findIterator("ItemCategoryWithEmptyIterator");
        category.executeQuery();
        
        DCIteratorBinding emp = ADFUtils.findIterator("EmpWithEmptyIterator");
        emp.executeQuery();
                
        this.orderReadableId = null;
        this.submitDateFrom = null;
        this.submitDateTo = null;
        
        String fromMenu = (String)JSFUtils.resolveExpression("#{pageFlowScope.fromMenu}");
        System.out.println("from menu: " + fromMenu);
        
        if ("verifier".equals(fromMenu)) {
            return "returnFromVerifier";
        } else if ("normal".equals(fromMenu)) {
            return "returnFromNormal";
        } else if ("approver".equals(fromMenu)) {
            return "returnFromApprover";
        } else if ("buyer".equals(fromMenu)) {
            return "returnFromBuyer";
        } else if ("dept".equals(fromMenu)) {
            return "returnFromDept";
        } else if ("query".equals(fromMenu)) {
            return "returnFromQuery";
        } else {
            return "returnFromReceiver";
        }
    }
    
    private void insertPoHistory(String orderId, String operatorId, String operationDetail) {
        OperationBinding oper = ADFUtils.findOperation("insertPoHistory");
        oper.getParamsMap().put("orderId", orderId);
        oper.getParamsMap().put("operatorId", operatorId);
        oper.getParamsMap().put("operationDetail", operationDetail);
        
        oper.execute();
    }
//
//    public void setCurrDate(Date currDate) {
//        this.currDate = currDate;
//    }

    public Date getCurrDate() {
        Date now = new Date(System.currentTimeMillis());
        return now;
    }

    public void setSubmitTotalComp(RichInputText submitTotalComp) {
        this.submitTotalComp = submitTotalComp;
    }

    public RichInputText getSubmitTotalComp() {
        return submitTotalComp;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void onConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            if(action.equals("submit")){
                submitPo(null);
            } else if (action.equals("deptVerify")) {
                verifyPoInDept(null);
            } else if (action.equals("deptReject")) {
                rejectPoInDept(null);
            } else if (action.equals("cancel")) {
                cancelPo(null);
            } else if (action.equals("verify")) {
                verifyPo(null);
            } else if (action.equals("approve")) {
                approvePo(null);
            } else if (action.equals("approve2nd")) {
                approvePo2nd(null);
            } else if (action.equals("reject")) {
                rejectPo(null);
            } else if (action.equals("execute")) {
                executePo(null);
            } else if (action.equals("finish")) {
                finishPo(null);
            } else if (action.equals("reopen")) {
                reopenPo(null);
            } else if (action.equals("backToVerify")) {
                backToVerifyPo(null);
            }
                        
            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void setActionListener(ClientEvent clientEvent) {
        System.err.println("here");
        String newAction  = (String) clientEvent.getParameters().get("payload");
        this.setAction(newAction);
    }

    public void setPoLinesTable(RichTable poLinesTable) {
        this.poLinesTable = poLinesTable;
    }

    public RichTable getPoLinesTable() {
        return poLinesTable;
    }

    public void setPoHistoryTable(RichTable poHistoryTable) {
        this.poHistoryTable = poHistoryTable;
    }

    public RichTable getPoHistoryTable() {
        return poHistoryTable;
    }

    public void setPoForm(RichPanelFormLayout poForm) {
        this.poForm = poForm;
    }

    public RichPanelFormLayout getPoForm() {
        return poForm;
    }

    public void setCancelButtonRendered(boolean cancelButtonRendered) {
        this.cancelButtonRendered = cancelButtonRendered;
    }

    public boolean isCancelButtonRendered() {
        cancelButtonRendered = 
            JSFUtils.resolveExpressionAsBoolean("#{(pageFlowScope.fromMenu eq 'normal' and (bindings.State.inputValue eq '1' or bindings.State.inputValue eq '2' or bindings.State.inputValue eq '4' or bindings.State.inputValue eq '8'))" + 
                                                " or (pageFlowScope.fromMenu ne 'query' and sessionScope.LoginUserBean.isUserInRole['采购审核'] and bindings.State.inputValue ne '1' and bindings.State.inputValue ne '2')}");
        return cancelButtonRendered;
    }
}

package edu.hp.view.bean.helpdesk;

import edu.hp.model.common.Constants;
import edu.hp.view.bean.BaseBean;
import edu.hp.view.security.LoginUser;
import edu.hp.view.utils.ADFUtils;

import edu.hp.view.utils.JSFUtils;

import java.sql.Date;

import java.util.Iterator;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.component.UIXTable;
import org.apache.myfaces.trinidad.component.UIXTree;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;

public class MyHelpdeskCallBean extends BaseBean {
    private RichTable resultTable;
    
    private String callReadableId;
    private Date submitDateFrom;
    private Date submitDateTo;
    
    private String fromButton;
    
    private String action;
    
    private boolean assignButtonRendered;
    private boolean processPanelRendered;
    private boolean assignLovRendered;

    public MyHelpdeskCallBean() {
    }

    public void setResultTable(RichTable resultTable) {
        this.resultTable = resultTable;
    }

    public RichTable getResultTable() {
        return resultTable;
    }

    public void doQuery(ActionEvent event) {
        String stateValue = getLovAttrValue("HdStateWithEmpty", "Value");
        System.out.println("State is: " + stateValue);
        String reasonLv1WithEmpty = getLovAttrValue("ReasonLevel1WithEmpty", "Value");
        System.out.println("reasonLv1WithEmpty is: " + reasonLv1WithEmpty);
//        String reasonLv2WithEmpty = getLovAttrValue("ReasonLevel2WithEmpty", "Value");
//        System.out.println("reasonLv2WithEmpty is: " + reasonLv2WithEmpty);
        System.out.println("CallReadableId is: " + callReadableId);
        System.out.println("SubmitDateFrom is: " + submitDateFrom);
        System.out.println("SubmitDateTo is: " + submitDateTo);
        String callerId = getLovAttrValue("EmpWithEmptyForCaller", "Id");
        System.out.println("callerId is: " + callerId);
        String calleeId = getLovAttrValue("EmpWithEmptyForCallee", "Id");
        System.out.println("calleeId is: " + calleeId);
        String hdResult = getLovAttrValue("HdResultWithEmpty", "Value");
        System.out.println("hdResult is: " + hdResult);
        String hdEval = getLovAttrValue("HdEvalWithEmpty", "Value");
        System.out.println("hdEval is: " + hdEval);
        String locId = getLovAttrValue("LocationsWithEmpty", "LocationId");
        System.out.println("locationId is: " + locId);
        
        OperationBinding binding = ADFUtils.findOperation("doQuery");
        binding.getParamsMap().put("rsnLv1", reasonLv1WithEmpty);
        binding.getParamsMap().put("state", stateValue);
        binding.getParamsMap().put("cReadableId", callReadableId);
        binding.getParamsMap().put("submitDateFrom", submitDateFrom);
        binding.getParamsMap().put("submitDateTo", submitDateTo);
        binding.getParamsMap().put("callerId", callerId);
        binding.getParamsMap().put("calleeId", calleeId);
        binding.getParamsMap().put("callResult", hdResult);
        binding.getParamsMap().put("callEval", hdEval);
        binding.getParamsMap().put("locId", locId);
                
        binding.execute();
        
        ADFUtils.partialRefreshComponenet(resultTable);
    }

    private String getLovAttrValue(String lovBindingName, String attrName) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        JUCtrlListBinding binding = (JUCtrlListBinding)bindings.get(lovBindingName);
        Row row = binding.getCurrentRow();
        Object value = row.getAttribute(attrName);
        if (value == null) {
            return null;
        } else if (value instanceof DBSequence) {
            return ((DBSequence)value).getValue() + "";
        } else {
            return value.toString();
        }
        
    }

    public void saveHdCall(ActionEvent event) {
        ADFUtils.commit("报修单已保存！", "报修单保存失败，请核对输入的信息或联系管理员！");
    }

    public void submitHdCall(ActionEvent actionEvent) {
        String rsnLv2 = (String)ADFUtils.getBoundAttributeValue("ReasonLevel2");
        String rsnLv3 = (String)ADFUtils.getBoundAttributeValue("ReasonLevel3");
        
        if (rsnLv2 == null || rsnLv2.isEmpty() || rsnLv3 == null || rsnLv3.isEmpty()) {
            JSFUtils.addFacesErrorMessage("二级原因和三级原因都不能为空！");
        } else {
            setSubmitDate();
            String state = (String)ADFUtils.getBoundAttributeValue("State");
            if (state != null && state.equals(Constants.STATE_INITIAL)) {
                ADFUtils.setBoundAttributeValue("State", Constants.STATE_ACCEPTED);
                
                String rsnLv1 = (String)ADFUtils.getBoundAttributeValue("ReasonLevel1");
                String deptId = getProcessDept(rsnLv1);
                ADFUtils.setBoundAttributeValue("BelongToDept", deptId);
                
                boolean success = ADFUtils.commit("报修单已提交！", "报修单提交失败，请核对输入的信息或联系管理员！");
                if (success) {
                    String id = (ADFUtils.getBoundAttributeValue("CallId")).toString();
                    String readableId = (ADFUtils.getBoundAttributeValue("CallReadableId")).toString();
                    String locId = (String)ADFUtils.getBoundAttributeValue("LocationId");
                    String locationDetail = (String)ADFUtils.getBoundAttributeValue("LocationDetail");
            //                String roleName = getRoleName(locId, rsnLv1);
                    String roleName = getRoleName(rsnLv1);
                    
                    //send to assigner
                    String noteTitle = "有新的报修请求等待分派，报修单号：" + readableId;
                    String noteContent = " 报修原因：" + rsnLv1 + " 详细地址：" + locationDetail + " 提交时间：" + getDateString();
                    sendNotification(noteTitle, noteContent, null, roleName);
                    
                    createTask(id, Constants.CONTEXT_TYPE_HELPDESK, noteTitle, roleName, readableId);
                    
                    ADFUtils.findOperation("Commit").execute();
                } else {
                    ADFUtils.setBoundAttributeValue("State", state);
                }
            }
        }
    }

    public void assignHdCall(ActionEvent event) {
        String originState = (String)ADFUtils.getBoundAttributeValue("State");
        String calleeId = ADFUtils.getBoundAttributeValue("CalleeId").toString();
        if (calleeId == null || calleeId.equals("")) {
            JSFUtils.addFacesErrorMessage("受理人不能为空");
        } else {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_ASSIGNED);
            
            boolean success = ADFUtils.commit("报修单已分派！", "报修单分派失败，请核对输入的信息或联系管理员！");
            if (success) {
                String rsnLv1 = (String)ADFUtils.getBoundAttributeValue("ReasonLevel1");
                String id = (ADFUtils.getBoundAttributeValue("CallId")).toString();
                String readableId = (ADFUtils.getBoundAttributeValue("CallReadableId")).toString();
                String locationDetail = (String)ADFUtils.getBoundAttributeValue("LocationDetail");
            //            String calleeId = (String)ADFUtils.getBoundAttributeValue("CalleeId");
                
                //Complete task for assigner
                String roleName = getRoleName(rsnLv1);
                completeTask(Constants.CONTEXT_TYPE_HELPDESK, id, roleName);
                
                //Complete task for callee if it is assigned before
//                System.out.println("之前分派的处理人id：" + oldCallee);
//                if (oldCallee != null) {
//                    completeTaskForUser(Constants.CONTEXT_TYPE_HELPDESK, id, oldCallee);
//                }
                completeTaskForUser(Constants.CONTEXT_TYPE_HELPDESK, id, null);
                
                String noteTitle = "有新的报修请求等待处理，报修单号：" + readableId;
                String noteContent = " 报修原因：" + rsnLv1 + " 详细地址：" + locationDetail;
                sendNotification(noteTitle, noteContent, calleeId, null);
                
                createTaskForUser(id, Constants.CONTEXT_TYPE_HELPDESK, noteTitle, calleeId, readableId);
                
                ADFUtils.findOperation("Commit").execute();
                
            } else {
                ADFUtils.setBoundAttributeValue("State", originState);
            }
        }
    }
    
    public void cancelHdCall(ActionEvent actionEvent) {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_CANCELED);
            boolean success = ADFUtils.commit("报修单已取消！", "报修单取消失败，请核对输入的信息或联系管理员！");
            if (success) {
                //在“已受理”状态进行cancel的话，需要结束创建给报修员的task
                String id = ((DBSequence)ADFUtils.getBoundAttributeValue("CallId")).toString();
                
                String calleeId = (String)ADFUtils.getBoundAttributeValue("CalleeId");
                String rsnLv1 = (String)ADFUtils.getBoundAttributeValue("ReasonLevel1");
                String roleName = getRoleName(rsnLv1);
                
                //complete the task for callee
                completeTask(Constants.CONTEXT_TYPE_HELPDESK, id, roleName);
                completeTaskForUser(Constants.CONTEXT_TYPE_HELPDESK, id, calleeId);
                
                ADFUtils.findOperation("Commit").execute();
            } else {
                ADFUtils.setBoundAttributeValue("State", state);
            }
        }
    }

    public void processHdCall(ActionEvent actionEvent) {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        //String calleeId = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
//        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
//        String calleeId = user.getUserId();
        
        String callerId = ADFUtils.getBoundAttributeValue("CallerId").toString();
        String calleeId = ADFUtils.getBoundAttributeValue("CalleeId").toString();
        System.out.println("calleeId is: " + calleeId);
        System.out.println("callerId is: " + callerId);
        
        if (state != null && state.equals(Constants.STATE_ASSIGNED)) {
            String callResult = (String)ADFUtils.getBoundAttributeValue("CallResult");
            String id = (ADFUtils.getBoundAttributeValue("CallId")).toString();
            String readableId = (String)ADFUtils.getBoundAttributeValue("CallReadableId");
            //修复了，让用户评价
            if (callResult.equals(Constants.HD_RESULT_FIXED)) {
                ADFUtils.setBoundAttributeValue("State", Constants.STATE_PROCESSED);
                boolean success = ADFUtils.commit("报修单已处理！", "报修单处理失败，请核对输入的信息或联系管理员！");
                if (success) {                    
                    //complete the task for callee
                    completeTaskForUser(Constants.CONTEXT_TYPE_HELPDESK, id, null);
                    
                    //create evaluation task for caller
                    createTaskForUser(id, Constants.CONTEXT_TYPE_HELPDESK, "您的报修请求已处理，请评价", callerId, readableId);
                    
                    ADFUtils.findOperation("Commit").execute();
                } else {
                    ADFUtils.setBoundAttributeValue("State", state);
                }
            } else { //转总务复核
                ADFUtils.setBoundAttributeValue("State", Constants.STATE_AFF_REVIEW);
                
                boolean success = ADFUtils.commit("报修单已转总务复核！", "报修单处理失败，请核对输入的信息或联系管理员！");
                
                if (success) {                
                    //complete the task for callee
                    completeTaskForUser(Constants.CONTEXT_TYPE_HELPDESK, id, null);
                    
                    //create task for hd affair review
                    createTask(id, Constants.CONTEXT_TYPE_HELPDESK, "您有新的报修请求等待复核，报修单号：" + readableId, Constants.ROLE_HD_REVIEW, readableId);
                    
                    ADFUtils.findOperation("Commit").execute();
                    
                } else {
                    ADFUtils.setBoundAttributeValue("State", state);
                }
            }
        }
    }

    public void reviewHdCall(ActionEvent actionEvent) {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        String reviewNote = (String)ADFUtils.getBoundAttributeValue("AffairReviewNote");
        if (reviewNote == null || reviewNote.isEmpty()) {
            JSFUtils.addFacesErrorMessage("复核意见不能为空！");
            
        } else if (state != null && state.equals(Constants.STATE_AFF_REVIEW)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_PROCESSED);
            
            boolean success = ADFUtils.commit("报修单复核完成！", "报修单复核失败，请核对输入的信息或联系管理员！");
            
            if (success) {
                String callerId = ADFUtils.getBoundAttributeValue("CallerId").toString();
                
                String id = (ADFUtils.getBoundAttributeValue("CallId")).toString();
                String readableId = (String)ADFUtils.getBoundAttributeValue("CallReadableId");
                
                //complete the task for affair review
                completeTask(Constants.CONTEXT_TYPE_HELPDESK, id, Constants.ROLE_HD_REVIEW);
                        
                //create evaluation task for caller
                createTaskForUser(id, Constants.CONTEXT_TYPE_HELPDESK, "您的报修请求已处理，请评价，报修单号：" + readableId, callerId, readableId);
                
                ADFUtils.findOperation("Commit").execute();
            } else {
                ADFUtils.setBoundAttributeValue("State", state);
            }
        }
    }

    public void evaluateHdCall(ActionEvent actionEvent) {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        String callerId = ADFUtils.getBoundAttributeValue("CallerId").toString();
//        String calleeId = ADFUtils.getBoundAttributeValue("CalleeId").toString();
        System.out.println("callerId is: " + callerId);
//        System.out.println("calleeId is: " + calleeId);
        
        if (state != null && state.equals(Constants.STATE_PROCESSED)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_EVALUATED);
            boolean success = ADFUtils.commit("报修单已评价！", "报修单评价失败，请核对输入的信息或联系管理员！");
            if (success) {
                String id = ((DBSequence)ADFUtils.getBoundAttributeValue("CallId")).toString();
                
                //complete the evaluation task
                completeTaskForUser(Constants.CONTEXT_TYPE_HELPDESK, id, callerId);
                
                //send notification to callee
//                sendNotification("您的报修处理已评价", "您的报修处理已评价", calleeId, null);
                
                ADFUtils.findOperation("Commit").execute();
            } else {
                ADFUtils.setBoundAttributeValue("State", state);
            }
        }
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

    public String getCallReadableId() {
        return callReadableId;
    }
    
    public void setCallReadableId(String rId) {
        this.callReadableId = rId;
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

    public void reasonLevelChanged(ValueChangeEvent valueChangeEvent) {
//        DCIteratorBinding lbinding = ADFUtils.findIterator("HelpdeskCallsViewIterator");
//        lbinding.executeQuery();
    }
    
    private void setSubmitDate() {
        DCIteratorBinding it = ADFUtils.findIterator("HelpdeskCallsViewIterator");
        Row row = it.getCurrentRow();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        System.out.println("Current time is: " + now);
        row.setAttribute("SubmitAt", now);
    }

    public void onReasonLevel1Change(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }

    private String getRoleName(final String locId, final String rsnLv1) {
        OperationBinding op = ADFUtils.findOperation("findByLocationIdAndHdReason");
        op.getParamsMap().put("locationId", locId);
        op.getParamsMap().put("hdReason", rsnLv1);
        op.execute();
        
        final String roleName = (String)op.getResult();
        System.out.println("Role name in bean is: " + roleName);
        return roleName;
    }
    
    private String getRoleName(final String rsnLv1) {
        OperationBinding op = ADFUtils.findOperation("findByHdReason");
        op.getParamsMap().put("hdReason", rsnLv1);
        op.execute();
        
        final String roleName = (String)op.getResult();
        System.out.println("Role name in bean is: " + roleName);
        return roleName;
    }

    public String returnClicked() {        
        DCIteratorBinding state = ADFUtils.findIterator("HdStateWithEmptyIterator");
        state.executeQuery();
        
        DCIteratorBinding result = ADFUtils.findIterator("HdResultWithEmptyIterator");
        result.executeQuery();
        
        DCIteratorBinding reason = ADFUtils.findIterator("ReasonLevel1WithEmptyIterator");
        reason.executeQuery();
        
        DCIteratorBinding callee = ADFUtils.findIterator("EmpWithEmptyForCalleeIterator");
        callee.executeQuery();
        
        DCIteratorBinding caller = ADFUtils.findIterator("EmpWithEmptyForCallerIterator");
        caller.executeQuery();
        
        DCIteratorBinding eval = ADFUtils.findIterator("HdEvalWithEmptyIterator");
        eval.executeQuery();
        
        DCIteratorBinding location = ADFUtils.findIterator("LocationsWithEmptyIterator");
        location.executeQuery();
        
        this.callReadableId = null;
        this.submitDateFrom = null;
        this.submitDateTo = null;
        
        String fromMenu = (String)JSFUtils.resolveExpression("#{pageFlowScope.fromMenu}");
        System.out.println("from menu: " + fromMenu);
        
        if ("callee".equals(fromMenu)) {
            return "returnFromCallee";
        } else if ("assign".equals(fromMenu)) {
            return "returnFromAssigner";
        } else if ("review".equals(fromMenu)) {
            return "returnFromReview";
        } else if ("query".equals(fromMenu)) {
            return "returnFromQuery";
        } else {
            return "returnFromCaller";
        }
    }

    public void confirm(DialogEvent dialogEvent) {
        System.out.println("fromButton: " + fromButton);
    }

    public void setFromButton(String fromButton) {
        System.out.println("set the fromButton: " + fromButton);
        this.fromButton = fromButton;
    }

    public String getFromButton() {
        return fromButton;
    }

    private String getProcessDept(String rsnLv1) {
        OperationBinding op = ADFUtils.findOperation("findDeptIdByHdReason");
        op.getParamsMap().put("hdReason", rsnLv1);
        op.execute();
        
        final String deptId = (String)op.getResult();
        System.out.println("Dept id in bean is: " + deptId);
        return deptId;
    }

    public void calleeChanged(ValueChangeEvent event) {
        //Set the old value so that it can be used to complete the task
//        oldCallee = (String)event.getOldValue();
    }
    
    public void submitConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            submitHdCall(null);
            
//            ADFUtils.partialRefreshComponenet(poLinesTable);
//            ADFUtils.partialRefreshComponenet(poHistoryTable);
//            ADFUtils.partialRefreshComponenet(poForm);
        }
    }
    
    public void assignConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            assignHdCall(null);
        }
    }
    
    public void cancelConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            cancelHdCall(null);
        }
    }
    
    public void processConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            processHdCall(null);
        }
    }
    
    public void reviewConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            reviewHdCall(null);
        }
    }
    
    public void evalConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            evaluateHdCall(null);
        }
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
    
    public void setActionListener(ClientEvent clientEvent) {
        System.err.println("here");
        String newAction  = (String) clientEvent.getParameters().get("payload");
        this.setAction(newAction);
    }
    
    public void setAssignButtonRendered(boolean assignButtonRendered) {
        this.assignButtonRendered = assignButtonRendered;
    }

    public boolean isAssignButtonRendered() {
        assignButtonRendered = 
            JSFUtils.resolveExpressionAsBoolean("#{((sessionScope.LoginUserBean.isUserInRole['总务报修分派'] and bindings.ReasonLevel1.inputValue eq '总务') or (sessionScope.LoginUserBean.isUserInRole['信息报修分派'] and bindings.ReasonLevel1.inputValue eq '信息'))" 
                                                + " and (bindings.State.inputValue eq '已受理' or bindings.State.inputValue eq '已分派') and pageFlowScope.fromMenu != 'query' and (pageFlowScope.fromMenu != '-' or (pageFlowScope.fromMenu == '-' and bindings.CalleeFullName == 'nullnull'))}");
        
        return assignButtonRendered;
    }

    public void setProcessPanelRendered(boolean processPanelRendered) {
        this.processPanelRendered = processPanelRendered;
    }

    public boolean isProcessPanelRendered() {
        processPanelRendered = 
            JSFUtils.resolveExpressionAsBoolean("#{(bindings.State.inputValue ne '未提交' and bindings.State.inputValue ne '已撤消' and bindings.State.inputValue ne '已受理'" + 
                                                " and (pageFlowScope.fromMenu eq 'callee' or (pageFlowScope.fromMenu eq '-' and ((!sessionScope.LoginUserBean.isUserInRole['总务报修分派'] and !sessionScope.LoginUserBean.isUserInRole['信息报修分派']) or sessionScope.LoginUserBean.userId eq bindings.CalleeId.inputValue))) )" + 
                                                " or (bindings.State.inputValue eq '已处理' or bindings.State.inputValue eq '已评价' or bindings.State.inputValue eq '总务复核')}");
        return processPanelRendered;
    }

    public void setAssignLovRendered(boolean assignLovRendered) {
        this.assignLovRendered = assignLovRendered;
    }

    public boolean isAssignLovRendered() {
        assignLovRendered = 
            JSFUtils.resolveExpressionAsBoolean("#{((sessionScope.LoginUserBean.isUserInRole['总务报修分派'] and bindings.ReasonLevel1.inputValue eq '总务') or (sessionScope.LoginUserBean.isUserInRole['信息报修分派'] and bindings.ReasonLevel1.inputValue eq '信息')) and pageFlowScope.fromMenu ne 'query' and bindings.State.inputValue ne '未提交'}");
        return assignLovRendered;
    }
}

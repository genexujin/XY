package com.xy.scpms.view.page;


import com.xy.scpms.model.utils.Codes;
import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.util.HashMap;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUEventBinding;


public class WelcomeBean {
    private RichPopup popup;
    private String approveType;
    private RichInputText memoInput;
    private RichTable pendInvTable;
    private Date planPaymentDate;
    private boolean isApprovedInv;
    private RichPopup invPopup;
    private String appInvMemo;
    private RichTable invReqTable;
    private RichTable dashboardTable;
    private Number contracId;
    private RichPopup contractPopup;

    public WelcomeBean() {
    }

    private void openContractById(Number id) {
        if (id != null) {
            this.contracId = id;
            contractPopup.cancel();
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.contractPopup.show(hints);
        }
    }
    
    public void openMthlyEffDBClick(ClientEvent clientEvent) {

        try {
            Number id = (Number)ADFUtils.getBoundAttributeValue("MthEffId");
            
            openContractById(id);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }
           
    public void openMthlyPayDBClick(ClientEvent clientEvent) {

        try {
            Number id = (Number)ADFUtils.getBoundAttributeValue("MthPayId");
            
            openContractById(id);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void openAIPContractDBClick(ClientEvent clientEvent) {

        try {
            Number id = (Number)ADFUtils.getBoundAttributeValue("ContractIdAIP");
            openContractById(id);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void openInvNotContractDBClick(ClientEvent clientEvent) {

        try {
            Number id = (Number)ADFUtils.getBoundAttributeValue("ContractId");
            openContractById(id);

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void openInvReqContractDBClick(ClientEvent clientEvent) {

        try {
            Number id = (Number)ADFUtils.getBoundAttributeValue("InvReqContractId");
            openContractById(id);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void openPayNotContractDBClick(ClientEvent clientEvent) {

        try {
            Number id = (Number)ADFUtils.getBoundAttributeValue("PaymentHeaderId");
            openContractById(id);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void submitInDashboard(ActionEvent actionEvent) {
        OperationBinding binding = ADFUtils.findOperation("aipActionExecute");
        binding.execute();
        if (!binding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("审批时出错, 请联系管理员 !");
        } else {
            ADFUtils.findOperation("setAIPCriteria").execute();
            JSFUtils.addFacesInformationMessage("审核操作已完成！");
//            ADFUtils.findIterator("ApprovalInProcessIterator").executeQuery();
        }
        ADFUtils.partialRefreshComponenet(dashboardTable);
        popup.hide();
    }

    public String submit() {
        OperationBinding binding = ADFUtils.findOperation("aipActionExecute");
        binding.execute();
        if (!binding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("审批时出错, 请联系管理员 !");
        } else {
            
            JSFUtils.addFacesInformationMessage("审核操作已完成，返回任务列表页面！");
        }
        return "taskList";
    }

    public String cancel() {
        this.popup.cancel();
        return null;
    }

    public String setApproved() {
        DCIteratorBinding it = ADFUtils.findIterator("ApprovalInProcessIterator");
        if (it.getCurrentRow() != null) {
            this.approveType = Codes.COL_VALUE_AIP_OUTCOME_APPROVED;
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.popup.show(hints);

        } else {
            JSFUtils.addFacesErrorMessage("请选择一条合同，然后再提交审核！");
        }
        return null;
    }

    public String setRejected() {
        DCIteratorBinding it = ADFUtils.findIterator("ApprovalInProcessIterator");
        if (it.getCurrentRow() != null) {
            this.approveType = Codes.COL_VALUE_AIP_OUTCOME_REJECTED;
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.popup.show(hints);
        } else {
            JSFUtils.addFacesErrorMessage("请选择一条合同，然后再提交审核！");
        }
        return null;
    }

    public void processEvent(ActionEvent actionEvent) {

        Object deptt = JSFUtils.resolveExpression("#{sessionScope.userDept}");
        if (deptt == null) {
            //得到iterator binding的引用
            OperationBinding method = ADFUtils.findOperation("getDeptById");
            method.execute();
            String deptName = (String)method.getResult();
            JSFUtils.setExpressionValue("#{sessionScope.userDept}", deptName);
        }
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUEventBinding eventBinding = (JUEventBinding)bindingContainer.get("appReqEvtBinding");
        ActionListener actionListener = (ActionListener)eventBinding.getListener();
        actionListener.processAction(actionEvent);
    }

    public String createInvReq() {

        Number amount = (Number)ADFUtils.getBoundAttributeValue("PaymentPlanAmount");
        Number paymentId = (Number)ADFUtils.getBoundAttributeValue("PaymentId");
        Number contractId = (Number)ADFUtils.getBoundAttributeValue("ContractId");
        Number lineId = (Number)ADFUtils.getBoundAttributeValue("ContractLineId");
        String dept = (String)ADFUtils.getBoundAttributeValue("DesignDept");


        HashMap payments = new HashMap();
        HashMap lineParam = new HashMap();
        lineParam.put("amount", amount);
        lineParam.put("contractId", contractId);
        lineParam.put("dept", dept);
        lineParam.put("lineId", lineId);
        lineParam.put("payPlanDate", this.planPaymentDate);

        payments.put(paymentId, lineParam);

        OperationBinding binding = ADFUtils.findOperation("quickCreateInvoiceRequisition");
        binding.getParamsMap().put("payments", payments);
        binding.execute();

        OperationBinding commit = ADFUtils.findOperation("Commit");
        commit.execute();

        if (!commit.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("提交发票申请时出错, 请联系管理员 !");
        } else {
            JSFUtils.addFacesInformationMessage("已成功提交发票申请！");
            ADFUtils.findIterator("InvoiceNotificaitonViewIterator").executeQuery();
            ADFUtils.partialRefreshComponenet(pendInvTable);
            planPaymentDate = null;
        }

        return null;
    }

    public void setPendInvTable(RichTable pendInvTable) {
        this.pendInvTable = pendInvTable;
    }

    public RichTable getPendInvTable() {
        return pendInvTable;
    }

    public void invAppSubmit(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            createInvReq();
        } else {
            ADFUtils.findOperation("Rollback").execute();
        }
    }

    public void setPlanPaymentDate(Date planPaymentDate) {
        this.planPaymentDate = planPaymentDate;
    }

    public Date getPlanPaymentDate() {
        return planPaymentDate;
    }

    public void confirmApproveInvoice(DialogEvent dialogEvent) {

        try {
            if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
                OperationBinding approvePayment = ADFUtils.findOperation("setReqApproved");
                OperationBinding rejectPayment = ADFUtils.findOperation("setReqRejected");
                DCIteratorBinding it = ADFUtils.findIterator("InvoiceRequisitionViewIterator");

                String loginUser = JSFUtils.resolveExpressionAsString("#{securityContext.userName}");

                Row row = it.getCurrentRow();

                if (JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['design_director']}")) {

                    if (this.isApprovedInv) {
                        row.setAttribute("DeptHeadApprover", loginUser);
                        row.setAttribute("DeptHeadOutcome", Codes.COL_VALUE_AIP_OUTCOME_APPROVED);
                        row.setAttribute("DeptHeadDate", new Date(new java.sql.Date(System.currentTimeMillis())));
                        row.setAttribute("DeptHeadMemo", this.appInvMemo);
                        row.setAttribute("MrktHeadOutcome", Codes.COL_VALUE_AIP_OUTCOME_PENDING);

                    }

                    else {
                        Number id = (Number)ADFUtils.getBoundAttributeValue("InvReqId");
                        row.setAttribute("DeptHeadApprover", loginUser);
                        row.setAttribute("DeptHeadOutcome", Codes.COL_VALUE_AIP_OUTCOME_REJECTED);
                        row.setAttribute("DeptHeadDate", new Date(new java.sql.Date(System.currentTimeMillis())));
                        row.setAttribute("DeptHeadMemo", appInvMemo);
                        row.setAttribute("MrktHeadOutcome", Codes.COL_VALUE_AIP_OUTCOME_REJECTED);
                        rejectPayment.getParamsMap().put("id", id);
                        rejectPayment.execute();
                    }

                } else if (JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['marketing_director']}")) {
                    if (this.isApprovedInv) {
                        Number id = (Number)ADFUtils.getBoundAttributeValue("InvReqId");
                        row.setAttribute("MrktHeadApprover", loginUser);
                        row.setAttribute("MrktHeadOutcome", Codes.COL_VALUE_AIP_OUTCOME_APPROVED);
                        row.setAttribute("MrktHeadDate", new Date(new java.sql.Date(System.currentTimeMillis())));
                        row.setAttribute("MrktHeadMemo", appInvMemo);
                        approvePayment.getParamsMap().put("id", id);
                        approvePayment.execute();


                    } else {
                        Number id = (Number)ADFUtils.getBoundAttributeValue("InvReqId");
                        row.setAttribute("MrktHeadApprover", loginUser);
                        row.setAttribute("MrktHeadOutcome", Codes.COL_VALUE_AIP_OUTCOME_REJECTED);
                        row.setAttribute("MrktHeadDate", new Date(new java.sql.Date(System.currentTimeMillis())));
                        row.setAttribute("MrktHeadMemo", appInvMemo);
                        rejectPayment.getParamsMap().put("id", id);
                        rejectPayment.execute();
                    }
                }


                OperationBinding op = ADFUtils.findOperation("Commit");
                op.execute();

                if (!(op.getErrors().size() == 0)) {
                    JSFUtils.addFacesErrorMessage("审核中发生系统错误，请联系系统管理员！");
                } else {
                    JSFUtils.addFacesInformationMessage("审核操作完成！");
                }

                it.executeQuery();
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.invReqTable);
                this.invPopup.hide();
            } else {
                ADFUtils.findOperation("Rollback").execute();
                this.invPopup.hide();
            }
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage("审核中发生系统错误，请联系系统管理员！");
            e.printStackTrace();
        }

    }

    public String setApproveInv() {
        DCIteratorBinding it = ADFUtils.findIterator("InvoiceRequisitionViewIterator");
        if (it.getCurrentRow() != null) {
            this.isApprovedInv = true;
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.invPopup.show(hints);
        } else {
            JSFUtils.addFacesErrorMessage("请选择一条发票申请，然后再提交审核！");
        }
        return null;
    }

    public String setRejectInv() {
        DCIteratorBinding it = ADFUtils.findIterator("InvoiceRequisitionViewIterator");
        if (it.getCurrentRow() != null) {
            this.isApprovedInv = false;
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.invPopup.show(hints);
        } else {
            JSFUtils.addFacesErrorMessage("请选择一条发票申请，然后再提交审核！");
        }
        return null;
    }

    public void setIsApprovedInv(boolean isApprovedInv) {
        this.isApprovedInv = isApprovedInv;
    }

    public boolean isIsApprovedInv() {
        return isApprovedInv;
    }

    public void setInvPopup(RichPopup invPopup) {
        this.invPopup = invPopup;
    }

    public RichPopup getInvPopup() {
        return invPopup;
    }

    public void setAppInvMemo(String appInvMemo) {
        this.appInvMemo = appInvMemo;
    }

    public String getAppInvMemo() {
        return appInvMemo;
    }

    public void setInvReqTable(RichTable invReqTable) {
        this.invReqTable = invReqTable;
    }

    public RichTable getInvReqTable() {
        return invReqTable;
    }

    public void setApproveType(String approveType) {
        this.approveType = approveType;
    }

    public String getApproveType() {
        return approveType;
    }

    public void setMemoInput(RichInputText memoInput) {
        this.memoInput = memoInput;
    }

    public RichInputText getMemoInput() {
        return memoInput;
    }

    public void setDashboardTable(RichTable dashboardTable) {
        this.dashboardTable = dashboardTable;
    }

    public RichTable getDashboardTable() {
        return dashboardTable;
    }

    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }

    public void setContracId(Number contracId) {
        this.contracId = contracId;
    }

    public Number getContracId() {
        return contracId;
    }

    public void setContractPopup(RichPopup contractPopup) {
        this.contractPopup = contractPopup;
    }

    public RichPopup getContractPopup() {
        return contractPopup;
    }
}

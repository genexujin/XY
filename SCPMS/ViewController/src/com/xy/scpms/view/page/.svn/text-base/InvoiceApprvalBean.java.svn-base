package com.xy.scpms.view.page;


import com.xy.scpms.model.utils.Codes;
import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;


public class InvoiceApprvalBean {
    private RichPanelFormLayout historyForm;
    private RichPanelFormLayout contractForm;
    private RichPanelFormLayout lineForm;
    private RichPopup popup;
    private String popupTitle;
    private String action;
    private RichInputText designMemo;
    private RichInputText marketingMemo;
    private RichTable table;
    private RichCommandToolbarButton okBtn;
    private RichCommandToolbarButton allOkBtn;
    private RichCommandToolbarButton rejectBtn;

    public InvoiceApprvalBean() {
    }

    public String confirm() {
        
        OperationBinding approvePayment =
            ADFUtils.findOperation("setReqApproved");
        OperationBinding rejectPayment =
            ADFUtils.findOperation("setReqRejected");

        try {
            String loginUser =
                JSFUtils.resolveExpressionAsString("#{securityContext.userName}");

            if (JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['design_director']}")) {

                if (action.equals("approve")) {
                    ADFUtils.setBoundAttributeValue("DeptHeadApprover",
                                                    loginUser);
                    ADFUtils.setBoundAttributeValue("DeptHeadOutcome",
                                                    Codes.COL_VALUE_AIP_OUTCOME_APPROVED);
                    ADFUtils.setBoundAttributeValue("DeptHeadDate",
                                                    new Date(new java.sql.Date(System.currentTimeMillis())));
                    ADFUtils.setBoundAttributeValue("DeptHeadMemo",
                                                    (String)designMemo.getValue());
                    ADFUtils.setBoundAttributeValue("MrktHeadOutcome",
                                                    Codes.COL_VALUE_AIP_OUTCOME_PENDING);

                } else if (action.equals("approveAll")) {
                    DCIteratorBinding binding =
                        ADFUtils.findIterator("InvoiceRequisitionViewIterator");
                    RowSetIterator iterator = binding.getRowSetIterator();
                    Row row = iterator.first();
                    while (row != null) {

                        row.setAttribute("DeptHeadApprover", loginUser);
                        row.setAttribute("DeptHeadOutcome",
                                         Codes.COL_VALUE_AIP_OUTCOME_APPROVED);
                        row.setAttribute("DeptHeadDate",
                                         new Date(new java.sql.Date(System.currentTimeMillis())));
                        row.setAttribute("DeptHeadMemo",
                                         (String)designMemo.getValue());
                        row.setAttribute("MrktHeadOutcome",
                                         Codes.COL_VALUE_AIP_OUTCOME_PENDING);
                        
                        if (iterator.hasNext())
                            row = iterator.next();
                        else
                            row = null;
                    }

                } else if (action.equals("reject")) {
                    Number id = (Number)ADFUtils.getBoundAttributeValue("Id");
                    ADFUtils.setBoundAttributeValue("DeptHeadApprover",
                                                    loginUser);
                    ADFUtils.setBoundAttributeValue("DeptHeadOutcome",
                                                    Codes.COL_VALUE_AIP_OUTCOME_REJECTED);
                    ADFUtils.setBoundAttributeValue("DeptHeadDate",
                                                    new Date(new java.sql.Date(System.currentTimeMillis())));
                    ADFUtils.setBoundAttributeValue("DeptHeadMemo",
                                                    (String)designMemo.getValue());
                    ADFUtils.setBoundAttributeValue("MrktHeadOutcome",
                                                    Codes.COL_VALUE_AIP_OUTCOME_REJECTED);
                    rejectPayment.getParamsMap().put("id", id);
                    rejectPayment.execute();
                }

            } else if (JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['marketing_director']}")) {
                if (action.equals("approve")) {
                    Number id = (Number)ADFUtils.getBoundAttributeValue("Id");
                    ADFUtils.setBoundAttributeValue("MrktHeadApprover",
                                                    loginUser);
                    ADFUtils.setBoundAttributeValue("MrktHeadOutcome",
                                                    Codes.COL_VALUE_AIP_OUTCOME_APPROVED);
                    ADFUtils.setBoundAttributeValue("MrktHeadDate",
                                                    new Date(new java.sql.Date(System.currentTimeMillis())));
                    ADFUtils.setBoundAttributeValue("MrktHeadMemo",
                                                    (String)marketingMemo.getValue());
                    approvePayment.getParamsMap().put("id", id);
                    approvePayment.execute();


                } else if (action.equals("approveAll")) {
                    DCIteratorBinding binding =
                        ADFUtils.findIterator("InvoiceRequisitionViewIterator");
                    RowSetIterator iterator = binding.getRowSetIterator();
                    Row row = iterator.first();
                    while (row != null) {
                        Number id = (Number)row.getAttribute("Id");
                        row.setAttribute("MrktHeadApprover", loginUser);
                        row.setAttribute("MrktHeadOutcome",
                                         Codes.COL_VALUE_AIP_OUTCOME_APPROVED);
                        row.setAttribute("MrktHeadDate",
                                         new Date(new java.sql.Date(System.currentTimeMillis())));
                        row.setAttribute("MrktHeadMemo",
                                         (String)marketingMemo.getValue());
                        approvePayment.getParamsMap().put("id", id);
                        approvePayment.execute();
                        if (iterator.hasNext())
                            row = iterator.next();
                        else
                            row = null;
                    }
                } else if (action.equals("reject")) {
                    Number id = (Number)ADFUtils.getBoundAttributeValue("Id");
                    ADFUtils.setBoundAttributeValue("MrktHeadApprover",
                                                    loginUser);
                    ADFUtils.setBoundAttributeValue("MrktHeadOutcome",
                                                    Codes.COL_VALUE_AIP_OUTCOME_REJECTED);
                    ADFUtils.setBoundAttributeValue("MrktHeadDate",
                                                    new Date(new java.sql.Date(System.currentTimeMillis())));
                    ADFUtils.setBoundAttributeValue("MrktHeadMemo",
                                                    (String)marketingMemo.getValue());
                    rejectPayment.getParamsMap().put("id", id);
                    rejectPayment.execute();
                }
            }
        } catch (Exception e) {
            ADFUtils.findOperation("Rollback").execute();
            JSFUtils.addFacesErrorMessage("审核中发生系统错误，请联系系统管理员！");
            e.printStackTrace();
        }


        OperationBinding op = ADFUtils.findOperation("Commit");
        op.execute();

        if (!(op.getErrors().size() == 0)) {
            JSFUtils.addFacesErrorMessage("审核中发生系统错误，请联系系统管理员！");
        } else {
            JSFUtils.addFacesInformationMessage("审核操作完成！");
        }

        ADFUtils.findIterator("InvoiceRequisitionViewIterator").executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(table);
        
        this.okBtn.setDisabled(true);
        this.allOkBtn.setDisabled(true);
        this.rejectBtn.setDisabled(true);
        
        ADFUtils.partialRefreshComponenet(this.okBtn);
        ADFUtils.partialRefreshComponenet(this.allOkBtn);
        ADFUtils.partialRefreshComponenet(this.rejectBtn);

        popup.hide();
        return null;
    }


    public String cancel() {
        popup.hide();
        return null;
    }


    public void setHistoryForm(RichPanelFormLayout historyForm) {
        this.historyForm = historyForm;
    }

    public RichPanelFormLayout getHistoryForm() {
        return historyForm;
    }

    public void setContractForm(RichPanelFormLayout contractForm) {
        this.contractForm = contractForm;
    }

    public RichPanelFormLayout getContractForm() {
        return contractForm;
    }

    public void setLineForm(RichPanelFormLayout lineForm) {
        this.lineForm = lineForm;
    }

    public RichPanelFormLayout getLineForm() {
        return lineForm;
    }


    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }

    public void setPopupTitle(String popupTitle) {
        this.popupTitle = popupTitle;
    }

    public String getPopupTitle() {
        return popupTitle;
    }

    public String setApproveAction() {
        this.action = "approve";
        this.popupTitle = "审核通过该发票申请。";
        showPopup();
        return null;
    }

    public String setApproveAllAction() {
        this.action = "approveAll";
        this.popupTitle = "审核通过所有发票申请。";
        showPopup();
        return null;
    }

    public String setRejectAction() {
        this.action = "reject";
        this.popupTitle = "退回该发票申请。";
        showPopup();
        return null;
    }

    public void showPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popup.show(hints);
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }


    public void setDesignMemo(RichInputText designMemo) {
        this.designMemo = designMemo;
    }

    public RichInputText getDesignMemo() {
        return designMemo;
    }

    public void setMarketingMemo(RichInputText marketingMemo) {
        this.marketingMemo = marketingMemo;
    }

    public RichInputText getMarketingMemo() {
        return marketingMemo;
    }

    public void setTable(RichTable table) {
        this.table = table;
    }

    public RichTable getTable() {
        return table;
    }

    public void setOkBtn(RichCommandToolbarButton okBtn) {
        this.okBtn = okBtn;
    }

    public RichCommandToolbarButton getOkBtn() {
        return okBtn;
    }

    public void setAllOkBtn(RichCommandToolbarButton allOkBtn) {
        this.allOkBtn = allOkBtn;
    }

    public RichCommandToolbarButton getAllOkBtn() {
        return allOkBtn;
    }

    public void setRejectBtn(RichCommandToolbarButton rejectBtn) {
        this.rejectBtn = rejectBtn;
    }

    public RichCommandToolbarButton getRejectBtn() {
        return rejectBtn;
    }

    /**
     * 当选择某行时，更新其相关信息
     * @param selectionEvent
     */
    public void onSelect(SelectionEvent selectionEvent) {
        
        try {
            
            ADFUtils.makeCurrent(selectionEvent);
            
            this.okBtn.setDisabled(false);
            this.allOkBtn.setDisabled(false);
            this.rejectBtn.setDisabled(false);
            
            ADFUtils.partialRefreshComponenet(this.okBtn);
            ADFUtils.partialRefreshComponenet(this.allOkBtn);
            ADFUtils.partialRefreshComponenet(this.rejectBtn);

            
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage("查询相关合同信息发生错误！");
            e.printStackTrace();
        }

    }
}

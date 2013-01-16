package com.xy.scpms.view.page;


import com.xy.scpms.model.query.InvoiceNotificaitonViewImpl;
import com.xy.scpms.model.utils.Codes;
import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.util.HashMap;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichQuery;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.QueryEvent;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;


public class PaymentNodesBean {

    private Number low;
    private Number high;
    private RichQuery query;
    private RichTable table;
    private HashMap paymentsMap;
    private RichPopup popup;
    private RichTable invReqTable;
    private RichPopup splitPopup;
    private Number paymentAmount;
    private RichTable splitTable;
    private RichPopup notePopup;
    private RichPanelFormLayout contractForm;
    private RichPanelFormLayout lineForm;
    private RichPanelFormLayout paymentForm;
    private RichCommandToolbarButton applyBtn;
    private RichPopup confirmSplit;
    private Number firstPayAmt;
    private Number paymentId;
    private RichPopup contractDetailPopup;
    
    public void openContract(ClientEvent clientEvent) {
        
        contractDetailPopup.cancel();
        
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        
        this.contractDetailPopup.show(hints);
        
        
    }

    public String cancelSplit() {
        OperationBinding op = ADFUtils.findOperation("Rollback");
        op.execute();
        reexecuteView();
        splitPopup.cancel();
        return null;
    }

    public String cancelInConfirm() {
        OperationBinding op = ADFUtils.findOperation("Rollback");
        op.execute();
        reexecuteView();
        if (splitPopup != null)
            splitPopup.cancel();
        if (confirmSplit != null)
            confirmSplit.cancel();
        return null;
    }

    public String doMath() {

        double total = this.paymentAmount.doubleValue();
        double first = this.firstPayAmt.doubleValue();
        if (total > first) {

            OperationBinding op = ADFUtils.findOperation("splitLinePayment");
            op.getParamsMap().put("paymentId", paymentId);
            op.getParamsMap().put("firstPayAmt", firstPayAmt);
            op.execute();

            if (op.getErrors().size() == 0) {
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                confirmSplit.show(hints);
            } else {
                JSFUtils.addFacesErrorMessage("在拆分节点时发生错误，请联系管理员！");
            }
        } else {
            JSFUtils.addFacesErrorMessage("请输入一个小于原节点金额的值！");
        }
        return null;
    }

    public String confirmToSplit() {
        //得到iterator binding的引用
        CollectionModel model = (CollectionModel)splitTable.getValue();
        JUCtrlHierBinding _adfTableBinding =
            (JUCtrlHierBinding)model.getWrappedData();
        Row[] allRowsInRange = _adfTableBinding.getAllRowsInRange();
        Number total = new Number(0);
        for (Row row : allRowsInRange) {
            Number amount = (Number)row.getAttribute("PaymentPlanAmount");
            total = total.add(amount);
            row.setAttribute("Status", Codes.COL_VALUE_PAYMENT_STATUS_INITIAL);
        }

        if (!total.equals(paymentAmount)) {
            JSFUtils.addFacesErrorMessage("拆分节点金额之和必须等于原节点！ 原节点金额为：" +
                                          paymentAmount);
        } else {
            OperationBinding op = ADFUtils.findOperation("Commit");
            op.execute();
            if (!(op.getErrors().size() == 0)) {
                JSFUtils.addFacesErrorMessage("在拆分节点时发生错误，请联系管理员！");
            } else {
                JSFUtils.addFacesInformationMessage("已成功拆分该节点！");
            }
            if (splitPopup != null)
                splitPopup.cancel();
            if (confirmSplit != null)
                confirmSplit.cancel();
            reexecuteView();
        }
        applyBtn.setDisabled(true);
        ADFUtils.partialRefreshComponenet(this.applyBtn);

        return null;
    }


    /**
     *
     * @param actionEvent
     */
    public void splitPaymentNode(ActionEvent actionEvent) {

        this.paymentId =
                (Number)actionEvent.getComponent().getAttributes().get("paymentId");
        //        OperationBinding op = ADFUtils.findOperation("splitLinePayment");
        //        op.getParamsMap().put("paymentId", paymentId);
        //        op.execute();
        OperationBinding op = ADFUtils.findOperation("setCurrentPayment");
        op.getParamsMap().put("id", paymentId);
        op.execute();
        paymentAmount =
                (Number)ADFUtils.getBoundAttributeValue("PaymentPlanAmount");
        if (paymentAmount != null) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            splitPopup.show(hints);
        } else {
            JSFUtils.addFacesErrorMessage("在拆分节点时发生错误，请联系管理员！");
        }


    }

    public String submitInvReqs() {
        //得到iterator binding的引用
        CollectionModel model = (CollectionModel)invReqTable.getValue();
        JUCtrlHierBinding _adfTableBinding =
            (JUCtrlHierBinding)model.getWrappedData();
        Row[] allRowsInRange = _adfTableBinding.getAllRowsInRange();
        for (Row row : allRowsInRange) {

            row.setAttribute("Status",
                             Codes.COL_VALUE_STATUS_PENDING_APPROVAL);
            row.setAttribute("DeptHeadOutcome",
                             Codes.COL_VALUE_AIP_OUTCOME_PENDING);
            row.setAttribute("MrktHeadOutcome",
                             Codes.COL_VALUE_AIP_OUTCOME_WAITING);
        }

        OperationBinding commit = ADFUtils.findOperation("Commit");
        commit.execute();

        if (!commit.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("提交审批时出错, 请联系管理员 !");
        } else {
            JSFUtils.addFacesInformationMessage("已成功提交发票申请！");
        }

        this.popup.hide();
        this.reexecuteView();
        applyBtn.setDisabled(true);
        ADFUtils.partialRefreshComponenet(this.applyBtn);

        return null;
    }

    /**
     *
     * @param queryEvent
     */
    public void onQuery(QueryEvent queryEvent) {
        Boolean isDeptMgr =
            JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['design_director']}");
        Boolean isVIP =
            JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['marketing_director,contractor,vip']}");
        if (isDeptMgr) {
            InvoiceNotificaitonViewImpl vo =
                applyViewCriteria("filterByDept", "InvoiceNotificaitonViewIterator");
            String dept =
                (String)JSFUtils.resolveExpression("#{sessionScope.userDept}");
            vo.setdept(dept);
        } else if (isVIP) {
            applyViewCriteria(null, "InvoiceNotificaitonViewIterator");

        } else {
            InvoiceNotificaitonViewImpl vo =
                applyViewCriteria("filterByMgrId", "InvoiceNotificaitonViewIterator");
            String userName =
                JSFUtils.resolveExpressionAsString("#{securityContext.userName}");
            vo.setmgrId(userName);
        }
        //执行query
        invokeQueryEventMethodExpression("#{bindings.ImplicitViewCriteriaQuery.processQuery}",
                                         queryEvent);

        ADFUtils.findIterator("ContractIterator").executeQuery();
        ADFUtils.findIterator("ContractLineIterator").executeQuery();
        ADFUtils.findIterator("ContractLinePaymentsIterator").executeQuery();

        DCIteratorBinding it =
            ADFUtils.findIterator("InvoiceNotificaitonViewIterator");

        Row currentRow = it.getCurrentRow();

        if (currentRow != null) {
            
            Number contractId = (Number)currentRow.getAttribute("ContractId");
            Number lineId = (Number)currentRow.getAttribute("ContractLineId");
            Number payId = (Number)currentRow.getAttribute("PaymentId");
            OperationBinding setContractOp =
                ADFUtils.findOperation("setCurrentContract");
            setContractOp.getParamsMap().put("id", contractId);
            setContractOp.execute();

            OperationBinding setLineOp =
                ADFUtils.findOperation("setCurrentLine");
            setLineOp.getParamsMap().put("id", lineId);
            setLineOp.execute();

            OperationBinding setPaymentOp =
                ADFUtils.findOperation("setCurrentPayment");
            setPaymentOp.getParamsMap().put("id", payId);
            setPaymentOp.execute();

            applyBtn.setDisabled(false);

            ADFUtils.partialRefreshComponenet(this.contractForm);
            ADFUtils.partialRefreshComponenet(this.lineForm);
            ADFUtils.partialRefreshComponenet(this.paymentForm);
            ADFUtils.partialRefreshComponenet(this.applyBtn);

        }

    }


    private InvoiceNotificaitonViewImpl applyViewCriteria(String criteriaName,
                                                          String iteratorName) {
        //得到iterator binding的引用
        DCIteratorBinding binding = ADFUtils.findIterator(iteratorName);
        //得到bind的viewobject,并为view object的bind variable赋值
        InvoiceNotificaitonViewImpl vo =
            (InvoiceNotificaitonViewImpl)binding.getViewObject();
        vo.setApplyViewCriteriaNames(null);
        if (criteriaName != null) {
            ViewCriteria criteria = vo.getViewCriteria(criteriaName);
            vo.applyViewCriteria(criteria);
        }
        if (low != null)
            vo.setlowDayLimit(low);

        if (high != null)
            vo.sethighDayLimit(high);

        return vo;
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

    /**
     * 当选择某行时，更新其相关信息
     * @param selectionEvent
     */
    public void onSelect(SelectionEvent selectionEvent) {

        try {
            JUCtrlHierNodeBinding wrappedRow =
                (JUCtrlHierNodeBinding)table.getSelectedRowData();
            Row rw = wrappedRow.getRow();
            Number ContractId = (Number)rw.getAttribute("ContractId");
            Number LineId = (Number)rw.getAttribute("LineId");
            Number PaymentId = (Number)rw.getAttribute("PaymentId");

            OperationBinding setContractOp =
                ADFUtils.findOperation("setCurrentContract");
            setContractOp.getParamsMap().put("id", ContractId);
            setContractOp.execute();

            OperationBinding setLineOp =
                ADFUtils.findOperation("setCurrentLine");
            setLineOp.getParamsMap().put("id", LineId);
            setLineOp.execute();

            OperationBinding setPaymentOp =
                ADFUtils.findOperation("setCurrentPayment");
            setPaymentOp.getParamsMap().put("id", PaymentId);
            setPaymentOp.execute();

            applyBtn.setDisabled(false);

            ADFUtils.partialRefreshComponenet(this.contractForm);
            ADFUtils.partialRefreshComponenet(this.lineForm);
            ADFUtils.partialRefreshComponenet(this.paymentForm);
            ADFUtils.partialRefreshComponenet(this.applyBtn);

        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage("查询相关合同信息发生错误！");
            e.printStackTrace();
        }

    }


    /**
     * 根据用户在页面上的选择，创建发票申请
     * @return
     */
    public String applyForInvoice() {
        //得到选择的行的keyset
        RowKeySet rks = table.getSelectedRowKeys();
        //get iterator
        Iterator selectedRowsIterator = rks.iterator();
        //initialize hashmap to hold params for appmodule methods.
        HashMap payments = new HashMap();

        if (selectedRowsIterator.hasNext()) {

            //loop over ketsets
            while (selectedRowsIterator.hasNext()) {
                //for each key, get the row attributes
                Object rowKey = selectedRowsIterator.next();
                table.setRowKey(rowKey);
                JUCtrlHierNodeBinding wrappedRow =
                    (JUCtrlHierNodeBinding)table.getRowData();
                Row rw = wrappedRow.getRow();
                Number paymentid = (Number)rw.getAttribute("PaymentId");
                HashMap lineParam = new HashMap();
                Number LineId = (Number)rw.getAttribute("LineId");
                Number amount = (Number)rw.getAttribute("PaymentPlanAmount");
                Number contractId = (Number)rw.getAttribute("ContractId");
                String dept = (String)rw.getAttribute("DesignDept");
                lineParam.put("amount", amount);
                lineParam.put("contractId", contractId);
                lineParam.put("dept", dept);
                lineParam.put("lineId", LineId);
                //put the value into hashmap
                payments.put(paymentid, lineParam);
            }

            //expose the hashmap onto managed bean property
            paymentsMap = payments;

            OperationBinding binding =
                ADFUtils.findOperation("createInvoiceRequisition");
            binding.execute();

            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            popup.show(hints);
        } else {
            JSFUtils.addFacesInformationMessage("请先选择收款节点！");
        }

        return null;
    }


    /**
     * 重新执行查询
     */
    private void reexecuteView() {
        //得到iterator binding的引用
        DCIteratorBinding binding =
            ADFUtils.findIterator("InvoiceNotificaitonViewIterator");
        //得到bind的viewobject,并为view object的bind variable赋值
        InvoiceNotificaitonViewImpl vo =
            (InvoiceNotificaitonViewImpl)binding.getViewObject();

        if (low != null)
            vo.setlowDayLimit(low);

        if (high != null)
            vo.sethighDayLimit(high);

        vo.executeQuery();

        AdfFacesContext.getCurrentInstance().addPartialTarget(table);

    }


    public void below30(ActionEvent actionEvent) {
        this.setHigh(new Number(30));
        this.setLow(new Number(-9999999));
        //执行query
        reexecuteView();
    }

    public void btw31and60(ActionEvent actionEvent) {
        this.setHigh(new Number(60));
        this.setLow(new Number(31));
        reexecuteView();
    }

    public void btw61and90(ActionEvent actionEvent) {
        this.setHigh(new Number(90));
        this.setLow(new Number(61));
        reexecuteView();
    }

    public void all(ActionEvent actionEvent) {
        this.setHigh(new Number(9999999));
        this.setLow(new Number(-9999999));
        reexecuteView();
    }


    public void abv91(ActionEvent actionEvent) {
        this.setHigh(new Number(9999999));
        this.setLow(new Number(91));
        reexecuteView();
    }

    public void setQuery(RichQuery query) {
        this.query = query;
    }

    public RichQuery getQuery() {
        return query;
    }

    public void setTable(RichTable table) {
        this.table = table;
    }

    public RichTable getTable() {
        return table;
    }


    public void setPaymentsMap(HashMap paymentsMap) {
        this.paymentsMap = paymentsMap;
    }

    public HashMap getPaymentsMap() {
        return paymentsMap;
    }

    public void setLow(Number low) {
        this.low = low;
    }

    public Number getLow() {
        return low;
    }

    public void setHigh(Number high) {
        this.high = high;
    }

    public Number getHigh() {
        return high;
    }

    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }

    public void setInvReqTable(RichTable invReqTable) {
        this.invReqTable = invReqTable;
    }

    public RichTable getInvReqTable() {
        return invReqTable;
    }

    public String cancelReq() {
        this.popup.hide();
        return null;
    }


    public void setSplitPopup(RichPopup splitPopup) {
        this.splitPopup = splitPopup;
    }

    public RichPopup getSplitPopup() {
        return splitPopup;
    }

    public void setPaymentAmount(Number paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Number getPaymentAmount() {
        return paymentAmount;
    }


    public void setSplitTable(RichTable splitTable) {
        this.splitTable = splitTable;
    }

    public RichTable getSplitTable() {
        return splitTable;
    }


    public void checkContact(ActionEvent actionEvent) {
        String contactId =
            (String)actionEvent.getComponent().getAttributes().get("contactId");
        JSFUtils.setExpressionValue("#{pageFlowScope.contactId}", contactId);
    }

    public void setNotePopup(RichPopup notePopup) {
        this.notePopup = notePopup;
    }

    public RichPopup getNotePopup() {
        return notePopup;
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

    public void setPaymentForm(RichPanelFormLayout paymentForm) {
        this.paymentForm = paymentForm;
    }

    public RichPanelFormLayout getPaymentForm() {
        return paymentForm;
    }

    public void setApplyBtn(RichCommandToolbarButton applyBtn) {
        this.applyBtn = applyBtn;
    }

    public RichCommandToolbarButton getApplyBtn() {
        return applyBtn;
    }

    public void confirmSubmit(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            this.submitInvReqs();
        }
    }

    public void setConfirmSplit(RichPopup confirmSplit) {
        this.confirmSplit = confirmSplit;
    }

    public RichPopup getConfirmSplit() {
        return confirmSplit;
    }


    public void setFirstPayAmt(Number firstPayAmt) {
        this.firstPayAmt = firstPayAmt;
    }

    public Number getFirstPayAmt() {
        return firstPayAmt;
    }

    public void setPaymentId(Number paymentId) {
        this.paymentId = paymentId;
    }

    public Number getPaymentId() {
        return paymentId;
    }


    public void setContractDetailPopup(RichPopup contractDetailPopup) {
        this.contractDetailPopup = contractDetailPopup;
    }

    public RichPopup getContractDetailPopup() {
        return contractDetailPopup;
    }
}

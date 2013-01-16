package com.xy.scpms.view.page;


import com.xy.scpms.model.utils.Codes;
import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import java.util.HashMap;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.share.security.SecurityContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;


public class BrowseBean implements Serializable {

    private RichCommandButton downloadButton;
    private RichPopup popup;
    private RichInputText approvalComments;
    private RichCommandToolbarButton popupPostionButton;
    private String terminateType = Codes.COL_VALUE_AIP_TYPE_TERMINATE;
    private String suspensionType = Codes.COL_VALUE_AIP_TYPE_SUSPEND;
    private String approvalType;
    private RichInputText memoInput;

    private RichPopup invReqPopup;
    private RichTable paymentNodesTable;
    private RichPopup memoPopup;
    private RichTable invReqTable;

    public void confirmInvReq(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            createInvReq();
        } else {
            ADFUtils.findOperation("Rollback").execute();
        }
    }
    
    public String cancelReq() {
        this.invReqPopup.hide();
        return null;
    }
    
    public void confirmSubmit(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            this.submitInvReqs();
        }
    }
    
    public String submitInvReqs() {
        //得到iterator binding的引用
      
        CollectionModel model = (CollectionModel)invReqTable.getValue();
        
        JUCtrlHierBinding _adfTableBinding =
            (JUCtrlHierBinding)model.getWrappedData();
        
        Row[] allRowsInRange = _adfTableBinding.getAllRowsInRange();
        for (Row row : allRowsInRange) {
            System.err.println(row.getAttribute("PaymentPlanDate"));
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

        this.invReqPopup.hide();
        ADFUtils.partialRefreshComponenet(paymentNodesTable);
        return null;
    }

    public void checkInvReqAvailability(ActionEvent actionEvent) {
        if (ADFUtils.getBoundAttributeValue("PaymentId") != null) {
            try {

                OperationBinding op = ADFUtils.findOperation("isAlreadyApplied");
                op.execute();
                Boolean isCreated = (Boolean)op.getResult();
                String contractStatus = (String)ADFUtils.getBoundAttributeValue("Status");
                String shipStatus = (String)ADFUtils.getBoundAttributeValue("ShipStatus");
                String paymentStatus = (String)ADFUtils.getBoundAttributeValue("PaymentStatus");
                if (isCreated) {
                    JSFUtils.addFacesInformationMessage("该节点已提交过申请，请勿重复提交！");
                } else if (!contractStatus.equals(Codes.COL_VALUE_STATUS_APPROVED)) {
                    JSFUtils.addFacesInformationMessage("该合同未审核通过或者正在审核中，不能提交发票申请！");
                } else if(!shipStatus.equals(Codes.COL_VALUE_STATUS_APPROVED)){
                    JSFUtils.addFacesInformationMessage("该船号未审核通过或者正在审核中，不能提交发票申请！");
                }else if(!paymentStatus.equals(Codes.COL_VALUE_PAYMENT_STATUS_INITIAL)){
                    JSFUtils.addFacesInformationMessage("该节点状态不正确，必须为待开票！");
                }else {
                    //        System.out.println("here");
                    Number amount = (Number)ADFUtils.getBoundAttributeValue("PaymentPlanAmount");
                    Number paymentId = (Number)ADFUtils.getBoundAttributeValue("PaymentId");
                    Number contractId = (Number)ADFUtils.getBoundAttributeValue("Id");
                    Number lineId = (Number)ADFUtils.getBoundAttributeValue("ContractLineId");
                    String dept = (String)ADFUtils.getBoundAttributeValue("DesignDept");
                    Date planPaymentDate = (Date)ADFUtils.getBoundAttributeValue("PaymentPlanDate");


                    HashMap payments = new HashMap();
                    HashMap lineParam = new HashMap();
                    lineParam.put("amount", amount);
                    lineParam.put("contractId", contractId);
                    lineParam.put("dept", dept);
                    lineParam.put("lineId", lineId);
                    lineParam.put("payPlanDate", planPaymentDate);
                    payments.put(paymentId, lineParam);

                    OperationBinding binding = ADFUtils.findOperation("quickCreateInvoiceRequisition");
                    binding.getParamsMap().put("payments", payments);
                    binding.execute();
                    
                    RichPopup.PopupHints hints = new RichPopup.PopupHints();
                    invReqPopup.show(hints);
                }


            } catch (Exception e) {
                JSFUtils.addFacesErrorMessage("申请开票时出错，错误为： " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            JSFUtils.addFacesInformationMessage("请先选择一个收款节点！");
        }

    }

    public String createInvReq() {
       

        OperationBinding commit = ADFUtils.findOperation("Commit");
        commit.execute();

        if (!commit.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("提交发票申请时出错, 请联系管理员 !");
        } else {
            JSFUtils.addFacesInformationMessage("已成功提交发票申请！");
            ADFUtils.partialRefreshComponenet(paymentNodesTable);
            
        }

        return null;
    }

    public String checkContact() {
        String contactId = (String)ADFUtils.getBoundAttributeValue("CustomerContactor");
        JSFUtils.setExpressionValue("#{pageFlowScope.contactId}", contactId);

        return "checkContact";
    }

    public String checkCustomer() {

        String customerId = (String)ADFUtils.getBoundAttributeValue("CustomerId");
        JSFUtils.setExpressionValue("#{pageFlowScope.customerId}", customerId);

        return "checkCustomer";
    }

    public String updateContract() {
        String status = (String)ADFUtils.getBoundAttributeValue("Status");
       // SecurityContext context = ADFContext.getCurrent().getSecurityContext();

     //   if (!context.isUserInRole("contractor")) {
            if (status.equals(Codes.COL_VALUE_STATUS_DRAFT) || status.equals(Codes.COL_VALUE_STATUS_APPROVED) ||
                status.equals(Codes.COL_VALUE_STATUS_PAID)) {
                Number total = (Number)ADFUtils.getBoundAttributeValue("TotalAmount");
                JSFUtils.setExpressionValue("#{pageFlowScope.totalAmount}", total);
                return "update";
            } else {
                JSFUtils.addFacesErrorMessage("该合同状态为:'" + status + "', 不能变更该合同信息 !");
                return null;
            }
       // }else{
       //     return "update";
       // }
    }

    public String getLineColor() {
        String status = JSFUtils.resolveExpressionAsString("#{row.Status}");
        if (status.equals(Codes.COL_VALUE_STATUS_PENDING_ABANDONED))
            return "background-color:#a52900;";
        else if (status.equals(Codes.COL_VALUE_STATUS_ABANDONED))
            return "background-color:#CC0000;";
        else
            return "";

    }

    public String goApproval() {
        String status = (String)ADFUtils.getBoundAttributeValue("Status");
                
        if (status.equals(Codes.COL_VALUE_STATUS_DRAFT) || status.equals(Codes.COL_VALUE_STATUS_APPROVED) ||
            status.equals(Codes.COL_VALUE_STATUS_PAID)) {

            OperationBinding approvalBinding = ADFUtils.findOperation("startApproval");
            approvalBinding.execute();
            if (!approvalBinding.getErrors().isEmpty()) {
                JSFUtils.addFacesErrorMessage("提交审批时出错, 请联系管理员 !");
            } else {
                JSFUtils.addFacesInformationMessage("已提交审核!");
            }

        } else {
            JSFUtils.addFacesErrorMessage("提交审批时出错, 该合同状态为:'" + status + "', 必须为'未提交'或'审核通过' !");
        }

        return null;
    }


    /**
     * 下载附件
     * @param facesContext
     * @param outputStream
     */
    public void download(FacesContext facesContext, OutputStream outputStream) {
        String filePath = (String)downloadButton.getAttributes().get("filePath");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        byte[] data = new byte[1];
        boolean isSuccessful = true;
        try {
            bis = new BufferedInputStream(new FileInputStream(filePath));
            bos = new BufferedOutputStream(outputStream);

            while (bis.read(data) != -1) {
                bos.write(data);
            }
            bos.flush();
        } catch (FileNotFoundException fnfe) {
            isSuccessful = false;
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            isSuccessful = false;
        } finally {
            try {
                if (bis != null && bos != null) {
                    bis.close();
                    bos.close();
                }
            } catch (IOException ioe) {
                isSuccessful = false;
            }
        }

        if (!isSuccessful) {

            JSFUtils.addFacesErrorMessage("下载失败！内部错误请联系系统管理员！");

        }
    }

    public void setDownloadButton(RichCommandButton downloadButton) {
        this.downloadButton = downloadButton;
    }

    public RichCommandButton getDownloadButton() {
        return downloadButton;
    }

    public String applyForTermination() {
        String status = (String)ADFUtils.getBoundAttributeValue("Status");
        if (status.equals(Codes.COL_VALUE_STATUS_APPROVED)) {
            approvalType = Codes.COL_VALUE_AIP_TYPE_TERMINATE;
            popupShow();
        } else {
            JSFUtils.addFacesErrorMessage("提交终止审批时出错, 该合同状态为:'" + status + "', 必须为'审核通过' !");
        }
        return null;
    }

    private void popupShow() {
        FacesContext context = JSFUtils.getFacesContext();
        String sourceId = popupPostionButton.getClientId(context);
        RichPopup.PopupHints hints = new RichPopup.PopupHints();

        popup.show(hints);
    }

    public String applyForSuspension() {
        String status = (String)ADFUtils.getBoundAttributeValue("Status");
        if (status.equals(Codes.COL_VALUE_STATUS_APPROVED)) {
            approvalType = Codes.COL_VALUE_AIP_TYPE_SUSPEND;
            popupShow();
        } else {
            JSFUtils.addFacesErrorMessage("提交暂停审批时出错, 该合同状态为:'" + status + "', 必须为'审核通过' !");
        }
        return null;
    }

    public String closePopup() {
        popup.cancel();
        return null;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setTerminateType(String terminateType) {
        this.terminateType = terminateType;
    }

    public String getTerminateType() {
        return terminateType;
    }

    public void setSuspensionType(String suspensionType) {
        this.suspensionType = suspensionType;
    }

    public String getSuspensionType() {
        return suspensionType;
    }


    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }

    public void setApprovalComments(RichInputText approvalComments) {
        this.approvalComments = approvalComments;
    }

    public RichInputText getApprovalComments() {
        return approvalComments;
    }


    public void setPopupPostionButton(RichCommandToolbarButton popupPostionButton) {
        this.popupPostionButton = popupPostionButton;
    }

    public RichCommandToolbarButton getPopupPostionButton() {
        return popupPostionButton;
    }


    public void setMemoInput(RichInputText memoInput) {
        this.memoInput = memoInput;
    }

    public RichInputText getMemoInput() {
        return memoInput;
    }

    public String submitForApproval() {
        // Add event code here...
        return null;
    }


    public void openContract(ClientEvent clientEvent) {
        // Add event code here...
    }


    public String startCustomerSelection() {
        // Add event code here...
        return null;
    }

    public void onSelectCustomerReturn(ReturnEvent returnEvent) {
        // Add event code here...
    }

    public String saveFourTech() {

        ADFUtils.commit();
        

        return null;
    }

    public void setInvReqPopup(RichPopup invReqPopup) {
        this.invReqPopup = invReqPopup;
    }

    public RichPopup getInvReqPopup() {
        return invReqPopup;
    }


    public void setPaymentNodesTable(RichTable paymentNodesTable) {
        this.paymentNodesTable = paymentNodesTable;
    }

    public RichTable getPaymentNodesTable() {
        return paymentNodesTable;
    }

    public String closeMemoPopup() {
        memoPopup.hide();
        return null;
    }

    public void setMemoPopup(RichPopup memoPopup) {
        this.memoPopup = memoPopup;
    }

    public RichPopup getMemoPopup() {
        return memoPopup;
    }

    public void save(ActionEvent actionEvent) {
        ADFUtils.commit();
    }

    public void setInvReqTable(RichTable invReqTable) {
        this.invReqTable = invReqTable;
    }

    public RichTable getInvReqTable() {
        return invReqTable;
    }
}

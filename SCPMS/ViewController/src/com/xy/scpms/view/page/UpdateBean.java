package com.xy.scpms.view.page;


import com.xy.scpms.model.utils.Codes;
import com.xy.scpms.view.Constants;
import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.LaunchPopupEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.ReturnEvent;


public class UpdateBean implements Serializable {

    private boolean disabled;
    private String contractType;
    private String customerType;
    private int abandonedNum = 0;
    private String approvalType = Codes.COL_VALUE_AIP_TYPE_UPDATE;
    //0 for contract, 1 for lines, 2 for payments, 3 for attachment
    private int removeType;
    private String customerTypePlant = Codes.AUTH_TYPE_PLANT;
    private String customerTypeBroker = Codes.AUTH_TYPE_BROKER;
    private String customerTypeOwner = Codes.AUTH_TYPE_OWNER;
    private RichPopup confrmPopup;
    private RichCommandToolbarButton positionButton;
    private RichTable lineTable;
    private RichInputText memoInput;
    private String createType = Codes.COL_VALUE_AIP_TYPE_UPDATE;
    private RichPopup approvalPopup;

    private RichInputText authInput;
    private RichInputListOfValues contactsLov;
    private RichInputText contactInput;
    private RichPopup memoPopup;
    private RichTable paymentTable;

    public void onLovLaunch(LaunchPopupEvent launchPopupEvent) {
        ADFUtils.onLovLaunch(launchPopupEvent, "customerContactNameId_afrLovPopupId", "soc8", "soc8",
                             "AdfRichPopup.ALIGN_BEFORE_END");
    }

    public void onPlantLovLaunch(LaunchPopupEvent launchPopupEvent) {
        ADFUtils.onLovLaunch(launchPopupEvent, "pc1:t1:shipPlantId_afrLovPopupId", "shipPlantId", "shipPlantId",
                             "AdfRichPopup.ALIGN_BEFORE_END");
    }

    public void onOwnerLovLaunch(LaunchPopupEvent launchPopupEvent) {
        ADFUtils.onLovLaunch(launchPopupEvent, "pc1:t1:shipOwnerId_afrLovPopupId", "shipOwnerId", "shipOwnerId",
                             "AdfRichPopup.ALIGN_BEFORE_END");
    }

    public void onBrokerLovLaunch(LaunchPopupEvent launchPopupEvent) {
        ADFUtils.onLovLaunch(launchPopupEvent, "pc1:t1:shipBrokerId_afrLovPopupId", "shipBrokerId", "shipBrokerId",
                             "AdfRichPopup.ALIGN_BEFORE_END");
    }

    public String startContactSelection() {

        String customerId = (String)ADFUtils.getBoundAttributeValue("CustomerId");
        if (customerId != null) {
            JSFUtils.setExpressionValue("#{pageFlowScope.customerIdlk}", customerId);
            return "checkContact";
        } else {
            JSFUtils.addFacesErrorMessage("请先选择一个委托方，然后才能选择委托方的联系人！");
            return null;
        }
    }

    public void onSelectContactReturn(ReturnEvent returnEvent) {

        String contactId = JSFUtils.resolveExpressionAsString("#{pageFlowScope.contactIdlk}");
        String contactName = JSFUtils.resolveExpressionAsString("#{pageFlowScope.contactNamelk}");

        ADFUtils.setBoundAttributeValue("CustomerContactor", contactId);
        ADFUtils.setBoundAttributeValue("CustomerContactName", contactName);

        AdfFacesContext.getCurrentInstance().addPartialTarget(contactInput);
        AdfFacesContext.getCurrentInstance().addPartialTarget(authInput);

    }

    public String startCustomerSelection() {
        String authType = null;

        authType = (String)ADFUtils.getBoundAttributeValue("AuthType");

        JSFUtils.setExpressionValue("#{pageFlowScope.searchCustomerType}", authType);

        return "checkCustomer";
    }

    public void onSelectCustomerReturn(ReturnEvent returnEvent) {
        String customerid = JSFUtils.resolveExpressionAsString("#{pageFlowScope.customerId}");
        String customerName = JSFUtils.resolveExpressionAsString("#{pageFlowScope.customerName}");
        ADFUtils.setBoundAttributeValue("AuthName", customerName);
        ADFUtils.setBoundAttributeValue("CustomerId", customerid);
        authInput.setValue(customerName);

        OperationBinding op1 = ADFUtils.findOperation("setContactNull");
        op1.execute();

        ADFUtils.findOperation("changeCustomer").execute();

        AdfFacesContext.getCurrentInstance().addPartialTarget(contactInput);
        AdfFacesContext.getCurrentInstance().addPartialTarget(authInput);
    }

    public String submitForApproval() {

        OperationBinding commitBinding = ADFUtils.findOperation("Commit");
        commitBinding.execute();
        if (!commitBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("保存合同时出错, 请联系管理员 !");
        } else {
            if (abandonedNum > 0) { //如果有弃船，则审批事件中附加弃船事件
                this.approvalType = approvalType + ";" + Codes.COL_VALUE_STATUS_PENDING_ABANDONED;
            }
            OperationBinding approvalBinding = ADFUtils.findOperation("startApproval");
            approvalBinding.execute();
            if (!commitBinding.getErrors().isEmpty()) {
                JSFUtils.addFacesErrorMessage("提交审批时出错, 请联系管理员 !");
            }

        }

        this.approvalPopup.hide();

        return "browse";
    }

    public String saveAll() {

        Number oldValue = (Number)ADFUtils.getBoundAttributeValue("TotalAmount");

        OperationBinding sumBinding = ADFUtils.findOperation("sumTotal");
        sumBinding.execute();
        if (!sumBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("保存合同时出错, 请联系管理员 !");
        }

        String status = (String)ADFUtils.getBoundAttributeValue("Status");
        
        //Do not allow update on contracts which are pending approval.
        if(status.equals(Codes.COL_VALUE_STATUS_PENDING_APPROVAL)){
            JSFUtils.addFacesInformationMessage("该合同审核中无法保存更改！");
            return null;
        }
        //        Number oldValue =
        //            (Number)JSFUtils.resolveExpression("#{pageFlowScope.totalAmount}");
        //        System.out.println("old value is: " + oldValue);
        Number total = (Number)ADFUtils.getBoundAttributeValue("TotalAmount");

        if ((!total.equals(oldValue)) &&
            (status.equals(Codes.COL_VALUE_STATUS_APPROVED) || status.equals(Codes.COL_VALUE_STATUS_PAID))) {
            JSFUtils.setExpressionValue("#{pageFlowScope.totalAmount}", total);
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.approvalPopup.show(hints);
        } else if (abandonedNum > 0) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.approvalPopup.show(hints);
        } else {
            OperationBinding commitBinding = ADFUtils.findOperation("Commit");
            commitBinding.execute();
            if (!commitBinding.getErrors().isEmpty()) {
                JSFUtils.addFacesErrorMessage("保存合同时出错, 请联系管理员 !");
            }
            JSFUtils.addFacesInformationMessage("合同已成功保存！");
        }

        return null;
    }

    public boolean isAgreement() {

        if (contractType == null) {
            contractType = (String)ADFUtils.getBoundAttributeValue("ContractType");
            if (contractType == null)
                return false;
        }

        return this.contractType.equals(Constants.FLOW_CREATION_TYPE_AGREEMENT);
    }

    public boolean isBroker() {
        if (customerType == null) {
            customerType = (String)ADFUtils.getBoundAttributeValue("AuthType");
            if (customerType == null)
                return false;

        }
        return this.customerType.equals(customerTypeBroker);
    }

    public boolean isOwner() {
        if (customerType == null) {
            customerType = (String)ADFUtils.getBoundAttributeValue("AuthType");
            if (customerType == null)
                return false;

        }
        return this.customerType.equals(customerTypeOwner);
    }

    public boolean isPlant() {
        if (customerType == null) {
            customerType = (String)ADFUtils.getBoundAttributeValue("AuthType");
            if (customerType == null)
                return false;
        }

        return this.customerType.equals(customerTypePlant);
    }

    public boolean isDisabled() {
        //如果是管理员、则可与修改任何数据
        Boolean isAdmin = JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['admin']}");
        if (isAdmin) {
            return false;
        }

        String status = (String)ADFUtils.getBoundAttributeValue("Status");
        if (status.equals(Codes.COL_VALUE_STATUS_DRAFT))
            disabled = false;
        else
            disabled = true;

        return disabled;
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

    /**
     * 弃船时判断其状态是否为‘审核通过’，如否则不执行弃船操作
     * 弃船操作讲该行记录状态置为’申请弃船‘
     * @return
     */
    public String abandonLine() {

        String status = (String)ADFUtils.getBoundAttributeValue("StatusOfLine");
        if (!status.equals(Codes.COL_VALUE_STATUS_APPROVED)) {
            JSFUtils.addFacesErrorMessage("弃船时出错, 该船号状态为:'" + status + "', 无法执行弃船 !");
        } else {
            abandonedNum++;
            //            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            //            this.approvalPopup.show(hints);
            ADFUtils.setBoundAttributeValue("StatusOfLine", Codes.COL_VALUE_STATUS_PENDING_ABANDONED);
            AdfFacesContext.getCurrentInstance().addPartialTarget(lineTable);
        }
        return null;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * 根据页面的不同选择，删除相应的记录
     * 如果是删除合同的话，则直接返回搜索页面。
     * @return
     */
    public String removeRecords() {

        OperationBinding binding = null;

        switch (removeType) {

        case 0:
            binding = ADFUtils.findOperation("DeleteContract");
            break;
        case 1:
            binding = ADFUtils.findOperation("DeleteLine");
            break;
        case 2:
            binding = ADFUtils.findOperation("DeletePayment");
            break;

        default:
            binding = ADFUtils.findOperation("DeleteAttachment");
            break;
        }

        binding.execute();
        //如果是删除整个合同，则直接commit;
        if (removeType == 0) {
            ADFUtils.findOperation("Commit").execute();
            return "search";
        }

        this.confrmPopup.cancel();
        return null;
    }

    /**
     * 标记当前操作为删除合同附件，并弹出对话框要求确认
     * @return
     */
    public String setRemoveAttachment() {
        this.removeType = 3;
        confrmPopupShow();
        return null;
    }

    /**
     *标记当前操作为删除合同，并弹出对话框要求确认
     * @return
     */
    public String setRemoveContract() {
        this.removeType = 0;
        confrmPopupShow();
        return null;
    }


    /**
     * 标记当前操作为删除收款节点，并弹出对话框要求确认
     * @return
     */
    public String setRemovePayment() {
        this.removeType = 2;
        confrmPopupShow();
        return null;
    }

    /**
     * 标记当前操作为删除船号，并弹出对话框要求确认
     * @return
     */
    public String setRemoveLine() {
        String status = (String)ADFUtils.getBoundAttributeValue("ShipStatus");
        if (status.equals(Codes.COL_VALUE_STATUS_DRAFT)) {
            this.removeType = 1;
            confrmPopupShow();
        } else {
            JSFUtils.addFacesInformationMessage("不能删除状态为'" + status + "'的船号，如有需要可以进行弃船操作！");
        }
        return null;
    }

    /**
     *  弹出确认对话框
     */
    private void confrmPopupShow() {
        FacesContext context = JSFUtils.getFacesContext();
        String sourceId = positionButton.getClientId(context);
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        //        hints.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID,
        //                  sourceId).add(RichPopup.PopupHints.HintTypes.HINT_LAUNCH_ID,
        //                                sourceId).add(RichPopup.PopupHints.HintTypes.HINT_ALIGN,
        //                                              RichPopup.PopupHints.AlignTypes.ALIGN_AFTER_END);
        confrmPopup.show(hints);
    }

    public void setConfrmPopup(RichPopup confrmPopup) {
        this.confrmPopup = confrmPopup;
    }

    public RichPopup getConfrmPopup() {
        return confrmPopup;
    }

    public void setPositionButton(RichCommandToolbarButton positionButton) {
        this.positionButton = positionButton;
    }

    public RichCommandToolbarButton getPositionButton() {
        return positionButton;
    }


    public String cancelDelete() {
        this.confrmPopup.hide();
        return null;
    }

    public void setLineTable(RichTable lineTable) {
        this.lineTable = lineTable;
    }

    public RichTable getLineTable() {
        return lineTable;
    }


    public String cancelApproval() {
        DCIteratorBinding it = ADFUtils.findIterator("ContractIterator");
        String currentRowKeyString = it.getCurrentRowKeyString();
        //        ADFUtils.findOperation("Rollback").execute();
        it.setCurrentRowWithKey(currentRowKeyString);
        this.approvalPopup.hide();
        return null;
    }

    public void setMemoInput(RichInputText memoInput) {
        this.memoInput = memoInput;
    }

    public RichInputText getMemoInput() {
        return memoInput;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

    public String getCreateType() {
        return createType;
    }

    public void setApprovalPopup(RichPopup approvalPopup) {
        this.approvalPopup = approvalPopup;
    }

    public RichPopup getApprovalPopup() {
        return approvalPopup;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public String undo() {
        this.abandonedNum = 0;
        DCIteratorBinding it = ADFUtils.findIterator("ContractIterator");
        String currentRowKeyString = it.getCurrentRowKeyString();
        ADFUtils.findOperation("Rollback").execute();
        it.setCurrentRowWithKey(currentRowKeyString);
        return null;
    }

    public void setAuthInput(RichInputText authInput) {
        this.authInput = authInput;
    }

    public RichInputText getAuthInput() {
        return authInput;
    }

    public void setContactsLov(RichInputListOfValues contactsLov) {
        this.contactsLov = contactsLov;
    }

    public RichInputListOfValues getContactsLov() {
        return contactsLov;
    }


    public void setContactInput(RichInputText contactInput) {
        this.contactInput = contactInput;
    }

    public RichInputText getContactInput() {
        return contactInput;
    }

    public String closeMemoPopup() {
        memoPopup.hide();
        //        ADFUtils.partialRefreshComponenet(paymentTable);
        return null;
    }

    public void setMemoPopup(RichPopup memoPopup) {
        this.memoPopup = memoPopup;
    }

    public RichPopup getMemoPopup() {
        return memoPopup;
    }

    public void setPaymentTable(RichTable paymentTable) {
        this.paymentTable = paymentTable;
    }

    public RichTable getPaymentTable() {
        return paymentTable;
    }
}

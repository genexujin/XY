package com.xy.scpms.view.page;


import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.input.RichInputListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.LaunchPopupEvent;

import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


public class HeaderBean implements Serializable {

    private RichInputListOfValues inputLOV;
    private FacesContext context;
    private RichCommandToolbarButton saveButton;

    private RichInputText authInput;
    private RichInputListOfValues contactsLov;
    private RichInputText contactInput;


    public HeaderBean() {
    }

    /**
     * 此处一定要保证数据库中的主数据顺序为1：船厂；2船东；3中间商
     * @return
     */
    public String startCustomerSelection() {

        Integer authType = null;

        authType = (Integer)ADFUtils.getBoundAttributeValue("AuthType");
        String type = null;
        if (authType == 1) {
            type = "船厂";
        } else if (authType == 2) {
            type = "船东";
        } else if (authType == 3)
            type = "中间商";
        if (authType == null) {
            //todo: popup warning messages...
        }
        JSFUtils.setExpressionValue("#{pageFlowScope.CreationFlowBean.searchCustomerType}", type);
        return "checkCustomer";
    }

    public String startContactSelection() {

        String customerId = (String)ADFUtils.getBoundAttributeValue("CustomerId");
        if (customerId != null) {
            JSFUtils.setExpressionValue("#{pageFlowScope.CreationFlowBean.customerId}", customerId);
            return "checkContact";
        } else {
            JSFUtils.addFacesErrorMessage("请先选择一个委托方，然后才能选择委托方的联系人！");
            return null;
        }
    }

    public void onSelectContactReturn(ReturnEvent returnEvent) {

        String contactId = JSFUtils.resolveExpressionAsString("#{pageFlowScope.CreationFlowBean.contactId}");
        String contactName = JSFUtils.resolveExpressionAsString("#{pageFlowScope.CreationFlowBean.contactName}");

        ADFUtils.setBoundAttributeValue("CustomerContactor", contactId);
        ADFUtils.setBoundAttributeValue("CustomerContactName", contactName);
        

        AdfFacesContext.getCurrentInstance().addPartialTarget(contactInput);
        AdfFacesContext.getCurrentInstance().addPartialTarget(authInput);

    }

    public void onSelectCustomerReturn(ReturnEvent returnEvent) {

        String customerid = JSFUtils.resolveExpressionAsString("#{pageFlowScope.CreationFlowBean.customerId}");
        String customerName = JSFUtils.resolveExpressionAsString("#{pageFlowScope.CreationFlowBean.customerName}");

        ADFUtils.setBoundAttributeValue("AuthName", customerName);
        ADFUtils.setBoundAttributeValue("CustomerId", customerid);

        authInput.setValue(customerName);
        OperationBinding op1 = ADFUtils.findOperation("setContactNull");
        op1.execute();
        ADFUtils.findOperation("changeCustomer").execute();

        AdfFacesContext.getCurrentInstance().addPartialTarget(contactInput);
        AdfFacesContext.getCurrentInstance().addPartialTarget(authInput);

    }

    /**
     * 使用这个方法 可以使popup的查询对话框显示在一个合适的位置
     * @param launchPopupEvent
     */
    public void onLOVLaunch(LaunchPopupEvent launchPopupEvent) {

        context = JSFUtils.getFacesContext();
        ExtendedRenderKitService erks = Service.getService(context.getRenderKit(), ExtendedRenderKitService.class);
        //create the JavaScript and invoke it on the client. The af:form id is "f1"
        StringBuffer scriptBuf = new StringBuffer();
        //        scriptBuf.append("var afForm = AdfPage.PAGE.findComponentByAbsoluteId(\"f1\");");
        //        scriptBuf.append("AdfCustomEvent.queue(afForm,\"inputLOVPostionSetter\",{},true);");
        scriptBuf.append("var popup = AdfPage.PAGE.findComponentByAbsoluteId(\"customerContactNameId_afrLovPopupId\");");
        //        scriptBuf.append("if (!popup.isPopupVisible()){");
        scriptBuf.append("var hints = {};");
        scriptBuf.append("hints[AdfRichPopup.HINT_LAUNCH_ID] = \"soc10\";");
        scriptBuf.append("hints[AdfRichPopup.HINT_ALIGN_ID] =  \"soc10\"; ");
        scriptBuf.append("hints[AdfRichPopup.HINT_ALIGN] = AdfRichPopup.ALIGN_BEFORE_END;");
        scriptBuf.append("popup.show(hints);");
        erks.addScript(context, scriptBuf.toString());

    }


    public void setInputLOV(RichInputListOfValues inputLOV) {
        this.inputLOV = inputLOV;
    }

    public RichInputListOfValues getInputLOV() {
        return inputLOV;
    }

    public void setSaveButton(RichCommandToolbarButton saveButton) {
        this.saveButton = saveButton;
    }

    public RichCommandToolbarButton getSaveButton() {
        return saveButton;
    }

    public String save() {
        OperationBinding sumBinding = ADFUtils.findOperation("sumTotal");
        sumBinding.execute();

        OperationBinding commitBinding = ADFUtils.findOperation("Commit");
        commitBinding.execute();

        if (!commitBinding.getErrors().isEmpty() || !sumBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("保存合同时出错, 请联系管理员 !");
        } else {
            JSFUtils.addFacesInformationMessage("合同已保存到数据库!");
        }
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

    public void onProjectMgrLovLaunch(LaunchPopupEvent launchPopupEvent) {
        ADFUtils.onLovLaunch(launchPopupEvent, "ilov1_afrLovPopupId", "ilov1", "ilov1",
                             "AdfRichPopup.ALIGN_BEFORE_END");
    }

    public void setContactInput(RichInputText contactInput) {
        this.contactInput = contactInput;
    }

    public RichInputText getContactInput() {
        return contactInput;
    }
}

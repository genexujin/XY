package com.xy.scpms.view.page;


import com.xy.scpms.model.utils.Codes;
import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.util.Map;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.SelectionEvent;


public class ApprovalBean {
    private RichPopup popup;
    private String approveType;
    private RichInputText memoInput;
    private RichCommandToolbarButton openBtn;
    private RichTable dashboardTable;

    public ApprovalBean() {
    }

    public String submit() {
        OperationBinding binding = ADFUtils.findOperation("aipActionExecute");
        binding.execute();
        if (!binding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("审批时出错, 请联系管理员 !");
        }else{
            JSFUtils.addFacesInformationMessage("审核操作已完成，返回任务列表页面！");
        }
        return "taskList";
    }
    

    public String cancel() {
        this.popup.cancel();
        return null;
    }



    public String setApproved() {
        
        this.approveType = Codes.COL_VALUE_AIP_OUTCOME_APPROVED;
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.popup.show(hints);
        return null;
    }

    public String setRejected() {
        this.approveType = Codes.COL_VALUE_AIP_OUTCOME_REJECTED;
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.popup.show(hints);
        return null;
    }

    public void setApproveType(String approveType) {
        
        this.approveType = approveType;
    }

    public String getApproveType() {
        return approveType;
    }
    
    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }

    public void setMemoInput(RichInputText memoInput) {
        this.memoInput = memoInput;
    }

    public RichInputText getMemoInput() {
        return memoInput;
    }

    public void onSelect(SelectionEvent selectionEvent) {

        ADFUtils.makeCurrent(selectionEvent);
        if (openBtn.isDisabled()) {
            this.openBtn.setDisabled(false);
            
            ADFUtils.partialRefreshComponenet(openBtn);
        }

    }

    public void setOpenBtn(RichCommandToolbarButton openBtn) {
        this.openBtn = openBtn;
    }

    public RichCommandToolbarButton getOpenBtn() {
        return openBtn;
    }

    public void openContractDBClick(ClientEvent clientEvent) {
        Number id = (Number)ADFUtils.getBoundAttributeValue("ContractId");
        OperationBinding op = ADFUtils.findOperation("findContractById");

        Map map = op.getParamsMap();
        map.put("id", id);
        op.execute();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,
                                                                              null,
                                                                              "open");
    }

    public void setDashboardTable(RichTable dashboardTable) {
        this.dashboardTable = dashboardTable;
    }

    public RichTable getDashboardTable() {
        return dashboardTable;
    }

    public void submitInDashboard(ActionEvent actionEvent) {
        OperationBinding binding = ADFUtils.findOperation("aipActionExecute");
        binding.execute();
        if (!binding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("审批时出错, 请联系管理员 !");
        }else{
            JSFUtils.addFacesInformationMessage("审核操作已完成！");
            ADFUtils.findIterator("ApprovalInProcessIterator").executeQuery();
        }
        ADFUtils.partialRefreshComponenet(dashboardTable);
        popup.hide();
    }
}

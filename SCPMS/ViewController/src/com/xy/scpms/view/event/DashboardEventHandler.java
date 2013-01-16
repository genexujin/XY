package com.xy.scpms.view.event;


import com.xy.view.utils.JSFUtils;

import javax.faces.context.FacesContext;


public class DashboardEventHandler {


    public void handleAIPEvent(oracle.jbo.domain.Number id) {
        JSFUtils.setExpressionValue("#{viewScope.contractId}", id);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "goTask");
        
    }
    
    public void handleInvReqEvent() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "goInvoice");
        
    }
    
    public void handlePayNotEvent() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "goPayNot");
        
    }
    
    public void handleApproveReqEvent() {
        JSFUtils.setExpressionValue("#{pageFlowScope.browse}", "no");
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "goApproveInv");
        
    }
    
    public void handleCrtAprvReqEvent() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "goTask");
        
    }
}

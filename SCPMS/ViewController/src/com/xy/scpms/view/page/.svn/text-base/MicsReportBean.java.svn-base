package com.xy.scpms.view.page;

import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import javax.faces.event.ValueChangeEvent;

import oracle.binding.OperationBinding;

public class MicsReportBean {

    public MicsReportBean() {
    }

    public void onTypeChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }

    public String runReport() {

        Boolean isPrjMgr =
            JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['project_manager,region_manager']}");
        Boolean isDesginDirector =
            JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['design_director']}");
        Boolean isVIP =
            JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['marketing_director,contractor,vip']}");
        if (isVIP) {
            //do nothing
        } else if (isDesginDirector) {
            String dept = JSFUtils.resolveExpressionAsString("#{sessionScope.userDept}");
            JSFUtils.setExpressionValue("#{pageFlowScope.dept}", dept);
            JSFUtils.setExpressionValue("#{pageFlowScope.user}", null);
        } else if (isPrjMgr) {
            String userName = JSFUtils.resolveExpressionAsString("#{securityContext.userName}");
            JSFUtils.setExpressionValue("#{pageFlowScope.user}", userName);
            JSFUtils.setExpressionValue("#{pageFlowScope.dept}", null);
        }

        OperationBinding binding = ADFUtils.findOperation("doQuery");
        binding.execute();

        return null;
    }
}

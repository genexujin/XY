package com.xy.scpms.view.backing;


import com.xy.scpms.view.Constants;
import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.io.Serializable;

import oracle.binding.OperationBinding;


public class MenuBean implements Serializable {

    private String createType;
    private String userDept;
    private String userRole;
    private String dept;
    private String role;
    private String browse;


    public MenuBean() {

    }

    public void setUserRole(String userRole) {


        this.userRole = userRole;
    }


    /**
     * 进入创建合同流程，传入参数为普通合同
     * @return
     */
    public String createFlow() {
        //    params = new HashMap();
        //    params.put(Constants.FLOW_CREATION_PARAM_CREATIONTYPE,
        //               Constants.FLOW_CREATION_TYPE_NORMAL);
        //    taskFlowId = "/WEB-INF/crt/create-flow.xml#create-flow";
        //      refreshRegion();
        this.createType = Constants.FLOW_CREATION_TYPE_NORMAL;
        return "goCreate";
    }

    /**
     * 进入创建合同流程，传入参数为复用合同
     * @return
     */
    public String createDuplicateFlow() {
        //    params = new HashMap();
        //    params.put(Constants.FLOW_CREATION_PARAM_CREATIONTYPE,
        //               Constants.FLOW_CREATION_TYPE_DUPLICATED);
        //    taskFlowId = "/WEB-INF/crt/create-flow.xml#create-flow";
        //      refreshRegion();
        this.createType = Constants.FLOW_CREATION_TYPE_DUPLICATED;
        return "goCreate";
    }

    /**
     * 进入创建合同流程，传入参数为协议合同
     * @return
     */
    public String createPaymentFlow() {
        //    params = new HashMap();
        //    params.put(Constants.FLOW_CREATION_PARAM_CREATIONTYPE,
        //               Constants.FLOW_CREATION_TYPE_AGREEMENT);
        //    taskFlowId = "/WEB-INF/crt/create-flow.xml#create-flow";
        //      refreshRegion();
        this.createType = Constants.FLOW_CREATION_TYPE_AGREEMENT;
        return "goCreate";
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

    public String getCreateType() {
        return createType;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }

    public String getUserDept() {
        return userDept;
    }


    public String getUserRole() {


        Object role = JSFUtils.resolveExpression("#{sessionScope.userRole}");


        if (role == null) {

            if ((Boolean)JSFUtils.resolveExpression("#{securityContext.userInRole['contractor']}")) {
                role = "合同管理员";
            } else if ((Boolean)JSFUtils.resolveExpression("#{securityContext.userInRole['design_director']}")) {
                role = "设计部主任";
            } else if ((Boolean)JSFUtils.resolveExpression("#{securityContext.userInRole['marketing_director']}")) {
                role = "市场部主任";
            } else if ((Boolean)JSFUtils.resolveExpression("#{securityContext.userInRole['project_manager']}")) {
                role = "项目经理";
            } else if ((Boolean)JSFUtils.resolveExpression("#{securityContext.userInRole['region_manager']}")) {
                role = "区域经理";
            } else {
                role = "院领导";
            }
            if (userRole == null)
                this.userRole = (String)role;
        } else {
            if (userRole == null)
                this.userRole = (String)role;
        }
        return userRole;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDept() {
        Object deptt = JSFUtils.resolveExpression("#{sessionScope.userDept}");
        try {
            if (deptt == null) {
                //            JSFUtils.resolveMethodExpression("#{data.com_xy_scpms_view_HomePageDef.getDeptById}", String.class, {String.class}, null);
                //得到iterator binding的引用
                OperationBinding method =
                    ADFUtils.findOperation("getDeptById");
//
//                String userName =
//                    JSFUtils.resolveExpressionAsString("#{securityContext.userName}");
//                method.getParamsMap().put("username", userName);
                method.execute();

                String deptName = (String)method.getResult();
                JSFUtils.setExpressionValue("#{sessionScope.userDept}",
                                            deptName);
                if (dept == null)
                    dept = deptName;

            } else {
                if (dept == null)
                    dept = (String)deptt;
            }
        } catch (Exception e) {
//            System.out.println(e.getMessage());
        }

        return dept;
    }

    public String goSearch() {

        getDept();
        // Add event code here...
        return "goSearch";
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {

        String role1;


        if ((JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['contractor']}")).booleanValue()) {
            role1 = "contractor";
        }else if ((JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['design_director']}")).booleanValue()) {
            role1 = "design_director";
        }  else if ((JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['marketing_director']}").booleanValue())) {
            role1 = "marketing_director";
        } else if ((JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['project_manager']}")).booleanValue()) {
            role1 = "project_manager";
        } else if ((JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['region_manager']}")).booleanValue()) {
            role1 = "region_manager";
        } else if ((JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['vip']}")).booleanValue()) {
            role1 = "vip";
        } else {
            role1 = null;
        }
        if (role == null)
            this.role = (String)role1;


        return role;
    }

    public String goApproveInvReq() {
        JSFUtils.setExpressionValue("#{pageFlowScope.browse}", "no");
        getDept();
        return "goApproveInv";
    }

    public String goBrowseReq() {
        JSFUtils.setExpressionValue("#{pageFlowScope.browse}", "yes");
        getDept();
        return "goApproveInv";
    }

    public void setBrowse(String browse) {
        this.browse = browse;
    }

    public String getBrowse() {
        return browse;
    }

    public String goInvBatch() {
        // Add event code here...
        return "goInvBatch";
    }

    public String goPayNot() {
        getDept();
        return "goPayNot";
    }
}

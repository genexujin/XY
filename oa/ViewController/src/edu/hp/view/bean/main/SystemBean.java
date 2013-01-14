package edu.hp.view.bean.main;

import edu.hp.view.bean.session.LoginUserMenuBean;
import edu.hp.view.security.LoginUser;
import edu.hp.view.security.LoginUserMenu;
import edu.hp.view.utils.utils.ADFUtils;

import edu.hp.view.utils.utils.JSFUtils;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import oracle.adf.controller.TaskFlowId;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.layout.RichPanelAccordion;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;

public class SystemBean extends UITabBean {

    private String taskFlowId = "/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf";
    private RichPanelAccordion menuPanelAccordion;


    public SystemBean() {
        initMenus();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        LoginUser user = (LoginUser)httpSession.getAttribute("LoginUserBean");
//        LoginUser user =(LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
        if (user != null) {
            System.err.println(user.getDisplayName());
            System.err.println(user.getUserName());
            System.err.println(user.getUserRoles().size());
        }
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setMenuPanelAccordion(RichPanelAccordion menuPanelAccordion) {
        this.menuPanelAccordion = menuPanelAccordion;
    }

    public RichPanelAccordion getMenuPanelAccordion() {
        return menuPanelAccordion;
    }

    public void setTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }

    public String getTaskFlowId() {
        return taskFlowId;
    }
}

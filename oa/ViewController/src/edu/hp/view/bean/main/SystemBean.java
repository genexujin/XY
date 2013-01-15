package edu.hp.view.bean.main;

import edu.hp.view.bean.session.LoginUserMenuBean;
import edu.hp.view.security.LoginUser;
import edu.hp.view.security.LoginUserMenu;
import edu.hp.view.utils.utils.ADFUtils;

import edu.hp.view.utils.utils.Constants;
import edu.hp.view.utils.utils.JSFUtils;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import oracle.adf.controller.TaskFlowId;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.layout.RichPanelAccordion;

import oracle.binding.OperationBinding;

import oracle.dms.http.HttpRequest;

import oracle.jbo.Row;

public class SystemBean extends UITabBean {

    private String taskFlowId = "/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf";
    private RichPanelAccordion menuPanelAccordion;


    public SystemBean() {
        initMenus();       
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

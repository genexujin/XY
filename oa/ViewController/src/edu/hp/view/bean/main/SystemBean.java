package edu.hp.view.bean.main;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.layout.RichPanelAccordion;


public class SystemBean extends UITabBean{

    private String taskFlowId = "/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf";
    private RichPanelAccordion menuPanelAccordion;
    private RichDocument document;


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

    public void setDocument(RichDocument document) {
        this.document = document;
    }

    public RichDocument getDocument() {
        return document;
    }
}

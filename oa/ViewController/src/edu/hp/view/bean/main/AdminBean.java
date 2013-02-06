package edu.hp.view.bean.main;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.layout.RichPanelAccordion;


public class AdminBean extends UITabBean{
    private String taskFlowId = "/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf";
    private RichDocument document;
    private RichPanelAccordion menuAccordion;

    public AdminBean() {
        initMenus();
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setDocument(RichDocument document) {
        this.document = document;
    }

    public RichDocument getDocument() {
        return document;
    }

    public void setMenuAccordion(RichPanelAccordion menuAccordion) {
        this.menuAccordion = menuAccordion;
    }

    public RichPanelAccordion getMenuAccordion() {
        return menuAccordion;
    }
}

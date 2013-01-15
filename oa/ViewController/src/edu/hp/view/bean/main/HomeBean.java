package edu.hp.view.bean.main;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.view.rich.component.rich.RichDocument;

public class HomeBean extends UITabBean{
    private String taskFlowId = "/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf";
    private RichDocument document;

    public HomeBean() {
        
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
}

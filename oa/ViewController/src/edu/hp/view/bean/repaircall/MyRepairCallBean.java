package edu.hp.view.bean.repaircall;

import edu.hp.view.bean.main.UITabBean;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.view.rich.component.rich.RichDocument;

public class MyRepairCallBean extends UITabBean {
    private String taskFlowId = "/WEB-INF/flows/repair-call/my-repair-call.xml#my_repair_call";

    private RichDocument document;
    
    public MyRepairCallBean() {
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

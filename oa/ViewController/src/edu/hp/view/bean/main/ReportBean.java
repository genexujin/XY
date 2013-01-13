package edu.hp.view.bean.main;

import oracle.adf.controller.TaskFlowId;

public class ReportBean extends UITabBean{
    private String taskFlowId = "/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf";

    public ReportBean() {
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }
}

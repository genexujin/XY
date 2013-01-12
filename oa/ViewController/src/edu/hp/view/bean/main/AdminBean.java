package edu.hp.view.bean.main;

import oracle.adf.controller.TaskFlowId;

public class AdminBean {
    private String taskFlowId = "/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf";

    public AdminBean() {
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }
}

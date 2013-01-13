package edu.hp.view.bean.main;

import oracle.adf.controller.TaskFlowId;

public class HomeBean extends UITabBean{
    private String taskFlowId = "/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf";

    public HomeBean() {
        
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }
}

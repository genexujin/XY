package edu.hp.view.bean.main;

import edu.hp.view.utils.JSFUtils;

import java.util.HashMap;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.layout.RichPanelAccordion;
import oracle.adf.view.rich.render.ClientEvent;


public class SystemBean extends UITabBean {

    private String taskFlowId = "/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf";
    private String CONTEXT_OBJECT_TYPE = "#{sessionScope.contextObjectType}";
    private String CONTEXT_OBJECT_ID = "#{sessionScope.contextObjectId}";

    private RichPanelAccordion menuPanelAccordion;
    private RichDocument document;
    private HashMap parameters;

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

    public void setParameters(HashMap parameters) {
        this.parameters = parameters;
    }

    public HashMap getParameters() {
        return parameters;
    }

    public void launchDefaultTab(ClientEvent clientEvent) {

        String contextObjectType = JSFUtils.resolveExpressionAsString(CONTEXT_OBJECT_TYPE);
        String contextObjectId = JSFUtils.resolveExpressionAsString(CONTEXT_OBJECT_ID);

        String taskFlowId = null;
        HashMap parameters;
        String title = null;
        if (contextObjectType != null && contextObjectId != null && !contextObjectType.equals("EXP") &&
            !contextObjectId.equals("EXP")) {

            JSFUtils.setExpressionValue(CONTEXT_OBJECT_TYPE, "EXP");
            JSFUtils.setExpressionValue(CONTEXT_OBJECT_ID, "EXP");

            if (contextObjectType.equals(edu.hp.model.common.Constants.CONTEXT_TYPE_VEHICLE)) {
                taskFlowId = edu.hp.model.common.Constants.CONTEXT_VEHICLE_TASKFLOW;
                title = "车辆申请";
                
            }
            parameters = new HashMap();
            parameters.put("id", contextObjectId);
            _launchActivity(title, taskFlowId, parameters, false);
        }

    }

   
}

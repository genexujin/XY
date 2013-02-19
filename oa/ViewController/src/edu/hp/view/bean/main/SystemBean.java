package edu.hp.view.bean.main;

import edu.hp.model.common.Constants;
import edu.hp.view.utils.JSFUtils;

import java.util.HashMap;

import javax.faces.component.UIComponent;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.layout.RichPanelAccordion;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.render.ClientEvent;

import org.apache.myfaces.trinidad.event.DisclosureEvent;


public class SystemBean extends UITabBean {

    private String taskFlowId = "/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf";
    private String CONTEXT_OBJECT_TYPE = "#{sessionScope.contextObjectType}";
    private String CONTEXT_OBJECT_ID = "#{sessionScope.contextObjectId}";
    private String CONTEXT_TITLE = "#{sessionScope.contextTitle}";

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
        String contextTitle = JSFUtils.resolveExpressionAsString(CONTEXT_TITLE);
        //System.err.println(contextObjectType);
        //System.err.println(contextObjectId);
        
        String taskFlowId = null;
        HashMap parameters;
        String title = null;
        if (contextObjectType != null && contextObjectId != null && !contextObjectType.equals("EXP") &&
            !contextObjectId.equals("EXP")) {            
            JSFUtils.setExpressionValue(CONTEXT_OBJECT_TYPE, "EXP");
            JSFUtils.setExpressionValue(CONTEXT_OBJECT_ID, "EXP");
            parameters = new HashMap();
            if (contextObjectType.equals(Constants.CONTEXT_TYPE_VEHICLE)) {
                taskFlowId = Constants.CONTEXT_VEHICLE_TASKFLOW;
                title = "车辆申请:"+contextTitle;
                parameters.put("id", contextObjectId);
            }else if (contextObjectType.equals(Constants.CONTEXT_TYPE_CONFRM)){
                taskFlowId = Constants.CONTEXT_CONFRM_TASKFLOW;
                title = "会议室申请:"+contextTitle;
                parameters.put("id", contextObjectId);
            }else if (contextObjectType.equals(Constants.CONTEXT_TYPE_HELPDESK)) {
                taskFlowId = Constants.CONTEXT_HELPDESK_TASKFLOW;
                title = "报修单处理:"+contextTitle;
                parameters.put("id", contextObjectId);
            }            
            
            _launchActivity(title, taskFlowId, parameters, true);
        }

    }


    public void onItemDisclosed(DisclosureEvent disclosureEvent) {

        if (disclosureEvent.isExpanded()) {
            //System.err.println("here");
            RichShowDetailItem component = (RichShowDetailItem)disclosureEvent.getComponent();
            for (UIComponent child : menuPanelAccordion.getChildren()) {
                RichShowDetailItem sdi = (RichShowDetailItem)child;
                if (!component.equals(child)) {
                    sdi.setDisclosed(false);
                } else {
                    String menuCode = (String)component.getAttributes().get("menuCode");
                    JSFUtils.setExpressionValue("#{pageFlowScope.openMenu}", menuCode);
                    //sdi.setDisclosed(true);
                }
            }
        }

    }
}

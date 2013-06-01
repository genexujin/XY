package edu.hp.view.bean;

import edu.hp.model.pojo.Notification;
import edu.hp.view.utils.ADFUtils;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.faces.event.ValueChangeEvent;

import oracle.binding.OperationBinding;

public class BaseBean {
    
    public void onAMPMChange(ValueChangeEvent valueChangeEvent, String startFieldName, String endFieldName, String pattern) throws Exception {
        String startTime = (String)ADFUtils.getBoundAttributeValue(startFieldName);
        String endTime = (String)ADFUtils.getBoundAttributeValue(endFieldName);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date startDate = format.parse(startTime);
        Date endDate = format.parse(endTime);
        if (valueChangeEvent.getNewValue().equals("AM")) {
            startDate.setHours(8);
            startDate.setMinutes(30);
            startDate.setSeconds(0);
            endDate.setHours(11);
            endDate.setMinutes(30);
            endDate.setSeconds(0);
        } else {
            startDate.setHours(13);
            startDate.setMinutes(30);
            startDate.setSeconds(0);
            endDate.setHours(16);
            endDate.setMinutes(30);
            endDate.setSeconds(0);
        }
       
        String startStr = format.format(startDate);
        String endStr = format.format(endDate);
        ADFUtils.setBoundAttributeValue(startFieldName, startStr);
        ADFUtils.setBoundAttributeValue(endFieldName, endStr);
    }

    protected void sendNotification(String title, String content, String userId, String roleName) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setContent(content);
        notification.setUserId(userId);
        notification.setRoleName(roleName);
        OperationBinding op = ADFUtils.findOperation("sendNotification");
        op.getParamsMap().put("notification", notification);
        op.execute();     
    }
    
    protected void createTask(String id, String contextType, String apprvTitle, String roleName, String contextTitle) {
        OperationBinding createTaskOp = ADFUtils.findOperation("createTask");
        createTaskOp.getParamsMap().put("title", apprvTitle);
        createTaskOp.getParamsMap().put("contextObjectType", contextType);
        createTaskOp.getParamsMap().put("contextObjectId", id);
        createTaskOp.getParamsMap().put("roleName", roleName);
        createTaskOp.getParamsMap().put("contextTitle", contextTitle);
        createTaskOp.execute();
    }
    
    protected String getDateString() {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm");
        String  dateStr = format.format(new Date());
        return dateStr;
    }
    
    protected void completeTask(String contextType, String id, String roleName) {
        OperationBinding binding = ADFUtils.findOperation("completeTask");
        binding.getParamsMap().put("contextObjectType",contextType);
        binding.getParamsMap().put("contextObjectId", id);
        binding.getParamsMap().put("roleName", roleName);
        binding.execute();
    }
    
    protected void createTaskForUser(String id, String contextType, String apprvTitle, String userId, String contextTitle) {
        OperationBinding createTaskOp = ADFUtils.findOperation("createTaskForUserId");
        createTaskOp.getParamsMap().put("title", apprvTitle);
        createTaskOp.getParamsMap().put("contextObjectType", contextType);
        createTaskOp.getParamsMap().put("contextObjectId", id);
        createTaskOp.getParamsMap().put("userId", userId);
        createTaskOp.getParamsMap().put("contextTitle", contextTitle);
        createTaskOp.execute();
    }
    
    protected void completeTaskForUser(String contextType, String id, String userId) {
        OperationBinding binding = ADFUtils.findOperation("completeTaskForUserId");
        binding.getParamsMap().put("contextObjectType",contextType);
        binding.getParamsMap().put("contextObjectId", id);
        binding.getParamsMap().put("userId", userId);
        binding.execute();
    }
    
    protected void cancelTask(String contextType, String id) {
        OperationBinding binding = ADFUtils.findOperation("cancelTask");
        binding.getParamsMap().put("contextObjectType",contextType);
        binding.getParamsMap().put("contextObjectId", id);        
        binding.execute();
    }

}

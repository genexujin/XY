package edu.hp.view.bean;

import edu.hp.model.pojo.Notification;
import edu.hp.view.utils.ADFUtils;

import java.util.Date;

import oracle.binding.OperationBinding;

public class BaseBean {

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
    
    protected void createTask(String id, String contextType, String apprvTitle, String roleName) {
        OperationBinding createTaskOp = ADFUtils.findOperation("createTask");
        createTaskOp.getParamsMap().put("title", apprvTitle);
        createTaskOp.getParamsMap().put("contextObjectType", contextType);
        createTaskOp.getParamsMap().put("contextObjectId", id);
        createTaskOp.getParamsMap().put("roleName", roleName);
        createTaskOp.execute();
    }
    
    protected String getDateString() {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
    
    protected void createTaskForUser(String id, String contextType, String apprvTitle, String userId) {
        OperationBinding createTaskOp = ADFUtils.findOperation("createTaskForUserId");
        createTaskOp.getParamsMap().put("title", apprvTitle);
        createTaskOp.getParamsMap().put("contextObjectType", contextType);
        createTaskOp.getParamsMap().put("contextObjectId", id);
        createTaskOp.getParamsMap().put("userId", userId);
        createTaskOp.execute();
    }
    
    protected void completeTaskForUser(String contextType, String id, String userId) {
        OperationBinding binding = ADFUtils.findOperation("completeTaskForUserId");
        binding.getParamsMap().put("contextObjectType",contextType);
        binding.getParamsMap().put("contextObjectId", id);
        binding.getParamsMap().put("userId", userId);
        binding.execute();
    }

}

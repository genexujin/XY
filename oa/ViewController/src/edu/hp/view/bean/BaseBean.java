package edu.hp.view.bean;

import edu.hp.model.pojo.Notification;
import edu.hp.view.utils.ADFUtils;

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

}

package edu.hp.model.pojo;

import java.io.Serializable;

public class Notification implements Serializable{
    private String title;
    private String content;
    private int priority;
    private String roleName;
    private String userId;
    private String contextType;
    private String contextObjectId;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

   

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextObjectId(String contextObjectId) {
        this.contextObjectId = contextObjectId;
    }

    public String getContextObjectId() {
        return contextObjectId;
    }
}

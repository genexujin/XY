package edu.hp.view.bean.test;

import edu.hp.view.utils.ADFUtils;

import oracle.adf.view.rich.event.ReturnPopupEvent;

public class LOVBean {
    public LOVBean() {
    }
    
    public String userName;

    public void onReturnValue(ReturnPopupEvent returnPopupEvent) {
        String attributeValue = (String)ADFUtils.getBoundAttributeValue("DisplayName");
        this.userName = attributeValue;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}

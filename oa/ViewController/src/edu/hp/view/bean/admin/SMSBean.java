package edu.hp.view.bean.admin;

import edu.hp.model.sms.SMSManager;

import javax.faces.event.ActionEvent;

public class SMSBean {
    public SMSBean() {
    }
    
    private String phoneNumber;
    private String testMsg;
    
    public boolean isSmsEnabled(){
        return SMSManager.isEnabled();
    }

    public String enable() {
        SMSManager.setEnabled(true);
        return null;
    }
    
    public double getBalance(){
        return SMSManager.checkBalance();
    }

    public String disable() {
        SMSManager.setEnabled(false);
        return null;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setTestMsg(String testMsg) {
        this.testMsg = testMsg;
    }

    public String getTestMsg() {
        return testMsg;
    }

    public void send(ActionEvent actionEvent) {
        if(this.phoneNumber!=null){
            SMSManager.sendSMS(new String[]{phoneNumber},testMsg, 1);
        }
    }
}

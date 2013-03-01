package edu.hp.view.bean.admin;

import edu.hp.model.sms.SMSManager;

public class SMSBean {
    public SMSBean() {
    }
    
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
}

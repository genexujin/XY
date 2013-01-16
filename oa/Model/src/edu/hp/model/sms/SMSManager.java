package edu.hp.model.sms;


public class SMSManager {


    //发送SMS
    public int sendSMS(String[] mobiles, String messageContent, int priority) {

        return SingletonSMSClient.getClient().sendSMS(mobiles, messageContent, priority);
    }
    
    public double checkBalance(){
        double result;
        
        try {
            result = SingletonSMSClient.getClient().getBalance();
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
        return result;
    }


}

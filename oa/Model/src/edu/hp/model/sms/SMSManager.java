package edu.hp.model.sms;


public class SMSManager {


    //发送SMS
    public static int sendSMS(String[] mobiles, String messageContent, int priority) {

        return SingletonSMSClient.getClient().sendSMS(mobiles, messageContent, priority);
    }
    
    public static double checkBalance(){
        double result=0;
        
        try {
            result = SingletonSMSClient.getClient().getBalance();
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
        return result;
    }


}

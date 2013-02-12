package edu.hp.model.sms;


public class SMSManager {

    private static boolean enabled = false;
    private static String SURFFIX = " 【黄埔教育】";

    //发送SMS
    public static int sendSMS(String[] mobiles, String messageContent, int priority) {
        if (enabled){
            messageContent += SURFFIX;
            return SingletonSMSClient.getClient().sendSMS(mobiles, messageContent, priority);
        }
        else
            return 0;
    }

    public static double checkBalance() {
        double result = 0;

        try {
            result = SingletonSMSClient.getClient().getBalance();
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

        return result;
    }


    public synchronized static void setEnabled(boolean enabled) {
        SMSManager.enabled = enabled;
    }

    public synchronized static boolean isEnabled() {
        return enabled;
    }
}

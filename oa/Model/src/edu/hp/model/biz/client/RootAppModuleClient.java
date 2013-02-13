package edu.hp.model.biz.client;

import edu.hp.model.biz.common.ClassRmModule;
import edu.hp.model.biz.common.HomeModule;
import edu.hp.model.biz.common.PurchaseOrderAppModule;
import edu.hp.model.biz.common.RootAppModule;

import edu.hp.model.pojo.Notification;

import oracle.jbo.ApplicationModule;
import oracle.jbo.client.remote.ApplicationModuleImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Feb 12 16:04:15 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class RootAppModuleClient extends ApplicationModuleImpl implements RootAppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public RootAppModuleClient() {
    }


    public ApplicationModule getAdminModule() {
        return (ApplicationModule)findApplicationModule("AdminModule");
    }

    public ApplicationModule getRepairCallsAppModule() {
        return (ApplicationModule)findApplicationModule("RepairCallsAppModule");
    }

    public ApplicationModule getNotificationModule() {
        return (ApplicationModule)findApplicationModule("NotificationModule1");
    }

    public ClassRmModule getClassRmModule() {
        return (ClassRmModule)findApplicationModule("ClassRmModule");
    }

    public ApplicationModule getHelpdeskCallsAppModule() {
        return (ApplicationModule)findApplicationModule("HelpdeskCallsAppModule1");
    }

    public ApplicationModule getConfRmModule() {
        return (ApplicationModule)findApplicationModule("ConfRmModule");
    }

    public ApplicationModule getVehicleModule() {
        return (ApplicationModule)findApplicationModule("VehicleModule");
    }

    public PurchaseOrderAppModule getPurchaseOrderAppModule() {
        return (PurchaseOrderAppModule)findApplicationModule("PurchaseOrderAppModule");
    }

    public HomeModule getHomeModule() {
        return (HomeModule)findApplicationModule("HomeModule");
    }


    public void completeTask(String contextObjectType, String contextObjectId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"completeTask",new String [] {"java.lang.String","java.lang.String"},new Object[] {contextObjectType, contextObjectId});
        return;
    }


    public void completeTask(String contextObjectType, String contextObjectId, String roleName) {
        Object _ret =
            this.riInvokeExportedMethod(this,"completeTask",new String [] {"java.lang.String","java.lang.String","java.lang.String"},new Object[] {contextObjectType, contextObjectId, roleName});
        return;
    }

    public void createTask(String title, String contextObjectType, String contextObjectId, String roleName) {
        Object _ret =
            this.riInvokeExportedMethod(this,"createTask",new String [] {"java.lang.String","java.lang.String","java.lang.String","java.lang.String"},new Object[] {title, contextObjectType, contextObjectId, roleName});
        return;
    }

    public void enableSMS(Boolean enalbed) {
        Object _ret = this.riInvokeExportedMethod(this,"enableSMS",new String [] {"java.lang.Boolean"},new Object[] {enalbed});
        return;
    }

    public void sendNotification(Notification notification) {
        Object _ret =
            this.riInvokeExportedMethod(this,"sendNotification",new String [] {"edu.hp.model.pojo.Notification"},new Object[] {notification});
        return;
    }
}

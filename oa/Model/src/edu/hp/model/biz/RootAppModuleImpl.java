package edu.hp.model.biz;

import edu.hp.model.biz.common.RootAppModule;
import edu.hp.model.pojo.Notification;
import edu.hp.model.sms.SMSManager;
import edu.hp.model.vo.EmployeesViewImpl;
import edu.hp.model.vo.NotificationsViewImpl;

import edu.hp.model.vo.NotificationsViewRowImpl;
import edu.hp.model.vo.RolesViewImpl;

import edu.hp.model.vo.query.note.ReceiverMobileListViewImpl;



import java.util.ArrayList;


import oracle.jbo.domain.Number;
import oracle.jbo.Row;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Feb 12 14:53:41 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class RootAppModuleImpl extends ApplicationModuleImpl implements RootAppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public RootAppModuleImpl() {
    }

    /**
     * 参数为true则启动sms,false则不启动SMS消息。
     * @param enalbed
     */
    public void enableSMS(Boolean enalbed) {
        SMSManager.setEnabled(enalbed);
    }

    /**
     * 注意该方法不包含commit，因此需要在方法外commit数据库变更
     * 通知可能有两类，一类是发送给一个角色的，另一类是发送给某个用户的。
     * 对于发送给某个人的则先查找他的电话号码然后发送消息
     * 如果是发给某个角色的，则先查找该角色的所有用户，然后发送消息给该角色的所有用户
     * @param notification
     */
    public void sendNotification(Notification notification) {

        if (notification != null && notification.getContent() != null) {
            //如果是发给某个role，则得到所有的用户
            if (notification.getRoleName() != null) {
                //get role id
                //System.err.println("sent role notifications");
                String role_name = notification.getRoleName();
                //System.err.println("role name: " + role_name);
                Row[] allRowsInRange = getRoleIdByName(role_name);
                if (allRowsInRange != null && allRowsInRange.length > 0) {
                    String roleId = ((DBSequence)allRowsInRange[0].getAttribute("RoleId")).toString();
                    //System.err.println("role id: " + roleId);
                    //save db notification
                    NotificationsViewImpl ntfVO = this.getNotifications();
                    //send sms
                    //get receiver list
                    ReceiverMobileListViewImpl receiverView = this.getReceiverMobileList();
                    receiverView.setroleId(roleId);
                    receiverView.setRangeSize(-1);
                    receiverView.executeQuery();
                    Row[] receivers = receiverView.getAllRowsInRange();
                    String[] phoneList = null;
                    if (receivers != null && receivers.length > 0) {
                        //ArrayList<String> mobileList = new ArrayList<String>();
                        phoneList = new String[receivers.length];
                        int i=0;
                        for (Row receiver : receivers) {
                            NotificationsViewRowImpl newRow = (NotificationsViewRowImpl)ntfVO.createRow();
                            //newRow.setCategory(category);
                            newRow.setContent(notification.getContent());
                            newRow.setEventDate(new Timestamp(System.currentTimeMillis()));
                            newRow.setIsSmsSent(SMSManager.isEnabled() ? "Y" : "N");
                            newRow.setPriority(new Number(notification.getPriority()));
                            newRow.setTitle(notification.getTitle());
                            String id = (String)receiver.getAttribute("Id");
                            newRow.setToUserId(id);
                            //INSERT IT INTO THE CURRENT RESULT SET
                            ntfVO.insertRow(newRow);
                            String mobile = (String)receiver.getAttribute("Mobile");
                            //System.err.println(mobile);
                            phoneList[i++] = mobile;
//                            if (mobile != null)
//                                mobileList.add(mobile);
                        }
                        //String[] phoneList = (String[])mobileList.toArray();
                        SMSManager.sendSMS(phoneList, notification.getTitle() +notification.getContent(), notification.getPriority());
                    }
                }
            }else if(notification.getUserId()!=null){
                //System.err.println("sent user notification");
                AdminModuleImpl adminModule = (AdminModuleImpl)this.getAdminModule();
                EmployeesViewImpl employees = (EmployeesViewImpl)adminModule.getEmployees();
                String mobile = employees.findUserMobile(notification.getUserId());
                NotificationsViewImpl ntfVO = this.getNotifications();
                NotificationsViewRowImpl newRow = (NotificationsViewRowImpl)ntfVO.createRow();
                //newRow.setCategory(category);
                newRow.setContent(notification.getContent());
                newRow.setEventDate(new Timestamp(System.currentTimeMillis()));
                newRow.setIsSmsSent(SMSManager.isEnabled() ? "Y" : "N");
                newRow.setPriority(new Number(notification.getPriority()));
                newRow.setTitle(notification.getTitle());                
                newRow.setToUserId(notification.getUserId());
                //INSERT IT INTO THE CURRENT RESULT SET
                ntfVO.insertRow(newRow);
                if(mobile!=null){
                    String[] phoneList = new String[]{mobile};
                    SMSManager.sendSMS(phoneList, notification.getTitle() + notification.getContent(), notification.getPriority());
                }
            }
        }
    }

    private Row[] getRoleIdByName(String role_name) {
        AdminModuleImpl adminModule = (AdminModuleImpl)this.getAdminModule();
        RolesViewImpl roles = adminModule.getRoles();
        roles.setApplyViewCriteriaNames(null);
        roles.setroleName(role_name);
        roles.applyViewCriteria(roles.getViewCriteria("queryByName"));
        roles.executeQuery();
        Row[] allRowsInRange = roles.getAllRowsInRange();
        return allRowsInRange;
    }

    /**
     * 注意：该方法不commit，需要在外部调用commit
     * @param title
     * @param contextObjectType
     * @param contextObjectId
     * @param roleName
     */
    public void createTask(String title, String contextObjectType, String contextObjectId, String roleName) {

        if (title != null && contextObjectType != null && contextObjectId != null && roleName != null) {
            Row[] allRowsInRange = getRoleIdByName(roleName);

            if (allRowsInRange != null && allRowsInRange.length > 0) {
                String roleId = ((DBSequence)allRowsInRange[0].getAttribute("RoleId")).toString();
                ViewObjectImpl taskVO = this.getTasks();
                Row newRow = taskVO.createRow();
                newRow.setAttribute("Title", title);
                newRow.setAttribute("AssignedDate", new Timestamp(System.currentTimeMillis()));
                newRow.setAttribute("ContextObjectType", contextObjectType);
                newRow.setAttribute("ContextObjectId", contextObjectId);
                newRow.setAttribute("AssigneeRoleId", roleId);
                taskVO.insertRow(newRow);
            }

        }

    }

    /**
     * Container's getter for Notifications.
     * @return Notifications
     */
    public NotificationsViewImpl getNotifications() {
        return (NotificationsViewImpl)findViewObject("Notifications");
    }

    /**
     * Container's getter for Tasks.
     * @return Tasks
     */
    public ViewObjectImpl getTasks() {
        return (ViewObjectImpl)findViewObject("Tasks");
    }

    /**
     * Container's getter for AdminModule.
     * @return AdminModule
     */
    public ApplicationModuleImpl getAdminModule() {
        return (ApplicationModuleImpl)findApplicationModule("AdminModule");
    }

    /**
     * Container's getter for RepairCallsAppModule.
     * @return RepairCallsAppModule
     */
    public ApplicationModuleImpl getRepairCallsAppModule() {
        return (ApplicationModuleImpl)findApplicationModule("RepairCallsAppModule");
    }

    /**
     * Container's getter for NotificationModule1.
     * @return NotificationModule1
     */
    public ApplicationModuleImpl getNotificationModule1() {
        return (ApplicationModuleImpl)findApplicationModule("NotificationModule1");
    }

    /**
     * Container's getter for ClassRmModule.
     * @return ClassRmModule
     */
    public ApplicationModuleImpl getClassRmModule() {
        return (ApplicationModuleImpl)findApplicationModule("ClassRmModule");
    }

    /**
     * Container's getter for HelpdeskCallsAppModule1.
     * @return HelpdeskCallsAppModule1
     */
    public ApplicationModuleImpl getHelpdeskCallsAppModule1() {
        return (ApplicationModuleImpl)findApplicationModule("HelpdeskCallsAppModule1");
    }

    /**
     * Container's getter for ConfRmModule.
     * @return ConfRmModule
     */
    public ApplicationModuleImpl getConfRmModule() {
        return (ApplicationModuleImpl)findApplicationModule("ConfRmModule");
    }

    /**
     * Container's getter for VehicleModule.
     * @return VehicleModule
     */
    public ApplicationModuleImpl getVehicleModule() {
        return (ApplicationModuleImpl)findApplicationModule("VehicleModule");
    }

    /**
     * Container's getter for PurchaseOrderAppModule.
     * @return PurchaseOrderAppModule
     */
    public ApplicationModuleImpl getPurchaseOrderAppModule() {
        return (ApplicationModuleImpl)findApplicationModule("PurchaseOrderAppModule");
    }

    /**
     * Container's getter for HomeModule.
     * @return HomeModule
     */
    public ApplicationModuleImpl getHomeModule() {
        return (ApplicationModuleImpl)findApplicationModule("HomeModule");
    }

    /**
     * Container's getter for ReceiverMobileListView1.
     * @return ReceiverMobileListView1
     */
    public ReceiverMobileListViewImpl getReceiverMobileList() {
        return (ReceiverMobileListViewImpl)findViewObject("ReceiverMobileList");
    }
}

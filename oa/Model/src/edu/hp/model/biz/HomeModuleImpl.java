package edu.hp.model.biz;

import edu.hp.model.biz.common.HomeModule;
import edu.hp.model.common.Constants;
import edu.hp.model.vo.NotificationsViewImpl;
import edu.hp.model.vo.query.home.UserNotificationsImpl;
import edu.hp.model.vo.query.home.UserTasksImpl;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewObjectImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Feb 07 13:47:46 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class HomeModuleImpl extends ApplicationModuleImpl implements HomeModule {
    /**
     * This is the default constructor (do not remove).
     */
    public HomeModuleImpl() {
    }
    
    public void initDashboard(String userName){
        
        UserTasksImpl userTask = (UserTasksImpl)this.getUserTasks();        
        userTask.setApplyViewCriteriaNames(null);
        userTask.applyViewCriteria(userTask.getViewCriteria("findByState"));
        userTask.setuserName(userName);
        userTask.setstate(Constants.STATE_TASK_PENDING);
        userTask.executeQuery();
        
        UserNotificationsImpl userNotifications = (UserNotificationsImpl)this.getUserNotifications();
        userNotifications.setApplyViewCriteriaNames(null);
//        userNotifications.applyViewCriteria(userNotifications.getViewCriteria("findByState"));
        System.err.println("User name: " + userName);
        userNotifications.setuserName(userName);
        userNotifications.setstate(Constants.STATE_NOTE_UNREAD);
        userNotifications.executeQuery();
    }

    /**
     * Container's getter for UserNotifications.
     * @return UserNotifications
     */
    public UserNotificationsImpl getUserNotifications() {
        return (UserNotificationsImpl)findViewObject("UserNotifications");
    }

    /**
     * Container's getter for UserTasks.
     * @return UserTasks
     */
    public UserTasksImpl getUserTasks() {
        return (UserTasksImpl)findViewObject("UserTasks");
    }

    /**
     * Container's getter for NotificationsView.
     * @return NotificationsView
     */
    public NotificationsViewImpl getNotificationsView() {
        return (NotificationsViewImpl)findViewObject("NotificationsView");
    }

    /**
     * Container's getter for TasksView.
     * @return TasksView
     */
    public ViewObjectImpl getTasksView() {
        return (ViewObjectImpl)findViewObject("TasksView");
    }
}

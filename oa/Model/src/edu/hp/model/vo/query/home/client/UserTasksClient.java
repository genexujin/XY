package edu.hp.model.vo.query.home.client;

import edu.hp.model.vo.query.home.common.UserTasks;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Feb 07 14:51:55 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class UserTasksClient extends ViewUsageImpl implements UserTasks {
    /**
     * This is the default constructor (do not remove).
     */
    public UserTasksClient() {
    }

    public void setForCompleted() {
        Object _ret = getApplicationModuleProxy().riInvokeExportedMethod(this,"setForCompleted",null,null);
        return;
    }

    public void setForPending() {
        Object _ret = getApplicationModuleProxy().riInvokeExportedMethod(this,"setForPending",null,null);
        return;
    }
}

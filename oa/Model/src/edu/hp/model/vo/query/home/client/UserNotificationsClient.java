package edu.hp.model.vo.query.home.client;

import edu.hp.model.vo.query.home.common.UserNotifications;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Feb 07 14:51:05 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class UserNotificationsClient extends ViewUsageImpl implements UserNotifications {
    /**
     * This is the default constructor (do not remove).
     */
    public UserNotificationsClient() {
    }

    public void setForRead() {
        Object _ret = getApplicationModuleProxy().riInvokeExportedMethod(this,"setForRead",null,null);
        return;
    }

    public void setForUnread() {
        Object _ret = getApplicationModuleProxy().riInvokeExportedMethod(this,"setForUnread",null,null);
        return;
    }
}

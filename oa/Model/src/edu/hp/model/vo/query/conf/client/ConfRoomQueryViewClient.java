package edu.hp.model.vo.query.conf.client;

import edu.hp.model.vo.query.conf.common.ConfRoomQueryView;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jan 27 18:59:28 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConfRoomQueryViewClient extends ViewUsageImpl implements ConfRoomQueryView {
    /**
     * This is the default constructor (do not remove).
     */
    public ConfRoomQueryViewClient() {
    }


    public void findByDateRange() {
        Object _ret = getApplicationModuleProxy().riInvokeExportedMethod(this,"findByDateRange",null,null);
        return;
    }

    public void findByUserId(boolean enabled, String userId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"findByUserId",new String [] {"boolean","java.lang.String"},new Object[] {new Boolean(enabled), userId});
        return;
    }

    public void refreshCalendar(String confRmNos) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"refreshCalendar",new String [] {"java.lang.String"},new Object[] {confRmNos});
        return;
    }
}

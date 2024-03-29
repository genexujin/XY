package edu.hp.model.vo.client;

import edu.hp.model.vo.common.VehicleSubTaskView;

import oracle.jbo.client.remote.ViewUsageImpl;
import oracle.jbo.domain.Timestamp;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jul 16 20:19:52 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class VehicleSubTaskViewClient extends ViewUsageImpl implements VehicleSubTaskView {
    /**
     * This is the default constructor (do not remove).
     */
    public VehicleSubTaskViewClient() {
    }

    public void deleteTargetRow(String masterId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"deleteTargetRow",new String [] {"java.lang.String"},new Object[] {masterId});
        return;
    }

    public void updateTargetRow(String masterId, Timestamp start, Timestamp end) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"updateTargetRow",new String [] {"java.lang.String","oracle.jbo.domain.Timestamp","oracle.jbo.domain.Timestamp"},new Object[] {masterId, start, end});
        return;
    }
}

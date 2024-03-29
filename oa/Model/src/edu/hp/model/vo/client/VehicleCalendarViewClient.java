package edu.hp.model.vo.client;

import edu.hp.model.vo.common.VehicleCalendarView;

import oracle.jbo.client.remote.ViewUsageImpl;
import oracle.jbo.domain.Timestamp;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jan 31 13:11:32 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class VehicleCalendarViewClient extends ViewUsageImpl implements VehicleCalendarView {
    /**
     * This is the default constructor (do not remove).
     */
    public VehicleCalendarViewClient() {
    }


    public void deleteByPK(String vehicleActId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"deleteByPK",new String [] {"java.lang.String"},new Object[] {vehicleActId});
        return;
    }

    public void findByDriver(String driverId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"findByDriver",new String [] {"java.lang.String"},new Object[] {driverId});
        return;
    }

    public void findByState(String state) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"findByState",new String [] {"java.lang.String"},new Object[] {state});
        return;
    }

    public void findByUserid(String userId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"findByUserid",new String [] {"java.lang.String"},new Object[] {userId});
        return;
    }

    public void newRow(String userDisplayName, String userId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"newRow",new String [] {"java.lang.String","java.lang.String"},new Object[] {userDisplayName, userId});
        return;
    }

    public void queryByPK(String confRmCalId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"queryByPK",new String [] {"java.lang.String"},new Object[] {confRmCalId});
        return;
    }

    public void updateActivityTime(String vehicleActId, Timestamp startTime, Timestamp endTime) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"updateActivityTime",new String [] {"java.lang.String","oracle.jbo.domain.Timestamp","oracle.jbo.domain.Timestamp"},new Object[] {vehicleActId, startTime, endTime});
        return;
    }

    public void updateEndTime(String vehicleActId, Timestamp endTime) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"updateEndTime",new String [] {"java.lang.String","oracle.jbo.domain.Timestamp"},new Object[] {vehicleActId, endTime});
        return;
    }
}

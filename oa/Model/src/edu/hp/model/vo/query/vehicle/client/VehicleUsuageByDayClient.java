package edu.hp.model.vo.query.vehicle.client;

import edu.hp.model.vo.query.vehicle.common.VehicleUsuageByDay;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Jun 01 13:35:08 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class VehicleUsuageByDayClient extends ViewUsageImpl implements VehicleUsuageByDay {
    /**
     * This is the default constructor (do not remove).
     */
    public VehicleUsuageByDayClient() {
    }

    public void doQuery(String day) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"doQuery",new String [] {"java.lang.String"},new Object[] {day});
        return;
    }

    public void setday(String value) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setday",new String [] {"java.lang.String"},new Object[] {value});
        return;
    }
}

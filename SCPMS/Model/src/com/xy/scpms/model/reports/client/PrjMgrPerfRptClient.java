package com.xy.scpms.model.reports.client;

import com.xy.scpms.model.reports.common.PrjMgrPerfRpt;

import oracle.jbo.client.remote.ViewUsageImpl;
import oracle.jbo.domain.Date;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Sep 12 16:24:54 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PrjMgrPerfRptClient extends ViewUsageImpl implements PrjMgrPerfRpt {
    /**
     * This is the default constructor (do not remove).
     */
    public PrjMgrPerfRptClient() {
    }

    public void doQuery(Date start, Date end, String dept) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"doQuery",new String [] {"oracle.jbo.domain.Date","oracle.jbo.domain.Date","java.lang.String"},new Object[] {start, end, dept});
        return;
    }

    public void clearRowSet() {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"clearRowSet",null,null);
        return;
    }

    public void doQuery(Date start, Date end, String dept, String mgrId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"doQuery",new String [] {"oracle.jbo.domain.Date","oracle.jbo.domain.Date","java.lang.String","java.lang.String"},new Object[] {start, end, dept, mgrId});
        return;
    }
}

package com.xy.scpms.model.vo.client;

import com.xy.scpms.model.vo.common.PaymentHistoryView;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Sep 28 09:56:01 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PaymentHistoryViewClient extends ViewUsageImpl implements PaymentHistoryView {
    /**
     * This is the default constructor (do not remove).
     */
    public PaymentHistoryViewClient() {
    }


    public void clearRowSet() {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"clearRowSet",null,null);
        return;
    }

    public String getdept() {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"getdept",null,null);
        return (String)_ret;
    }

    public oracle.jbo.domain.Number getendYr() {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"getendYr",null,null);
        return (oracle.jbo.domain.Number)_ret;
    }

    public oracle.jbo.domain.Number getstartYr() {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"getstartYr",null,null);
        return (oracle.jbo.domain.Number)_ret;
    }

    public void queryByYrDept(String dept, oracle.jbo.domain.Number start,
                              oracle.jbo.domain.Number end) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"queryByYrDept",new String [] {"java.lang.String","oracle.jbo.domain.Number","oracle.jbo.domain.Number"},new Object[] {dept, start, end});
        return;
    }
}

package com.xy.scpms.model.vo.client;

import com.xy.scpms.model.vo.common.InvoiceRequisitionView;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Nov 12 19:59:15 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class InvoiceRequisitionViewClient extends ViewUsageImpl implements InvoiceRequisitionView {
    /**
     * This is the default constructor (do not remove).
     */
    public InvoiceRequisitionViewClient() {
    }

    public String getdept() {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"getdept",null,null);
        return (String)_ret;
    }

    public oracle.jbo.domain.Number getpaymentId() {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"getpaymentId",null,null);
        return (oracle.jbo.domain.Number)_ret;
    }

    public String getuserName() {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"getuserName",null,null);
        return (String)_ret;
    }

    public boolean isAlreadyApplied(oracle.jbo.domain.Number paymentId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"isAlreadyApplied",new String [] {"oracle.jbo.domain.Number"},new Object[] {paymentId});
        return ((Boolean)_ret).booleanValue();
    }

    public void setReqApproved(oracle.jbo.domain.Number id) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setReqApproved",new String [] {"oracle.jbo.domain.Number"},new Object[] {id});
        return;
    }

    public void setReqRejected(oracle.jbo.domain.Number id) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setReqRejected",new String [] {"oracle.jbo.domain.Number"},new Object[] {id});
        return;
    }

    public void setdept(String value) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setdept",new String [] {"java.lang.String"},new Object[] {value});
        return;
    }

    public void setpaymentId(oracle.jbo.domain.Number value) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setpaymentId",new String [] {"oracle.jbo.domain.Number"},new Object[] {value});
        return;
    }

    public void setuserName(String value) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setuserName",new String [] {"java.lang.String"},new Object[] {value});
        return;
    }
}
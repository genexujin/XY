package com.xy.scpms.model.reports.client;

import com.xy.scpms.model.reports.common.InvoicedNotPaid;

import java.util.ArrayList;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Mar 27 09:57:42 CST 2012
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class InvoicedNotPaidClient extends ViewUsageImpl implements InvoicedNotPaid {
    /**
     * This is the default constructor (do not remove).
     */
    public InvoicedNotPaidClient() {
    }

    public void runQuery(ArrayList contractNos) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"runQuery",new String [] {"java.util.ArrayList"},new Object[] {contractNos});
        return;
    }
}
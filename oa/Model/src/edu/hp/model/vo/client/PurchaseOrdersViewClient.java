package edu.hp.model.vo.client;

import edu.hp.model.vo.common.PurchaseOrdersView;

import oracle.jbo.client.remote.ViewUsageImpl;
import oracle.jbo.domain.Date;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Feb 02 16:28:32 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PurchaseOrdersViewClient extends ViewUsageImpl implements PurchaseOrdersView {
    /**
     * This is the default constructor (do not remove).
     */
    public PurchaseOrdersViewClient() {
    }

    public void doQuery(String oRdId, String state, String category, Date submitDateFrom, Date submitDateTo) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"doQuery",new String [] {"java.lang.String","java.lang.String","java.lang.String","oracle.jbo.domain.Date","oracle.jbo.domain.Date"},new Object[] {oRdId, state, category, submitDateFrom, submitDateTo});
        return;
    }

    public void newRow() {
        Object _ret = getApplicationModuleProxy().riInvokeExportedMethod(this,"newRow",null,null);
        return;
    }
}

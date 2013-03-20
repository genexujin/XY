package edu.hp.model.vo.client;

import edu.hp.model.vo.common.PurchaseOrdersView;

import oracle.jbo.client.remote.ViewUsageImpl;
import oracle.jbo.domain.Date;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Mar 07 19:04:52 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PurchaseOrdersViewClient extends ViewUsageImpl implements PurchaseOrdersView {
    /**
     * This is the default constructor (do not remove).
     */
    public PurchaseOrdersViewClient() {
    }

    public void doQuery(String oRdId, String state, String category, Date submitDateFrom, Date submitDateTo,
                        String submitterId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"doQuery",new String [] {"java.lang.String","java.lang.String","java.lang.String","oracle.jbo.domain.Date","oracle.jbo.domain.Date","java.lang.String"},new Object[] {oRdId, state, category, submitDateFrom, submitDateTo, submitterId});
        return;
    }


    public void doQuery(String oRdId, String state, String category, Date submitDateFrom, Date submitDateTo,
                        String submitterId, String fromMenu) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"doQuery",new String [] {"java.lang.String","java.lang.String","java.lang.String","oracle.jbo.domain.Date","oracle.jbo.domain.Date","java.lang.String","java.lang.String"},new Object[] {oRdId, state, category, submitDateFrom, submitDateTo, submitterId, fromMenu});
        return;
    }


    public void doQuery(String oRdId, String state, String category, Date submitDateFrom, Date submitDateTo,
                        String submitterId, String fromMenu, String isFinalApprover) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"doQuery",new String [] {"java.lang.String","java.lang.String","java.lang.String","oracle.jbo.domain.Date","oracle.jbo.domain.Date","java.lang.String","java.lang.String","java.lang.String"},new Object[] {oRdId, state, category, submitDateFrom, submitDateTo, submitterId, fromMenu, isFinalApprover});
        return;
    }

    public void findByState(String state) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"findByState",new String [] {"java.lang.String"},new Object[] {state});
        return;
    }

    public void findBySubmitterId(String submitterId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"findBySubmitterId",new String [] {"java.lang.String"},new Object[] {submitterId});
        return;
    }

    public void newRow() {
        Object _ret = getApplicationModuleProxy().riInvokeExportedMethod(this,"newRow",null,null);
        return;
    }

    public void newRow(String userId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"newRow",new String [] {"java.lang.String"},new Object[] {userId});
        return;
    }
}

package com.xy.scpms.model.query.search.client;

import com.xy.scpms.model.query.search.common.SearchContractByMgrIdView;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jun 19 17:09:20 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SearchContractByMgrIdViewClient extends ViewUsageImpl implements SearchContractByMgrIdView {
    /**
     * This is the default constructor (do not remove).
     */
    public SearchContractByMgrIdViewClient() {
    }


    public void setMgrIdBind(oracle.jbo.domain.Number value) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setMgrIdBind",new String [] {"oracle.jbo.domain.Number"},new Object[] {value});
        return;
    }

    public void setMgrIdBind(String value) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setMgrIdBind",new String [] {"java.lang.String"},new Object[] {value});
        return;
    }
}

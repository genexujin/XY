package com.xy.scpms.model.vo.client;

import com.xy.scpms.model.vo.common.CustomerContactsView;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Aug 20 17:41:34 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CustomerContactsViewClient extends ViewUsageImpl implements CustomerContactsView {
    /**
     * This is the default constructor (do not remove).
     */
    public CustomerContactsViewClient() {
    }


    public String getcontactId() {
        Object _ret = getApplicationModuleProxy().riInvokeExportedMethod(this,"getcontactId",null,null);
        return (String)_ret;
    }

    public String getcustIdBind() {
        Object _ret = getApplicationModuleProxy().riInvokeExportedMethod(this,"getcustIdBind",null,null);
        return (String)_ret;
    }

    public void setQueryTypeByCustomerId(String customerId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setQueryTypeByCustomerId",new String [] {"java.lang.String"},new Object[] {customerId});
        return;
    }

    public void setQueryTypeById(String id) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setQueryTypeById",new String [] {"java.lang.String"},new Object[] {id});
        return;
    }

    public void setcontactId(String value) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setcontactId",new String [] {"java.lang.String"},new Object[] {value});
        return;
    }

    public void setcustIdBind(String value) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setcustIdBind",new String [] {"java.lang.String"},new Object[] {value});
        return;
    }
}

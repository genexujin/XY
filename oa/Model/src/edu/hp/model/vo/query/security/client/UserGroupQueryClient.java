package edu.hp.model.vo.query.security.client;

import edu.hp.model.vo.query.security.common.UserGroupQuery;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jul 16 11:38:59 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class UserGroupQueryClient extends ViewUsageImpl implements UserGroupQuery {
    /**
     * This is the default constructor (do not remove).
     */
    public UserGroupQueryClient() {
    }

    public void query(String grpId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"query",new String [] {"java.lang.String"},new Object[] {grpId});
        return;
    }
}
package edu.hp.model.vo.client;

import edu.hp.model.vo.common.DepartmentsView;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jul 11 13:26:58 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DepartmentsViewClient extends ViewUsageImpl implements DepartmentsView {
    /**
     * This is the default constructor (do not remove).
     */
    public DepartmentsViewClient() {
    }

    public void findByName(String Name) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"findByName",new String [] {"java.lang.String"},new Object[] {Name});
        return;
    }
}

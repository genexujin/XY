package edu.hp.model.biz.client;

import edu.hp.model.biz.common.ConfRmModule;

import oracle.jbo.client.remote.ApplicationModuleImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jun 02 11:22:55 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConfRmModuleClient extends ApplicationModuleImpl implements ConfRmModule {
    /**
     * This is the default constructor (do not remove).
     */
    public ConfRmModuleClient() {
    }

    public void deleteCurrentBatchOrder() {
        Object _ret = this.riInvokeExportedMethod(this,"deleteCurrentBatchOrder",null,null);
        return;
    }

    public boolean saveBatchOrders() {
        Object _ret = this.riInvokeExportedMethod(this,"saveBatchOrders",null,null);
        return ((Boolean)_ret).booleanValue();
    }
}

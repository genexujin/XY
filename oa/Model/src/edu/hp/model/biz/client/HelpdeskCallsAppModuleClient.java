package edu.hp.model.biz.client;

import edu.hp.model.biz.common.HelpdeskCallsAppModule;

import oracle.jbo.client.remote.ApplicationModuleImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Feb 15 15:14:21 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class HelpdeskCallsAppModuleClient extends ApplicationModuleImpl implements HelpdeskCallsAppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public HelpdeskCallsAppModuleClient() {
    }


    public void findByCallId(String id) {
        Object _ret = this.riInvokeExportedMethod(this,"findByCallId",new String [] {"java.lang.String"},new Object[] {id});
        return;
    }

    public void findByCallerId(String callerId) {
        Object _ret = this.riInvokeExportedMethod(this,"findByCallerId",new String [] {"java.lang.String"},new Object[] {callerId});
        return;
    }

    public void findByState(String state) {
        Object _ret = this.riInvokeExportedMethod(this,"findByState",new String [] {"java.lang.String"},new Object[] {state});
        return;
    }
}
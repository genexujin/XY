package edu.hp.model.vo.query.helpdesk;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Feb 15 15:50:57 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class HdStateWithEmptyImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public HdStateWithEmptyImpl() {
    }

    /**
     * Returns the variable value for state.
     * @return variable value for state
     */
    public String getstate() {
        return (String)ensureVariableManager().getVariableValue("state");
    }

    /**
     * Sets <code>value</code> for variable state.
     * @param value value to bind as state
     */
    public void setstate(String value) {
        ensureVariableManager().setVariableValue("state", value);
    }
}

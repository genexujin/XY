package edu.hp.model.vo;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Mar 28 20:59:31 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DepartmentsViewImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public DepartmentsViewImpl() {
    }

    /**
     * Returns the variable value for DpId.
     * @return variable value for DpId
     */
    public String getDpId() {
        return (String)ensureVariableManager().getVariableValue("DpId");
    }

    /**
     * Sets <code>value</code> for variable DpId.
     * @param value value to bind as DpId
     */
    public void setDpId(String value) {
        ensureVariableManager().setVariableValue("DpId", value);
    }
}
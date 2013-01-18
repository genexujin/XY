package com.xy.scpms.model.query;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Sep 10 15:46:46 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PaymentNotificationImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public PaymentNotificationImpl() {
    }

    /**
     * Returns the bind variable value for highDayLimit.
     * @return bind variable value for highDayLimit
     */
    public Number gethighDayLimit() {
        return (Number)getNamedWhereClauseParam("highDayLimit");
    }

    /**
     * Sets <code>value</code> for bind variable highDayLimit.
     * @param value value to bind as highDayLimit
     */
    public void sethighDayLimit(Number value) {
        setNamedWhereClauseParam("highDayLimit", value);
    }

    /**
     * Returns the bind variable value for lowDayLimit.
     * @return bind variable value for lowDayLimit
     */
    public Number getlowDayLimit() {
        return (Number)getNamedWhereClauseParam("lowDayLimit");
    }

    /**
     * Sets <code>value</code> for bind variable lowDayLimit.
     * @param value value to bind as lowDayLimit
     */
    public void setlowDayLimit(Number value) {
        setNamedWhereClauseParam("lowDayLimit", value);
    }

    /**
     * Returns the variable value for mgrId.
     * @return variable value for mgrId
     */
    public String getmgrId() {
        return (String)ensureVariableManager().getVariableValue("mgrId");
    }

    /**
     * Sets <code>value</code> for variable mgrId.
     * @param value value to bind as mgrId
     */
    public void setmgrId(String value) {
        ensureVariableManager().setVariableValue("mgrId", value);
    }

    /**
     * Returns the variable value for dept.
     * @return variable value for dept
     */
    public String getdept() {
        return (String)ensureVariableManager().getVariableValue("dept");
    }

    /**
     * Sets <code>value</code> for variable dept.
     * @param value value to bind as dept
     */
    public void setdept(String value) {
        ensureVariableManager().setVariableValue("dept", value);
    }
}
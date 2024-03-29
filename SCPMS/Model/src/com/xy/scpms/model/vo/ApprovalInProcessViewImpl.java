package com.xy.scpms.model.vo;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jun 01 13:09:36 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ApprovalInProcessViewImpl extends ViewObjectImpl {
  /**
   * This is the default constructor (do not remove).
   */
  public ApprovalInProcessViewImpl() {
  }

    /**
     * Returns the bind variable value for approvalRoleBind.
     * @return bind variable value for approvalRoleBind
     */
    public String getapprovalRoleBind() {
        return (String)getNamedWhereClauseParam("approvalRoleBind");
    }

    /**
     * Sets <code>value</code> for bind variable approvalRoleBind.
     * @param value value to bind as approvalRoleBind
     */
    public void setapprovalRoleBind(String value) {
        setNamedWhereClauseParam("approvalRoleBind", value);
    }

    /**
     * Returns the variable value for contractId.
     * @return variable value for contractId
     */
    public Number getcontractId() {
        return (Number)ensureVariableManager().getVariableValue("contractId");
    }

    /**
     * Sets <code>value</code> for variable contractId.
     * @param value value to bind as contractId
     */
    public void setcontractId(Number value) {
        ensureVariableManager().setVariableValue("contractId", value);
    }
}

package edu.hp.model.vo;

import edu.hp.model.common.BaseView;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jan 17 19:57:27 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class RolesViewImpl extends BaseView {
    /**
     * This is the default constructor (do not remove).
     */
    public RolesViewImpl() {

    }
  
    
    public void queryById(String roleId){        
        queryByVC("queryById","roleId",roleId);
    }

    /**
     * Returns the variable value for roleId.
     * @return variable value for roleId
     */
    public String getroleId() {
        return (String)ensureVariableManager().getVariableValue("roleId");
    }

    /**
     * Sets <code>value</code> for variable roleId.
     * @param value value to bind as roleId
     */
    public void setroleId(String value) {
        ensureVariableManager().setVariableValue("roleId", value);
    }
}

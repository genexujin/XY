package edu.hp.model.vo;

import edu.hp.model.vo.common.DepartmentsView;

import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Mar 28 20:59:31 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DepartmentsViewImpl extends ViewObjectImpl implements DepartmentsView {
    /**
     * This is the default constructor (do not remove).
     */
    public DepartmentsViewImpl() {
    }
    
    public void queryAll(){
        
    }
    
    public void findByName(String name){
        ViewCriteria criteria = this.getViewCriteria("findByName");
        this.setApplyViewCriteriaNames(null);
        this.applyViewCriteria(criteria);
        this.setdeptName(name);
        this.executeQuery();
        this.setApplyViewCriteriaNames(null);
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

    /**
     * Returns the variable value for deptName.
     * @return variable value for deptName
     */
    public String getdeptName() {
        return (String)ensureVariableManager().getVariableValue("deptName");
    }

    /**
     * Sets <code>value</code> for variable deptName.
     * @param value value to bind as deptName
     */
    public void setdeptName(String value) {
        ensureVariableManager().setVariableValue("deptName", value);
    }
}

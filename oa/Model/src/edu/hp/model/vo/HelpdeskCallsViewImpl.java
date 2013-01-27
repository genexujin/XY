package edu.hp.model.vo;

import edu.hp.model.vo.common.HelpdeskCallsView;

import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Jan 26 21:24:56 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class HelpdeskCallsViewImpl extends ViewObjectImpl implements HelpdeskCallsView {
    /**
     * This is the default constructor (do not remove).
     */
    public HelpdeskCallsViewImpl() {
    }
    
    
    public void doQuery(String level){
        this.setApplyViewCriteriaNames(null);
        System.err.println(level);
        ViewCriteria criteria = this.getViewCriteria("HelpdeskCallsViewCriteria");
        this.applyViewCriteria(criteria);
        this.setRsnLv1(level);
        this.executeQuery();
    }

    /**
     * Returns the variable value for RsnLv1.
     * @return variable value for RsnLv1
     */
    public String getRsnLv1() {
        return (String)ensureVariableManager().getVariableValue("RsnLv1");
    }

    /**
     * Sets <code>value</code> for variable RsnLv1.
     * @param value value to bind as RsnLv1
     */
    public void setRsnLv1(String value) {
        ensureVariableManager().setVariableValue("RsnLv1", value);
    }
}

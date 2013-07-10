package edu.hp.model.vo;

import edu.hp.model.vo.common.DocumentTaskView;

import oracle.jbo.Variable;
import oracle.jbo.ViewCriteria;
import oracle.jbo.common.VariableImpl;
import oracle.jbo.domain.Date;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jul 09 20:52:11 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DocumentTaskViewImpl extends ViewObjectImpl implements DocumentTaskView {
    /**
     * This is the default constructor (do not remove).
     */
    public DocumentTaskViewImpl() {
    }

    public void executeEmptyQuery() {
        executeEmptyRowSet();
    }

    public void search(String taskName, String state, Date startDate, Date endDate, String dept) {
        //        System.err.println(taskName);
        //        System.err.println(state);
        //        System.err.println(startDate);
        //        System.err.println(endDate);
        System.err.println(dept);

        this.setApplyViewCriteriaNames(null);
        ViewCriteria criteria = this.getViewCriteria("mainSearch");
        this.setRangeSize(-1);
        this.applyViewCriteria(criteria);
        this.settaskName(taskName);
        this.setstate(state);
        this.setstartDate(startDate);
        this.setendDate(endDate);
        this.setdept(dept);
        this.executeQuery();
    }

    /**
     * Returns the variable value for startDate.
     * @return variable value for startDate
     */
    public Date getstartDate() {
        return (Date)ensureVariableManager().getVariableValue("startDate");
    }

    /**
     * Sets <code>value</code> for variable startDate.
     * @param value value to bind as startDate
     */
    public void setstartDate(Date value) {
        ensureVariableManager().setVariableValue("startDate", value);
    }

    /**
     * Returns the variable value for endDate.
     * @return variable value for endDate
     */
    public Date getendDate() {
        return (Date)ensureVariableManager().getVariableValue("endDate");
    }

    /**
     * Sets <code>value</code> for variable endDate.
     * @param value value to bind as endDate
     */
    public void setendDate(Date value) {
        ensureVariableManager().setVariableValue("endDate", value);
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

    /**
     * Returns the variable value for taskName.
     * @return variable value for taskName
     */
    public String gettaskName() {
        return (String)ensureVariableManager().getVariableValue("taskName");
    }

    /**
     * Sets <code>value</code> for variable taskName.
     * @param value value to bind as taskName
     */
    public void settaskName(String value) {
        ensureVariableManager().setVariableValue("taskName", value);
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

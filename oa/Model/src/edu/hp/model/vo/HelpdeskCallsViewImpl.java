package edu.hp.model.vo;

import edu.hp.model.vo.common.HelpdeskCallsView;

import java.util.ArrayList;
import java.util.List;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.Date;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Jan 26 21:24:56 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class HelpdeskCallsViewImpl extends ViewObjectImpl implements HelpdeskCallsView {
    private static final String STATE_UN_SUBMITTED = "1";
    private static final String STATE_SUBMITTED = "2";
    private static final String STATE_PROCESSED = "3";
    private static final String STATE_EVALUATED = "4";
    private static final String STATE_CANCELLED = "5";
    
    /**
     * This is the default constructor (do not remove).
     */
    public HelpdeskCallsViewImpl() {
    }
    
    
    public void doQuery(String rsnLv1, String state){
        this.setApplyViewCriteriaNames(null);
        
        System.err.println("In VO: ReasonLevel1 is: " + rsnLv1);
        ViewCriteria criteria = this.getViewCriteria("ReasonLevel1Criteria");
        this.applyViewCriteria(criteria);
        this.setRsnLv1(rsnLv1);
        
        System.err.println("In VO: State is: " + state);
        ViewCriteria criteria2 = this.getViewCriteria("StateCriteria");
        this.applyViewCriteria(criteria2);
        this.setStateV(state);
        
        this.executeQuery();
    }
    
    public void doQuery(String cReadableId, String rsnLv1, String rsnLv2, String rsnLv3, 
                        Date submitDateFrom, Date submitDateTo, String callerId,
                        String calleeId, String state, String callResult) {
        this.setApplyViewCriteriaNames(null);
        
        System.err.println("In VO: cReadableId is: " + cReadableId);
        System.err.println("In VO: state is: " + state);
        System.err.println("In VO: rsnLv1 is: " + rsnLv1);
        System.err.println("In VO: rsnLv2 is: " + rsnLv2);
        System.err.println("In VO: rsnLv3 is: " + rsnLv3);
        System.err.println("In VO: submitDateFrom is: " + submitDateFrom);
        System.err.println("In VO: submitDateTo is: " + submitDateTo);
        System.err.println("In VO: callerId is: " + callerId);
        System.err.println("In VO: calleeId is: " + calleeId);
        System.err.println("In VO: callResult is: " + callResult);
        
        List<String> vcNames = new ArrayList<String>();
        
        if (cReadableId != null) {
            this.setcRdId(cReadableId);
            ViewCriteria cRdIdCriteria = this.getViewCriteria("CallReadableIdCriteria");
            vcNames.add(cRdIdCriteria.getName());
        }
        
        if (rsnLv1 != null) {
            this.setRsnLv1(rsnLv1);
            ViewCriteria rsnLv1Criteria = this.getViewCriteria("ReasonLevel1Criteria");
            vcNames.add(rsnLv1Criteria.getName());
        }
        
        if (rsnLv2 != null) {
            this.setRsnLv2(rsnLv2);
            ViewCriteria rsnLv2Criteria = this.getViewCriteria("ReasonLevel2Criteria");
            vcNames.add(rsnLv2Criteria.getName());
        }
        
        if (rsnLv3 != null) {
            this.setRsnLv3(rsnLv3);
            ViewCriteria rsnLv3Criteria = this.getViewCriteria("ReasonLevel3Criteria");
            vcNames.add(rsnLv3Criteria.getName());
        }
        
        if (submitDateFrom != null) {
            this.setSubmitDateFm(submitDateFrom);
            ViewCriteria submitDateFromCriteria = this.getViewCriteria("SubmitDateFromCriteria");
            vcNames.add(submitDateFromCriteria.getName());
        }
        
        if (submitDateTo != null) {
            this.setSubmitDateTo(submitDateTo);
            ViewCriteria submitDateToCriteria = this.getViewCriteria("SubmitDateToCriteria");
            vcNames.add(submitDateToCriteria.getName());
        }
        
        if (callerId != null) {
            this.setcRdId(cReadableId);
            ViewCriteria cRdIdCriteria = this.getViewCriteria("CallReadableIdCriteria");
            vcNames.add(cRdIdCriteria.getName());
        }
    }
    
    public void newRow() {
        System.err.println("here");
        Row newRow = this.createRow();
        newRow.setAttribute("State", STATE_UN_SUBMITTED);
        this.insertRow(newRow);        
        this.setCurrentRow(newRow);
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

    /**
     * Returns the variable value for StateV.
     * @return variable value for StateV
     */
    public String getStateV() {
        return (String)ensureVariableManager().getVariableValue("StateV");
    }

    /**
     * Sets <code>value</code> for variable StateV.
     * @param value value to bind as StateV
     */
    public void setStateV(String value) {
        ensureVariableManager().setVariableValue("StateV", value);
    }

    /**
     * Returns the variable value for RsnLv2.
     * @return variable value for RsnLv2
     */
    public String getRsnLv2() {
        return (String)ensureVariableManager().getVariableValue("RsnLv2");
    }

    /**
     * Sets <code>value</code> for variable RsnLv2.
     * @param value value to bind as RsnLv2
     */
    public void setRsnLv2(String value) {
        ensureVariableManager().setVariableValue("RsnLv2", value);
    }

    /**
     * Returns the variable value for RsnLv3.
     * @return variable value for RsnLv3
     */
    public String getRsnLv3() {
        return (String)ensureVariableManager().getVariableValue("RsnLv3");
    }

    /**
     * Sets <code>value</code> for variable RsnLv3.
     * @param value value to bind as RsnLv3
     */
    public void setRsnLv3(String value) {
        ensureVariableManager().setVariableValue("RsnLv3", value);
    }

    /**
     * Returns the variable value for SubmitDateFm.
     * @return variable value for SubmitDateFm
     */
    public Date getSubmitDateFm() {
        return (Date)ensureVariableManager().getVariableValue("SubmitDateFm");
    }

    /**
     * Sets <code>value</code> for variable SubmitDateFm.
     * @param value value to bind as SubmitDateFm
     */
    public void setSubmitDateFm(Date value) {
        ensureVariableManager().setVariableValue("SubmitDateFm", value);
    }

    /**
     * Returns the variable value for SubmitDateTo.
     * @return variable value for SubmitDateTo
     */
    public Date getSubmitDateTo() {
        return (Date)ensureVariableManager().getVariableValue("SubmitDateTo");
    }

    /**
     * Sets <code>value</code> for variable SubmitDateTo.
     * @param value value to bind as SubmitDateTo
     */
    public void setSubmitDateTo(Date value) {
        ensureVariableManager().setVariableValue("SubmitDateTo", value);
    }

    /**
     * Returns the variable value for cRdId.
     * @return variable value for cRdId
     */
    public String getcRdId() {
        return (String)ensureVariableManager().getVariableValue("cRdId");
    }

    /**
     * Sets <code>value</code> for variable cRdId.
     * @param value value to bind as cRdId
     */
    public void setcRdId(String value) {
        ensureVariableManager().setVariableValue("cRdId", value);
    }

    /**
     * Returns the variable value for cResult.
     * @return variable value for cResult
     */
    public String getcResult() {
        return (String)ensureVariableManager().getVariableValue("cResult");
    }

    /**
     * Sets <code>value</code> for variable cResult.
     * @param value value to bind as cResult
     */
    public void setcResult(String value) {
        ensureVariableManager().setVariableValue("cResult", value);
    }
}

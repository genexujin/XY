package edu.hp.model.vo;

import edu.hp.model.vo.common.PurchaseOrdersView;

import java.util.ArrayList;
import java.util.List;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.Date;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Feb 02 16:05:26 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PurchaseOrdersViewImpl extends ViewObjectImpl implements PurchaseOrdersView {

    private static final String STATE_UN_SUBMITTED = "1";
    private static final String STATE_UN_VERIFIED = "2";
    private static final String STATE_UN_APPROVED = "3";
    private static final String STATE_REJECTED = "4";
    private static final String STATE_IN_EXEC = "5";
    private static final String STATE_FINISHED = "6";
    private static final String STATE_CANCELLED = "7";
    /**
     * This is the default constructor (do not remove).
     */
    public PurchaseOrdersViewImpl() {
    }
    
    public void doQuery(String oRdId, String state, String category, Date submitDateFrom, Date submitDateTo, String submitterId) {
        this.setApplyViewCriteriaNames(null);
        
        System.err.println("In VO: oRdId is: " + oRdId);
        System.err.println("In VO: state is: " + state);
        System.err.println("In VO: category is: " + category);
        System.err.println("In VO: submitDateFrom is: " + submitDateFrom);
        System.err.println("In VO: submitDateTo is: " + submitDateTo);
        System.err.println("In VO: submitterId is: " + submitterId);
        
        List<String> vcNames = new ArrayList<String>();
        
        if (oRdId != null) {
            this.setOdRdId(oRdId);
            ViewCriteria oRdIdCriteria = this.getViewCriteria("OrderReadableIdCriteria");
            vcNames.add(oRdIdCriteria.getName());
        }
                
        if (state != null && !"0".equals(state)) {
            this.setOrStateId(state);
            ViewCriteria oStateIdCriteria = this.getViewCriteria("OrderStateCriteria");
            vcNames.add(oStateIdCriteria.getName());
        }
        
        if (category != null && !" ".equals(category)) {
            this.setItCatgId(category);
            ViewCriteria itCatgCriteria = this.getViewCriteria("ItemCategoryCriteria");
            vcNames.add(itCatgCriteria.getName());
        }
        
        if (submitDateFrom != null) {
            this.setsubmitDateFm(submitDateFrom);
            ViewCriteria dFromCriteria = this.getViewCriteria("SubmitDateFromCriteria");
            vcNames.add(dFromCriteria.getName());
        }
        
        if (submitDateTo != null) {
            this.setsubmitDateTo(submitDateTo);
            ViewCriteria dToCriteria = this.getViewCriteria("SubmitDateToCriteria");
            vcNames.add(dToCriteria.getName());
        }
        
        if (submitterId != null && !"0".equals(submitterId)) {
            this.setsbmtId(submitterId);
            ViewCriteria sIdCriteria = this.getViewCriteria("SubmitterIdCriteria");
            vcNames.add(sIdCriteria.getName());
        }
        
        this.setApplyViewCriteriaNames(vcNames.toArray(new String[0]));
        this.executeQuery();
    }
       
    public void newRow() {
        System.err.println("here");
        Row newRow = this.createRow();
        newRow.setAttribute("State", STATE_UN_SUBMITTED);
        this.insertRow(newRow);        
        this.setCurrentRow(newRow);
    }
    
    public void newRow(String userId) {
        System.err.println("Creating new PO. SubmitterId is: " + userId);
        Row newRow = this.createRow();
        newRow.setAttribute("State", STATE_UN_SUBMITTED);
        newRow.setAttribute("SubmitterId", userId);
        this.insertRow(newRow);        
        this.setCurrentRow(newRow);
    }
    
    public void findBySubmitterId(String submitterId) {
        this.setApplyViewCriteriaNames(null);
        
        System.err.println("In VO: submitterId is: " + submitterId);
        if (submitterId != null) {
            this.setsbmtId(submitterId);
            ViewCriteria sIdCriteria = this.getViewCriteria("SubmitterIdCriteria");
            this.setApplyViewCriteriaName(sIdCriteria.getName());
            this.executeQuery();
        }
    }
    
    public void findByState(String state) {
        this.setApplyViewCriteriaNames(null);
        
        System.err.println("In VO: state is: " + state);
        
        if (state != null) {
            this.setOrStateId(state);
            ViewCriteria oStateIdCriteria = this.getViewCriteria("OrderStateCriteria");
            this.setApplyViewCriteriaName(oStateIdCriteria.getName());
            this.executeQuery();
        }
    }


    /**
     * Returns the variable value for OdRdId.
     * @return variable value for OdRdId
     */
    public String getOdRdId() {
        return (String)ensureVariableManager().getVariableValue("OdRdId");
    }

    /**
     * Sets <code>value</code> for variable OdRdId.
     * @param value value to bind as OdRdId
     */
    public void setOdRdId(String value) {
        ensureVariableManager().setVariableValue("OdRdId", value);
    }

    /**
     * Returns the variable value for OrStateId.
     * @return variable value for OrStateId
     */
    public String getOrStateId() {
        return (String)ensureVariableManager().getVariableValue("OrStateId");
    }

    /**
     * Sets <code>value</code> for variable OrStateId.
     * @param value value to bind as OrStateId
     */
    public void setOrStateId(String value) {
        ensureVariableManager().setVariableValue("OrStateId", value);
    }

    /**
     * Returns the variable value for ItCatgId.
     * @return variable value for ItCatgId
     */
    public String getItCatgId() {
        return (String)ensureVariableManager().getVariableValue("ItCatgId");
    }

    /**
     * Sets <code>value</code> for variable ItCatgId.
     * @param value value to bind as ItCatgId
     */
    public void setItCatgId(String value) {
        ensureVariableManager().setVariableValue("ItCatgId", value);
    }

    /**
     * Returns the variable value for submitDateFm.
     * @return variable value for submitDateFm
     */
    public Date getsubmitDateFm() {
        return (Date)ensureVariableManager().getVariableValue("submitDateFm");
    }

    /**
     * Sets <code>value</code> for variable submitDateFm.
     * @param value value to bind as submitDateFm
     */
    public void setsubmitDateFm(Date value) {
        ensureVariableManager().setVariableValue("submitDateFm", value);
    }

    /**
     * Returns the variable value for submitDateTo.
     * @return variable value for submitDateTo
     */
    public Date getsubmitDateTo() {
        return (Date)ensureVariableManager().getVariableValue("submitDateTo");
    }

    /**
     * Sets <code>value</code> for variable submitDateTo.
     * @param value value to bind as submitDateTo
     */
    public void setsubmitDateTo(Date value) {
        ensureVariableManager().setVariableValue("submitDateTo", value);
    }

    /**
     * Returns the variable value for sbmtId.
     * @return variable value for sbmtId
     */
    public String getsbmtId() {
        return (String)ensureVariableManager().getVariableValue("sbmtId");
    }

    /**
     * Sets <code>value</code> for variable sbmtId.
     * @param value value to bind as sbmtId
     */
    public void setsbmtId(String value) {
        ensureVariableManager().setVariableValue("sbmtId", value);
    }
}

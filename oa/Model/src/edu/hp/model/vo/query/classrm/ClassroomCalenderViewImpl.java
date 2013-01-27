package edu.hp.model.vo.query.classrm;

import edu.hp.model.vo.query.classrm.common.ClassroomCalenderView;

import oracle.jbo.domain.Date;
import oracle.jbo.server.ViewObjectImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jan 20 14:50:06 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ClassroomCalenderViewImpl extends ViewObjectImpl implements ClassroomCalenderView {
    /**
     * This is the default constructor (do not remove).
     */
    public ClassroomCalenderViewImpl() {
    }
    
    
    public void findByUserId(boolean enabled, String userId){

        if(enabled){
            this.setApplyViewCriteriaNames(null);
            this.applyViewCriteria(getViewCriteria("findByUserId"));
            this.setuserId(userId);
            this.executeQuery();
        }else{
            this.setApplyViewCriteriaNames(null);
            this.executeQuery();
        }
    }
    
    public void refreshCalendar(String clsRmNos){
            this.setclsRmNos(clsRmNos);
            this.executeQuery();
        }

    /**
     * Returns the bind variable value for clsRmNos.
     * @return bind variable value for clsRmNos
     */
    public String getclsRmNos() {
        return (String)getNamedWhereClauseParam("clsRmNos");
    }

    /**
     * Sets <code>value</code> for bind variable clsRmNos.
     * @param value value to bind as clsRmNos
     */
    public void setclsRmNos(String value) {
        setNamedWhereClauseParam("clsRmNos", value);
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
     * Returns the variable value for timeZone.
     * @return variable value for timeZone
     */
    public String gettimeZone() {
        return (String)ensureVariableManager().getVariableValue("timeZone");
    }

    /**
     * Sets <code>value</code> for variable timeZone.
     * @param value value to bind as timeZone
     */
    public void settimeZone(String value) {
        ensureVariableManager().setVariableValue("timeZone", value);
    }

    /**
     * Returns the variable value for userId.
     * @return variable value for userId
     */
    public String getuserId() {
        return (String)ensureVariableManager().getVariableValue("userId");
    }

    /**
     * Sets <code>value</code> for variable userId.
     * @param value value to bind as userId
     */
    public void setuserId(String value) {
        ensureVariableManager().setVariableValue("userId", value);
    }
}

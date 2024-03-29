package edu.hp.model.vo.query.conf;

import edu.hp.model.vo.query.conf.common.ConfRoomQueryView;

import java.util.ArrayList;
import java.util.List;

import oracle.jbo.domain.Date;
import oracle.jbo.server.ViewObjectImpl;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jan 27 18:57:35 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConfRoomQueryViewImpl extends ViewObjectImpl implements ConfRoomQueryView {
    /**
     * This is the default constructor (do not remove).
     */
    public ConfRoomQueryViewImpl() {
    }
    
    public void findByDateRange(){
        this.setApplyViewCriteriaNames(null);
        List<String> vcNames = new ArrayList<String>();
        vcNames.add("findByDateRange");
        if(getuserId()!=null){
            vcNames.add("findByUserId");
        }
        this.setApplyViewCriteriaNames(vcNames.toArray(new String[0]));
        //this.applyViewCriteria(getViewCriteria("findByDateRange"));        
        this.executeQuery();
    }
    

    public void findByUserId(boolean enabled, String userId) {

        if (enabled) {
            this.setApplyViewCriteriaNames(null);
            this.applyViewCriteria(getViewCriteria("findByUserId"));
            this.setuserId(userId);
            this.executeQuery();
        } else {
            this.setuserId(null);
            this.setApplyViewCriteriaNames(null);
            this.executeQuery();
        }
    }

    public void refreshCalendar(String confRmNos) {
        this.setconfRmNos(confRmNos);
        this.executeQuery();
    }

    /**
     * Returns the bind variable value for confRmNos.
     * @return bind variable value for confRmNos
     */
    public String getconfRmNos() {
        return (String)getNamedWhereClauseParam("confRmNos");
    }

    /**
     * Sets <code>value</code> for bind variable confRmNos.
     * @param value value to bind as confRmNos
     */
    public void setconfRmNos(String value) {
        setNamedWhereClauseParam("confRmNos", value);
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

    /**
     * Returns the bind variable value for initStat.
     * @return bind variable value for initStat
     */
    public String getinitStat() {
        return (String)getNamedWhereClauseParam("initStat");
    }

    /**
     * Sets <code>value</code> for bind variable initStat.
     * @param value value to bind as initStat
     */
    public void setinitStat(String value) {
        setNamedWhereClauseParam("initStat", value);
    }
}

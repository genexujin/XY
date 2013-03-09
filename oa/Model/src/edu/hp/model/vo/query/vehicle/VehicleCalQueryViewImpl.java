package edu.hp.model.vo.query.vehicle;

import edu.hp.model.vo.query.vehicle.common.VehicleCalQueryView;

import java.util.ArrayList;
import java.util.List;

import oracle.jbo.domain.Date;
import oracle.jbo.server.ViewObjectImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jan 31 12:08:33 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class VehicleCalQueryViewImpl extends ViewObjectImpl implements VehicleCalQueryView {
    /**
     * This is the default constructor (do not remove).
     */
    public VehicleCalQueryViewImpl() {
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
            this.setApplyViewCriteriaNames(null);
            this.executeQuery();
        }
    }

    public void refreshCalendar(String vehicleIds) {
//        System.err.println(vehicleIds);
        this.setvehicleIds(vehicleIds);
        this.executeQuery();
//        System.err.println("query executed!");
    }

    /**
     * Returns the bind variable value for VehicleIds.
     * @return bind variable value for VehicleIds
     */
    public String getvehicleIds() {
        return (String)getNamedWhereClauseParam("vehicleIds");
    }

    /**
     * Sets <code>value</code> for bind variable VehicleIds.
     * @param value value to bind as VehicleIds
     */
    public void setvehicleIds(String value) {
        setNamedWhereClauseParam("vehicleIds", value);
    }

    /**
     * Returns the bind variable value for initStat.
     * @return bind variable value for initStat
     */
    public String getinitStat() {
        return (String)getNamedWhereClauseParam("initStat");
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
}

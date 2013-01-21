package edu.hp.model.vo.query.classrm;

import edu.hp.model.vo.query.classrm.common.ClassroomCalendarConflitQuery;

import java.sql.Timestamp;

import java.util.Calendar;

import oracle.jbo.server.ViewObjectImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Jan 19 19:24:55 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ClassroomCalendarConflitQueryImpl extends ViewObjectImpl implements ClassroomCalendarConflitQuery {
    /**
     * This is the default constructor (do not remove).
     */
    public ClassroomCalendarConflitQueryImpl() {
    }
    
    public Boolean ifConflict(Timestamp actStartTime, Timestamp actEndTime, String clsRmId, String actId){
        
        try {
            
            if(actStartTime.equals(actEndTime)){
                
                if (actEndTime != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(actEndTime.getTime());
                    calendar.set(Calendar.HOUR_OF_DAY, 23);
                    calendar.set(Calendar.MINUTE, 59);
                    calendar.set(Calendar.SECOND, 59);
                    actEndTime = new Timestamp(calendar.getTimeInMillis());
                }      

                
            }
            
            ensureVariableManager().setVariableValue("endQueryTime", actEndTime);
            ensureVariableManager().setVariableValue("startQueryTime", actStartTime);
            ensureVariableManager().setVariableValue("clsRoomId", clsRmId);
//            System.err.println(actStartTime);
//            System.err.println(actEndTime);
//            System.err.println(clsRmId);
//            System.err.println(actId);
            ensureVariableManager().setVariableValue("actId", actId);
            setRangeSize(-1);
            executeQuery();
            if (first() !=null){
                return false;                
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Returns the bind variable value for startQueryTime.
     * @return bind variable value for startQueryTime
     */
    public Timestamp getstartQueryTime() {
        return (Timestamp)getNamedWhereClauseParam("startQueryTime");
    }

    /**
     * Sets <code>value</code> for bind variable startQueryTime.
     * @param value value to bind as startQueryTime
     */
    public void setstartQueryTime(Timestamp value) {
        setNamedWhereClauseParam("startQueryTime", value);
    }

    /**
     * Returns the bind variable value for endQueryTime.
     * @return bind variable value for endQueryTime
     */
    public Timestamp getendQueryTime() {
        return (Timestamp)getNamedWhereClauseParam("endQueryTime");
    }

    /**
     * Sets <code>value</code> for bind variable endQueryTime.
     * @param value value to bind as endQueryTime
     */
    public void setendQueryTime(Timestamp value) {
        setNamedWhereClauseParam("endQueryTime", value);
    }

    /**
     * Returns the bind variable value for clsRoomId.
     * @return bind variable value for clsRoomId
     */
    public String getclsRoomId() {
        return (String)getNamedWhereClauseParam("clsRoomId");
    }

    /**
     * Sets <code>value</code> for bind variable clsRoomId.
     * @param value value to bind as clsRoomId
     */
    public void setclsRoomId(String value) {
        setNamedWhereClauseParam("clsRoomId", value);
    }

    /**
     * Returns the bind variable value for actId.
     * @return bind variable value for actId
     */
    public String getactId() {
        return (String)getNamedWhereClauseParam("actId");
    }

    /**
     * Sets <code>value</code> for bind variable actId.
     * @param value value to bind as actId
     */
    public void setactId(String value) {
        setNamedWhereClauseParam("actId", value);
    }
}

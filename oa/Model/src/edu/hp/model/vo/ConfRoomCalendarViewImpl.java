package edu.hp.model.vo;

import edu.hp.model.common.BaseView;
import edu.hp.model.vo.common.ConfRoomCalendarView;

import oracle.jbo.Row;
import oracle.jbo.domain.Timestamp;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jan 27 19:04:26 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConfRoomCalendarViewImpl extends BaseView implements ConfRoomCalendarView {
    /**
     * This is the default constructor (do not remove).
     */
    public ConfRoomCalendarViewImpl() {
    }
    
    public void newRow(String userDisplayName, String userId) {
        Row newRow = this.createRow();
        newRow.setAttribute("UserDisplayName", userDisplayName);
        newRow.setAttribute("UserId", userId);
        this.insertRow(newRow);
        this.setCurrentRow(newRow);
    }
    
    public void updateActivityTime(String confRmCalId, Timestamp startTime, Timestamp endTime) {
        this.queryByPK(confRmCalId);
        Row[] rows = this.getAllRowsInRange();
        if (rows != null && rows.length > 0) {
            rows[0].setAttribute("StartTime", startTime);
            rows[0].setAttribute("EndTime", endTime);
            this.getDBTransaction().commit();
        }
    }

    public void updateEndTime(String clsRmCalId, Timestamp endTime) {
        this.queryByPK(clsRmCalId);

        Row[] rows = this.getAllRowsInRange();
        if (rows != null && rows.length > 0) {

            //rows[0].removeFromCollection();
            rows[0].setAttribute("EndTime", endTime);
            this.getDBTransaction().commit();
        }
    }


    public void deleteByPK(String clsRmCalId) {
        this.queryByPK(clsRmCalId);
        Row[] rows = this.getAllRowsInRange();
        if (rows != null && rows.length > 0) {
            //rows[0].removeFromCollection();
            rows[0].remove();
            this.getDBTransaction().commit();
        }
    }

    public void queryByPK(String confRmCalId) {
        super.queryByPK("findById", "confRmCalId", confRmCalId);
    }

    /**
     * Returns the variable value for id.
     * @return variable value for id
     */
    public String getconfRmCalId() {
        return (String)ensureVariableManager().getVariableValue("confRmCalId");
    }

    /**
     * Sets <code>value</code> for variable id.
     * @param value value to bind as id
     */
    public void setconfRmCalId(String value) {
        ensureVariableManager().setVariableValue("confRmCalId", value);
    }
}

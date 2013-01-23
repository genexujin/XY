package edu.hp.model.vo;

import edu.hp.model.common.BaseView;
import edu.hp.model.vo.common.ClsRmCalDMLView;

import oracle.jbo.Row;
import oracle.jbo.domain.Timestamp;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jan 20 13:21:12 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ClsRmCalDMLViewImpl extends BaseView implements ClsRmCalDMLView {
    /**
     * This is the default constructor (do not remove).
     */
    public ClsRmCalDMLViewImpl() {
    }
    
    public void updateEndTime(String clsRmCalId, Timestamp endTime){
        this.queryByPK(clsRmCalId);
        Row[] rows = this.getAllRowsInRange();
        if (rows != null && rows.length > 0) {
            //rows[0].removeFromCollection();
            rows[0].setAttribute("ActEndTime", endTime);
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

    public void queryByPK(String clsRmCalId) {
        super.queryByPK("findById", "clsRmCalId", clsRmCalId);
    }

    public void newRow(String userDisplayName, String userId) {
        Row newRow = this.createRow();
        newRow.setAttribute("UserDisplayName", userDisplayName);
        newRow.setAttribute("UserId", userId);
        this.insertRow(newRow);
        this.setCurrentRow(newRow);
    }

    /**
     * Returns the variable value for clsRmCalId.
     * @return variable value for clsRmCalId
     */
    public String getclsRmCalId() {
        return (String)ensureVariableManager().getVariableValue("clsRmCalId");
    }

    /**
     * Sets <code>value</code> for variable clsRmCalId.
     * @param value value to bind as clsRmCalId
     */
    public void setclsRmCalId(String value) {
        ensureVariableManager().setVariableValue("clsRmCalId", value);
    }
}
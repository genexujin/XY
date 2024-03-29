package edu.hp.model.vo;

import edu.hp.model.vo.common.VehicleSubTaskView;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jul 16 20:17:56 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class VehicleSubTaskViewImpl extends ViewObjectImpl implements VehicleSubTaskView {
    /**
     * This is the default constructor (do not remove).
     */
    public VehicleSubTaskViewImpl() {
    }

    public void deleteTargetRow(String masterId) {
        System.err.println("master id " + masterId);
        this.setApplyViewCriteriaNames(null);
        ViewCriteria criteria = this.getViewCriteria("findByMaster");
        this.applyViewCriteria(criteria);
        this.setmasterId(masterId);
        this.executeQuery();
        if (this.getAllRowsInRange().length > 0) {
            System.err.println("found!");
            Row[] allRowsInRange = this.getAllRowsInRange();
            Row target = allRowsInRange[0];
            target.remove();
//            target.removeFromCollection();
        }
    }

    public void updateTargetRow(String masterId, Timestamp start, Timestamp end) {
        this.setApplyViewCriteriaNames(null);
        ViewCriteria criteria = this.getViewCriteria("findByMaster");
        this.applyViewCriteria(criteria);
        this.setmasterId(masterId);
        this.executeQuery();
        if (this.getAllRowsInRange().length > 0) {
            Row[] allRowsInRange = this.getAllRowsInRange();
            Row target = allRowsInRange[0];
            target.setAttribute("StartTime", start);
            target.setAttribute("EndTime", end);
        }

    }

    /**
     * Returns the variable value for masterId.
     * @return variable value for masterId
     */
    public String getmasterId() {
        return (String)ensureVariableManager().getVariableValue("masterId");
    }

    /**
     * Sets <code>value</code> for variable masterId.
     * @param value value to bind as masterId
     */
    public void setmasterId(String value) {
        ensureVariableManager().setVariableValue("masterId", value);
    }
}

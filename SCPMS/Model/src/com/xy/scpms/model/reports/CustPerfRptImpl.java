package com.xy.scpms.model.reports;

import com.xy.scpms.model.common.NoAutoQueryView;
import com.xy.scpms.model.reports.common.CustPerfRpt;

import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.Date;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Sep 12 19:51:18 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CustPerfRptImpl extends NoAutoQueryView implements CustPerfRpt {
    /**
     * This is the default constructor (do not remove).
     */
    public CustPerfRptImpl() {
    }
    public void doQuery(Date start,Date end){
        
             
        if(start!=null)
            this.setstartdate(start);
        if(end!=null)
            this.setendDate(end);
        
        this.executeQuery();
    }

    /**
     * Returns the bind variable value for startDate.
     * @return bind variable value for startDate
     */
    public Date getstartdate() {
        return (Date)getNamedWhereClauseParam("startdate");
    }

    /**
     * Sets <code>value</code> for bind variable startDate.
     * @param value value to bind as startDate
     */
    public void setstartdate(Date value) {
        setNamedWhereClauseParam("startdate", value);
    }

    /**
     * Returns the bind variable value for endDate.
     * @return bind variable value for endDate
     */
    public Date getendDate() {
        return (Date)getNamedWhereClauseParam("endDate");
    }

    /**
     * Sets <code>value</code> for bind variable endDate.
     * @param value value to bind as endDate
     */
    public void setendDate(Date value) {
        setNamedWhereClauseParam("endDate", value);
    }
}

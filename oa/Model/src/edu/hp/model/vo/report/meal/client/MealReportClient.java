package edu.hp.model.vo.report.meal.client;

import edu.hp.model.vo.report.meal.common.MealReport;

import oracle.jbo.client.remote.ViewUsageImpl;
import oracle.jbo.domain.Date;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jul 02 14:41:54 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class MealReportClient extends ViewUsageImpl implements MealReport {
    /**
     * This is the default constructor (do not remove).
     */
    public MealReportClient() {
    }

    public void runReport(String location, Date startDt, Date endDt) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"runReport",new String [] {"java.lang.String","oracle.jbo.domain.Date","oracle.jbo.domain.Date"},new Object[] {location, startDt, endDt});
        return;
    }
}

package com.xy.scpms.model.reports.common;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Sep 12 16:24:53 CST 2011
// ---------------------------------------------------------------------
public interface PrjMgrPerfRpt extends ViewObject {
    void doQuery(Date start, Date end, String dept, String mgrId);

    void clearRowSet();
}
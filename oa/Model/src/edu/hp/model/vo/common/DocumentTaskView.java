package edu.hp.model.vo.common;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jul 09 21:03:33 CST 2013
// ---------------------------------------------------------------------
public interface DocumentTaskView extends ViewObject {
    void search(String taskName, String state, Date startDate, Date endDate, String dept);

    void executeEmptyQuery();
}
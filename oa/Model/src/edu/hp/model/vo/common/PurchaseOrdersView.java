package edu.hp.model.vo.common;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Mar 07 19:04:51 CST 2013
// ---------------------------------------------------------------------
public interface PurchaseOrdersView extends ViewObject {
    void doQuery(String oRdId, String state, String category, Date submitDateFrom, Date submitDateTo,
                 String submitterId);

    void findByState(String state);

    void findBySubmitterId(String submitterId);

    void newRow();

    void newRow(String userId);
}

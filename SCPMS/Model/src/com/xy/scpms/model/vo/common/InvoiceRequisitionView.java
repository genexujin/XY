package com.xy.scpms.model.vo.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Nov 12 19:59:15 CST 2011
// ---------------------------------------------------------------------
public interface InvoiceRequisitionView extends ViewObject {
    boolean isAlreadyApplied(oracle.jbo.domain.Number paymentId);

    String getdept();

    oracle.jbo.domain.Number getpaymentId();

    String getuserName();

    void setReqApproved(oracle.jbo.domain.Number id);

    void setReqRejected(oracle.jbo.domain.Number id);

    void setdept(String value);

    void setpaymentId(oracle.jbo.domain.Number value);

    void setuserName(String value);
}

package com.xy.scpms.model.vo.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Aug 27 09:43:09 CST 2011
// ---------------------------------------------------------------------
public interface ContractView extends ViewObject {
    void setCurrentRowById(oracle.jbo.domain.Number id);

    void setCurrentRowCustomerId(String id);

    void setContactNull();

    void refreshCustomerContactVA(String id);

    void changeCustomer();

    oracle.jbo.domain.Number getidBind();

    String getpmBind();

    String getuserBind();

    void setContractApproved(oracle.jbo.domain.Number id);

    void setContractRejected(oracle.jbo.domain.Number id);

    void setContractSuspended(oracle.jbo.domain.Number id);

    void setContractTerminated(oracle.jbo.domain.Number id);

    void setidBind(oracle.jbo.domain.Number value);

    void setpmBind(String value);

    void setuserBind(String value);

    void sumTotal();

    void findById(oracle.jbo.domain.Number id);
}

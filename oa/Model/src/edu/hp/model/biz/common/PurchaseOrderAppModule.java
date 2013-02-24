package edu.hp.model.biz.common;

import java.math.BigDecimal;

import oracle.jbo.ApplicationModule;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Feb 07 21:12:40 CST 2013
// ---------------------------------------------------------------------
public interface PurchaseOrderAppModule extends ApplicationModule {
    void findByUserId(String submitterId);

    void newPo(String userId);

    void findByPoId(String poId);

    BigDecimal getApprovalLimitForCategoryId(String categoryId);
}

package edu.hp.model.vo.query.security.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jan 13 16:40:17 CST 2013
// ---------------------------------------------------------------------
public interface UserMenuQuery extends ViewObject {
    void queryUserMenu(String masterCategory, String category, String userName);

    void queryUserMenu(String userName);
}
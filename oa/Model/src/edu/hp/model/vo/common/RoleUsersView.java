package edu.hp.model.vo.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Apr 13 12:25:53 CST 2013
// ---------------------------------------------------------------------
public interface RoleUsersView extends ViewObject {
    boolean isUserInRole(String userId, String roleName);
}

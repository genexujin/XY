package edu.hp.model.vo.query.conf.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jan 27 18:59:28 CST 2013
// ---------------------------------------------------------------------
public interface ConfRoomQueryView extends ViewObject {
    void findByUserId(boolean enabled, String userId);

    void refreshCalendar(String confRmNos);
}

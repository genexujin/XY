package edu.hp.model.vo.common;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.Timestamp;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jan 31 13:11:32 CST 2013
// ---------------------------------------------------------------------
public interface VehicleCalendarView extends ViewObject {
    void deleteByPK(String vehicleActId);

    void findByState(String state);

    void findByUserid(String userId);

    void newRow(String userDisplayName, String userId);

    void queryByPK(String confRmCalId);

    void updateActivityTime(String vehicleActId, Timestamp startTime, Timestamp endTime);

    void updateEndTime(String vehicleActId, Timestamp endTime);

    void findByDriver(String driverId);
}

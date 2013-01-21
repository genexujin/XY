package edu.hp.view.bean.common;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import oracle.adf.view.rich.model.CalendarActivity;


public class OACalendarActivity implements Serializable {
    
    public OACalendarActivity(CalendarActivity activity) {
        _tz = TimeZone.getDefault();
        _activity = activity;
        //location is used as userid
        userId = activity.getLocation();

        if (CalendarActivity.TimeType.ALLDAY.equals(_activity.getTimeType())) {
            _allDay = true;
        } else {
            _allDay = false;
        }

    }

    public boolean isAllDay() {
        return _allDay;
    }


    public void setAllDay(boolean allDay) {
        _allDay = allDay;
    }

    public boolean isRecurring() {
        if (_activity.getRecurring() == CalendarActivity.Recurring.RECURRING) {
            return true;
        }

        return false;
    }

    public boolean isReminder() {
        if (_activity.getReminder() == CalendarActivity.Reminder.ON) {
            return true;
        }

        return false;
    }

    public void setReminder(boolean reminder) {
        _activity.setReminder(reminder ? CalendarActivity.Reminder.ON : CalendarActivity.Reminder.OFF);
    }

    public void setFrom(Date from) {
        //do nothing
    }

    public Date getFrom() {

        return _activity.getStartDate(_tz);
    }

    public Date getTo() {

        return _activity.getEndDate(_tz);
    }

    public void setTo(Date to) {
        //do nothing
    }


    public void setRecurring(boolean isRecurring) {
        //do nothing
    }

    public String getTitle() {
        return _activity.getTitle();
    }

    public String getProviderId() {
        return _activity.getProvider().getId();
    }

    public void setProviderId(String newProviderId) {
        //do nothing
    }

    public String getId() {
        return _activity.getId();
    }

    public String getLocation() {
        return _activity.getLocation();
    }

    public Map<String, Object> getCustomAttributes() {
        return _activity.getCustomAttributes();
    }


    public void setTitle(String title) {
        _activity.setTitle(title);
    }

    public void setLocation(String location) {
        _activity.setLocation(location);
    }


    public void setActivity(CalendarActivity activity) {
        _activity = activity;
    }

    public CalendarActivity getActivity() {
        return _activity;
    }


    protected CalendarActivity _activity = null;
    private boolean _allDay = false;
    private TimeZone _tz;
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}


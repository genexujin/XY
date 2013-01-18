package edu.hp.view.bean.common;

import java.io.Serializable;

import oracle.adf.view.rich.model.CalendarProvider;


/**
 * This is a class extending the CalendarModel abstract class
 */
public class OACalendarProvider extends CalendarProvider implements Serializable {

    public OACalendarProvider(String id, String displayName) {
        _id = id;
        _displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return _displayName;
    }

    @Override
    public String getId() {
        return _id;
    }


    @Override
    public CalendarProvider.Enabled getEnabled() {
        return _enabled;
    }

    @Override
    public void setEnabled(CalendarProvider.Enabled enabled) {
        _enabled = enabled;
    }
    private String _noOfPpl;
    private String _id = null;
    private String _displayName = null;
    private CalendarProvider.Enabled _enabled = CalendarProvider.Enabled.ENABLED;

    public void setNoOfPpl(String _noOfPpl) {
        this._noOfPpl = _noOfPpl;
    }

    public String getNoOfPpl() {
        return _noOfPpl;
    }
}


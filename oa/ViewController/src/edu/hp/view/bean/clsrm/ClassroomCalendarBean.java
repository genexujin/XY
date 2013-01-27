package edu.hp.view.bean.clsrm;

import edu.hp.view.bean.common.CalendarBean;
import edu.hp.view.bean.common.OACalendarActivity;
import edu.hp.view.security.LoginUser;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.event.CalendarActivityDurationChangeEvent;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.model.CalendarActivity;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.Timestamp;


public class ClassroomCalendarBean extends CalendarBean {

    public ClassroomCalendarBean() {

        refreshCalendarOptName = "refreshCalendar";
        refreshCalendarParamName = "clsRmNos";
        this.providerIteratorName = "ClassroomOfLocationIterator";
        this.locationIteratorName = "LocationsIterator";
        this.calendarIteratorName = "CalendarIterator";
        this.calendarid = "c1";
        reload();
    }

    public boolean isEditable() {

        boolean result = false;

        if (this.getCurrActivity() != null) {
            LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");

            if (user.getIsUserInRole().get("系统管理员") != null)
                return true;
            if (user.getIsUserInRole().get("教室管理员") != null)
                return true;

            String userId = this.getCurrActivity().getUserId();
            if (userId.equals(user.getUserName())) {

                return true;
            }
        }

        return result;

    }


    public void locationChange(ValueChangeEvent valueChangeEvent) {

        Integer newValue = (Integer)valueChangeEvent.getNewValue();
        //System.err.println(newValue);
        setCurrentLocation(newValue.intValue());
        this.reload();
        UIComponent component = JSFUtils.findComponent(valueChangeEvent.getComponent().getNamingContainer(), "pgl3");
        ADFUtils.partialRefreshComponenet(component);
    }


    public void deleteListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            OperationBinding binding = ADFUtils.findOperation("deleteByPK");
            String clsRmCalId = this.getCurrActivity().getActivity().getId();
            //            System.err.println("to del act id: " + clsRmCalId);
            binding.getParamsMap().put("clsRmCalId", clsRmCalId);
            binding.execute();
            if (binding.getErrors().isEmpty()) {
                this._currActivity = null;
                UIComponent calendar = JSFUtils.findComponentInRoot(calendarid);
                refreshCalendar(calendar);
            }

        }
    }


    public void providerEnabledChange(ValueChangeEvent valueChangeEvent) {
        this.providerChange(valueChangeEvent);
    }

    public void providerColorChange(ValueChangeEvent valueChangeEvent) {
        this.colorChange(valueChangeEvent);
    }

    public String doEdit() {

        OperationBinding binding = ADFUtils.findOperation("queryByPK");
        binding.getParamsMap().put("clsRmCalId", this.getCurrActivity().getActivity().getId());
        binding.execute();

        if (!binding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("无法编辑当前日历事件！");
            return null;
        }

        return "Edit";
    }

    public void calDurationChanged(CalendarActivityDurationChangeEvent ae) {
        CalendarActivity activity = ae.getCalendarActivity();

        if (activity == null) {


            setCurrActivity(null);

            UIComponent calendar = JSFUtils.findComponentInRoot(calendarid);
            if (calendar != null)
                refreshCalendar(calendar);
            return;
        }

        OACalendarActivity demoActivity = new OACalendarActivity(activity);
        this.setCurrActivity(demoActivity);

        Boolean hasNoConflict =
            ensureTimeConflicts(new java.sql.Timestamp(demoActivity.getFrom().getTime()), new java.sql.Timestamp(ae.getNewEndDate().getTime()),
                                (String)demoActivity.getCustomAttributes().get("LocationId"), demoActivity.getId());
        if (hasNoConflict) {

            OperationBinding binding = ADFUtils.findOperation("updateEndTime");
            binding.getParamsMap().put("clsRmCalId", demoActivity.getId());
            binding.getParamsMap().put("endTime", new Timestamp(ae.getNewEndDate()));
            binding.execute();
            if (binding.getErrors().isEmpty()) {
                UIComponent calendar = JSFUtils.findComponentInRoot(calendarid);
                if (calendar != null)
                    refreshCalendar(calendar);
            } else {
                JSFUtils.addFacesErrorMessage("时间调整失败！");
            }

        } else {
            JSFUtils.addFacesErrorMessage("该教室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
        }
        //update clsrmcaldmlvo

    }

    protected Boolean ensureTimeConflicts(java.sql.Timestamp actStartTime, java.sql.Timestamp actEndTime,
                                          String clsRmId, String actId) {

        Boolean result = false;
        OperationBinding binding = ADFUtils.findOperation("ifConflict");
        binding.getParamsMap().put("actStartTime", actStartTime);
        binding.getParamsMap().put("actEndTime", actEndTime);
        binding.getParamsMap().put("clsRmId", clsRmId);
        binding.getParamsMap().put("actId", actId);
        binding.execute();
        result = (Boolean)binding.getResult();
        return result;

    }


    protected void doUpdateCalendar(OACalendarActivity activity, Date newStart, Date newEnd) {

        super.doUpdateCalendar(activity, newStart, newEnd);

        Boolean hasNoConflict =
            ensureTimeConflicts(new java.sql.Timestamp(newStart.getTime()), new java.sql.Timestamp(newEnd.getTime()),
                                (String)activity.getCustomAttributes().get("LocationId"), activity.getId());
        if (hasNoConflict) {

            OperationBinding binding = ADFUtils.findOperation("updateActivityTime");
            binding.getParamsMap().put("clsRmCalId", activity.getId());
            binding.getParamsMap().put("endTime", new Timestamp(newEnd));
            binding.getParamsMap().put("startTime", new Timestamp(newStart));
            binding.execute();
            if (binding.getErrors().isEmpty()) {
                UIComponent calendar = JSFUtils.findComponentInRoot(calendarid);
                if (calendar != null)
                    refreshCalendar(calendar);
            } else {
                JSFUtils.addFacesErrorMessage("时间调整失败！");
            }

        } else {
            JSFUtils.addFacesErrorMessage("该教室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
        }

    }

    public void onMyViewChange(ValueChangeEvent valueChangeEvent) {
        Boolean newValue = (Boolean)valueChangeEvent.getNewValue();
        OperationBinding binding = ADFUtils.findOperation("findByUserId");
        this.setMyView(newValue);
        binding.execute();
        UIComponent calendar = JSFUtils.findComponentInRoot(calendarid);
        this.refreshCalendar(calendar);
    }
}

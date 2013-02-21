package edu.hp.view.bean.vehicle;

import edu.hp.model.common.Constants;
import edu.hp.model.pojo.Notification;
import edu.hp.view.bean.BaseBean;
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


public class VehicleCalendarBean extends CalendarBean {

    private boolean myVelView = false;
    private boolean changeMade = false;
    private Timestamp startDayTime = null;
    private String vehicleId = null;
    private String action = "save";

    public VehicleCalendarBean() {

        needCheckConflict = false;
        refreshCalendarOptName = "refreshCalendar";
        providerNumOfPplCol = "FlexCol1";
        refreshCalendarParamName = "vehicleIds";
        this.providerIteratorName = "VehiclesIterator";
        this.locationIteratorName = "LocationsIterator";
        this.calendarIteratorName = "VehicleCalQueryIterator";
        this.calendarid = "c3";
        this.moduleAdminRole = "车辆调度";
        locationIdFieldName = "VehicleId";
        reload();
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
            binding.getParamsMap().put("vehicleActId", clsRmCalId);
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
        binding.getParamsMap().put("confRmCalId", this.getCurrActivity().getActivity().getId());
        binding.execute();

        if (!binding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("无法编辑当前日历事件！");
            return null;
        }

        return "Edit";
    }

    public void calDurationChanged(CalendarActivityDurationChangeEvent ae) {

        if (this.isEditable()) {

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

            OperationBinding binding = ADFUtils.findOperation("updateEndTime");
            binding.getParamsMap().put("vehicleActId", demoActivity.getId());
            binding.getParamsMap().put("endTime", new Timestamp(ae.getNewEndDate()));
            binding.execute();
            if (binding.getErrors().isEmpty()) {
                UIComponent calendar = JSFUtils.findComponentInRoot(calendarid);
                if (calendar != null)
                    refreshCalendar(calendar);
            } else {
                JSFUtils.addFacesErrorMessage("时间调整失败！");
            }

        }
    }


    protected void doUpdateCalendar(OACalendarActivity activity, Date newStart, Date newEnd) {

        super.doUpdateCalendar(activity, newStart, newEnd);


        OperationBinding binding = ADFUtils.findOperation("updateActivityTime");
        binding.getParamsMap().put("vehicleActId", activity.getId());
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


    }

    public void onMyViewChange(ValueChangeEvent valueChangeEvent) {
        Boolean newValue = (Boolean)valueChangeEvent.getNewValue();
        OperationBinding binding = ADFUtils.findOperation("findByUserId");
        this.setMyView(newValue);
        binding.execute();
        UIComponent calendar = JSFUtils.findComponentInRoot(calendarid);
        //System.err.println(calendar.getClientId());
        this.refreshCalendar(calendar);
    }

    public String save() {
        startDayTime = (Timestamp)ADFUtils.getBoundAttributeValue("StartTime");
        Timestamp submit = (Timestamp)ADFUtils.getBoundAttributeValue("SubmitDate");
        if (submit == null)
            ADFUtils.setBoundAttributeValue("SubmitDate", new Timestamp(System.currentTimeMillis()));
        vehicleId = (String)ADFUtils.getBoundAttributeValue("VehicleId");
        boolean success = ADFUtils.commit("车辆预订已保存！", "车辆预订保存失败，请核对输入的信息或联系管理员！");
        if (success)
            sendNotification();
        return null;
    }

    private void sendNotification() {

        String vehicleName = (String)ADFUtils.getBoundAttributeValue("VehicleName");
        String contactId = (String)ADFUtils.getBoundAttributeValue("ContactId");
        String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
        String title = (String)ADFUtils.getBoundAttributeValue("Title");
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        String noteTitle;
        String noteContent;
        String dateStr = getDateString();
        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
        if (action.equals("save") && state.equals(Constants.STATE_TRIP_PLANNED)) {
            noteTitle = "您的车辆预订：" + title + " 已完成调度并被"+user.getDisplayName()+"修改。 ";
            noteContent = " 修改时间：" + dateStr + " 使用的车辆为：" + vehicleName;
            //System.err.println("sent as saved");
        } else if (action.equals("cancel")) {
            noteTitle = "您的车辆预订：" + title + " 已取消。 ";
            noteContent = " 取消时间：" + dateStr;
            //System.err.println("sent as cancelled");
        } else {
            noteTitle = "您的车辆预订：" + title + " 已调度完成。 ";
            noteContent = " 完成时间：" + dateStr + " 使用的车辆为：" + vehicleName;
            //System.err.println("sent as planned");
        }
        this.sendNotification(noteTitle, noteContent, userId, null);
        this.sendNotification(noteTitle, noteContent, contactId, null);
        
        changeMade = true;
        ADFUtils.findOperation("Commit").execute();

    }

    public void onStateChange(ValueChangeEvent valueChangeEvent) {

        String state = (String)ADFUtils.getBoundAttributeValue("State");
        String newState = (String)valueChangeEvent.getNewValue();

        if (state != null && newState != null && !state.equals(newState)) {
            if (newState.equals(Constants.STATE_CANCELED)) {
                action = "cancel";
            } else if (newState.equals(Constants.STATE_TRIP_PLANNED)) {
                action = "approve";
            }
        } else {
            action = "save";
        }
    }

    public String goBackToCalendar() {

        if (vehicleId != null && startDayTime != null && changeMade) {
            String ids = this.getProviderIds();
            if (ids != null && ids.indexOf(vehicleId) < 0 && !ids.equals("NA")) {
                ids = ids + "," + vehicleId;
            } else if (ids == null || ids.equals("NA")) {
                ids = vehicleId;
            }

            Date active = new Date(startDayTime.getTime());
            this.setActiveDay(active);
            OperationBinding refreshOp = ADFUtils.findOperation("refreshCalendar");
            refreshOp.getParamsMap().put("vehicleIds", ids);
            refreshOp.execute();

        }
        return "Calendar";
    }

    public void setMyVelView(boolean myVelView) {
        this.myVelView = myVelView;
    }

    public boolean isMyVelView() {
        return myVelView;
    }
}

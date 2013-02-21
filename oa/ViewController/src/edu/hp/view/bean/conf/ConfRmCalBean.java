package edu.hp.view.bean.conf;

import edu.hp.view.bean.common.CalendarBean;
import edu.hp.view.bean.common.OACalendarActivity;
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


public class ConfRmCalBean extends CalendarBean {

    public ConfRmCalBean() {

        refreshCalendarOptName = "refreshCalendar";
        refreshCalendarParamName = "confRmNos";
        this.providerIteratorName = "ConfRmOfLocationIterator";
        this.locationIteratorName = "LocationsIterator";
        this.calendarIteratorName = "ConfRoomQueryIterator";
        this.calendarid = "c2";
        this.moduleAdminRole = "会议室审核";
        locationIdFieldName = "MeetingRoomId";
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
            String clsRmCalId = this.getCurrActivity().getId();
            String title = getCurrActivity().getTitle();
            String userId = getCurrActivity().getUserId();
            String dateStr = getDateString();
            String noteTitle = "您为会议主题：" + title + " 所做的会议室申请已取消。 ";
            String noteContent = " 取消时间：" + dateStr;
            
            //            System.err.println("to del act id: " + clsRmCalId);
            binding.getParamsMap().put("clsRmCalId", clsRmCalId);
            binding.execute();
            if (binding.getErrors().isEmpty()) {
                this._currActivity = null;
                UIComponent calendar = JSFUtils.findComponentInRoot(calendarid);
                refreshCalendar(calendar);                
                sendNotification(noteTitle, noteContent, userId, null);
                ADFUtils.findOperation("Commit").execute();
            }
            
        }
    }
    
    protected String getDateString() {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String  dateStr = format.format(new Date());
        return dateStr;
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
//            System.err.println(activity.getTitle());
//            System.err.println(demoActivity.getId());
//            System.err.println(demoActivity.getFrom());
//            System.err.println(ae.getNewEndDate());
//            System.err.println(demoActivity.getCustomAttributes().get("MeetingRoomId"));
//            System.err.println("param ending...");

            Boolean hasNoConflict =
                ensureTimeConflicts(new java.sql.Timestamp(demoActivity.getFrom().getTime()), new java.sql.Timestamp(ae.getNewEndDate().getTime()),
                                    (String)demoActivity.getCustomAttributes().get("MeetingRoomId"),
                                    demoActivity.getId());
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
                JSFUtils.addFacesErrorMessage("该会议室该时间段已经有其他预订，无法创建新的预订，请更换时间段！");
            }
            //update clsrmcaldmlvo
        }
    }

    


    protected void doUpdateCalendar(OACalendarActivity activity, Date newStart, Date newEnd) {

        super.doUpdateCalendar(activity, newStart, newEnd);

        Boolean hasNoConflict =
            ensureTimeConflicts(new java.sql.Timestamp(newStart.getTime()), new java.sql.Timestamp(newEnd.getTime()),
                                (String)activity.getCustomAttributes().get("MeetingRoomId"), activity.getId());
        if (hasNoConflict) {

            OperationBinding binding = ADFUtils.findOperation("updateActivityTime");
            binding.getParamsMap().put("confRmCalId", activity.getId());
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
            JSFUtils.addFacesErrorMessage("该会议室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
        }

    }

    public void onMyViewChange(ValueChangeEvent valueChangeEvent) {
        //System.err.println("here");
        Boolean newValue = (Boolean)valueChangeEvent.getNewValue();
        OperationBinding binding = ADFUtils.findOperation("findByUserId");
        this.setMyView(newValue);
        binding.execute();
        UIComponent calendar = JSFUtils.findComponentInRoot(calendarid);
        //System.err.println(calendar.getClientId());
        this.refreshCalendar(calendar);
    }
}

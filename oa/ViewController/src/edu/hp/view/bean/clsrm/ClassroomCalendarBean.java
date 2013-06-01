package edu.hp.view.bean.clsrm;

import edu.hp.view.bean.common.CalendarBean;
import edu.hp.view.bean.common.OACalendarActivity;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.event.CalendarActivityDurationChangeEvent;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.ItemEvent;
import oracle.adf.view.rich.model.CalendarActivity;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.Timestamp;

import org.apache.myfaces.trinidad.event.DisclosureEvent;


public class ClassroomCalendarBean extends CalendarBean {


    public ClassroomCalendarBean() {

        refreshCalendarOptName = "refreshCalendar";
        refreshCalendarParamName = "clsRmNos";
        this.providerIteratorName = "ClassroomOfLocationIterator";
        this.locationIteratorName = "LocationsIterator";
        this.calendarIteratorName = "CalendarIterator";
        this.calendarid = "c1";
        this.moduleAdminRole = "教室管理员";
        locationIdFieldName = "ClassroomId";
        reload();
    }


    public void locationChange(ValueChangeEvent valueChangeEvent) {

        Integer newValue = (Integer)valueChangeEvent.getNewValue();
        if (newValue != null) {
            //this.location = newValue;
            setCurrentLocation(newValue.intValue());
            this.reload();
            UIComponent component =
                JSFUtils.findComponent(valueChangeEvent.getComponent().getNamingContainer(), "pgl3");
            ADFUtils.partialRefreshComponenet(component);
        }
    }


    public void deleteListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            OperationBinding binding = ADFUtils.findOperation("deleteByPK");
            String clsRmCalId = this.getCurrActivity().getActivity().getId();
            // System.err.println("to del act id: " + clsRmCalId);
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

        if (isEditable()) {

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
                                    (String)demoActivity.getCustomAttributes().get("ClassroomId"),
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
                JSFUtils.addFacesErrorMessage("该教室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
            }

        }
        //update clsrmcaldmlvo

    }


    protected void doUpdateCalendar(OACalendarActivity activity, Date newStart, Date newEnd) {

        super.doUpdateCalendar(activity, newStart, newEnd);

        Boolean hasNoConflict =
            ensureTimeConflicts(new java.sql.Timestamp(newStart.getTime()), new java.sql.Timestamp(newEnd.getTime()),
                                (String)activity.getCustomAttributes().get("ClassroomId"), activity.getId());
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
        //System.err.println("here");
        Boolean newValue = (Boolean)valueChangeEvent.getNewValue();
        OperationBinding binding = ADFUtils.findOperation("findByUserId");
        this.setMyView(newValue);
        binding.execute();
        UIComponent calendar = JSFUtils.findComponentInRoot(calendarid);
        //System.err.println(calendar.getClientId());
        this.refreshCalendar(calendar);
    }

    public void onSelectTableView(DisclosureEvent disclosureEvent) {
        //RichCalendar calendar = (RichCalendar)JSFUtils.findComponentInRoot(calendarid);
        if (disclosureEvent.isExpanded()) {
            //        Object startDate = JSFUtils.resolveExpression("#{bindings.Calendar.startDate}");
            //        System.err.println(startDate);
            //        Object endDate = JSFUtils.resolveExpression("#{bindings.Calendar.endDate}");
            //        System.err.println(endDate);
            ADFUtils.findOperation("findByDateRange").execute();
        }
    }


    public void onTableView(ItemEvent itemEvent) {

    }

   
}

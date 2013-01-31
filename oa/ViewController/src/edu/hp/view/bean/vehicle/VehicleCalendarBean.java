package edu.hp.view.bean.vehicle;

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


public class VehicleCalendarBean extends CalendarBean {

    public VehicleCalendarBean() {
        
        needCheckConflict = false;
        refreshCalendarOptName = "refreshCalendar";
        providerNumOfPplCol = "FlexCol1";
        refreshCalendarParamName = "vehicleIds";
        this.providerIteratorName = "VehiclesIterator";
        this.locationIteratorName = "LocationsIterator";
        this.calendarIteratorName = "VehicleCalQueryIterator";
        this.calendarid = "c1";
        this.moduleAdminRole = "总务处主任";
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
            //            System.err.println(activity.getTitle());
            //            System.err.println(demoActivity.getId());
            //            System.err.println(demoActivity.getFrom());
            //            System.err.println(ae.getNewEndDate());
            //            System.err.println(demoActivity.getCustomAttributes().get("MeetingRoomId"));
            //            System.err.println("param ending...");


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
        this.refreshCalendar(calendar);
    }
    
    public String save(){
        ADFUtils.commit("车辆预订已保存！", "车辆预订保存失败，请核对输入的信息或联系管理员！");
        return null;
    }
}

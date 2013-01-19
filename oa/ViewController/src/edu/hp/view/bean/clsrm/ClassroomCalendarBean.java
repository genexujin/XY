package edu.hp.view.bean.clsrm;

import edu.hp.view.bean.common.CalendarBean;
import edu.hp.view.utils.ADFUtils;

import edu.hp.view.utils.JSFUtils;

import javax.faces.event.ValueChangeEvent;


import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;


public class ClassroomCalendarBean extends CalendarBean {

    private RichPanelSplitter spliter;
    private RichPanelGroupLayout clsrmGrpLayout;
    

    public ClassroomCalendarBean() {
        
        refreshCalendarOptName = "refreshCalendar";
        refreshCalendarParamName = "clsRmNos";
        this.providerIteratorName = "ClassroomOfLocationIterator";
        this.locationIteratorName = "LocationsIterator";        
        this.calendarIteratorName = "ClassroomCalendarIterator";
        reload();
    }
    
    public void createDialogListener(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
                if(ensureTimeConflicts())
                    doCommit();
                else
                    JSFUtils.addFacesErrorMessage("该教室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
            } else {
                doRollback();
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }
    
    protected Boolean ensureTimeConflicts(){
        
        Boolean result = false;        
        OperationBinding binding = ADFUtils.findOperation("ifConflict");
        binding.execute();
        result = (Boolean)binding.getResult();
        return result;

    }
    
    public void locationChange(ValueChangeEvent valueChangeEvent) {
        
        Integer newValue = (Integer)valueChangeEvent.getNewValue();
        //System.err.println(newValue);
        setCurrentLocation(newValue.intValue());
        this.reload();
        ADFUtils.partialRefreshComponenet(clsrmGrpLayout);
    }
    

    public void deleteListener(DialogEvent dialogEvent) {
        // Add event code here...
    }

    public void editDialogListener(DialogEvent dialogEvent) {
        // Add event code here...
    }

    public void providerEnabledChange(ValueChangeEvent valueChangeEvent) {
        this.providerChange(valueChangeEvent);
    }

    public void providerColorChange(ValueChangeEvent valueChangeEvent) {
        this.colorChange(valueChangeEvent);
    }

    public void setSpliter(RichPanelSplitter spliter) {
        this.spliter = spliter;
    }

    public RichPanelSplitter getSpliter() {
        return spliter;
    }

   

    public void setClarmGrpLayout(RichPanelGroupLayout clsrmGrpLayout) {
        this.clsrmGrpLayout = clsrmGrpLayout;
    }

    public RichPanelGroupLayout getClarmGrpLayout() {
        return clsrmGrpLayout;
    }
    
}

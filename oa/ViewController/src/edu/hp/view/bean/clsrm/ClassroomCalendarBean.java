package edu.hp.view.bean.clsrm;

import edu.hp.view.bean.common.CalendarBean;
import edu.hp.view.utils.utils.ADFUtils;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.event.DialogEvent;


public class ClassroomCalendarBean extends CalendarBean {

    private RichPanelSplitter spliter;
    private RichPanelGroupLayout clsrmGrpLayout;

    public ClassroomCalendarBean() {
        this.providerIteratorName = "ClassroomOfLocationIterator";
        this.locationIteratorName = "LocationsIterator";        
        reload();
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
        // Add event code here...
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

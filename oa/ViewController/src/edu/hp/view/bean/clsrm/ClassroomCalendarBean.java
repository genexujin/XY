package edu.hp.view.bean.clsrm;

import edu.hp.view.bean.common.CalendarBean;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.event.DialogEvent;


public class ClassroomCalendarBean extends CalendarBean {
    
    public ClassroomCalendarBean() {
        
        refreshCalendarOptName = "refreshCalendar";
        refreshCalendarParamName = "clsRmNos";
        this.providerIteratorName = "ClassroomOfLocationIterator";
        this.locationIteratorName = "LocationsIterator";        
        this.calendarIteratorName = "CalendarIterator";
        this.calendarid="c1";
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

}

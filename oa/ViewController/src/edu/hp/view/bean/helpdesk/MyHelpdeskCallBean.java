package edu.hp.view.bean.helpdesk;

import edu.hp.view.utils.ADFUtils;

import edu.hp.view.utils.JSFUtils;

import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.Row;

public class MyHelpdeskCallBean {
    private final String reasonLevel1IteratorName = "ReasonLevel1Iterator";
    private final String reasonLevel2IteratorName = "ReasonLevel2Iterator";
    
    public MyHelpdeskCallBean() {
        
    }

    public void reasonLevel1Changed(ValueChangeEvent valueChangeEvent) {
        int newValue = (Integer)valueChangeEvent.getNewValue();
//        System.out.println(newValue);
        setCurrentReasonLevel1(newValue);
//        loadReasonLevel2();
        UIComponent component = JSFUtils.findComponent(valueChangeEvent.getComponent().getNamingContainer(), "soc2");
        ADFUtils.partialRefreshComponenet(component);
    }
    
    public void setCurrentReasonLevel1(final int index) {
        DCIteratorBinding it = ADFUtils.findIterator(reasonLevel1IteratorName);
        it.setCurrentRowIndexInRange(index);
    }

    private void loadReasonLevel2() {
        DCIteratorBinding it = ADFUtils.findIterator(reasonLevel2IteratorName);
        if (it != null) {
            it.setRangeSize(-1);
            it.executeQuery();
            Row[] allRowsInRange = it.getAllRowsInRange();
            if (allRowsInRange != null && allRowsInRange.length > 0) {
                for (Row row : allRowsInRange) {
                    
                }
            }
        }
    }
}

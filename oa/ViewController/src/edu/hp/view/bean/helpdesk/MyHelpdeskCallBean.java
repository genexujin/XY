package edu.hp.view.bean.helpdesk;

import edu.hp.view.utils.ADFUtils;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;

public class MyHelpdeskCallBean {
    private final String reasonLevel1IteratorName = "ReasonLevel1Iterator";
    
    public MyHelpdeskCallBean() {
        
    }

    public void reasonLevel1Changed(ValueChangeEvent valueChangeEvent) {
        int newValue = (Integer)valueChangeEvent.getNewValue();
//        System.out.println(newValue);
        setCurrentReasonLevel1(newValue);
    }
    
    public void setCurrentReasonLevel1(final int index) {
        DCIteratorBinding it = ADFUtils.findIterator(reasonLevel1IteratorName);
        it.setCurrentRowIndexInRange(index);
    }
}

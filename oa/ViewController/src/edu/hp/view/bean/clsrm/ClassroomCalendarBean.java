package edu.hp.view.bean.clsrm;

import edu.hp.view.bean.common.CalendarBean;
import edu.hp.view.security.LoginUser;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;


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
            if (userId.equals(user.getUserName())){
                
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
            System.err.println("to del act id: " + clsRmCalId);
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
}

package edu.hp.model.biz;

import edu.hp.model.biz.common.HelpdeskCallsAppModule;
import edu.hp.model.vo.EmployeesViewImpl;
import edu.hp.model.vo.HelpdeskCallsViewImpl;

import edu.hp.model.vo.HelpdeskRolesViewImpl;
import edu.hp.model.vo.query.helpdesk.EmpWithEmptyImpl;

import edu.hp.model.vo.query.helpdesk.HdStateWithEmptyImpl;

import edu.hp.model.vo.query.helpdesk.ReasonLevel2WithEmptyImpl;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Feb 15 13:10:27 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class HelpdeskCallsAppModuleImpl extends ApplicationModuleImpl implements HelpdeskCallsAppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public HelpdeskCallsAppModuleImpl() {
    }
    
    public void findByCallerId(String callerId) {
        HelpdeskCallsViewImpl hdView = this.getHelpdeskCallsView();
        hdView.setApplyViewCriteriaNames(null);
        
        //Do the query        
        System.err.println("In AppModule: callerId is: " + callerId);
        if (callerId != null) {
            hdView.setcallerId(callerId);
            ViewCriteria cIdCriteria = hdView.getViewCriteria("CallerCriteria");
            hdView.setApplyViewCriteriaName(cIdCriteria.getName());
            hdView.executeQuery();
        }
        
        //Set the lov's value, so on page the correct user will be selected by default in the lov
        EmpWithEmptyImpl eView = this.getEmpWithEmptyForCaller();
        eView.setApplyViewCriteriaNames(null);
        eView.setcId(callerId);
        ViewCriteria empCriteria = eView.getViewCriteria("findByCallerIdCriteria");
        eView.setApplyViewCriteriaName(empCriteria.getName());
        eView.executeQuery();
    }
    
    public void findByState(String state) {
        HelpdeskCallsViewImpl hdView = this.getHelpdeskCallsView();
        hdView.setApplyViewCriteriaNames(null);
        
        System.err.println("In AppModule: state is: " + state);
        if (state != null) {
            hdView.setStateV(state);
            ViewCriteria sCriteria = hdView.getViewCriteria("StateCriteria");
            hdView.setApplyViewCriteriaName(sCriteria.getName());
            hdView.executeQuery();
        }
        
        HdStateWithEmptyImpl sView = this.getHdStateWithEmpty();
        sView.setApplyViewCriteriaNames(null);
        sView.setstate(state);
        ViewCriteria sCriteria = sView.getViewCriteria("HdStateCriteria");
        sView.setApplyViewCriteriaName(sCriteria.getName());
        sView.executeQuery();
    }
    
    public void findByCallId(String id) {
        HelpdeskCallsViewImpl hdView = this.getHelpdeskCallsView();
        hdView.setApplyViewCriteriaNames(null);
        
        System.err.println("In AppModule: CallId is: " + id);
        if (id != null) {
            hdView.setcallId(id);
            ViewCriteria cCriteria = hdView.getViewCriteria("CallIdCriteria");
            hdView.setApplyViewCriteriaName(cCriteria.getName());
            hdView.executeQuery();
        }
    }

    /**
     * Container's getter for HelpdeskCallsView.
     * @return HelpdeskCallsView
     */
    public HelpdeskCallsViewImpl getHelpdeskCallsView() {
        return (HelpdeskCallsViewImpl)findViewObject("HelpdeskCallsView");
    }

    /**
     * Container's getter for EmployeesView.
     * @return EmployeesView
     */
    public EmployeesViewImpl getEmployeesView() {
        return (EmployeesViewImpl)findViewObject("EmployeesView");
    }

    /**
     * Container's getter for HelpdeskCallsViewForCallee.
     * @return HelpdeskCallsViewForCallee
     */
    public HelpdeskCallsViewImpl getHelpdeskCallsViewForCallee() {
        return (HelpdeskCallsViewImpl)findViewObject("HelpdeskCallsViewForCallee");
    }

    /**
     * Container's getter for EmployeesView2.
     * @return EmployeesView2
     */
    public EmployeesViewImpl getEmployeesView2() {
        return (EmployeesViewImpl)findViewObject("EmployeesView2");
    }

    /**
     * Container's getter for HelpdeskCallsViewForCaller.
     * @return HelpdeskCallsViewForCaller
     */
    public HelpdeskCallsViewImpl getHelpdeskCallsViewForCaller() {
        return (HelpdeskCallsViewImpl)findViewObject("HelpdeskCallsViewForCaller");
    }

    /**
     * Container's getter for ReasonLevel1.
     * @return ReasonLevel1
     */
    public ViewObjectImpl getReasonLevel1() {
        return (ViewObjectImpl)findViewObject("ReasonLevel1");
    }

    /**
     * Container's getter for ReasonLevel2.
     * @return ReasonLevel2
     */
    public ViewObjectImpl getReasonLevel2() {
        return (ViewObjectImpl)findViewObject("ReasonLevel2");
    }

    /**
     * Container's getter for HdState.
     * @return HdState
     */
    public ViewObjectImpl getHdState() {
        return (ViewObjectImpl)findViewObject("HdState");
    }

    /**
     * Container's getter for ReasonLevel3.
     * @return ReasonLevel3
     */
    public ViewObjectImpl getReasonLevel3() {
        return (ViewObjectImpl)findViewObject("ReasonLevel3");
    }

    /**
     * Container's getter for HdResult.
     * @return HdResult
     */
    public ViewObjectImpl getHdResult() {
        return (ViewObjectImpl)findViewObject("HdResult");
    }

    /**
     * Container's getter for HdEval.
     * @return HdEval
     */
    public ViewObjectImpl getHdEval() {
        return (ViewObjectImpl)findViewObject("HdEval");
    }

    /**
     * Container's getter for HdLovView.
     * @return HdLovView
     */
    public ViewObjectImpl getHdLovView() {
        return (ViewObjectImpl)findViewObject("HdLovView");
    }

    /**
     * Container's getter for HdStateWithEmpty.
     * @return HdStateWithEmpty
     */
    public HdStateWithEmptyImpl getHdStateWithEmpty() {
        return (HdStateWithEmptyImpl)findViewObject("HdStateWithEmpty");
    }

    /**
     * Container's getter for HdResultWithEmpty.
     * @return HdResultWithEmpty
     */
    public ViewObjectImpl getHdResultWithEmpty() {
        return (ViewObjectImpl)findViewObject("HdResultWithEmpty");
    }

    /**
     * Container's getter for ReasonLevel1WithEmpty.
     * @return ReasonLevel1WithEmpty
     */
    public ViewObjectImpl getReasonLevel1WithEmpty() {
        return (ViewObjectImpl)findViewObject("ReasonLevel1WithEmpty");
    }

    /**
     * Container's getter for ReasonLevel2WithEmpty.
     * @return ReasonLevel2WithEmpty
     */
    public ViewObjectImpl getReasonLevel2WithEmpty() {
        return (ViewObjectImpl)findViewObject("ReasonLevel2WithEmpty");
    }

    /**
     * Container's getter for EmpWithEmptyForCaller.
     * @return EmpWithEmptyForCaller
     */
    public EmpWithEmptyImpl getEmpWithEmptyForCaller() {
        return (EmpWithEmptyImpl)findViewObject("EmpWithEmptyForCaller");
    }

    /**
     * Container's getter for EmpWithEmptyForCallee.
     * @return EmpWithEmptyForCallee
     */
    public EmpWithEmptyImpl getEmpWithEmptyForCallee() {
        return (EmpWithEmptyImpl)findViewObject("EmpWithEmptyForCallee");
    }

    /**
     * Container's getter for EmpHelpdeskCalleeViewLink.
     * @return EmpHelpdeskCalleeViewLink
     */
    public ViewLinkImpl getEmpHelpdeskCalleeViewLink() {
        return (ViewLinkImpl)findViewLink("EmpHelpdeskCalleeViewLink");
    }

    /**
     * Container's getter for EmpHelpdeskCallerViewLink.
     * @return EmpHelpdeskCallerViewLink
     */
    public ViewLinkImpl getEmpHelpdeskCallerViewLink() {
        return (ViewLinkImpl)findViewLink("EmpHelpdeskCallerViewLink");
    }

    /**
     * Container's getter for HdReasonLevel1ToLevel2Link1.
     * @return HdReasonLevel1ToLevel2Link1
     */
    public ViewLinkImpl getHdReasonLevel1ToLevel2Link1() {
        return (ViewLinkImpl)findViewLink("HdReasonLevel1ToLevel2Link1");
    }

    /**
     * Container's getter for HdReasonLevel2ToLevel3Link1.
     * @return HdReasonLevel2ToLevel3Link1
     */
    public ViewLinkImpl getHdReasonLevel2ToLevel3Link1() {
        return (ViewLinkImpl)findViewLink("HdReasonLevel2ToLevel3Link1");
    }

    /**
     * Container's getter for HdReasonL1ToL2EmptyLink1.
     * @return HdReasonL1ToL2EmptyLink1
     */
    public ViewLinkImpl getHdReasonL1ToL2EmptyLink1() {
        return (ViewLinkImpl)findViewLink("HdReasonL1ToL2EmptyLink1");
    }

    /**
     * Container's getter for HdEvalWithEmpty1.
     * @return HdEvalWithEmpty1
     */
    public ViewObjectImpl getHdEvalWithEmpty() {
        return (ViewObjectImpl)findViewObject("HdEvalWithEmpty");
    }

    /**
     * Container's getter for HelpdeskRolesView1.
     * @return HelpdeskRolesView1
     */
    public HelpdeskRolesViewImpl getHelpdeskRolesView() {
        return (HelpdeskRolesViewImpl)findViewObject("HelpdeskRolesView");
    }

    /**
     * Container's getter for LocationsWithEmpty1.
     * @return LocationsWithEmpty1
     */
    public ViewObjectImpl getLocationsWithEmpty() {
        return (ViewObjectImpl)findViewObject("LocationsWithEmpty");
    }
}

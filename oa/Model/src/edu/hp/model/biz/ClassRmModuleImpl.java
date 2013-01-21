package edu.hp.model.biz;

//import edu.hp.model.vo.ClassroomCalendarViewImpl;
import edu.hp.model.vo.ClsRmCalDMLViewImpl;
import edu.hp.model.vo.query.classrm.ClassroomCalendarConflitQueryImpl;
import edu.hp.model.vo.query.classrm.ClassroomQueryImpl;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jan 18 23:34:43 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ClassRmModuleImpl extends ApplicationModuleImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public ClassRmModuleImpl() {
    }
    
  

    /**
     * Container's getter for ClassroomBatchReservation.
     * @return ClassroomBatchReservation
     */
    public ViewObjectImpl getClassroomBatchReservation() {
        return (ViewObjectImpl)findViewObject("ClassroomBatchReservation");
    }


    /**
     * Container's getter for Classrooms.
     * @return Classrooms
     */
    public ClassroomQueryImpl getClassrooms() {
        return (ClassroomQueryImpl)findViewObject("Classrooms");
    }

    /**
     * Container's getter for Locations.
     * @return Locations
     */
    public ViewObjectImpl getLocations() {
        return (ViewObjectImpl)findViewObject("Locations");
    }

    /**
     * Container's getter for ClassroomOfLocation.
     * @return ClassroomOfLocation
     */
    public ViewObjectImpl getClassroomOfLocation() {
        return (ViewObjectImpl)findViewObject("ClassroomOfLocation");
    }

    /**
     * Container's getter for LocClsRmViewLink1.
     * @return LocClsRmViewLink1
     */
    public ViewLinkImpl getLocClsRmViewLink1() {
        return (ViewLinkImpl)findViewLink("LocClsRmViewLink1");
    }

    /**
     * Container's getter for ClassroomCalendarConflitQuery1.
     * @return ClassroomCalendarConflitQuery1
     */
    public ClassroomCalendarConflitQueryImpl getConflitQuery() {
        return (ClassroomCalendarConflitQueryImpl)findViewObject("ConflitQuery");
    }

    /**
     * Container's getter for ClsRmCalDMLView1.
     * @return ClsRmCalDMLView1
     */
    public ViewObjectImpl getClsRmCalDMLView() {
        return (ViewObjectImpl)findViewObject("ClsRmCalDMLView");
    }

    /**
     * Container's getter for ClassroomCalenderView1.
     * @return ClassroomCalenderView1
     */
    public ViewObjectImpl getCalendar() {
        return (ViewObjectImpl)findViewObject("Calendar");
    }
}

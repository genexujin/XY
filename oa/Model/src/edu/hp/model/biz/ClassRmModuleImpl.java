package edu.hp.model.biz;

import edu.hp.model.biz.common.ClassRmModule;
import edu.hp.model.vo.ClsRmBtchRsvtnViewImpl;
import edu.hp.model.vo.ClsRmCalDMLViewImpl;
import edu.hp.model.vo.query.classrm.ClassroomCalendarConflitQueryImpl;
import edu.hp.model.vo.query.classrm.ClassroomQueryImpl;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jan 18 23:34:43 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ClassRmModuleImpl extends ApplicationModuleImpl implements ClassRmModule {
    /**
     * This is the default constructor (do not remove).
     */
    public ClassRmModuleImpl() {
    }

    /**
     * 返回值表示该批量预订是否在创建相关预订时发现有冲突，true为有冲突，反之为无冲突
     * @return
     */
    public boolean saveBatchOrders() {

        boolean hasconflict = false;

        ClsRmBtchRsvtnViewImpl btchRsvtnView = this.getClsRmBtchRsvtnView();
        /* Validate Current Batch Input */
        Row currentRow = btchRsvtnView.getCurrentRow();
        currentRow.validate();


        /* Remove all classroom activity associated with this batch  */
        deleteRelatedClassroomActivities(currentRow);

        /*Create new Classroom Activities */
        boolean[] occurs = new boolean[14];
        occurs[0] = ((String)currentRow.getAttribute("FstWkSun")).equals("Y") ? true : false;
        occurs[1] = ((String)currentRow.getAttribute("FstWkMon")).equals("Y") ? true : false;
        occurs[2] = ((String)currentRow.getAttribute("FstWkTue")).equals("Y") ? true : false;
        occurs[3] = ((String)currentRow.getAttribute("FstWkWed")).equals("Y") ? true : false;
        occurs[4] = ((String)currentRow.getAttribute("FstWkThu")).equals("Y") ? true : false;
        occurs[5] = ((String)currentRow.getAttribute("FstWkFri")).equals("Y") ? true : false;
        occurs[6] = ((String)currentRow.getAttribute("FstWkSat")).equals("Y") ? true : false;
        occurs[7] = ((String)currentRow.getAttribute("SndWkSun")).equals("Y") ? true : false;
        occurs[8] = ((String)currentRow.getAttribute("SndWkMon")).equals("Y") ? true : false;
        occurs[9] = ((String)currentRow.getAttribute("SndWkTue")).equals("Y") ? true : false;
        occurs[10] = ((String)currentRow.getAttribute("SndWkWed")).equals("Y") ? true : false;
        occurs[11] = ((String)currentRow.getAttribute("SndWkThu")).equals("Y") ? true : false;
        occurs[12] = ((String)currentRow.getAttribute("SndWkFri")).equals("Y") ? true : false;
        occurs[13] = ((String)currentRow.getAttribute("SndWkSat")).equals("Y") ? true : false;


        //set the start and end time
        String allday = (String)currentRow.getAttribute("AllDay");
        Timestamp actEndTime = (Timestamp)currentRow.getAttribute("ActEndTime");
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTimeInMillis(actEndTime.getTime());
        Timestamp actStartTime = (Timestamp)currentRow.getAttribute("ActStartTime");
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTimeInMillis(actStartTime.getTime());
        // if allday then set the start time and end time
        if (allday != null && allday.equals("ALLDAY")) {

            if (actEndTime != null) {
                endCalendar.set(Calendar.HOUR_OF_DAY, 23);
                endCalendar.set(Calendar.MINUTE, 59);
                endCalendar.set(Calendar.SECOND, 59);
            }
            if (actStartTime != null) {
                startCalendar.set(Calendar.HOUR_OF_DAY, 0);
                startCalendar.set(Calendar.MINUTE, 0);
                startCalendar.set(Calendar.SECOND, 0);
            }
        }

        //what we need is only the time part
        int startHr = startCalendar.get(Calendar.HOUR_OF_DAY);
        int startMin = startCalendar.get(Calendar.MINUTE);
        int startSec = startCalendar.get(Calendar.SECOND);
        int endHr = endCalendar.get(Calendar.HOUR_OF_DAY);
        int endMin = endCalendar.get(Calendar.MINUTE);
        int endSec = endCalendar.get(Calendar.SECOND);

        //get other parts of reservation
        String batchNo = (String)currentRow.getAttribute("BatchNo");
        String title = (String)currentRow.getAttribute("ActTitle");
        String userId = (String)currentRow.getAttribute("UserId");
        String userDisplayName = (String)currentRow.getAttribute("UserDisplayName");
        BigDecimal numOfPeople = (BigDecimal)currentRow.getAttribute("NumOfPeople");
        String classroom = (String)currentRow.getAttribute("Classroom");
        String locationId = (String)currentRow.getAttribute("LocationId");
        String locationName = (String)currentRow.getAttribute("LocationName");
        String classroomId = (String)currentRow.getAttribute("ClassroomId");
        String allDay = (String)currentRow.getAttribute("AllDay");
        String comments = (String)currentRow.getAttribute("Comments");

        //get all days
        String recurType = (String)currentRow.getAttribute("RecurrenceType");
        int loopCap = recurType.equals("WEEKLY") ? 7 : 14;
        int offset = recurType.equals("WEEKLY") ? 0 : 7;

        //get start and end day
        Timestamp effDate = (Timestamp)currentRow.getAttribute("BatchEffectiveDate");
        Timestamp expireDate = (Timestamp)currentRow.getAttribute("BatchExpireDate");
        Calendar effStartCal = Calendar.getInstance();
        effStartCal.setTimeInMillis(effDate.getTime());
        Calendar expEndCal = Calendar.getInstance();
        expEndCal.setTimeInMillis(expireDate.getTime());

        //set new effective and expire date
        effStartCal.set(Calendar.DAY_OF_WEEK, 1);
        expEndCal.set(Calendar.DAY_OF_WEEK, 7);
        currentRow.setAttribute("BatchEffectiveDate", new Timestamp(effStartCal.getTime()));
        currentRow.setAttribute("BatchExpireDate", new Timestamp(expEndCal.getTime()));

        //get all occuring day
        int j = 0;
        ArrayList<Date> occurDays = new ArrayList<Date>();
        while (!(effStartCal.get(Calendar.DAY_OF_YEAR) == expEndCal.get(Calendar.DAY_OF_YEAR) &&
                 effStartCal.get(Calendar.YEAR) == expEndCal.get(Calendar.YEAR))) {

            int dayOfWeek = effStartCal.get(Calendar.DAY_OF_WEEK);
            if (j == loopCap)
                j = 0;
            if (j >= 7)
                dayOfWeek = dayOfWeek + offset;
            if (occurs[dayOfWeek - 1])
                occurDays.add(effStartCal.getTime());
            j++;
            effStartCal.add(Calendar.DAY_OF_YEAR, 1);
        }

        //create activities
        if (occurDays.size() > 0) {
            Calendar tempCal = Calendar.getInstance();
            ClassroomCalendarConflitQueryImpl calendarConflitQueryImpl = this.getConflitQuery();
            ClsRmCalDMLViewImpl calDMLViewImpl = this.getClsRmCalDMLView();

            for (Date date : occurDays) {
                Row newActivity = calDMLViewImpl.createRow();
                newActivity.setAttribute("BatchNo", batchNo);
                newActivity.setAttribute("ActTitle", title);
                newActivity.setAttribute("UserId", userId);
                newActivity.setAttribute("UserDisplayName", userDisplayName);
                newActivity.setAttribute("NumOfPeople", numOfPeople);
                newActivity.setAttribute("Classroom", classroom);
                newActivity.setAttribute("LocationId", locationId);
                newActivity.setAttribute("LocationName", locationName);
                newActivity.setAttribute("ClassroomId", classroomId);
                newActivity.setAttribute("AllDay", allDay);
                newActivity.setAttribute("Comments", comments);
                tempCal.setTime(date);
                tempCal.set(Calendar.HOUR_OF_DAY, startHr);
                tempCal.set(Calendar.MINUTE, startMin);
                tempCal.set(Calendar.SECOND, startSec);
                newActivity.setAttribute("ActStartTime", new Timestamp(tempCal.getTimeInMillis()));
                java.sql.Timestamp start = new java.sql.Timestamp(tempCal.getTimeInMillis());
                tempCal.set(Calendar.HOUR_OF_DAY, endHr);
                tempCal.set(Calendar.MINUTE, endMin);
                tempCal.set(Calendar.SECOND, endSec);
                newActivity.setAttribute("ActEndTime", new Timestamp(tempCal.getTimeInMillis()));
                java.sql.Timestamp end = new java.sql.Timestamp(tempCal.getTimeInMillis());
                calDMLViewImpl.insertRow(newActivity);
                if (!hasconflict) {
                    hasconflict = !calendarConflitQueryImpl.ifConflict(start, end, classroomId, "-1");
                }
            }
        }

        //update the sequence
        ViewObjectImpl batchSeqView = this.getClsRmBatchSeqView();
        batchSeqView.executeQuery();
        Row[] inRange = batchSeqView.getAllRowsInRange();
        String seq = (String)inRange[0].getAttribute("Value");
        int i = new Integer(seq).intValue();
        i++;
        inRange[0].setAttribute("Value", new Integer(i).toString());

        this.getDBTransaction().commit();

        return hasconflict;
    }

    public void deleteCurrentBatchOrder() {
        
        ClsRmBtchRsvtnViewImpl btchRsvtnView = this.getClsRmBtchRsvtnView();
        /* Validate Current Batch Input */
        Row currentRow = btchRsvtnView.getCurrentRow();
        if (currentRow != null) {
            /* Remove all classroom activity associated with this batch  */
            deleteRelatedClassroomActivities(currentRow);
            currentRow.remove();
            this.getDBTransaction().commit();
        }
    }

    /**
     * 删除和批量预订相关联的所有预订
     * @param currentRow
     */
    private void deleteRelatedClassroomActivities(Row currentRow) {
        ClsRmCalDMLViewImpl calDMLView = this.getClsRmCalDMLView();
        calDMLView.setApplyViewCriteriaNames(null);
        ViewCriteria criteria = calDMLView.getViewCriteria("findByBatchNo");
        calDMLView.applyViewCriteria(criteria);
        calDMLView.setRangeSize(-1);
        calDMLView.setbatchNo((String)currentRow.getAttribute("BatchNo"));
        calDMLView.executeQuery();
        Row[] allRowsInRange = calDMLView.getAllRowsInRange();
        if (allRowsInRange != null && allRowsInRange.length > 0) {
            for (Row row : allRowsInRange) {
                row.remove();
            }
        }
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
    public ClsRmCalDMLViewImpl getClsRmCalDMLView() {
        return (ClsRmCalDMLViewImpl)findViewObject("ClsRmCalDMLView");
    }

    /**
     * Container's getter for ClassroomCalenderView1.
     * @return ClassroomCalenderView1
     */
    public ViewObjectImpl getCalendar() {
        return (ViewObjectImpl)findViewObject("Calendar");
    }

    /**
     * Container's getter for ClsRmBtchRsvtnView1.
     * @return ClsRmBtchRsvtnView1
     */
    public ClsRmBtchRsvtnViewImpl getClsRmBtchRsvtnView() {
        return (ClsRmBtchRsvtnViewImpl)findViewObject("ClsRmBtchRsvtnView");
    }

    /**
     * Container's getter for LovsView1.
     * @return LovsView1
     */
    public ViewObjectImpl getClsRmBatchSeqView() {
        return (ViewObjectImpl)findViewObject("ClsRmBatchSeqView");
    }
}

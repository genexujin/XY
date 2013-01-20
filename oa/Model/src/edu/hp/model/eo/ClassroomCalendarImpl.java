package edu.hp.model.eo;

import edu.hp.model.common.BaseEntity;

import java.math.BigDecimal;

import java.util.Calendar;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSet;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Jan 19 15:50:24 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ClassroomCalendarImpl extends EntityImpl {
    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {

        if (this.getAllDay().equals("ALLDAY")) {

            Timestamp actEndTime = this.getActEndTime();
            if (actEndTime != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(actEndTime.getTime());
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                this.setActEndTime(new Timestamp(calendar.getTimeInMillis()));
            }

        }

        super.doDML(operation, e);
    }


//    /**
//     * Validation method for ClassroomCalendar.
//     */
//    public boolean validateTimeConflict() {
//
//        Timestamp actStartTime = this.getActStartTime();
//        Timestamp actEndTime = this.getActEndTime();
//        String clsRmId = this.getClassroomId();
//        try {
//            
//            RowSet calendarView = this.getClassroomCalendarConflitQuery1();
//            calendarView.ensureVariableManager().setVariableValue("endQueryTime", actEndTime);
//            calendarView.ensureVariableManager().setVariableValue("startQueryTime", actStartTime);
//            calendarView.ensureVariableManager().setVariableValue("clsRoomId", clsRmId);
//            calendarView.setRangeSize(-1);
//            calendarView.executeQuery();
//
//            if (calendarView.first() !=null){
//                return false;                
//            }
//            
//        } catch (Exception e) {
//            // TODO: Add catch code
//            e.printStackTrace();
//            return false;
//        }
//        
//        return true;
//    }

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        Id {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getId();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setId((DBSequence)value);
            }
        }
        ,
        ActTitle {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getActTitle();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setActTitle((String)value);
            }
        }
        ,
        ActStartTime {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getActStartTime();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setActStartTime((Timestamp)value);
            }
        }
        ,
        ActEndTime {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getActEndTime();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setActEndTime((Timestamp)value);
            }
        }
        ,
        UserId {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getUserId();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setUserId((String)value);
            }
        }
        ,
        UserDisplayName {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getUserDisplayName();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setUserDisplayName((String)value);
            }
        }
        ,
        NumOfPeople {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getNumOfPeople();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setNumOfPeople((BigDecimal)value);
            }
        }
        ,
        Classroom {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getClassroom();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setClassroom((String)value);
            }
        }
        ,
        Comments {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getComments();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setComments((String)value);
            }
        }
        ,
        CreatedAt {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getCreatedAt();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        BatchNo {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getBatchNo();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setBatchNo((String)value);
            }
        }
        ,
        BatchId {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getBatchId();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setBatchId((String)value);
            }
        }
        ,
        LocationName {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getLocationName();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setLocationName((String)value);
            }
        }
        ,
        LocationId {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getLocationId();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setLocationId((String)value);
            }
        }
        ,
        ClassroomId {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getClassroomId();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setClassroomId((String)value);
            }
        }
        ,
        AllDay {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getAllDay();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setAllDay((String)value);
            }
        }
        ,
        ClassroomBatchReservation {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getClassroomBatchReservation();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setClassroomBatchReservation((EntityImpl)value);
            }
        }
        ,
        ClassroomCalendarConflitQuery1 {
            public Object get(ClassroomCalendarImpl obj) {
                return obj.getClassroomCalendarConflitQuery1();
            }

            public void put(ClassroomCalendarImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(ClassroomCalendarImpl object);

        public abstract void put(ClassroomCalendarImpl object, Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int ID = AttributesEnum.Id.index();
    public static final int ACTTITLE = AttributesEnum.ActTitle.index();
    public static final int ACTSTARTTIME = AttributesEnum.ActStartTime.index();
    public static final int ACTENDTIME = AttributesEnum.ActEndTime.index();
    public static final int USERID = AttributesEnum.UserId.index();
    public static final int USERDISPLAYNAME = AttributesEnum.UserDisplayName.index();
    public static final int NUMOFPEOPLE = AttributesEnum.NumOfPeople.index();
    public static final int CLASSROOM = AttributesEnum.Classroom.index();
    public static final int COMMENTS = AttributesEnum.Comments.index();
    public static final int CREATEDAT = AttributesEnum.CreatedAt.index();
    public static final int BATCHNO = AttributesEnum.BatchNo.index();
    public static final int BATCHID = AttributesEnum.BatchId.index();
    public static final int LOCATIONNAME = AttributesEnum.LocationName.index();
    public static final int LOCATIONID = AttributesEnum.LocationId.index();
    public static final int CLASSROOMID = AttributesEnum.ClassroomId.index();
    public static final int ALLDAY = AttributesEnum.AllDay.index();
    public static final int CLASSROOMBATCHRESERVATION = AttributesEnum.ClassroomBatchReservation.index();
    public static final int CLASSROOMCALENDARCONFLITQUERY1 = AttributesEnum.ClassroomCalendarConflitQuery1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ClassroomCalendarImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("edu.hp.model.eo.ClassroomCalendar");
    }

    /**
     * Gets the attribute value for Id, using the alias name Id.
     * @return the value of Id
     */
    public DBSequence getId() {
        return (DBSequence)getAttributeInternal(ID);
    }

    /**
     * Sets <code>value</code> as the attribute value for Id.
     * @param value value to set the Id
     */
    public void setId(DBSequence value) {
        setAttributeInternal(ID, value);
    }

    /**
     * Gets the attribute value for ActTitle, using the alias name ActTitle.
     * @return the value of ActTitle
     */
    public String getActTitle() {
        return (String)getAttributeInternal(ACTTITLE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ActTitle.
     * @param value value to set the ActTitle
     */
    public void setActTitle(String value) {
        setAttributeInternal(ACTTITLE, value);
    }

    /**
     * Gets the attribute value for ActStartTime, using the alias name ActStartTime.
     * @return the value of ActStartTime
     */
    public Timestamp getActStartTime() {
        return (Timestamp)getAttributeInternal(ACTSTARTTIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for ActStartTime.
     * @param value value to set the ActStartTime
     */
    public void setActStartTime(Timestamp value) {
        setAttributeInternal(ACTSTARTTIME, value);
    }

    /**
     * Gets the attribute value for ActEndTime, using the alias name ActEndTime.
     * @return the value of ActEndTime
     */
    public Timestamp getActEndTime() {
        return (Timestamp)getAttributeInternal(ACTENDTIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for ActEndTime.
     * @param value value to set the ActEndTime
     */
    public void setActEndTime(Timestamp value) {
        setAttributeInternal(ACTENDTIME, value);
    }

    /**
     * Gets the attribute value for UserId, using the alias name UserId.
     * @return the value of UserId
     */
    public String getUserId() {
        return (String)getAttributeInternal(USERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for UserId.
     * @param value value to set the UserId
     */
    public void setUserId(String value) {
        setAttributeInternal(USERID, value);
    }

    /**
     * Gets the attribute value for UserDisplayName, using the alias name UserDisplayName.
     * @return the value of UserDisplayName
     */
    public String getUserDisplayName() {
        return (String)getAttributeInternal(USERDISPLAYNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for UserDisplayName.
     * @param value value to set the UserDisplayName
     */
    public void setUserDisplayName(String value) {
        setAttributeInternal(USERDISPLAYNAME, value);
    }

    /**
     * Gets the attribute value for NumOfPeople, using the alias name NumOfPeople.
     * @return the value of NumOfPeople
     */
    public BigDecimal getNumOfPeople() {
        return (BigDecimal)getAttributeInternal(NUMOFPEOPLE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NumOfPeople.
     * @param value value to set the NumOfPeople
     */
    public void setNumOfPeople(BigDecimal value) {
        setAttributeInternal(NUMOFPEOPLE, value);
    }

    /**
     * Gets the attribute value for Classroom, using the alias name Classroom.
     * @return the value of Classroom
     */
    public String getClassroom() {
        return (String)getAttributeInternal(CLASSROOM);
    }

    /**
     * Sets <code>value</code> as the attribute value for Classroom.
     * @param value value to set the Classroom
     */
    public void setClassroom(String value) {
        setAttributeInternal(CLASSROOM, value);
    }

    /**
     * Gets the attribute value for Comments, using the alias name Comments.
     * @return the value of Comments
     */
    public String getComments() {
        return (String)getAttributeInternal(COMMENTS);
    }

    /**
     * Sets <code>value</code> as the attribute value for Comments.
     * @param value value to set the Comments
     */
    public void setComments(String value) {
        setAttributeInternal(COMMENTS, value);
    }

    /**
     * Gets the attribute value for CreatedAt, using the alias name CreatedAt.
     * @return the value of CreatedAt
     */
    public Timestamp getCreatedAt() {
        return (Timestamp)getAttributeInternal(CREATEDAT);
    }

    /**
     * Gets the attribute value for BatchNo, using the alias name BatchNo.
     * @return the value of BatchNo
     */
    public String getBatchNo() {
        return (String)getAttributeInternal(BATCHNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for BatchNo.
     * @param value value to set the BatchNo
     */
    public void setBatchNo(String value) {
        setAttributeInternal(BATCHNO, value);
    }

    /**
     * Gets the attribute value for BatchId, using the alias name BatchId.
     * @return the value of BatchId
     */
    public String getBatchId() {
        return (String)getAttributeInternal(BATCHID);
    }

    /**
     * Sets <code>value</code> as the attribute value for BatchId.
     * @param value value to set the BatchId
     */
    public void setBatchId(String value) {
        setAttributeInternal(BATCHID, value);
    }

    /**
     * Gets the attribute value for LocationName, using the alias name LocationName.
     * @return the value of LocationName
     */
    public String getLocationName() {
        return (String)getAttributeInternal(LOCATIONNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for LocationName.
     * @param value value to set the LocationName
     */
    public void setLocationName(String value) {
        setAttributeInternal(LOCATIONNAME, value);
    }

    /**
     * Gets the attribute value for LocationId, using the alias name LocationId.
     * @return the value of LocationId
     */
    public String getLocationId() {
        return (String)getAttributeInternal(LOCATIONID);
    }

    /**
     * Sets <code>value</code> as the attribute value for LocationId.
     * @param value value to set the LocationId
     */
    public void setLocationId(String value) {
        setAttributeInternal(LOCATIONID, value);
    }

    /**
     * Gets the attribute value for ClassroomId, using the alias name ClassroomId.
     * @return the value of ClassroomId
     */
    public String getClassroomId() {
        return (String)getAttributeInternal(CLASSROOMID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ClassroomId.
     * @param value value to set the ClassroomId
     */
    public void setClassroomId(String value) {
        setAttributeInternal(CLASSROOMID, value);
    }

    /**
     * Gets the attribute value for AllDay, using the alias name AllDay.
     * @return the value of AllDay
     */
    public String getAllDay() {
        return (String)getAttributeInternal(ALLDAY);
    }

    /**
     * Sets <code>value</code> as the attribute value for AllDay.
     * @param value value to set the AllDay
     */
    public void setAllDay(String value) {
        setAttributeInternal(ALLDAY, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getClassroomBatchReservation() {
        return (EntityImpl)getAttributeInternal(CLASSROOMBATCHRESERVATION);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setClassroomBatchReservation(EntityImpl value) {
        setAttributeInternal(CLASSROOMBATCHRESERVATION, value);
    }

    /**
     * Gets the view accessor <code>RowSet</code> ClassroomCalendarConflitQuery1.
     */
    public RowSet getClassroomCalendarConflitQuery1() {
        return (RowSet)getAttributeInternal(CLASSROOMCALENDARCONFLITQUERY1);
    }

    /**
     * @param id key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(DBSequence id) {
        return new Key(new Object[]{id});
    }


}

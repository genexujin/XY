package edu.hp.model.eo;

import oracle.jbo.Key;
import oracle.jbo.RowInconsistentException;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Feb 27 21:21:43 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class HelpdeskCallsImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        CallId {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCallId();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setCallId((DBSequence)value);
            }
        }
        ,
        CallReadableId {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCallReadableId();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setCallReadableId((String)value);
            }
        }
        ,
        CallerId {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCallerId();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setCallerId((String)value);
            }
        }
        ,
        CalleeId {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCalleeId();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setCalleeId((String)value);
            }
        }
        ,
        LocationId {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getLocationId();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setLocationId((String)value);
            }
        }
        ,
        LocationDetail {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getLocationDetail();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setLocationDetail((String)value);
            }
        }
        ,
        CreateBy {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCreateBy();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setCreateBy((String)value);
            }
        }
        ,
        CreateAt {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCreateAt();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setLastUpdatedBy((String)value);
            }
        }
        ,
        LastUpdatedAt {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getLastUpdatedAt();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        State {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getState();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setState((String)value);
            }
        }
        ,
        CallResult {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCallResult();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setCallResult((String)value);
            }
        }
        ,
        CallResultDetail {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCallResultDetail();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setCallResultDetail((String)value);
            }
        }
        ,
        CallEval {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCallEval();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setCallEval((String)value);
            }
        }
        ,
        CallEvalDetail {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCallEvalDetail();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setCallEvalDetail((String)value);
            }
        }
        ,
        ReasonLevel1 {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getReasonLevel1();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setReasonLevel1((String)value);
            }
        }
        ,
        ReasonLevel2 {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getReasonLevel2();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setReasonLevel2((String)value);
            }
        }
        ,
        ReasonLevel3 {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getReasonLevel3();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setReasonLevel3((String)value);
            }
        }
        ,
        ReasonDetail {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getReasonDetail();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setReasonDetail((String)value);
            }
        }
        ,
        SubmitAt {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getSubmitAt();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setSubmitAt((Timestamp)value);
            }
        }
        ,
        CalleeDisplayName {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCalleeDisplayName();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setCalleeDisplayName((String)value);
            }
        }
        ,
        BelongToDept {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getBelongToDept();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setBelongToDept((String)value);
            }
        }
        ,
        AffairReviewNote {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getAffairReviewNote();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setAffairReviewNote((String)value);
            }
        }
        ,
        Callee {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCallee();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setCallee((EmployeesImpl)value);
            }
        }
        ,
        Caller {
            public Object get(HelpdeskCallsImpl obj) {
                return obj.getCaller();
            }

            public void put(HelpdeskCallsImpl obj, Object value) {
                obj.setCaller((EmployeesImpl)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(HelpdeskCallsImpl object);

        public abstract void put(HelpdeskCallsImpl object, Object value);

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


    public static final int CALLID = AttributesEnum.CallId.index();
    public static final int CALLREADABLEID = AttributesEnum.CallReadableId.index();
    public static final int CALLERID = AttributesEnum.CallerId.index();
    public static final int CALLEEID = AttributesEnum.CalleeId.index();
    public static final int LOCATIONID = AttributesEnum.LocationId.index();
    public static final int LOCATIONDETAIL = AttributesEnum.LocationDetail.index();
    public static final int CREATEBY = AttributesEnum.CreateBy.index();
    public static final int CREATEAT = AttributesEnum.CreateAt.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATEDAT = AttributesEnum.LastUpdatedAt.index();
    public static final int STATE = AttributesEnum.State.index();
    public static final int CALLRESULT = AttributesEnum.CallResult.index();
    public static final int CALLRESULTDETAIL = AttributesEnum.CallResultDetail.index();
    public static final int CALLEVAL = AttributesEnum.CallEval.index();
    public static final int CALLEVALDETAIL = AttributesEnum.CallEvalDetail.index();
    public static final int REASONLEVEL1 = AttributesEnum.ReasonLevel1.index();
    public static final int REASONLEVEL2 = AttributesEnum.ReasonLevel2.index();
    public static final int REASONLEVEL3 = AttributesEnum.ReasonLevel3.index();
    public static final int REASONDETAIL = AttributesEnum.ReasonDetail.index();
    public static final int SUBMITAT = AttributesEnum.SubmitAt.index();
    public static final int CALLEEDISPLAYNAME = AttributesEnum.CalleeDisplayName.index();
    public static final int BELONGTODEPT = AttributesEnum.BelongToDept.index();
    public static final int AFFAIRREVIEWNOTE = AttributesEnum.AffairReviewNote.index();
    public static final int CALLEE = AttributesEnum.Callee.index();
    public static final int CALLER = AttributesEnum.Caller.index();

    /**
     * This is the default constructor (do not remove).
     */
    public HelpdeskCallsImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("edu.hp.model.eo.HelpdeskCalls");
    }

    /**
     * Gets the attribute value for CallId, using the alias name CallId.
     * @return the value of CallId
     */
    public DBSequence getCallId() {
        return (DBSequence)getAttributeInternal(CALLID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CallId.
     * @param value value to set the CallId
     */
    public void setCallId(DBSequence value) {
        setAttributeInternal(CALLID, value);
    }

    /**
     * Gets the attribute value for CallReadableId, using the alias name CallReadableId.
     * @return the value of CallReadableId
     */
    public String getCallReadableId() {
        return (String)getAttributeInternal(CALLREADABLEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CallReadableId.
     * @param value value to set the CallReadableId
     */
    public void setCallReadableId(String value) {
        setAttributeInternal(CALLREADABLEID, value);
    }

    /**
     * Gets the attribute value for CallerId, using the alias name CallerId.
     * @return the value of CallerId
     */
    public String getCallerId() {
        return (String)getAttributeInternal(CALLERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CallerId.
     * @param value value to set the CallerId
     */
    public void setCallerId(String value) {
        setAttributeInternal(CALLERID, value);
    }

    /**
     * Gets the attribute value for CalleeId, using the alias name CalleeId.
     * @return the value of CalleeId
     */
    public String getCalleeId() {
        return (String)getAttributeInternal(CALLEEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CalleeId.
     * @param value value to set the CalleeId
     */
    public void setCalleeId(String value) {
        setAttributeInternal(CALLEEID, value);
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
     * Gets the attribute value for LocationDetail, using the alias name LocationDetail.
     * @return the value of LocationDetail
     */
    public String getLocationDetail() {
        return (String)getAttributeInternal(LOCATIONDETAIL);
    }

    /**
     * Sets <code>value</code> as the attribute value for LocationDetail.
     * @param value value to set the LocationDetail
     */
    public void setLocationDetail(String value) {
        setAttributeInternal(LOCATIONDETAIL, value);
    }

    /**
     * Gets the attribute value for CreateBy, using the alias name CreateBy.
     * @return the value of CreateBy
     */
    public String getCreateBy() {
        return (String)getAttributeInternal(CREATEBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreateBy.
     * @param value value to set the CreateBy
     */
    public void setCreateBy(String value) {
        setAttributeInternal(CREATEBY, value);
    }

    /**
     * Gets the attribute value for CreateAt, using the alias name CreateAt.
     * @return the value of CreateAt
     */
    public Timestamp getCreateAt() {
        return (Timestamp)getAttributeInternal(CREATEAT);
    }

    /**
     * Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy.
     * @return the value of LastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return (String)getAttributeInternal(LASTUPDATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdatedBy.
     * @param value value to set the LastUpdatedBy
     */
    public void setLastUpdatedBy(String value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**
     * Gets the attribute value for LastUpdatedAt, using the alias name LastUpdatedAt.
     * @return the value of LastUpdatedAt
     */
    public Timestamp getLastUpdatedAt() {
        return (Timestamp)getAttributeInternal(LASTUPDATEDAT);
    }

    /**
     * Gets the attribute value for State, using the alias name State.
     * @return the value of State
     */
    public String getState() {
        return (String)getAttributeInternal(STATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for State.
     * @param value value to set the State
     */
    public void setState(String value) {
        setAttributeInternal(STATE, value);
    }

    /**
     * Gets the attribute value for CallResult, using the alias name CallResult.
     * @return the value of CallResult
     */
    public String getCallResult() {
        return (String)getAttributeInternal(CALLRESULT);
    }

    /**
     * Sets <code>value</code> as the attribute value for CallResult.
     * @param value value to set the CallResult
     */
    public void setCallResult(String value) {
        setAttributeInternal(CALLRESULT, value);
    }

    /**
     * Gets the attribute value for CallResultDetail, using the alias name CallResultDetail.
     * @return the value of CallResultDetail
     */
    public String getCallResultDetail() {
        return (String)getAttributeInternal(CALLRESULTDETAIL);
    }

    /**
     * Sets <code>value</code> as the attribute value for CallResultDetail.
     * @param value value to set the CallResultDetail
     */
    public void setCallResultDetail(String value) {
        setAttributeInternal(CALLRESULTDETAIL, value);
    }

    /**
     * Gets the attribute value for CallEval, using the alias name CallEval.
     * @return the value of CallEval
     */
    public String getCallEval() {
        return (String)getAttributeInternal(CALLEVAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for CallEval.
     * @param value value to set the CallEval
     */
    public void setCallEval(String value) {
        setAttributeInternal(CALLEVAL, value);
    }

    /**
     * Gets the attribute value for CallEvalDetail, using the alias name CallEvalDetail.
     * @return the value of CallEvalDetail
     */
    public String getCallEvalDetail() {
        return (String)getAttributeInternal(CALLEVALDETAIL);
    }

    /**
     * Sets <code>value</code> as the attribute value for CallEvalDetail.
     * @param value value to set the CallEvalDetail
     */
    public void setCallEvalDetail(String value) {
        setAttributeInternal(CALLEVALDETAIL, value);
    }

    /**
     * Gets the attribute value for ReasonLevel1, using the alias name ReasonLevel1.
     * @return the value of ReasonLevel1
     */
    public String getReasonLevel1() {
        return (String)getAttributeInternal(REASONLEVEL1);
    }

    /**
     * Sets <code>value</code> as the attribute value for ReasonLevel1.
     * @param value value to set the ReasonLevel1
     */
    public void setReasonLevel1(String value) {
        setAttributeInternal(REASONLEVEL1, value);
    }

    /**
     * Gets the attribute value for ReasonLevel2, using the alias name ReasonLevel2.
     * @return the value of ReasonLevel2
     */
    public String getReasonLevel2() {
        return (String)getAttributeInternal(REASONLEVEL2);
    }

    /**
     * Sets <code>value</code> as the attribute value for ReasonLevel2.
     * @param value value to set the ReasonLevel2
     */
    public void setReasonLevel2(String value) {
        setAttributeInternal(REASONLEVEL2, value);
    }

    /**
     * Gets the attribute value for ReasonLevel3, using the alias name ReasonLevel3.
     * @return the value of ReasonLevel3
     */
    public String getReasonLevel3() {
        return (String)getAttributeInternal(REASONLEVEL3);
    }

    /**
     * Sets <code>value</code> as the attribute value for ReasonLevel3.
     * @param value value to set the ReasonLevel3
     */
    public void setReasonLevel3(String value) {
        setAttributeInternal(REASONLEVEL3, value);
    }

    /**
     * Gets the attribute value for ReasonDetail, using the alias name ReasonDetail.
     * @return the value of ReasonDetail
     */
    public String getReasonDetail() {
        return (String)getAttributeInternal(REASONDETAIL);
    }

    /**
     * Sets <code>value</code> as the attribute value for ReasonDetail.
     * @param value value to set the ReasonDetail
     */
    public void setReasonDetail(String value) {
        setAttributeInternal(REASONDETAIL, value);
    }

    /**
     * Gets the attribute value for SubmitAt, using the alias name SubmitAt.
     * @return the value of SubmitAt
     */
    public Timestamp getSubmitAt() {
        return (Timestamp)getAttributeInternal(SUBMITAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for SubmitAt.
     * @param value value to set the SubmitAt
     */
    public void setSubmitAt(Timestamp value) {
        setAttributeInternal(SUBMITAT, value);
    }

    /**
     * Gets the attribute value for CalleeDisplayName, using the alias name CalleeDisplayName.
     * @return the value of CalleeDisplayName
     */
    public String getCalleeDisplayName() {
        return (String)getAttributeInternal(CALLEEDISPLAYNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for CalleeDisplayName.
     * @param value value to set the CalleeDisplayName
     */
    public void setCalleeDisplayName(String value) {
        setAttributeInternal(CALLEEDISPLAYNAME, value);
    }

    /**
     * Gets the attribute value for BelongToDept, using the alias name BelongToDept.
     * @return the value of BelongToDept
     */
    public String getBelongToDept() {
        return (String)getAttributeInternal(BELONGTODEPT);
    }

    /**
     * Sets <code>value</code> as the attribute value for BelongToDept.
     * @param value value to set the BelongToDept
     */
    public void setBelongToDept(String value) {
        setAttributeInternal(BELONGTODEPT, value);
    }

    /**
     * Gets the attribute value for AffairReviewNote, using the alias name AffairReviewNote.
     * @return the value of AffairReviewNote
     */
    public String getAffairReviewNote() {
        return (String)getAttributeInternal(AFFAIRREVIEWNOTE);
    }

    /**
     * Sets <code>value</code> as the attribute value for AffairReviewNote.
     * @param value value to set the AffairReviewNote
     */
    public void setAffairReviewNote(String value) {
        setAttributeInternal(AFFAIRREVIEWNOTE, value);
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
     * @return the associated entity EmployeesImpl.
     */
    public EmployeesImpl getCallee() {
        return (EmployeesImpl)getAttributeInternal(CALLEE);
    }

    /**
     * Sets <code>value</code> as the associated entity EmployeesImpl.
     */
    public void setCallee(EmployeesImpl value) {
        setAttributeInternal(CALLEE, value);
    }

    /**
     * @return the associated entity EmployeesImpl.
     */
    public EmployeesImpl getCaller() {
        return (EmployeesImpl)getAttributeInternal(CALLER);
    }

    /**
     * Sets <code>value</code> as the associated entity EmployeesImpl.
     */
    public void setCaller(EmployeesImpl value) {
        setAttributeInternal(CALLER, value);
    }


    /**
     * @param callId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(DBSequence callId) {
        return new Key(new Object[]{callId});
    }

    public void lock() {
        try { 
            super.lock(); 
        } catch (RowInconsistentException e) { 
            e.printStackTrace(); 
            refresh(REFRESH_WITH_DB_ONLY_IF_UNCHANGED | REFRESH_CONTAINEES);
            System.out.println("已被处理的异常信息："+new java.util.Date().toLocaleString()+" 更新时出现锁异常！");
            super.lock(); 
        } 
    }
    
}

package edu.hp.model.eo;

import edu.hp.model.common.BaseEntity;

import oracle.jbo.Key;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Feb 08 13:29:22 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DepartmentsImpl extends BaseEntity {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        DeptId {
            public Object get(DepartmentsImpl obj) {
                return obj.getDeptId();
            }

            public void put(DepartmentsImpl obj, Object value) {
                obj.setDeptId((DBSequence)value);
            }
        }
        ,
        DeptName {
            public Object get(DepartmentsImpl obj) {
                return obj.getDeptName();
            }

            public void put(DepartmentsImpl obj, Object value) {
                obj.setDeptName((String)value);
            }
        }
        ,
        LocationId {
            public Object get(DepartmentsImpl obj) {
                return obj.getLocationId();
            }

            public void put(DepartmentsImpl obj, Object value) {
                obj.setLocationId((String)value);
            }
        }
        ,
        MgrId {
            public Object get(DepartmentsImpl obj) {
                return obj.getMgrId();
            }

            public void put(DepartmentsImpl obj, Object value) {
                obj.setMgrId((String)value);
            }
        }
        ,
        CreatedAt {
            public Object get(DepartmentsImpl obj) {
                return obj.getCreatedAt();
            }

            public void put(DepartmentsImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        CreatedBy {
            public Object get(DepartmentsImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(DepartmentsImpl obj, Object value) {
                obj.setCreatedBy((String)value);
            }
        }
        ,
        LastUpdatedAt {
            public Object get(DepartmentsImpl obj) {
                return obj.getLastUpdatedAt();
            }

            public void put(DepartmentsImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(DepartmentsImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(DepartmentsImpl obj, Object value) {
                obj.setLastUpdatedBy((String)value);
            }
        }
        ,
        MgrName {
            public Object get(DepartmentsImpl obj) {
                return obj.getMgrName();
            }

            public void put(DepartmentsImpl obj, Object value) {
                obj.setMgrName((String)value);
            }
        }
        ,
        Expired {
            public Object get(DepartmentsImpl obj) {
                return obj.getExpired();
            }

            public void put(DepartmentsImpl obj, Object value) {
                obj.setExpired((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(DepartmentsImpl object);

        public abstract void put(DepartmentsImpl object, Object value);

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


    public static final int DEPTID = AttributesEnum.DeptId.index();
    public static final int DEPTNAME = AttributesEnum.DeptName.index();
    public static final int LOCATIONID = AttributesEnum.LocationId.index();
    public static final int MGRID = AttributesEnum.MgrId.index();
    public static final int CREATEDAT = AttributesEnum.CreatedAt.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int LASTUPDATEDAT = AttributesEnum.LastUpdatedAt.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int MGRNAME = AttributesEnum.MgrName.index();
    public static final int EXPIRED = AttributesEnum.Expired.index();

    /**
     * This is the default constructor (do not remove).
     */
    public DepartmentsImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("edu.hp.model.eo.Departments");
    }

    /**
     * Gets the attribute value for DeptId, using the alias name DeptId.
     * @return the value of DeptId
     */
    public DBSequence getDeptId() {
        return (DBSequence)getAttributeInternal(DEPTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for DeptId.
     * @param value value to set the DeptId
     */
    public void setDeptId(DBSequence value) {
        setAttributeInternal(DEPTID, value);
    }

    /**
     * Gets the attribute value for DeptName, using the alias name DeptName.
     * @return the value of DeptName
     */
    public String getDeptName() {
        return (String)getAttributeInternal(DEPTNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for DeptName.
     * @param value value to set the DeptName
     */
    public void setDeptName(String value) {
        setAttributeInternal(DEPTNAME, value);
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
     * Gets the attribute value for MgrId, using the alias name MgrId.
     * @return the value of MgrId
     */
    public String getMgrId() {
        return (String)getAttributeInternal(MGRID);
    }

    /**
     * Sets <code>value</code> as the attribute value for MgrId.
     * @param value value to set the MgrId
     */
    public void setMgrId(String value) {
        setAttributeInternal(MGRID, value);
    }

    /**
     * Gets the attribute value for CreatedAt, using the alias name CreatedAt.
     * @return the value of CreatedAt
     */
    public Timestamp getCreatedAt() {
        return (Timestamp)getAttributeInternal(CREATEDAT);
    }

    /**
     * Gets the attribute value for CreatedBy, using the alias name CreatedBy.
     * @return the value of CreatedBy
     */
    public String getCreatedBy() {
        return (String)getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreatedBy.
     * @param value value to set the CreatedBy
     */
    public void setCreatedBy(String value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for LastUpdatedAt, using the alias name LastUpdatedAt.
     * @return the value of LastUpdatedAt
     */
    public Timestamp getLastUpdatedAt() {
        return (Timestamp)getAttributeInternal(LASTUPDATEDAT);
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
     * Gets the attribute value for MgrName, using the alias name MgrName.
     * @return the value of MgrName
     */
    public String getMgrName() {
        return (String)getAttributeInternal(MGRNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for MgrName.
     * @param value value to set the MgrName
     */
    public void setMgrName(String value) {
        setAttributeInternal(MGRNAME, value);
    }

    /**
     * Gets the attribute value for Expired, using the alias name Expired.
     * @return the value of Expired
     */
    public String getExpired() {
        return (String)getAttributeInternal(EXPIRED);
    }

    /**
     * Sets <code>value</code> as the attribute value for Expired.
     * @param value value to set the Expired
     */
    public void setExpired(String value) {
        setAttributeInternal(EXPIRED, value);
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
     * @param deptId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(DBSequence deptId) {
        return new Key(new Object[]{deptId});
    }


}
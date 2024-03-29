package edu.hp.model.eo;

import edu.hp.model.common.BaseEntity;

import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jan 15 21:01:28 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EmployeesImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        UserName {
            public Object get(EmployeesImpl obj) {
                return obj.getUserName();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setUserName((String)value);
            }
        }
        ,
        Password {
            public Object get(EmployeesImpl obj) {
                return obj.getPassword();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setPassword((String)value);
            }
        }
        ,
        FirstName {
            public Object get(EmployeesImpl obj) {
                return obj.getFirstName();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setFirstName((String)value);
            }
        }
        ,
        LastName {
            public Object get(EmployeesImpl obj) {
                return obj.getLastName();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setLastName((String)value);
            }
        }
        ,
        Gender {
            public Object get(EmployeesImpl obj) {
                return obj.getGender();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setGender((String)value);
            }
        }
        ,
        MgrId {
            public Object get(EmployeesImpl obj) {
                return obj.getMgrId();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setMgrId((String)value);
            }
        }
        ,
        DeptId {
            public Object get(EmployeesImpl obj) {
                return obj.getDeptId();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setDeptId((String)value);
            }
        }
        ,
        Email {
            public Object get(EmployeesImpl obj) {
                return obj.getEmail();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setEmail((String)value);
            }
        }
        ,
        Mobile {
            public Object get(EmployeesImpl obj) {
                return obj.getMobile();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setMobile((String)value);
            }
        }
        ,
        OfficePhone {
            public Object get(EmployeesImpl obj) {
                return obj.getOfficePhone();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setOfficePhone((String)value);
            }
        }
        ,
        HomePhone {
            public Object get(EmployeesImpl obj) {
                return obj.getHomePhone();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setHomePhone((String)value);
            }
        }
        ,
        Fax {
            public Object get(EmployeesImpl obj) {
                return obj.getFax();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setFax((String)value);
            }
        }
        ,
        LocationId {
            public Object get(EmployeesImpl obj) {
                return obj.getLocationId();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setLocationId((String)value);
            }
        }
        ,
        HomeAddress {
            public Object get(EmployeesImpl obj) {
                return obj.getHomeAddress();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setHomeAddress((String)value);
            }
        }
        ,
        OfficeAddress {
            public Object get(EmployeesImpl obj) {
                return obj.getOfficeAddress();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setOfficeAddress((String)value);
            }
        }
        ,
        Photo {
            public Object get(EmployeesImpl obj) {
                return obj.getPhoto();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setPhoto((BlobDomain)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(EmployeesImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setLastUpdatedBy((String)value);
            }
        }
        ,
        LastUpdatedAt {
            public Object get(EmployeesImpl obj) {
                return obj.getLastUpdatedAt();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        CreatedBy {
            public Object get(EmployeesImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setCreatedBy((String)value);
            }
        }
        ,
        CreatedAt {
            public Object get(EmployeesImpl obj) {
                return obj.getCreatedAt();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Title {
            public Object get(EmployeesImpl obj) {
                return obj.getTitle();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setTitle((String)value);
            }
        }
        ,
        Id {
            public Object get(EmployeesImpl obj) {
                return obj.getId();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setId((DBSequence)value);
            }
        }
        ,
        MgrName {
            public Object get(EmployeesImpl obj) {
                return obj.getMgrName();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setMgrName((String)value);
            }
        }
        ,
        DeptName {
            public Object get(EmployeesImpl obj) {
                return obj.getDeptName();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setDeptName((String)value);
            }
        }
        ,
        Expired {
            public Object get(EmployeesImpl obj) {
                return obj.getExpired();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setExpired((String)value);
            }
        }
        ,
        MgrEmployee {
            public Object get(EmployeesImpl obj) {
                return obj.getMgrEmployee();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        SubEmployees {
            public Object get(EmployeesImpl obj) {
                return obj.getSubEmployees();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setSubEmployees((EmployeesImpl)value);
            }
        }
        ,
        Roles {
            public Object get(EmployeesImpl obj) {
                return obj.getRoles();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        RepairCallsForCallee {
            public Object get(EmployeesImpl obj) {
                return obj.getRepairCallsForCallee();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        RepairCallsForCaller {
            public Object get(EmployeesImpl obj) {
                return obj.getRepairCallsForCaller();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        HelpdeskCallsForCallee {
            public Object get(EmployeesImpl obj) {
                return obj.getHelpdeskCallsForCallee();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        HelpdeskCallsForCaller {
            public Object get(EmployeesImpl obj) {
                return obj.getHelpdeskCallsForCaller();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        RoleUsers {
            public Object get(EmployeesImpl obj) {
                return obj.getRoleUsers();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        PurchaseOrders {
            public Object get(EmployeesImpl obj) {
                return obj.getPurchaseOrders();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Groups {
            public Object get(EmployeesImpl obj) {
                return obj.getGroups();
            }

            public void put(EmployeesImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;


        private static int firstIndex = 0;

        public abstract Object get(EmployeesImpl object);

        public abstract void put(EmployeesImpl object, Object value);

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


    public static final int USERNAME = AttributesEnum.UserName.index();
    public static final int PASSWORD = AttributesEnum.Password.index();
    public static final int FIRSTNAME = AttributesEnum.FirstName.index();
    public static final int LASTNAME = AttributesEnum.LastName.index();
    public static final int GENDER = AttributesEnum.Gender.index();
    public static final int MGRID = AttributesEnum.MgrId.index();
    public static final int DEPTID = AttributesEnum.DeptId.index();
    public static final int EMAIL = AttributesEnum.Email.index();
    public static final int MOBILE = AttributesEnum.Mobile.index();
    public static final int OFFICEPHONE = AttributesEnum.OfficePhone.index();
    public static final int HOMEPHONE = AttributesEnum.HomePhone.index();
    public static final int FAX = AttributesEnum.Fax.index();
    public static final int LOCATIONID = AttributesEnum.LocationId.index();
    public static final int HOMEADDRESS = AttributesEnum.HomeAddress.index();
    public static final int OFFICEADDRESS = AttributesEnum.OfficeAddress.index();
    public static final int PHOTO = AttributesEnum.Photo.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATEDAT = AttributesEnum.LastUpdatedAt.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATEDAT = AttributesEnum.CreatedAt.index();
    public static final int TITLE = AttributesEnum.Title.index();
    public static final int ID = AttributesEnum.Id.index();
    public static final int MGRNAME = AttributesEnum.MgrName.index();
    public static final int DEPTNAME = AttributesEnum.DeptName.index();
    public static final int EXPIRED = AttributesEnum.Expired.index();
    public static final int MGREMPLOYEE = AttributesEnum.MgrEmployee.index();
    public static final int SUBEMPLOYEES = AttributesEnum.SubEmployees.index();
    public static final int ROLES = AttributesEnum.Roles.index();
    public static final int REPAIRCALLSFORCALLEE = AttributesEnum.RepairCallsForCallee.index();
    public static final int REPAIRCALLSFORCALLER = AttributesEnum.RepairCallsForCaller.index();
    public static final int HELPDESKCALLSFORCALLEE = AttributesEnum.HelpdeskCallsForCallee.index();
    public static final int HELPDESKCALLSFORCALLER = AttributesEnum.HelpdeskCallsForCaller.index();
    public static final int ROLEUSERS = AttributesEnum.RoleUsers.index();
    public static final int PURCHASEORDERS = AttributesEnum.PurchaseOrders.index();
    public static final int GROUPS = AttributesEnum.Groups.index();

    /**
     * This is the default constructor (do not remove).
     */
    public EmployeesImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("edu.hp.model.eo.Employees");
    }

    /**
     * Gets the attribute value for UserName, using the alias name UserName.
     * @return the value of UserName
     */
    public String getUserName() {
        return (String)getAttributeInternal(USERNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for UserName.
     * @param value value to set the UserName
     */
    public void setUserName(String value) {
        setAttributeInternal(USERNAME, value);
    }

    /**
     * Gets the attribute value for Password, using the alias name Password.
     * @return the value of Password
     */
    public String getPassword() {
        return (String)getAttributeInternal(PASSWORD);
    }

    /**
     * Sets <code>value</code> as the attribute value for Password.
     * @param value value to set the Password
     */
    public void setPassword(String value) {
        setAttributeInternal(PASSWORD, value);
    }

    /**
     * Gets the attribute value for FirstName, using the alias name FirstName.
     * @return the value of FirstName
     */
    public String getFirstName() {
        return (String)getAttributeInternal(FIRSTNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for FirstName.
     * @param value value to set the FirstName
     */
    public void setFirstName(String value) {
        setAttributeInternal(FIRSTNAME, value);
    }

    /**
     * Gets the attribute value for LastName, using the alias name LastName.
     * @return the value of LastName
     */
    public String getLastName() {
        return (String)getAttributeInternal(LASTNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastName.
     * @param value value to set the LastName
     */
    public void setLastName(String value) {
        setAttributeInternal(LASTNAME, value);
    }

    /**
     * Gets the attribute value for Gender, using the alias name Gender.
     * @return the value of Gender
     */
    public String getGender() {
        return (String)getAttributeInternal(GENDER);
    }

    /**
     * Sets <code>value</code> as the attribute value for Gender.
     * @param value value to set the Gender
     */
    public void setGender(String value) {
        setAttributeInternal(GENDER, value);
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
     * Gets the attribute value for DeptId, using the alias name DeptId.
     * @return the value of DeptId
     */
    public String getDeptId() {
        return (String)getAttributeInternal(DEPTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for DeptId.
     * @param value value to set the DeptId
     */
    public void setDeptId(String value) {
        setAttributeInternal(DEPTID, value);
    }

    /**
     * Gets the attribute value for Email, using the alias name Email.
     * @return the value of Email
     */
    public String getEmail() {
        return (String)getAttributeInternal(EMAIL);
    }

    /**
     * Sets <code>value</code> as the attribute value for Email.
     * @param value value to set the Email
     */
    public void setEmail(String value) {
        setAttributeInternal(EMAIL, value);
    }

    /**
     * Gets the attribute value for Mobile, using the alias name Mobile.
     * @return the value of Mobile
     */
    public String getMobile() {
        return (String)getAttributeInternal(MOBILE);
    }

    /**
     * Sets <code>value</code> as the attribute value for Mobile.
     * @param value value to set the Mobile
     */
    public void setMobile(String value) {
        setAttributeInternal(MOBILE, value);
    }

    /**
     * Gets the attribute value for OfficePhone, using the alias name OfficePhone.
     * @return the value of OfficePhone
     */
    public String getOfficePhone() {
        return (String)getAttributeInternal(OFFICEPHONE);
    }

    /**
     * Sets <code>value</code> as the attribute value for OfficePhone.
     * @param value value to set the OfficePhone
     */
    public void setOfficePhone(String value) {
        setAttributeInternal(OFFICEPHONE, value);
    }

    /**
     * Gets the attribute value for HomePhone, using the alias name HomePhone.
     * @return the value of HomePhone
     */
    public String getHomePhone() {
        return (String)getAttributeInternal(HOMEPHONE);
    }

    /**
     * Sets <code>value</code> as the attribute value for HomePhone.
     * @param value value to set the HomePhone
     */
    public void setHomePhone(String value) {
        setAttributeInternal(HOMEPHONE, value);
    }

    /**
     * Gets the attribute value for Fax, using the alias name Fax.
     * @return the value of Fax
     */
    public String getFax() {
        return (String)getAttributeInternal(FAX);
    }

    /**
     * Sets <code>value</code> as the attribute value for Fax.
     * @param value value to set the Fax
     */
    public void setFax(String value) {
        setAttributeInternal(FAX, value);
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
     * Gets the attribute value for HomeAddress, using the alias name HomeAddress.
     * @return the value of HomeAddress
     */
    public String getHomeAddress() {
        return (String)getAttributeInternal(HOMEADDRESS);
    }

    /**
     * Sets <code>value</code> as the attribute value for HomeAddress.
     * @param value value to set the HomeAddress
     */
    public void setHomeAddress(String value) {
        setAttributeInternal(HOMEADDRESS, value);
    }

    /**
     * Gets the attribute value for OfficeAddress, using the alias name OfficeAddress.
     * @return the value of OfficeAddress
     */
    public String getOfficeAddress() {
        return (String)getAttributeInternal(OFFICEADDRESS);
    }

    /**
     * Sets <code>value</code> as the attribute value for OfficeAddress.
     * @param value value to set the OfficeAddress
     */
    public void setOfficeAddress(String value) {
        setAttributeInternal(OFFICEADDRESS, value);
    }

    /**
     * Gets the attribute value for Photo, using the alias name Photo.
     * @return the value of Photo
     */
    public BlobDomain getPhoto() {
        return (BlobDomain)getAttributeInternal(PHOTO);
    }

    /**
     * Sets <code>value</code> as the attribute value for Photo.
     * @param value value to set the Photo
     */
    public void setPhoto(BlobDomain value) {
        setAttributeInternal(PHOTO, value);
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
     * Gets the attribute value for CreatedAt, using the alias name CreatedAt.
     * @return the value of CreatedAt
     */
    public Timestamp getCreatedAt() {
        return (Timestamp)getAttributeInternal(CREATEDAT);
    }


    /**
     * Gets the attribute value for Title, using the alias name Title.
     * @return the value of Title
     */
    public String getTitle() {
        return (String)getAttributeInternal(TITLE);
    }

    /**
     * Sets <code>value</code> as the attribute value for Title.
     * @param value value to set the Title
     */
    public void setTitle(String value) {
        setAttributeInternal(TITLE, value);
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
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getMgrEmployee() {
        return (RowIterator)getAttributeInternal(MGREMPLOYEE);
    }

    /**
     * @return the associated entity EmployeesImpl.
     */
    public EmployeesImpl getSubEmployees() {
        return (EmployeesImpl)getAttributeInternal(SUBEMPLOYEES);
    }

    /**
     * Sets <code>value</code> as the associated entity EmployeesImpl.
     */
    public void setSubEmployees(EmployeesImpl value) {
        setAttributeInternal(SUBEMPLOYEES, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getRoles() {
        return (RowIterator)getAttributeInternal(ROLES);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getRepairCallsForCallee() {
        return (RowIterator)getAttributeInternal(REPAIRCALLSFORCALLEE);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getRepairCallsForCaller() {
        return (RowIterator)getAttributeInternal(REPAIRCALLSFORCALLER);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getHelpdeskCallsForCallee() {
        return (RowIterator)getAttributeInternal(HELPDESKCALLSFORCALLEE);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getHelpdeskCallsForCaller() {
        return (RowIterator)getAttributeInternal(HELPDESKCALLSFORCALLER);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */

    public RowIterator getRoleUsers() {
        return (RowIterator)getAttributeInternal(ROLEUSERS);
    }
    public RowIterator getPurchaseOrders() {
        return (RowIterator)getAttributeInternal(PURCHASEORDERS);

    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getGroups() {
        return (RowIterator)getAttributeInternal(GROUPS);
    }

    /**
     * @param id key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(DBSequence id) {
        return new Key(new Object[]{id});
    }


}

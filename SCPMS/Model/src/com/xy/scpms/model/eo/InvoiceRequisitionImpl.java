package com.xy.scpms.model.eo;


import com.xy.scpms.model.common.BaseEntity;
import com.xy.scpms.model.utils.Codes;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jun 01 20:51:56 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class InvoiceRequisitionImpl extends BaseEntity {

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        Id {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getId();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setId((Number)value);
            }
        }
        ,
        Requestor {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getRequestor();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        RequestDate {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getRequestDate();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        LinePaymentId {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getLinePaymentId();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setLinePaymentId((Number)value);
            }
        }
        ,
        Amount {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getAmount();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setAmount((Number)value);
            }
        }
        ,
        Status {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getStatus();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        }
        ,
        Memo {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getMemo();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setMemo((String)value);
            }
        }
        ,
        DeptHeadApprover {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getDeptHeadApprover();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setDeptHeadApprover((String)value);
            }
        }
        ,
        DeptHeadOutcome {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getDeptHeadOutcome();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setDeptHeadOutcome((String)value);
            }
        }
        ,
        DeptHeadDate {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getDeptHeadDate();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setDeptHeadDate((Date)value);
            }
        }
        ,
        DeptHeadMemo {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getDeptHeadMemo();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setDeptHeadMemo((String)value);
            }
        }
        ,
        MrktHeadApprover {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getMrktHeadApprover();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setMrktHeadApprover((String)value);
            }
        }
        ,
        MrktHeadOutcome {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getMrktHeadOutcome();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setMrktHeadOutcome((String)value);
            }
        }
        ,
        MrktHeadMemo {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getMrktHeadMemo();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setMrktHeadMemo((String)value);
            }
        }
        ,
        MrktHeadDate {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getMrktHeadDate();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setMrktHeadDate((Date)value);
            }
        }
        ,
        BatchId {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getBatchId();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setBatchId((Number)value);
            }
        }
        ,
        Deleted {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getDeleted();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setDeleted((String)value);
            }
        }
        ,
        Department {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getDepartment();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setDepartment((String)value);
            }
        }
        ,
        ContractId {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getContractId();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setContractId((Number)value);
            }
        }
        ,
        LineId {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getLineId();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setLineId((Number)value);
            }
        }
        ,
        AuthName {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getAuthName();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setAuthName((String)value);
            }
        }
        ,
        Contact {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getContact();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setContact((String)value);
            }
        }
        ,
        Broker {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getBroker();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setBroker((String)value);
            }
        }
        ,
        Owner {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getOwner();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setOwner((String)value);
            }
        }
        ,
        Plant {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getPlant();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setPlant((String)value);
            }
        }
        ,
        MnftShipNo {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getMnftShipNo();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setMnftShipNo((String)value);
            }
        }
        ,
        ContractLinePayments {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getContractLinePayments();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setContractLinePayments((ContractLinePaymentsImpl)value);
            }
        }
        ,
        Users {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getUsers();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setUsers((EntityImpl)value);
            }
        }
        ,
        InvoiceBatch {
            public Object get(InvoiceRequisitionImpl obj) {
                return obj.getInvoiceBatch();
            }

            public void put(InvoiceRequisitionImpl obj, Object value) {
                obj.setInvoiceBatch((InvoiceBatchImpl)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(InvoiceRequisitionImpl object);

        public abstract void put(InvoiceRequisitionImpl object, Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() +
                AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int ID = AttributesEnum.Id.index();
    public static final int REQUESTOR = AttributesEnum.Requestor.index();
    public static final int REQUESTDATE = AttributesEnum.RequestDate.index();
    public static final int LINEPAYMENTID = AttributesEnum.LinePaymentId.index();
    public static final int AMOUNT = AttributesEnum.Amount.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int MEMO = AttributesEnum.Memo.index();
    public static final int DEPTHEADAPPROVER = AttributesEnum.DeptHeadApprover.index();
    public static final int DEPTHEADOUTCOME = AttributesEnum.DeptHeadOutcome.index();
    public static final int DEPTHEADDATE = AttributesEnum.DeptHeadDate.index();
    public static final int DEPTHEADMEMO = AttributesEnum.DeptHeadMemo.index();
    public static final int MRKTHEADAPPROVER = AttributesEnum.MrktHeadApprover.index();
    public static final int MRKTHEADOUTCOME = AttributesEnum.MrktHeadOutcome.index();
    public static final int MRKTHEADMEMO = AttributesEnum.MrktHeadMemo.index();
    public static final int MRKTHEADDATE = AttributesEnum.MrktHeadDate.index();
    public static final int BATCHID = AttributesEnum.BatchId.index();
    public static final int DELETED = AttributesEnum.Deleted.index();
    public static final int DEPARTMENT = AttributesEnum.Department.index();
    public static final int CONTRACTID = AttributesEnum.ContractId.index();
    public static final int LINEID = AttributesEnum.LineId.index();
    public static final int AUTHNAME = AttributesEnum.AuthName.index();
    public static final int CONTACT = AttributesEnum.Contact.index();
    public static final int BROKER = AttributesEnum.Broker.index();
    public static final int OWNER = AttributesEnum.Owner.index();
    public static final int PLANT = AttributesEnum.Plant.index();
    public static final int MNFTSHIPNO = AttributesEnum.MnftShipNo.index();
    public static final int CONTRACTLINEPAYMENTS = AttributesEnum.ContractLinePayments.index();
    public static final int USERS = AttributesEnum.Users.index();
    public static final int INVOICEBATCH = AttributesEnum.InvoiceBatch.index();

    /**
     * This is the default constructor (do not remove).
     */
    public InvoiceRequisitionImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.xy.scpms.model.eo.InvoiceRequisition");
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        if (this.getStatus().equals(Codes.COL_VALUE_INVOICE_APP_STATUS_DRAFT)) {
            this.remove();
        }
        super.doDML(operation, e);
        if (this.getStatus().equals(Codes.COL_VALUE_STATUS_PENDING_APPROVAL)) {
            this.getContractLinePayments().setAttribute("Status",
                                                        Codes.COL_VALUE_STATUS_PENDING_APPROVAL);
        } else if (this.getStatus().equals(Codes.COL_VALUE_STATUS_REJECTED)) {
            this.getContractLinePayments().setAttribute("Status",
                                                        Codes.COL_VALUE_PAYMENT_STATUS_INITIAL);
        } else if (this.getStatus().equals(Codes.COL_VALUE_STATUS_APPROVED)) {
            this.getContractLinePayments().setAttribute("Status",
                                                        Codes.COL_VALUE_PAYMENT_STATUS_APPROVED);
        } else if (this.getStatus().equals(Codes.COL_VALUE_PAYMENT_STATUS_INVOICED)) {
            this.getContractLinePayments().setAttribute("Status",
                                                        Codes.COL_VALUE_PAYMENT_STATUS_INVOICED);
        }
    }


    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        setAttribute("Status", Codes.COL_VALUE_INVOICE_APP_STATUS_DRAFT);
    }


    /**
     * Gets the attribute value for Id, using the alias name Id.
     * @return the Id
     */
    public Number getId() {
        return (Number)getAttributeInternal(ID);
    }

    /**
     * Sets <code>value</code> as the attribute value for Id.
     * @param value value to set the Id
     */
    public void setId(Number value) {
        setAttributeInternal(ID, value);
    }

    /**
     * Gets the attribute value for Requestor, using the alias name Requestor.
     * @return the Requestor
     */
    public String getRequestor() {
        return (String)getAttributeInternal(REQUESTOR);
    }

    /**
     * Gets the attribute value for RequestDate, using the alias name RequestDate.
     * @return the RequestDate
     */
    public Date getRequestDate() {
        return (Date)getAttributeInternal(REQUESTDATE);
    }

    /**
     * Gets the attribute value for LinePaymentId, using the alias name LinePaymentId.
     * @return the LinePaymentId
     */
    public Number getLinePaymentId() {
        return (Number)getAttributeInternal(LINEPAYMENTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for LinePaymentId.
     * @param value value to set the LinePaymentId
     */
    public void setLinePaymentId(Number value) {
        setAttributeInternal(LINEPAYMENTID, value);
    }

    /**
     * Gets the attribute value for Amount, using the alias name Amount.
     * @return the Amount
     */
    public Number getAmount() {
        return (Number)getAttributeInternal(AMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for Amount.
     * @param value value to set the Amount
     */
    public void setAmount(Number value) {
        setAttributeInternal(AMOUNT, value);
    }

    /**
     * Gets the attribute value for Status, using the alias name Status.
     * @return the Status
     */
    public String getStatus() {
        return (String)getAttributeInternal(STATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for Status.
     * @param value value to set the Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**
     * Gets the attribute value for Memo, using the alias name Memo.
     * @return the Memo
     */
    public String getMemo() {
        return (String)getAttributeInternal(MEMO);
    }

    /**
     * Sets <code>value</code> as the attribute value for Memo.
     * @param value value to set the Memo
     */
    public void setMemo(String value) {
        setAttributeInternal(MEMO, value);
    }

    /**
     * Gets the attribute value for DeptHeadApprover, using the alias name DeptHeadApprover.
     * @return the DeptHeadApprover
     */
    public String getDeptHeadApprover() {
        return (String)getAttributeInternal(DEPTHEADAPPROVER);
    }

    /**
     * Sets <code>value</code> as the attribute value for DeptHeadApprover.
     * @param value value to set the DeptHeadApprover
     */
    public void setDeptHeadApprover(String value) {
        setAttributeInternal(DEPTHEADAPPROVER, value);
    }

    /**
     * Gets the attribute value for DeptHeadOutcome, using the alias name DeptHeadOutcome.
     * @return the DeptHeadOutcome
     */
    public String getDeptHeadOutcome() {
        return (String)getAttributeInternal(DEPTHEADOUTCOME);
    }

    /**
     * Sets <code>value</code> as the attribute value for DeptHeadOutcome.
     * @param value value to set the DeptHeadOutcome
     */
    public void setDeptHeadOutcome(String value) {
        setAttributeInternal(DEPTHEADOUTCOME, value);
    }

    /**
     * Gets the attribute value for DeptHeadDate, using the alias name DeptHeadDate.
     * @return the DeptHeadDate
     */
    public Date getDeptHeadDate() {
        return (Date)getAttributeInternal(DEPTHEADDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for DeptHeadDate.
     * @param value value to set the DeptHeadDate
     */
    public void setDeptHeadDate(Date value) {
        setAttributeInternal(DEPTHEADDATE, value);
    }

    /**
     * Gets the attribute value for DeptHeadMemo, using the alias name DeptHeadMemo.
     * @return the DeptHeadMemo
     */
    public String getDeptHeadMemo() {
        return (String)getAttributeInternal(DEPTHEADMEMO);
    }

    /**
     * Sets <code>value</code> as the attribute value for DeptHeadMemo.
     * @param value value to set the DeptHeadMemo
     */
    public void setDeptHeadMemo(String value) {
        setAttributeInternal(DEPTHEADMEMO, value);
    }

    /**
     * Gets the attribute value for MrktHeadApprover, using the alias name MrktHeadApprover.
     * @return the MrktHeadApprover
     */
    public String getMrktHeadApprover() {
        return (String)getAttributeInternal(MRKTHEADAPPROVER);
    }

    /**
     * Sets <code>value</code> as the attribute value for MrktHeadApprover.
     * @param value value to set the MrktHeadApprover
     */
    public void setMrktHeadApprover(String value) {
        setAttributeInternal(MRKTHEADAPPROVER, value);
    }

    /**
     * Gets the attribute value for MrktHeadOutcome, using the alias name MrktHeadOutcome.
     * @return the MrktHeadOutcome
     */
    public String getMrktHeadOutcome() {
        return (String)getAttributeInternal(MRKTHEADOUTCOME);
    }

    /**
     * Sets <code>value</code> as the attribute value for MrktHeadOutcome.
     * @param value value to set the MrktHeadOutcome
     */
    public void setMrktHeadOutcome(String value) {
        setAttributeInternal(MRKTHEADOUTCOME, value);
    }

    /**
     * Gets the attribute value for MrktHeadMemo, using the alias name MrktHeadMemo.
     * @return the MrktHeadMemo
     */
    public String getMrktHeadMemo() {
        return (String)getAttributeInternal(MRKTHEADMEMO);
    }

    /**
     * Sets <code>value</code> as the attribute value for MrktHeadMemo.
     * @param value value to set the MrktHeadMemo
     */
    public void setMrktHeadMemo(String value) {
        setAttributeInternal(MRKTHEADMEMO, value);
    }

    /**
     * Gets the attribute value for MrktHeadDate, using the alias name MrktHeadDate.
     * @return the MrktHeadDate
     */
    public Date getMrktHeadDate() {
        return (Date)getAttributeInternal(MRKTHEADDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for MrktHeadDate.
     * @param value value to set the MrktHeadDate
     */
    public void setMrktHeadDate(Date value) {
        setAttributeInternal(MRKTHEADDATE, value);
    }

    /**
     * Gets the attribute value for BatchId, using the alias name BatchId.
     * @return the BatchId
     */
    public Number getBatchId() {
        return (Number)getAttributeInternal(BATCHID);
    }

    /**
     * Sets <code>value</code> as the attribute value for BatchId.
     * @param value value to set the BatchId
     */
    public void setBatchId(Number value) {
        setAttributeInternal(BATCHID, value);
    }

    /**
     * Gets the attribute value for Deleted, using the alias name Deleted.
     * @return the value of Deleted
     */
    public String getDeleted() {
        return (String)getAttributeInternal(DELETED);
    }

    /**
     * Sets <code>value</code> as the attribute value for Deleted.
     * @param value value to set the Deleted
     */
    public void setDeleted(String value) {
        setAttributeInternal(DELETED, value);
    }

    /**
     * Gets the attribute value for Department, using the alias name Department.
     * @return the value of Department
     */
    public String getDepartment() {
        return (String)getAttributeInternal(DEPARTMENT);
    }

    /**
     * Sets <code>value</code> as the attribute value for Department.
     * @param value value to set the Department
     */
    public void setDepartment(String value) {
        setAttributeInternal(DEPARTMENT, value);
    }

    /**
     * Gets the attribute value for ContractId, using the alias name ContractId.
     * @return the value of ContractId
     */
    public Number getContractId() {
        return (Number)getAttributeInternal(CONTRACTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ContractId.
     * @param value value to set the ContractId
     */
    public void setContractId(Number value) {
        setAttributeInternal(CONTRACTID, value);
    }

    /**
     * Gets the attribute value for LineId, using the alias name LineId.
     * @return the value of LineId
     */
    public Number getLineId() {
        return (Number)getAttributeInternal(LINEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for LineId.
     * @param value value to set the LineId
     */
    public void setLineId(Number value) {
        setAttributeInternal(LINEID, value);
    }

    /**
     * Gets the attribute value for AuthName, using the alias name AuthName.
     * @return the value of AuthName
     */
    public String getAuthName() {
        return (String)getAttributeInternal(AUTHNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for AuthName.
     * @param value value to set the AuthName
     */
    public void setAuthName(String value) {
        setAttributeInternal(AUTHNAME, value);
    }

    /**
     * Gets the attribute value for Contact, using the alias name Contact.
     * @return the value of Contact
     */
    public String getContact() {
        return (String)getAttributeInternal(CONTACT);
    }

    /**
     * Sets <code>value</code> as the attribute value for Contact.
     * @param value value to set the Contact
     */
    public void setContact(String value) {
        setAttributeInternal(CONTACT, value);
    }

    /**
     * Gets the attribute value for Broker, using the alias name Broker.
     * @return the value of Broker
     */
    public String getBroker() {
        return (String)getAttributeInternal(BROKER);
    }

    /**
     * Sets <code>value</code> as the attribute value for Broker.
     * @param value value to set the Broker
     */
    public void setBroker(String value) {
        setAttributeInternal(BROKER, value);
    }

    /**
     * Gets the attribute value for Owner, using the alias name Owner.
     * @return the value of Owner
     */
    public String getOwner() {
        return (String)getAttributeInternal(OWNER);
    }

    /**
     * Sets <code>value</code> as the attribute value for Owner.
     * @param value value to set the Owner
     */
    public void setOwner(String value) {
        setAttributeInternal(OWNER, value);
    }

    /**
     * Gets the attribute value for Plant, using the alias name Plant.
     * @return the value of Plant
     */
    public String getPlant() {
        return (String)getAttributeInternal(PLANT);
    }

    /**
     * Sets <code>value</code> as the attribute value for Plant.
     * @param value value to set the Plant
     */
    public void setPlant(String value) {
        setAttributeInternal(PLANT, value);
    }

    /**
     * Gets the attribute value for MnftShipNo, using the alias name MnftShipNo.
     * @return the value of MnftShipNo
     */
    public String getMnftShipNo() {
        return (String)getAttributeInternal(MNFTSHIPNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for MnftShipNo.
     * @param value value to set the MnftShipNo
     */
    public void setMnftShipNo(String value) {
        setAttributeInternal(MNFTSHIPNO, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
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
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }


    /**
     * @return the associated entity ContractLinePaymentsImpl.
     */
    public ContractLinePaymentsImpl getContractLinePayments() {
        return (ContractLinePaymentsImpl)getAttributeInternal(CONTRACTLINEPAYMENTS);
    }

    /**
     * Sets <code>value</code> as the associated entity ContractLinePaymentsImpl.
     */
    public void setContractLinePayments(ContractLinePaymentsImpl value) {
        setAttributeInternal(CONTRACTLINEPAYMENTS, value);
    }


    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getUsers() {
        return (EntityImpl)getAttributeInternal(USERS);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setUsers(EntityImpl value) {
        setAttributeInternal(USERS, value);
    }

    /**
     * @return the associated entity com.xy.scpms.model.common.BaseEntity.
     */
    public InvoiceBatchImpl getInvoiceBatch() {
        return (InvoiceBatchImpl)getAttributeInternal(INVOICEBATCH);
    }

    /**
     * Sets <code>value</code> as the associated entity com.xy.scpms.model.common.BaseEntity.
     */
    public void setInvoiceBatch(InvoiceBatchImpl value) {
        setAttributeInternal(INVOICEBATCH, value);
    }


    /**
     * @param id key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number id) {
        return new Key(new Object[]{id});
    }

    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
    }


}

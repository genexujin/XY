package edu.hp.model.eo;

import edu.hp.model.common.Constants;

import java.math.BigDecimal;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowInconsistentException;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Number;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Feb 01 22:34:50 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PurchaseOrdersImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        OrderId {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getOrderId();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setOrderId((DBSequence)value);
            }
        },
        OrderReadableId {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getOrderReadableId();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setOrderReadableId((String)value);
            }
        },
        SubmitterId {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getSubmitterId();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setSubmitterId((String)value);
            }
        },
        CreateBy {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getCreateBy();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setCreateBy((String)value);
            }
        },
        CreateAt {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getCreateAt();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setCreateAt((Timestamp)value);
            }
        },
        LastUpdatedBy {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setLastUpdatedBy((String)value);
            }
        },
        LastUpdatedAt {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getLastUpdatedAt();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setLastUpdatedAt((Timestamp)value);
            }
        },
        State {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getState();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setState((String)value);
            }
        },
        SubmitTotal {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getSubmitTotal();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setSubmitTotal((BigDecimal)value);
            }
        },
        VerifyTotal {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getVerifyTotal();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setVerifyTotal((BigDecimal)value);
            }
        },
        OrderNote {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getOrderNote();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setOrderNote((String)value);
            }
        },
        ItemCategoryId {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getItemCategoryId();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setItemCategoryId((String)value);
            }
        },
        LineNum {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getLineNum();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setLineNum((Number)value);
            }
        },
        SubmitAt {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getSubmitAt();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setSubmitAt((Timestamp)value);
            }
        },
        CurrentApprover {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getCurrentApprover();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setCurrentApprover((String)value);
            }
        },
        CurrentExecutor {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getCurrentExecutor();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setCurrentExecutor((String)value);
            }
        },
        Submitter {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getSubmitter();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setSubmitter((EmployeesImpl)value);
            }
        },
        PurchaseOrderLines {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getPurchaseOrderLines();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        },
        PurchaseOrderHistorys {
            public Object get(PurchaseOrdersImpl obj) {
                return obj.getPurchaseOrderHistorys();
            }

            public void put(PurchaseOrdersImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        };
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(PurchaseOrdersImpl object);

        public abstract void put(PurchaseOrdersImpl object, Object value);

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


    public static final int ORDERID = AttributesEnum.OrderId.index();
    public static final int ORDERREADABLEID = AttributesEnum.OrderReadableId.index();
    public static final int SUBMITTERID = AttributesEnum.SubmitterId.index();
    public static final int CREATEBY = AttributesEnum.CreateBy.index();
    public static final int CREATEAT = AttributesEnum.CreateAt.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATEDAT = AttributesEnum.LastUpdatedAt.index();
    public static final int STATE = AttributesEnum.State.index();
    public static final int SUBMITTOTAL = AttributesEnum.SubmitTotal.index();
    public static final int VERIFYTOTAL = AttributesEnum.VerifyTotal.index();
    public static final int ORDERNOTE = AttributesEnum.OrderNote.index();
    public static final int ITEMCATEGORYID = AttributesEnum.ItemCategoryId.index();
    public static final int LINENUM = AttributesEnum.LineNum.index();
    public static final int SUBMITAT = AttributesEnum.SubmitAt.index();
    public static final int CURRENTAPPROVER = AttributesEnum.CurrentApprover.index();
    public static final int CURRENTEXECUTOR = AttributesEnum.CurrentExecutor.index();
    public static final int SUBMITTER = AttributesEnum.Submitter.index();
    public static final int PURCHASEORDERLINES = AttributesEnum.PurchaseOrderLines.index();
    public static final int PURCHASEORDERHISTORYS = AttributesEnum.PurchaseOrderHistorys.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PurchaseOrdersImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("edu.hp.model.eo.PurchaseOrders");
    }

    /**
     * Gets the attribute value for OrderId, using the alias name OrderId.
     * @return the value of OrderId
     */
    public DBSequence getOrderId() {
        return (DBSequence)getAttributeInternal(ORDERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for OrderId.
     * @param value value to set the OrderId
     */
    public void setOrderId(DBSequence value) {
        setAttributeInternal(ORDERID, value);
    }

    /**
     * Gets the attribute value for OrderReadableId, using the alias name OrderReadableId.
     * @return the value of OrderReadableId
     */
    public String getOrderReadableId() {
        return (String)getAttributeInternal(ORDERREADABLEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for OrderReadableId.
     * @param value value to set the OrderReadableId
     */
    public void setOrderReadableId(String value) {
        setAttributeInternal(ORDERREADABLEID, value);
    }

    /**
     * Gets the attribute value for SubmitterId, using the alias name SubmitterId.
     * @return the value of SubmitterId
     */
    public String getSubmitterId() {
        return (String)getAttributeInternal(SUBMITTERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for SubmitterId.
     * @param value value to set the SubmitterId
     */
    public void setSubmitterId(String value) {
        setAttributeInternal(SUBMITTERID, value);
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
     * Sets <code>value</code> as the attribute value for CreateAt.
     * @param value value to set the CreateAt
     */
    public void setCreateAt(Timestamp value) {
        setAttributeInternal(CREATEAT, value);
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
     * Sets <code>value</code> as the attribute value for LastUpdatedAt.
     * @param value value to set the LastUpdatedAt
     */
    public void setLastUpdatedAt(Timestamp value) {
        setAttributeInternal(LASTUPDATEDAT, value);
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
     * Gets the attribute value for SubmitTotal, using the alias name SubmitTotal.
     * @return the value of SubmitTotal
     */
    public BigDecimal getSubmitTotal() {
        return (BigDecimal)getAttributeInternal(SUBMITTOTAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for SubmitTotal.
     * @param value value to set the SubmitTotal
     */
    public void setSubmitTotal(BigDecimal value) {
        setAttributeInternal(SUBMITTOTAL, value);
    }

    /**
     * Gets the attribute value for VerifyTotal, using the alias name VerifyTotal.
     * @return the value of VerifyTotal
     */
    public BigDecimal getVerifyTotal() {
        return (BigDecimal)getAttributeInternal(VERIFYTOTAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for VerifyTotal.
     * @param value value to set the VerifyTotal
     */
    public void setVerifyTotal(BigDecimal value) {
        setAttributeInternal(VERIFYTOTAL, value);
    }

    /**
     * Gets the attribute value for OrderNote, using the alias name OrderNote.
     * @return the value of OrderNote
     */
    public String getOrderNote() {
        return (String)getAttributeInternal(ORDERNOTE);
    }

    /**
     * Sets <code>value</code> as the attribute value for OrderNote.
     * @param value value to set the OrderNote
     */
    public void setOrderNote(String value) {
        setAttributeInternal(ORDERNOTE, value);
    }

    /**
     * Gets the attribute value for ItemCategoryId, using the alias name ItemCategoryId.
     * @return the value of ItemCategoryId
     */
    public String getItemCategoryId() {
        return (String)getAttributeInternal(ITEMCATEGORYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ItemCategoryId.
     * @param value value to set the ItemCategoryId
     */
    public void setItemCategoryId(String value) {
        setAttributeInternal(ITEMCATEGORYID, value);
    }

    /**
     * Gets the attribute value for LineNum, using the alias name LineNum.
     * @return the value of LineNum
     */
    public oracle.jbo.domain.Number getLineNum() {
        return (oracle.jbo.domain.Number)getAttributeInternal(LINENUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for LineNum.
     * @param value value to set the LineNum
     */
    public void setLineNum(oracle.jbo.domain.Number value) {
        setAttributeInternal(LINENUM, value);
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
     * Gets the attribute value for CurrentApprover, using the alias name CurrentApprover.
     * @return the value of CurrentApprover
     */
    public String getCurrentApprover() {
        return (String)getAttributeInternal(CURRENTAPPROVER);
    }

    /**
     * Sets <code>value</code> as the attribute value for CurrentApprover.
     * @param value value to set the CurrentApprover
     */
    public void setCurrentApprover(String value) {
        setAttributeInternal(CURRENTAPPROVER, value);
    }

    /**
     * Gets the attribute value for CurrentExecutor, using the alias name CurrentExecutor.
     * @return the value of CurrentExecutor
     */
    public String getCurrentExecutor() {
        return (String)getAttributeInternal(CURRENTEXECUTOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for CurrentExecutor.
     * @param value value to set the CurrentExecutor
     */
    public void setCurrentExecutor(String value) {
        setAttributeInternal(CURRENTEXECUTOR, value);
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
    public EmployeesImpl getSubmitter() {
        return (EmployeesImpl)getAttributeInternal(SUBMITTER);
    }

    /**
     * Sets <code>value</code> as the associated entity EmployeesImpl.
     */
    public void setSubmitter(EmployeesImpl value) {
        setAttributeInternal(SUBMITTER, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getPurchaseOrderLines() {
        return (RowIterator)getAttributeInternal(PURCHASEORDERLINES);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getPurchaseOrderHistorys() {
        return (RowIterator)getAttributeInternal(PURCHASEORDERHISTORYS);
    }


    /**
     * Validation method for PurchaseOrders.
     */
    public boolean validatePurchaseQuantity() {

        RowIterator iterator = this.getPurchaseOrderLines();
      
        while (iterator.hasNext()) {
            Row next = iterator.next();
            System.err.println(this.getAttribute("State"));
            System.err.println(next.getAttribute("PurchaseQuantity"));
            if (this.getAttribute("State").equals(Constants.PO_STATE_PENDING_APPROVAL) &&
                next.getAttribute("PurchaseQuantity") == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param orderId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(DBSequence orderId) {
        return new Key(new Object[] { orderId });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
    }

    /**
     * Add entity remove logic in this method.
     */
    public void remove() {
        super.remove();
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        super.doDML(operation, e);
    }

    public void lock() {
        try {
            super.lock();
        } catch (RowInconsistentException e) {
            e.printStackTrace();
            refresh(REFRESH_WITH_DB_ONLY_IF_UNCHANGED | REFRESH_CONTAINEES);
            System.out.println("已被处理的异常信息：" + new java.util.Date().toLocaleString() + " 更新时出现锁异常！");
            super.lock();
        }
    }
}

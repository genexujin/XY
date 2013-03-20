package edu.hp.model.eo;

import edu.hp.model.common.Constants;

import java.math.BigDecimal;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.RowInconsistentException;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Feb 01 22:34:20 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PurchaseOrderLinesImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        OrderLineId {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getOrderLineId();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setOrderLineId((DBSequence)value);
            }
        }
        ,
        ItemId {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getItemId();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setItemId((String)value);
            }
        }
        ,
        ItemDescription {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getItemDescription();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setItemDescription((String)value);
            }
        }
        ,
        SubmitQuantity {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getSubmitQuantity();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setSubmitQuantity((BigDecimal)value);
            }
        }
        ,
        SubmitUnit {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getSubmitUnit();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setSubmitUnit((String)value);
            }
        }
        ,
        SubmitPrice {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getSubmitPrice();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setSubmitPrice((BigDecimal)value);
            }
        }
        ,
        SubmitTotal {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getSubmitTotal();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setSubmitTotal((BigDecimal)value);
            }
        }
        ,
        ExpectedDate {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getExpectedDate();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setExpectedDate((Timestamp)value);
            }
        }
        ,
        SubmitNote {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getSubmitNote();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setSubmitNote((String)value);
            }
        }
        ,
        State {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getState();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setState((String)value);
            }
        }
        ,
        PurchaseQuantity {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getPurchaseQuantity();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setPurchaseQuantity((BigDecimal)value);
            }
        }
        ,
        ActualPrice {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getActualPrice();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setActualPrice((BigDecimal)value);
            }
        }
        ,
        ActualTotal {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getActualTotal();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setActualTotal((BigDecimal)value);
            }
        }
        ,
        VerifyNote {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getVerifyNote();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setVerifyNote((String)value);
            }
        }
        ,
        ReceiveQuantity {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getReceiveQuantity();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setReceiveQuantity((BigDecimal)value);
            }
        }
        ,
        OrderId {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getOrderId();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setOrderId((String)value);
            }
        }
        ,
        ItemCategoryId {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getItemCategoryId();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setItemCategoryId((String)value);
            }
        }
        ,
        Cancelled {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getCancelled();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setCancelled((String)value);
            }
        }
        ,
        PurchaseOrder {
            public Object get(PurchaseOrderLinesImpl obj) {
                return obj.getPurchaseOrder();
            }

            public void put(PurchaseOrderLinesImpl obj, Object value) {
                obj.setPurchaseOrder((PurchaseOrdersImpl)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(PurchaseOrderLinesImpl object);

        public abstract void put(PurchaseOrderLinesImpl object, Object value);

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


    public static final int ORDERLINEID = AttributesEnum.OrderLineId.index();
    public static final int ITEMID = AttributesEnum.ItemId.index();
    public static final int ITEMDESCRIPTION = AttributesEnum.ItemDescription.index();
    public static final int SUBMITQUANTITY = AttributesEnum.SubmitQuantity.index();
    public static final int SUBMITUNIT = AttributesEnum.SubmitUnit.index();
    public static final int SUBMITPRICE = AttributesEnum.SubmitPrice.index();
    public static final int SUBMITTOTAL = AttributesEnum.SubmitTotal.index();
    public static final int EXPECTEDDATE = AttributesEnum.ExpectedDate.index();
    public static final int SUBMITNOTE = AttributesEnum.SubmitNote.index();
    public static final int STATE = AttributesEnum.State.index();
    public static final int PURCHASEQUANTITY = AttributesEnum.PurchaseQuantity.index();
    public static final int ACTUALPRICE = AttributesEnum.ActualPrice.index();
    public static final int ACTUALTOTAL = AttributesEnum.ActualTotal.index();
    public static final int VERIFYNOTE = AttributesEnum.VerifyNote.index();
    public static final int RECEIVEQUANTITY = AttributesEnum.ReceiveQuantity.index();
    public static final int ORDERID = AttributesEnum.OrderId.index();
    public static final int ITEMCATEGORYID = AttributesEnum.ItemCategoryId.index();
    public static final int CANCELLED = AttributesEnum.Cancelled.index();
    public static final int PURCHASEORDER = AttributesEnum.PurchaseOrder.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PurchaseOrderLinesImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("edu.hp.model.eo.PurchaseOrderLines");
    }

    /**
     * Gets the attribute value for OrderLineId, using the alias name OrderLineId.
     * @return the value of OrderLineId
     */
    public DBSequence getOrderLineId() {
        return (DBSequence)getAttributeInternal(ORDERLINEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for OrderLineId.
     * @param value value to set the OrderLineId
     */
    public void setOrderLineId(DBSequence value) {
        setAttributeInternal(ORDERLINEID, value);
    }

    /**
     * Gets the attribute value for ItemId, using the alias name ItemId.
     * @return the value of ItemId
     */
    public String getItemId() {
        return (String)getAttributeInternal(ITEMID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ItemId.
     * @param value value to set the ItemId
     */
    public void setItemId(String value) {
        setAttributeInternal(ITEMID, value);
    }

    /**
     * Gets the attribute value for ItemDescription, using the alias name ItemDescription.
     * @return the value of ItemDescription
     */
    public String getItemDescription() {
        return (String)getAttributeInternal(ITEMDESCRIPTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for ItemDescription.
     * @param value value to set the ItemDescription
     */
    public void setItemDescription(String value) {
        setAttributeInternal(ITEMDESCRIPTION, value);
    }

    /**
     * Gets the attribute value for SubmitQuantity, using the alias name SubmitQuantity.
     * @return the value of SubmitQuantity
     */
    public BigDecimal getSubmitQuantity() {
        return (BigDecimal)getAttributeInternal(SUBMITQUANTITY);
    }

    /**
     * Sets <code>value</code> as the attribute value for SubmitQuantity.
     * @param value value to set the SubmitQuantity
     */
    public void setSubmitQuantity(BigDecimal value) {
        setAttributeInternal(SUBMITQUANTITY, value);
    }

    /**
     * Gets the attribute value for SubmitUnit, using the alias name SubmitUnit.
     * @return the value of SubmitUnit
     */
    public String getSubmitUnit() {
        return (String)getAttributeInternal(SUBMITUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for SubmitUnit.
     * @param value value to set the SubmitUnit
     */
    public void setSubmitUnit(String value) {
        setAttributeInternal(SUBMITUNIT, value);
    }

    /**
     * Gets the attribute value for SubmitPrice, using the alias name SubmitPrice.
     * @return the value of SubmitPrice
     */
    public BigDecimal getSubmitPrice() {
        return (BigDecimal)getAttributeInternal(SUBMITPRICE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SubmitPrice.
     * @param value value to set the SubmitPrice
     */
    public void setSubmitPrice(BigDecimal value) {
        setAttributeInternal(SUBMITPRICE, value);
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
     * Gets the attribute value for ExpectedDate, using the alias name ExpectedDate.
     * @return the value of ExpectedDate
     */
    public Timestamp getExpectedDate() {
        return (Timestamp)getAttributeInternal(EXPECTEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ExpectedDate.
     * @param value value to set the ExpectedDate
     */
    public void setExpectedDate(Timestamp value) {
        setAttributeInternal(EXPECTEDDATE, value);
    }

    /**
     * Gets the attribute value for SubmitNote, using the alias name SubmitNote.
     * @return the value of SubmitNote
     */
    public String getSubmitNote() {
        return (String)getAttributeInternal(SUBMITNOTE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SubmitNote.
     * @param value value to set the SubmitNote
     */
    public void setSubmitNote(String value) {
        setAttributeInternal(SUBMITNOTE, value);
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
     * Gets the attribute value for PurchaseQuantity, using the alias name PurchaseQuantity.
     * @return the value of PurchaseQuantity
     */
    public BigDecimal getPurchaseQuantity() {
        return (BigDecimal)getAttributeInternal(PURCHASEQUANTITY);
    }

    /**
     * Sets <code>value</code> as the attribute value for PurchaseQuantity.
     * @param value value to set the PurchaseQuantity
     */
    public void setPurchaseQuantity(BigDecimal value) {
        setAttributeInternal(PURCHASEQUANTITY, value);
    }

    /**
     * Gets the attribute value for ActualPrice, using the alias name ActualPrice.
     * @return the value of ActualPrice
     */
    public BigDecimal getActualPrice() {
        return (BigDecimal)getAttributeInternal(ACTUALPRICE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ActualPrice.
     * @param value value to set the ActualPrice
     */
    public void setActualPrice(BigDecimal value) {
        setAttributeInternal(ACTUALPRICE, value);
    }

    /**
     * Gets the attribute value for ActualTotal, using the alias name ActualTotal.
     * @return the value of ActualTotal
     */
    public BigDecimal getActualTotal() {
        return (BigDecimal)getAttributeInternal(ACTUALTOTAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for ActualTotal.
     * @param value value to set the ActualTotal
     */
    public void setActualTotal(BigDecimal value) {
        setAttributeInternal(ACTUALTOTAL, value);
    }

    /**
     * Gets the attribute value for VerifyNote, using the alias name VerifyNote.
     * @return the value of VerifyNote
     */
    public String getVerifyNote() {
        return (String)getAttributeInternal(VERIFYNOTE);
    }

    /**
     * Sets <code>value</code> as the attribute value for VerifyNote.
     * @param value value to set the VerifyNote
     */
    public void setVerifyNote(String value) {
        setAttributeInternal(VERIFYNOTE, value);
    }

    /**
     * Gets the attribute value for ReceiveQuantity, using the alias name ReceiveQuantity.
     * @return the value of ReceiveQuantity
     */
    public BigDecimal getReceiveQuantity() {
        return (BigDecimal)getAttributeInternal(RECEIVEQUANTITY);
    }

    /**
     * Sets <code>value</code> as the attribute value for ReceiveQuantity.
     * @param value value to set the ReceiveQuantity
     */
    public void setReceiveQuantity(BigDecimal value) {
        setAttributeInternal(RECEIVEQUANTITY, value);
    }

    /**
     * Gets the attribute value for OrderId, using the alias name OrderId.
     * @return the value of OrderId
     */
    public String getOrderId() {
        return (String)getAttributeInternal(ORDERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for OrderId.
     * @param value value to set the OrderId
     */
    public void setOrderId(String value) {
        setAttributeInternal(ORDERID, value);
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
     * Gets the attribute value for Cancelled, using the alias name Cancelled.
     * @return the value of Cancelled
     */
    public String getCancelled() {
        return (String)getAttributeInternal(CANCELLED);
    }

    /**
     * Sets <code>value</code> as the attribute value for Cancelled.
     * @param value value to set the Cancelled
     */
    public void setCancelled(String value) {
        setAttributeInternal(CANCELLED, value);
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
    public PurchaseOrdersImpl getPurchaseOrder() {
        return (PurchaseOrdersImpl)getAttributeInternal(PURCHASEORDER);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setPurchaseOrder(PurchaseOrdersImpl value) {
        setAttributeInternal(PURCHASEORDER, value);
    }


    /**
     * Validation method for PurchaseOrderLines.
     */
    public boolean validatePurchaseQuantity() {
        
        
        if(this.getState().equals(Constants.PO_STATE_PENDING_APPROVAL)&& this.getPurchaseQuantity()==null){
            return false;
        }
        
        return true;
    }

    /**
     * @param orderLineId key constituent
     * @param orderId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(DBSequence orderLineId, String orderId) {
        return new Key(new Object[]{orderLineId, orderId});
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
     * Add locking logic here.
     */
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

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        super.doDML(operation, e);
    }
}

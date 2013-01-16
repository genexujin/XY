package com.xy.scpms.model.eo;


import com.xy.scpms.model.common.BaseEntity;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jul 15 22:19:13 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FourTechContractImpl extends BaseEntity {
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
        super.doDML(operation, e);
//        if (operation == DML_INSERT) {
//            setLinkedContractLine(getContractNo());
//        } else if (operation == DML_DELETE) {
//            setLinkedContractLine(null);
//        }
    }

//    private void setLinkedContractLine(String batch) {
//        RowIterator contractLines = this.getContractLines();
//        while (contractLines.hasNext()) {
//            ContractLineImpl line = (ContractLineImpl)contractLines.next();
//            line.setFourContractBatch(batch);
//        }
//    }

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        Id {
            public Object get(FourTechContractImpl obj) {
                return obj.getId();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setId((Number)value);
            }
        }
        ,
        ContractNo {
            public Object get(FourTechContractImpl obj) {
                return obj.getContractNo();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setContractNo((String)value);
            }
        }
        ,
        Amount {
            public Object get(FourTechContractImpl obj) {
                return obj.getAmount();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setAmount((Number)value);
            }
        }
        ,
        Type {
            public Object get(FourTechContractImpl obj) {
                return obj.getType();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setType((String)value);
            }
        }
        ,
        RegNo {
            public Object get(FourTechContractImpl obj) {
                return obj.getRegNo();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setRegNo((String)value);
            }
        }
        ,
        SentDate {
            public Object get(FourTechContractImpl obj) {
                return obj.getSentDate();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setSentDate((Date)value);
            }
        }
        ,
        RcvDate {
            public Object get(FourTechContractImpl obj) {
                return obj.getRcvDate();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setRcvDate((Date)value);
            }
        }
        ,
        SubmitDate {
            public Object get(FourTechContractImpl obj) {
                return obj.getSubmitDate();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setSubmitDate((Date)value);
            }
        }
        ,
        RegDate {
            public Object get(FourTechContractImpl obj) {
                return obj.getRegDate();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setRegDate((Date)value);
            }
        }
        ,
        Creator {
            public Object get(FourTechContractImpl obj) {
                return obj.getCreator();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        CreationDate {
            public Object get(FourTechContractImpl obj) {
                return obj.getCreationDate();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Memo {
            public Object get(FourTechContractImpl obj) {
                return obj.getMemo();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setMemo((String)value);
            }
        }
        ,
        FilePath {
            public Object get(FourTechContractImpl obj) {
                return obj.getFilePath();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setFilePath((String)value);
            }
        }
        ,
        ContractId {
            public Object get(FourTechContractImpl obj) {
                return obj.getContractId();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setContractId((Number)value);
            }
        }
        ,
        LineId {
            public Object get(FourTechContractImpl obj) {
                return obj.getLineId();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setLineId((Number)value);
            }
        }
        ,
        Deleted {
            public Object get(FourTechContractImpl obj) {
                return obj.getDeleted();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setDeleted((String)value);
            }
        }
        ,
        LineContractNo {
            public Object get(FourTechContractImpl obj) {
                return obj.getLineContractNo();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setLineContractNo((String)value);
            }
        }
        ,
        LineShipMnftNo {
            public Object get(FourTechContractImpl obj) {
                return obj.getLineShipMnftNo();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setLineShipMnftNo((String)value);
            }
        }
        ,
        ContractLines {
            public Object get(FourTechContractImpl obj) {
                return obj.getContractLines();
            }

            public void put(FourTechContractImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(FourTechContractImpl object);

        public abstract void put(FourTechContractImpl object, Object value);

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
    public static final int CONTRACTNO = AttributesEnum.ContractNo.index();
    public static final int AMOUNT = AttributesEnum.Amount.index();
    public static final int TYPE = AttributesEnum.Type.index();
    public static final int REGNO = AttributesEnum.RegNo.index();
    public static final int SENTDATE = AttributesEnum.SentDate.index();
    public static final int RCVDATE = AttributesEnum.RcvDate.index();
    public static final int SUBMITDATE = AttributesEnum.SubmitDate.index();
    public static final int REGDATE = AttributesEnum.RegDate.index();
    public static final int CREATOR = AttributesEnum.Creator.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int MEMO = AttributesEnum.Memo.index();
    public static final int FILEPATH = AttributesEnum.FilePath.index();
    public static final int CONTRACTID = AttributesEnum.ContractId.index();
    public static final int LINEID = AttributesEnum.LineId.index();
    public static final int DELETED = AttributesEnum.Deleted.index();
    public static final int LINECONTRACTNO = AttributesEnum.LineContractNo.index();
    public static final int LINESHIPMNFTNO = AttributesEnum.LineShipMnftNo.index();
    public static final int CONTRACTLINES = AttributesEnum.ContractLines.index();

    /**
     * This is the default constructor (do not remove).
     */
    public FourTechContractImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.xy.scpms.model.eo.FourTechContract");
    }

    /**
     * Gets the attribute value for Id, using the alias name Id.
     * @return the value of Id
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
     * Gets the attribute value for ContractNo, using the alias name ContractNo.
     * @return the value of ContractNo
     */
    public String getContractNo() {
        return (String)getAttributeInternal(CONTRACTNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for ContractNo.
     * @param value value to set the ContractNo
     */
    public void setContractNo(String value) {
        setAttributeInternal(CONTRACTNO, value);
    }

    /**
     * Gets the attribute value for Amount, using the alias name Amount.
     * @return the value of Amount
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
     * Gets the attribute value for Type, using the alias name Type.
     * @return the value of Type
     */
    public String getType() {
        return (String)getAttributeInternal(TYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for Type.
     * @param value value to set the Type
     */
    public void setType(String value) {
        setAttributeInternal(TYPE, value);
    }

    /**
     * Gets the attribute value for RegNo, using the alias name RegNo.
     * @return the value of RegNo
     */
    public String getRegNo() {
        return (String)getAttributeInternal(REGNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for RegNo.
     * @param value value to set the RegNo
     */
    public void setRegNo(String value) {
        setAttributeInternal(REGNO, value);
    }

    /**
     * Gets the attribute value for SentDate, using the alias name SentDate.
     * @return the value of SentDate
     */
    public Date getSentDate() {
        return (Date)getAttributeInternal(SENTDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SentDate.
     * @param value value to set the SentDate
     */
    public void setSentDate(Date value) {
        setAttributeInternal(SENTDATE, value);
    }

    /**
     * Gets the attribute value for RcvDate, using the alias name RcvDate.
     * @return the value of RcvDate
     */
    public Date getRcvDate() {
        return (Date)getAttributeInternal(RCVDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for RcvDate.
     * @param value value to set the RcvDate
     */
    public void setRcvDate(Date value) {
        setAttributeInternal(RCVDATE, value);
    }

    /**
     * Gets the attribute value for SubmitDate, using the alias name SubmitDate.
     * @return the value of SubmitDate
     */
    public Date getSubmitDate() {
        return (Date)getAttributeInternal(SUBMITDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SubmitDate.
     * @param value value to set the SubmitDate
     */
    public void setSubmitDate(Date value) {
        setAttributeInternal(SUBMITDATE, value);
    }

    /**
     * Gets the attribute value for RegDate, using the alias name RegDate.
     * @return the value of RegDate
     */
    public Date getRegDate() {
        return (Date)getAttributeInternal(REGDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for RegDate.
     * @param value value to set the RegDate
     */
    public void setRegDate(Date value) {
        setAttributeInternal(REGDATE, value);
    }

    /**
     * Gets the attribute value for Creator, using the alias name Creator.
     * @return the value of Creator
     */
    public String getCreator() {
        return (String)getAttributeInternal(CREATOR);
    }

    /**
     * Gets the attribute value for CreationDate, using the alias name CreationDate.
     * @return the value of CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**
     * Gets the attribute value for Memo, using the alias name Memo.
     * @return the value of Memo
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
     * Gets the attribute value for FilePath, using the alias name FilePath.
     * @return the value of FilePath
     */
    public String getFilePath() {
        return (String)getAttributeInternal(FILEPATH);
    }

    /**
     * Sets <code>value</code> as the attribute value for FilePath.
     * @param value value to set the FilePath
     */
    public void setFilePath(String value) {
        setAttributeInternal(FILEPATH, value);
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
     * Gets the attribute value for LineContractNo, using the alias name LineContractNo.
     * @return the value of LineContractNo
     */
    public String getLineContractNo() {
        return (String)getAttributeInternal(LINECONTRACTNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for LineContractNo.
     * @param value value to set the LineContractNo
     */
    public void setLineContractNo(String value) {
        setAttributeInternal(LINECONTRACTNO, value);
    }

    /**
     * Gets the attribute value for LineShipMnftNo, using the alias name LineShipMnftNo.
     * @return the value of LineShipMnftNo
     */
    public String getLineShipMnftNo() {
        return (String)getAttributeInternal(LINESHIPMNFTNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for LineShipMnftNo.
     * @param value value to set the LineShipMnftNo
     */
    public void setLineShipMnftNo(String value) {
        setAttributeInternal(LINESHIPMNFTNO, value);
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
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getContractLines() {
        return (RowIterator)getAttributeInternal(CONTRACTLINES);
    }


    /**
     * @param id key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number id) {
        return new Key(new Object[]{id});
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);

    }
}

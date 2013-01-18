package com.xy.scpms.model.eo;


import com.xy.scpms.model.common.BaseEntity;
import com.xy.scpms.model.utils.Codes;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.RowSet;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Aug 18 21:23:18 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ContractImpl extends BaseEntity {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        Id {
            public Object get(ContractImpl obj) {
                return obj.getId();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setId((Number)value);
            }
        }
        ,
        ContractNo {
            public Object get(ContractImpl obj) {
                return obj.getContractNo();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setContractNo((String)value);
            }
        }
        ,
        ProjectName {
            public Object get(ContractImpl obj) {
                return obj.getProjectName();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setProjectName((String)value);
            }
        }
        ,
        ProjectManager {
            public Object get(ContractImpl obj) {
                return obj.getProjectManager();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setProjectManager((String)value);
            }
        }
        ,
        RegionManager {
            public Object get(ContractImpl obj) {
                return obj.getRegionManager();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setRegionManager((String)value);
            }
        }
        ,
        ShipModel {
            public Object get(ContractImpl obj) {
                return obj.getShipModel();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setShipModel((String)value);
            }
        }
        ,
        DesignDept {
            public Object get(ContractImpl obj) {
                return obj.getDesignDept();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setDesignDept((String)value);
            }
        }
        ,
        ShipNoInternal {
            public Object get(ContractImpl obj) {
                return obj.getShipNoInternal();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setShipNoInternal((String)value);
            }
        }
        ,
        ContractEntity {
            public Object get(ContractImpl obj) {
                return obj.getContractEntity();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setContractEntity((String)value);
            }
        }
        ,
        CustomerContactor {
            public Object get(ContractImpl obj) {
                return obj.getCustomerContactor();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setCustomerContactor((String)value);
            }
        }
        ,
        DesignPhase {
            public Object get(ContractImpl obj) {
                return obj.getDesignPhase();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setDesignPhase((String)value);
            }
        }
        ,
        AuthType {
            public Object get(ContractImpl obj) {
                return obj.getAuthType();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setAuthType((String)value);
            }
        }
        ,
        AuthName {
            public Object get(ContractImpl obj) {
                return obj.getAuthName();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setAuthName((String)value);
            }
        }
        ,
        AuthorizerType {
            public Object get(ContractImpl obj) {
                return obj.getAuthorizerType();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setAuthorizerType((String)value);
            }
        }
        ,
        Status {
            public Object get(ContractImpl obj) {
                return obj.getStatus();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        }
        ,
        Memo {
            public Object get(ContractImpl obj) {
                return obj.getMemo();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setMemo((String)value);
            }
        }
        ,
        AgreementImgUrl {
            public Object get(ContractImpl obj) {
                return obj.getAgreementImgUrl();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setAgreementImgUrl((String)value);
            }
        }
        ,
        CreatedBy {
            public Object get(ContractImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        CreatedAt {
            public Object get(ContractImpl obj) {
                return obj.getCreatedAt();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Deleted {
            public Object get(ContractImpl obj) {
                return obj.getDeleted();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setDeleted((String)value);
            }
        }
        ,
        ContractType {
            public Object get(ContractImpl obj) {
                return obj.getContractType();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setContractType((String)value);
            }
        }
        ,
        ParentContractId {
            public Object get(ContractImpl obj) {
                return obj.getParentContractId();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setParentContractId((Number)value);
            }
        }
        ,
        FirstDesignFee {
            public Object get(ContractImpl obj) {
                return obj.getFirstDesignFee();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setFirstDesignFee((Number)value);
            }
        }
        ,
        TotalAmount {
            public Object get(ContractImpl obj) {
                return obj.getTotalAmount();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setTotalAmount((Number)value);
            }
        }
        ,
        RefinedFlag {
            public Object get(ContractImpl obj) {
                return obj.getRefinedFlag();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setRefinedFlag((String)value);
            }
        }
        ,
        CustomerId {
            public Object get(ContractImpl obj) {
                return obj.getCustomerId();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setCustomerId((String)value);
            }
        }
        ,
        CustomerContactName {
            public Object get(ContractImpl obj) {
                return obj.getCustomerContactName();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setCustomerContactName((String)value);
            }
        }
        ,
        ParentContractNo {
            public Object get(ContractImpl obj) {
                return obj.getParentContractNo();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setParentContractNo((String)value);
            }
        }
        ,
        UnitWeight {
            public Object get(ContractImpl obj) {
                return obj.getUnitWeight();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setUnitWeight((Number)value);
            }
        }
        ,
        TotalWeight {
            public Object get(ContractImpl obj) {
                return obj.getTotalWeight();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setTotalWeight((Number)value);
            }
        }
        ,
        TotalCompleteWeight {
            public Object get(ContractImpl obj) {
                return obj.getTotalCompleteWeight();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setTotalCompleteWeight((Number)value);
            }
        }
        ,
        Contract {
            public Object get(ContractImpl obj) {
                return obj.getContract();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        ParentContractIdContract {
            public Object get(ContractImpl obj) {
                return obj.getParentContractIdContract();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setParentContractIdContract((ContractImpl)value);
            }
        }
        ,
        ContractAttachment {
            public Object get(ContractImpl obj) {
                return obj.getContractAttachment();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        ContractLine {
            public Object get(ContractImpl obj) {
                return obj.getContractLine();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        PrjMgrAccessor {
            public Object get(ContractImpl obj) {
                return obj.getPrjMgrAccessor();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setPrjMgrAccessor((EntityImpl)value);
            }
        }
        ,
        RegionMgrAccessor {
            public Object get(ContractImpl obj) {
                return obj.getRegionMgrAccessor();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setRegionMgrAccessor((EntityImpl)value);
            }
        }
        ,
        Users {
            public Object get(ContractImpl obj) {
                return obj.getUsers();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setUsers((EntityImpl)value);
            }
        }
        ,
        ChangeHistoryView {
            public Object get(ContractImpl obj) {
                return obj.getChangeHistoryView();
            }

            public void put(ContractImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(ContractImpl object);

        public abstract void put(ContractImpl object, Object value);

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
    public static final int CONTRACTNO = AttributesEnum.ContractNo.index();
    public static final int PROJECTNAME = AttributesEnum.ProjectName.index();
    public static final int PROJECTMANAGER = AttributesEnum.ProjectManager.index();
    public static final int REGIONMANAGER = AttributesEnum.RegionManager.index();
    public static final int SHIPMODEL = AttributesEnum.ShipModel.index();
    public static final int DESIGNDEPT = AttributesEnum.DesignDept.index();
    public static final int SHIPNOINTERNAL = AttributesEnum.ShipNoInternal.index();
    public static final int CONTRACTENTITY = AttributesEnum.ContractEntity.index();
    public static final int CUSTOMERCONTACTOR = AttributesEnum.CustomerContactor.index();
    public static final int DESIGNPHASE = AttributesEnum.DesignPhase.index();
    public static final int AUTHTYPE = AttributesEnum.AuthType.index();
    public static final int AUTHNAME = AttributesEnum.AuthName.index();
    public static final int AUTHORIZERTYPE = AttributesEnum.AuthorizerType.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int MEMO = AttributesEnum.Memo.index();
    public static final int AGREEMENTIMGURL = AttributesEnum.AgreementImgUrl.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATEDAT = AttributesEnum.CreatedAt.index();
    public static final int DELETED = AttributesEnum.Deleted.index();
    public static final int CONTRACTTYPE = AttributesEnum.ContractType.index();
    public static final int PARENTCONTRACTID = AttributesEnum.ParentContractId.index();
    public static final int FIRSTDESIGNFEE = AttributesEnum.FirstDesignFee.index();
    public static final int TOTALAMOUNT = AttributesEnum.TotalAmount.index();
    public static final int REFINEDFLAG = AttributesEnum.RefinedFlag.index();
    public static final int CUSTOMERID = AttributesEnum.CustomerId.index();
    public static final int CUSTOMERCONTACTNAME = AttributesEnum.CustomerContactName.index();
    public static final int PARENTCONTRACTNO = AttributesEnum.ParentContractNo.index();
    public static final int UNITWEIGHT = AttributesEnum.UnitWeight.index();
    public static final int TOTALWEIGHT = AttributesEnum.TotalWeight.index();
    public static final int TOTALCOMPLETEWEIGHT = AttributesEnum.TotalCompleteWeight.index();
    public static final int CONTRACT = AttributesEnum.Contract.index();
    public static final int PARENTCONTRACTIDCONTRACT = AttributesEnum.ParentContractIdContract.index();
    public static final int CONTRACTATTACHMENT = AttributesEnum.ContractAttachment.index();
    public static final int CONTRACTLINE = AttributesEnum.ContractLine.index();
    public static final int PRJMGRACCESSOR = AttributesEnum.PrjMgrAccessor.index();
    public static final int REGIONMGRACCESSOR = AttributesEnum.RegionMgrAccessor.index();
    public static final int USERS = AttributesEnum.Users.index();
    public static final int CHANGEHISTORYVIEW = AttributesEnum.ChangeHistoryView.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ContractImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.xy.scpms.model.eo.Contract");
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
     * Gets the attribute value for ProjectName, using the alias name ProjectName.
     * @return the value of ProjectName
     */
    public String getProjectName() {
        return (String)getAttributeInternal(PROJECTNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for ProjectName.
     * @param value value to set the ProjectName
     */
    public void setProjectName(String value) {
        setAttributeInternal(PROJECTNAME, value);
    }

    /**
     * Gets the attribute value for ProjectManager, using the alias name ProjectManager.
     * @return the value of ProjectManager
     */
    public String getProjectManager() {
        return (String)getAttributeInternal(PROJECTMANAGER);
    }

    /**
     * Sets <code>value</code> as the attribute value for ProjectManager.
     * @param value value to set the ProjectManager
     */
    public void setProjectManager(String value) {
        setAttributeInternal(PROJECTMANAGER, value);
    }

    /**
     * Gets the attribute value for RegionManager, using the alias name RegionManager.
     * @return the value of RegionManager
     */
    public String getRegionManager() {
        return (String)getAttributeInternal(REGIONMANAGER);
    }

    /**
     * Sets <code>value</code> as the attribute value for RegionManager.
     * @param value value to set the RegionManager
     */
    public void setRegionManager(String value) {
        setAttributeInternal(REGIONMANAGER, value);
    }

    /**
     * Gets the attribute value for ShipModel, using the alias name ShipModel.
     * @return the value of ShipModel
     */
    public String getShipModel() {
        return (String)getAttributeInternal(SHIPMODEL);
    }

    /**
     * Sets <code>value</code> as the attribute value for ShipModel.
     * @param value value to set the ShipModel
     */
    public void setShipModel(String value) {
        setAttributeInternal(SHIPMODEL, value);
    }

    /**
     * Gets the attribute value for DesignDept, using the alias name DesignDept.
     * @return the value of DesignDept
     */
    public String getDesignDept() {
        return (String)getAttributeInternal(DESIGNDEPT);
    }

    /**
     * Sets <code>value</code> as the attribute value for DesignDept.
     * @param value value to set the DesignDept
     */
    public void setDesignDept(String value) {
        setAttributeInternal(DESIGNDEPT, value);
    }

    /**
     * Gets the attribute value for ShipNoInternal, using the alias name ShipNoInternal.
     * @return the value of ShipNoInternal
     */
    public String getShipNoInternal() {
        return (String)getAttributeInternal(SHIPNOINTERNAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for ShipNoInternal.
     * @param value value to set the ShipNoInternal
     */
    public void setShipNoInternal(String value) {
        setAttributeInternal(SHIPNOINTERNAL, value);
    }

    /**
     * Gets the attribute value for ContractEntity, using the alias name ContractEntity.
     * @return the value of ContractEntity
     */
    public String getContractEntity() {
        return (String)getAttributeInternal(CONTRACTENTITY);
    }

    /**
     * Sets <code>value</code> as the attribute value for ContractEntity.
     * @param value value to set the ContractEntity
     */
    public void setContractEntity(String value) {
        setAttributeInternal(CONTRACTENTITY, value);
    }

    /**
     * Gets the attribute value for CustomerContactor, using the alias name CustomerContactor.
     * @return the value of CustomerContactor
     */
    public String getCustomerContactor() {
        return (String)getAttributeInternal(CUSTOMERCONTACTOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for CustomerContactor.
     * @param value value to set the CustomerContactor
     */
    public void setCustomerContactor(String value) {
        setAttributeInternal(CUSTOMERCONTACTOR, value);
    }

    /**
     * Gets the attribute value for DesignPhase, using the alias name DesignPhase.
     * @return the value of DesignPhase
     */
    public String getDesignPhase() {
        return (String)getAttributeInternal(DESIGNPHASE);
    }

    /**
     * Sets <code>value</code> as the attribute value for DesignPhase.
     * @param value value to set the DesignPhase
     */
    public void setDesignPhase(String value) {
        setAttributeInternal(DESIGNPHASE, value);
    }

    /**
     * Gets the attribute value for AuthType, using the alias name AuthType.
     * @return the value of AuthType
     */
    public String getAuthType() {
        return (String)getAttributeInternal(AUTHTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for AuthType.
     * @param value value to set the AuthType
     */
    public void setAuthType(String value) {
        setAttributeInternal(AUTHTYPE, value);
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
     * Gets the attribute value for AuthorizerType, using the alias name AuthorizerType.
     * @return the value of AuthorizerType
     */
    public String getAuthorizerType() {
        return (String)getAttributeInternal(AUTHORIZERTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for AuthorizerType.
     * @param value value to set the AuthorizerType
     */
    public void setAuthorizerType(String value) {
        setAttributeInternal(AUTHORIZERTYPE, value);
    }

    /**
     * Gets the attribute value for Status, using the alias name Status.
     * @return the value of Status
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
     * Gets the attribute value for AgreementImgUrl, using the alias name AgreementImgUrl.
     * @return the value of AgreementImgUrl
     */
    public String getAgreementImgUrl() {
        return (String)getAttributeInternal(AGREEMENTIMGURL);
    }

    /**
     * Sets <code>value</code> as the attribute value for AgreementImgUrl.
     * @param value value to set the AgreementImgUrl
     */
    public void setAgreementImgUrl(String value) {
        setAttributeInternal(AGREEMENTIMGURL, value);
    }

    /**
     * Gets the attribute value for CreatedBy, using the alias name CreatedBy.
     * @return the value of CreatedBy
     */
    public String getCreatedBy() {
        return (String)getAttributeInternal(CREATEDBY);
    }

    /**
     * Gets the attribute value for CreatedAt, using the alias name CreatedAt.
     * @return the value of CreatedAt
     */
    public Date getCreatedAt() {
        return (Date)getAttributeInternal(CREATEDAT);
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
     * Gets the attribute value for ContractType, using the alias name ContractType.
     * @return the value of ContractType
     */
    public String getContractType() {
        return (String)getAttributeInternal(CONTRACTTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ContractType.
     * @param value value to set the ContractType
     */
    public void setContractType(String value) {
        setAttributeInternal(CONTRACTTYPE, value);
    }

    /**
     * Gets the attribute value for ParentContractId, using the alias name ParentContractId.
     * @return the value of ParentContractId
     */
    public Number getParentContractId() {
        return (Number)getAttributeInternal(PARENTCONTRACTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ParentContractId.
     * @param value value to set the ParentContractId
     */
    public void setParentContractId(Number value) {
        setAttributeInternal(PARENTCONTRACTID, value);
    }

    /**
     * Gets the attribute value for FirstDesignFee, using the alias name FirstDesignFee.
     * @return the value of FirstDesignFee
     */
    public Number getFirstDesignFee() {
        return (Number)getAttributeInternal(FIRSTDESIGNFEE);
    }

    /**
     * Sets <code>value</code> as the attribute value for FirstDesignFee.
     * @param value value to set the FirstDesignFee
     */
    public void setFirstDesignFee(Number value) {
        setAttributeInternal(FIRSTDESIGNFEE, value);
    }

    /**
     * Gets the attribute value for TotalAmount, using the alias name TotalAmount.
     * @return the value of TotalAmount
     */
    public Number getTotalAmount() {
        return (Number)getAttributeInternal(TOTALAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for TotalAmount.
     * @param value value to set the TotalAmount
     */
    public void setTotalAmount(Number value) {
        setAttributeInternal(TOTALAMOUNT, value);
    }

    /**
     * Gets the attribute value for RefinedFlag, using the alias name RefinedFlag.
     * @return the value of RefinedFlag
     */
    public String getRefinedFlag() {
        return (String)getAttributeInternal(REFINEDFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for RefinedFlag.
     * @param value value to set the RefinedFlag
     */
    public void setRefinedFlag(String value) {
        setAttributeInternal(REFINEDFLAG, value);
    }

    /**
     * Gets the attribute value for CustomerId, using the alias name CustomerId.
     * @return the value of CustomerId
     */
    public String getCustomerId() {
        return (String)getAttributeInternal(CUSTOMERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CustomerId.
     * @param value value to set the CustomerId
     */
    public void setCustomerId(String value) {
        setAttributeInternal(CUSTOMERID, value);
    }

    /**
     * Gets the attribute value for CustomerContactName, using the alias name CustomerContactName.
     * @return the value of CustomerContactName
     */
    public String getCustomerContactName() {
        return (String)getAttributeInternal(CUSTOMERCONTACTNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for CustomerContactName.
     * @param value value to set the CustomerContactName
     */
    public void setCustomerContactName(String value) {
        setAttributeInternal(CUSTOMERCONTACTNAME, value);
    }

    /**
     * Gets the attribute value for ParentContractNo, using the alias name ParentContractNo.
     * @return the value of ParentContractNo
     */
    public String getParentContractNo() {
        return (String)getAttributeInternal(PARENTCONTRACTNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for ParentContractNo.
     * @param value value to set the ParentContractNo
     */
    public void setParentContractNo(String value) {
        setAttributeInternal(PARENTCONTRACTNO, value);
    }

    /**
     * Gets the attribute value for UnitWeight, using the alias name UnitWeight.
     * @return the value of UnitWeight
     */
    public Number getUnitWeight() {
        return (Number)getAttributeInternal(UNITWEIGHT);
    }

    /**
     * Sets <code>value</code> as the attribute value for UnitWeight.
     * @param value value to set the UnitWeight
     */
    public void setUnitWeight(Number value) {
        setAttributeInternal(UNITWEIGHT, value);
    }

    /**
     * Gets the attribute value for TotalWeight, using the alias name TotalWeight.
     * @return the value of TotalWeight
     */
    public Number getTotalWeight() {
        return (Number)getAttributeInternal(TOTALWEIGHT);
    }

    /**
     * Sets <code>value</code> as the attribute value for TotalWeight.
     * @param value value to set the TotalWeight
     */
    public void setTotalWeight(Number value) {
        setAttributeInternal(TOTALWEIGHT, value);
    }

    /**
     * Gets the attribute value for TotalCompleteWeight, using the alias name TotalCompleteWeight.
     * @return the value of TotalCompleteWeight
     */
    public Number getTotalCompleteWeight() {
        return (Number)getAttributeInternal(TOTALCOMPLETEWEIGHT);
    }

    /**
     * Sets <code>value</code> as the attribute value for TotalCompleteWeight.
     * @param value value to set the TotalCompleteWeight
     */
    public void setTotalCompleteWeight(Number value) {
        setAttributeInternal(TOTALCOMPLETEWEIGHT, value);
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
    public RowIterator getContract() {
        return (RowIterator)getAttributeInternal(CONTRACT);
    }

    /**
     * @return the associated entity ContractImpl.
     */
    public ContractImpl getParentContractIdContract() {
        return (ContractImpl)getAttributeInternal(PARENTCONTRACTIDCONTRACT);
    }

    /**
     * Sets <code>value</code> as the associated entity ContractImpl.
     */
    public void setParentContractIdContract(ContractImpl value) {
        setAttributeInternal(PARENTCONTRACTIDCONTRACT, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getContractAttachment() {
        return (RowIterator)getAttributeInternal(CONTRACTATTACHMENT);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getContractLine() {
        return (RowIterator)getAttributeInternal(CONTRACTLINE);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getPrjMgrAccessor() {
        return (EntityImpl)getAttributeInternal(PRJMGRACCESSOR);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setPrjMgrAccessor(EntityImpl value) {
        setAttributeInternal(PRJMGRACCESSOR, value);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getRegionMgrAccessor() {
        return (EntityImpl)getAttributeInternal(REGIONMGRACCESSOR);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setRegionMgrAccessor(EntityImpl value) {
        setAttributeInternal(REGIONMGRACCESSOR, value);
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
     * Gets the view accessor <code>RowSet</code> ChangeHistoryView.
     */
    public RowSet getChangeHistoryView() {
        return (RowSet)getAttributeInternal(CHANGEHISTORYVIEW);
    }


    /**
     * @param id key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number id) {
        return new Key(new Object[]{id});
    }

    /**
     * Validation method for Contract.
     */
    public boolean validateDocs() {
        return true;
    }


    /**
     * Validation method for Contract.
     */
    public boolean validateCustomer() {
        if (getCustomerId() == null || this.getCustomerContactor() == null)
            return false;
        else
            return true;
    }


    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {

        super.create(attributeList);

        setAttribute("Status", Codes.COL_VALUE_STATUS_DRAFT);
    }


}
package com.xy.scpms.model.vo;

import com.xy.scpms.model.common.BaseEntity;

import com.xy.scpms.model.eo.ContractImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jun 01 13:09:36 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ApprovalInProcessViewRowImpl extends ViewRowImpl {


    public static final int ENTITY_APPROVALINPROCESS = 0;
    public static final int ENTITY_CONTRACT = 1;
    public static final int ENTITY_CREATOR = 2;
    public static final int ENTITY_APPROVER = 3;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
  public enum AttributesEnum {
        Id {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getId();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setId((Number)value);
            }
        }
        ,
        RoutingNo {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getRoutingNo();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setRoutingNo((Number)value);
            }
        }
        ,
        Seq {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getSeq();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setSeq((Number)value);
            }
        }
        ,
        Approver {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getApprover();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setApprover((String)value);
            }
        }
        ,
        Outcome {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getOutcome();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setOutcome((String)value);
            }
        }
        ,
        ContractId {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getContractId();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setContractId((Number)value);
            }
        }
        ,
        Memo {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getMemo();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setMemo((String)value);
            }
        }
        ,
        ApprovalDate {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getApprovalDate();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setApprovalDate((Date)value);
            }
        }
        ,
        ApprovalRole {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getApprovalRole();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setApprovalRole((String)value);
            }
        }
        ,
        ContractNo {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getContractNo();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setContractNo((String)value);
            }
        }
        ,
        Creator {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getCreator();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setCreator((String)value);
            }
        }
        ,
        Type {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getType();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setType((String)value);
            }
        }
        ,
        AuthName {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getAuthName();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setAuthName((String)value);
            }
        }
        ,
        ProjectName {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getProjectName();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setProjectName((String)value);
            }
        }
        ,
        TotalAmount {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getTotalAmount();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setTotalAmount((Number)value);
            }
        }
        ,
        ContractEntity {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getContractEntity();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setContractEntity((String)value);
            }
        }
        ,
        ContractType {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getContractType();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setContractType((String)value);
            }
        }
        ,
        Id1 {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getId1();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setId1((Number)value);
            }
        }
        ,
        FullName {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getFullName();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setFullName((String)value);
            }
        }
        ,
        Username {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getUsername();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setUsername((String)value);
            }
        }
        ,
        FullName1 {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getFullName1();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setFullName1((String)value);
            }
        }
        ,
        Username1 {
            public Object get(ApprovalInProcessViewRowImpl obj) {
                return obj.getUsername1();
            }

            public void put(ApprovalInProcessViewRowImpl obj, Object value) {
                obj.setUsername1((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

    public abstract Object get(ApprovalInProcessViewRowImpl object);

    public abstract void put(ApprovalInProcessViewRowImpl object,
                             Object value);

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
    public static final int ROUTINGNO = AttributesEnum.RoutingNo.index();
    public static final int SEQ = AttributesEnum.Seq.index();
    public static final int APPROVER = AttributesEnum.Approver.index();
    public static final int OUTCOME = AttributesEnum.Outcome.index();
    public static final int CONTRACTID = AttributesEnum.ContractId.index();
    public static final int MEMO = AttributesEnum.Memo.index();
    public static final int APPROVALDATE = AttributesEnum.ApprovalDate.index();
    public static final int APPROVALROLE = AttributesEnum.ApprovalRole.index();
    public static final int CONTRACTNO = AttributesEnum.ContractNo.index();
    public static final int CREATOR = AttributesEnum.Creator.index();
    public static final int TYPE = AttributesEnum.Type.index();
    public static final int AUTHNAME = AttributesEnum.AuthName.index();
    public static final int PROJECTNAME = AttributesEnum.ProjectName.index();
    public static final int TOTALAMOUNT = AttributesEnum.TotalAmount.index();
    public static final int CONTRACTENTITY = AttributesEnum.ContractEntity.index();
    public static final int CONTRACTTYPE = AttributesEnum.ContractType.index();
    public static final int ID1 = AttributesEnum.Id1.index();
    public static final int FULLNAME = AttributesEnum.FullName.index();
    public static final int USERNAME = AttributesEnum.Username.index();
    public static final int FULLNAME1 = AttributesEnum.FullName1.index();
    public static final int USERNAME1 = AttributesEnum.Username1.index();

    /**
     * This is the default constructor (do not remove).
     */
  public ApprovalInProcessViewRowImpl() {
  }

  /**
   * Gets ApprovalInProcess entity object.
   * @return the ApprovalInProcess
   */
  public BaseEntity getApprovalInProcess() {
    return (BaseEntity)getEntity(0);
  }

    /**
     * Gets Contract entity object.
     * @return the Contract
     */
    public ContractImpl getContract() {
        return (ContractImpl)getEntity(ENTITY_CONTRACT);
    }


    /**
     * Gets the attribute value for ID using the alias name Id.
     * @return the ID
     */
  public Number getId() {
    return (Number) getAttributeInternal(ID);
  }

  /**
   * Sets <code>value</code> as attribute value for ID using the alias name Id.
   * @param value value to set the ID
   */
  public void setId(Number value) {
    setAttributeInternal(ID, value);
  }

  /**
   * Gets the attribute value for ROUTING_NO using the alias name RoutingNo.
   * @return the ROUTING_NO
   */
  public Number getRoutingNo() {
    return (Number) getAttributeInternal(ROUTINGNO);
  }

  /**
   * Sets <code>value</code> as attribute value for ROUTING_NO using the alias name RoutingNo.
   * @param value value to set the ROUTING_NO
   */
  public void setRoutingNo(Number value) {
    setAttributeInternal(ROUTINGNO, value);
  }

  /**
   * Gets the attribute value for SEQ using the alias name Seq.
   * @return the SEQ
   */
  public Number getSeq() {
    return (Number) getAttributeInternal(SEQ);
  }

  /**
   * Sets <code>value</code> as attribute value for SEQ using the alias name Seq.
   * @param value value to set the SEQ
   */
  public void setSeq(Number value) {
    setAttributeInternal(SEQ, value);
  }

  /**
     * Gets the attribute value for APPROVER using the alias name Approver.
     * @return the APPROVER
     */
    public String getApprover() {
        return (String) getAttributeInternal(APPROVER);
    }

  /**
   * Sets <code>value</code> as attribute value for APPROVER using the alias name Approver.
   * @param value value to set the APPROVER
   */
  public void setApprover(String value) {
    setAttributeInternal(APPROVER, value);
  }

  /**
   * Gets the attribute value for OUTCOME using the alias name Outcome.
   * @return the OUTCOME
   */
  public String getOutcome() {
    return (String) getAttributeInternal(OUTCOME);
  }

  /**
   * Sets <code>value</code> as attribute value for OUTCOME using the alias name Outcome.
   * @param value value to set the OUTCOME
   */
  public void setOutcome(String value) {
    setAttributeInternal(OUTCOME, value);
  }

  /**
   * Gets the attribute value for CONTRACT_ID using the alias name ContractId.
   * @return the CONTRACT_ID
   */
  public Number getContractId() {
    return (Number) getAttributeInternal(CONTRACTID);
  }

  /**
   * Sets <code>value</code> as attribute value for CONTRACT_ID using the alias name ContractId.
   * @param value value to set the CONTRACT_ID
   */
  public void setContractId(Number value) {
    setAttributeInternal(CONTRACTID, value);
  }

  /**
   * Gets the attribute value for MEMO using the alias name Memo.
   * @return the MEMO
   */
  public String getMemo() {
    return (String) getAttributeInternal(MEMO);
  }

  /**
   * Sets <code>value</code> as attribute value for MEMO using the alias name Memo.
   * @param value value to set the MEMO
   */
  public void setMemo(String value) {
    setAttributeInternal(MEMO, value);
  }

  /**
   * Gets the attribute value for APPROVAL_DATE using the alias name ApprovalDate.
   * @return the APPROVAL_DATE
   */
  public Date getApprovalDate() {
    return (Date) getAttributeInternal(APPROVALDATE);
  }

  /**
   * Sets <code>value</code> as attribute value for APPROVAL_DATE using the alias name ApprovalDate.
   * @param value value to set the APPROVAL_DATE
   */
  public void setApprovalDate(Date value) {
    setAttributeInternal(APPROVALDATE, value);
  }

  /**
   * Gets the attribute value for APPROVAL_ROLE using the alias name ApprovalRole.
   * @return the APPROVAL_ROLE
   */
  public String getApprovalRole() {
    return (String) getAttributeInternal(APPROVALROLE);
  }

  /**
   * Sets <code>value</code> as attribute value for APPROVAL_ROLE using the alias name ApprovalRole.
   * @param value value to set the APPROVAL_ROLE
   */
  public void setApprovalRole(String value) {
    setAttributeInternal(APPROVALROLE, value);
  }

  /**
   * Gets the attribute value for CONTRACT_NO using the alias name ContractNo.
   * @return the CONTRACT_NO
   */
  public String getContractNo() {
    return (String) getAttributeInternal(CONTRACTNO);
  }

  /**
   * Sets <code>value</code> as attribute value for CONTRACT_NO using the alias name ContractNo.
   * @param value value to set the CONTRACT_NO
   */
  public void setContractNo(String value) {
    setAttributeInternal(CONTRACTNO, value);
  }

  /**
     * Gets the attribute value for CREATOR using the alias name Creator.
     * @return the CREATOR
     */
    public String getCreator() {
        return (String)getAttributeInternal(CREATOR);
    }


    /**
     * Sets <code>value</code> as attribute value for CREATOR using the alias name Creator.
     * @param value value to set the CREATOR
     */
  public void setCreator(String value) {
    setAttributeInternal(CREATOR, value);
  }

    /**
     * Gets the attribute value for TYPE using the alias name Type.
     * @return the TYPE
     */
    public String getType() {
        return (String) getAttributeInternal(TYPE);
    }

    /**
     * Sets <code>value</code> as attribute value for TYPE using the alias name Type.
     * @param value value to set the TYPE
     */
    public void setType(String value) {
        setAttributeInternal(TYPE, value);
    }

    /**
     * Gets the attribute value for AUTH_NAME using the alias name AuthName.
     * @return the AUTH_NAME
     */
    public String getAuthName() {
        return (String) getAttributeInternal(AUTHNAME);
    }

    /**
     * Sets <code>value</code> as attribute value for AUTH_NAME using the alias name AuthName.
     * @param value value to set the AUTH_NAME
     */
    public void setAuthName(String value) {
        setAttributeInternal(AUTHNAME, value);
    }

    /**
     * Gets the attribute value for PROJECT_NAME using the alias name ProjectName.
     * @return the PROJECT_NAME
     */
    public String getProjectName() {
        return (String) getAttributeInternal(PROJECTNAME);
    }

    /**
     * Sets <code>value</code> as attribute value for PROJECT_NAME using the alias name ProjectName.
     * @param value value to set the PROJECT_NAME
     */
    public void setProjectName(String value) {
        setAttributeInternal(PROJECTNAME, value);
    }

    /**
     * Gets the attribute value for TOTAL_AMOUNT using the alias name TotalAmount.
     * @return the TOTAL_AMOUNT
     */
    public Number getTotalAmount() {
        return (Number) getAttributeInternal(TOTALAMOUNT);
    }

    /**
     * Sets <code>value</code> as attribute value for TOTAL_AMOUNT using the alias name TotalAmount.
     * @param value value to set the TOTAL_AMOUNT
     */
    public void setTotalAmount(Number value) {
        setAttributeInternal(TOTALAMOUNT, value);
    }

    /**
     * Gets the attribute value for CONTRACT_ENTITY using the alias name ContractEntity.
     * @return the CONTRACT_ENTITY
     */
    public String getContractEntity() {
        return (String) getAttributeInternal(CONTRACTENTITY);
    }

    /**
     * Sets <code>value</code> as attribute value for CONTRACT_ENTITY using the alias name ContractEntity.
     * @param value value to set the CONTRACT_ENTITY
     */
    public void setContractEntity(String value) {
        setAttributeInternal(CONTRACTENTITY, value);
    }

    /**
     * Gets the attribute value for CONTRACT_TYPE using the alias name ContractType.
     * @return the CONTRACT_TYPE
     */
    public String getContractType() {
        return (String) getAttributeInternal(CONTRACTTYPE);
    }

    /**
     * Sets <code>value</code> as attribute value for CONTRACT_TYPE using the alias name ContractType.
     * @param value value to set the CONTRACT_TYPE
     */
    public void setContractType(String value) {
        setAttributeInternal(CONTRACTTYPE, value);
    }

    /**
     * Gets the attribute value for ID using the alias name Id1.
     * @return the ID
     */
    public Number getId1() {
        return (Number) getAttributeInternal(ID1);
    }

    /**
     * Sets <code>value</code> as attribute value for ID using the alias name Id1.
     * @param value value to set the ID
     */
    public void setId1(Number value) {
        setAttributeInternal(ID1, value);
    }

    /**
     * Gets the attribute value for FULL_NAME using the alias name FullName.
     * @return the FULL_NAME
     */
    public String getFullName() {
        return (String) getAttributeInternal(FULLNAME);
    }

    /**
     * Sets <code>value</code> as attribute value for FULL_NAME using the alias name FullName.
     * @param value value to set the FULL_NAME
     */
    public void setFullName(String value) {
        setAttributeInternal(FULLNAME, value);
    }

    /**
     * Gets the attribute value for USERNAME using the alias name Username.
     * @return the USERNAME
     */
    public String getUsername() {
        return (String) getAttributeInternal(USERNAME);
    }

    /**
     * Sets <code>value</code> as attribute value for USERNAME using the alias name Username.
     * @param value value to set the USERNAME
     */
    public void setUsername(String value) {
        setAttributeInternal(USERNAME, value);
    }

    /**
     * Gets the attribute value for FULL_NAME using the alias name FullName1.
     * @return the FULL_NAME
     */
    public String getFullName1() {
        return (String) getAttributeInternal(FULLNAME1);
    }

    /**
     * Sets <code>value</code> as attribute value for FULL_NAME using the alias name FullName1.
     * @param value value to set the FULL_NAME
     */
    public void setFullName1(String value) {
        setAttributeInternal(FULLNAME1, value);
    }

    /**
     * Gets the attribute value for USERNAME using the alias name Username1.
     * @return the USERNAME
     */
    public String getUsername1() {
        return (String) getAttributeInternal(USERNAME1);
    }

    /**
     * Sets <code>value</code> as attribute value for USERNAME using the alias name Username1.
     * @param value value to set the USERNAME
     */
    public void setUsername1(String value) {
        setAttributeInternal(USERNAME1, value);
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
}
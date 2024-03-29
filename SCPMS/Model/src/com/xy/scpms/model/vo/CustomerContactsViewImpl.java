package com.xy.scpms.model.vo;


import com.xy.scpms.model.vo.common.CustomerContactsView;

import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Aug 20 17:40:51 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CustomerContactsViewImpl extends ViewObjectImpl implements CustomerContactsView {
    /**
     * This is the default constructor (do not remove).
     */
    public CustomerContactsViewImpl() {
    }
    
    public void setQueryTypeById(String id){
//        System.out.println("contact id: " +  id);
        this.setApplyViewCriteriaNames(null);
        ViewCriteria criteria = this.getViewCriteria("findById");
        this.applyViewCriteria(criteria);
        this.setcontactId(id);
        this.executeQuery();
    }
    
    public void setQueryTypeByCustomerId(String customerId){
    //        System.out.println("contact id: " +  id);
        this.setApplyViewCriteriaNames(null);
        ViewCriteria criteria = this.getViewCriteria("filterByCustomer");
        this.applyViewCriteria(criteria);
        this.setcustIdBind(customerId);
        this.executeQuery();
    }

    /**
     * Returns the variable value for custIdBind.
     * @return variable value for custIdBind
     */
    public String getcustIdBind() {
        return (String)ensureVariableManager().getVariableValue("custIdBind");
    }

    /**
     * Sets <code>value</code> for variable custIdBind.
     * @param value value to bind as custIdBind
     */
    public void setcustIdBind(String value) {
        ensureVariableManager().setVariableValue("custIdBind", value);
        
    }

    /**
     * Returns the variable value for contactId.
     * @return variable value for contactId
     */
    public String getcontactId() {
        return (String)ensureVariableManager().getVariableValue("contactId");
    }

    /**
     * Sets <code>value</code> for variable contactId.
     * @param value value to bind as contactId
     */
    public void setcontactId(String value) {
        ensureVariableManager().setVariableValue("contactId", value);
    }
}

package edu.hp.model.common;

import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

public class BaseView  extends ViewObjectImpl {

    //Common method for do a query based on pk
    //need to define view criteria,bind variable in view object
    //make sure to use correct vc name and variable name
    public void queryByPK(String vcName, String varName, Object value){
        setApplyViewCriteriaNames(null);
        ViewCriteria criteria = this.getViewCriteria(vcName);
        applyViewCriteria(criteria);
        ensureVariableManager().setVariableValue(varName, value);
        executeQuery();
    }
    
}

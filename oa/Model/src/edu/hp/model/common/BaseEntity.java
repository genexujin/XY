package edu.hp.model.common;


import edu.hp.view.security.LoginUser;

import edu.hp.view.utils.utils.JSFUtils;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import oracle.jbo.AttributeList;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.EntityRowSetImpl;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.TransactionEvent;
import oracle.jbo.server.ViewAccessorDef;

public class BaseEntity extends EntityImpl {

//    public void remove() {
//
//        String entityName = getEntityDef().getName();
//        if (entityName.equals("Lov")) {
//            super.remove();
//        } else {
//            setAttribute("Deleted", "Y");
//            super.remove();
//        }
//    }

    /**
     * avoid deleting records
     * @param operation
     * @param e
     */
    protected void doDML(int operation, TransactionEvent e) {
        String entityName = getEntityDef().getName();
        LoginUser user = null;
        try {
            user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
        } catch (Exception e1) {
            // TODO: Add catch code
            e1.printStackTrace();
        }
        
        if (operation == DML_INSERT ) {
            if(user!=null)
                this.setAttribute("CreatedBy", user.getDisplayName());
        }
        
        if(user!=null) this.setAttribute("LastUpdatedBy", user.getDisplayName());

        super.doDML(operation, e);
    }

    

//    protected void create(AttributeList attributeList) {
//        super.create(attributeList);
//        String seq =
//            (String)this.getStructureDef().findAttributeDef("Id").getProperty("SEQ");
//
//        SequenceImpl s = new SequenceImpl(seq, getDBTransaction());
//        setAttribute("Id", s.getSequenceNumber());
//
//    }


//    @Override
//    public void afterCommit(TransactionEvent transactionEvent) {
//        super.afterCommit(transactionEvent);
//
//    }
}

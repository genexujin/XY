package edu.hp.model.common;


import edu.hp.view.security.LoginUser;
import edu.hp.view.utils.utils.JSFUtils;

import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;


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

        if (this.getEntityDef().getAttributeIndexOf("CreatedBy") > 0) {

            if (operation == DML_INSERT) {
                if (user != null)
                    this.setAttribute("CreatedBy", user.getDisplayName());
            }

        }

        if (this.getEntityDef().getAttributeIndexOf("LastUpdatedBy") > 0) {
            if (user != null)
                this.setAttribute("LastUpdatedBy", user.getDisplayName());
        }

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

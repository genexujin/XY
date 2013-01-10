package edu.hp.model.common;


import oracle.jbo.AttributeList;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.EntityRowSetImpl;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.TransactionEvent;
import oracle.jbo.server.ViewAccessorDef;

public class BaseEntity extends EntityImpl {

    public void remove() {

        String entityName = getEntityDef().getName();
        if (entityName.equals("Lov")) {
            super.remove();
        } else {
            setAttribute("Deleted", "Y");
            super.remove();
        }
    }

    /**
     * avoid deleting records
     * @param operation
     * @param e
     */
    protected void doDML(int operation, TransactionEvent e) {
        String entityName = getEntityDef().getName();
        if (operation == DML_DELETE && !(entityName.equals("Lov"))) {
            operation = DML_UPDATE;
        }

        super.doDML(operation, e);
    }

    

    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        String seq =
            (String)this.getStructureDef().findAttributeDef("Id").getProperty("SEQ");

        SequenceImpl s = new SequenceImpl(seq, getDBTransaction());
        setAttribute("Id", s.getSequenceNumber());

    }


    @Override
    public void afterCommit(TransactionEvent transactionEvent) {
        super.afterCommit(transactionEvent);

    }
}

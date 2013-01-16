package com.xy.scpms.model.common;


import com.xy.scpms.model.eo.ContractImpl;
import com.xy.scpms.model.eo.ContractLineImpl;

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

        //do track record changes for contract tables.
        logHistory(operation, entityName);

        super.doDML(operation, e);
    }

    /**
     * 仅记录和合同相关的变更记录
     * @param operation
     * @param entityName
     */
    private void logHistory(int operation, String entityName) {

        if (entityName.equals("Contract") ||
            entityName.equals("ContractLine") ||
            entityName.equals("ContractLinePayments")) {

            ViewAccessorDef accessorDef =
                findViewAccessorDef("ChangeHistoryView");
            ViewObject historyView = findViewAccessorVO(accessorDef);

            if (operation == DML_INSERT) {

                Row history = historyView.createRow();
                history.setAttribute("TableName", entityName);
                history.setAttribute("Operation", "新建");
                String[] attrs = getAttributeNames();
                String newValue = "";
                for (String attr : attrs) {
                    Object value = getAttribute(attr);
                    if ((!attr.equals("AgreementImgUrl")) &&
                        (!attr.equals("ContractTotal")) &&
                        (!attr.equals("ContractVA")) &&
                        (!attr.equals("Contract")) &&
                        (!attr.equals("ContractLine")) &&
                        (!attr.equals("ChangeHistoryView")) &&
                        (!attr.equals("MasterLine")) &&
                        (!attr.equals("TotalAmtOfLine")) &&
                        (!attr.equals("CreatedBy")) &&
                        (!attr.equals("CreatedAt")) && value != null &&
                        !(value instanceof EntityRowSetImpl))
                        newValue =
                                newValue + attr + ":" + value.toString() + ";";
                }
                history.setAttribute("NewValue", newValue);
                history.setAttribute("OldValue", "N/A");
                history.setAttribute("RowId1", getAttribute("Id"));

                if (entityName.equals("Contract")) {
                    history.setAttribute("ContractNo",
                                         getAttribute("ContractNo"));
                } else if (entityName.equals("ContractLine")) {
                    ContractImpl contract =
                        (ContractImpl)getAttribute("Contract");
                    history.setAttribute("ContractNo",
                                         contract.getAttribute("ContractNo"));
                } else if (entityName.equals("ContractLinePayments")) {
                    ContractLineImpl line =
                        (ContractLineImpl)getAttribute("ContractLine");
                    ContractImpl contract = line.getContract();
                    history.setAttribute("ContractNo",
                                         contract.getAttribute("ContractNo"));
                }


            } else if (operation == DML_UPDATE) {

                String[] attrs = getAttributeNames();
                for (String attr : attrs) {
                    Object newValue = getAttribute(attr);
                    Object oldValue =
                        this.getPostedAttribute(this.getAttributeIndexOf(attr));


                    if ((!attr.equals("AgreementImgUrl")) &&
                        (!attr.equals("ContractTotal")) &&
                        (!attr.equals("ContractVA")) &&
                        (!attr.equals("Contract")) &&
                        (!attr.equals("ContractLine")) &&
                        (!attr.equals("ChangeHistoryView")) &&
                        (!attr.equals("MasterLine")) &&
                        (!attr.equals("TotalAmtOfLine")) &&
                        (!attr.equals("CreatedBy")) &&
                        (!attr.equals("CreatedAt")) && (newValue != null) &&
                        (!newValue.equals(oldValue)) &&
                        !(newValue instanceof EntityRowSetImpl)) {
                        Row history = historyView.createRow();
                        history.setAttribute("TableName", entityName);
                        history.setAttribute("ColumnName", attr);
                        history.setAttribute("Operation", "修改");
                        history.setAttribute("NewValue", newValue);
                        history.setAttribute("OldValue", oldValue);
                        history.setAttribute("RowId1", getAttribute("Id"));

                        if (entityName.equals("Contract")) {
                            history.setAttribute("ContractNo",
                                                 getAttribute("ContractNo"));
                        } else if (entityName.equals("ContractLine")) {
                            ContractImpl contract =
                                (ContractImpl)getAttribute("Contract");
                            history.setAttribute("ContractNo",
                                                 contract.getAttribute("ContractNo"));
                        } else if (entityName.equals("ContractLinePayments")) {
                            ContractLineImpl line =
                                (ContractLineImpl)getAttribute("ContractLine");
                            ContractImpl contract = line.getContract();
                            history.setAttribute("ContractNo",
                                                 contract.getAttribute("ContractNo"));
                        }

                    }

                }
            }
        }
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

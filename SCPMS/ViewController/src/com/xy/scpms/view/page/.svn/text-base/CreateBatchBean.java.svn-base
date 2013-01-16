package com.xy.scpms.view.page;


import com.xy.scpms.model.vo.InvoiceRequisitionViewRowImpl;
import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.util.Iterator;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.model.RowKeySet;


public class CreateBatchBean {
    private RichTable table;
    private RichPopup popup;

    public CreateBatchBean() {
    }

    public String createBatch() {
        
        OperationBinding binding = ADFUtils.findOperation("CreateInsert");
        binding.execute();
        
        Number batchId = (Number)ADFUtils.getBoundAttributeValue("Id");
        
        //得到选择的行的keyset
        RowKeySet rks = table.getSelectedRowKeys();
        //get iterator
        Iterator selectedRowsIterator = rks.iterator();

        //loop over ketsets
        while(selectedRowsIterator.hasNext()){
            //for each key, get the row attributes
            Object rowKey = selectedRowsIterator.next();
            table.setRowKey(rowKey);
            JUCtrlHierNodeBinding wrappedRow = (JUCtrlHierNodeBinding) table.getRowData();
            InvoiceRequisitionViewRowImpl rw = (InvoiceRequisitionViewRowImpl)wrappedRow.getRow();
            rw.setBatchId(batchId);
            Row contractLinePaymentsView = rw.getContractLinePaymentsView();
            contractLinePaymentsView.setAttribute("InvoiceBatchId", batchId);
        }
        
        ADFUtils.setBoundAttributeValue("InvNum", new Number(rks.size()));
        ADFUtils.setBoundAttributeValue("Status", "新建");
        
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popup.show(hints);
        
        
        return null;
    }

    public void setTable(RichTable table) {
        this.table = table;
    }

    public RichTable getTable() {
        return table;
    }

    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }

    public String confirm() {
        ADFUtils.findOperation("Commit").execute();
        JSFUtils.addFacesInformationMessage("发票批已成功创建！");
        ADFUtils.findIterator("InvReq4BatchIterator").executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(table);
        popup.hide();
        return null;
    }

    public String cancel() {
        ADFUtils.findOperation("Rollback").execute();
        popup.hide();
        return null;
    }

    public String confirmAndPrint() {
        ADFUtils.findOperation("Commit").execute();        
        ADFUtils.findIterator("InvoiceBatchesIterator").executeQuery();
        ADFUtils.findIterator("InvReq4BatchIterator").executeQuery();
        JSFUtils.addFacesInformationMessage("发票批已成功创建！");
        return "print";
    }
}

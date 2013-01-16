package com.xy.scpms.view.page;


import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.util.List;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;


public class FourTechBean {

    private RichPopup popup;
    private RichTable table;
    private int numOfShips;
    private boolean disabled;
    private RichCommandButton saveBtn;
    private List lines;

    public FourTechBean() {
    }


    public String createInsert() {

        try {
           
            //得到选择的行的keyset
            RowKeySet rks = table.getSelectedRowKeys();
            //get iterator
            Iterator selectedRowsIterator = rks.iterator();
            //initialize hashmap to hold params for appmodule methods.
            List ids = new ArrayList();
            
            numOfShips = 0;
            //loop over ketsets
            while (selectedRowsIterator.hasNext()) {
                //for each key, get the row attributes
                Object rowKey = selectedRowsIterator.next();
                table.setRowKey(rowKey);
                JUCtrlHierNodeBinding wrappedRow =
                    (JUCtrlHierNodeBinding)table.getRowData();
                Row rw = wrappedRow.getRow();
                Number id = (Number)rw.getAttribute("Id");
//                System.err.println(id);
                ids.add(id);
                numOfShips++;
            }
            lines = ids;

            OperationBinding operation =
                ADFUtils.findOperation("createFourTech");
            operation.getParamsMap().put("linesId", ids);
            operation.execute();
            if (operation.getErrors().isEmpty()) {
                
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                
                popup.show(hints);
            }else{
                JSFUtils.addFacesErrorMessage("创建四技合同过程中出错，请联系管理员！");
            }
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage("创建四技合同过程中出错，请联系管理员！");
            e.printStackTrace();
        }

        return null;
    }


    /**
     * save button action codes.
     * @return
     */
    public String save() {
        try {
            disabled = true;
            ADFUtils.findOperation("saveFourTech").execute();            
            ADFUtils.findOperation("Commit").execute();
            JSFUtils.addFacesInformationMessage("已成功创建四技合同并保存！");
            saveBtn.setDisabled(disabled);
            AdfFacesContext.getCurrentInstance().addPartialTarget(saveBtn);
            lines = null;
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage("保存四技合同过程中出错，请联系管理员！");
            e.printStackTrace();
        }
        return null;
    }

    public String cancel() {
        
        saveBtn.setDisabled(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(saveBtn);
        popup.cancel();
        ADFUtils.findOperation("findBlankFourTechLines").execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(table);

        return null;
    }

    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }


    public void setTable(RichTable table) {
        this.table = table;
    }

    public RichTable getTable() {
        return table;
    }

    public void onSelectLine(SelectionEvent selectionEvent) {

    }

    public void setNumOfShips(int numOfShips) {
        this.numOfShips = numOfShips;
    }

    public int getNumOfShips() {
        return numOfShips;
    }

    public void setSaveBtn(RichCommandButton saveBtn) {
        this.saveBtn = saveBtn;
    }

    public RichCommandButton getSaveBtn() {
        return saveBtn;
    }
}

package edu.hp.view.bean.helpdesk;

import edu.hp.view.utils.ADFUtils;

import edu.hp.view.utils.JSFUtils;

import java.util.Iterator;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import oracle.jbo.domain.DBSequence;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.component.UIXTable;
import org.apache.myfaces.trinidad.component.UIXTree;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;

public class MyHelpdeskCallBean {
    private final String reasonLevel1IteratorName = "ReasonLevel1Iterator";
    private final String reasonLevel2IteratorName = "ReasonLevel2Iterator";
    private RichTable resultTable;

    public MyHelpdeskCallBean() {
        
    }

    public void reasonLevel1Changed(ValueChangeEvent valueChangeEvent) {
        int newValue = (Integer)valueChangeEvent.getNewValue();
//        System.out.println(newValue);
        setCurrentReasonLevel1(newValue);
//        loadReasonLevel2();
        UIComponent component = JSFUtils.findComponent(valueChangeEvent.getComponent().getNamingContainer(), "soc2");
        ADFUtils.partialRefreshComponenet(component);
    }
    
    public void setCurrentReasonLevel1(final int index) {
        DCIteratorBinding it = ADFUtils.findIterator(reasonLevel1IteratorName);
        it.setCurrentRowIndexInRange(index);
    }
    
//    private DCIteratorBinding getBinding(UIComponent comp) {
//        if (comp instanceof UIXTable || comp instanceof UIXTree) {
//            CollectionModel model = comp instanceof UIXTable ?
//                (CollectionModel)((UIXTable)comp).getValue() :
//                (CollectionModel)((UIXTree)comp).getValue();
//            JUCtrlHierBinding adfTreeBinding = (JUCtrlHierBinding)model.getWrappedData();
//            return adfTreeBinding.getDCIteratorBinding();
//        }
//    }
    
    private void howToGetTableSelectedRow() {
        RichTable table = this.getResultTable();
        RowKeySet rks = table.getSelectedRowKeys(); //One row has many columns, so the name is "keys"
        Iterator it = rks.iterator();
        if (it.hasNext()) {
            List keys = (List)it.next();
            CollectionModel model = (CollectionModel)table.getValue();
            JUCtrlHierBinding treeBinding = (JUCtrlHierBinding)model.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = treeBinding.findNodeByKeyPath(keys);
            Row rw = nodeBinding.getRow();
        }
        
    }

    public void doQuery(ActionEvent actionEvent) {
//        DCIteratorBinding it = ADFUtils.findIterator(reasonLevel1IteratorName);
        BindingContainer bindings = ADFUtils.getBindingContainer();
        JUCtrlListBinding rsnLv1 = (JUCtrlListBinding)bindings.get("ReasonLevel1");
        Object object = rsnLv1.getSelectedValue();
        int index = rsnLv1.getSelectedIndex();
        Row row = rsnLv1.getCurrentRow();
        DBSequence id = (DBSequence)row.getAttribute("Id");
        System.out.println(object);
        System.out.println(index);
        System.out.println(id);
                
//        DCIteratorBinding it = ADFUtils.findIterator("HelpdeskCallsView1Iterator");
//        JUCtrlHierBinding treeBinding = (JUCtrlHierBinding)ADFUtils.getBindingContainer().get("HelpdeskCallsView11");
//        
//        if (it != null) {
//            it.setRangeSize(-1);
//            it.executeQuery();
//            
//            
//        }
        OperationBinding binding = ADFUtils.findOperation("QueryHepdeskCalls");
        binding.getParamsMap().put("RsnLv1", id.getValue());
        binding.execute();
        
        ADFUtils.partialRefreshComponenet(resultTable);
    }

    public void setResultTable(RichTable resultTable) {
        this.resultTable = resultTable;
    }

    public RichTable getResultTable() {
        return resultTable;
    }

    public String doQuery2() {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        JUCtrlListBinding rsnLv1 = (JUCtrlListBinding)bindings.get("ReasonLevel1");
        Object object = rsnLv1.getSelectedValue();
        int index = rsnLv1.getSelectedIndex();
        Row row = rsnLv1.getCurrentRow();
        DBSequence id = (DBSequence)row.getAttribute("Id");
        System.out.println(id);
        //OperationBinding binding = ADFUtils.findOperation("QueryHepdeskCalls");
        
        //binding.getParamsMap().put("RsnLv1", id.getValue());
        OperationBinding binding = ADFUtils.findOperation("doQuery");
        binding.getParamsMap().put("level", id.getValue());
        binding.execute();
        
        ADFUtils.partialRefreshComponenet(resultTable);
        return null;
    }
}

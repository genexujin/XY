package edu.hp.view.bean.helpdesk;

import edu.hp.model.common.Constants;
import edu.hp.view.bean.BaseBean;
import edu.hp.view.utils.ADFUtils;

import edu.hp.view.utils.JSFUtils;

import java.sql.Date;

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
import oracle.jbo.domain.Timestamp;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.component.UIXTable;
import org.apache.myfaces.trinidad.component.UIXTree;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;

public class MyHelpdeskCallBean extends BaseBean {
    private RichTable resultTable;
    
    private String callReadableId;
    private Date submitDateFrom;
    private Date submitDateTo;

    public MyHelpdeskCallBean() {
        
    }

//    public void reasonLevel1Changed(ValueChangeEvent valueChangeEvent) {
//        int newValue = (Integer)valueChangeEvent.getNewValue();
////        System.out.println(newValue);
//        setCurrentReasonLevel1(newValue);
////        loadReasonLevel2();
//        UIComponent component = JSFUtils.findComponent(valueChangeEvent.getComponent().getNamingContainer(), "soc2");
//        ADFUtils.partialRefreshComponenet(component);
//    }
    
//    public void setCurrentReasonLevel1(final int index) {
//        DCIteratorBinding it = ADFUtils.findIterator("ReasonLevel1Iterator");
//        it.setCurrentRowIndexInRange(index);
//    }
    
//    private DCIteratorBinding getBinding(UIComponent comp) {
//        if (comp instanceof UIXTable || comp instanceof UIXTree) {
//            CollectionModel model = comp instanceof UIXTable ?
//                (CollectionModel)((UIXTable)comp).getValue() :
//                (CollectionModel)((UIXTree)comp).getValue();
//            JUCtrlHierBinding adfTreeBinding = (JUCtrlHierBinding)model.getWrappedData();
//            return adfTreeBinding.getDCIteratorBinding();
//        }
//    }
    
//    private void howToGetTableSelectedRow() {
//        RichTable table = this.getResultTable();
//        RowKeySet rks = table.getSelectedRowKeys(); //One row has many columns, so the name is "keys"
//        Iterator it = rks.iterator();
//        if (it.hasNext()) {
//            List keys = (List)it.next();
//            CollectionModel model = (CollectionModel)table.getValue();
//            JUCtrlHierBinding treeBinding = (JUCtrlHierBinding)model.getWrappedData();
//            JUCtrlHierNodeBinding nodeBinding = treeBinding.findNodeByKeyPath(keys);
//            Row rw = nodeBinding.getRow();
//        }
//        
//    }

    public void setResultTable(RichTable resultTable) {
        this.resultTable = resultTable;
    }

    public RichTable getResultTable() {
        return resultTable;
    }

    public void doQuery(ActionEvent event) {
        String stateValue = getLovAttrValue("HdStateWithEmpty", "Value");
        System.out.println("State is: " + stateValue);
        String reasonLv1WithEmpty = getLovAttrValue("ReasonLevel1WithEmpty", "Value");
        System.out.println("reasonLv1WithEmpty is: " + reasonLv1WithEmpty);
//        String reasonLv2WithEmpty = getLovAttrValue("ReasonLevel2WithEmpty", "Value");
//        System.out.println("reasonLv2WithEmpty is: " + reasonLv2WithEmpty);
        System.out.println("CallReadableId is: " + callReadableId);
        System.out.println("SubmitDateFrom is: " + submitDateFrom);
        System.out.println("SubmitDateTo is: " + submitDateTo);
        String callerId = getLovAttrValue("EmpWithEmptyForCaller", "Id");
        System.out.println("callerId is: " + callerId);
        String calleeId = getLovAttrValue("EmpWithEmptyForCallee", "Id");
        System.out.println("calleeId is: " + calleeId);
        String hdResult = getLovAttrValue("HdResultWithEmpty", "Value");
        System.out.println("hdResult is: " + hdResult);
        String hdEval = getLovAttrValue("HdEvalWithEmpty", "Value");
        System.out.println("hdEval is: " + hdEval);
        
        OperationBinding binding = ADFUtils.findOperation("doQuery");
        binding.getParamsMap().put("rsnLv1", reasonLv1WithEmpty);
        binding.getParamsMap().put("state", stateValue);
        binding.getParamsMap().put("cReadableId", callReadableId);
        binding.getParamsMap().put("submitDateFrom", submitDateFrom);
        binding.getParamsMap().put("submitDateTo", submitDateTo);
        binding.getParamsMap().put("callerId", callerId);
        binding.getParamsMap().put("calleeId", calleeId);
        binding.getParamsMap().put("callResult", hdResult);
        binding.getParamsMap().put("callEval", hdEval);
                
        binding.execute();
        
        ADFUtils.partialRefreshComponenet(resultTable);
//        return null;
        
    }

    private String getLovAttrValue(String lovBindingName, String attrName) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        JUCtrlListBinding binding = (JUCtrlListBinding)bindings.get(lovBindingName);
        Row row = binding.getCurrentRow();
        Object value = row.getAttribute(attrName);
        if (value == null) {
            return null;
        } else if (value instanceof DBSequence) {
            return ((DBSequence)value).getValue() + "";
        } else {
            return value.toString();
        }
        
    }

    public void submitHdCall(ActionEvent actionEvent) {
        setSubmitDate();
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && state.equals(Constants.STATE_INITIAL)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_ACCEPTED);
            boolean success = ADFUtils.commit("报修单已提交！", "报修单提交失败，请核对输入的信息或联系管理员！");
            if (success) {
                String locationDetail = (String)ADFUtils.getBoundAttributeValue("LocationDetail");
                String rsnLv1 = (String)ADFUtils.getBoundAttributeValue("ReasonLevel1");
                
                //send to callee
                String noteTitle = "有新的报修请求等待处理";
                String noteContent = "详细地址：" + locationDetail + " 报修原因：" + rsnLv1 + " 提交时间：" + getDateString();                
                sendNotification(noteTitle, noteContent, null, Constants.ROLE_HD_ADMIN);
                
                ADFUtils.findOperation("Commit").execute();
            } else {
                ADFUtils.setBoundAttributeValue("State", state);
            }
        }
    }

    public void cancelHdCall(ActionEvent actionEvent) {
        toState(Constants.STATE_CANCELED_2);
    }

    public void processHdCall(ActionEvent actionEvent) {
        toState(Constants.STATE_PROCESSED);
    }

    public void evaluateHdCall(ActionEvent actionEvent) {
        toState(Constants.STATE_EVALUATED);
    }
    
    private void toState(String state) {
        //DCIteratorBinding binding = ADFUtils.findIterator("HelpdeskCallsViewIterator");
        //Row row = binding.getCurrentRow();
        //row.setAttribute("State", state);
        ADFUtils.setBoundAttributeValue("State", state);
//        ADFUtils.setBoundAttributeValue("CallId", 100);        
        OperationBinding oper = ADFUtils.findOperation("Commit");
        oper.execute();
    }

    public String getCallReadableId() {
        return callReadableId;
    }
    
    public void setCallReadableId(String rId) {
        this.callReadableId = rId;
    }
    
    public void setSubmitDateFrom(Date submitDateFrom) {
        this.submitDateFrom = submitDateFrom;
    }

    public Date getSubmitDateFrom() {
        return submitDateFrom;
    }

    public void setSubmitDateTo(Date submitDateTo) {
        this.submitDateTo = submitDateTo;
    }

    public Date getSubmitDateTo() {
        return submitDateTo;
    }

    public void reasonLevelChanged(ValueChangeEvent valueChangeEvent) {
//        DCIteratorBinding lbinding = ADFUtils.findIterator("HelpdeskCallsViewIterator");
//        lbinding.executeQuery();
    }
    
    private void setSubmitDate() {
        DCIteratorBinding it = ADFUtils.findIterator("HelpdeskCallsViewIterator");
        Row row = it.getCurrentRow();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        System.out.println("Current time is: " + now);
        row.setAttribute("CreateAt", now);
    }

    public void onReasonLevel1Change(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }
}

package com.xy.scpms.view.flow.backing;


import com.xy.scpms.model.query.search.SearchContractBaseViewImpl;
import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.event.QueryEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;


public class SearchBean implements Serializable {
    private RichCommandToolbarButton openBtn;
    private RichTable resultTable;

    public SearchBean() {
    }

    public String openContract() {
        JSFUtils.setExpressionValue("#{pageFlowScope.isSearch}", true);
        return "open";
    }

    //  this method is used inside creating contract flow
    //when user select to create a duplicated contract,
    //before user comes to the editing page, they must search and selcect a parent contract

    public void onDuplicateQuery(QueryEvent queryEvent) {
        onQuery(queryEvent, "ContractIterator");
    }

    // this method is used on search contract flow

    public void onSearchQuery(QueryEvent queryEvent) {
        onQuery(queryEvent, "SearchContractBaseViewIterator");
    }

    public void onQuery(QueryEvent queryEvent, String iteratorName) {
        Boolean isDeptMgr = JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['design_director']}");
        Boolean isPrjMgr = JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['project_manager']}");
        Boolean isVIP =
            JSFUtils.resolveExpressionAsBoolean("#{securityContext.userInRole['marketing_director,contractor,vip']}");
        if (isPrjMgr) {
            if (isDeptMgr) {
                SearchContractBaseViewImpl vo =
                    (SearchContractBaseViewImpl)applyViewCriteria("filterByDeptIdManagerId", iteratorName);
                String dept = (String)JSFUtils.resolveExpression("#{sessionScope.userDept}");
                vo.setdeptBind(dept);
            } else
                applyViewCriteria("filterByManagerId", iteratorName);
        } else if (isDeptMgr) {
            SearchContractBaseViewImpl vo =
                (SearchContractBaseViewImpl)applyViewCriteria("filterByDept", iteratorName);
            String dept = (String)JSFUtils.resolveExpression("#{sessionScope.userDept}");

            vo.setdeptBind(dept);
        } else if (isVIP) {
            applyViewCriteria(null, iteratorName);
        } else {
            applyViewCriteria("filterByManagerId", iteratorName);
        }
        //执行query
        invokeQueryEventMethodExpression("#{bindings.ImplicitViewCriteriaQuery.processQuery}", queryEvent);

    }

    private ViewObjectImpl applyViewCriteria(String criteriaName, String iteratorName) {
        //得到iterator binding的引用
        DCIteratorBinding binding = ADFUtils.findIterator(iteratorName);
        //得到bind的viewobject,并为view object的bind variable赋值
        ViewObjectImpl vo = (ViewObjectImpl)binding.getViewObject();
        vo.setApplyViewCriteriaNames(null);
        if (criteriaName != null) {
            ViewCriteria criteria = vo.getViewCriteria(criteriaName);
            vo.applyViewCriteria(criteria);
        }
        return vo;
    }

    private void invokeQueryEventMethodExpression(String expression, QueryEvent queryEvent) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ELContext elctx = fctx.getELContext();
        ExpressionFactory efactory = fctx.getApplication().getExpressionFactory();
        MethodExpression me =
            efactory.createMethodExpression(elctx, expression, Object.class, new Class[] { QueryEvent.class });
        me.invoke(elctx, new Object[] { queryEvent });
    }

    //双击打开一个合同

    public void openContractDBClick(ClientEvent clientEvent) {

        try {
            Number id = (Number)ADFUtils.getBoundAttributeValue("Id");
            openContractById(id);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    //双击打开一个合同

    public void openSubContractDBClick(ClientEvent clientEvent) {

        try {
            Number id = (Number)ADFUtils.getBoundAttributeValue("SubId");
            openContractById(id);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    //双击打开一个合同

    public void openParContractDBClick(ClientEvent clientEvent) {

        try {
            Number id = (Number)ADFUtils.getBoundAttributeValue("ParId");
            openContractById(id);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    private void openContractById(Number id) {

        JSFUtils.setExpressionValue("#{pageFlowScope.isSearch}", true);
        OperationBinding op = ADFUtils.findOperation("findContractById");

        Map map = op.getParamsMap();
        map.put("id", id);
        op.execute();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "open");
    }

    public void onSelect(SelectionEvent selectionEvent) {

        ADFUtils.makeCurrent(selectionEvent);
        if (openBtn.isDisabled()) {
            this.openBtn.setDisabled(false);

            ADFUtils.partialRefreshComponenet(openBtn);
        }

    }

    public void setOpenBtn(RichCommandToolbarButton openBtn) {
        this.openBtn = openBtn;
    }

    public RichCommandToolbarButton getOpenBtn() {
        return openBtn;
    }

    public void onSelect1(SelectionEvent selectionEvent) {
        //        System.out.println("here");
    }

    public void dupContractByDbClick(ClientEvent clientEvent) {

        Number id = (Number)ADFUtils.getBoundAttributeValue("Id");
        JSFUtils.setExpressionValue("#{pageFlowScope.CreationFlowBean.parentContractId}", id);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "copy");
    }

    public void setResultTable(RichTable resultTable) {
        this.resultTable = resultTable;
    }

    public RichTable getResultTable() {
        return resultTable;
    }

    /**
     * This method is used inside urge-sttlement-btf.xml
     */
    public String urgeSettlement() {

        try {
            //得到选择的行的keyset
            RowKeySet rks = resultTable.getSelectedRowKeys();
            //get iterator
            Iterator selectedRowsIterator = rks.iterator();
            //initialize hashmap to hold params for appmodule methods.
            ArrayList<String> contractNos = new ArrayList<String>();

            if (selectedRowsIterator.hasNext()) {

                //loop over ketsets
                while (selectedRowsIterator.hasNext()) {
                    //for each key, get the row attributes
                    Object rowKey = selectedRowsIterator.next();
                    resultTable.setRowKey(rowKey);
                    JUCtrlHierNodeBinding wrappedRow = (JUCtrlHierNodeBinding)resultTable.getRowData();
                    Row rw = wrappedRow.getRow();
                    String contractNo = (String)rw.getAttribute("ContractNo");
                    contractNos.add(contractNo);
                }
                
                OperationBinding runQuery = ADFUtils.findOperation("runQuery");
                runQuery.getParamsMap().put("contractNos", contractNos);
                runQuery.execute();
                
                OperationBinding op = ADFUtils.findOperation("queryByContractNos");
                op.getParamsMap().put("contractNos", contractNos);
                op.execute();
                if (op.getErrors().isEmpty()&&runQuery.getErrors().isEmpty()) {
                    return "runReport";
                }else{
                    JSFUtils.addFacesErrorMessage("生成催款函过程中出错！");
                }


            } else {
                JSFUtils.addFacesErrorMessage("必须选择至少一个合同！");
            }
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage("生成催款函过程中出错！");
            e.printStackTrace();
        }
        // Add event code here...
        return null;
    }
}

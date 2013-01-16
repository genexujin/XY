package com.xy.scpms.view.page;

import com.xy.view.utils.ADFUtils;

import com.xy.view.utils.JSFUtils;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.Date;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.Number;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;



public class DynPayStatusReportBean {
    
    private RichSelectOneChoice deptSel;
    private RichSelectOneChoice fySel;

    
    public String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date);
        return dateStr;
    }

    public DynPayStatusReportBean() {
    }

    public void setDeptSel(RichSelectOneChoice deptSel) {
        this.deptSel = deptSel;
    }

    public RichSelectOneChoice getDeptSel() {
        return deptSel;
    }

    public void setFySel(RichSelectOneChoice fySel) {
        this.fySel = fySel;
    }

    public RichSelectOneChoice getFySel() {
        return fySel;
    }

    public String runReport() {

        try {
            String dept =
                (String)ADFUtils.getBoundAttributeValue("selectedDept");
            if (dept.equals("0")) {
                dept = "研究开发部";
            } else if (dept.equals("1")) {
                dept = "海洋工程部";
            } else if (dept.equals("2")) {
                dept = "船舶设计一部";
            } else if (dept.equals("3")) {
                dept = "船舶设计二部";
            } else if (dept.equals("4")) {
                dept = "生产设计部";
            } else if (dept.equals("5")) {
                dept = "南通斯达瑞";
            }
            
            String fy = (String)ADFUtils.getBoundAttributeValue("selectedFY");
            Number fiscalYear = new Number(fy);

            OperationBinding setDept = ADFUtils.findOperation("setdept");
            setDept.getParamsMap().put("value", dept);
            setDept.execute();
            
            OperationBinding setFY = ADFUtils.findOperation("setyearBind");
            setFY.getParamsMap().put("value", fiscalYear);
            setFY.execute();
            
            ADFUtils.findOperation("applyDeptCriteria").execute();

            DCIteratorBinding it = ADFUtils.findIterator("Rpt_DynPayStatusByYearIterator");
            it.executeQuery();
            
            JSFUtils.setExpressionValue("#{pageFlowScope.dept}",dept);
            JSFUtils.setExpressionValue("#{pageFlowScope.fy}",fy);


        } catch (Exception sqle) {
            sqle.printStackTrace();
        }


        return null;
    }

 
}

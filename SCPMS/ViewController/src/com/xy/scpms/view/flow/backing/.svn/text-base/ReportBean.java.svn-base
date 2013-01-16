package com.xy.scpms.view.flow.backing;

import com.xy.view.utils.*;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.event.QueryEvent;
import oracle.adf.view.rich.model.AttributeCriterion;
import oracle.adf.view.rich.model.AttributeDescriptor;
import oracle.adf.view.rich.model.ConjunctionCriterion;
import oracle.adf.view.rich.model.Criterion;
import oracle.adf.view.rich.model.QueryDescriptor;


import oracle.binding.OperationBinding;

import oracle.jbo.domain.Number;

public class ReportBean {
    private RichSelectOneChoice deptLov;
    private RichSelectOneChoice fyLov;
    private RichSelectOneChoice endLov;
    private RichSelectOneChoice monLov;

    public ReportBean() {
        super();
    }


    public String runReport() {

        try {

            String dept = deptLov==null?"":(String)deptLov.getValue();
            
            String fy = fyLov==null?"0":(String)fyLov.getValue();
            Number fiscalYear = new Number(fy);
            
            String endYr = endLov==null?"0":(String)endLov.getValue();
            Number endYrNum = new Number(endYr);
            
            String mth = monLov==null?"0":(String)monLov.getValue();
            Number month = new Number(mth);

            JSFUtils.setExpressionValue("#{pageFlowScope.dept}", dept);
            JSFUtils.setExpressionValue("#{pageFlowScope.year}", fiscalYear);
            JSFUtils.setExpressionValue("#{pageFlowScope.startYr}", fiscalYear);
            JSFUtils.setExpressionValue("#{pageFlowScope.endYr}", endYrNum);            
            JSFUtils.setExpressionValue("#{pageFlowScope.month}", month);

        } catch (Exception sqle) {
            sqle.printStackTrace();
        }


        return "run";
    }

    public String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date);
        return dateStr;
    }

    public void setDeptLov(RichSelectOneChoice deptLov) {
        this.deptLov = deptLov;
    }

    public RichSelectOneChoice getDeptLov() {
        return deptLov;
    }

    public void setFyLov(RichSelectOneChoice fyLov) {
        this.fyLov = fyLov;
    }

    public RichSelectOneChoice getFyLov() {
        return fyLov;
    }

    public void setMonLov(RichSelectOneChoice monLov) {
        this.monLov = monLov;
    }

    public RichSelectOneChoice getMonLov() {
        return monLov;
    }


    public void onEveGraphQuery(QueryEvent queryEvent) {

        try {
            QueryDescriptor qdesc =
                (QueryDescriptor)queryEvent.getDescriptor();

            ConjunctionCriterion conCrit = qdesc.getConjunctionCriterion();

            String dept = null;
            Number startYr = null;
            Number endYr = null;

            //access the list of search fields
            List<Criterion> criterionList = conCrit.getCriterionList();
            //iterate over the attributes to find FromDate and ToDate
            for (Criterion criterion : criterionList) {
                AttributeDescriptor attrDescriptor =
                    ((AttributeCriterion)criterion).getAttribute();
                if (attrDescriptor.getName().equalsIgnoreCase("Department")) {
                    dept =
(String)((AttributeCriterion)criterion).getValues().get(0);
                    JSFUtils.setExpressionValue("#{pageFlowScope.dept}", dept);
                } else if (attrDescriptor.getName().equalsIgnoreCase("Year")) {
                    if (startYr == null) {
                        startYr =
                                (Number)((AttributeCriterion)criterion).getValues().get(0);
                        JSFUtils.setExpressionValue("#{pageFlowScope.startYr}",
                                                    startYr);
                    } else {
                        endYr =
                                (Number)((AttributeCriterion)criterion).getValues().get(0);
                        JSFUtils.setExpressionValue("#{pageFlowScope.endYr}",
                                                    endYr);
                    }
                }
                //startDate must be lower than end date
                if ((startYr != null && endYr != null) &&
                    (startYr.compareTo(endYr) > -1)) {
                    JSFUtils.addFacesErrorMessage("起始年不能大于终止年");
                    FacesContext fctx = FacesContext.getCurrentInstance();
                    //immediately render response if ToDate is lower than FromDate
                    fctx.renderResponse();
                } else {
                    //only if the date fields are enetered correctly, execute the
                    //QueryListener search binding using a copy of the original EL
                    //string added by JDeveloper upon drag and drop

                    ADFUtils.invokeQueryEventMethodExpression("#{bindings.filterByYearRangeQuery.processQuery}",
                                                              queryEvent);

                }
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

    }

    public void setEndLov(RichSelectOneChoice endLov) {
        this.endLov = endLov;
    }

    public RichSelectOneChoice getEndLov() {
        return endLov;
    }
}


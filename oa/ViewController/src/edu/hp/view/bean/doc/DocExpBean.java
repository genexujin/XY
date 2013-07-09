package edu.hp.view.bean.doc;

import edu.hp.view.utils.ADFUtils;

import java.util.Date;

import java.util.Map;

import oracle.binding.OperationBinding;

public class DocExpBean {
    public DocExpBean() {
    }

    private String taskName;
    private String state;
    private Date startDate;
    private Date endDate;

    public String doSearch() {

        oracle.jbo.domain.Date stDt = null, edDt = null;
        
        if (startDate != null)
            stDt = new oracle.jbo.domain.Date(new java.sql.Date(startDate.getTime()));
        if (endDate != null)
            edDt = new oracle.jbo.domain.Date(new java.sql.Date(endDate.getTime()));

        OperationBinding binding = ADFUtils.findOperation("search");
        Map params = binding.getParamsMap();
        params.put("taskName", taskName);
        params.put("state", state);
        params.put("startDate", stDt);
        params.put("endDate", edDt);
        binding.execute();        
        return null;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}

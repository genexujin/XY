package edu.hp.view.bean.doc;

import edu.hp.model.common.Constants;
import edu.hp.view.security.LoginUser;
import edu.hp.view.utils.ADFUtils;

import edu.hp.view.utils.JSFUtils;

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
    private String dept;

    public String doSearch() {
        
        System.err.println(dept);
        if(!isAdmin()){
            LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
            dept = user.getDeptName();
        }

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
        params.put("dept", dept);
        binding.execute();        
        return null;
    }
    
    public boolean isAdmin(){
        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
        if(user.getIsUserInRole().get(Constants.ROLE_DOC_REVIEW) != null){
            return true;
        }
        return false;
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

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDept() {
        return dept;
    }
}

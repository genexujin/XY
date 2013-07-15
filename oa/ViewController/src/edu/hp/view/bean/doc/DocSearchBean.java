package edu.hp.view.bean.doc;

import edu.hp.model.common.Constants;
import edu.hp.view.security.LoginUser;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import java.util.Date;
import java.util.Map;

import oracle.binding.OperationBinding;

public class DocSearchBean {
    
    private String title;
    private String state;
    private Date startDt;
    private Date endDt;
    private String userId;
    
    public DocSearchBean() {
    }

    public String doSearch() {
        
        
        if(!isAdmin()){
            LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
            userId = user.getUserId();
        }

        oracle.jbo.domain.Date stDt = null, edDt = null;
        
        if (startDt != null)
            stDt = new oracle.jbo.domain.Date(new java.sql.Date(startDt.getTime()));
        if (endDt != null)
            edDt = new oracle.jbo.domain.Date(new java.sql.Date(endDt.getTime()));

        OperationBinding binding = ADFUtils.findOperation("search");
        Map params = binding.getParamsMap();
        params.put("title", title);
        params.put("state", state);
        params.put("startDate", stDt);
        params.put("endDate", edDt);
        params.put("editorId", userId);        
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public Date getEndDt() {
        return endDt;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}

package edu.hp.view.security;

import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;

import javax.sql.DataSource;

import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.event.DialogEvent;

public class LoginUser {
    private String userId;
    private String currentPassword;
    private String userName;
    private String displayName;
    private String deptName;
    private ArrayList<String> userGroups;
    private ArrayList<String> userRoles;
    private HashMap<String,Boolean> isUserInRole;
    
    private String dialogCurrPassword;
    private String dialogNewPassword;
    private String dialogNewPasswordConfirmed;
    private RichInputText currPasswdComp;
    private RichInputText newPasswdComp;
    private RichInputText newPasswdCnfmComp;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserGroups(ArrayList<String> userGroups) {
        this.userGroups = userGroups;
    }

    public ArrayList<String> getUserGroups() {
        return userGroups;
    }

    public void setUserRoles(ArrayList<String> userRoles) {
        this.userRoles = userRoles;
    }

    public ArrayList<String> getUserRoles() {
        return userRoles;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setIsUserInRole(HashMap<String, Boolean> isUserInRole) {
        this.isUserInRole = isUserInRole;
    }

    public HashMap<String, Boolean> getIsUserInRole() {
        return isUserInRole;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }
    
    public void changePassword(DialogEvent event) {
        if (!dialogCurrPassword.equals(currentPassword)) {
            JSFUtils.addFacesErrorMessage("当前密码不正确，请重新输入");   
            dialogCurrPassword = null;
            dialogNewPassword = null;
            dialogNewPasswordConfirmed = null;
            
            ADFUtils.partialRefreshComponenet(currPasswdComp);
            ADFUtils.partialRefreshComponenet(newPasswdComp);
            ADFUtils.partialRefreshComponenet(newPasswdCnfmComp);
        } else if (!dialogNewPassword.equals(dialogNewPasswordConfirmed)) {
            JSFUtils.addFacesErrorMessage("输入的两次新密码不匹配，请重新输入"); 
            dialogCurrPassword = null;
            dialogNewPassword = null;
            dialogNewPasswordConfirmed = null;
            
            ADFUtils.partialRefreshComponenet(currPasswdComp);
            ADFUtils.partialRefreshComponenet(newPasswdComp);
            ADFUtils.partialRefreshComponenet(newPasswdCnfmComp);
        } else {
            String sql = "update employees set employees.password = ? where employees.id = ?";
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                // get JNDI JDBC connection
                InitialContext ctxt = new InitialContext();
                DataSource ds = (DataSource)ctxt.lookup("jdbc/oaDS");
                conn = ds.getConnection();
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, dialogNewPassword);
                stmt.setString(2, userId);
                stmt.executeUpdate();
                
                this.currentPassword = this.dialogNewPassword;
                JSFUtils.addFacesInformationMessage("密码修改成功！");
                
            } catch (Exception sqle) {
                // TODO: Add catch code
                sqle.printStackTrace();
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                    if (rs != null)
                        rs.close();
                    if (conn != null)
                        conn.close();
                } catch (SQLException sqle) {
                    // TODO: Add catch code
                    sqle.printStackTrace();
                }
            }
            
            dialogCurrPassword = null;
            dialogNewPassword = null;
            dialogNewPasswordConfirmed = null;
            
            ADFUtils.partialRefreshComponenet(currPasswdComp);
            ADFUtils.partialRefreshComponenet(newPasswdComp);
            ADFUtils.partialRefreshComponenet(newPasswdCnfmComp);
        }
    }

    public void setDialogCurrPassword(String dialogCurrPassword) {
        this.dialogCurrPassword = dialogCurrPassword;
    }

    public String getDialogCurrPassword() {
        return dialogCurrPassword;
    }

    public void setDialogNewPassword(String dialogNewPassword) {
        this.dialogNewPassword = dialogNewPassword;
    }

    public String getDialogNewPassword() {
        return dialogNewPassword;
    }

    public void setDialogNewPasswordConfirmed(String dialogNewPasswordConfirmed) {
        this.dialogNewPasswordConfirmed = dialogNewPasswordConfirmed;
    }

    public String getDialogNewPasswordConfirmed() {
        return dialogNewPasswordConfirmed;
    }

    public void setCurrPasswdComp(RichInputText currPasswdComp) {
        this.currPasswdComp = currPasswdComp;
    }

    public RichInputText getCurrPasswdComp() {
        return currPasswdComp;
    }

    public void setNewPasswdComp(RichInputText newPasswdComp) {
        this.newPasswdComp = newPasswdComp;
    }

    public RichInputText getNewPasswdComp() {
        return newPasswdComp;
    }

    public void setNewPasswdCnfmComp(RichInputText newPasswdCnfmComp) {
        this.newPasswdCnfmComp = newPasswdCnfmComp;
    }

    public RichInputText getNewPasswdCnfmComp() {
        return newPasswdCnfmComp;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }
}

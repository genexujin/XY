package edu.hp.view.security;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginUser {
    
    private String userName;
    private String displayName;
    private ArrayList<String> userGroups;
    private ArrayList<String> userRoles;
    private HashMap<String,Boolean> isUserInRole;
    
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
}

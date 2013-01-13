package edu.hp.view.security;

import java.util.ArrayList;

public class LoginUser {
    
    private String userName;
    private ArrayList<String> userGroups;
    private ArrayList<String> userRoles;
    
    
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
}

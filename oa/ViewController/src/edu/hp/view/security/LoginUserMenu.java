package edu.hp.view.security;

public class LoginUserMenu {
    
    private String menuId;
    private String menuName;
    private String menuTaskFlowURL;
    private String menuCategory;
    private String parentId;
    private String menuIconURL;
    private String menuMasterCategory;

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuTaskFlowURL(String menuTaskFlowURL) {
        this.menuTaskFlowURL = menuTaskFlowURL;
    }

    public String getMenuTaskFlowURL() {
        return menuTaskFlowURL;
    }

    public void setMenuCategory(String menuCategory) {
        this.menuCategory = menuCategory;
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setMenuIconURL(String menuIconURL) {
        this.menuIconURL = menuIconURL;
    }

    public String getMenuIconURL() {
        return menuIconURL;
    }

    public void setMenuMasterCategory(String menuMasterCategory) {
        this.menuMasterCategory = menuMasterCategory;
    }

    public String getMenuMasterCategory() {
        return menuMasterCategory;
    }
}

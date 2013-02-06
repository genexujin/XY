package edu.hp.view.bean.main;

import edu.hp.view.bean.session.LoginUserMenuBean;
import edu.hp.view.security.LoginUser;
import edu.hp.view.security.LoginUserMenu;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.Constants;
import edu.hp.view.utils.JSFUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpSession;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichToolbar;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import oracle.ui.pattern.dynamicShell.Tab;
import oracle.ui.pattern.dynamicShell.TabContext;


public class UITabBean {

    protected TabContext tabContext = null;

    protected LoginUserMenuBean menuBean;

    //    protected String launchTitle;
    //    protected String launchTaskflow;

    public UITabBean() {

        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");

        if (user == null) {
            HttpSession session =
                (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            user = (LoginUser)session.getAttribute(Constants.SECURITY_FILTER_SESSION_KEY);
            JSFUtils.setExpressionValue("#{sessionScope.LoginUserBean}", user);

        }

    }

    /**
     *
     */
    protected void initMenus() {

        LoginUserMenuBean menus = (LoginUserMenuBean)JSFUtils.resolveExpression("#{sessionScope.LoginUserMenuBean}");

        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");

        if (menus == null) {

            menus = new LoginUserMenuBean();

            JSFUtils.setExpressionValue("#{sessionScope.LoginUserMenuBean}", menus);


            OperationBinding binding = ADFUtils.findOperation("queryUserMenu");
            Map map = binding.getParamsMap();
            System.err.println("user name: " + user.getUserName());
            map.put("userName", user.getUserName());
            binding.execute();

            if (binding.getErrors().isEmpty()) {
                createMenus(menus);
            }

        }
    }

    /**
     *
     * @param menus
     */
    protected void createMenus(LoginUserMenuBean menus) {

        //TODO add more menus list for Report and Admin pages
        menus.setSysAncmtMenus(new ArrayList<LoginUserMenu>());
        menus.setSysCarMenus(new ArrayList<LoginUserMenu>());
        menus.setSysClsRmMenus(new ArrayList<LoginUserMenu>());
        menus.setSysConfRmMenus(new ArrayList<LoginUserMenu>());
        menus.setSysHdMenus(new ArrayList<LoginUserMenu>());
        menus.setSysMealMenus(new ArrayList<LoginUserMenu>());
        menus.setSysPurMenus(new ArrayList<LoginUserMenu>());
        menus.setAdminMenus(new ArrayList<LoginUserMenu>());


        DCIteratorBinding it = ADFUtils.findIterator("UserMenusIterator");
        //it.executeQuery();
        Row[] allRowsInRange = it.getAllRowsInRange();
        if (allRowsInRange != null) {
            for (Row row :allRowsInRange) {

                LoginUserMenu menu = new LoginUserMenu();

                menu.setMenuId((String)row.getAttribute("MenuId"));
                menu.setMenuCategory((String)row.getAttribute("MenuCategory"));
                menu.setMenuMasterCategory((String)row.getAttribute("MenuMasterCategory"));
                menu.setMenuName((String)row.getAttribute("MenuName"));
                menu.setMenuTaskFlowURL((String)row.getAttribute("MenuTaskflowUrl"));
                menu.setMenuIconURL((String)row.getAttribute("MenuIconUrl"));

                if (menu.getMenuMasterCategory().equals("SYS") && menu.getMenuCategory().equals("ANCMT")) {
                    menus.getSysAncmtMenus().add(menu);
                } else if (menu.getMenuMasterCategory().equals("SYS") && menu.getMenuCategory().equals("CAR")) {
                    menus.getSysCarMenus().add(menu);
                } else if (menu.getMenuMasterCategory().equals("SYS") && menu.getMenuCategory().equals("CLSRM")) {
                    menus.getSysClsRmMenus().add(menu);
                } else if (menu.getMenuMasterCategory().equals("SYS") && menu.getMenuCategory().equals("CONFRM")) {
                    menus.getSysConfRmMenus().add(menu);
                } else if (menu.getMenuMasterCategory().equals("SYS") && menu.getMenuCategory().equals("HD")) {
                    menus.getSysHdMenus().add(menu);
                } else if (menu.getMenuMasterCategory().equals("SYS") && menu.getMenuCategory().equals("MEAL")) {
                    menus.getSysMealMenus().add(menu);
                } else if (menu.getMenuMasterCategory().equals("SYS") && menu.getMenuCategory().equals("PUR")) {
                    menus.getSysPurMenus().add(menu);
                } else if (menu.getMenuMasterCategory().equals("ADMIN")) {
                    menus.getAdminMenus().add(menu);
                }
            }
        }
    }

    /**
     * @param title The tab title shown
     * @param taskflowId task flow document to show
     * @param parameters required and optional task flow parameters passed in a HashMap
     * @param newTab true / false dependent on whether a new tab instance should
     *        be displayed or an existing selected
     */
    protected void _launchActivity(String title, String taskflowId, HashMap parameters, boolean newTab) {
        try {
            if (newTab) {
                TabContext.getCurrentInstance().addTab(title, taskflowId, parameters);
            } else {
                TabContext.getCurrentInstance().addOrSelectTab(title, taskflowId, parameters);
            }
        } catch (TabContext.TabOverflowException toe) {
            // causes a dialog to be displayed to the user saying that there are
            // too many tabs open - the new tab will not be opened...
            toe.handleDefault();
        }
    }


    public void openMenu(ActionEvent actionEvent) {
        try {
            UIComponent component = actionEvent.getComponent();
            String title = (String)(component.getAttributes().get("title"));
            String taskflowId = (String)(component.getAttributes().get("taskflow"));
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            TabContext.getCurrentInstance().addOrSelectTab(title, taskflowId, parameters);

        } catch (TabContext.TabOverflowException toe) {
            // causes a dialog to be displayed to the user saying that there are
            // too many tabs open - the new tab will not be opened...
            toe.handleDefault();
        }
    }

    public void closeCurrentActivity(ActionEvent actionEvent) {
        TabContext tabContext = TabContext.getCurrentInstance();
        int tabIndex = tabContext.getSelectedTabIndex();
        if (tabIndex != -1) {
            tabContext.removeTab(tabIndex);
        }
    }

    public void currentTabDirty(ActionEvent e) {
        /**
            * When called, marks the current tab "dirty". Only at the View level
            * is it possible to mark a tab dirty since the model level does not
            * track to which tab data belongs.
            */
        TabContext tabContext = TabContext.getCurrentInstance();
        tabContext.markCurrentTabDirty(true);
    }

    public void currentTabClean(ActionEvent e) {
        TabContext tabContext = TabContext.getCurrentInstance();
        tabContext.markCurrentTabDirty(false);
    }

    protected void _launchActivity(String title, String taskflowId, boolean newTab) {
        try {
            if (newTab) {
                TabContext.getCurrentInstance().addTab(title, taskflowId);
            } else {
                TabContext.getCurrentInstance().addOrSelectTab(title, taskflowId);
            }
        } catch (TabContext.TabOverflowException toe) {
            // causes a dialog to be displayed to the user saying that there are
            // too many tabs open - the new tab will not be opened...
            toe.handleDefault();
        }
    }

    public void showInnerToolbar() {
        if (!this.isInnerToolBarVisible()) {
            RichToolbar rt = this.getInnerToolBar();
            rt.setVisible(true);
            AdfFacesContext adfFacesCtx = AdfFacesContext.getCurrentInstance();
            adfFacesCtx.addPartialTarget(rt);
        }
    }

    public void hideInnerToolBar() {
        if (this.isInnerToolBarVisible()) {
            this.getTabContext().getInnerToolbarArea().setVisible(true);
            RichToolbar rt = this.getInnerToolBar();
            rt.setVisible(false);
            AdfFacesContext adfFacesCtx = AdfFacesContext.getCurrentInstance();
            adfFacesCtx.addPartialTarget(this.getTabContext().getInnerToolbarArea());
            adfFacesCtx.addPartialTarget(rt);
        }
    }

    public boolean isInnerToolBarVisible() {
        if (this.getInnerToolBar() != null) {
            return this.getInnerToolBar().isVisible();
        }
        return false;
    }

    public TabContext getTabContext() {
        tabContext = TabContext.getCurrentInstance();
        return tabContext;
    }

    //closes all opened tabs

    public void closeAllTabs(ActionEvent actionEvent) {
        List<Tab> tabs = this.getTabContext().getTabs();
        if (tabs != null && tabs.size() > 0) {
            for (Tab t : tabs) {
                this.getTabContext().removeTab(t.getIndex());
            }
        }
    }

    //closes all opened tabs except the current

    public void closeAllButCurrent(ActionEvent actionEvent) {
        List<Tab> tabs = this.getTabContext().getTabs();
        if (tabs != null && tabs.size() > 0) {
            int selectedTabIndex = this.getTabContext().getSelectedTabIndex();
            for (int i = 0; i < tabs.size(); i++) {
                if (selectedTabIndex != i) {
                    this.getTabContext().removeTab(i);
                }
            }
        }
    }


    //the inner tool bar is a Facet contained in a RichPanelGroupLayout component. The facet has a single
    //child, which is the toolbar added in the CustomerCare page

    protected RichToolbar getInnerToolBar() {
        RichPanelGroupLayout innerToolbarArea = this.getTabContext().getInnerToolbarArea();
        UIComponent comp = innerToolbarArea.getChildren().get(0).getChildren().get(0);
        if (comp instanceof RichToolbar) {
            RichToolbar rt = (RichToolbar)comp;
            return rt;
        }
        return null;
    }

    public void setTabContext(TabContext tabContext) {
        this.tabContext = tabContext;
    }

    public void setMenuBean(LoginUserMenuBean menuBean) {
        this.menuBean = menuBean;
    }

    public LoginUserMenuBean getMenuBean() {
        return menuBean;
    }


}

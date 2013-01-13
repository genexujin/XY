package hpedu.test.view;

import java.util.HashMap;
import java.util.List;
import hpedu.test.view.utils.*;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichToolbar;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.ui.pattern.dynamicShell.Tab;
import oracle.ui.pattern.dynamicShell.TabContext;

public class RegionManager {
    private String taskFlowId = "/WEB-INF/dept-rpt.xml#dept-rpt";
    private HashMap params;

    public RegionManager() {
        
        String welcomeURL = JSFUtils.resolveExpressionAsString("#{viewScope.welcomeURL}");           
        System.err.println(welcomeURL);
        if(welcomeURL!=null)
            this.taskFlowId=   welcomeURL;
    }
    
    private TabContext tabContext = null;
    private String taskFlowName;
    
    public void multipleInstanceActivity(ActionEvent actionEvent) {
        /**
        * Example method when called repeatedly, will open another instance as
        * oppose to selecting a previously opened tab instance. Note the boolean
        * to create another tab instance is set to true.
        */

        _launchActivity("A New Activity", "/WEB-INF/flows/new.xml#new", true);
    }

    public void launchFirstActivity(ActionEvent actionEvent) {
        /**
          * Example method to call a single instance task flow. Note the boolean
          * to create another tab instance is set to false. The taskflow ID is used
          * to track whether to create a new tab or select an existing one.
          */
        _launchActivity("The First Activity", "/WEB-INF/flows/first.xml#first", false);
    }

    public void launchSecondActivity(ActionEvent actionEvent) {
        _launchActivity("Next Activity", "/WEB-INF/flows/second.xml#second", false);
    }

    public void launchThirdActivity(ActionEvent actionEvent) {
        _launchActivity("Third Activity", "/WEB-INF/flows/third.xml#third", false);
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

    private void _launchActivity(String title, String taskflowId, boolean newTab) {
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

    public void launchFirstReplaceNPlace(ActionEvent actionEvent) {
        TabContext tabContext = TabContext.getCurrentInstance();
        try {
            tabContext.setMainContent("/WEB-INF/flows/first.xml#first");
        } catch (TabContext.TabContentAreaDirtyException toe) {
            // TODO: warn user TabContext api needed for this use case.
        }
    }

    public void launchSecondReplaceNPlace(ActionEvent actionEvent) {
        TabContext tabContext = TabContext.getCurrentInstance();
        try {
            tabContext.setMainContent("/WEB-INF/flows/second.xml#second");
        } catch (TabContext.TabContentAreaDirtyException toe) {
            // TODO: warn user TabContext api needed for this use case.
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
    private RichToolbar getInnerToolBar(){
        RichPanelGroupLayout innerToolbarArea = this.getTabContext().getInnerToolbarArea();
        UIComponent comp = innerToolbarArea.getChildren().get(0).getChildren().get(0);
            if (comp instanceof RichToolbar) {
                RichToolbar rt = (RichToolbar)comp;
                return rt;
            }
        return null;
    }
    
    /**
     * @param title The tab title shown 
     * @param taskflowId task flow document to show
     * @param parameters required and optional task flow parameters passed in a HashMap
     * @param newTab true / false dependent on whether a new tab instance should
     *        be displayed or an existing selected
     */
    private void _launchActivity(String title, String taskflowId, HashMap parameters, boolean newTab) {
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

   
    public void launchNewEmployee(ActionEvent actionEvent) {
        System.err.println("here");
        _launchActivity("new Employee","/WEB-INF/new-employee.xml#new-employee",true);
        this.showInnerToolbar();
    }

    public void fireButton(ActionEvent actionEvent) {
        // Add event code here...
        System.err.println("here");
    }

    public void launchEmployeeReport(ActionEvent actionEvent) {
        _launchActivity("员工相关报表","/WEB-INF/emp-rpt.xml#emp-rpt",false);
        this.showInnerToolbar();
    }

    public void launchDeptReport(ActionEvent actionEvent) {
        _launchActivity("部门相关报表","/WEB-INF/dept-rpt.xml#dept-rpt",false);
        this.showInnerToolbar();
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }

    public String getTaskFlowId() {
        return taskFlowId;
    }

    public void setParams(HashMap params) {
        this.params = params;
    }

    public HashMap getParams() {
        return params;
    }

    public void setTabContext(TabContext tabContext) {
        this.tabContext = tabContext;
    }

    public void setTaskFlowName(String taskFlowName) {
        this.taskFlowName = taskFlowName;
    }

    public String getTaskFlowName() {
        return taskFlowName;
    }
}

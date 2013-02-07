package edu.hp.view.dc;

import edu.hp.view.utils.JSFUtils;

import java.util.Map;

import javax.faces.context.FacesContext;

import oracle.binding.DataControl;
import oracle.binding.OperationBinding;
import oracle.binding.TransactionalDataControl;


public class DashboardHandler implements TransactionalDataControl {

    public void handleTaskEvent(String id, String type) {
        JSFUtils.setExpressionValue("#{pageFlowScope.contextObjectType}", type);
        JSFUtils.setExpressionValue("#{pageFlowScope.contextObjectId}", id);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "System");
    }

    public String getName() {
        return null;
    }

    public void release() {
    }

    public Object getDataProvider() {
        return null;
    }

    public boolean invokeOperation(Map p0, OperationBinding p1) {
        return false;
    }

    public boolean isTransactionDirty() {
        return false;
    }

    public void rollbackTransaction() {
    }

    public void commitTransaction() {
    }
}

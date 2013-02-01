package edu.hp.view.bean.admin;

import edu.hp.view.utils.ADFUtils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichSelectManyShuttle;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.Row;
import oracle.jbo.domain.DBSequence;


public class AdminOperationBean {


    List selectedMenus;
    List allMenus;
    List selectedRoles;
    List allRoles;

    private RichSelectManyShuttle roleMenuShuttle;

    public AdminOperationBean() {
    }

    public void confirm(DialogEvent dialogEvent) {

        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            ADFUtils.commit("所有的操作已保存！", "无法保存当前的所有操作，请联系系统管理员！");
        }
        // Add event code here...
    }

    protected void _getAllMenus() {
        allMenus =
                selectItemsForIterator("MenusIterator", "MenuId", "MenuName", "MenuDesc", "MenuMasterCategory", "MenuCategory");
    }

    public static List<SelectItem> selectItemsForIterator(String iteratorName, String valueAttrName,
                                                          String displayAttrName, String displayDescAttrName,
                                                          String masterCategoryAttrName, String subCatetoryAttrName) {
        BindingContext bc = BindingContext.getCurrent();
        DCBindingContainer binding = (DCBindingContainer)bc.getCurrentBindingsEntry();
        DCIteratorBinding iter = binding.findIteratorBinding(iteratorName);
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getAttribute(valueAttrName),
                                           (String)r.getAttribute(masterCategoryAttrName) + "| " +
                                           (String)r.getAttribute(subCatetoryAttrName) + "| " +
                                           (String)r.getAttribute(displayAttrName) + " " +
                                           (String)r.getAttribute(displayDescAttrName)));
        }
        return selectItems;
    }


    public void setSelectedMenus(List selectedMenus) {
        this.selectedMenus = selectedMenus;
    }

    public List getSelectedMenus() {
        if (selectedMenus == null)
            selectedMenus = ADFUtils.attributeListForIterator("MenusOfRoleIterator", "MenuId");
        return selectedMenus;
    }

    public List getSelectedRoles() {
        if (selectedRoles == null)
            selectedRoles = ADFUtils.attributeListForIterator("RolesOfUserIterator", "RoleId");
        return selectedRoles;
    }


    public List getAllMenus() {

        if (allMenus == null)
            _getAllMenus();
        return allMenus;
    }

    public List getAllRoles() {
        if (allRoles == null) {
            BindingContext bc = BindingContext.getCurrent();
            DCBindingContainer binding = (DCBindingContainer)bc.getCurrentBindingsEntry();
            DCIteratorBinding iter = binding.findIteratorBinding("RolesIterator");
            List<SelectItem> selectItems = new ArrayList<SelectItem>();
            for (Row r : iter.getAllRowsInRange()) {
                selectItems.add(new SelectItem(r.getAttribute("RoleId").toString(),
                                               (String)r.getAttribute("RoleName") + "| " +
                                               (String)r.getAttribute("RoleDesc")));
            }
            allRoles = selectItems;
        }
        return allRoles;
    }

    public String saveEmpRoles() {

        DBSequence userId = (DBSequence)ADFUtils.getBoundAttributeValue("Id");

        DCIteratorBinding iter = ADFUtils.findIterator("RolesOfUserIterator");
        //Removing all rows
        for (Row r : iter.getAllRowsInRange()) {
            r.remove();
        }

        if (selectedRoles != null && selectedRoles.size() > 0) {
            for (int i = 0; i < selectedRoles.size(); i++) {
                Row row = iter.getRowSetIterator().createRow();
                row.setNewRowState(Row.STATUS_INITIALIZED);
                row.setAttribute("UserId", userId.toString());
                row.setAttribute("RoleId", selectedRoles.get(i));
                iter.getRowSetIterator().insertRow(row);
                iter.setCurrentRowWithKey(row.getKey().toStringFormat(true));
            }
        }

        ADFUtils.commit("用户角色保存成功！", "用户角色保存失败，请联系管理员！");
        return null;
    }


    public String saveRoleMenu() {

        DBSequence roleId = (DBSequence)ADFUtils.getBoundAttributeValue("RoleId");

        DCIteratorBinding iter = ADFUtils.findIterator("MenusOfRoleIterator");
        //Removing all rows
        for (Row r : iter.getAllRowsInRange()) {
            r.remove();
        }

        if (selectedMenus != null && selectedMenus.size() > 0) {
            for (int i = 0; i < selectedMenus.size(); i++) {
                Row row = iter.getRowSetIterator().createRow();
                row.setNewRowState(Row.STATUS_INITIALIZED);
                row.setAttribute("RoleId", roleId);
                row.setAttribute("MenuId", selectedMenus.get(i));
                iter.getRowSetIterator().insertRow(row);
                iter.setCurrentRowWithKey(row.getKey().toStringFormat(true));
            }
        }

        ADFUtils.commit("角色菜单权限保存成功！", "角色菜单权限保存失败，请联系管理员！");

        return null;
    }

    public void setRoleMenuShuttle(RichSelectManyShuttle roleMenuShuttle) {
        this.roleMenuShuttle = roleMenuShuttle;
    }

    public RichSelectManyShuttle getRoleMenuShuttle() {
        return roleMenuShuttle;
    }

    public void setSelectedRoles(List selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public void setAllMenus(List allMenus) {
        this.allMenus = allMenus;
    }

    public void setAllRoles(List allRoles) {

        this.allRoles = allRoles;
    }


}

package edu.hp.view.bean.doc;

import edu.hp.view.utils.ADFUtils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.DBSequence;

public class UserGroupBean {
    private RichPopup popup;

    public UserGroupBean() {
    }

    private String userName;
    List selectedEmps;
    List<SelectItem> allEmps;

    public String searchUser() {
        System.err.println(userName);
        OperationBinding op = ADFUtils.findOperation("query");
        op.getParamsMap().put("userName", userName);
        op.execute();
        //        ADFUtils.findIterator("EmpsQueryIterator").executeQuery();
        allEmps = selectItemsForIterator("EmpsQueryIterator", "Id", "Fullname");
        return null;
    }

    public String save() {
        ADFUtils.commit("用户组保存成功！", "用户组保存失败，请联系管理员！");
        return null;
    }

    public void onPopupLaunch(PopupFetchEvent popupFetchEvent) {
        System.err.println("ahah");
        this.selectedEmps = null;
    }

    public String saveGroupUsers() {

        DBSequence grpId = (DBSequence)ADFUtils.getBoundAttributeValue("Id");

        DCIteratorBinding iter = ADFUtils.findIterator("UserGroupsIterator");

        if (selectedEmps != null && selectedEmps.size() > 0) {
            for (int i = 0; i < selectedEmps.size(); i++) {
                boolean alreadyAdded = false;
                for (Row r : iter.getAllRowsInRange()) {
                    if (r.getAttribute("UserId").equals(selectedEmps.get(i))) {
                        alreadyAdded = true;
                        break;
                    }
                }
                if (alreadyAdded)
                    continue;

                System.err.println("here");
                Row row = iter.getRowSetIterator().createRow();
                row.setNewRowState(Row.STATUS_INITIALIZED);
                row.setAttribute("GroupId", grpId.toString());
                row.setAttribute("UserId", selectedEmps.get(i));
                String userNameTmp = null;
                for (SelectItem item : allEmps) {
                    if (item.getValue().equals(selectedEmps.get(i)))
                        userNameTmp = item.getLabel();
                }
                row.setAttribute("UserName", userNameTmp);
                iter.getRowSetIterator().insertRow(row);
                //                iter.setCurrentRowWithKey(row.getKey().toStringFormat(true));
            }
        }

        this.popup.hide();
        ADFUtils.commit("用户组保存成功！", "用户组保存失败，请联系管理员！");
        return null;
    }

    public static List<SelectItem> selectItemsForIterator(String iteratorName, String valueAttrName,
                                                          String displayAttrName) {
        BindingContext bc = BindingContext.getCurrent();
        DCBindingContainer binding = (DCBindingContainer)bc.getCurrentBindingsEntry();
        DCIteratorBinding iter = binding.findIteratorBinding(iteratorName);
        iter.setRangeSize(-1);
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getAttribute(valueAttrName), (String)r.getAttribute(displayAttrName)));
        }
        return selectItems;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setSelectedEmps(List selectedEmps) {
        this.selectedEmps = selectedEmps;
    }

    public List getSelectedEmps() {
        //
        //        if (selectedEmps == null)
        //        selectedEmps = ADFUtils.attributeListForIterator("UserGroupsIterator", "UserId");
        return selectedEmps;

    }

    public void setAllEmps(List allEmps) {
        this.allEmps = allEmps;
    }

    public List getAllEmps() {
        return allEmps;
    }

    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }


}

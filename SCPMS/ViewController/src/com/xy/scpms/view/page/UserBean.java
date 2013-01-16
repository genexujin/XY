package com.xy.scpms.view.page;


import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.io.Serializable;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;


/**
 * Implements the basic backing-bean mechanics to handle page with shuttle.
 *
 * By injecting managed properties into the properties of this bean
 * you can setup the shuttle data binding declaratively.
 *
 */
public class UserBean implements Serializable {

    String beanName = "UserBean";
    String allItemsIteratorName = "GroupsIterator";
    String allItemsValueAttrName = "GName";
    String allItemsDisplayAttrName = "GName";
    String allItemsDescriptionAttrName = "GDescription";
    String selectedValuesIteratorName = "UserRolesIterator";
    String selectedValuesValueAttrName = "GName";
    List selectedValues;
    List allItems;
    private boolean refreshSelectedList = false;
    private BindingContainer bindings;
    private RichPopup popup;
    private RichInputText passwordInput;
    private RichInputText confirmPassInput;

    public String showPopup() {

        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popup.show(hints);
        return null;
    }

    public String updateRole() {
        try {
            OperationBinding binding =
                ADFUtils.findOperation("updateUserRole");
            binding.getParamsMap().put("userRoles", selectedValues);
            binding.execute();
            popup.hide();
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage("更新角色时出错！");
        }
        return null;
    }

    public String cancelUpdateRole() {
        popup.hide();
        return null;
    }


    /**
     * Setter for 'allItemsIteratorName' property.
     * @param allItemsIteratorName Name of the iterator for all items in the list
     */
    public void setAllItemsIteratorName(String allItemsIteratorName) {
        this.allItemsIteratorName = allItemsIteratorName;
    }

    /**
     * Getter for 'allItemsIteratorName' property.
     * @return Name of the iterator to use for all items in the list
     */
    public String getAllItemsIteratorName() {
        return allItemsIteratorName;
    }

    /**
     * Set allItems value attribute name.
     * @param allItemsValueAttrName name of attr to use as value of all items list
     */
    public void setAllItemsValueAttrName(String allItemsValueAttrName) {
        this.allItemsValueAttrName = allItemsValueAttrName;
    }

    /**
     * Get allItems value attribute name.
     * @return name of attr to use as value of all items list
     */
    public String getAllItemsValueAttrName() {
        return allItemsValueAttrName;
    }

    /**
     * Setter for 'allItemsDisplayAttrName' property.
     * @param allItemsDisplayAttrName attr to use for display in all items list
     */
    public void setAllItemsDisplayAttrName(String allItemsDisplayAttrName) {
        this.allItemsDisplayAttrName = allItemsDisplayAttrName;
    }

    /**
     * Getter for 'allItemsDisplayAttrName' property.
     * @return attr to use for display in all items list
     */
    public String getAllItemsDisplayAttrName() {
        return allItemsDisplayAttrName;
    }

    /**
     * Setter for 'allItemsDescriptionAttrName' property.
     * @param allItemsDescriptionAttrName attrib for description in all items list
     */
    public void setAllItemsDescriptionAttrName(String allItemsDescriptionAttrName) {
        this.allItemsDescriptionAttrName = allItemsDescriptionAttrName;
    }

    /**
     * Getter for 'allItemsDescriptionAttrName' property.
     * @return attrib for description in all items list
     */
    public String getAllItemsDescriptionAttrName() {
        return allItemsDescriptionAttrName;
    }

    /**
     * Setter for 'selectedValuesIteratorName' property.
     * @param selectedValuesIteratorName name of iterator for selected values
     */
    public void setSelectedValuesIteratorName(String selectedValuesIteratorName) {
        this.selectedValuesIteratorName = selectedValuesIteratorName;
    }

    /**
     * Getter for 'selectedValuesIteratorName' property.
     * @return name of iterator for selected values
     */
    public String getSelectedValuesIteratorName() {
        return selectedValuesIteratorName;
    }

    /**
     * Setter for 'selectedValuesValueAttrName' property.
     * @param selectedValuesValueAttrName name of attr to use for selected value
     */
    public void setSelectedValuesValueAttrName(String selectedValuesValueAttrName) {
        this.selectedValuesValueAttrName = selectedValuesValueAttrName;
    }

    /**
     * Getter for 'selectedValuesValueAttrName' property.
     * @return name of attr to use for selected value
     */
    public String getSelectedValuesValueAttrName() {
        return selectedValuesValueAttrName;
    }

    /**
     * Setter for 'selectedValues' property.
     * @param selectedValues List of selected values in shuttle
     */
    public void setSelectedValues(List selectedValues) {
        this.selectedValues = selectedValues;
    }

    /**
     * Event handler for shuttle value change event.
     * @param event value change event
     */
    public void refreshSelectedList(ValueChangeEvent event) {
        refreshSelectedList = true;
    }

    /**
     * Getter for 'selectedValues' property.
     * @return List of selected values in shuttle
     */
    public List getSelectedValues() {

        selectedValues =
                ADFUtils.attributeListForIterator(selectedValuesIteratorName,
                                                  selectedValuesValueAttrName);

        return selectedValues;
    }

    /**
     * Setter for 'allItems' property.
     * @param allItems list of SelectItem representing all items in available list
     */
    public void setAllItems(List allItems) {
        this.allItems = allItems;
    }

    /**
     * Getter for 'allItems' property.
     * @return list of SelectItem representing all items in available list
     */
    public List getAllItems() {
        if (allItems == null) {
            allItems =
                    ADFUtils.selectItemsForIterator(allItemsIteratorName, allItemsValueAttrName,
                                                    allItemsDisplayAttrName,
                                                    allItemsDescriptionAttrName);
        }
        return allItems;
    }

    /**
     * Action handler for the (Update Interests) button.
     *
     * Note that this event handler does not execute the ADF OperationBinding
     * since that gets executed by JSF by virtue of the EL expression in the
     * ActionListener property of the button:
     *
     * @return JSF navigation rule name - null to stay on same page
     */
    public String onUpdateInterests() {
        FacesMessage msg =
            JSFUtils.getMessageFromBundle("userInterests.changedApplied",
                                          FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }


    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }


    public String saveNewPassword() {
        String password = (String)this.getPasswordInput().getValue();
        String passwordRepeated =
            (String)this.getConfirmPassInput().getValue();

        if (password != null && passwordRepeated != null &&
            password.equals(passwordRepeated)) {
            ADFUtils.findOperation("Commit").execute();
            JSFUtils.addFacesInformationMessage("密码已成功更改！");
        } else {
            ADFUtils.findOperation("Rollback").execute();
            JSFUtils.addFacesErrorMessage("两次密码输入不匹配，请重新输入！");
        }

        return null;
    }

    public void setPasswordInput(RichInputText passwordInput) {
        this.passwordInput = passwordInput;
    }

    public RichInputText getPasswordInput() {
        return passwordInput;
    }

    public void setConfirmPassInput(RichInputText confirmPassInput) {
        this.confirmPassInput = confirmPassInput;
    }

    public RichInputText getConfirmPassInput() {
        return confirmPassInput;
    }

    public void confirm(DialogEvent dialogEvent) {

        try {
            if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
                ADFUtils.findOperation("Commit").execute();
                JSFUtils.addFacesInformationMessage("数据保存成功！");
            } else {
                ADFUtils.findOperation("Rollback").execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

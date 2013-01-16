package com.xy.scpms.view.page;


import com.xy.view.utils.ADFUtils;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;


public class InvBatchBean {
    private RichPopup popup;
    private String action;

    public InvBatchBean() {
    }

    public String delete() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popup.show(hints);
        action = "delete";
        return null;
    }

    public void confirm(DialogEvent dialogEvent) {
        // Add event code here...
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {

            if (action.equals("delete")) {
                OperationBinding binding = ADFUtils.findOperation("Delete");
                binding.execute();
            }
            OperationBinding binding = ADFUtils.findOperation("Commit");
            binding.execute();

        }
        popup.hide();


    }

    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }

    public String save() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popup.show(hints);
        action = "save";
        return null;
    }
}

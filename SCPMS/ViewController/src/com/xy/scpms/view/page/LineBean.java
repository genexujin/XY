package com.xy.scpms.view.page;


import com.xy.view.utils.ADFUtils;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.LaunchPopupEvent;


public class LineBean {
    private RichPopup memoInput;

    public LineBean() {
    }

    public void onLovLaunch(LaunchPopupEvent launchPopupEvent) {
        // Add event code here...
    }

    public void onPlantLovLaunch(LaunchPopupEvent launchPopupEvent) {
        ADFUtils.onLovLaunch(launchPopupEvent, "pc1:t2:shipPlantId_afrLovPopupId",
                             "shipPlantId", "shipPlantId",
                             "AdfRichPopup.ALIGN_BEFORE_END");
    }

    public void onOwnerLovLaunch(LaunchPopupEvent launchPopupEvent) {
        ADFUtils.onLovLaunch(launchPopupEvent, "pc1:t2:shipOwnerId_afrLovPopupId",
                             "shipOwnerId", "shipOwnerId",
                             "AdfRichPopup.ALIGN_BEFORE_END");
    }

    public void onBrokerLovLaunch(LaunchPopupEvent launchPopupEvent) {
        ADFUtils.onLovLaunch(launchPopupEvent, "pc1:t2:shipBrokerId_afrLovPopupId",
                             "shipBrokerId", "shipBrokerId",
                             "AdfRichPopup.ALIGN_BEFORE_END");
    }

    public void setMemoInput(RichPopup memoInput) {
        this.memoInput = memoInput;
    }

    public RichPopup getMemoInput() {
        return memoInput;
    }

    public String closeMemoPopup() {
       memoInput.hide();
        return null;
    }
}

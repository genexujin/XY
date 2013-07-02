package edu.hp.view.bean.meal;

import edu.hp.view.utils.ADFUtils;

import oracle.adf.view.rich.event.DialogEvent;

public class MealListBean {
    public MealListBean() {
    }

    public void onDelConfirm(DialogEvent dialogEvent) {
        if(dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)){
            ADFUtils.findOperation("Delete").execute();
            ADFUtils.findOperation("Commit").execute();
        }
    }
}

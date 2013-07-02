package edu.hp.view.bean.meal;

import edu.hp.view.utils.ADFUtils;

import oracle.adf.view.rich.event.DialogEvent;

public class MealEditBean {
    public MealEditBean() {
    }

    public void onSaveConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            boolean b = ADFUtils.commit("用餐申请已保存并生效！", "用餐申请保存过程中出错，请寻求管理员帮助！");
        }
    }

    public String newRow() {
        ADFUtils.findOperation("Rollback").execute();
        ADFUtils.findOperation("newRow").execute();
        return null;
    }
}

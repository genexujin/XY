package edu.hp.view.bean.vehicle;

import edu.hp.model.common.Constants;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;


public class VehicleApplicationBean {

    private String queryState;

    public VehicleApplicationBean() {
    }

    public String save() {

        //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
        ADFUtils.commit("车辆预订已保存！", "车辆预订保存失败，请核对输入的信息或联系管理员！");

        return null;
    }

    public String submit() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && state.equals(Constants.STATE_INITIAL)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_PENDING_REVIEW);
            ADFUtils.commit("车辆预订已提交审核！", "车辆预订提交审核失败，请核对输入的信息或联系管理员！");
        }
        return null;
    }

    public String approve() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && state.equals(Constants.STATE_PENDING_REVIEW)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_REVIEWED);

            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            ADFUtils.commit("车辆预订已审核！", "车辆预订审核失败，请核对输入的信息或联系管理员！");

        }
        return null;
    }

    public String reject() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null &&
            (state.equals(Constants.STATE_PENDING_REVIEW) || state.equals(Constants.STATE_REVIEWED))) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_REJECTED);
            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            ADFUtils.commit("车辆预订已提交审核！", "车辆预订提交审核失败，请核对输入的信息或联系管理员！");
        }
        return null;
    }

    public String cancel() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && (state.equals(Constants.STATE_PENDING_REVIEW) || state.equals(Constants.STATE_INITIAL))) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_CANCELED);
            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            ADFUtils.commit("车辆预订已取消！", "车辆预订取消失败，请核对输入的信息或联系管理员！");
        }
        return null;
    }

    public String findByState() {
        OperationBinding binding = ADFUtils.findOperation("findByState");
        binding.execute();
        return null;
    }

    public void setQueryState(String queryState) {
        this.queryState = queryState;
    }

    public String getQueryState() {
        return queryState;
    }

    public String planTrip() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && state.equals(Constants.STATE_REVIEWED)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_TRIP_PLANNED);

            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            ADFUtils.commit("车辆预订已完成调度！", "车辆预订调度失败，请核对输入的信息或联系管理员！");

        }
        return null;
    }

    public void delete(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            ADFUtils.findOperation("deleteByPK").execute();
            JSFUtils.addFacesInformationMessage("车辆预定已删除！");
        }
    }
}

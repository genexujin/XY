package edu.hp.view.bean.conf;

import edu.hp.model.common.Constants;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import oracle.binding.OperationBinding;


public class ConfRmAppBean {
    public ConfRmAppBean() {
    }

    private String queryState;

    public String findByState() {
        //System.err.println(queryState);
        OperationBinding binding = ADFUtils.findOperation("findByState");
        binding.execute();
        return null;
    }

    public String save() {

        if (ensureTimeConflicts()) {
            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            ADFUtils.commit("会议室预订已保存！", "会议室预订保存失败，请核对输入的信息或联系管理员！");
        } else {
            JSFUtils.addFacesErrorMessage("该会议室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
        }
        return null;
    }

    public String submit() {

        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && state.equals(Constants.STATE_INITIAL)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_PENDING_REVIEW);
            if (ensureTimeConflicts()) {
                //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
                ADFUtils.commit("会议室预订已提交审核！", "会议室预订提交审核失败，请核对输入的信息或联系管理员！");
            } else {
                JSFUtils.addFacesErrorMessage("该会议室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
            }
        }
        return null;
    }

    public String reject() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && state.equals(Constants.STATE_PENDING_REVIEW)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_REJECTED);
            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            ADFUtils.commit("会议室预订已拒绝！", "会议室预订审核拒绝失败，请核对输入的信息或联系管理员！");
        }
        return null;
    }

    public String cancel() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && !state.equals(Constants.STATE_REVIEWED)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_CANCELED);
            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            ADFUtils.commit("会议室预订已取消！", "会议室预订取消失败，请核对输入的信息或联系管理员！");
        }
        return null;
    }

    public String approve() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && state.equals(Constants.STATE_PENDING_REVIEW)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_REVIEWED);
            if (ensureTimeConflicts()) {
                //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
                ADFUtils.commit("会议室预订已审核！", "会议室预订审核失败，请核对输入的信息或联系管理员！");
            } else {
                JSFUtils.addFacesErrorMessage("该会议室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
            }
        }
        return null;
    }

    protected Boolean ensureTimeConflicts() {

        Boolean result = false;
        OperationBinding binding = ADFUtils.findOperation("ifConflict");
        binding.execute();
        result = (Boolean)binding.getResult();
        return result;

    }

    public void setQueryState(String queryState) {
        this.queryState = queryState;
    }

    public String getQueryState() {
        return queryState;
    }
}

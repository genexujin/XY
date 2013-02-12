package edu.hp.view.bean.vehicle;

import edu.hp.model.common.Constants;
import edu.hp.model.pojo.Notification;
import edu.hp.view.bean.BaseBean;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import java.util.Date;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.DBSequence;


public class VehicleApplicationBean extends BaseBean {

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
            boolean success = ADFUtils.commit("车辆预订已提交审核！", "车辆预订提交审核失败，请核对输入的信息或联系管理员！");
            if (success) {
                String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
                String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
                String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
                String title = (String)ADFUtils.getBoundAttributeValue("Title");

                String noteTitle = "您为事由：" + title + " 所做的用车申请已提交审核 ";
                String noteContent = " 提交时间：" + new Date();
                //send to requester
                sendNotification(noteTitle, noteContent, userId, null);
                //send to approver
                String apprvTitle = "有新的用车申请等待您的审核";
                String apprvContent = " 事由：" + title + " 申请人： " + userDisplayName;
                sendNotification(apprvTitle, apprvContent, null, Constants.ROLE_OFFICE_MGR);
                
                //create task
                createTask(id, Constants.CONTEXT_TYPE_VEHICLE, apprvTitle, Constants.ROLE_OFFICE_MGR);

                ADFUtils.findOperation("Commit").execute();
            }else{
                ADFUtils.setBoundAttributeValue("State", Constants.STATE_INITIAL);
            }
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
            JSFUtils.addFacesInformationMessage("车辆预订已删除！");
        }
    }
}

package edu.hp.view.bean.vehicle;

import edu.hp.model.common.Constants;
import edu.hp.model.pojo.Notification;
import edu.hp.view.bean.BaseBean;
import edu.hp.view.security.LoginUser;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import java.util.Date;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;


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
        ADFUtils.setBoundAttributeValue("State", Constants.STATE_REVIEWED);
        ADFUtils.setBoundAttributeValue("SubmitDate", new Timestamp(System.currentTimeMillis()));

        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
        int needNotification = 0;
        if (user.getIsUserInRole().get(Constants.ROLE_ZONGWU_MGR) != null) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_TRIP_PLANNED);
            needNotification = 0;
        } else {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_REVIEWED);
            needNotification = 2;
        }
        
        boolean success = ADFUtils.commit("车辆预订已提交并等待调度！", "车辆预订提交失败，请核对输入的信息或联系管理员！");

        if (success) {
        
            String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
            String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
            String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
            String title = (String)ADFUtils.getBoundAttributeValue("Title");

            if (needNotification == 2) {
                String noteTitle = "您为事由：" + title + " 所做的用车申请已提交并等待调度！ ";
                String dateStr = getDateString();
                String noteContent = " 提交时间：" + dateStr;
                //send to requester
                sendNotification(noteTitle, noteContent, userId, null);
                //send to approver
                String apprvTitle = "有新的用车申请等待您的调度！ ";
                String apprvContent = " 事由：" + title + " 申请人： " + userDisplayName;
                sendNotification(apprvTitle, apprvContent, null, Constants.ROLE_ZONGWU_MGR);
                //create task
                createTask(id, Constants.CONTEXT_TYPE_VEHICLE, apprvTitle, Constants.ROLE_ZONGWU_MGR, title);
            }             
            ADFUtils.findOperation("Commit").execute();
        } else {
            ADFUtils.setBoundAttributeValue("State", state);
        }

        return null;
    }
    
        /**  根据2013-05-02 需求变更，用车不需要审核直接到调度
    public String submit() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");

        ADFUtils.setBoundAttributeValue("State", Constants.STATE_PENDING_REVIEW);
        ADFUtils.setBoundAttributeValue("SubmitDate", new Timestamp(System.currentTimeMillis()));

        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
        int needNotification = 0;
        if (user.getIsUserInRole().get(Constants.ROLE_ZONGWU_MGR) != null) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_TRIP_PLANNED);
            needNotification = 0;
        } else if (user.getIsUserInRole().get(Constants.ROLE_OFFICE_MGR) != null) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_REVIEWED);
            needNotification = 1;
        } else {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_PENDING_REVIEW);
            needNotification = 2;
        }
        boolean success = ADFUtils.commit("车辆预订已提交审核！", "车辆预订提交审核失败，请核对输入的信息或联系管理员！");

        if (success) {
            String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
            String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
            String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
            String title = (String)ADFUtils.getBoundAttributeValue("Title");

            if (needNotification == 2) {
                String noteTitle = "您为事由：" + title + " 所做的用车申请已提交审核 ";
                String dateStr = getDateString();
                String noteContent = " 提交时间：" + dateStr;
                //send to requester
                sendNotification(noteTitle, noteContent, userId, null);
                //send to approver
                String apprvTitle = "有新的用车申请等待您的审核";
                String apprvContent = " 事由：" + title + " 申请人： " + userDisplayName;
                sendNotification(apprvTitle, apprvContent, null, Constants.ROLE_OFFICE_MGR);
                //create task
                createTask(id, Constants.CONTEXT_TYPE_VEHICLE, apprvTitle, Constants.ROLE_OFFICE_MGR, title);
            } else if (needNotification == 1) {
                String noteTitle = "您为事由：" + title + " 所做的用车申请已审核完成，等待调度中。 ";
                String dateStr = getDateString();
                String noteContent = "审核时间：" + dateStr;
                //send to requester
                sendNotification(noteTitle, noteContent, userId, null);
                //send to approver
                String apprvTitle = "有新的用车申请已审核完成，等待您调度车辆。";
                String apprvContent = " 事由：" + title + " 申请人： " + userDisplayName;
                sendNotification(apprvTitle, apprvContent, null, Constants.ROLE_ZONGWU_MGR);
                //create task
                createTask(id, Constants.CONTEXT_TYPE_VEHICLE, apprvTitle, Constants.ROLE_ZONGWU_MGR, title);
            }

            ADFUtils.findOperation("Commit").execute();
        } else {
            ADFUtils.setBoundAttributeValue("State", state);
        }

        return null;
    }

    public String approve() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && state.equals(Constants.STATE_PENDING_REVIEW)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_REVIEWED);

            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            boolean success = ADFUtils.commit("车辆预订已审核！", "车辆预订审核失败，请核对输入的信息或联系管理员！");
            if (success) {
                String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
                String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
                String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
                String title = (String)ADFUtils.getBoundAttributeValue("Title");

                String noteTitle = "您为事由：" + title + " 所做的用车申请已审核完成，等待调度中。 ";
                String dateStr = getDateString();

                String noteContent = "审核时间：" + dateStr;
                //send to requester
                sendNotification(noteTitle, noteContent, userId, null);
                //send to approver
                String apprvTitle = "有新的用车申请已审核完成，等待您调度车辆。";
                String apprvContent = " 事由：" + title + " 申请人： " + userDisplayName;
                sendNotification(apprvTitle, apprvContent, null, Constants.ROLE_ZONGWU_MGR);

                //create task
                createTask(id, Constants.CONTEXT_TYPE_VEHICLE, apprvTitle, Constants.ROLE_ZONGWU_MGR, title);

                completeTask(Constants.CONTEXT_TYPE_VEHICLE, id, Constants.ROLE_OFFICE_MGR);

                ADFUtils.findOperation("Commit").execute();
            } else {
                ADFUtils.setBoundAttributeValue("State", Constants.STATE_PENDING_REVIEW);
            }
        }
        return null;
    }

*/

    public String refreshTableIterator() {
        ADFUtils.findIterator("VehicleDMLIterator").executeQuery();
        return null;
    }


    public String reject() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null &&
            (state.equals(Constants.STATE_PENDING_REVIEW) || state.equals(Constants.STATE_REVIEWED))) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_REJECTED);
            ADFUtils.setBoundAttributeValue("VehicleId",null);
            ADFUtils.setBoundAttributeValue("VehicleNameVal",null);
            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            boolean success = ADFUtils.commit("车辆预订已拒绝！", "车辆预订拒绝失败，请核对输入的信息或联系管理员！");

            if (success) {
                String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
                String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
                String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
                String title = (String)ADFUtils.getBoundAttributeValue("Title");

                String noteTitle = "您为事由：" + title + " 所做的用车申请已被拒绝。 ";
                String dateStr = getDateString();

                String noteContent = "审核时间：" + dateStr;
                //send to requester
                sendNotification(noteTitle, noteContent, userId, null);
                if (state.equals(Constants.STATE_PENDING_REVIEW))
                    completeTask(Constants.CONTEXT_TYPE_VEHICLE, id, Constants.ROLE_OFFICE_MGR);
                else
                    completeTask(Constants.CONTEXT_TYPE_VEHICLE, id, Constants.ROLE_ZONGWU_MGR);

                ADFUtils.findOperation("Commit").execute();
            } else {
                ADFUtils.setBoundAttributeValue("State", state);
            }
        }
        return null;
    }

    public String cancel() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        //        if (state != null && (state.equals(Constants.STATE_PENDING_REVIEW) || state.equals(Constants.STATE_INITIAL))) {
        ADFUtils.setBoundAttributeValue("State", Constants.STATE_CANCELED);
        //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
        boolean success = ADFUtils.commit("车辆预订已取消！", "车辆预订取消失败，请核对输入的信息或联系管理员！");

        if (success) {
            String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
            String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
            String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
            String title = (String)ADFUtils.getBoundAttributeValue("Title");

            String noteTitle = "您为事由：" + title + " 所做的用车申请已取消。 ";
            String dateStr = getDateString();

            String noteContent = "取消时间：" + dateStr;
            //send to requester
            sendNotification(noteTitle, noteContent, userId, null);
            this.cancelTask(Constants.CONTEXT_TYPE_VEHICLE, id);
            ADFUtils.findOperation("Commit").execute();
        } else {
            ADFUtils.setBoundAttributeValue("State", state);
        }
        //        }
        return null;
    }


    public String planTrip() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && state.equals(Constants.STATE_REVIEWED)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_TRIP_PLANNED);

            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            boolean success = ADFUtils.commit("车辆预订已完成调度！", "车辆预订调度失败，请核对输入的信息或联系管理员！");
            if (success) {
                String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
                String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
                String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
                String title = (String)ADFUtils.getBoundAttributeValue("Title");
                String vehicleName = (String)ADFUtils.getBoundAttributeValue("VehicleName");

                String noteTitle = "您为事由：" + title + " 所做的用车申请已完成调度。 ";
                String dateStr = getDateString();

                String noteContent = "完成调度时间：" + dateStr + " 使用的车辆为：" + vehicleName;
                //send to requester
                sendNotification(noteTitle, noteContent, userId, null);

                completeTask(Constants.CONTEXT_TYPE_VEHICLE, id, Constants.ROLE_ZONGWU_MGR);

                ADFUtils.findOperation("Commit").execute();
            } else {
                ADFUtils.setBoundAttributeValue("State", state);
            }
        }
        return null;
    }

    public void delete(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            ADFUtils.findOperation("deleteByPK").execute();
            JSFUtils.addFacesInformationMessage("车辆预订已删除！");
        }
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
}

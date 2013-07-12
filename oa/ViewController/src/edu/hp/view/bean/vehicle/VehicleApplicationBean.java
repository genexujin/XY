package edu.hp.view.bean.vehicle;

import edu.hp.model.common.Constants;
import edu.hp.model.pojo.Notification;
import edu.hp.view.bean.BaseBean;
import edu.hp.view.security.LoginUser;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.ContextInfoEvent;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;


public class VehicleApplicationBean extends BaseBean {

    private String queryState;
    private String day;
    private RichPopup usuagePopup;

    public VehicleApplicationBean() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        day = formatter.format(new Date());
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
                sendNotification(noteTitle, noteContent, userId, null, Constants.CONTEXT_TYPE_VEHICLE, id);
                //send to approver
                String apprvTitle = "有新的用车申请等待您的调度！ ";
                String apprvContent = " 事由：" + title + " 申请人： " + userDisplayName;
                sendNotification(apprvTitle, apprvContent, null, Constants.ROLE_ZONGWU_MGR,
                                 Constants.CONTEXT_TYPE_VEHICLE, id);
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
            ADFUtils.setBoundAttributeValue("VehicleId", null);
            ADFUtils.setBoundAttributeValue("VehicleNameVal", null);
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
                sendNotification(noteTitle, noteContent, userId, null, Constants.CONTEXT_TYPE_VEHICLE, id);
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
            sendNotification(noteTitle, noteContent, userId, null, Constants.CONTEXT_TYPE_VEHICLE, id);
            this.cancelTask(Constants.CONTEXT_TYPE_VEHICLE, id);
            ADFUtils.findOperation("Commit").execute();
        } else {
            ADFUtils.setBoundAttributeValue("State", state);
        }
        //        }
        return null;
    }

    public String confirm() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && state.equals(Constants.STATE_TRIP_PLANNED)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_TRIP_CONFIRMED);
            boolean success = ADFUtils.commit("您已确认该次用车！", "车辆预订调度失败，请核对输入的信息或联系管理员！");
            if (success) {
                String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
                LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
                completeTaskForUser(Constants.CONTEXT_TYPE_VEHICLE, id, user.getUserId());
                String vehicleName = (String)ADFUtils.getBoundAttributeValue("VehicleName");
                String driverName = (String)ADFUtils.getBoundAttributeValue("DriverName");
                String title = (String)ADFUtils.getBoundAttributeValue("Title");

                sendNotification(driverName + "已确认用车单！", "事由: " + title + " 车辆：" + vehicleName, null,
                                 Constants.ROLE_ZONGWU_MGR, Constants.CONTEXT_TYPE_VEHICLE, id);
                ADFUtils.findOperation("Commit").execute();
            } else {
                ADFUtils.setBoundAttributeValue("State", state);
            }
        }
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
                String ContactPhone = (String)ADFUtils.getBoundAttributeValue("ContactPhone");
                String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
                String title = (String)ADFUtils.getBoundAttributeValue("Title");
                String vehicleName = (String)ADFUtils.getBoundAttributeValue("VehicleName");
                String driverName = (String)ADFUtils.getBoundAttributeValue("DriverName");
                String noteTitle = "您为事由：" + title + " 所做的用车申请已完成调度。 ";
                String dateStr = getDateString();
                String noteContent = "完成调度时间：" + dateStr + " 使用的车辆为：" + vehicleName + " 司机：" + driverName;
                //send to requester
                sendNotification(noteTitle, noteContent, userId, null, Constants.CONTEXT_TYPE_VEHICLE, id);
                completeTask(Constants.CONTEXT_TYPE_VEHICLE, id, Constants.ROLE_ZONGWU_MGR);

                //如果选择了一个司机，则发通知给司机并要求其在系统中确认
                String driverId = (String)ADFUtils.getBoundAttributeValue("DriverId");
                if (driverId != null) {
                    String contactName = (String)ADFUtils.getBoundAttributeValue("ContactName");
                    String contactPhone = (String)ADFUtils.getBoundAttributeValue("ContactPhone");
                    String tripStart = (String)ADFUtils.getBoundAttributeValue("TripStart");
                    String startTime = (String)ADFUtils.getBoundAttributeValue("StartTime");
                    String smsTitle = "有新的派车单发送给您！";
                    //                    String smsContent =
                    //                        " 使用车辆为： " + vehicleName + " 申请人为：" + userDisplayName + " 申请人电话：" + ContactPhone;
                    //                    sendNotification(smsTitle, smsContent, driverId, null);
                    this.sendNotification("您有新的出车单！",
                                          "联系人：" + contactName + " 使用车辆：" + vehicleName + " 联系人电话：" + contactPhone +
                                          "开始用车时间: " + startTime + " 目的地:" + tripStart, driverId, null,
                                          Constants.CONTEXT_TYPE_VEHICLE, id);
                    createTaskForUser(id, Constants.CONTEXT_TYPE_VEHICLE, smsTitle, driverId, "确认调度");
                }
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

    public boolean isVehicleVisible() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state == null) {
            return false;
        }

        Boolean isPlanner = JSFUtils.resolveExpressionAsBoolean("#{sessionScope.LoginUserBean.isUserInRole['车辆调度']}");

        //如果已经调度或者已经确认，则可见
        if (state.equals(Constants.STATE_TRIP_PLANNED) || state.equals(Constants.STATE_TRIP_CONFIRMED))
            return true;
        //如果已经审核且当前登录用户为调度员则可见
        else if (isPlanner != null && state.equals(Constants.STATE_REVIEWED) && isPlanner)
            return true;
        //否则不可见
        else
            return false;
    }

    public boolean isVehichleReadonly() {
        //仅当为调度员时可以修改
        Boolean isPlanner = (Boolean)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean.isUserInRole['车辆调度']}");
        if (isPlanner == null)
            return true;
        return !isPlanner;
    }

    public void setQueryState(String queryState) {
        this.queryState = queryState;
    }

    public String getQueryState() {
        return queryState;
    }

    public void openVehicleUsuage(ActionEvent actionEvent) {

        String startTime = (String)ADFUtils.getBoundAttributeValue("StartTime");
        //        System.err.println(startTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            if (startTime != null)
                date = format.parse(startTime);
            //            System.err.println(date);
        } catch (ParseException pe) {
            // TODO: Add catch code
            pe.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (date != null) {
            this.day = formatter.format(date);
        }
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        //        hints.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID, "ot14");
        //        hints.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, RichPopup.PopupHints.AlignTypes.ALIGN_END_AFTER);
        this.usuagePopup.show(hints);
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setUsuagePopup(RichPopup usuagePopup) {
        this.usuagePopup = usuagePopup;
    }

    public RichPopup getUsuagePopup() {
        return usuagePopup;
    }
}

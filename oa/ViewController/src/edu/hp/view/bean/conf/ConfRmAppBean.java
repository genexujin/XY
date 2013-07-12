package edu.hp.view.bean.conf;

import edu.hp.model.common.Constants;
import edu.hp.view.bean.BaseBean;
import edu.hp.view.security.LoginUser;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;


public class ConfRmAppBean extends BaseBean {
    public ConfRmAppBean() {
    }

    private String queryState;

    public String findByState() {
        //System.err.println(queryState);
        OperationBinding binding = ADFUtils.findOperation("findByState");
        binding.execute();
        return null;
    }

    public String save() throws Exception{

        if (ensureTimeConflicts()) {
            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            ADFUtils.commit("会议室预订已保存！", "会议室预订保存失败，请核对输入的信息或联系管理员！");
        } else {
            JSFUtils.addFacesErrorMessage("该会议室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
        }
        return null;
    }
    
    public String refreshTableIterator(){
        ADFUtils.findIterator("ConfRoomCalendarViewIterator").executeQuery();
        return null;
    }

    public String submit() throws Exception {

        String state = (String)ADFUtils.getBoundAttributeValue("State");
        //if (state != null && state.equals(Constants.STATE_INITIAL)) {
        ADFUtils.setBoundAttributeValue("SubmitDate", new Timestamp(System.currentTimeMillis()));
        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
        
        if (user.getIsUserInRole().get(Constants.ROLE_CONFRM_ADMIN) != null)
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_REVIEWED);
        else
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_PENDING_REVIEW);

        if (ensureTimeConflicts()) {
            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            boolean success = ADFUtils.commit("会议室预订已提交审核！", "会议室预订提交审核失败，请核对输入的信息或联系管理员！");
            
            if (success) {
                String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
                String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
                String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
                String title = (String)ADFUtils.getBoundAttributeValue("Title");

                String noteTitle = "您为会议主题：" + title + " 所做的会议室申请已提交审核。 ";
                String dateStr = getDateString();
                String noteContent = " 提交时间：" + dateStr;
                //send to requester
                if (user.getIsUserInRole().get(Constants.ROLE_CONFRM_ADMIN) == null)
                    sendNotification(noteTitle, noteContent, userId, null,Constants.CONTEXT_TYPE_CONFRM, id);

                String apprvTitle = "有新的会议室申请等待您的审核。";
                String apprvContent = " 会议主题：" + title + " 申请人： " + userDisplayName;
                if (user.getIsUserInRole().get(Constants.ROLE_CONFRM_ADMIN) == null)
                    sendNotification(apprvTitle, apprvContent, null, Constants.ROLE_CONFRM_ADMIN,Constants.CONTEXT_TYPE_CONFRM, id);
                
                //create task
                if (user.getIsUserInRole().get(Constants.ROLE_CONFRM_ADMIN) == null)
                    createTask(id, Constants.CONTEXT_TYPE_CONFRM, apprvTitle, Constants.ROLE_CONFRM_ADMIN,title);

                ADFUtils.findOperation("Commit").execute();
            } else {
                ADFUtils.setBoundAttributeValue("State", state);
            }
        } else {
            JSFUtils.addFacesErrorMessage("该会议室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
            ADFUtils.setBoundAttributeValue("State", state);
        }
        
        //refreshTableIterator();
        //}
        return null;
    }

    public String reject() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && state.equals(Constants.STATE_PENDING_REVIEW)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_REJECTED);
            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            boolean success = ADFUtils.commit("会议室预订已拒绝！", "会议室预订审核拒绝失败，请核对输入的信息或联系管理员！");
            if (success) {
                String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
                //String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
                String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
                String title = (String)ADFUtils.getBoundAttributeValue("Title");

                String noteTitle = "您为会议主题：" + title + " 所做的会议室申请审核未通过。 ";
                String dateStr = getDateString();
                String noteContent = " 审核时间：" + dateStr;
                //send to requester
                sendNotification(noteTitle, noteContent, userId, null,Constants.CONTEXT_TYPE_CONFRM, id);
                completeTask(Constants.CONTEXT_TYPE_CONFRM, id, Constants.ROLE_CONFRM_ADMIN);

                ADFUtils.findOperation("Commit").execute();
            } else {
                ADFUtils.setBoundAttributeValue("State", state);
            }
        }
        return null;
    }

    public String cancel() {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        //if (state != null && !state.equals(Constants.STATE_REVIEWED)) {
        ADFUtils.setBoundAttributeValue("State", Constants.STATE_CANCELED);
        //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
        boolean success = ADFUtils.commit("会议室预订已取消！", "会议室预订取消失败，请核对输入的信息或联系管理员！");
        if (success) {
            String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
            //                String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
            String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
            String title = (String)ADFUtils.getBoundAttributeValue("Title");

            String noteTitle = "您为会议主题：" + title + " 所做的会议室申请已取消。 ";
            String dateStr = getDateString();
            String noteContent = " 取消时间：" + dateStr;
            //send to requester
            sendNotification(noteTitle, noteContent, userId, null,Constants.CONTEXT_TYPE_CONFRM, id);
            this.cancelTask(Constants.CONTEXT_TYPE_CONFRM, id);
            ADFUtils.findOperation("Commit").execute();
        } else {
            ADFUtils.setBoundAttributeValue("State", state);
        }
        // }
        return null;
    }

    public String approve() throws Exception{
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        if (state != null && state.equals(Constants.STATE_PENDING_REVIEW)) {
            ADFUtils.setBoundAttributeValue("State", Constants.STATE_REVIEWED);
            if (ensureTimeConflicts()) {
                //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
                boolean success = ADFUtils.commit("会议室预订已审核！", "会议室预订审核失败，请核对输入的信息或联系管理员！");
                if (success) {
                    String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
                    //                    String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
                    String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
                    String title = (String)ADFUtils.getBoundAttributeValue("Title");

                    String noteTitle = "您为会议主题：" + title + " 所做的会议室申请已通过审核。 ";
                    String dateStr = getDateString();
                    String noteContent = " 审核时间：" + dateStr;
                    //send to requester
                    sendNotification(noteTitle, noteContent, userId, null,Constants.CONTEXT_TYPE_CONFRM, id);
                    completeTask(Constants.CONTEXT_TYPE_CONFRM, id, Constants.ROLE_CONFRM_ADMIN);
                    ADFUtils.findOperation("Commit").execute();
                } else {
                    ADFUtils.setBoundAttributeValue("State", state);
                }
            } else {
                ADFUtils.setBoundAttributeValue("State", state);
                JSFUtils.addFacesErrorMessage("该会议室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
            }
        }
        return null;
    }

    protected Boolean ensureTimeConflicts() throws Exception {
        String startTime = (String)ADFUtils.getBoundAttributeValue("StartTime");        
        String endTime = (String)ADFUtils.getBoundAttributeValue("EndTime");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = format.parse(startTime);
        Date endDate = format.parse(endTime);
        
        java.sql.Timestamp actStartTime = new java.sql.Timestamp(startDate.getTime());
        java.sql.Timestamp actEndTime = new java.sql.Timestamp(endDate.getTime());
        
        Boolean result = false;
        OperationBinding binding = ADFUtils.findOperation("ifConflict");
        binding.getParamsMap().put("actStartTime", actStartTime);
        binding.getParamsMap().put("actEndTime", actEndTime);
        
        binding.execute();
        result = (Boolean)binding.getResult();
        return result;

    }

    public void delete(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            ADFUtils.findOperation("deleteByPK").execute();
            JSFUtils.addFacesInformationMessage("会议室预订已删除！");
        }
    }

    public void setQueryState(String queryState) {
        this.queryState = queryState;
    }

    public String getQueryState() {
        return queryState;
    }

    public void onAMPMChange(ValueChangeEvent valueChangeEvent) throws Exception{
        super.onAMPMChange(valueChangeEvent, "id3", "id4", Constants.TIME_FORMAT_FULL);
    }
}

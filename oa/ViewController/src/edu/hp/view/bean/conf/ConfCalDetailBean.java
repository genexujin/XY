package edu.hp.view.bean.conf;

import edu.hp.model.common.Constants;
import edu.hp.view.bean.BaseBean;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import java.util.Date;

import javax.faces.event.ValueChangeEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;


public class ConfCalDetailBean extends BaseBean {
    public ConfCalDetailBean() {
    }

    private boolean changeMade = false;
    private Timestamp startDayTime = null;
    private String meetingroomId = null;
    private String action = "save";

    public String save() {

        if (ensureTimeConflicts()) {

            startDayTime = (Timestamp)ADFUtils.getBoundAttributeValue("StartTime");
            meetingroomId = (String)ADFUtils.getBoundAttributeValue("MeetingRoomId");
            //ADFUtils.setBoundAttributeValue("State", Constants.STATE_REVIEWED);
            String state = (String)ADFUtils.getBoundAttributeValue("State");
            Timestamp submit = (Timestamp)ADFUtils.getBoundAttributeValue("SubmitDate");
            if (submit == null)
                ADFUtils.setBoundAttributeValue("SubmitDate", new Timestamp(System.currentTimeMillis()));
            boolean success = ADFUtils.commit("会议室预订已保存！", "会议室预订保存失败，请核对输入的信息或联系管理员！");

            if (success) {
                //String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
                //String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
                String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
                String title = (String)ADFUtils.getBoundAttributeValue("Title");
                String noteTitle;
                String dateStr = getDateString();
                String noteContent;
                if(action.equals("save")&&state.equals(Constants.STATE_REVIEWED)){
                    noteTitle = "您为会议主题：" + title + " 所做的会议室申请已修改。 ";
                    noteContent = " 修改时间：" + dateStr;
                }
                else if(action.equals("cancel")) {
                    noteTitle = "您为会议主题：" + title + " 所做的会议室申请已取消。 ";
                    noteContent = " 取消时间：" + dateStr;
                }
                else{
                    noteTitle = "您为会议主题：" + title + " 所做的会议室申请已通过审核。 ";
                    noteContent = " 审核时间：" + dateStr;
                }
                
                
                //send to requester
                sendNotification(noteTitle, noteContent, userId, null);
                ADFUtils.findOperation("Commit").execute();
                changeMade = true;
            }
        } else {
            JSFUtils.addFacesErrorMessage("该会议室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
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

    public String goBackToCalendar() {

        if (meetingroomId != null && startDayTime != null && changeMade) {
            String ids = (String)JSFUtils.resolveExpression("#{pageFlowScope.confRmCalBean.providerIds}");
            if (ids != null && ids.indexOf(meetingroomId) < 0 && !ids.equals("NA")) {
                ids = ids + "," + meetingroomId;
            } else if (ids == null || ids.equals("NA")) {
                ids = meetingroomId;
            }

            Date activeDay = new Date(startDayTime.getTime());
            JSFUtils.setExpressionValue("#{pageFlowScope.confRmCalBean.activeDay}", activeDay);
            OperationBinding refreshOp = ADFUtils.findOperation("refreshCalendar");
            refreshOp.getParamsMap().put("confRmNos", ids);
            refreshOp.execute();

        }
        return "Calendar";
    }

    public void onStateChange(ValueChangeEvent valueChangeEvent) {
        
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        String newState = (String)valueChangeEvent.getNewValue();
        
        if (state != null && newState != null && !state.equals(newState)) {
            if (newState.equals(Constants.STATE_CANCELED)) {
                action = "cancel";
            }else if(newState.equals(Constants.STATE_REVIEWED)){
                action = "approve";
            }
        } else {
            action = "save";
        }
    }
}

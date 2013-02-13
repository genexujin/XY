package edu.hp.view.bean.conf;

import edu.hp.model.common.Constants;
import edu.hp.view.bean.BaseBean;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.DBSequence;


public class ConfCalDetailBean extends BaseBean{
    public ConfCalDetailBean() {
    }

    public String save() {
        if(ensureTimeConflicts()){
        
            //ADFUtils.setBoundAttributeValue("State", Constants.STATE_REVIEWED);
            String state = (String)ADFUtils.getBoundAttributeValue("State");
            boolean success = ADFUtils.commit("会议室预订已保存！", "会议室预订保存失败，请核对输入的信息或联系管理员！");
            
            if (success && state.equals(Constants.STATE_REVIEWED)) {
                String id = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).toString();
                String userDisplayName = (String)ADFUtils.getBoundAttributeValue("UserDisplayName");
                String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
                String title = (String)ADFUtils.getBoundAttributeValue("Title");

                String noteTitle = "您为会议主题：" + title + " 所做的会议室申请已通过审核。 ";
                String dateStr = getDateString();
                String noteContent = " 审核时间：" + dateStr;
                //send to requester
                sendNotification(noteTitle, noteContent, userId, null);
                ADFUtils.findOperation("Commit").execute();
            }
        }else{
            JSFUtils.addFacesErrorMessage("该会议室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
        }
        return null;

    }
    
    protected Boolean ensureTimeConflicts(){
        
        Boolean result = false;        
        OperationBinding binding = ADFUtils.findOperation("ifConflict");        
        binding.execute();        
        result = (Boolean)binding.getResult();        
        return result;

    }
}

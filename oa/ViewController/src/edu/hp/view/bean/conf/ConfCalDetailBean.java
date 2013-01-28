package edu.hp.view.bean.conf;

import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import oracle.binding.OperationBinding;


public class ConfCalDetailBean {
    public ConfCalDetailBean() {
    }

    public String save() {
        if(ensureTimeConflicts()){
            //ADFUtils.setBoundAttributeValue("State", edu.hp.model.common.Constants.STATE_REVIEWED);
            ADFUtils.commit("会议室预订已保存！", "会议室预订保存失败，请核对输入的信息或联系管理员！");
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

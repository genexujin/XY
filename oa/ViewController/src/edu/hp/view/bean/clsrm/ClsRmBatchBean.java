package edu.hp.view.bean.clsrm;

import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;


public class ClsRmBatchBean {
    public ClsRmBatchBean() {
    }

    public void onDelConfirm(DialogEvent dialogEvent) {
        if(dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)){
            OperationBinding binding = ADFUtils.findOperation("deleteCurrentBatchOrder");
            binding.execute();
            if(binding.getErrors().isEmpty()){
                JSFUtils.addFacesInformationMessage("批量预订已成功删除！");
            }else{
                JSFUtils.addFacesInformationMessage("无法进行批量预订操作，请联系管理员！");
            }
        }
    }

    public String saveBatch() {
        
       
        OperationBinding binding = ADFUtils.findOperation("saveBatchOrders");
        binding.execute();
        if(binding.getErrors().isEmpty()){
            if((Boolean)binding.getResult()){
                JSFUtils.addFacesInformationMessage("批量预订已完成，但部分预订和已有预订有冲突，请自行检查并修正！");
            }else{
                JSFUtils.addFacesInformationMessage("批量预订已完成！");
            }
        }else{
            JSFUtils.addFacesErrorMessage("无法完成批量预订，请联系系统管理员！");
        }
     
        return null;
    }
}

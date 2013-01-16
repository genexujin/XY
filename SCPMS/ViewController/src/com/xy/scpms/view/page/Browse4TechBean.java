package com.xy.scpms.view.page;


import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;


public class Browse4TechBean {
    public Browse4TechBean() {
    }

    public void confirm(DialogEvent dialogEvent) {
        if(dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)){
            OperationBinding binding = ADFUtils.findOperation("Commit");
            binding.execute();
            if(binding.getErrors().isEmpty()){
                JSFUtils.addFacesInformationMessage("保存成功！");
            }else{
                JSFUtils.addFacesErrorMessage("保存失败,请联系管理员！");
            }
        }
    }
}

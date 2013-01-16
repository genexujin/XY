package com.xy.scpms.view.page;


import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import oracle.binding.OperationBinding;

public class InvoiceEntryBean {
    public InvoiceEntryBean() {
    }

    public String save() {

        OperationBinding binding = ADFUtils.findOperation("Commit");
        binding.execute();
        if((binding.getErrors().isEmpty())){
            JSFUtils.addFacesInformationMessage("保存成功！");
        }else{
            JSFUtils.addFacesErrorMessage((String)binding.getErrors().get(0));
        }

        return null;
    }
}

package edu.hp.view.bean.conf;

import edu.hp.model.common.Constants;
import edu.hp.view.bean.BaseBean;
import edu.hp.view.bean.clsrm.ClsRmBatchBean;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.DBSequence;

public class ConfBatchBean extends BaseBean{
    private RichPopup updateConfirmPopup;

    public ConfBatchBean() {
    }

    public void onDelConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            OperationBinding binding = ADFUtils.findOperation("deleteCurrentBatchOrder");
            binding.execute();
            if (binding.getErrors().isEmpty()) {
                JSFUtils.addFacesInformationMessage("批量预订已成功删除！");
            } else {
                JSFUtils.addFacesInformationMessage("无法进行批量预订操作，请联系管理员！");
            }
        }
    }

    public String saveBatch() {
        DBSequence id = (DBSequence)ADFUtils.getBoundAttributeValue("Id");
        
        if (id != null && new Integer(id.toString()) > 0){
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            updateConfirmPopup.show(hints);
        }else{
            _saveBatch();
        }
        
        return null;
    }

    private void _saveBatch() {
        OperationBinding binding = ADFUtils.findOperation("saveBatchOrders");
        binding.execute();
        if (binding.getErrors().isEmpty()) {
            if ((Boolean)binding.getResult()) {
                JSFUtils.addFacesInformationMessage("批量预订已完成，但部分预订和已有预订有冲突，请自行检查并修正！");
            } else {
                JSFUtils.addFacesInformationMessage("批量预订已完成！");
            }
        } else {
            JSFUtils.addFacesErrorMessage("无法完成批量预订，请联系系统管理员！");
        }
    }

    public void onBatchUpdateConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            _saveBatch();
        }
    }

    public void setUpdateConfirmPopup(RichPopup updateConfirmPopup) {
        this.updateConfirmPopup = updateConfirmPopup;
    }

    public RichPopup getUpdateConfirmPopup() {
        return updateConfirmPopup;
    }

    public void onAMPMChange(ValueChangeEvent valueChangeEvent) throws Exception{
        super.onAMPMChange(valueChangeEvent, "ActStartTime", "ActEndTime", Constants.TIME_FORMAT_SHORT);
    }
}

package com.xy.scpms.view.page;


import com.xy.scpms.model.utils.Codes;
import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.binding.OperationBinding;


public class ConfirmationBean implements Serializable {
    private RichInputText memoInput;
    private RichPopup popup;
    private RichCommandButton downloadButton;
    private String createType = Codes.COL_VALUE_AIP_TYPE_NEW;

    public ConfirmationBean() {
    }


    public void setMemoInput(RichInputText memoInput) {
        this.memoInput = memoInput;
    }

    public RichInputText getMemoInput() {
        return memoInput;
    }

    public String submitForApproval() {
        
        OperationBinding sumBinding = ADFUtils.findOperation("sumTotal");
        sumBinding.execute();
        
        OperationBinding commitBinding = ADFUtils.findOperation("Commit");
        commitBinding.execute();
        if (!commitBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("保存合同时出错, 请联系管理员 !");
        } else {

            OperationBinding approvalBinding = ADFUtils.findOperation("startApproval");
            approvalBinding.execute();
            if (!commitBinding.getErrors().isEmpty()) {
                JSFUtils.addFacesErrorMessage("提交审批时出错, 请联系管理员 !");
            }

        }

        popup.hide();

        return "return";
    }

    public String cancelConfirmation() {
        popup.hide();
        return null;
    }

    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }

    public void download(FacesContext facesContext, OutputStream outputStream) {
        
        String filePath = (String)downloadButton.getAttributes().get("filePath");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        byte[] data = new byte[1];
        boolean isSuccessful = true;
        try {
            bis = new BufferedInputStream(new FileInputStream(filePath));
            bos = new BufferedOutputStream(outputStream);

            while (bis.read(data) != -1) {
                bos.write(data);
            }
            bos.flush();
        } catch (FileNotFoundException fnfe) {
            isSuccessful = false;
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            isSuccessful = false;
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException ioe) {
                isSuccessful = false;
            }
        }
        
        if (!isSuccessful) {

            JSFUtils.addFacesErrorMessage("下载失败！内部错误请联系系统管理员！");

        }
    }

    public void setDownloadButton(RichCommandButton downloadButton) {
        this.downloadButton = downloadButton;
    }

    public RichCommandButton getDownloadButton() {
        return downloadButton;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

    public String getCreateType() {
        return createType;
    }
}

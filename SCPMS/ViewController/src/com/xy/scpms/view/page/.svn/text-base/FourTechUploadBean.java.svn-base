package com.xy.scpms.view.page;


import com.xy.scpms.view.Constants;
import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.event.ValueChangeEvent;

import org.apache.myfaces.trinidad.model.UploadedFile;


public class FourTechUploadBean {
    
    private UploadedFile file;
    
    public FourTechUploadBean() {
    }

    public void onfileChose(ValueChangeEvent valueChangeEvent) {
        String filename = this.file.getFilename();
        String newFileName = null;
        String fullPath = null;
        boolean isSuccessful = true;

        if (!(filename.endsWith(".pdf") || filename.endsWith(".doc") ||
              filename.endsWith("docx"))) {
            isSuccessful = false;
        } else {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                byte[] data = new byte[1];
                bis = new BufferedInputStream(file.getInputStream());
                String fileName = file.getFilename();
                String fileExt = fileName.substring(fileName.lastIndexOf("."));
                String contractNo =
                    (String)ADFUtils.getBoundAttributeValue("ContractNo");
                String lineMnftNo =
                    (String)ADFUtils.getBoundAttributeValue("ShipMnftNo");

                newFileName = contractNo + "_" + lineMnftNo + fileExt;
                fullPath =
                        Constants.CONTRACT_UPLOAD_FOLDER_ABSOLUTE_PATH + "\\" +
                        contractNo + "\\" + newFileName;
                new File(Constants.CONTRACT_UPLOAD_FOLDER_ABSOLUTE_PATH +
                         "\\" + contractNo).mkdir();
                bos = new BufferedOutputStream(new FileOutputStream(fullPath));

                while (bis.read(data) != -1) {
                    bos.write(data);
                }
                bos.flush();


            } catch (IOException ioe) {
                isSuccessful = false;
                ioe.printStackTrace();
            } finally {
                try {
                    bis.close();
                    bos.close();
                } catch (IOException ioe) {
                    isSuccessful = false;
                }
            }

        }

        if (!isSuccessful) {

            JSFUtils.addFacesErrorMessage("上传失败！内部错误或者上传文件非pdf,doc,docx类型文件");

        } else {

            ADFUtils.setBoundAttributeValue("FilePath", fullPath);

            JSFUtils.addFacesInformationMessage("文件上传成功，文件名： " + newFileName +
                                                " (" + file.getLength() +
                                                " 字节)");
            // Here's where we could call file.getInputStream()
        }
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFile() {
        return file;
    }
}

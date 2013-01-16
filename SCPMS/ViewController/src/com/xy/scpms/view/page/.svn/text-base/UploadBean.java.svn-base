package com.xy.scpms.view.page;


import com.xy.scpms.view.Constants;
import com.xy.scpms.view.exporter.Converter;
import com.xy.view.utils.ADFUtils;
import com.xy.view.utils.JSFUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.PollEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;


public class UploadBean implements Serializable {
    private UploadedFile file;
    private UploadedFile attachment;
    private RichPopup popup;
    private RichCommandButton downloadButton;
    private RichCommandToolbarButton browseBtn;
    private RichCommandToolbarButton uploadBtn;
    private RichCommandToolbarButton attUpldBtn;
    private RichPopup overwirtePopup;
    private RichInputFile uploadInput;
    private RichCommandToolbarButton delBtn;
    private RichImage browseImg;
    private RichPopup browsePopup;
    private RichPopup memoPopup;


    public UploadBean() {
    }

    public void onAttachmentSelected(ValueChangeEvent valueChangeEvent) {
        //
        //            System.out.println();
        //                    this.attUpldBtn.setRendered(true);
        //                    AdfFacesContext.getCurrentInstance().addPartialTarget(attUpldBtn);

    }

    public void fileUploaded(ValueChangeEvent event) {
        //
        //            System.out.println("here");
        //            this.uploadBtn.setRendered(true);
        //            AdfFacesContext.getCurrentInstance().addPartialTarget(uploadBtn);

    }

    /**
     * 下载附件
     * @param facesContext
     * @param outputStream
     */
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
                if (bis != null && bos != null) {
                    bis.close();
                    bos.close();
                }
            } catch (IOException ioe) {
                isSuccessful = false;
            }
        }

        if (!isSuccessful) {

            JSFUtils.addFacesErrorMessage("下载失败！内部错误请联系系统管理员！");

        }
    }


    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFile() {
        return file;
    }
    
    private void delete(File f) throws IOException { 
      if (f.isDirectory()) { 
        for (File c : f.listFiles()) 
          delete(c); 
      } 
      if (!f.delete()) 
        throw new FileNotFoundException("Failed to delete file: " + f); 
    } 

   
    private void removeFolder(String contractNo) throws IOException{
        ADFUtils.setBoundAttributeValue("AgreementImgUrl", null);
        //重命名文件夹
        delete(new File(Constants.CONTRACT_UPLOAD_FOLDER_ABSOLUTE_PATH + "\\" +
                 contractNo));
        //重命名文件夹
        delete(new File(Constants.CONTRACT_CONVERTED_FOLDER_ABSOLUTE_PATH + "\\" +
                 contractNo));
        
    }

    public void confirmOverwrite(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
                String contractNo = (String)ADFUtils.getBoundAttributeValue("ContractNo");
                removeFolder(contractNo);
                JSFUtils.addFacesInformationMessage("文件已删除！");
                ADFUtils.partialRefreshComponenet(uploadInput);
                ADFUtils.partialRefreshComponenet(uploadBtn);
                ADFUtils.partialRefreshComponenet(delBtn);
                ADFUtils.partialRefreshComponenet(browseBtn);
                ADFUtils.partialRefreshComponenet(browsePopup);
            }
        } catch (IOException ioe) {
           JSFUtils.addFacesErrorMessage("删除文件时出现错误，请联系管理员查看系统日志！");
        }
        overwirtePopup.hide();
    }

    public String doUpload() {

        if (file != null) {
            String filename = file.getFilename();
            String newFileName = null;
            String fullPath = null;
            String imagePath = null;
            StringBuffer imageURL = new StringBuffer();

            boolean isSuccessful = true;


            if (!(filename.endsWith(".pdf") || filename.endsWith(".doc") || filename.endsWith("docx"))) {
                isSuccessful = false;
            } else {
                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                try {
                    byte[] data = new byte[1];
                    bis = new BufferedInputStream(file.getInputStream());
                    String fileName = file.getFilename();
                    String fileExt = fileName.substring(fileName.lastIndexOf("."));
                    String contractNo = (String)ADFUtils.getBoundAttributeValue("ContractNo");
                    contractNo = contractNo.trim();

                    newFileName = contractNo + fileExt;
                    //未转化源文件路径
                    fullPath = Constants.CONTRACT_UPLOAD_FOLDER_ABSOLUTE_PATH + "\\" + contractNo + "\\" + newFileName;
                    //格式转换后的图片存放路径
                    imagePath =
                            Constants.CONTRACT_CONVERTED_FOLDER_ABSOLUTE_PATH + "\\" + contractNo + "\\" + contractNo +
                            ".gif";
                    //格式转换后的图片URL
                    String imageURLBase = Constants.CONTRACT_UPLOAD_URL_PATH_ROOT + contractNo + "/";
                    //创建文件夹
                    new File(Constants.CONTRACT_UPLOAD_FOLDER_ABSOLUTE_PATH + "\\" + contractNo).mkdir();
                    //创建文件夹
                    new File(Constants.CONTRACT_CONVERTED_FOLDER_ABSOLUTE_PATH + "\\" + contractNo).mkdir();
                    bos = new BufferedOutputStream(new FileOutputStream(fullPath));

                    while (bis.read(data) != -1) {
                        bos.write(data);

                        int availableBytes = bis.available();

                    }
                    bos.flush();

                    Converter converter = new Converter();
                    converter.convert(fullPath, imagePath, 0);

                    String imageFolderPath = Constants.CONTRACT_CONVERTED_FOLDER_ABSOLUTE_PATH + "\\" + contractNo;
                    File folder = new File(imageFolderPath);
                    String[] list = folder.list();
                    for (String item : list) {
                        imageURL.append(item + ";");

                    }


                } catch (Exception ioe) {
                    isSuccessful = false;
                    ioe.printStackTrace();
                } finally {
                    try {


                        bis.close();
                        bos.close();
                    } catch (Exception ioe) {
                        isSuccessful = false;
                    }
                }

            }

            if (!isSuccessful) {

                JSFUtils.addFacesErrorMessage("上传失败！内部错误或者上传文件非pdf,doc,docx类型文件");

            } else {

                this.browseBtn.setDisabled(false);
                ADFUtils.setBoundAttributeValue("AgreementImgUrl", imageURL.toString());

                JSFUtils.addFacesInformationMessage("文件上传成功，文件名： " + newFileName + " (" + file.getLength() + " 字节)");

                // Here's where we could call file.getInputStream()
            }
            this.file = null;


            AdfFacesContext.getCurrentInstance().addPartialTarget(this.browseBtn);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.delBtn);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.browsePopup);

        } else {
            JSFUtils.addFacesErrorMessage("请先选择上传的文件!");
        }
        return "self";
    }

    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }


    public void setAttachment(UploadedFile attachment) {
        this.attachment = attachment;
    }

    public UploadedFile getAttachment() {
        return attachment;
    }

    public String close() {
        this.popup.hide();
        return null;
    }

    public String submit() {

        if (attachment != null) {

            String filename = attachment.getFilename();

            boolean isSuccessful = true;
            String fullPath = null;
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                byte[] data = new byte[1];
                bis = new BufferedInputStream(attachment.getInputStream());
                String contractNo = (String)ADFUtils.getBoundAttributeValue("ContractNo");


                fullPath =
                        Constants.ATTACHMENT_UPLOAD_FOLDER_ABSOLUTE_PATH + "\\" + contractNo + "\\attachments\\" + filename;

                new File(Constants.ATTACHMENT_UPLOAD_FOLDER_ABSOLUTE_PATH + "\\" + contractNo +
                         "\\attachments").mkdirs();

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


            if (!isSuccessful) {

                JSFUtils.addFacesErrorMessage("上传失败！内部错误或者上传文件非pdf,doc,docx类型文件");

            } else {

                ADFUtils.setBoundAttributeValue("FileLink", fullPath);
                ADFUtils.setBoundAttributeValue("FileName", filename);

                JSFUtils.addFacesInformationMessage("文件上传成功，文件名： " + filename + " (" + attachment.getLength() +
                                                    " 字节)");
                // Here's where we could call file.getInputStream()
            }

            this.attachment = null;
            //            this.attUpldBtn.setRendered(false);
            //            AdfFacesContext.getCurrentInstance().addPartialTarget(this.attUpldBtn);
        } else {
            JSFUtils.addFacesErrorMessage("请先选择上传的文件");
        }

        return null;
    }

    public String[] getImageList() {
        String contractNo = (String)ADFUtils.getBoundAttributeValue("ContractNo");
        String baseURL = "/converted/" + contractNo + "/";
        String imageURLs = (String)ADFUtils.getBoundAttributeValue("AgreementImgUrl");
        if (imageURLs != null && imageURLs.length() > 0) {
            String[] urls = imageURLs.split(";");
            String[] result = new String[urls.length];
            int i = 0;
            for (String item : urls) {
                result[i] = baseURL + item;
                i++;
            }

            return result;
        } else {
            return null;
        }
    }

    public String addNewAttachment() {
        ADFUtils.findOperation("CreateInsertAttachment").execute();
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.popup.show(hints);
        return null;
    }


    public void setDownloadButton(RichCommandButton downloadButton) {
        this.downloadButton = downloadButton;
    }

    public RichCommandButton getDownloadButton() {
        return downloadButton;
    }

    public static void main(String[] args) {


        String abcde = "a.gif;b.gif;c.gif";
        String[] split = abcde.split(";");
        for (String item : split) {
            System.out.println(item);
        }


    }


    public void polling(PollEvent pollEvent) {
        System.out.println("doing something...");
    }


    public void setBrowseBtn(RichCommandToolbarButton browseBtn) {
        this.browseBtn = browseBtn;
    }

    public RichCommandToolbarButton getBrowseBtn() {
        return browseBtn;
    }

    public void setUploadBtn(RichCommandToolbarButton uploadBtn) {
        this.uploadBtn = uploadBtn;
    }

    public RichCommandToolbarButton getUploadBtn() {
        return uploadBtn;
    }


    public void setAttUpldBtn(RichCommandToolbarButton attUpldBtn) {
        this.attUpldBtn = attUpldBtn;
    }

    public RichCommandToolbarButton getAttUpldBtn() {
        return attUpldBtn;
    }


    public void setOverwirtePopup(RichPopup overwirtePopup) {
        this.overwirtePopup = overwirtePopup;
    }

    public RichPopup getOverwirtePopup() {
        return overwirtePopup;
    }

    public void setUploadInput(RichInputFile uploadInput) {
        this.uploadInput = uploadInput;
    }

    public RichInputFile getUploadInput() {
        return uploadInput;
    }

    public void setDelBtn(RichCommandToolbarButton delBtn) {
        this.delBtn = delBtn;
    }

    public RichCommandToolbarButton getDelBtn() {
        return delBtn;
    }

    public void setBrowseImg(RichImage browseImg) {
        this.browseImg = browseImg;
    }

    public RichImage getBrowseImg() {
        return browseImg;
    }

    public void setBrowsePopup(RichPopup browsePopup) {
        this.browsePopup = browsePopup;
    }

    public RichPopup getBrowsePopup() {
        return browsePopup;
    }

    public void setMemoPopup(RichPopup memoPopup) {
        this.memoPopup = memoPopup;
    }

    public RichPopup getMemoPopup() {
        return memoPopup;
    }
}

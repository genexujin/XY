package edu.hp.view.bean.doc;

import edu.hp.model.common.Constants;
import edu.hp.view.bean.BaseBean;
import edu.hp.view.file.FileManager;
import edu.hp.view.security.LoginUser;
import edu.hp.view.utils.ADFUtils;

import edu.hp.view.utils.JSFUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputFile;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import oracle.jbo.domain.Date;

import oracle.jbo.domain.Timestamp;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class DocDetailBean extends BaseBean{

    private static final String DEPT_TASK_ITERATOR = "DeptTasksIterator";
    private static final String HISTORY_ITERATOR = "HistoryIterator";
    private String smsContent;
    private String lockerId;
    private UploadedFile uploadFile = null;
    private String downloadingFile;
    private String downloadingHistFile;
    private String sampleFileName;
    private String lockBtnText;
    private RichInputFile inputFile;
    private RichPopup uploadPopup;
    private String uploadingDeptTaskId;
    private String uploadingDeptName;
    private RichPopup historyPopup;
    private RichPopup smsPopup;


    public boolean isComplete() {
        String state = (String)ADFUtils.getBoundAttributeValue("TaskState");
        //        System.err.println(ADFUtils.getBoundAttributeValue("IsPublic"));
        return state == "已完成" || state == "已取消";
    }

    public boolean isAdmin() {
        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
        if (user.getIsUserInRole().get(Constants.ROLE_DOC_REVIEW) != null) {
            return true;
        }
        return false;
    }

    public String doCancel() {
        ADFUtils.setBoundAttributeValue("TaskState", "已取消");
        ADFUtils.commit("项目已取消！", "项目取消过程中出错，请联系管理员！");
        return null;
    }

    public String doComplete() {
        ADFUtils.setBoundAttributeValue("TaskState", "已完成");
        ADFUtils.commit("项目已完成！", "项目完成过程中出错，请联系管理员！");
        return null;
    }

    public void fileDownload(FacesContext facesContext, OutputStream outputStream) {

        String filePath = (String)ADFUtils.getBoundAttributeValue("SampleFileUrl");

        if (filePath != null) {
            FileManager mgr = new FileManager();
            boolean result = mgr.downloadFile(filePath, outputStream);
            if (!result) {
                JSFUtils.addFacesErrorMessage("下载模板发生错误！");
            }
        }
    }

    public String getSampleFileName() {
        String filePath = (String)ADFUtils.getBoundAttributeValue("SampleFileUrl");
        if (filePath != null) {
            int lastSlash = filePath.lastIndexOf("/");
            String fileName = filePath.substring(lastSlash + 1);
            return fileName;
        }
        return null;
    }

    /**
     * 锁定当前部门文档
     * @param actionEvent
     */
    public void doLock(ActionEvent actionEvent) {
        String deptTaskId = actionEvent.getComponent().getAttributes().get("deptTaskId").toString();
        System.err.println(deptTaskId);
        if (deptTaskId != null) {
            Row row = ADFUtils.findByKey(deptTaskId, "DeptTasksIterator");
            LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
            row.setAttribute("CurrentEditorId", user.getUserId());
            row.setAttribute("CurrentEditorName", user.getDisplayName());
            ADFUtils.commit();
        }
    }


    /**
     * 释放当前部门文档
     * @param actionEvent
     */
    public void doUnlock(ActionEvent actionEvent) {
        String deptTaskId = actionEvent.getComponent().getAttributes().get("deptTaskId").toString();
        if (deptTaskId != null) {
            Row row = ADFUtils.findByKey(deptTaskId, "DeptTasksIterator");
            row.setAttribute("CurrentEditorId", null);
            row.setAttribute("CurrentEditorName", null);
            ADFUtils.commit();
        }
    }

    public void openHistoryPopup(ActionEvent actionEvent) {
        String deptTaskId = actionEvent.getComponent().getAttributes().get("deptTaskId").toString();
        if (deptTaskId != null) {
            ADFUtils.makeCurrent(deptTaskId, DEPT_TASK_ITERATOR);
            ADFUtils.findIterator(HISTORY_ITERATOR).executeQuery();
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.historyPopup.show(hints);
        }
    }

    public void openUploadPopup(ActionEvent actionEvent) {
        String deptTaskId = actionEvent.getComponent().getAttributes().get("deptTaskId").toString();
        String deptName = actionEvent.getComponent().getAttributes().get("deptName").toString();
        if (deptTaskId != null && deptName != null) {
            //记录当前上传的文件是属于哪个部门的
            this.uploadingDeptTaskId = deptTaskId;
            this.uploadingDeptName = deptName;
            //打开上传窗口
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.uploadPopup.show(hints);
        }
    }

    public boolean isExpired() {
        Date date = ADFUtils.now();
        Timestamp expDt = (Timestamp)ADFUtils.getBoundAttributeValue("ExpireDate");
        if (date.getValue().getTime() > expDt.getTime()) {
            return true;
        }
        return false;
    }

    public void fileUploaded(ValueChangeEvent valueChangeEvent) {

        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
        Date date = ADFUtils.now();
        Timestamp expDt = (Timestamp)ADFUtils.getBoundAttributeValue("ExpireDate");
        if (date.getValue().getTime() > expDt.getTime()) {
            JSFUtils.addFacesErrorMessage("该项目已经过期，无法继续上传公文！");
            return;
        }
        //        System.err.println("upload here");
        if (file != null) {

            System.err.println("get the file!");
            String fileName = file.getFilename();
            if (!(fileName.endsWith(".pdf") || fileName.endsWith(".doc") || fileName.endsWith("docx"))) {
                JSFUtils.addFacesErrorMessage("请上传pdf,doc或者docx类型文件， 且文件大小不要大于50M ！");
            } else {

                System.err.println(fileName);
                String projectName = (String)ADFUtils.getBoundAttributeValue("TaskName");
                System.err.println(projectName);

                FileManager fileMgr = new FileManager();
                InputStream is = null;
                try {
                    is = file.getInputStream();
                    System.err.println("get is");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                String savedFilePath = null;
                System.err.println("ready to save");
                if (fileName != null && projectName != null && is != null) {
                    //将当前部门任务标记为迭代器中的当前行
                    ADFUtils.makeCurrent(this.uploadingDeptTaskId, DEPT_TASK_ITERATOR);
                    //得到相关联的文档历史
                    DCIteratorBinding histories = ADFUtils.findIterator(HISTORY_ITERATOR);
                    //执行查询
                    histories.executeQuery();

                    RowSetIterator hisIter = histories.getRowSetIterator();
                    //得到记录数
                    int seq = hisIter.getFetchedRowCount() + 1;
                    System.err.println("历史记录数: " + seq);
                    //保存文件
                    savedFilePath =
                            fileMgr.saveDeptDocument(fileName, projectName, this.uploadingDeptName, Integer.toString(seq),
                                                     is);

                    if (savedFilePath != null) {
                        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");

                        //更新部门任务的当前公文文件路径
                        Row theRow = ADFUtils.findByKey(uploadingDeptTaskId, DEPT_TASK_ITERATOR);
                        theRow.setAttribute("DocumentFileUrl", savedFilePath);
                        theRow.setAttribute("LastEditorName", user.getDisplayName());
                        theRow.setAttribute("FileName", fileName);
                        //记录公文上传历史

                        Row hisRow = hisIter.createRow();
                        hisRow.setAttribute("EditorName", user.getDisplayName());
                        hisRow.setAttribute("EditorId", user.getUserId());
                        hisRow.setAttribute("UploadTime", ADFUtils.now());
                        hisRow.setAttribute("FileUrl", savedFilePath);
                        hisRow.setAttribute("FileName", fileName);
                        hisIter.insertRow(hisRow);

                        ADFUtils.commit("公文上传成功 ！", "上传文件发生异常，请联系管理员！");
                    } else {
                        JSFUtils.addFacesErrorMessage("上传文件发生异常，请联系管理员！");
                    }
                }
            }
        }
    }


    public void doDownload(ActionEvent actionEvent) {
        downloadingFile = actionEvent.getComponent().getAttributes().get("filePath").toString();
        System.err.println(downloadingFile);
    }

    public void downloadDeptFile(FacesContext facesContext, OutputStream outputStream) {
        System.err.println("start to download");
        FileManager mgr = new FileManager();
        boolean result = mgr.downloadFile(downloadingFile, outputStream);
        if (!result) {
            JSFUtils.addFacesErrorMessage("下载公文发生错误！");
        }
    }

    public void downloadHisFile(FacesContext facesContext, OutputStream outputStream) {
        FileManager mgr = new FileManager();
        boolean result = mgr.downloadFile(downloadingHistFile, outputStream);
        if (!result) {
            JSFUtils.addFacesErrorMessage("下载公文发生错误！");
        }
    }

    public void doHisDownload(ActionEvent actionEvent) {
        downloadingHistFile = actionEvent.getComponent().getAttributes().get("filePath").toString();
    }

    public void setLockBtnText(String lockBtnText) {
        this.lockBtnText = lockBtnText;
    }

    public String getLockBtnText() {
        String currentEditor = (String)ADFUtils.getBoundAttributeValue("CurrentEditorId");
        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
        String userId = user.getUserId();
        if (currentEditor == null) {
            lockBtnText = "锁定";
        } else if (currentEditor.equals(userId)) {
            lockBtnText = "释放";
        }
        return lockBtnText;
    }


    public String save() {
        // Add event code here...
        return null;
    }

    public void openSMSPopup(ActionEvent actionEvent) {
        String taskName = actionEvent.getComponent().getAttributes().get("taskName").toString();
        String deptName = actionEvent.getComponent().getAttributes().get("deptName").toString();
        lockerId = actionEvent.getComponent().getAttributes().get("lockerId").toString();
        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
        String userName = user.getDisplayName();
        this.smsContent = "用户" + userName + "发出请求，请您释放" + deptName + "的公文项目：" + taskName + "的编辑锁，谢谢！";
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.smsPopup.show(hints);
    }


    public void notifyToRelease(DialogEvent dialogEvent) {
        if(dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)){
            System.err.println(lockerId);
            super.sendNotification("公文编辑锁释放提醒！", this.smsContent, lockerId, null, Constants.CONTEXT_TYPE_DOCTASK,
                                   null);
            ADFUtils.commit();
        }
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsPopup(RichPopup smsPopup) {
        this.smsPopup = smsPopup;
    }

    public RichPopup getSmsPopup() {
        return smsPopup;
    }

    public void setUploadFile(UploadedFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public UploadedFile getUploadFile() {
        return uploadFile;
    }


    public void setInputFile(RichInputFile inputFile) {
        this.inputFile = inputFile;
    }

    public RichInputFile getInputFile() {
        return inputFile;
    }

    public void setUploadPopup(RichPopup uploadPopup) {
        this.uploadPopup = uploadPopup;
    }

    public RichPopup getUploadPopup() {
        return uploadPopup;
    }


    public void setHistoryPopup(RichPopup historyPopup) {
        this.historyPopup = historyPopup;
    }

    public RichPopup getHistoryPopup() {
        return historyPopup;
    }


    public void setSampleFileName(String sampleFileName) {
        this.sampleFileName = sampleFileName;
    }


}

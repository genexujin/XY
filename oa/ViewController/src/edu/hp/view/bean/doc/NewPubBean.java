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

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichSelectManyShuttle;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Timestamp;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class NewPubBean extends BaseBean {

    List<String> selectedGroups;
    List<SelectItem> allGroups;
    private RichSelectManyShuttle theShuttle;
    private UploadedFile uploadFile = null;
    private RichInputFile inputFile;


    public NewPubBean() {
    }


    public String startPub() {
        saveGrpList();

        ArrayList<String> userIds = new ArrayList<String>();

        DCIteratorBinding it = ADFUtils.findIterator("EmpOfGrpsIterator");
        for (String grp : selectedGroups) {
            OperationBinding searchDeptOp = ADFUtils.findOperation("findByName");
            searchDeptOp.getParamsMap().put("grpName", grp);
            searchDeptOp.execute();
            it.setRangeSize(-1);
            it.executeQuery();
            Row[] allRowsInRange = it.getAllRowsInRange();
            for (Row row : allRowsInRange) {
                if (userIds.contains(row.getAttribute("Id")))
                    userIds.add((String)row.getAttribute("Id"));
            }
        }
        //更新DocTask上的部分字段
        ADFUtils.setBoundAttributeValue("State", "已发送");
        ADFUtils.setBoundAttributeValue("SubmitDate", new Date(new java.sql.Date(System.currentTimeMillis())));
        ADFUtils.setBoundAttributeValue("ReceiverList", userIds.toString());
        LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");
        ADFUtils.setBoundAttributeValue("SubmitBy", user.getDisplayName());
        ADFUtils.commit("信息已下达！", "发送过程中出错，请联系管理员！");
        System.err.println(user.getUserId());
        if (!userIds.contains(user.getUserId()))
            userIds.add(user.getUserId());
        //通知所有相关用户
        notifyUsers(userIds);

        return null;
    }

    private void notifyUsers(ArrayList<String> userIds) {

        String taskName = (String)ADFUtils.getBoundAttributeValue("Title");

        String content = " 已下达" + taskName + " 请进入系统查看详情！";

        String docTaskId = (ADFUtils.getBoundAttributeValue("Id")).toString();
        for (String userId : userIds) {
            super.sendNotification("有新的信息下达！", content, userId, null, Constants.CONTEXT_TYPE_PUB, docTaskId);
        }
        ADFUtils.commit();
    }


    public void fileUploaded(ValueChangeEvent valueChangeEvent) {
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
        //        System.err.println("upload here");
        if (file != null) {

            System.err.println("get the file!");
            String fileName = file.getFilename();
            if (!(fileName.endsWith(".pdf") || fileName.endsWith(".doc") || fileName.endsWith("docx"))) {
                JSFUtils.addFacesErrorMessage("请上传pdf,doc或者docx类型文件， 且文件大小不要大于50M ！");
            } else {

                System.err.println(fileName);
                String projectName = (String)ADFUtils.getBoundAttributeValue("Title");
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
                if (fileName != null && projectName != null && is != null)
                    savedFilePath = fileMgr.savePublicDocument(fileName, projectName, is);
                if (savedFilePath != null) {
                    ADFUtils.setBoundAttributeValue("FileUrl", savedFilePath);
                    saveGrpList();
                    ADFUtils.commit("公文上传成功 ！", "上传文件发生异常，请联系管理员！");
                } else {
                    JSFUtils.addFacesErrorMessage("上传文件发生异常，请联系管理员！");
                }
            }
        }

    }

    public void setSelectedGroups(List<String> selectedDepts) {
        this.selectedGroups = selectedDepts;
    }

    public List<String> getSelectedGroups() {
        if (selectedGroups == null) {
            selectedGroups = new ArrayList();
            //            System.err.println("here!");
            String grpList = (String)ADFUtils.getBoundAttributeValue("GroupList");
            if (grpList != null) {
                String[] grps = grpList.split(",");
                for (String grp : grps) {
                    selectedGroups.add(grp);
                }
            }
        }
        return selectedGroups;
    }

    public String getFileName() {
        String filePath = (String)ADFUtils.getBoundAttributeValue("FileUrl");
        if (filePath != null) {
            int lastSlash = filePath.lastIndexOf("/");
            String fileName = filePath.substring(lastSlash + 1);
            return fileName;
        }
        return null;
    }

    private void saveGrpList() {
        String grpList = "";
        //        System.err.println(selectedDepts.size());
        for (String grp : selectedGroups) {
            grpList = grpList + grp + ",";
        }
        grpList = grpList.substring(0, grpList.length() - 1);
        //        System.err.println(deptList);
        ADFUtils.setBoundAttributeValue("GroupList", grpList);
    }

    public void fileDownload(FacesContext facesContext, OutputStream outputStream) {

        String filePath = (String)ADFUtils.getBoundAttributeValue("FileUrl");

        if (filePath != null) {
            FileManager mgr = new FileManager();
            boolean result = mgr.downloadFile(filePath, outputStream);
            if (!result) {
                JSFUtils.addFacesErrorMessage("下载发生错误！");
            }
        }

    }

    public void setAllGroups(List<SelectItem> allDepts) {
        this.allGroups = allDepts;
    }

    public List<SelectItem> getAllGroups() {
        if (allGroups == null)
            _getAllGroups();
        return allGroups;
    }

    protected void _getAllGroups() {
        allGroups = selectItemsForIterator("GroupsIterator", "GroupName");
    }

    public static List<SelectItem> selectItemsForIterator(String iteratorName, String valueAttrName) {
        //        System.err.println("here");
        BindingContext bc = BindingContext.getCurrent();
        DCBindingContainer binding = (DCBindingContainer)bc.getCurrentBindingsEntry();
        DCIteratorBinding iter = binding.findIteratorBinding(iteratorName);
        iter.setRangeSize(-1);
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            //            System.err.println(r.getAttribute(valueAttrName));
            selectItems.add(new SelectItem(r.getAttribute(valueAttrName), (String)r.getAttribute(valueAttrName)));
        }

        //        System.err.println(selectItems.size());
        return selectItems;
    }

    public void setTheShuttle(RichSelectManyShuttle theShuttle) {
        this.theShuttle = theShuttle;
    }

    public RichSelectManyShuttle getTheShuttle() {
        return theShuttle;
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
}

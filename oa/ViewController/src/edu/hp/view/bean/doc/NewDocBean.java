package edu.hp.view.bean.doc;

import edu.hp.view.file.FileManager;
import edu.hp.view.utils.ADFUtils;

import edu.hp.view.utils.JSFUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichSelectManyShuttle;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class NewDocBean {

    List<String> selectedDepts;
    List<SelectItem> allDepts;
    private RichSelectManyShuttle theShuttle;
    private UploadedFile uploadFile = null;
    private RichInputFile inputFile;

    public NewDocBean() {
    }

    public List getSelectedDepts() {
        if (selectedDepts == null) {
            selectedDepts = new ArrayList();
            //            System.err.println("here!");
            String deptList = (String)ADFUtils.getBoundAttributeValue("DeptList");
            if (deptList != null) {
                String[] depts = deptList.split(",");
                for (String dept : depts) {
                    selectedDepts.add(dept);
                }
            }
        }
        return selectedDepts;
    }

    protected void _getAllDepts() {
        allDepts = selectItemsForIterator("DepartmentsIterator", "DeptName");
    }

    public List<SelectItem> getAllDepts() {
        if (allDepts == null)
            _getAllDepts();
        return allDepts;
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


    public String saveDoc() {
        System.err.println("start to save");
        saveDeptList();
        ADFUtils.commit("公文项目已保存！", "公文项目保存过程中出错，请联系管理员！");
        return null;
    }

    public String startDoc() {
        // Add event code here...
        return null;
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
                if (fileName != null && projectName != null && is != null)
                    savedFilePath = fileMgr.saveSampleDocument(fileName, projectName, is);
                if (savedFilePath != null) {
                    ADFUtils.setBoundAttributeValue("SampleFileUrl", savedFilePath);
                    saveDeptList();
                    ADFUtils.commit("公文上传成功 ！", "上传文件发生异常，请联系管理员！");
                } else {
                    JSFUtils.addFacesErrorMessage("上传文件发生异常，请联系管理员！");
                }
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

    private void saveDeptList() {
        String deptList = "";
        //        System.err.println(selectedDepts.size());
        for (String dept : selectedDepts) {
            deptList = deptList + dept + ",";
        }
        deptList = deptList.substring(0, deptList.length() - 1);
        //        System.err.println(deptList);
        ADFUtils.setBoundAttributeValue("DeptList", deptList);
    }


    public void setAllDepts(List allDepts) {
        this.allDepts = allDepts;
    }


    public void setTheShuttle(RichSelectManyShuttle theShuttle) {
        this.theShuttle = theShuttle;
    }

    public RichSelectManyShuttle getTheShuttle() {
        return theShuttle;
    }

    public void setSelectedDepts(List selectedDepts) {
        this.selectedDepts = selectedDepts;
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

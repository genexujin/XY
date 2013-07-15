package edu.hp.view.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;

import java.io.OutputStream;

import java.util.Calendar;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class FileManager {

    private static String rootFolder;

    public FileManager() {
        super();
    }

    static {
        ResourceBundle bundle = PropertyResourceBundle.getBundle("file-store-config");
        rootFolder = bundle.getString("rootFolder");
    }
    
    public boolean downloadFile(String filePath, OutputStream outputStream){
        File f = new File(filePath);
        FileInputStream fis;        
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;        
        byte[] data = new byte[1024];
        
        try {
            bis = new BufferedInputStream(new FileInputStream(f));
            bos = new BufferedOutputStream(outputStream);

            while (bis.read(data) != -1) {                
                bos.write(data);
            }
            bos.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bos.close();
                bis.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();                
            }
        }
        
        return false;
    }

    public String saveSampleDocument(String fileName, String projectName, InputStream is) {
        
        //make dir
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        int year = cal.get(Calendar.YEAR);
        String dir = rootFolder + year + "/projects/" + projectName.trim() + "/sample";
        System.err.println(dir);
        String filePath = dir + "/" + fileName;        
        System.err.println(filePath);
        new File(dir).mkdirs();
        System.err.println("made dir");
        //save file
        boolean success = _saveFile(is, filePath);
        System.err.println("file saved");
        if(!success) return null;
        
        //return full file path
        return filePath;
    }
    
    public String saveDeptDocument(String fileName, String projectName, String deptName, String seq, InputStream is) {
        
        //make dir
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        int year = cal.get(Calendar.YEAR);
        String dir = rootFolder + year + "/projects/" + projectName.trim() + "/" + deptName + "/" + seq;
        System.err.println(dir);
        String filePath = dir + "/" + fileName;        
        System.err.println(filePath);
        new File(dir).mkdirs();
        System.err.println("made dir");
        //save file
        boolean success = _saveFile(is, filePath);
        System.err.println("file saved");
        if(!success) return null;
        
        //return full file path
        return filePath;
    }
    
    public String savePublicDocument(String fileName, String title, InputStream is) {
        
        //make dir
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        int year = cal.get(Calendar.YEAR);
        String dir = rootFolder + year + "/public/" + title.trim() ;
        System.err.println(dir);
        String filePath = dir + "/" + fileName;        
        System.err.println(filePath);
        new File(dir).mkdirs();
        System.err.println("made dir");
        //save file
        boolean success = _saveFile(is, filePath);
        System.err.println("file saved");
        if(!success) return null;        
        //return full file path
        return filePath;
    }

    private boolean _saveFile(InputStream is, String filePath) {
        
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        byte[] data = new byte[1024];
        try {
            bos = new BufferedOutputStream(new FileOutputStream(filePath));
            bis = new BufferedInputStream(is);
            while (bis.read(data) != -1) {
                bos.write(data);
            }
            bos.flush();
            return true;
        } catch (Exception fnfe) {
            fnfe.printStackTrace();           
        } finally {
            try {
                bos.close();
                bis.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        
        return false;
    }
}

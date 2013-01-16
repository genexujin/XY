package com.xy.scpms.view;

public class Constants {

//    public static final String CONTRACT_UPLOAD_FOLDER_ABSOLUTE_PATH =
//        "c:\\xy\\upload\\raw";
//    public static final String CONTRACT_CONVERTED_FOLDER_ABSOLUTE_PATH =
//        "c:\\xy\\upload\\converted";
//    public static final String ATTACHMENT_UPLOAD_FOLDER_ABSOLUTE_PATH =
//        "c:\\xy\\upload\\";
        public static final String CONTRACT_UPLOAD_FOLDER_ABSOLUTE_PATH =
            "E:\\OM\\xy\\upload\\raw";
        public static final String CONTRACT_CONVERTED_FOLDER_ABSOLUTE_PATH =
            "E:\\OM\\xy\\upload\\converted";
        public static final String ATTACHMENT_UPLOAD_FOLDER_ABSOLUTE_PATH =
            "E:\\OM\\xy\\upload\\";

    public static final String CONTRACT_UPLOAD_URL_PATH_ROOT = "/converted/";

    public static final String ATTACHMENT_UPLOAD_URL_BASE =
        "/upload/attachment/";
    public static final String CONTRACT_UPLOAD_URL_BASE = "/upload/contract/";

    public static final String FLOW_CREATION_TYPE_DUPLICATED = "duplicated";
    public static final String FLOW_CREATION_TYPE_AGREEMENT = "payment";
    public static final String FLOW_CREATION_TYPE_NORMAL = "normal";
    public static final String FLOW_CREATION_PARAM_CREATIONTYPE =
        "creationType";

    public String createAuthorizerType() {
        // Add event code here...
        return null;
    }
}

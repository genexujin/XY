package edu.hp.model.common;

public class Constants {

    //public static final String CLSRM_BATCH_PREFIX = "CLSRM-BATCH-";
    public static final String TIME_FORMAT_FULL = "yyyy-MM-dd HH:mm";
    public static final String TIME_FORMAT_SHORT = "HH:mm";
    public static final String CLSRM_BATCH_PREFIX = "B-";
    public static final String STATE_INITIAL = "未提交";
    public static final String STATE_PENDING_REVIEW = "待审核";
    public static final String STATE_REVIEWED = "已审核";
    public static final String STATE_REJECTED = "已拒绝";
    public static final String STATE_CANCELED = "已取消";
    public static final String STATE_PENDING_APPROVAL = "待审批";
    public static final String STATE_TRIP_PLANNED = "已调度";
    public static final String STATE_TRIP_CONFIRMED = "已确认";
    public static final String STATE_ACCEPTED = "已受理";
    public static final String STATE_ASSIGNED = "已分派";
    public static final String STATE_AFF_REVIEW = "总务复核";
    public static final String STATE_PROCESSED = "已处理";
    public static final String STATE_EVALUATED = "已评价";
    public static final String STATE_NOTE_UNREAD = "UNREAD";
    public static final String STATE_NOTE_READ = "READ";
    public static final String STATE_TASK_PENDING = "PENDING";
    public static final String STATE_TASK_COMPLETED = "COMPLETED";
    public static final String STATE_TASK_CANCELED = "CANCELED";
    public static final String CONTEXT_TYPE_VEHICLE = "VEHICLE";
    public static final String CONTEXT_TYPE_CONFRM = "CONFRM";
    public static final String CONTEXT_TYPE_HELPDESK = "HELPDESK";
    public static final String CONTEXT_TYPE_PO = "PO";
    
    public static final String PO_STATE_INITIAL = "1";
    public static final String PO_STATE_DEPT_REVIEW = "8";
    public static final String PO_STATE_PENDING_REVIEW = "2";
    public static final String PO_STATE_PENDING_APPROVAL = "3";
    public static final String PO_STATE_REJECTED = "4";
    public static final String PO_STATE_EXECUTING = "5";
    public static final String PO_STATE_FINISHED = "6";
    public static final String PO_STATE_CANCELLED = "7";
    public static final String PO_LINE_STATE_INITIAL = "1";
    public static final String PO_LINE_STATE_PENDING_REVIEW = "2";
    public static final String PO_LINE_STATE_PENDING_APPROVAL = "3";
    public static final String PO_LINE_STATE_EXECUTING = "4";
    public static final String PO_LINE_STATE_FINISHED = "5";
    public static final String PO_LINE_STATE_CANCELLED = "6";
    
    public static final String ROLE_OFFICE_MGR="车辆审核";
    public static final String ROLE_ZONGWU_MGR="车辆调度";
    public static final String ROLE_DRIVER="司机";
    public static final String ROLE_VP="副院长";
    public static final String ROLE_CEO="院长";
    public static final String ROLE_BUYER="采购员";
    public static final String ROLE_USER="普通用户";
    public static final String ROLE_CONFRM_ADMIN="会议室审核";
    public static final String ROLE_CLSRM_ADMIN="教室管理员";
    public static final String ROLE_PO_VERIFIER="采购审核";
    public static final String ROLE_PO_APPROVER="采购审批";
    public static final String ROLE_PO_2ND_APPROVER="采购终审";
    public static final String ROLE_PO_BUYER="采购执行";
    public static final String ROLE_PO_RECEIVER="采购收货";
    public static final String ROLE_PO_DEPT_VERIFIER="采购部门审核";
    public static final String ROLE_HD_REVIEW="报修总务复核";
    public static final String ROLE_DOC_REVIEW="公文管理员";
           
    public static final String HD_REASON_IT = "信息";
    public static final String HD_REASON_AFF = "总务";
    public static final String HD_RESULT_FIXED = "修复";
    public static final String HD_RESULT_REVIEW = "转总务";
    
    public static final String CONTEXT_OBJ_VEH= "VEH";
    public static final String CONTEXT_OBJ_VEH_ID = "id";
    

    public static final String CONTEXT_VEHICLE_TASKFLOW =
        "/WEB-INF/flows/vehicle/vehicle-dashboard-btf.xml#vehicle-dashboard-btf";
    public static final String CONTEXT_CONFRM_TASKFLOW =
        "/WEB-INF/flows/confRm/confRm-dashboard-btf.xml#confRm-dashboard-btf";
    public static final String CONTEXT_HELPDESK_TASKFLOW =
        "/WEB-INF/flows/helpdesk/MyHdCallDashboard.xml#MyHdCallDashboard";
    public static final String CONTEXT_PO_TASKFLOW =
        "/WEB-INF/flows/po/MyPoDashboard.xml#MyPoDashboard";

}

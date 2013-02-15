package edu.hp.model.common;

public class Constants {

    public static final String CLSRM_BATCH_PREFIX = "CLSRM-BATCH-";
    public static final String STATE_INITIAL = "未提交";
    public static final String STATE_PENDING_REVIEW = "待审核";
    public static final String STATE_REVIEWED = "已审核";
    public static final String STATE_REJECTED = "已拒绝";
    public static final String STATE_CANCELED = "已取消";
    public static final String STATE_PENDING_APPROVAL = "待审批";
    public static final String STATE_TRIP_PLANNED = "已调度";
    public static final String STATE_ACCEPTED = "已受理";
    public static final String STATE_PROCESSED = "已处理";
    public static final String STATE_EVALUATED = "已评价";
    //状态“取消”改为“撤消”可能更好，因为页面上经常需要个按钮来实现取消之前的编辑的功能
    public static final String STATE_CANCELED_2 = "已撤消";
    public static final String STATE_NOTE_UNREAD = "UNREAD";
    public static final String STATE_NOTE_READ = "READ";
    public static final String STATE_TASK_PENDING = "PENDING";
    public static final String STATE_TASK_COMPLETED = "COMPLETED";
    public static final String CONTEXT_TYPE_VEHICLE = "VEHICLE";
    public static final String CONTEXT_TYPE_CONFRM = "CONFRM";
    
    public static final String ROLE_OFFICE_MGR="办公室主任";
    public static final String ROLE_ZONGWU_MGR="总务处主任";
    public static final String ROLE_VP="副院长";
    public static final String ROLE_CEO="院长";
    public static final String ROLE_BUYER="采购员";
    public static final String ROLE_USER="普通用户";
    public static final String ROLE_CONFRM_ADMIN="会议室管理员";
    public static final String ROLE_CLSRM_ADMIN="教室管理员";
    public static final String ROLE_HD_ADMIN="报修处理员";
    
    public static final String CONTEXT_OBJ_VEH= "VEH";
    public static final String CONTEXT_OBJ_VEH_ID = "id";
    

    public static final String CONTEXT_VEHICLE_TASKFLOW =
        "/WEB-INF/flows/vehicle/vehicle-dashboard-btf.xml#vehicle-dashboard-btf";
    public static final String CONTEXT_CONFRM_TASKFLOW =
        "/WEB-INF/flows/confRm/confRm-dashboard-btf.xml#confRm-dashboard-btf";

}

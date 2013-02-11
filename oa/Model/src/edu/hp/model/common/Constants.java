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
    public static final String STATE_NOTE_UNREAD = "UNREAD";
    public static final String STATE_NOTE_READ = "READ";
    public static final String STATE_TASK_PENDING = "PENDING";
    public static final String STATE_TASK_COMPLETED = "COMPLETED";
    public static final String CONTEXT_TYPE_VEHICLE = "VEHICLE";
    public static final String CONTEXT_TYPE_CONFRM = "CONFRM";

    public static final String CONTEXT_VEHICLE_TASKFLOW =
        "/WEB-INF/flows/vehicle/vehicle-dashboard-btf.xml#vehicle-dashboard-btf";

}

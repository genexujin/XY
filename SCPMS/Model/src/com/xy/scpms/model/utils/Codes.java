package com.xy.scpms.model.utils;

public class Codes {

    public static final String AUTH_TYPE_PLANT = "船厂";
    public static final String AUTH_TYPE_OWNER = "船东";
    public static final String AUTH_TYPE_BROKER = "中间商";
    public static final String CONTRACT_TYPE_AGREEMENT = "协议";
    public static final String CONTRACT_TYPE_NORMAL = "普通合同";
    public static final String CONTRACT_TYPE_DUPLICATE = "复用合同";
    
    public static final String CONTRACT_VALUE_NEW = "新船";
    public static final String CONTRACT_VALUE_COPY = "复用";
    public static final String CONTRACT_VALUE_OTHER = "其他";

    public static final String COL_VALUE_AUTH_TYPE = "N/A";
    public static final String COL_VALUE_STATUS_DRAFT = "未提交";
    public static final String COL_VALUE_STATUS_PENDING_APPROVAL = "审核中";
    public static final String COL_VALUE_STATUS_APPROVED = "审核通过";
    public static final String COL_VALUE_STATUS_REJECTED = "已否决";
    public static final String COL_VALUE_STATUS_ABANDONED = "已弃船";
    public static final String COL_VALUE_STATUS_TERMINATED = "合同终止";
    public static final String COL_VALUE_STATUS_SUSPENDED = "合同暂停";    
    public static final String COL_VALUE_STATUS_PENDING_ABANDONED = "申请弃船";
    public static final String COL_VALUE_STATUS_PAID = "完成收款";

    public static final String COL_VALUE_SHIP_NO = "收款协议";


    // COL VALUES for ApprovalInProcess Table
    public static final String COL_VALUE_AIP_APPROLE_OWNER = "合同创建人";
    public static final String COL_VALUE_AIP_APPROLE_CONTRACTOR = "合同管理员";
    public static final String COL_VALUE_AIP_APPROLE_MANAGER = "市场部主任";
    public static final String COL_VALUE_AIP_OUTCOME_PENDING = "审批中";
    public static final String COL_VALUE_AIP_OUTCOME_WAITING = "待审批";
    public static final String COL_VALUE_AIP_OUTCOME_SUBMIT = "提交";
    public static final String COL_VALUE_AIP_OUTCOME_APPROVED = "通过";
    public static final String COL_VALUE_AIP_OUTCOME_REJECTED = "未通过";
    public static final String COL_VALUE_AIP_TYPE_NEW = "新建合同申请";
    public static final String COL_VALUE_AIP_TYPE_TERMINATE = "终止合同";
    public static final String COL_VALUE_AIP_TYPE_SUSPEND = "暂停合同";
    public static final String COL_VALUE_AIP_TYPE_ABANDON = "弃船";
    public static final String COL_VALUE_AIP_TYPE_UPDATE = "合同修改";

    public static final String COL_VALUE_INVOICE_APP_STATUS_DRAFT = "未提交";

    public static final String COL_VALUE_PAYMENT_STATUS_INITIAL = "待开票";
    public static final String COL_VALUE_PAYMENT_STATUS_APPROVED = "审核通过";
    public static final String COL_VALUE_PAYMENT_STATUS_INVOICED = "已开票";
    public static final String COL_VALUE_PAYMENT_STATUS_PAID = "已收款";
    public static final String COL_VALUE_PAYMENT_STATUS_PAID_VAR = "已收款（差异）";
    public static final String COL_VALUE_PAYMENT_STATUS_PARTIALLY_PAID = "部分收款";
    public static final String COL_VALUE_PAYMENT_STATUS_REJECTED = "提交被退";


}

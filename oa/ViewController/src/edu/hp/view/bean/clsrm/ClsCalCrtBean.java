package edu.hp.view.bean.clsrm;

import edu.hp.model.common.Constants;
import edu.hp.view.bean.BaseBean;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import java.util.Calendar;

import java.util.Date;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.Key;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;


public class ClsCalCrtBean extends BaseBean {


    public ClsCalCrtBean() {
    }

    private boolean changeMade = false;
    private Timestamp startDayTime = null;
    private String clsRmId = null;

    public String save() {
        //Todo： 产生请购订单
        if (ensureTimeConflicts()) {
            boolean success = ADFUtils.commit("教室预订已保存！", "预订保存失败，请核对输入的信息或联系管理员！");
            if (success) {
                
                startDayTime = (Timestamp)ADFUtils.getBoundAttributeValue("ActStartTime");
                clsRmId = (String)ADFUtils.getBoundAttributeValue("ClassroomId");

                String userId = (String)ADFUtils.getBoundAttributeValue("UserId");
                String title = (String)ADFUtils.getBoundAttributeValue("ActTitle");

                String noteTitle = "您为事由：" + title + " 所做的教室预订已成功，请准时使用。 ";
                String dateStr = getDateString();
                String noteContent = " 提交时间：" + dateStr;
                //send to requester
                sendNotification(noteTitle, noteContent, userId, null);
                ADFUtils.findOperation("Commit").execute();
                changeMade = true;
            }
        } else {
            JSFUtils.addFacesErrorMessage("该教室该时间段已经有其他预订，无法创建新的预定，请更换时间段！");
        }

        return null;
    }

    protected Boolean ensureTimeConflicts() {

        Boolean result = false;
        OperationBinding binding = ADFUtils.findOperation("ifConflict");
        binding.execute();
        result = (Boolean)binding.getResult();
        return result;

    }

    public String goToCal() {

        //Integer locIndex = (Integer)JSFUtils.resolveExpression("#{pageFlowScope.clsCalBean.location}");
        Key currentLocationKey = (Key)JSFUtils.resolveExpression("#{pageFlowScope.clsCalBean.currentLocationKey}");
        DCIteratorBinding it = ADFUtils.findIterator("LocationsIterator");
        it.setCurrentRowWithKey(currentLocationKey.toStringFormat(true));

        //it.setCurrentRowIndexInRange(locIndex);
        return "Calendar";
    }

    public String goBackToCalendar() {

        if (clsRmId != null && startDayTime != null && changeMade) {
            String ids = (String)JSFUtils.resolveExpression("#{pageFlowScope.clsCalBean.providerIds}");
            if (ids != null && ids.indexOf(clsRmId) < 0) {
                if (!ids.equals("NA"))
                    ids = ids + "," + clsRmId;
                else
                    ids = clsRmId;
            }
            
            Date activeDay = new Date(startDayTime.getTime());            
            JSFUtils.setExpressionValue("#{pageFlowScope.clsCalBean.activeDay}", activeDay);
            OperationBinding refreshOp = ADFUtils.findOperation("refreshCalendar");
            refreshOp.getParamsMap().put("clsRmNos", ids);
            refreshOp.execute();
        }
        return "Calendar";
    }
}

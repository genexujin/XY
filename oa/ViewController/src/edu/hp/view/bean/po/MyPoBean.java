package edu.hp.view.bean.po;

import edu.hp.model.common.Constants;
import edu.hp.view.bean.BaseBean;
import edu.hp.view.utils.ADFUtils;

import edu.hp.view.utils.JSFUtils;

import java.math.BigDecimal;

import java.sql.Date;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

public class MyPoBean extends BaseBean {
    private String orderReadableId;
    private Date submitDateFrom;
    private Date submitDateTo;
    private RichTable resultTable;
    private RichInputText submitTotalComp;
    private String action;

    private RichTable poLinesTable;
    private RichTable poHistoryTable;
    private RichPanelFormLayout poForm;

    private boolean cancelButtonRendered;
    private boolean receiveQuantityReadonly;
    private boolean actualPriceReadonly;

    public MyPoBean() {
    }

    public String doQuery() {
        String poStateId = getLovAttrValue("PoStateWithEmpty", "FlexCol1");
        //        System.out.println("PoState Id is: " + poStateId);
        //        String itemCategoryId = getLovAttrValue("ItemCategory", "Id");
        //        System.out.println("ItemCategory Id is: " + itemCategoryId);
        String submitterId = getLovAttrValue("EmpWithEmpty", "Id");
        //        System.out.println("submitterId is: " + submitterId);
        String newItemCategory = getLovAttrValue("ItemCategoryWithEmpty", "Value");
        //        System.out.println("ItemCategory is: " + newItemCategory);
        //
        //        System.out.println("OrderReadableId is: " + orderReadableId);
        //        System.out.println("SubmitDateFrom is: " + submitDateFrom);
        //        System.out.println("SubmitDateTo is: " + submitDateTo);

        OperationBinding binding = ADFUtils.findOperation("doQuery");
        binding.getParamsMap().put("oRdId", orderReadableId);
        binding.getParamsMap().put("state", poStateId);
        binding.getParamsMap().put("category", newItemCategory);
        binding.getParamsMap().put("submitDateFrom", submitDateFrom);
        binding.getParamsMap().put("submitDateTo", submitDateTo);
        binding.getParamsMap().put("submitterId", submitterId);
        //
        String buyerId = null;
        if (JSFUtils.resolveExpressionAsBoolean("#{pageFlowScope.fromMenu=='buyer'}")) {
            buyerId = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
            System.out.println("BuyerId is: " + buyerId);
        }
        binding.getParamsMap().put("buyerId", buyerId);
        binding.execute();

        ADFUtils.partialRefreshComponenet(resultTable);

        return null;
    }

    private String getLovAttrValue(String lovBindingName, String attrName) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        JUCtrlListBinding binding = (JUCtrlListBinding)bindings.get(lovBindingName);
        Row row = binding.getCurrentRow();
        Object value = row.getAttribute(attrName);
        if (value instanceof DBSequence) {
            return ((DBSequence)value).getValue() + "";
        }
        return value.toString();
    }

    public void setOrderReadableId(String orderReadableId) {
        this.orderReadableId = orderReadableId;
    }

    public String getOrderReadableId() {
        return orderReadableId;
    }

    public void setSubmitDateFrom(Date submitDateFrom) {
        this.submitDateFrom = submitDateFrom;
    }

    public Date getSubmitDateFrom() {
        return submitDateFrom;
    }

    public void setSubmitDateTo(Date submitDateTo) {
        this.submitDateTo = submitDateTo;
    }

    public Date getSubmitDateTo() {
        return submitDateTo;
    }

    public void setResultTable(RichTable resultTable) {
        this.resultTable = resultTable;
    }

    public RichTable getResultTable() {
        return resultTable;
    }

    public void savePo(ActionEvent actionEvent) {
        computeTotal("SubmitPrice", "SubmitQuantity", "SubmitTotal", "SubmitTotal");
        String fromMenu = JSFUtils.resolveExpressionAsString("#{pageFlowScope.fromMenu}");
        if ("buyer".equals(fromMenu)) {
            computeTotal("ActualPrice", "PurchaseQuantity", "ActualTotal", "ActualTotal");
        }

        ADFUtils.commit("采购订单已保存！", "采购订单保存失败，请核对输入的信息或联系管理员！");
    }

    public void submitPo(ActionEvent actionEvent) {
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lineIt.setRangeSize(-1);
        if (lineIt.getAllRowsInRange().length == 0) {
            JSFUtils.addFacesErrorMessage("必须有订单行才能提交订单！");
        } else {
            String state = (String)ADFUtils.getBoundAttributeValue("State");
            if (state != null &&
                (state.equals(Constants.PO_STATE_INITIAL) || state.equals(Constants.PO_STATE_REJECTED))) {
                if (state.equals(Constants.PO_STATE_INITIAL)) {
                    computeTotal("SubmitPrice", "SubmitQuantity", "SubmitTotal", "SubmitTotal");
                    setSubmitDate();
                }

                String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
                boolean skip = submitterIsDeptSuporvisor(submitterId);

                //If submitter is dept suporvisor, then go to PO_STATE_PENDING_REVIEW directly
                if (skip) {
                    ADFUtils.setBoundAttributeValue("State", Constants.PO_STATE_PENDING_REVIEW);
                } else {
                    ADFUtils.setBoundAttributeValue("State", Constants.PO_STATE_DEPT_REVIEW);
                }

                boolean success = ADFUtils.commit("采购订单已提交！", "采购订单提交失败，请核对输入的信息或联系管理员！");
                if (success) {
                    String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
                    String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
                    String submitTotal = ADFUtils.getBoundAttributeValue("SubmitTotal").toString();

                    insertPoHistory(id, submitterId, "提交了该订单");

                    if (skip) {
                        createTask(id, Constants.CONTEXT_TYPE_PO,
                                   "有新的采购订单等待审核，订单号：" + readableId + "，预算总金额：" + submitTotal,
                                   Constants.ROLE_PO_VERIFIER, readableId);

                        sendNotification("有新的采购订单等待审核。", "订单号：" + readableId + "，预算总金额：" + submitTotal, null,
                                         Constants.ROLE_PO_VERIFIER, Constants.CONTEXT_TYPE_PO, id);

                    } else {
                        String supervisorId = getDeptSupervisorId(submitterId);
                        ADFUtils.setBoundAttributeValue("DeptVerifier", supervisorId);

                        createTaskForUser(id, Constants.CONTEXT_TYPE_PO,
                                          "有新的采购订单等待部门审核，订单号：" + readableId + "，预算总金额：" + submitTotal, supervisorId,
                                          readableId);

                        sendNotification("有新的采购订单等待部门审核。", "订单号：" + readableId + "，预算总金额：" + submitTotal, supervisorId,
                                         null, Constants.CONTEXT_TYPE_PO, id);
                        //还得发消息给部门经理！Supervisor不是部门经理！
                        String mgrId = getDeptMgrId(submitterId);
                        String submitterName = ADFUtils.getBoundAttributeValue("SubmitterName").toString();
                        sendNotification("有新的采购订单发给部门主管审核。",
                                         "订单号：" + readableId + "，申请人：" + submitterName + "，预算总金额：" + submitTotal,
                                         mgrId, null, Constants.CONTEXT_TYPE_PO, id);
                    }


                    //有一种情况下需要completeTask，就是在订单被拒绝后，会为提交者创建一个新task。用户可以再次提交该订单，这时候需要complete之前的task
                    //（后来发现这种情况下只会发通知，所以不需要了）
                    //                    completeTaskForUser(Constants.CONTEXT_TYPE_PO, id, submitterId);

                    ADFUtils.findOperation("Commit").execute();
                } else {
                    ADFUtils.setBoundAttributeValue("State", state);
                }
            }
        }
    }

    public void verifyPo(ActionEvent actionEvent) {
        if (!checkAllRowsHasPurchaseQuantity()) {
            JSFUtils.addFacesErrorMessage("所有未取消的行都必须有采购数量");
        } else if (!checkAssignedBuyer()) {
            JSFUtils.addFacesErrorMessage("必须指派采购员");
        } else {
            double verifyTotal = computeTotal("SubmitPrice", "PurchaseQuantity", null, "VerifyTotal");

            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            boolean skip = submitterIsApprover(submitterId);
            //不但可能跳过第一级审批，如果小于终审下限的话，还会跳过第二级审批
            String category = (String)ADFUtils.getBoundAttributeValue("ItemCategoryId1");
            System.out.println("Item category id is: " + category);
            BigDecimal limit = getApprovalLimit(category);

            if (verifyTotal == 0) {
                System.out.println("VerifyTotal is 0. No need to approve.");
                changeState(Constants.PO_STATE_FINISHED);
            } else if (skip && new BigDecimal(verifyTotal).compareTo(limit) <= 0) {
                changeState(Constants.PO_STATE_EXECUTING);
            } else {
                changeState(Constants.PO_STATE_PENDING_APPROVAL);
            }

            boolean success = ADFUtils.commit("采购订单已审核！", "采购订单审核失败，请核对输入的信息或联系管理员！");
            if (success) {
                String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
                String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
                String verifier = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
                insertPoHistory(id, verifier, "审核了该订单");

                if (verifyTotal != 0) { //If verifyTotal is 0, then no need to approve.
                    if (skip && new BigDecimal(verifyTotal).compareTo(limit) <= 0) {
                        ADFUtils.setBoundAttributeValue("CurrentApprover", "");
                        ADFUtils.setBoundAttributeValue("CurrentExecutor", Constants.ROLE_PO_BUYER);

                        //需要发送消息给真正的采购者
                        String buyerId = (String)ADFUtils.getBoundAttributeValue("CurrentBuyerId");
                        sendNotification("有新的采购订单等待采购及收货。", "订单号：" + readableId + "，总金额：" + verifyTotal, buyerId, null,
                                         Constants.CONTEXT_TYPE_PO, id);
                        //                        sendNotification("有新的采购订单等待采购及收货。", "订单号：" + readableId + "，总金额：" + verifyTotal, null, Constants.ROLE_PO_BUYER);
                        sendNotification("您的采购订单已审批。", "订单号： " + readableId, submitterId, null,
                                         Constants.CONTEXT_TYPE_PO, id);
                    } else if (skip) {
                        ADFUtils.setBoundAttributeValue("CurrentApprover", Constants.ROLE_PO_2ND_APPROVER);
                        //Create task for 2nd approver
                        createTask(id, Constants.CONTEXT_TYPE_PO,
                                   "有新的采购订单等待审批，订单号：" + readableId + "，审核总金额：" + verifyTotal,
                                   Constants.ROLE_PO_2ND_APPROVER, readableId);
                        sendNotification("有新的采购订单等待审批。", "订单号：" + readableId, null, Constants.ROLE_PO_2ND_APPROVER,
                                         Constants.CONTEXT_TYPE_PO, id);
                    } else {
                        ADFUtils.setBoundAttributeValue("CurrentApprover", Constants.ROLE_PO_APPROVER);
                        //Create task for approver
                        createTask(id, Constants.CONTEXT_TYPE_PO,
                                   "有新的采购订单等待审批，订单号：" + readableId + "，审核总金额：" + verifyTotal,
                                   Constants.ROLE_PO_APPROVER, readableId);
                        sendNotification("有新的采购订单等待审批。", "订单号：" + readableId, null, Constants.ROLE_PO_APPROVER,
                                         Constants.CONTEXT_TYPE_PO, id);
                    }

                    sendNotificationForVerify(verifyTotal);
                } else {
                    sendNotificationForFinish();
                }

                //Complete task for verifier
                completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_VERIFIER);

                ADFUtils.findOperation("Commit").execute();
            } else {
                //Change back the state if commit fails
                changeState(Constants.PO_STATE_PENDING_REVIEW);
            }
        }

    }

    public void verifyPoInDept(ActionEvent actionEvent) {
        String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
        boolean skip = submitterIsVerifier(submitterId);
        //如果申请人是本身就是总务审核，则跳过总务审核
        //跳过时，采购价格则直接用预算价格。审核备注则为空
        if (skip) {
            changeState(Constants.PO_STATE_PENDING_APPROVAL);
            setPurchaseQuantityAndComputeTotal();
        } else {
            changeState(Constants.PO_STATE_PENDING_REVIEW);
        }

        boolean success = ADFUtils.commit("采购订单部门审核通过！", "采购订单审核失败，请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String verifier = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
            String submitTotal = ADFUtils.getBoundAttributeValue("SubmitTotal").toString();


            insertPoHistory(id, verifier, "完成了部门审核");

            if (skip) {
                String verifyTotal = ADFUtils.getBoundAttributeValue("VerifyTotal").toString();
                createTask(id, Constants.CONTEXT_TYPE_PO, "有新的采购订单等待审批，订单号：" + readableId + "，审核总金额：" + verifyTotal,
                           Constants.ROLE_PO_APPROVER, readableId);
                sendNotification("有新的采购订单等待审批。", "订单号：" + readableId + "，审核总金额：" + submitTotal, null,
                                 Constants.ROLE_PO_APPROVER, Constants.CONTEXT_TYPE_PO, id);

                sendNotification("您的采购订单已完成部门审核。", "订单号： " + readableId, submitterId, null, Constants.CONTEXT_TYPE_PO,
                                 id);
            } else {
                createTask(id, Constants.CONTEXT_TYPE_PO, "有新的采购订单等待审核，订单号：" + readableId + "，预算总金额：" + submitTotal,
                           Constants.ROLE_PO_VERIFIER, readableId);
                sendNotification("有新的采购订单等待审核。", "订单号：" + readableId + "，预算总金额：" + submitTotal, null,
                                 Constants.ROLE_PO_VERIFIER, Constants.CONTEXT_TYPE_PO, id);

                sendNotification("您的采购订单已完成部门审核,等待采购审核中。", "订单号： " + readableId, submitterId, null,
                                 Constants.CONTEXT_TYPE_PO, id);
            }

            //Complete task for dept verifier
            completeTaskForUser(Constants.CONTEXT_TYPE_PO, id, verifier);

            ADFUtils.findOperation("Commit").execute();
        } else {
            //Change back the state if commit fails
            changeState(Constants.PO_STATE_DEPT_REVIEW);
        }
    }

    private void sendNotificationForFinish() {
        String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
        String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
        sendNotification("您的采购订单已完成。", "订单号： " + readableId, submitterId, null, Constants.CONTEXT_TYPE_PO, null);
    }

    private void sendNotificationForVerify(double verifyTotal) {
        String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
        String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
        sendNotification("您的采购订单已完成审核,等待审批中。", "订单号：" + readableId + "，审核总金额：" + verifyTotal, submitterId, null,
                         Constants.CONTEXT_TYPE_PO, null);
    }

    public void approvePo(ActionEvent actionEvent) {
        BigDecimal verifyTotal = new BigDecimal((String)ADFUtils.getBoundAttributeValue("VerifyTotal"));
        String category = (String)ADFUtils.getBoundAttributeValue("ItemCategoryId1");
        System.out.println("Item category id is: " + category);
        BigDecimal limit = getApprovalLimit(category);
        String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
        boolean skip = submitterIs2ndApprover(submitterId);

        System.out.println("Apprval Limit for Item category \"" + category + "\" is: " + limit);
        boolean success = false;
        if (verifyTotal.compareTo(limit) <= 0 || skip) { //No need for 2nd level approval
            changeState(Constants.PO_STATE_EXECUTING);
            success = ADFUtils.commit("采购订单已审批，正在执行中", "采购订单审批失败，请核对输入的信息或联系管理员！");
        } else {
            success = ADFUtils.commit("采购订单已审批，需要下一级审批", "采购订单审批失败，请核对输入的信息或联系管理员！");
        }

        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();

            String approver = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
            insertPoHistory(id, approver, "审批了该订单");

            if (verifyTotal.compareTo(limit) <= 0 || skip) {
                ADFUtils.setBoundAttributeValue("CurrentApprover", "");
                ADFUtils.setBoundAttributeValue("CurrentExecutor", Constants.ROLE_PO_BUYER);

                //需要发送给真正的采购者
                String buyerId = (String)ADFUtils.getBoundAttributeValue("CurrentBuyerId");
                sendNotification("有新的采购订单等待采购及收货。", "订单号：" + readableId + "，总金额：" + verifyTotal, buyerId, null,
                                 Constants.CONTEXT_TYPE_PO, id);
                //                sendNotification("有新的采购订单等待采购及收货。", "订单号：" + readableId + "，总金额：" + verifyTotal, null, Constants.ROLE_PO_BUYER);
                sendNotification("您的采购订单已审批。", "订单号： " + readableId, submitterId, null, Constants.CONTEXT_TYPE_PO, id);
            } else {
                //Set the current verifier for the po
                ADFUtils.setBoundAttributeValue("CurrentApprover", Constants.ROLE_PO_2ND_APPROVER);

                //Should create task for 2nd level approver
                createTask(id, Constants.CONTEXT_TYPE_PO, "有新的采购订单等待审批，订单号：" + readableId + "，总金额：" + verifyTotal,
                           Constants.ROLE_PO_2ND_APPROVER, readableId);

                sendNotification("有新的采购订单等待审批。", "订单号：" + readableId + "，总金额：" + verifyTotal, null,
                                 Constants.ROLE_PO_2ND_APPROVER, Constants.CONTEXT_TYPE_PO, id);
            }

            //Complete task for approver
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_APPROVER);

            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_PENDING_APPROVAL);
        }
    }

    public void approvePo2nd(ActionEvent actionEvent) {
        changeState(Constants.PO_STATE_EXECUTING);

        boolean success = ADFUtils.commit("采购订单已审批！", "采购订单审批失败，请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();

            String approver = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
            insertPoHistory(id, approver, "审批了该订单");

            //Clear the current approver
            ADFUtils.setBoundAttributeValue("CurrentApprover", "");
            ADFUtils.setBoundAttributeValue("CurrentExecutor", Constants.ROLE_PO_BUYER);

            //需要发送给真正的采购者
            String buyerId = (String)ADFUtils.getBoundAttributeValue("CurrentBuyerId");
            sendNotification("有新的采购订单等待采购及收货。", "订单号：" + readableId, buyerId, null, Constants.CONTEXT_TYPE_PO, id);
            //            sendNotification("有新的采购订单等待采购。", "订单号： " + readableId, null, Constants.ROLE_PO_BUYER);
            sendNotification("有新的采购订单等待收货。", "订单号： " + readableId, null, Constants.ROLE_PO_RECEIVER,
                             Constants.CONTEXT_TYPE_PO, id);
            sendNotification("您的采购订单已审批。", "订单号： " + readableId, submitterId, null, Constants.CONTEXT_TYPE_PO, id);

            //Complete task for 2nd level approver
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_2ND_APPROVER);

            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_PENDING_APPROVAL);
        }
    }

    public void executePo(ActionEvent actionEvent) {
        //First check all lines have "ActualPrice"
        if (!checkAllRowsHasActualPrice()) {
            JSFUtils.addFacesErrorMessage("所有未取消的订单行都必须输入实际单价！");
        } else {
            changeState(Constants.PO_STATE_FINISHED);

            computeTotal("ActualPrice", "ReceiveQuantity", "ActualTotal", "ActualTotal");
            boolean success = ADFUtils.commit("采购订单已完成！", "采购订单提交失败，请核对输入的信息或联系管理员！");
            if (success) {
                String id = ADFUtils.getBoundAttributeValue("OrderId").toString();

                String buyer = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
                insertPoHistory(id, buyer, "完成了该订单");

                //ADFUtils.setBoundAttributeValue("CurrentExecutor", Constants.ROLE_PO_RECEIVER);

                //send notification to submitter
                sendNotificationForFinish();

                ADFUtils.findOperation("Commit").execute();
            }
        }
    }

    public void finishPo(ActionEvent actionEvent) {
        boolean success = ADFUtils.commit("采购订单收货完成！", "采购订单收货失败，请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            //            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            //            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();
            //
            String receiver = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
            insertPoHistory(id, receiver, "对该订单进行了收货");
            //            ADFUtils.setBoundAttributeValue("CurrentExecutor", "");

            //Only notification sent to receiver, no task created. So no task to complete
            //            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_RECEIVER);

            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_EXECUTING);
        }
    }

    public void rejectPo(ActionEvent actionEvent) {
        changeState(Constants.PO_STATE_REJECTED);
        boolean success = ADFUtils.commit("采购订单已拒绝！", "采购订单拒绝失败！请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();

            String approver = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
            insertPoHistory(id, approver, "拒绝了该订单");

            //Clear the current approver
            ADFUtils.setBoundAttributeValue("CurrentApprover", "");

            //No task created after rejection. Only notification
            //            createTaskForUser(id, Constants.CONTEXT_TYPE_PO, "您的采购订单被拒绝，请取消或者重新提交订单。", submitterId, readableId);
            sendNotification("您的采购订单被拒绝,请取消或者重新提交订单。", "订单号： " + readableId, submitterId, null,
                             Constants.CONTEXT_TYPE_PO, id);
            //Complete task for approver or verifier
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_VERIFIER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_APPROVER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_2ND_APPROVER);

            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_PENDING_APPROVAL);
        }

    }

    public void rejectPoInDept(ActionEvent actionEvent) {
        changeState(Constants.PO_STATE_REJECTED);
        boolean success = ADFUtils.commit("采购订单已拒绝！", "采购订单拒绝失败！请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();

            String verifier = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
            insertPoHistory(id, verifier, "拒绝了该订单");

            //No task created after rejection. Only notification
            //            createTaskForUser(id, Constants.CONTEXT_TYPE_PO, "您的采购订单被拒绝，请取消或者重新提交订单。", submitterId, readableId);
            sendNotification("您的采购订单被拒绝,请取消或者重新提交订单。", "订单号： " + readableId, submitterId, null,
                             Constants.CONTEXT_TYPE_PO, id);

            completeTaskForUser(Constants.CONTEXT_TYPE_PO, id, verifier);

            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(Constants.PO_STATE_DEPT_REVIEW);
        }

    }

    public void cancelPo(ActionEvent actionEvent) {

        String state = (String)ADFUtils.getBoundAttributeValue("State");

        if (state.equals(Constants.PO_STATE_CANCELLED)) {
            JSFUtils.addFacesErrorMessage("订单已经被取消，无法重复取消！");
            return;
        }

        changeState(Constants.PO_STATE_CANCELLED);

        boolean success = ADFUtils.commit("采购订单已取消！", "采购订单取消失败！请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String submitterId = ADFUtils.getBoundAttributeValue("SubmitterId").toString();

            String deptVerifier = null;
            if (ADFUtils.getBoundAttributeValue("DeptVerifier") != null) {
                deptVerifier = ADFUtils.getBoundAttributeValue("DeptVerifier").toString();
            }

            String operator = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
            insertPoHistory(id, operator, "取消了该订单");

            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_VERIFIER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_APPROVER);
            if (deptVerifier != null) {
                completeTaskForUser(Constants.CONTEXT_TYPE_PO, id, deptVerifier);
            }
            completeTaskForUser(Constants.CONTEXT_TYPE_PO, id, submitterId);

            String fromMenu = JSFUtils.resolveExpressionAsString("#{pageFlowScope.fromMenu}");
            if (!"normal".equals(fromMenu)) {
                String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
                sendNotification("您的采购订单已经取消。", "订单号： " + readableId, submitterId, null, Constants.CONTEXT_TYPE_PO,
                                 id);
            }

            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(state);
        }
    }

    public void reopenPo(ActionEvent actionEvent) {
        String state = (String)ADFUtils.getBoundAttributeValue("State");
        String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();
        String verifyTotal = (String)ADFUtils.getBoundAttributeValue("VerifyTotal");
        if (Double.parseDouble(verifyTotal) == 0.0) {
            JSFUtils.addFacesInformationMessage("审核总金额为0，无法重新打开");
        } else {
            ADFUtils.setBoundAttributeValue("State", Constants.PO_STATE_EXECUTING);
            boolean success = ADFUtils.commit("采购订单已重新打开！", "采购订单重新打开失败！请核对输入的信息或联系管理员！");
            if (success) {
                String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
                String operator = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
                insertPoHistory(id, operator, "重新打开了该订单");

                sendNotification("有采购订单重新打开。", "订单号： " + readableId, null, Constants.ROLE_PO_BUYER,
                                 Constants.CONTEXT_TYPE_PO, id);

                ADFUtils.findOperation("Commit").execute();
            } else {
                changeState(state);
            }
        }
    }

    public void backToVerifyPo(ActionEvent actionEvent) {
        String state = (String)ADFUtils.getBoundAttributeValue("State");

        ADFUtils.setBoundAttributeValue("State", Constants.PO_STATE_PENDING_REVIEW);

        boolean success = ADFUtils.commit("采购订单已重新打开！", "采购订单重新打开失败！请核对输入的信息或联系管理员！");
        if (success) {
            String id = ADFUtils.getBoundAttributeValue("OrderId").toString();
            String readableId = ADFUtils.getBoundAttributeValue("OrderReadableId").toString();

            String operator = JSFUtils.resolveExpressionAsString("#{sessionScope.LoginUserBean.userId}");
            insertPoHistory(id, operator, "将该订单重新变为待审核");

            //need to complete task which is opened for other roles
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_APPROVER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_2ND_APPROVER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_BUYER);
            completeTask(Constants.CONTEXT_TYPE_PO, id, Constants.ROLE_PO_RECEIVER);

            ADFUtils.setBoundAttributeValue("CurrentApprover", "");
            ADFUtils.setBoundAttributeValue("CurrentExecutor", "");

            createTask(id, Constants.CONTEXT_TYPE_PO, "有新的采购订单等待审核，订单号： " + readableId, Constants.ROLE_PO_VERIFIER,
                       readableId);

            //Need to clear the following values
            ADFUtils.setBoundAttributeValue("CurrentApprover", "");
            ADFUtils.setBoundAttributeValue("CurrentExecutor", "");

            if (state != null && state.equals(Constants.PO_STATE_EXECUTING)) {
                sendNotification("采购订单需重新审核，暂停采购。", "订单号： " + readableId, null, Constants.ROLE_PO_BUYER,
                                 Constants.CONTEXT_TYPE_PO, id);
            }
            ADFUtils.findOperation("Commit").execute();
        } else {
            changeState(state);

        }
    }

    private boolean checkAllRowsHasActualPrice() {
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lineIt.setRangeSize(-1);
        Row[] rows = lineIt.getAllRowsInRange();
        if (rows == null || rows.length == 0) {
            System.err.println("There is no row at all!");
            return false;
        } else {
            for (Row row : rows) {
                String isCancelled = (String)row.getAttribute("Cancelled");
                //已取消或者采购数量为0的行不需要设置ActualPrice
                if (!"Y".equals(isCancelled) &&
                    !(((BigDecimal)row.getAttribute("PurchaseQuantity")).intValue() == 0)) {
                    double price = 0;
                    BigDecimal p = (BigDecimal)row.getAttribute("ActualPrice");
                    if (p == null) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private boolean checkAllRowsHasPurchaseQuantity() {
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lineIt.setRangeSize(-1);
        Row[] rows = lineIt.getAllRowsInRange();
        if (rows == null || rows.length == 0) {
            System.err.println("There is no row at all!");
            return false;
        } else {
            for (Row row : rows) {
                String isCancelled = (String)row.getAttribute("Cancelled");
                //已取消的行不需要设置ActualPrice
                if (!"Y".equals(isCancelled)) {
                    double price = 0;
                    BigDecimal p = (BigDecimal)row.getAttribute("PurchaseQuantity");
                    if (p == null) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private double computeTotal(String priceAttr, String quantityAttr, String lineTotalAttr, String masterTotalAttr) {
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lineIt.setRangeSize(-1);
        Row[] rows = lineIt.getAllRowsInRange();
        double masterTotal = 0;
        if (rows == null || rows.length == 0) {
            System.err.println("There is no row at all!");
        } else {
            System.out.println("The PO line rows count is: " + rows.length);
            for (Row row : rows) {
                String isCancelled = (String)row.getAttribute("Cancelled");
                System.out.println("Is the PO line cancelled? " + isCancelled);
                //已取消的行不计算在总金额中
                if (!"Y".equals(isCancelled)) {
                    double price = 0;
                    BigDecimal p = (BigDecimal)row.getAttribute(priceAttr);
                    if (p != null) {
                        price = p.doubleValue();
                    }
                    double quantity = 0;
                    BigDecimal q = (BigDecimal)row.getAttribute(quantityAttr);
                    if (q != null) {
                        quantity = q.doubleValue();
                    }
                    System.out.println(priceAttr + " is: " + price + " " + quantityAttr + " is: " + quantity);
                    if (lineTotalAttr != null) {
                        row.setAttribute(lineTotalAttr, price * quantity);
                    }
                    masterTotal += price * quantity;
                }
            }

            System.out.println("Master " + masterTotalAttr + " is: " + masterTotal);
            if (masterTotalAttr != null) {
                DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrdersViewIterator");
                Row masterRow = it.getCurrentRow();
                masterRow.setAttribute(masterTotalAttr, masterTotal);
            }
        }

        return masterTotal;
    }

    private void changeState(String state) {
        DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrdersViewIterator");
        Row row = it.getCurrentRow();
        row.setAttribute("State", state);
    }

    private void commit() {
        OperationBinding oper = ADFUtils.findOperation("Commit");
        oper.execute();
    }

    public void itemCategoryChanged(ValueChangeEvent valueChangeEvent) {
        DCIteratorBinding lbinding = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lbinding.executeQuery();
    }

    public void submitterChanged(ValueChangeEvent valueChangeEvent) {
    }

    public void submitPriceChanged(ValueChangeEvent valueChangeEvent) {
        String newPriceStr = (String)valueChangeEvent.getNewValue();
        System.out.println("newPrice is: " + newPriceStr);

        computeTotal("SubmitPrice", "SubmitQuantity", "SubmitTotal", "SubmitTotal");
        ADFUtils.partialRefreshComponenet(submitTotalComp);
    }

    public void submitQuantityChanged(ValueChangeEvent valueChangeEvent) {
        long newQuantity = ((BigDecimal)valueChangeEvent.getNewValue()).longValue();
        System.out.println("newQuantity is: " + newQuantity);

        computeTotal("SubmitPrice", "SubmitQuantity", "SubmitTotal", "SubmitTotal");
        ADFUtils.partialRefreshComponenet(submitTotalComp);
    }

    public void purchaseQuantityChanged(ValueChangeEvent valueChangeEvent) {
        long newQuantity = ((BigDecimal)valueChangeEvent.getNewValue()).longValue();
        System.out.println("newQuantity is: " + newQuantity);
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        Row row = lineIt.getCurrentRow();
        double price = 0;
        BigDecimal p = (BigDecimal)row.getAttribute("ActualPrice");
        if (p != null) {
            price = p.doubleValue();
        }
        System.out.println("Actual Price is: " + price);
        row.setAttribute("ActualTotal", price * newQuantity);
        //Set the PurchaseQuantity to the new value, so that the following compute method can use it.
        row.setAttribute("PurchaseQuantity", newQuantity);

        computePoVerifyTotal(lineIt);
    }

    public void actualPriceChanged(ValueChangeEvent valueChangeEvent) {
        double newPrice = ((BigDecimal)valueChangeEvent.getNewValue()).doubleValue();
        System.out.println("newPrice is: " + newPrice);
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        Row row = lineIt.getCurrentRow();
        long quantity = 0;
        BigDecimal q = (BigDecimal)row.getAttribute("PurchaseQuantity");
        if (q != null) {
            quantity = q.longValue();
        }
        System.out.println("Purchase Quantity is: " + quantity);
        row.setAttribute("ActualTotal", newPrice * quantity);
    }

    private void computePoSubmitTotal(DCIteratorBinding lineIt) {
        Row[] rows = lineIt.getAllRowsInRange();
        System.out.println("Will RangeSize be large enough? RangeSize is:" + lineIt.getRangeSize());
        double masterTotal = 0;
        for (Row r : rows) {
            double submitTotal = 0;
            BigDecimal t = (BigDecimal)r.getAttribute("SubmitTotal");
            if (t != null) {
                submitTotal = t.doubleValue();
            }
            masterTotal += submitTotal;
        }
        System.out.println("Master SubmitTotal is: " + masterTotal);
        DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrdersViewIterator");
        Row masterRow = it.getCurrentRow();
        masterRow.setAttribute("SubmitTotal", masterTotal);
    }


    private void computePoVerifyTotal(DCIteratorBinding lineIt) {
        Row[] rows = lineIt.getAllRowsInRange();
        System.out.println("Will RangeSize be large enough? RangeSize is:" + lineIt.getRangeSize());
        double masterTotal = 0;
        for (Row r : rows) {
            double submitPrice = 0;
            BigDecimal p = (BigDecimal)r.getAttribute("SubmitPrice");
            if (p != null) {
                submitPrice = p.doubleValue();
            }
            long quantity = 0;
            BigDecimal q = (BigDecimal)r.getAttribute("PurchaseQuantity");
            if (q != null) {
                quantity = q.longValue();
            }
            masterTotal += submitPrice * quantity;
        }
        System.out.println("Master VerifyTotal is: " + masterTotal);
        DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrdersViewIterator");
        Row masterRow = it.getCurrentRow();
        masterRow.setAttribute("VerifyTotal", masterTotal);
    }

    private void setSubmitDate() {
        DCIteratorBinding it = ADFUtils.findIterator("PurchaseOrdersViewIterator");
        Row row = it.getCurrentRow();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        System.out.println("Current time is: " + now);
        row.setAttribute("SubmitAt", now);
    }

    public void newPoLine(ActionEvent actionEvent) {
        String catg = (String)ADFUtils.getBoundAttributeValue("ItemCategoryId1");
        if (catg == null || catg.isEmpty()) {
            JSFUtils.addFacesErrorMessage("必须先选择采购类别");
        } else {
            OperationBinding oper = ADFUtils.findOperation("newRow");
            oper.execute();
        }
    }

    private BigDecimal getApprovalLimit(final String catgId) {
        OperationBinding oper = ADFUtils.findOperation("getApprovalLimitForCategoryId");
        oper.getParamsMap().put("categoryId", catgId);
        oper.execute();
        BigDecimal result = (BigDecimal)oper.getResult();
        if (result != null) {
            return result;
        }

        return new BigDecimal(0);
    }

    private String getDeptSupervisorId(final String submitterId) {
        OperationBinding oper = ADFUtils.findOperation("getDeptSupervisorId");
        oper.getParamsMap().put("submitterId", submitterId);
        oper.execute();
        String result = (String)oper.getResult();
        if (result != null) {
            return result;
        }

        return "";
    }

    public String returnClicked() {
        DCIteratorBinding state = ADFUtils.findIterator("PoStateWithEmptyIterator");
        state.executeQuery();

        DCIteratorBinding category = ADFUtils.findIterator("ItemCategoryWithEmptyIterator");
        category.executeQuery();

        DCIteratorBinding emp = ADFUtils.findIterator("EmpWithEmptyIterator");
        emp.executeQuery();

        this.orderReadableId = null;
        this.submitDateFrom = null;
        this.submitDateTo = null;

        String fromMenu = (String)JSFUtils.resolveExpression("#{pageFlowScope.fromMenu}");
        System.out.println("from menu: " + fromMenu);

        if ("verifier".equals(fromMenu)) {
            return "returnFromVerifier";
        } else if ("normal".equals(fromMenu)) {
            return "returnFromNormal";
        } else if ("approver".equals(fromMenu)) {
            return "returnFromApprover";
        } else if ("buyer".equals(fromMenu)) {
            return "returnFromBuyer";
        } else if ("dept".equals(fromMenu)) {
            return "returnFromDept";
        } else if ("query".equals(fromMenu)) {
            return "returnFromQuery";
        } else {
            return "returnFromReceiver";
        }
    }

    private void insertPoHistory(String orderId, String operatorId, String operationDetail) {
        OperationBinding oper = ADFUtils.findOperation("insertPoHistory");
        oper.getParamsMap().put("orderId", orderId);
        oper.getParamsMap().put("operatorId", operatorId);
        oper.getParamsMap().put("operationDetail", operationDetail);

        oper.execute();
    }
    //
    //    public void setCurrDate(Date currDate) {
    //        this.currDate = currDate;
    //    }

    public Date getCurrDate() {
        Date now = new Date(System.currentTimeMillis());
        return now;
    }

    public void setSubmitTotalComp(RichInputText submitTotalComp) {
        this.submitTotalComp = submitTotalComp;
    }

    public RichInputText getSubmitTotalComp() {
        return submitTotalComp;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void submitConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            submitPo(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void deptVerifyConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            verifyPoInDept(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void deptRejectConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            rejectPoInDept(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void verifyConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            verifyPo(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void approve1Confirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            approvePo(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void approve2Confirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            approvePo2nd(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void rejectConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            rejectPo(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void cancelConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            cancelPo(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void finishConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            finishPo(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void receiveConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            finishPo(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void executeConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            executePo(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void reopenConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            reopenPo(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void reverifyConfirm(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            backToVerifyPo(null);

            ADFUtils.partialRefreshComponenet(poLinesTable);
            ADFUtils.partialRefreshComponenet(poHistoryTable);
            ADFUtils.partialRefreshComponenet(poForm);
        }
    }

    public void setActionListener(ClientEvent clientEvent) {
        System.err.println("here");
        String newAction = (String)clientEvent.getParameters().get("payload");
        this.setAction(newAction);
    }

    public void setPoLinesTable(RichTable poLinesTable) {
        this.poLinesTable = poLinesTable;
    }

    public RichTable getPoLinesTable() {
        return poLinesTable;
    }

    public void setPoHistoryTable(RichTable poHistoryTable) {
        this.poHistoryTable = poHistoryTable;
    }

    public RichTable getPoHistoryTable() {
        return poHistoryTable;
    }

    public void setPoForm(RichPanelFormLayout poForm) {
        this.poForm = poForm;
    }

    public RichPanelFormLayout getPoForm() {
        return poForm;
    }

    public void setCancelButtonRendered(boolean cancelButtonRendered) {
        this.cancelButtonRendered = cancelButtonRendered;
    }

    public boolean isCancelButtonRendered() {
        cancelButtonRendered =
                JSFUtils.resolveExpressionAsBoolean("#{(pageFlowScope.fromMenu eq 'normal' and (bindings.State.inputValue eq '1' or bindings.State.inputValue eq '2' or bindings.State.inputValue eq '4' or bindings.State.inputValue eq '8'))" +
                                                    " or (pageFlowScope.fromMenu ne 'query' and sessionScope.LoginUserBean.isUserInRole['采购审核'] and bindings.State.inputValue ne '1' and bindings.State.inputValue ne '2')}");
        return cancelButtonRendered;
    }

    private boolean submitterIsDeptSuporvisor(String submitterId) {
        return isSubmitterInRole(submitterId, Constants.ROLE_PO_DEPT_VERIFIER);
    }

    private boolean submitterIsVerifier(String submitterId) {
        return isSubmitterInRole(submitterId, Constants.ROLE_PO_VERIFIER);
    }

    private boolean submitterIsApprover(String submitterId) {
        return isSubmitterInRole(submitterId, Constants.ROLE_PO_APPROVER);
    }

    private boolean submitterIs2ndApprover(String submitterId) {
        return isSubmitterInRole(submitterId, Constants.ROLE_PO_2ND_APPROVER);
    }

    private boolean isSubmitterInRole(String submitterId, String roleName) {
        OperationBinding oper = ADFUtils.findOperation("isUserInRole");
        oper.getParamsMap().put("userId", submitterId);
        oper.getParamsMap().put("roleName", roleName);
        oper.execute();
        boolean result = (Boolean)oper.getResult();

        return result;
    }

    public void setReceiveQuantityReadonly(boolean receiveQuantityReadonly) {
        this.receiveQuantityReadonly = receiveQuantityReadonly;
    }

    public boolean isReceiveQuantityReadonly() {
        receiveQuantityReadonly =
                JSFUtils.resolveExpressionAsBoolean("#{pageFlowScope.fromMenu eq 'query' or bindings.State.inputValue ne '5' or (not (pageFlowScope.fromMenu eq 'receiver')) or row.bindings.Cancelled.inputValue eq true or row.bindings.PurchaseQuantity.inputValue eq 0}");

        return receiveQuantityReadonly;
    }

    public void setActualPriceReadonly(boolean actualPriceReadonly) {
        this.actualPriceReadonly = actualPriceReadonly;
    }

    public boolean isActualPriceReadonly() {
        actualPriceReadonly =
                JSFUtils.resolveExpressionAsBoolean("#{pageFlowScope.fromMenu eq 'query' or bindings.State.inputValue ne '5' or (not (pageFlowScope.fromMenu eq 'buyer')) or row.bindings.Cancelled.inputValue eq true or row.bindings.PurchaseQuantity.inputValue eq 0}");
        return actualPriceReadonly;
    }

    private void setPurchaseQuantityAndComputeTotal() {
        DCIteratorBinding lineIt = ADFUtils.findIterator("PurchaseOrderLinesViewIterator");
        lineIt.setRangeSize(-1);
        Row[] rows = lineIt.getAllRowsInRange();
        if (rows != null && rows.length > 0) {
            for (Row row : rows) {
                String isCancelled = (String)row.getAttribute("Cancelled");
                //已取消或者采购数量为0的行不需要设置ActualPrice
                if (!"Y".equals(isCancelled)) {
                    row.setAttribute("PurchaseQuantity", row.getAttribute("SubmitQuantity"));
                }
            }
        }

        computeTotal("SubmitPrice", "PurchaseQuantity", null, "VerifyTotal");
    }

    private String getDeptMgrId(String submitterId) {
        OperationBinding oper = ADFUtils.findOperation("getDeptMgrId");
        oper.getParamsMap().put("submitterId", submitterId);
        oper.execute();
        String result = (String)oper.getResult();
        if (result != null) {
            return result;
        }

        return "";
    }

    private boolean checkAssignedBuyer() {
        String buyerName = (String)ADFUtils.getBoundAttributeValue("CurrentBuyerName");
        System.err.println("buyerName is: " + buyerName);
        if (buyerName != null) {
            return true;
        }
        return false;
    }
}

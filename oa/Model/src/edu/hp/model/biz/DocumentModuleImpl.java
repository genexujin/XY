package edu.hp.model.biz;

import edu.hp.model.vo.DocumentPublishViewImpl;
import edu.hp.model.vo.DocumentTaskViewImpl;

import edu.hp.model.vo.EmployeesViewImpl;

import edu.hp.model.vo.GroupsViewImpl;

import edu.hp.model.vo.query.admin.EmployeeQueryByDisplayNameViewImpl;

import edu.hp.model.vo.query.security.UserGroupQueryImpl;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jul 11 13:48:30 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DocumentModuleImpl extends ApplicationModuleImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public DocumentModuleImpl() {
    }


    /**
     * Container's getter for DocumentHistory.
     * @return DocumentHistory
     */
    public ViewObjectImpl getDocumentHistory() {
        return (ViewObjectImpl)findViewObject("DocumentHistory");
    }

    /**
     * Container's getter for DocumentTask.
     * @return DocumentTask
     */
    public DocumentTaskViewImpl getDocumentTask() {
        return (DocumentTaskViewImpl)findViewObject("DocumentTask");
    }

    /**
     * Container's getter for DeptTasks.
     * @return DeptTasks
     */
    public ViewObjectImpl getDeptTasks() {
        return (ViewObjectImpl)findViewObject("DeptTasks");
    }

    /**
     * Container's getter for DocumentDeptTask.
     * @return DocumentDeptTask
     */
    public ViewObjectImpl getDocumentDeptTask() {
        return (ViewObjectImpl)findViewObject("DocumentDeptTask");
    }

    /**
     * Container's getter for HistoryOfDeptDoc.
     * @return HistoryOfDeptDoc
     */
    public ViewObjectImpl getHistoryOfDeptDoc() {
        return (ViewObjectImpl)findViewObject("HistoryOfDeptDoc");
    }

    /**
     * Container's getter for History.
     * @return History
     */
    public ViewObjectImpl getHistory() {
        return (ViewObjectImpl)findViewObject("History");
    }

    /**
     * Container's getter for Doc2DeptViewLink.
     * @return Doc2DeptViewLink
     */
    public ViewLinkImpl getDoc2DeptViewLink() {
        return (ViewLinkImpl)findViewLink("Doc2DeptViewLink");
    }

    /**
     * Container's getter for DocDept2HistoryViewLink.
     * @return DocDept2HistoryViewLink
     */
    public ViewLinkImpl getDocDept2HistoryViewLink() {
        return (ViewLinkImpl)findViewLink("DocDept2HistoryViewLink");
    }

    /**
     * Container's getter for DocDept2HistoryViewLink1.
     * @return DocDept2HistoryViewLink1
     */
    public ViewLinkImpl getDocDept2HistoryViewLink1() {
        return (ViewLinkImpl)findViewLink("DocDept2HistoryViewLink1");
    }

    /**
     * Container's getter for GroupsView1.
     * @return GroupsView1
     */
    public ViewObjectImpl getGroups() {
        return (ViewObjectImpl)findViewObject("Groups");
    }

    /**
     * Container's getter for EmployeesView1.
     * @return EmployeesView1
     */
    public EmployeesViewImpl getEmpOfGrps() {
        return (EmployeesViewImpl)findViewObject("EmpOfGrps");
    }

    /**
     * Container's getter for UserGrpViewLink1.
     * @return UserGrpViewLink1
     */
    public ViewLinkImpl getUserGrpViewLink1() {
        return (ViewLinkImpl)findViewLink("UserGrpViewLink1");
    }

    /**
     * Container's getter for DocumentPublishView1.
     * @return DocumentPublishView1
     */
    public DocumentPublishViewImpl getDocumentPublish() {
        return (DocumentPublishViewImpl)findViewObject("DocumentPublish");
    }

    /**
     * Container's getter for UserGroupsView1.
     * @return UserGroupsView1
     */
    public ViewObjectImpl getUserGroups() {
        return (ViewObjectImpl)findViewObject("UserGroups");
    }

    /**
     * Container's getter for GroupUserViewLink1.
     * @return GroupUserViewLink1
     */
    public ViewLinkImpl getGroupUserViewLink1() {
        return (ViewLinkImpl)findViewLink("GroupUserViewLink1");
    }

    /**
     * Container's getter for EmployeeQueryByDisplayNameView1.
     * @return EmployeeQueryByDisplayNameView1
     */
    public ViewObjectImpl getEmpsQuery() {
        return (ViewObjectImpl)findViewObject("EmpsQuery");
    }

    /**
     * Container's getter for UserGroupsView1.
     * @return UserGroupsView1
     */
    public ViewObjectImpl getUserGroupsView() {
        return (ViewObjectImpl)findViewObject("UserGroupsView");
    }

    /**
     * Container's getter for UserGroupQuery1.
     * @return UserGroupQuery1
     */
    public UserGroupQueryImpl getUserGroupQuery() {
        return (UserGroupQueryImpl)findViewObject("UserGroupQuery");
    }
}

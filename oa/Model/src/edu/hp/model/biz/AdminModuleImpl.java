package edu.hp.model.biz;

import edu.hp.model.vo.EmployeesViewImpl;
import edu.hp.model.vo.ItemCategoryApprovalViewImpl;
import edu.hp.model.vo.LovsViewImpl;
import edu.hp.model.vo.RoleMenusViewImpl;
import edu.hp.model.vo.RolesViewImpl;
import edu.hp.model.vo.query.security.UserMenuQueryImpl;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Feb 12 15:24:52 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AdminModuleImpl extends ApplicationModuleImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public AdminModuleImpl() {
    }

    /**
     * Container's getter for Menus.
     * @return Menus
     */
    public ViewObjectImpl getMenus() {
        return (ViewObjectImpl)findViewObject("Menus");
    }

    /**
     * Container's getter for RoleUsers.
     * @return RoleUsers
     */
    public ViewObjectImpl getRoleUsers() {
        return (ViewObjectImpl)findViewObject("RoleUsers");
    }

    /**
     * Container's getter for Roles.
     * @return Roles
     */
    public RolesViewImpl getRoles() {
        return (RolesViewImpl)findViewObject("Roles");
    }

    /**
     * Container's getter for Employees.
     * @return Employees
     */
    public ViewObjectImpl getEmployees() {
        return (ViewObjectImpl)findViewObject("Employees");
    }

    /**
     * Container's getter for Subordinates.
     * @return Subordinates
     */
    public ViewObjectImpl getSubordinates() {
        return (ViewObjectImpl)findViewObject("Subordinates");
    }

    /**
     * Container's getter for UserMenus.
     * @return UserMenus
     */
    public UserMenuQueryImpl getUserMenus() {
        return (UserMenuQueryImpl)findViewObject("UserMenus");
    }

    /**
     * Container's getter for LovsView.
     * @return LovsView
     */
    public ViewObjectImpl getLovsView() {
        return (ViewObjectImpl)findViewObject("LovsView");
    }

    /**
     * Container's getter for Locations.
     * @return Locations
     */
    public ViewObjectImpl getLocations() {
        return (ViewObjectImpl)findViewObject("Locations");
    }

    /**
     * Container's getter for Departments.
     * @return Departments
     */
    public ViewObjectImpl getDepartments() {
        return (ViewObjectImpl)findViewObject("Departments");
    }

    /**
     * Container's getter for MenusOfRole.
     * @return MenusOfRole
     */
    public RoleMenusViewImpl getMenusOfRole() {
        return (RoleMenusViewImpl)findViewObject("MenusOfRole");
    }

    /**
     * Container's getter for RoleMenus.
     * @return RoleMenus
     */
    public RoleMenusViewImpl getRoleMenus() {
        return (RoleMenusViewImpl)findViewObject("RoleMenus");
    }

    /**
     * Container's getter for RolesOfUser.
     * @return RolesOfUser
     */
    public ViewObjectImpl getRolesOfUser() {
        return (ViewObjectImpl)findViewObject("RolesOfUser");
    }

    /**
     * Container's getter for EmpMgrViewLink.
     * @return EmpMgrViewLink
     */
    public ViewLinkImpl getEmpMgrViewLink() {
        return (ViewLinkImpl)findViewLink("EmpMgrViewLink");
    }

    /**
     * Container's getter for RoleMenuViewLink1.
     * @return RoleMenuViewLink1
     */
    public ViewLinkImpl getRoleMenuViewLink1() {
        return (ViewLinkImpl)findViewLink("RoleMenuViewLink1");
    }

    /**
     * Container's getter for UserRoleDMLViewLink1.
     * @return UserRoleDMLViewLink1
     */
    public ViewLinkImpl getUserRoleDMLViewLink1() {
        return (ViewLinkImpl)findViewLink("UserRoleDMLViewLink1");
    }

    /**
     * Container's getter for ClassroomView1.
     * @return ClassroomView1
     */
    public ViewObjectImpl getClassrooms() {
        return (ViewObjectImpl)findViewObject("Classrooms");
    }


    /**
     * Container's getter for ConferenceRoomsView1.
     * @return ConferenceRoomsView1
     */
    public ViewObjectImpl getConferenceRooms() {
        return (ViewObjectImpl)findViewObject("ConferenceRooms");
    }

    /**
     * Container's getter for VehicleView1.
     * @return VehicleView1
     */
    public ViewObjectImpl getVehicles() {
        return (ViewObjectImpl)findViewObject("Vehicles");
    }

    /**
     * Container's getter for HdResult1.
     * @return HdResult1
     */
    public ViewObjectImpl getHdResult() {
        return (ViewObjectImpl)findViewObject("HdResult");
    }

    /**
     * Container's getter for ReasonLevel1_1.
     * @return ReasonLevel1_1
     */
    public ViewObjectImpl getReasonLevel1() {
        return (ViewObjectImpl)findViewObject("ReasonLevel1");
    }

    /**
     * Container's getter for ReasonLevel2_1.
     * @return ReasonLevel2_1
     */
    public ViewObjectImpl getReasonLevel2() {
        return (ViewObjectImpl)findViewObject("ReasonLevel2");
    }

    /**
     * Container's getter for ReasonLevel3_1.
     * @return ReasonLevel3_1
     */
    public ViewObjectImpl getReasonLevel3() {
        return (ViewObjectImpl)findViewObject("ReasonLevel3");
    }

    /**
     * Container's getter for HdReasonLevel2ToLevel3Link1.
     * @return HdReasonLevel2ToLevel3Link1
     */
    public ViewLinkImpl getHdReasonLevel2ToLevel3Link1() {
        return (ViewLinkImpl)findViewLink("HdReasonLevel2ToLevel3Link1");
    }

    /**
     * Container's getter for ReasonLevel2_1.
     * @return ReasonLevel2_1
     */
    public ViewObjectImpl getReasonLevel2_1() {
        return (ViewObjectImpl)findViewObject("ReasonLevel2_1");
    }

    /**
     * Container's getter for HdReasonLevel1ToLevel2Link1.
     * @return HdReasonLevel1ToLevel2Link1
     */
    public ViewLinkImpl getHdReasonLevel1ToLevel2Link1() {
        return (ViewLinkImpl)findViewLink("HdReasonLevel1ToLevel2Link1");
    }

    /**
     * Container's getter for ItemCategory1.
     * @return ItemCategory1
     */
    public ViewObjectImpl getItemCategory() {
        return (ViewObjectImpl)findViewObject("ItemCategory");
    }

    /**
     * Container's getter for ItemLov1.
     * @return ItemLov1
     */
    public ViewObjectImpl getItemLov() {
        return (ViewObjectImpl)findViewObject("ItemLov");
    }

    /**
     * Container's getter for PoCatgToSubCatgLink1.
     * @return PoCatgToSubCatgLink1
     */
    public ViewLinkImpl getPoCatgToSubCatgLink1() {
        return (ViewLinkImpl)findViewLink("PoCatgToSubCatgLink1");
    }

    /**
     * Container's getter for ItemCategoryApprovalView1.
     * @return ItemCategoryApprovalView1
     */
    public ItemCategoryApprovalViewImpl getItemCategoryApprovalView() {
        return (ItemCategoryApprovalViewImpl)findViewObject("ItemCategoryApprovalView");
    }

    /**
     * Container's getter for EmployeesView1.
     * @return EmployeesView1
     */
    public EmployeesViewImpl getEmpOfRoles() {
        return (EmployeesViewImpl)findViewObject("EmpOfRoles");
    }

    /**
     * Container's getter for UserRoleViewLink1.
     * @return UserRoleViewLink1
     */
    public ViewLinkImpl getUserRoleViewLink1() {
        return (ViewLinkImpl)findViewLink("UserRoleViewLink1");
    }
}

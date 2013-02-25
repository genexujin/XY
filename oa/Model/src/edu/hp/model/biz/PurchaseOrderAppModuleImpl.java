package edu.hp.model.biz;

import edu.hp.model.biz.common.PurchaseOrderAppModule;
import edu.hp.model.vo.EmployeesViewImpl;
import edu.hp.model.vo.ItemCategoryApprovalViewImpl;
import edu.hp.model.vo.PurchaseOrderHistorysViewImpl;
import edu.hp.model.vo.PurchaseOrderLinesViewImpl;
import edu.hp.model.vo.PurchaseOrdersViewImpl;

import edu.hp.model.vo.query.po.EmpWithEmptyImpl;

import edu.hp.model.vo.query.po.PoStateWithEmptyImpl;

import java.math.BigDecimal;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Feb 07 21:05:32 CST 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PurchaseOrderAppModuleImpl extends ApplicationModuleImpl implements PurchaseOrderAppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public PurchaseOrderAppModuleImpl() {
    }
    
    public void findByUserId(String submitterId) {
        PurchaseOrdersViewImpl po = this.getPurchaseOrdersView();
        po.setApplyViewCriteriaNames(null);
        
        //Do the query        
        System.err.println("In AppModule: submitterId is: " + submitterId);
        if (submitterId != null) {
            po.setsbmtId(submitterId);
            ViewCriteria sIdCriteria = po.getViewCriteria("SubmitterIdCriteria");
            po.setApplyViewCriteriaName(sIdCriteria.getName());
            po.executeQuery();            
            
            //Set the lov's value, so on page the correct user will be selected by default in the lov
            EmpWithEmptyImpl eLov = (EmpWithEmptyImpl)this.getEmpWithEmpty();
            eLov.setApplyViewCriteriaNames(null);
            eLov.setsubmiterId(submitterId);
            ViewCriteria empCriteria = eLov.getViewCriteria("findBySubmitterIdCriteria");
            eLov.setApplyViewCriteriaName(empCriteria.getName());
            eLov.executeQuery();
        }
    }

    public void findByPoId(String poId) {
        PurchaseOrdersViewImpl po = this.getPurchaseOrdersView();
        po.setApplyViewCriteriaNames(null);
        
        //Do the query        
        System.err.println("In AppModule: poId is: " + poId);
        if (poId != null) {
            po.setOrdId(poId);
            ViewCriteria poIdCriteria = po.getViewCriteria("OrderIdCriteria");
            po.setApplyViewCriteriaName(poIdCriteria.getName());
            po.executeQuery();
        }
    }

    public void newPo(String userId) {
        System.err.println("In PO AppModule. Creating new PO. SubmitterId is: " + userId);
        PurchaseOrdersViewImpl poView = this.getPurchaseOrdersView();
        Row po = poView.createRow();
        po.setAttribute("State", "1");
        po.setAttribute("SubmitterId", userId);
        poView.insertRow(po);
        poView.setCurrentRow(po);
        
        System.err.println("Also creating a new PO Line.");
        PurchaseOrderLinesViewImpl poLineView = this.getPurchaseOrderLinesView();
        Row poLine = poLineView.createRow();
        poLine.setAttribute("State", 1);
        poLine.setAttribute("OrderId", po.getAttribute("OrderId"));
        poLineView.insertRow(poLine);
        poLineView.setCurrentRow(poLine);
    }
    
    public BigDecimal getApprovalLimitForCategoryId(String categoryId) {
        ItemCategoryApprovalViewImpl vo = (ItemCategoryApprovalViewImpl)this.getItemCategoryApprovalView();
        vo.setApplyViewCriteriaNames(null);
        
        if (categoryId != null) {
            vo.setCategoryId(categoryId);
            ViewCriteria vc = vo.getViewCriteria("ItemCategoryIdCriteria");
            vo.setApplyViewCriteriaName(vc.getName());
            vo.executeQuery();
            Row[] rows = vo.getAllRowsInRange();
            if (rows != null && rows.length > 0) {
                return (BigDecimal)rows[0].getAttribute("ApprovalLimit");
            }
        }
        
        return new BigDecimal(0);
    }

    /**
     * Container's getter for PurchaseOrdersView.
     * @return PurchaseOrdersView
     */
    public PurchaseOrdersViewImpl getPurchaseOrdersView() {
        return (PurchaseOrdersViewImpl)findViewObject("PurchaseOrdersView");
    }

    /**
     * Container's getter for ItemCategory.
     * @return ItemCategory
     */
    public ViewObjectImpl getItemCategory() {
        return (ViewObjectImpl)findViewObject("ItemCategory");
    }

    /**
     * Container's getter for PurchaseOrderLinesView.
     * @return PurchaseOrderLinesView
     */
    public PurchaseOrderLinesViewImpl getPurchaseOrderLinesView() {
        return (PurchaseOrderLinesViewImpl)findViewObject("PurchaseOrderLinesView");
    }

    /**
     * Container's getter for PoState.
     * @return PoState
     */
    public ViewObjectImpl getPoState() {
        return (ViewObjectImpl)findViewObject("PoState");
    }

    /**
     * Container's getter for PoLineCount.
     * @return PoLineCount
     */
    public ViewObjectImpl getPoLineCount() {
        return (ViewObjectImpl)findViewObject("PoLineCount");
    }

    /**
     * Container's getter for EmployeesViewForLOV.
     * @return EmployeesViewForLOV
     */
    public ViewObjectImpl getEmployeesViewForLOV() {
        return (ViewObjectImpl)findViewObject("EmployeesViewForLOV");
    }


    /**
     * Container's getter for PoToPoLineLink1.
     * @return PoToPoLineLink1
     */
    public ViewLinkImpl getPoToPoLineLink1() {
        return (ViewLinkImpl)findViewLink("PoToPoLineLink1");
    }

    /**
     * Container's getter for PoToPoLineCountLink.
     * @return PoToPoLineCountLink
     */
    public ViewLinkImpl getPoToPoLineCountLink() {
        return (ViewLinkImpl)findViewLink("PoToPoLineCountLink");
    }

    /**
     * Container's getter for ItemCategoryWithEmpty1.
     * @return ItemCategoryWithEmpty1
     */
    public ViewObjectImpl getItemCategoryWithEmpty() {
        return (ViewObjectImpl)findViewObject("ItemCategoryWithEmpty");
    }

    /**
     * Container's getter for PoStateWithEmpty1.
     * @return PoStateWithEmpty1
     */
    public ViewObjectImpl getPoStateWithEmpty() {
        return (ViewObjectImpl)findViewObject("PoStateWithEmpty");
    }

    /**
     * Container's getter for EmpWithEmpty1.
     * @return EmpWithEmpty1
     */
    public ViewObjectImpl getEmpWithEmpty() {
        return (ViewObjectImpl)findViewObject("EmpWithEmpty");
    }

    /**
     * Container's getter for ItemCategoryApprovalView1.
     * @return ItemCategoryApprovalView1
     */
    public ViewObjectImpl getItemCategoryApprovalView() {
        return (ViewObjectImpl)findViewObject("ItemCategoryApprovalView");
    }

    /**
     * Container's getter for PurchaseOrderHistorysView1.
     * @return PurchaseOrderHistorysView1
     */
    public PurchaseOrderHistorysViewImpl getPurchaseOrderHistorysView() {
        return (PurchaseOrderHistorysViewImpl)findViewObject("PurchaseOrderHistorysView");
    }

    /**
     * Container's getter for PoToPoHistoryLink1.
     * @return PoToPoHistoryLink1
     */
    public ViewLinkImpl getPoToPoHistoryLink1() {
        return (ViewLinkImpl)findViewLink("PoToPoHistoryLink1");
    }
}

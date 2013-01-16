package com.xy.scpms.view.page;

import com.xy.scpms.model.query.InvoiceNotificaitonViewImpl;
import com.xy.scpms.model.query.PaymentNotificationImpl;
import com.xy.view.utils.ADFUtils;

import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.QueryEvent;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.domain.Number;

public class PaymentNoticeBean {
    
    private Number low;
    private Number high;
    private RichTable table;
    private RichPopup contractDetailPopup;
    
    public void openContract(ClientEvent clientEvent) {
        
        contractDetailPopup.cancel();
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.contractDetailPopup.show(hints);
        
        
    }
    public PaymentNoticeBean() {
    }

    public void onQuery(QueryEvent queryEvent) {
        ADFUtils.findOperation("setPaymentNotification").execute();
        //执行query
        ADFUtils.invokeQueryEventMethodExpression("#{bindings.queryFormQuery.processQuery}",
                                         queryEvent);
    }
    
    /**
     * 重新执行查询
     */
    private void reexecuteView() {
        //得到iterator binding的引用
        DCIteratorBinding binding =
            ADFUtils.findIterator("PaymentNotificationIterator");
        //得到bind的viewobject,并为view object的bind variable赋值
        PaymentNotificationImpl vo =
            (PaymentNotificationImpl)binding.getViewObject();

        if (low != null)
            vo.setlowDayLimit(low);

        if (high != null)
            vo.sethighDayLimit(high);

        vo.executeQuery();

        AdfFacesContext.getCurrentInstance().addPartialTarget(table);

    }


    public void below30(ActionEvent actionEvent) {
        this.setHigh(new Number(30));
        this.setLow(new Number(-9999999));
        //执行query
        reexecuteView();
    }

    public void btw31and60(ActionEvent actionEvent) {
        this.setHigh(new Number(60));
        this.setLow(new Number(31));
        reexecuteView();
    }

    public void btw61and90(ActionEvent actionEvent) {
        this.setHigh(new Number(90));
        this.setLow(new Number(61));
        reexecuteView();
    }

    public void all(ActionEvent actionEvent) {
        this.setHigh(new Number(9999999));
        this.setLow(new Number(-9999999));
        reexecuteView();
    }


    public void abv91(ActionEvent actionEvent) {
        this.setHigh(new Number(9999999));
        this.setLow(new Number(91));
        reexecuteView();
    }

    public void setLow(Number low) {
        this.low = low;
    }

    public Number getLow() {
        return low;
    }

    public void setHigh(Number high) {
        this.high = high;
    }

    public Number getHigh() {
        return high;
    }

    public void setTable(RichTable table) {
        this.table = table;
    }

    public RichTable getTable() {
        return table;
    }

 

    public void setContractDetailPopup(RichPopup contractDetailPopup) {
        this.contractDetailPopup = contractDetailPopup;
    }

    public RichPopup getContractDetailPopup() {
        return contractDetailPopup;
    }
}

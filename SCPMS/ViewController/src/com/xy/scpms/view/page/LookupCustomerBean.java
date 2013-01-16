package com.xy.scpms.view.page;

import com.xy.view.utils.ADFUtils;

import java.io.Serializable;

import java.util.Map;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

public class LookupCustomerBean implements Serializable{
  private RichTable customerTable;

  public LookupCustomerBean() {
  }

  public String select() {
    CollectionModel model = (CollectionModel)customerTable.getValue();
    JUCtrlHierBinding tableBinding = (JUCtrlHierBinding)model.getWrappedData();
    //table synchronizes row selection with current binding row
    DCIteratorBinding tableIterator = tableBinding.getDCIteratorBinding();
    if (tableIterator.getCurrentRow() != null) {
      Object customerId = tableIterator.getCurrentRow().getAttribute("Id");
      Object customerName =
        tableIterator.getCurrentRow().getAttribute("CustomerName");

      ADFUtils.setBoundAttributeValue("AuthName", customerName);
      ADFUtils.setBoundAttributeValue("CustomerId", customerId);

    }
    return "return";
  }

  public void setCustomerTable(RichTable customerTable) {
    this.customerTable = customerTable;
  }

  public RichTable getCustomerTable() {
    return customerTable;
  }

  public String cancel() {
    return "return";
  }
}

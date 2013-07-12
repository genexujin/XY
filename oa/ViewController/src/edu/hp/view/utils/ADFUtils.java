package edu.hp.view.utils;


import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.model.binding.DCParameter;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.LaunchPopupEvent;
import oracle.adf.view.rich.event.QueryEvent;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.ControlBinding;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;
import oracle.jbo.uicli.binding.JUCtrlValueBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


/**
 * A series of convenience functions for dealing with ADF Bindings.
 * Note: Updated for JDeveloper 11
 *
 * @author Duncan Mills
 * @author Steve Muench
 *
 * $Id: ADFUtils.java 2513 2007-09-20 20:39:13Z ralsmith $.
 */
public class ADFUtils {

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(ADFUtils.class);
    
    //传入主键，返回当前迭代器中主键相符的 Row
    public static Row findByKey(String id, String iteratorName) {
        DCIteratorBinding it = ADFUtils.findIterator(iteratorName);
        Key key = new Key(new Object[] { id });
        RowSetIterator rsi = it.getRowSetIterator();
        Row row = rsi.findByKey(key, 1)[0];
        return row;
    }
    
    public static Date now(){
        return new Date(new java.sql.Date(System.currentTimeMillis()));
    }
    
    //传入主键，返回当前迭代器中主键相符的 Row
    public static void makeCurrent(String id, String iteratorName) {
        DCIteratorBinding it = ADFUtils.findIterator(iteratorName);
        Key key = new Key(new Object[] { id });
        RowSetIterator rsi = it.getRowSetIterator();
        Row row = rsi.findByKey(key, 1)[0];
        rsi.setCurrentRow(row);
    }

    /**
     * ensure the calling page has a binding operation called Commit.
     */
    public static boolean commit(String msg, String errMsg) {
        boolean success = true;
        try {
            OperationBinding operation = findOperation("Commit");
            operation.execute();
            if (operation.getErrors().isEmpty()) {
                JSFUtils.addFacesInformationMessage(msg);
            } else {
                List errors = operation.getErrors();
                for (Object error : errors) {
                    System.err.println(error);
                }
                JSFUtils.addFacesErrorMessage(errMsg);
                success = false;
            }
        } catch (Exception e) {
            success = false;
            JSFUtils.addFacesErrorMessage(errMsg);
            e.printStackTrace();
        }

        return success;
    }

    public static boolean commit() {
        boolean success = true;
        try {
            OperationBinding operation = findOperation("Commit");
            operation.execute();
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }

    public static void createInsert(String errMsg) {

        try {
            OperationBinding operation = findOperation("CreateInsert");
            operation.execute();
            if (!operation.getErrors().isEmpty()) {
                JSFUtils.addFacesErrorMessage(errMsg);
            }
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage(errMsg);
            e.printStackTrace();
        }


    }

    public static void rollBack() {
        try {
            OperationBinding operation = findOperation("Rollback");
            operation.execute();
            if (!operation.getErrors().isEmpty())
                JSFUtils.addFacesErrorMessage("回退异常，请联系管理员！");
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage("回退异常，请联系管理员！");
            e.printStackTrace();
        }
    }

    /**  invoke a binding query **/
    public static void invokeQueryEventMethodExpression(String expression, QueryEvent queryEvent) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        System.out.println();
        ELContext elctx = fctx.getELContext();
        ExpressionFactory efactory = fctx.getApplication().getExpressionFactory();
        MethodExpression me =
            efactory.createMethodExpression(elctx, expression, Object.class, new Class[] { QueryEvent.class });
        me.invoke(elctx, new Object[] { queryEvent });
    }

    /** * Synchronizes the table UI component row selection with the * selection in the ADF binding layer * @param selectionEvent event object created by the table * component upon row selection */
    public static void makeCurrent(SelectionEvent selectionEvent) {
        RichTable _table = (RichTable)selectionEvent.getSource();
        //the Collection Model is the object that provides the
        //structured data for the table to render
        CollectionModel _tableModel = (CollectionModel)_table.getValue();
        //the ADF object that implements the CollectionModel is
        ////JUCtrlHierBinding. It is wrapped by the CollectionModel API
        JUCtrlHierBinding _adfTableBinding = (JUCtrlHierBinding)_tableModel.getWrappedData();
        //Acess the ADF iterator binding that is used with
        //ADF table binding
        DCIteratorBinding _tableIteratorBinding = _adfTableBinding.getDCIteratorBinding();
        //the role of this method is to synchronize the table component
        //selection with the selection in the ADF model
        Object _selectedRowData = _table.getSelectedRowData();
        //cast to JUCtrlHierNodeBinding, which is the ADF object that represents a row
        JUCtrlHierNodeBinding _nodeBinding = (JUCtrlHierNodeBinding)_selectedRowData;
        //get the row key from the node binding and set it as the current row in the iterator
        Key _rwKey = _nodeBinding.getRowKey();
        _tableIteratorBinding.setCurrentRowWithKey(_rwKey.toStringFormat(true));
    }

    public static void makeCurrent(RichTable _table) {

        //the Collection Model is the object that provides the
        //structured data for the table to render
        CollectionModel _tableModel = (CollectionModel)_table.getValue();
        //the ADF object that implements the CollectionModel is
        ////JUCtrlHierBinding. It is wrapped by the CollectionModel API
        JUCtrlHierBinding _adfTableBinding = (JUCtrlHierBinding)_tableModel.getWrappedData();
        //Acess the ADF iterator binding that is used with
        //ADF table binding
        DCIteratorBinding _tableIteratorBinding = _adfTableBinding.getDCIteratorBinding();
        //the role of this method is to synchronize the table component
        //selection with the selection in the ADF model
        Object _selectedRowData = _table.getSelectedRowData();
        //cast to JUCtrlHierNodeBinding, which is the ADF object that represents a row
        JUCtrlHierNodeBinding _nodeBinding = (JUCtrlHierNodeBinding)_selectedRowData;
        //get the row key from the node binding and set it as the current row in the iterator
        Key _rwKey = _nodeBinding.getRowKey();
        _tableIteratorBinding.setCurrentRowWithKey(_rwKey.toStringFormat(true));
    }


    public static void partialRefreshComponenet(UIComponent newTarget) {
        AdfFacesContext.getCurrentInstance().addPartialTarget(newTarget);
    }

    /**
     * inputLovPopupId = <af:inputlistofvalues id> + _afrLovPopupId
     * @param launchPopupEvent
     * @param inputLovPopupId
     * @param hintLaunchId
     * @param hintAlignId
     * @param hintAlign : {AdfRichPopup.ALIGN_BEFORE_END,AdfRichPopup.ALIGN_AFTER_END,etc}
     */
    public static void onLovLaunch(LaunchPopupEvent launchPopupEvent, String inputLovPopupId, String hintLaunchId,
                                   String hintAlignId, String hintAlign) {

        FacesContext context = JSFUtils.getFacesContext();
        ExtendedRenderKitService erks = Service.getService(context.getRenderKit(), ExtendedRenderKitService.class);
        //create the JavaScript and invoke it on the client. The af:form id is "f1"
        StringBuffer scriptBuf = new StringBuffer();
        //        scriptBuf.append("var afForm = AdfPage.PAGE.findComponentByAbsoluteId(\"f1\");");
        //        scriptBuf.append("AdfCustomEvent.queue(afForm,\"inputLOVPostionSetter\",{},true);");
        scriptBuf.append("var popup = AdfPage.PAGE.findComponentByAbsoluteId(\"" + inputLovPopupId + "\");");
        //        scriptBuf.append("if (!popup.isPopupVisible()){");
        scriptBuf.append("var hints = {};");
        scriptBuf.append("hints[AdfRichPopup.HINT_LAUNCH_ID] = \"" + hintLaunchId + "\";");
        scriptBuf.append("hints[AdfRichPopup.HINT_ALIGN_ID] =  \"" + hintAlignId + "\"; ");
        scriptBuf.append("hints[AdfRichPopup.HINT_ALIGN] =" + hintAlign + ";");
        scriptBuf.append("popup.show(hints);");
        erks.addScript(context, scriptBuf.toString());


    }

    /**
     * Get application module for an application module data control by name.
     * @param name application module data control name
     * @return ApplicationModule
     */
    public static ApplicationModule getApplicationModuleForDataControl(String name) {
        return (ApplicationModule)JSFUtils.resolveExpression("#{data." + name + ".dataProvider}");
    }

    /**
     * A convenience method for getting the value of a bound attribute in the
     * current page context programatically.
     * @param attributeName of the bound value in the pageDef
     * @return value of the attribute
     */
    public static Object getBoundAttributeValue(String attributeName) {
        return findControlBinding(attributeName).getInputValue();
    }

    /**
     * A convenience method for setting the value of a bound attribute in the
     * context of the current page.
     * @param attributeName of the bound value in the pageDef
     * @param value to set
     */
    public static void setBoundAttributeValue(String attributeName, Object value) {
        findControlBinding(attributeName).setInputValue(value);
    }

    /**
     * Returns the evaluated value of a pageDef parameter.
     * @param pageDefName reference to the page definition file of the page with the parameter
     * @param parameterName name of the pagedef parameter
     * @return evaluated value of the parameter as a String
     */
    public static Object getPageDefParameterValue(String pageDefName, String parameterName) {
        BindingContainer bindings = findBindingContainer(pageDefName);
        DCParameter param = ((DCBindingContainer)bindings).findParameter(parameterName);
        return param.getValue();
    }

    /**
     * Convenience method to find a DCControlBinding as an AttributeBinding
     * to get able to then call getInputValue() or setInputValue() on it.
     * @param bindingContainer binding container
     * @param attributeName name of the attribute binding.
     * @return the control value binding with the name passed in.
     *
     */
    public static AttributeBinding findControlBinding(BindingContainer bindingContainer, String attributeName) {
        if (attributeName != null) {
            if (bindingContainer != null) {
                ControlBinding ctrlBinding = bindingContainer.getControlBinding(attributeName);
                if (ctrlBinding instanceof AttributeBinding) {
                    return (AttributeBinding)ctrlBinding;
                }
            }
        }
        return null;
    }

    /**
     * Convenience method to find a DCControlBinding as a JUCtrlValueBinding
     * to get able to then call getInputValue() or setInputValue() on it.
     * @param attributeName name of the attribute binding.
     * @return the control value binding with the name passed in.
     *
     */
    public static AttributeBinding findControlBinding(String attributeName) {
        return findControlBinding(getBindingContainer(), attributeName);
    }

    /**
     * Return the current page's binding container.
     * @return the current page's binding container
     */
    public static BindingContainer getBindingContainer() {
        return (BindingContainer)JSFUtils.resolveExpression("#{bindings}");

        //Maybe another way to do this is:
        //BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    /**
     * Return the Binding Container as a DCBindingContainer.
     * @return current binding container as a DCBindingContainer
     */
    public static DCBindingContainer getDCBindingContainer() {
        return (DCBindingContainer)getBindingContainer();
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     *
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     *
     * @param iteratorName ADF iterator binding name
     * @param valueAttrName name of the value attribute to use
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsForIterator(String iteratorName, String valueAttrName,
                                                          String displayAttrName) {
        return selectItemsForIterator(findIterator(iteratorName), valueAttrName, displayAttrName);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with description.
     *
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     *
     * @param iteratorName ADF iterator binding name
     * @param valueAttrName name of the value attribute to use
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute to use for description
     * @return ADF Faces SelectItem for an iterator binding with description
     */
    public static List<SelectItem> selectItemsForIterator(String iteratorName, String valueAttrName,
                                                          String displayAttrName, String descriptionAttrName) {
        return selectItemsForIterator(findIterator(iteratorName), valueAttrName, displayAttrName, descriptionAttrName);
    }

    /**
     * Get List of attribute values for an iterator.
     * @param iteratorName ADF iterator binding name
     * @param valueAttrName value attribute to use
     * @return List of attribute values for an iterator
     */
    public static List attributeListForIterator(String iteratorName, String valueAttrName) {
        return attributeListForIterator(findIterator(iteratorName), valueAttrName);
    }

    /**
     * Get List of Key objects for rows in an iterator.
     * @param iteratorName iterabot binding name
     * @return List of Key objects for rows
     */
    public static List<Key> keyListForIterator(String iteratorName) {
        return keyListForIterator(findIterator(iteratorName));
    }

    /**
     * Get List of Key objects for rows in an iterator.
     * @param iter iterator binding
     * @return List of Key objects for rows
     */
    public static List<Key> keyListForIterator(DCIteratorBinding iter) {
        List<Key> attributeList = new ArrayList<Key>();
        for (Row r : iter.getAllRowsInRange()) {
            attributeList.add(r.getKey());
        }
        return attributeList;
    }

    /**
     * Get List of Key objects for rows in an iterator using key attribute.
     * @param iteratorName iterator binding name
     * @param keyAttrName name of key attribute to use
     * @return List of Key objects for rows
     */
    public static List<Key> keyAttrListForIterator(String iteratorName, String keyAttrName) {
        return keyAttrListForIterator(findIterator(iteratorName), keyAttrName);
    }

    /**
     * Get List of Key objects for rows in an iterator using key attribute.
     *
     * @param iter iterator binding
     * @param keyAttrName name of key attribute to use
     * @return List of Key objects for rows
     */
    public static List<Key> keyAttrListForIterator(DCIteratorBinding iter, String keyAttrName) {
        List<Key> attributeList = new ArrayList<Key>();
        for (Row r : iter.getAllRowsInRange()) {
            attributeList.add(new Key(new Object[] { r.getAttribute(keyAttrName) }));
        }
        return attributeList;
    }

    /**
     * Get a List of attribute values for an iterator.
     *
     * @param iter iterator binding
     * @param valueAttrName name of value attribute to use
     * @return List of attribute values
     */
    public static List attributeListForIterator(DCIteratorBinding iter, String valueAttrName) {
        List attributeList = new ArrayList();
        for (Row r : iter.getAllRowsInRange()) {
            attributeList.add(r.getAttribute(valueAttrName));
        }
        return attributeList;
    }

    /**
     * Find an iterator binding in the current binding container by name.
     *
     * @param name iterator binding name
     * @return iterator binding
     */
    public static DCIteratorBinding findIterator(String name) {
        DCIteratorBinding iter = getDCBindingContainer().findIteratorBinding(name);
        if (iter == null) {
            throw new RuntimeException("Iterator '" + name + "' not found");
        }
        return iter;
    }

    /**
     * @param bindingContainer
     * @param iterator
     * @return
     */
    public static DCIteratorBinding findIterator(String bindingContainer, String iterator) {
        DCBindingContainer bindings = (DCBindingContainer)JSFUtils.resolveExpression("#{" + bindingContainer + "}");
        if (bindings == null) {
            throw new RuntimeException("Binding container '" + bindingContainer + "' not found");
        }
        DCIteratorBinding iter = bindings.findIteratorBinding(iterator);
        if (iter == null) {
            throw new RuntimeException("Iterator '" + iterator + "' not found");
        }
        return iter;
    }

    /**
     * @param name
     * @return
     */
    public static JUCtrlValueBinding findCtrlBinding(String name) {
        JUCtrlValueBinding rowBinding = (JUCtrlValueBinding)getDCBindingContainer().findCtrlBinding(name);
        if (rowBinding == null) {
            throw new RuntimeException("CtrlBinding " + name + "' not found");
        }
        return rowBinding;
    }

    /**
     * Find an operation binding in the current binding container by name.
     *
     * @param name operation binding name
     * @return operation binding
     */
    public static OperationBinding findOperation(String name) {
        OperationBinding op = getDCBindingContainer().getOperationBinding(name);
        if (op == null) {
            throw new RuntimeException("Operation '" + name + "' not found");
        }
        return op;
    }

    /**
     * Find an operation binding in the current binding container by name.
     *
     * @param bindingContianer binding container name
     * @param opName operation binding name
     * @return operation binding
     */
    public static OperationBinding findOperation(String bindingContianer, String opName) {
        DCBindingContainer bindings = (DCBindingContainer)JSFUtils.resolveExpression("#{" + bindingContianer + "}");
        if (bindings == null) {
            throw new RuntimeException("Binding container '" + bindingContianer + "' not found");
        }
        OperationBinding op = bindings.getOperationBinding(opName);
        if (op == null) {
            throw new RuntimeException("Operation '" + opName + "' not found");
        }
        return op;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with description.
     *
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     *
     * @param iter ADF iterator binding
     * @param valueAttrName name of value attribute to use for key
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute for description
     * @return ADF Faces SelectItem for an iterator binding with description
     */
    public static List<SelectItem> selectItemsForIterator(DCIteratorBinding iter, String valueAttrName,
                                                          String displayAttrName, String descriptionAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getAttribute(valueAttrName), (String)r.getAttribute(displayAttrName),
                                           (String)r.getAttribute(descriptionAttrName)));
        }
        return selectItems;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     *
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     *
     * @param iter ADF iterator binding
     * @param valueAttrName name of value attribute to use for key
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsForIterator(DCIteratorBinding iter, String valueAttrName,
                                                          String displayAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getAttribute(valueAttrName), (String)r.getAttribute(displayAttrName)));
        }
        return selectItems;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     *
     * Uses the rowKey of each row as the SelectItem key.
     *
     * @param iteratorName ADF iterator binding name
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsByKeyForIterator(String iteratorName, String displayAttrName) {
        return selectItemsByKeyForIterator(findIterator(iteratorName), displayAttrName);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with discription.
     *
     * Uses the rowKey of each row as the SelectItem key.
     *
     * @param iteratorName ADF iterator binding name
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute for description
     * @return ADF Faces SelectItem for an iterator binding with discription
     */
    public static List<SelectItem> selectItemsByKeyForIterator(String iteratorName, String displayAttrName,
                                                               String descriptionAttrName) {
        return selectItemsByKeyForIterator(findIterator(iteratorName), displayAttrName, descriptionAttrName);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with discription.
     *
     * Uses the rowKey of each row as the SelectItem key.
     *
     * @param iter ADF iterator binding
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute for description
     * @return ADF Faces SelectItem for an iterator binding with discription
     */
    public static List<SelectItem> selectItemsByKeyForIterator(DCIteratorBinding iter, String displayAttrName,
                                                               String descriptionAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getKey(), (String)r.getAttribute(displayAttrName),
                                           (String)r.getAttribute(descriptionAttrName)));
        }
        return selectItems;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     *
     * Uses the rowKey of each row as the SelectItem key.
     *
     * @param iter ADF iterator binding
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return List of ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsByKeyForIterator(DCIteratorBinding iter, String displayAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getKey(), (String)r.getAttribute(displayAttrName)));
        }
        return selectItems;
    }

    /**
     * Find the BindingContainer for a page definition by name.
     *
     * Typically used to refer eagerly to page definition parameters. It is
     * not best practice to reference or set bindings in binding containers
     * that are not the one for the current page.
     *
     * @param pageDefName name of the page defintion XML file to use
     * @return BindingContainer ref for the named definition
     */
    private static BindingContainer findBindingContainer(String pageDefName) {
        BindingContext bctx = getDCBindingContainer().getBindingContext();
        BindingContainer foundContainer = bctx.findBindingContainer(pageDefName);
        return foundContainer;
    }

    /**
     * @param opList
     */
    public static void printOperationBindingExceptions(List opList) {
        if (opList != null && !opList.isEmpty()) {
            for (Object error : opList) {
                LOGGER.severe(error.toString());
            }
        }
    }
}

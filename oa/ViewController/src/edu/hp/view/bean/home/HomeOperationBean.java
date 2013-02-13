package edu.hp.view.bean.home;

import edu.hp.model.common.Constants;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.component.UIXIterator;


public class HomeOperationBean {
    private UIXIterator taskIterator;
    private UIXIterator notesIterator;
    private int rangeSize = 10;
    private int selectDefault = 0;
    private String defaultTaskState = "PENDING";
    private String defaultNoteState = "UNREAD";

    public HomeOperationBean() {
    }

    public void setNotesIterator(UIXIterator notesIterator) {
        this.notesIterator = notesIterator;
    }

    public UIXIterator getNotesIterator() {
        return notesIterator;
    }

    public void setTaskIterator(UIXIterator taskIterator) {
        this.taskIterator = taskIterator;
    }

    public UIXIterator getTaskIterator() {
        return taskIterator;
    }


    public void refreshNotes(ActionEvent actionEvent) {
        ADFUtils.findIterator("UserNotificationsIterator").executeQuery();
        this.getNotesIterator().setFirst(0);
    }

    public void refreshTaskList(ActionEvent actionEvent) {
        ADFUtils.findIterator("UserTasksIterator").executeQuery();
        this.getTaskIterator().setFirst(0);
    }

    public void firstTask(ActionEvent actionEvent) {
        this.getTaskIterator().setFirst(0);
    }

    public void lastTask(ActionEvent actionEvent) {
        int rowFactor = this.getTaskIterator().getRowCount() / rangeSize;
        int rowCount = rowFactor * rangeSize;
        if (rowCount == this.getTaskIterator().getRowCount()) {
            rowCount -= rangeSize;
        }
        this.getTaskIterator().setFirst(rowCount);
    }

    public void previousTask(ActionEvent actionEvent) {
        this.getTaskIterator().setFirst(this.getTaskIterator().getFirst() - rangeSize);
    }

    public void nextTask(ActionEvent actionEvent) {
        this.getTaskIterator().setFirst(this.getTaskIterator().getFirst() + rangeSize);
    }

    public boolean isBeforeTaskDisabled() {
        return this.getTaskIterator().getFirst() == 0;
    }

    public boolean isAfterTaskDisabled() {
        return this.getTaskIterator().getFirst() >= this.getTaskIterator().getRowCount() - rangeSize;
    }

    public void firstNotes(ActionEvent actionEvent) {
        this.getNotesIterator().setFirst(0);
    }

    public void previousNotes(ActionEvent actionEvent) {
        this.getNotesIterator().setFirst(this.getTaskIterator().getFirst() - rangeSize);
    }

    public void nextNotes(ActionEvent actionEvent) {
        this.getNotesIterator().setFirst(this.getTaskIterator().getFirst() + rangeSize);
    }

    public void lastNotes(ActionEvent actionEvent) {
        int rowFactor = this.getNotesIterator().getRowCount() / rangeSize;
        int rowCount = rowFactor * rangeSize;
        if (rowCount == this.getNotesIterator().getRowCount()) {
            rowCount -= rangeSize;
        }
        this.getNotesIterator().setFirst(rowCount);
    }

    public boolean isBeforeNoteDisabled() {
        return this.getNotesIterator().getFirst() == 0;
    }

    public boolean isAfterNoteDisabled() {
        return this.getNotesIterator().getFirst() >= this.getNotesIterator().getRowCount() - rangeSize;
    }

    public void onTaskSateChange(ValueChangeEvent valueChangeEvent) {
        String newValue = (String)valueChangeEvent.getNewValue();

        if (newValue != null && newValue.equals(Constants.STATE_TASK_COMPLETED)) {
            OperationBinding binding = ADFUtils.findOperation("setForCompleted");
            binding.execute();
        } else if (newValue != null && newValue.equals(Constants.STATE_TASK_PENDING)) {
            OperationBinding binding = ADFUtils.findOperation("setForPending");
            binding.execute();
        }
        this.getTaskIterator().setFirst(0);
        ADFUtils.partialRefreshComponenet(getTaskIterator());
    }

    public void onNoteStateChange(ValueChangeEvent valueChangeEvent) {
        String newValue = (String)valueChangeEvent.getNewValue();

        if (newValue != null && newValue.equals(Constants.STATE_NOTE_READ)) {
            OperationBinding binding = ADFUtils.findOperation("setForRead");
            binding.execute();
        } else if (newValue != null && newValue.equals(Constants.STATE_NOTE_UNREAD)) {
            OperationBinding binding = ADFUtils.findOperation("setForUnread");
            binding.execute();
        }
        this.getNotesIterator().setFirst(0);
        ADFUtils.partialRefreshComponenet(getNotesIterator());
    }


    public void openTask(ActionEvent actionEvent) {
        UIComponent component = actionEvent.getComponent();
        String contextObjectId = (String)component.getAttributes().get("contextObjectId");
        //System.err.println(contextObjectId);
        String contextObjectType = (String)component.getAttributes().get("contextObjectType");
        //System.err.println(contextObjectType);
        JSFUtils.setExpressionValue("#{sessionScope.contextObjectId}", contextObjectId);
        JSFUtils.setExpressionValue("#{sessionScope.contextObjectType}", contextObjectType);
        FacesContext context = JSFUtils.getFacesContext();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "openTask");
        // Add event code here...
    }

    public void setSelectDefault(int selectDefault) {
        this.selectDefault = selectDefault;
    }

    public int getSelectDefault() {
        return selectDefault;
    }

    public void setDefaultNoteState(String defaultNoteState) {
        this.defaultNoteState = defaultNoteState;
    }

    public String getDefaultNoteState() {
        return defaultNoteState;
    }

    public void setDefaultTaskState(String defaultTaskState) {
        this.defaultTaskState = defaultTaskState;
    }

    public String getDefaultTaskState() {
        return defaultTaskState;
    }

    public void markAsRead(ActionEvent actionEvent) {
        UIComponent component = actionEvent.getComponent();
        String id = (String)component.getAttributes().get("noteId");
        //System.err.println(id);
        if(id!=null){
            OperationBinding op = ADFUtils.findOperation("markAsRead");
            op.getParamsMap().put("id", id);
            op.execute();
            OperationBinding commit = ADFUtils.findOperation("Commit");
            commit.execute();
            refreshNotes(actionEvent);
        }
    }
}

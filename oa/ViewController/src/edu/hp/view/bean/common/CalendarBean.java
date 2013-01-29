package edu.hp.view.bean.common;

import edu.hp.view.security.LoginUser;
import edu.hp.view.utils.ADFUtils;
import edu.hp.view.utils.JSFUtils;

import java.awt.Color;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.datatransfer.DataFlavor;
import oracle.adf.view.rich.datatransfer.Transferable;
import oracle.adf.view.rich.dnd.CalendarDropSite;
import oracle.adf.view.rich.dnd.DnDAction;
import oracle.adf.view.rich.event.CalendarActivityEvent;
import oracle.adf.view.rich.event.DropEvent;
import oracle.adf.view.rich.event.TriggerType;
import oracle.adf.view.rich.model.CalendarActivity;
import oracle.adf.view.rich.model.CalendarProvider;
import oracle.adf.view.rich.util.CalendarActivityRamp;
import oracle.adf.view.rich.util.InstanceStyles;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Array;


public class CalendarBean {


    protected List<OACalendarProvider> _providerList;
    protected Map<OACalendarProvider, ProviderData> _providerData;
    protected boolean myView = false;
    protected String calendarid;
    protected OACalendarActivity _currActivity;
    protected static List<Color> _defaultOrderProviderColors;
    protected Map<Set<String>, InstanceStyles> _activityStyles;
    protected List _colorData = CalendarActivityRamp.getRampColorKeys();
    protected Array showClsRmList;
    protected String calendarIteratorName;
    protected String providerIteratorName;
    protected String locationIteratorName;
    protected String refreshCalendarOptName;
    protected String refreshCalendarParamName;
    protected String providerIdCol = "Id";
    protected String providerDisplayNameCol = "Value";
    protected String providerLocCol = "FlexCol1";
    protected String providerNumOfPplCol = "FlexCol2";
    protected String superAdminRole = "系统管理员";
    protected String moduleAdminRole;
    protected String locationIdFieldName;

    public CalendarBean() {
    }

    protected void setCurrentLocation(int id) {

        DCIteratorBinding it = ADFUtils.findIterator(locationIteratorName);
        it.setCurrentRowIndexInRange(id);

    }

    protected void reload() {
        try {
            //Set<String>[] tags = _modelBean.getTags();
            List<Color> defaultOrderProviderColors = getDefaultOrderProviderColors();

            DCIteratorBinding it = ADFUtils.findIterator(providerIteratorName);
            if (it != null) {

                it.setRangeSize(-1);
                it.executeQuery();
                Row[] allRowsInRange = it.getAllRowsInRange();

                if (allRowsInRange != null && allRowsInRange.length > 0) {
                    _providerList = new ArrayList<OACalendarProvider>();
                    for (Row row : allRowsInRange) {
                        OACalendarProvider prvd =
                            new OACalendarProvider(row.getAttribute(providerIdCol).toString(), (String)row.getAttribute(providerDisplayNameCol));
                        prvd.setEnabled(CalendarProvider.Enabled.DISABLED);
                        prvd.setNoOfPpl((String)row.getAttribute(providerNumOfPplCol));
                        //System.err.println(prvd.getDisplayName());
                        _providerList.add(prvd);
                    }


                    _activityStyles = new HashMap<Set<String>, InstanceStyles>(_providerList.size());
                    _providerData = new HashMap<OACalendarProvider, ProviderData>();

                    for (int i = 0; i < _providerList.size(); i++) {
                        Color color = defaultOrderProviderColors.get(i % defaultOrderProviderColors.size());
                        InstanceStyles styles = CalendarActivityRamp.getActivityRamp(color);
                        Set<String> tags = new CopyOnWriteArraySet<String>();
                        OACalendarProvider provider = _providerList.get(i);
                        // TODO - should I be able to look this up instead of creating it?
                        tags.add(provider.getId());
                        _activityStyles.put(tags, styles);
                        ProviderData data = new ProviderData(provider, color);
                        _providerData.put(provider, data);
                    }

                }
            }

            UIComponent calendar = JSFUtils.findComponentInRoot(calendarid);
            if (calendar != null)
                refreshCalendar(calendar);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void activityListener(CalendarActivityEvent ae) {


        CalendarActivity activity = ae.getCalendarActivity();

        if (activity == null) {
            setCurrActivity(null);
        } else {
            setCurrActivity(new OACalendarActivity(activity));
            //System.err.println(this.getCurrActivity().getId());
        }


        if (!ae.getTriggerType().equals(TriggerType.HOVER)) {
            UIComponent calendar = JSFUtils.findComponentInRoot(calendarid);
            ADFUtils.partialRefreshComponenet(calendar);
        }

    }


    protected void colorChange(ValueChangeEvent vce) {

        UIComponent component = vce.getComponent();
        String providerId = component.getAttributes().get("providerId").toString();
        // TODO - should I be creating this every time? used to use (getModelBean().getTags()[0]
        Set<String> providerSet = new CopyOnWriteArraySet<String>();
        providerSet.add(providerId);


        Color newColor = (Color)vce.getNewValue();
        InstanceStyles styles = CalendarActivityRamp.getActivityRamp(newColor);

        _activityStyles.put(providerSet, styles);

        UIComponent calendar = JSFUtils.findComponent(vce.getComponent().getNamingContainer(), calendarid);
        ADFUtils.partialRefreshComponenet(calendar);


    }

    protected void providerChange(ValueChangeEvent valueChangeEvent) {

        UIComponent component = valueChangeEvent.getComponent();
        String providerId = component.getAttributes().get("providerId").toString();

        Boolean newVal = (Boolean)valueChangeEvent.getNewValue();

        for (OACalendarProvider provider : _providerList) {

            if (provider.getId().equals(providerId)) {
                if (newVal)
                    provider.setEnabled(CalendarProvider.Enabled.ENABLED);
                else
                    provider.setEnabled(CalendarProvider.Enabled.DISABLED);
            }
        }

        UIComponent calendar =
            JSFUtils.findComponent(valueChangeEvent.getComponent().getNamingContainer(), calendarid);

        refreshCalendar(calendar);
    }


    protected void refreshCalendar(UIComponent calendar) {

        StringBuffer clsRmNos = new StringBuffer();

        for (OACalendarProvider provider : _providerList) {

            if (provider.getEnabled().equals(CalendarProvider.Enabled.ENABLED)) {
                clsRmNos.append(provider.getId() + ",");
            }
        }

        if (clsRmNos.length() > 0) {
            clsRmNos.deleteCharAt(clsRmNos.length() - 1);
        } else {
            clsRmNos.append("NA");
        }

        OperationBinding refreshOp = ADFUtils.findOperation(refreshCalendarOptName);

        refreshOp.getParamsMap().put(refreshCalendarParamName, clsRmNos.toString());

        refreshOp.execute();

        ADFUtils.partialRefreshComponenet(calendar);
    }

    public DnDAction handleDrop(DropEvent dropEvent) {
        if (isEditable()) {
            //System.err.println("editable!");
            Transferable transferable = dropEvent.getTransferable();
            CalendarDropSite dropSite = (CalendarDropSite)dropEvent.getDropSite();
            Date dropSiteDate = dropSite.getDate();
            CalendarActivity.TimeType timeType = dropSite.getTimeType();

            CalendarActivity activity =
                (CalendarActivity)transferable.getData(DataFlavor.getDataFlavor(CalendarActivity.class));


            _handleCalendarActivityDrop(dropEvent, dropSiteDate, activity);

            return dropEvent.getProposedAction();
        } else{
            //System.err.println("not editbale!");
            return DnDAction.NONE;
        }
    }

    protected Boolean ensureTimeConflicts(java.sql.Timestamp actStartTime, java.sql.Timestamp actEndTime,
                                          String clsRmId, String actId) {

        Boolean result = false;
        OperationBinding binding = ADFUtils.findOperation("ifConflict");
        binding.getParamsMap().put("actStartTime", actStartTime);
        binding.getParamsMap().put("actEndTime", actEndTime);
        binding.getParamsMap().put("clsRmId", clsRmId);
        binding.getParamsMap().put("actId", actId);
        binding.execute();
        result = (Boolean)binding.getResult();
        return result;

    }

    protected void _handleCalendarActivityDrop(DropEvent dropEvent, Date dropSiteDate, CalendarActivity activity) {
        OACalendarActivity movingActivity = new OACalendarActivity(activity);

        // If this is a timed event
        Date startDate = movingActivity.getFrom();

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar dropCal = Calendar.getInstance();
        dropCal.setTime(dropSiteDate);

        int startDayOfYear = startCal.get(Calendar.DAY_OF_YEAR);
        int startHour = startCal.get(Calendar.HOUR_OF_DAY);
        int startMin = startCal.get(Calendar.MINUTE);
        int dropDayOfYear = dropCal.get(Calendar.DAY_OF_YEAR);
        int dropHour = dropCal.get(Calendar.HOUR_OF_DAY);
        int dropMin = dropCal.get(Calendar.MINUTE);
        Date _proposedStartDate;

        // move the start date to the new time
        if (startDayOfYear != dropDayOfYear) {
            startCal.set(Calendar.DAY_OF_YEAR, dropDayOfYear);
            startCal.set(Calendar.YEAR, dropCal.get(Calendar.YEAR));
            startCal.set(Calendar.MONTH, dropCal.get(Calendar.MONTH));
        }

        // move this activity to the new location
        if (movingActivity.isAllDay()) {
            _proposedStartDate = startCal.getTime();

            // Get the original start day
            startCal = Calendar.getInstance();
            startCal.setTime(movingActivity.getFrom());

            // Calcuate new end day by using the new start day and the original delta
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(movingActivity.getTo());


            long delta = endCal.getTime().getTime() - startCal.getTime().getTime();
            Date endDate = new Date(_proposedStartDate.getTime() + delta);


            Boolean hasNoConflict =
                ensureTimeConflicts(new Timestamp(_proposedStartDate.getTime()), new Timestamp(endDate.getTime()),
                                    (String)(movingActivity.getCustomAttributes().get(this.locationIdFieldName)),
                                    movingActivity.getId());
            //System.err.println("here " + hasNoConflict);
            if (hasNoConflict)
                // update to the new start and end day
                doUpdateCalendar(movingActivity, _proposedStartDate, endDate);
            else
                JSFUtils.addFacesErrorMessage("该时间段已经有其他预订，无法创建新的预订，请更换时间段！");


        } else {
            String view = ((RichCalendar)dropEvent.getDropComponent()).getView();

            if ((RichCalendar.VIEW_DAY.equals(view) || RichCalendar.VIEW_WEEK.equals(view)) &&
                CalendarActivity.TimeType.TIME.equals(activity.getTimeType())) {
                if (startHour != dropHour)
                    startCal.set(Calendar.HOUR_OF_DAY, dropHour);

                if (dropMin != startMin) {
                    if (dropMin == 0 && startMin >= 30)
                        startCal.add(Calendar.MINUTE, -30);
                    else if (dropMin == 30 && startMin < 30)
                        startCal.add(Calendar.MINUTE, 30);
                }
            }

            Date endDate = movingActivity.getTo();
            long delta = endDate.getTime() - startDate.getTime();

            startDate = startCal.getTime();
            endDate = new Date(startDate.getTime() + delta);

            Boolean hasNoConflict =
                ensureTimeConflicts(new Timestamp(startDate.getTime()), new Timestamp(endDate.getTime()),
                                    (String)(movingActivity.getCustomAttributes().get(locationIdFieldName)),
                                    movingActivity.getId());
            //System.err.println("here " + hasNoConflict);
            if (hasNoConflict)
                doUpdateCalendar(movingActivity, startDate, endDate);
            else
                JSFUtils.addFacesErrorMessage("该时间段已经有其他预订，无法创建新的预订，请更换时间段！");

        }


    }

    public boolean isEditable() {

        boolean result = false;

        if (this.getCurrActivity() != null) {
            LoginUser user = (LoginUser)JSFUtils.resolveExpression("#{sessionScope.LoginUserBean}");

            if (user.getIsUserInRole().get(this.superAdminRole) != null)
                return true;
            if (user.getIsUserInRole().get(moduleAdminRole) != null)
                return true;

            String userId = this.getCurrActivity().getUserId();
            if (userId.equals(user.getUserName())) {

                return true;
            }
        }

        return result;

    }

    protected void doUpdateCalendar(OACalendarActivity activity, Date newStart, Date newEnd) {
        //do nothing
    }

    /**
     * @return The default order of colors for providers
     */
    static public List<Color> getDefaultOrderProviderColors() {
        return _defaultOrderProviderColors;
    }

    public void setProviderList(List<OACalendarProvider> _providerList) {
        this._providerList = _providerList;
    }

    public List<OACalendarProvider> getProviderList() {
        return _providerList;
    }

    public void setProviderData(Map<OACalendarProvider, CalendarBean.ProviderData> _providerData) {
        this._providerData = _providerData;
    }

    public Map<OACalendarProvider, CalendarBean.ProviderData> getProviderData() {
        return _providerData;
    }

    public void setCurrActivity(OACalendarActivity _currActivity) {
        this._currActivity = _currActivity;
    }

    public OACalendarActivity getCurrActivity() {
        return _currActivity;
    }

    public void setMyView(boolean myView) {
        this.myView = myView;
    }

    public boolean isMyView() {
        return myView;
    }

    public static class ProviderData implements Serializable {

        public ProviderData(OACalendarProvider provider, Color color) {
            _provider = provider;
            _color = color;

        }

        public void setColor(Color _color) {
            this._color = _color;
        }

        public Color getColor() {
            return _color;
        }

        public boolean isEnabled() {
            return _provider.getEnabled().equals(OACalendarProvider.Enabled.ENABLED);
        }

        public void setEnabled(boolean enabled) {
            if (enabled)
                _provider.setEnabled(OACalendarProvider.Enabled.ENABLED);
            else
                _provider.setEnabled(OACalendarProvider.Enabled.DISABLED);
        }

        private OACalendarProvider _provider;
        private Color _color;
    }

    static {
        _defaultOrderProviderColors = new ArrayList<Color>(12);

        CalendarActivityRamp redRamp = CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.RED);
        CalendarActivityRamp orangeRamp = CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.ORANGE);
        CalendarActivityRamp blueRamp = CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.BLUE);
        CalendarActivityRamp greenRamp = CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.GREEN);
        CalendarActivityRamp goldRamp = CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.GOLD);
        CalendarActivityRamp tealRamp = CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.TEAL);
        CalendarActivityRamp lavendarRamp =
            CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.LAVENDAR);
        CalendarActivityRamp seaweedRamp = CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.SEAWEED);
        CalendarActivityRamp indigoRamp = CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.INDIGO);
        CalendarActivityRamp plumRamp = CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.PLUM);
        CalendarActivityRamp limeRamp = CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.LIME);
        CalendarActivityRamp midnightblueRamp =
            CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.MIDNIGHTBLUE);

        _defaultOrderProviderColors.add(redRamp.getRepresentativeColor());
        _defaultOrderProviderColors.add(orangeRamp.getRepresentativeColor());
        _defaultOrderProviderColors.add(blueRamp.getRepresentativeColor());
        _defaultOrderProviderColors.add(greenRamp.getRepresentativeColor());
        _defaultOrderProviderColors.add(goldRamp.getRepresentativeColor());
        _defaultOrderProviderColors.add(tealRamp.getRepresentativeColor());
        _defaultOrderProviderColors.add(lavendarRamp.getRepresentativeColor());
        _defaultOrderProviderColors.add(seaweedRamp.getRepresentativeColor());
        _defaultOrderProviderColors.add(indigoRamp.getRepresentativeColor());
        _defaultOrderProviderColors.add(plumRamp.getRepresentativeColor());
        _defaultOrderProviderColors.add(limeRamp.getRepresentativeColor());
        _defaultOrderProviderColors.add(midnightblueRamp.getRepresentativeColor());
    }


    public void setShowClsRmList(Array showClsRmList) {
        this.showClsRmList = showClsRmList;
    }

    public Array getShowClsRmList() {
        return showClsRmList;
    }


    public void setActivityStyles(Map<Set<String>, InstanceStyles> _activityStyles) {
        this._activityStyles = _activityStyles;
    }

    public Map<Set<String>, InstanceStyles> getActivityStyles() {
        return _activityStyles;
    }

    public void setColorData(List _colorData) {
        this._colorData = _colorData;
    }

    public List getColorData() {
        return _colorData;
    }
}

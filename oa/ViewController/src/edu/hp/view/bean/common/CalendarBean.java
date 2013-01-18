package edu.hp.view.bean.common;

import edu.hp.view.utils.utils.ADFUtils;

import java.awt.Color;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;
import oracle.adf.view.rich.model.CalendarProvider;
import oracle.adf.view.rich.util.CalendarActivityRamp;
import oracle.adf.view.rich.util.InstanceStyles;

import oracle.jbo.Row;
import oracle.jbo.domain.Array;

import org.apache.myfaces.trinidad.context.RequestContext;


public class CalendarBean{

    private List<OACalendarProvider> _providerList;
    private Map<OACalendarProvider, ProviderData> _providerData;
    private RichCalendar calendar;
    protected static List<Color> _defaultOrderProviderColors;
    private Map<Set<String>, InstanceStyles> _activityStyles;
    private List _colorData = CalendarActivityRamp.getRampColorKeys();
    protected Array showClsRmList;
    protected String providerIteratorName;
    protected String locationIteratorName;
    protected String providerIdCol = "Id";
    protected String providerDisplayNameCol = "Value";
    protected String providerLocCol = "FlexCol1";
    protected String providerNumOfPplCol = "FlexCol2";

    public CalendarBean() {
    }
    
    protected void setCurrentLocation(int id){
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
                    _providerData =  new HashMap<OACalendarProvider, ProviderData>();

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
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }
    
    protected void doCommit(){
        ADFUtils.commit("预订保存成功！","预订保存失败，请核对输入的信息或联系管理员！");        
    }
    
    protected void doRollback(){
        ADFUtils.rollBack();     
    }
    
    public void createPopupListener(PopupFetchEvent popupFetchEvent) {
        ADFUtils.createInsert("创建教室预订失败，请联系管理员！");
    }

    public void createDialogListener(DialogEvent dialogEvent) {
        if(dialogEvent.getOutcome().equals(dialogEvent.Outcome.ok)){
            doCommit();
        }else{
            doRollback();
        }
    }
    
    protected void createListener(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
    }
    
    protected void colorChange(ValueChangeEvent vce){
        
        UIComponent component = vce.getComponent();
        String providerId = component.getAttributes().get("providerId").toString();
        // TODO - should I be creating this every time? used to use (getModelBean().getTags()[0]
        Set <String> providerSet = new CopyOnWriteArraySet<String>();
        providerSet.add(providerId);


        Color newColor = (Color)vce.getNewValue();
        InstanceStyles styles = CalendarActivityRamp.getActivityRamp(newColor);

        _activityStyles.put(providerSet, styles);

        RequestContext adfContext = RequestContext.getCurrentInstance();
        adfContext.addPartialTarget(calendar);
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

    public void setCalendar(RichCalendar calendar) {
        this.calendar = calendar;
    }

    public RichCalendar getCalendar() {
        return calendar;
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
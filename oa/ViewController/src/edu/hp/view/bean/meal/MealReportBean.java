package edu.hp.view.bean.meal;

import edu.hp.view.utils.ADFUtils;

import java.util.Calendar;
import java.util.Date;

import oracle.binding.OperationBinding;

public class MealReportBean {
    public MealReportBean() {
    }
    
    private String location = "LY";
    private int duration = 5;
    private Date startDate = new Date(); 

    public String runReport() throws Exception{
//        System.err.println(location);
//        System.err.println(duration);
//        System.err.println(startDate);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_YEAR, duration);
        Date endDate = cal.getTime();
        
        oracle.jbo.domain.Date stDt = new oracle.jbo.domain.Date(new java.sql.Date(startDate.getTime()));
        oracle.jbo.domain.Date edDt = new oracle.jbo.domain.Date(new java.sql.Date(endDate.getTime()));

        OperationBinding binding = ADFUtils.findOperation("runReport");
        binding.getParamsMap().put("location", location);
        binding.getParamsMap().put("startDt", stDt);
        binding.getParamsMap().put("endDt", edDt);
        binding.execute();

        return null;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }
}

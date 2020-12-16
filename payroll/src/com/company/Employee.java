package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Employee {
    private String name;
    private String dob;
    private String rol;
    private String startDate;
    private String startSal;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", rol='" + rol + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startSal='" + startSal + '\'' +
                '}';
    }

    public Employee(String name, String dob, String rol, String startDate, String startSal) {
        this.name = name;
        this.dob = dob;
        this.rol = rol;
        this.startDate = startDate;
        this.startSal = startSal;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getRol() {
        return rol;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartSal() {
        return startSal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setStartSal(String startSal) {
        this.startSal = startSal;
    }

    /**
     * get age of employee
     * @param dob - birthDay of employee
     * @return age of employee
     */
    public int getAge(String dob){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = sdf.parse(dob);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return Calendar.getInstance().get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        } catch (ParseException e) {
           return 0;
        }

    }

    /**
     * calculate working day of employee
     * @param startDate
     * @return
     */
    public long getWorkingDays (String startDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = sdf.parse(startDate);
            long diff =(new Date()).getTime() - date.getTime() ;
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            return 0;
        }
    }
}

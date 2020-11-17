package com.example.jibs;

import java.util.ArrayList;
import java.util.List;

//This class will hold all of the holidays for a given day.
public class HolidayContainer {
    //The day of the month the holidays are on.
    int day;
    //This is the list of holidays.
    List<TempHoilday> holidays = new ArrayList<>();

    //GETTERS AND SETTERS
    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }
    public List<TempHoilday> getHolidays() { return holidays; }
    public void setHolidays(List<TempHoilday> holidays) { this.holidays = holidays; }

    //How to add a holiday without having to know how this class stores the holidays.
    public void addHoliday(TempHoilday holiday) {
        holidays.add(holiday);
    }

}

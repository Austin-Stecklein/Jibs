package com.example.jibs;

import java.util.ArrayList;
import java.util.List;

public class HolidayContainer {
    int day;
    List<TempHoilday> holidays = new ArrayList<>();

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<TempHoilday> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<TempHoilday> holidays) {
        this.holidays = holidays;
    }

    public void addHoliday(TempHoilday holiday) {
        holidays.add(holiday);
    }

}

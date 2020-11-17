package com.example.jibs;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class DataController implements Runnable{
    MainActivity mainActivity;

    public DataController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void run() {
    //This is where the interface with the user save data class will go. For now I will fill it with
        // some random data.

        TempHoilday tempHoliday1 = new TempHoilday("Pizza Day", 1);
        TempHoilday tempHoliday2 = new TempHoilday("Pie Day", 2);
        TempHoilday tempHoliday3 = new TempHoilday("spaceX Day", 3);
        TempHoilday tempHoliday4 = new TempHoilday("Canada Day", 4);

        List<TempHoilday> holidays = new ArrayList<>();
        holidays.add(tempHoliday1);
        holidays.add(tempHoliday2);
        holidays.add(tempHoliday3);
        holidays.add(tempHoliday4);

        List<HolidayContainer> holidayList = new ArrayList<>();

        for(int i = 0; i < 31; i++) {
            holidayList.add(new HolidayContainer());
        }

        for(TempHoilday holiday : holidays) {
            holidayList.get(holiday.getDate() -1).addHoliday(holiday);
        }

        mainActivity.setHolidays(holidayList);
    }
}

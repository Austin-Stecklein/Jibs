package com.example.jibs;

import android.app.Activity;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class DataController implements Runnable{
    MonthView monthView;
    boolean countDown;
    MainActivity mainActivity;

    public DataController(MonthView monthView) {
        this.monthView = monthView;
        this.countDown = false;
    }

    public DataController(MonthView monthView, boolean countDown) {
        this.monthView = monthView;
        this.countDown = countDown;
    }

    public DataController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.countDown = true;
    }

    public DataController(MainActivity mainActivity, boolean countDown) {
        this.mainActivity = mainActivity;
        this.countDown = countDown;
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


        if(countDown) {
            //Interface with the save data controller differently.
            //This is the grab for just count down holidays.
            Log.i("Info", "I made it here");

        }
        else {
            //This is the grab for holidays normally.
        }


        List<HolidayContainer> holidayList = new ArrayList<>();

        //This is filling the holiday list with lists for each day in the month.
        for(int i = 0; i < 31; i++) {
            holidayList.add(new HolidayContainer());
        }

        //This is sorting the holidays into the right spot.
        for(TempHoilday holiday : holidays) {
            holidayList.get(holiday.getDate() -1).addHoliday(holiday);
        }
        //This is setting the list full of the holidays.
        if(countDown)
        {
            mainActivity.setList(holidays);
        }
        else {
            monthView.setHolidays(holidayList);
        }

    }
}

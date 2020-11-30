package com.example.jibs;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonthlyData implements DataController, Runnable {
    MonthView monthView;
    int month;

    public MonthlyData(Activity activity, int month) {
        this.monthView = (MonthView) activity;
        this.month = month;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        //interface for the data.
        //Variables
        String apID = "41cd18df10c447b484da94c8c3ed45e1";
        String country = "US";
        int year = 2020;
        URL url;

        final List<HolidayItem> holidayList;
        List<HolidayItem> tempList;
        Functions functionMonth = new Functions();
        try {
            url = functionMonth.getMonth(apID, country, year, month);
            Functions functionUrl = new Functions();
            Functions functionJson = new Functions();

            holidayList =  new ArrayList<>(Arrays.asList(functionUrl.getData(url)));
            tempList = new ArrayList<>(Arrays.asList(functionJson.readJson(monthView, month))); // Add a filter AUSTIN this line is more important
            holidayList.addAll(tempList);
            final List<HolidayContainer> holidayCList = new ArrayList<>();

            //This is filling the holiday list with lists for each day in the month.
            for(int i = 0; i < 31; i++) {
                holidayCList.add(new HolidayContainer());
            }

            //This is sorting the holidays into the right spot.
            for(HolidayItem holiday : holidayList) {
                holidayCList.get(holiday.getDate_day() -1).addHoliday(holiday);
            }


            monthView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        monthView.setHolidays(holidayCList, month);
                    }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

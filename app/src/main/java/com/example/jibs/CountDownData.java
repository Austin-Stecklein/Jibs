package com.example.jibs;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountDownData implements DataController, Runnable{
    MainActivity mainActivity;
    int month;
    Activity activity;

    public CountDownData(Activity activity, int month) {
        this.mainActivity = (MainActivity) activity;
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
            tempList = new ArrayList<>(Arrays.asList(functionJson.readJson(mainActivity, month))); // Add a filter AUSTIN this line is more important
            holidayList.addAll(tempList);

            //This is setting the list full of the holidays.
            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mainActivity.setList(holidayList); }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

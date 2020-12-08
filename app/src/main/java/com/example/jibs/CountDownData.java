package com.example.jibs;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountDownData implements DataController, Runnable{
    MainActivity mainActivity;
    int month;


    public CountDownData(Activity activity, int month) {
        this.mainActivity = (MainActivity) activity;
        this.month = month;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {




        List<HolidayItem> tempList = new ArrayList<>();
        Functions functionMonth = new Functions();



        UserSaveData userSaveData = new UserSaveData(mainActivity);
        try {
            tempList = userSaveData.getSaveData();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        final List<HolidayItem> holidayList = tempList;

        //This is setting the list full of the holidays.
        mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mainActivity.setList(holidayList); }
            });
    }
}

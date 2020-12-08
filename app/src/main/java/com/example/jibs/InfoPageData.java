package com.example.jibs;

import android.os.Build;
import android.view.View;
import android.widget.Switch;

import androidx.annotation.RequiresApi;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class InfoPageData implements DataController, Runnable{

    private HolidayInfo holidayInfo;
    private HolidayItem holidayItem;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public InfoPageData(HolidayInfo holidayInfo, HolidayItem holidayItem) {
        this.holidayInfo = holidayInfo;
        this.holidayItem = holidayItem;
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void run() {
        int confirm = new UserSaveData(holidayInfo).deleteItem(holidayItem);
        String message = "";
        if(confirm == 0) {
            message = "Saved Data";
        }
        else {
            message = "Save Failed";
        }

        final String finalMessage = message;
        holidayInfo.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Confirmation().onReceive(holidayInfo, finalMessage);
            }
        });

    }



}

package com.example.jibs;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class DataControllerFactory {
    public DataController getController(Activity activity, int month, boolean countDown) {
        if(countDown) {
            return new CountDownData(activity, month);
        }
        else {
            return new MonthlyData(activity, month);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public DataController getController(Activity activity, HolidayItem holidayItem) {
        return new InfoPageData((HolidayInfo) activity, holidayItem);
    }
}

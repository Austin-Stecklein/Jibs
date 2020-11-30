package com.example.jibs;

import android.app.Activity;
import android.content.Context;

public class DataControllerFactory {
    public DataController getController(Activity activity, int month, boolean countDown) {
        if(countDown) {
            return new CountDownData(activity, month);
        }
        else {
            return new MonthlyData(activity, month);
        }
    }
}

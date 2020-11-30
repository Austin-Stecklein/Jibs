package com.example.jibs;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Functions {

    public URL getMonth(String apID, String country, int year, int month) throws IOException {
        URL url = new URL("https://holidays.abstractapi.com/v1/?api_key=" + apID + "&country=" + country + "&year=" + year + "&month=" + month);
        return url;
    }

    public URL getDay(String apID, String country, int year, int month, int day) throws IOException {
        URL url = new URL("https://holidays.abstractapi.com/v1/?api_key=" + apID + "&country=" + country + "&year=" + year + "&month=" + month + "&day=" + day);
        return url;
    }

    public HolidayItem[] getData(URL url) throws IOException {
        HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
        String line = bufferedReader.readLine();

        bufferedReader.close();

        Gson gson = new GsonBuilder().create();
        HolidayItem[] hList = gson.fromJson(line, HolidayItem[].class);
        return hList;
        /*for (HolidayItem holidayItem : hList) {
            System.out.println(holidayItem);
        }*/
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public HolidayItem[] readJson(Context context, int month) throws IOException {
        String fileName = "funHolidays.json";
        String jsonString;

        InputStream inputStream = context.getAssets().open(fileName);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();

        jsonString = new String(buffer, "UTF-8");
        /*HolidayItem that = new HolidayItem("Joe");
        HolidayItem[] hList2 = {that};*/


        Gson gson = new Gson();
            HolidayItem[] hList = gson.fromJson(jsonString, HolidayItem[].class);
            ArrayList<HolidayItem> hList2 = new ArrayList<HolidayItem>();
            for (HolidayItem holidayItem : hList) {
                if (holidayItem.date_month == month) {
                    hList2.add(holidayItem);
                }
            }
            return hList2.toArray(new HolidayItem[0]);
    }
}
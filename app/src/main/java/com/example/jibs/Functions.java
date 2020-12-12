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

    // This grabs the url for the API based on the month that the holidays are in
    public URL getMonth(String apID, String country, int year, int month) throws IOException {
        URL url = new URL("https://holidays.abstractapi.com/v1/?api_key=" + apID + "&country=" + country + "&year=" + year + "&month=" + month);
        return url;
    }

    // This function takes the url and places it into the API to gather the data into an array list
    public HolidayItem[] getData(URL url) throws IOException {
        HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
        String line = bufferedReader.readLine();

        bufferedReader.close();

        Gson gson = new GsonBuilder().create();
        HolidayItem[] hList = gson.fromJson(line, HolidayItem[].class);
        return hList;
    }

    // This goes into the json file and filters through the holidays by month to allow the data connection to grab the holidays into the appropriate month
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

        // This filters the json into months for the data connection
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
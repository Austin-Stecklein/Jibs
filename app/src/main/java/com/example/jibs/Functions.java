package com.example.jibs;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Functions {

    public URL getMonth(String apID, String country, int year, int month) throws IOException {
        URL url = new URL("https://holidays.abstractapi.com/v1/?api_key=" + apID + "&country=" + country + "&year=" + year + "&month=" + month);
        return url;
    }

    public URL getDay(String apID, String country, int year, int month, int day) throws IOException {
        URL url = new URL("https://holidays.abstractapi.com/v1/?api_key=" + apID + "&country=" + country + "&year=" + year + "&month=" + month + "&day=" + day);
        return url;
    }

    public void getData(URL url) throws IOException {
        HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
        String line = bufferedReader.readLine();

        bufferedReader.close();

        Gson gson = new GsonBuilder().create();
        HolidayItem[] hList = gson.fromJson(line, HolidayItem[].class);
        for (HolidayItem holidayItem : hList) {
            System.out.println(holidayItem);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void readJson(int month, int day) {
        Gson gson = new Gson();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("D:\\School\\Fall2020\\CS246_Java\\funHolidays.json"));
            HolidayItem[] hList = gson.fromJson(reader, HolidayItem[].class);
            for (HolidayItem holidayItem : hList) {
                if (holidayItem.date_month == month && day == 0) {
                    System.out.println(holidayItem);
                } else if (holidayItem.date_month == month && holidayItem.date_day == day) {
                    System.out.println(holidayItem);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
package com.example.jibs;

import android.view.View;

public class HolidayItem {
    String name;
    String description;
    String location;
    String type;
    String date;
    int date_year;
    int date_month;
    int date_day;
    String notification;
    int icon;

    public HolidayItem(String name, String description, String location, String type, String date, int date_year, int date_month, int date_day, String notification, int icon) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.date = date;
        this.date_year = date_year;
        this.date_month = date_month;
        this.date_day = date_day;
        this.notification = notification;
        this.icon = icon;
    }

    public int getDate_day() {
        return date_day;
    }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getNotification() { return notification; }


    @Override
    public String toString() {
        return name + ": " + '\n' +
                "Date " + date + '\n';
    }
}
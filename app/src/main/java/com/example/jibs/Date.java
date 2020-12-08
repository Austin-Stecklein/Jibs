package com.example.jibs;

import android.util.Log;

import java.util.Calendar;

public class Date {
    int day;
    int month;
    int year;


    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int daysUntil(Date holiday) {

        int sum = 0;
        int yearDifference = this.year - holiday.year;
        int currentYear = this.year;
        if(yearDifference != 0) {
            for (int i = 0; i < yearDifference - 1; i++) {
                if (isLeapYear(currentYear + i)) {
                    sum += 366;
                } else {
                    sum += 365;
                }
            }
        }
        sum += daysUntilM(holiday);
        sum += holiday.day - this.day;

        return sum;
    }

    private int daysUntilM(Date holiday) {
        int sum = 0;
        if(this.month == holiday.month) {
            return sum;
        }
        else {
            Date date = new Date(this.day, this.month, this.year);
            Date date1 = new Date(holiday.day, holiday.month, this.year);

            if (date.month != date1.month)
            {

                int monthNum = date.month;
                while(monthNum != date1.month) {
                    //Log.i("Loop", "Looping here");
                    switch (monthNum) {
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                        case 1:
                            sum += 31;
                            break;
                        case 2:
                            if(isLeapYear(date.year)) {
                                sum+= 29;
                            }
                            else {
                                sum+= 28;
                            }
                            break;
                        case 6:
                        case 9:
                        case 11:
                        case 4:
                            sum+= 30;
                            break;

                    }
                    monthNum++;
                }
            }
            return sum;
        }
    }
    private boolean isLeapYear(int year) {
        return (((year % 4) == 0) && ((year % 100) == 0) && !((year % 400) == 0));
    }
}




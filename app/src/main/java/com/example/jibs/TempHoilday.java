package com.example.jibs;

//TEMP CLASS USED ONLY TO TEST UNTIL JON IMPORTS HIS CLASS.
public class TempHoilday {
    String name;
    int date;

    public TempHoilday(String name, int date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return
                "Holiday: '" + name + '\n' +
                ", date:" + date +
                '}';
    }
}

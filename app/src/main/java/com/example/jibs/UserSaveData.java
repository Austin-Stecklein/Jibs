package com.example.jibs;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class UserSaveData {

    private Context context;
    private final String filename = "userSaveData.txt";

    public UserSaveData(Context context) {
        this.context = context;
    }

    public int saveData(HolidayItem holidayItem) {

        String data = new Gson().toJson(holidayItem);
        data += '\n';

        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_APPEND);
            fos.write(data.getBytes());

            fos.close();
        } catch (FileNotFoundException e) {

            return 1;
        } catch (IOException e) {

            return 1;
        }
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<HolidayItem> getSaveData() throws FileNotFoundException {
        List<String> strings = new ArrayList<String>();
        List<HolidayItem> objects = new ArrayList<HolidayItem>();
        FileInputStream fis =  context.openFileInput(filename);

        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line = reader.readLine();

            while (line != null) {

                strings.add(line);
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            String contents = stringBuilder.toString();
        }

        for(String item: strings) {

            objects.add(new Gson().fromJson(item, HolidayItem.class));

        }

        return objects;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void deleteItem(HolidayItem newItem) {
        List<HolidayItem> holidayItems = new ArrayList<>();
        try {
            holidayItems = getSaveData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        FileOutputStream fose = null;
        try {
            String data = "";
            fose = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fose.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<HolidayItem> it = holidayItems;
        for(int i = 0; i < it.size(); i++) {

            if(it.get(i).getName().equals(newItem.getName())) {
                holidayItems.remove(i);
            }
        }
        if(newItem.getNotification().equals("True")) {
            holidayItems.add(newItem);
        }


        for(HolidayItem holidayItem: holidayItems) {
            String data = new Gson().toJson(holidayItem);
            data += '\n';

            try {
                FileOutputStream fos = context.openFileOutput(filename, Context.MODE_APPEND);
                fos.write(data.getBytes());

                fos.close();
            } catch (FileNotFoundException e) {} catch (IOException e) {}

        }

    }
}



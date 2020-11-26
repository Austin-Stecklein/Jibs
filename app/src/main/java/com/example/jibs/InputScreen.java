package com.example.jibs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

/*
This is the activity where user can enter in their own holidays.
 */
public class InputScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);
    }

    //This is where are going to add notifications.
    public void addNotifications(View view) {
        EditText editText = (EditText) findViewById(R.id.Name);
        EditText editText1 = (EditText) findViewById(R.id.Date);
        EditText editText2 = (EditText) findViewById(R.id.Description);
        Switch simpleSwitch = (Switch) findViewById(R.id.enable);

        //This is the data from the page.
        String name = editText.toString();
        String date = editText1.toString();
        String Description = editText2.toString();
        boolean switchState = simpleSwitch.isChecked();
    }
}
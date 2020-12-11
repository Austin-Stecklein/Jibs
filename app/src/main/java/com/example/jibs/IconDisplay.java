package com.example.jibs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class IconDisplay extends AppCompatActivity {

    private Icon[] icons = new Icon[5];
    private String activity;
    private String name;
    private String date;
    private String description;
    private String notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_display);

        Intent intent = getIntent();
        activity = intent.getStringExtra("activity");
        name = intent.getStringExtra("Name");
        date = intent.getStringExtra("Date");
        description = intent.getStringExtra("Description");
        notification = intent.getStringExtra("Notification");

        icons[0] = new Icon(R.drawable.tree);
        icons[1] = new Icon(R.drawable.cups);
        icons[2] = new Icon(R.drawable.heart);
        icons[3] = new Icon(R.drawable.fireworks);
        icons[4] = new Icon(R.drawable.add);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        GridAdapter gridAdapter = new GridAdapter(this, icons);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendIconBack(position);
            }
        });
    }

    public void sendIconBack(int position){
        Intent intent =  null;
        if(activity.equals("input")) {
            intent = new Intent(this, InputScreen.class);
        }
        else {
            intent = new Intent(this, HolidayInfo.class);
            intent.putExtra("Name", name);
            intent.putExtra("Description", description);
            intent.putExtra("Date", date);
            intent.putExtra("Notification", notification);
            intent.putExtra("activity", "IconDisplay");
        }

        intent.putExtra("location", icons[position].getLocation());
        startActivity(intent);
    }
}
package com.example.jibs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class IconDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_display);

        Icon[] icons = new Icon[4];

        icons[0] = new Icon(R.drawable.tree);
        icons[1] = new Icon(R.drawable.cups);
        icons[2] = new Icon(R.drawable.heart);
        icons[3] = new Icon(R.drawable.fireworks);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        GridAdapter gridAdapter = new GridAdapter(this, icons);
        gridView.setAdapter(gridAdapter);
    }
}
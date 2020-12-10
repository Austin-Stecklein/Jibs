package com.example.jibs;

import android.content.Context;
import android.widget.Toast;

//This class simply adds a toast to be used when settings have been changed
public class Confirmation {
    public void onReceive(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_LONG).show();
    }
}

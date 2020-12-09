package com.example.jibs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    public final Context mContext;
    public final Icon[] icons;

    public GridAdapter(Context mContext, Icon[] icons) {
        this.mContext = mContext;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Icon icon = icons[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_icon, null);
        }

        final ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview_icon);
        imageView.setImageResource(icon.getLocation());

        return convertView;
    }
}

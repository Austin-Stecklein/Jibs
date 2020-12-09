package com.example.jibs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FavHolidayAdapter extends ArrayAdapter<HolidayItem> {
    private Context mContext;
    private int mResource;


    public FavHolidayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HolidayItem> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource,parent,false);

        ImageView imageView = convertView.findViewById(R.id.image);

        TextView txtName = convertView.findViewById(R.id.txtName);

        TextView txtDes = convertView.findViewById(R.id.txtDes);

        if(getItem(position).icon != "" && getItem(position).icon != null) {
            imageView.setImageResource(Integer.parseInt(getItem(position).icon));
        }
        else {
            imageView.setImageResource(R.drawable.add);
        }

        txtName.setText(getItem(position).getName());

        txtDes.setText(getItem(position).getDescription());

        return convertView;
    }
}

package com.example.jobs.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jobs.R;
import com.example.jobs.model.City;
import com.example.jobs.model.District;

import java.util.ArrayList;

public class AdapterDistrict extends BaseAdapter {


    Activity activity;
    ArrayList<District> list;
    LayoutInflater inflater;

    public AdapterDistrict(Activity activity, ArrayList<District> list) {
        this.activity = activity;
        this.list = list;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null)
            view = inflater.inflate(R.layout.item_city, null);


        TextView id = view.findViewById(R.id.id_city);
        TextView name = view.findViewById(R.id.name_city);


        District district = list.get(position);

        id.setText(district.getId() + "");
        name.setText(district.getName() + "");


        return view;
    }
}

package com.example.pritish.baseadapter_image;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class customAdapter extends BaseAdapter {
    Context context;
    int animals[];
    LayoutInflater inflater;

    public customAdapter(Context applicationContext, int[] animals){
        this.animals = animals;
        this.context = applicationContext;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return animals.length;
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
        convertView = inflater.inflate(R.layout.activity_gridview, null);
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        icon.setImageResource(animals[position]);
        return convertView;
    }
}

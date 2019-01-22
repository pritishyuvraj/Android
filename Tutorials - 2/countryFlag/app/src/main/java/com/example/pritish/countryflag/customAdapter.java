package com.example.pritish.countryflag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class customAdapter extends BaseAdapter {

    String country[];
    int flags[];
    LayoutInflater inflate;
    Context context;

    public customAdapter (String country[], int flags[], Context context){
        this.country = country;
        this.flags = flags;
        this.context = context;
        inflate = LayoutInflater.from(context);

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflate.inflate(R.layout.activity_list, null);
        ImageView image =(ImageView) convertView.findViewById(R.id.image);
        TextView text = (TextView) convertView.findViewById(R.id.text);
        image.setImageResource(flags[position]);
        text.setText(country[position]);
        return convertView;
    }
}

package com.example.androidversions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<WordImageContent> {
    public  CustomAdapter(Context context, ArrayList<WordImageContent> wordContent){
        super(context, 0, wordContent);
    }


    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {
        WordImageContent wordContent = getItem(position);


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_android_version, parent, false);
        }

        TextView androidName = (TextView) convertView.findViewById(R.id.android_name);
        TextView androidVersion = (TextView) convertView.findViewById(R.id.version_number);
        ImageView androidImage = (ImageView) convertView.findViewById(R.id.android_image);

        androidName.setText(wordContent.name);
        androidVersion.setText(wordContent.version);
        androidImage.setImageResource(wordContent.image);

        return convertView;
    }
}

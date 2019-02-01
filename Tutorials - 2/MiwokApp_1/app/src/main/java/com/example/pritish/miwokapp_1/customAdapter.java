package com.example.pritish.miwokapp_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class customAdapter extends ArrayAdapter<CustomModel> {

    public customAdapter(Context context, ArrayList<CustomModel> users){
        super(context, 0, users);
    }

    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {

        CustomModel customModel = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.left_text);
        TextView tvHome = (TextView) convertView.findViewById(R.id.right_text);

        tvName.setText(customModel.name);
        tvHome.setText(customModel.hometown);

        return convertView;
    }
}

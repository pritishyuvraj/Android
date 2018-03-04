package com.example.priti.live;

/**
 * Created by priti on 12/26/2017.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priti.live.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
//    private List<String> values;
    private ArrayList<HashMap<String, String>> values;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public TextView txtDay;
        public View layout;
        public ImageView foodTime;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            txtDay = (TextView) v.findViewById(R.id.thirdLine);
            foodTime = (ImageView) v.findViewById(R.id.icon);
        }
    }

//    public void add(int position, String item) {
//        values.add(position, item);
//        notifyItemInserted(position);
//    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
//    public MyAdapter(List<String> myDataset) {
//        values = myDataset;
//    }
        public MyAdapter(ArrayList<HashMap<String, String>> database){
            values = database;
        }
    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        HashMap<String, String> temp = new HashMap<>();
        temp = values.get(position);
//        final String name = values.get(position);
        String name = "";
        String calories = "";
        String mealTime = "";
        String day = "";
        for (String key: temp.keySet()){
            Log.e("sadsad", key +" search -> "+ temp.get(key));
            if (key == "food_name"){
                name = temp.get(key);
            }
            if (key == "Calories"){
                calories = temp.get(key);
            }
            if (key == "MealTime"){
                mealTime = temp.get(key);
            }
            if (key == "Day"){
                day = temp.get(key);
                if (day == "Breakfast"){
                    holder.foodTime.setImageResource(R.drawable.break_fast);
                }
                else if(day == "Lunch"){
                    holder.foodTime.setImageResource(R.mipmap.lunch_try_1);
                }
                else{
                    holder.foodTime.setImageResource(R.drawable.dinner);
                }
            }
        }
        holder.txtHeader.setText(name);

        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });

        holder.txtFooter.setText("Calories: " + calories);
        holder.txtDay.setText(day);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
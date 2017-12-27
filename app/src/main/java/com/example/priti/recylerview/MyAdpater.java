package com.example.priti.recylerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import com.example.priti.recylerview.R;

import java.util.List;

/**
 * Created by priti on 12/26/2017.
 */

public class MyAdpater extends
        RecyclerView.Adapter<MyAdpater.ViewHolder> {

    private List<String> values;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v){
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);

        }
    }

    public void add(int position, String item){
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int postion){
        values.remove(postion);
        notifyItemRemoved(postion);
    }

    public MyAdpater(List<String> myDataset){
        values = myDataset;
    }

    //Create new views (invoked by the layout manager)
    @Override
    public MyAdpater.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType){

        //Create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,
                                 final int position){
        final String name = values.get(position);
        holder.txtHeader.setText(name);
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
            }
        });

        holder.txtFooter.setText("Footer: " + name);
    }

    @Override
    public int getItemCount(){
        return values.size();
    }
}


package com.example.pritish.prioritytask;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by pritish on 1/19/2018.
 */

public class MyAdpater extends RecyclerView.Adapter<MyAdpater.ViewHolder> {
    private List<String> sno;
    private List<String> desc;
    private List<String> priority;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txt_sno;
        public TextView txt_desc;
        public TextView txt_priority;
        public View layout;

        public ViewHolder(View v){
            super(v);
            layout = v;
            txt_sno = (TextView) v.findViewById(R.id.serial_no);
            txt_desc = (TextView) v.findViewById(R.id.text_);
            txt_priority = (TextView) v.findViewById(R.id.priority);
        }
    }

    public void add(int position, String item){
        //values.add(position, item);
        sno.add(position, item);
        desc.add(position, item);
        priority.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position){
        //values.remove(position);
        sno.remove(position);
        desc.remove(position);
        priority.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdpater(List<String> sno_list, List<String> desc_list, List<String> priority_list){
        sno = sno_list;
        desc = desc_list;
        priority = priority_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String final_sno = sno.get(position);
        final String final_desc = desc.get(position);
        final String final_priority = priority.get(position);

        //holder.txtheader.setText(name);
        holder.txt_sno.setText(final_sno);
        holder.txt_desc.setBackgroundResource(R.color.maroon);
        holder.txt_desc.setText(final_desc);
        holder.txt_priority.setText(final_priority);

        holder.txt_desc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Toast.makeText((),"Something CLicked", Toast.LENGTH_LONG, );
                Log.e("asdasdasd", "Clicked -> " );
            }

        });
        /*
        holder.txtheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
            }
        });
        holder.txtfooter.setText(name);
        */
    }

    @Override
    public int getItemCount() {
        return sno.size();
    }
}

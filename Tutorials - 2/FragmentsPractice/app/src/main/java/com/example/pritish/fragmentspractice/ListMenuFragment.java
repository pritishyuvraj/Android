package com.example.pritish.fragmentspractice;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListMenuFragment extends ListFragment {

    String[] users = new String[] {"Suresh", "Rohini", "Trishika", "Praveen"};
    String[] location = new String[] {"Hyderabad", "Guntur", "Hyderabad", "Bangalore"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getActivity() == null){
            Log.e("TAG Pritish", "get Activity is null");
            return null;

        } else{
            View view = inflater.inflate(R.layout.list_items_info, container, false);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, users);
            setListAdapter(adapter);
            return view;
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        try{
            DetailsFragment txt = (DetailsFragment) getFragmentManager().findFragmentById(R.id.fragment2);
            txt.change("Name: " + users[position], "Location: " + location[position]);
            getListView().setSelector(android.R.color.holo_blue_dark);
        } catch (NullPointerException e){
            Log.e("TAG Pritish", "Null Pointer Exception" + e.getMessage());
        } catch (Exception e){
            Log.e("TAG Pritish", "Some kind of Exception" + e.getMessage());
        }

    }
}

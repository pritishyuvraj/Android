package com.example.pritish.miwokapp_1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    public customAdapter customAdapterUsers;

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.numbers, container, false);

//        Copying code from Numbers activity
        ArrayList<CustomModel> customListOfUsers = new ArrayList<>();
        customAdapterUsers = new customAdapter(getActivity(), customListOfUsers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(customAdapterUsers);

        CustomModel newmodel = new CustomModel("Pritish", "Yuvraj", R.drawable.ic_launcher_background, R.raw.guitar);
        customAdapterUsers.add(newmodel);
        CustomModel newmodel2 = new CustomModel("Kate", "Upton", R.drawable.ic_launcher_foreground, R.raw.piano);
        customAdapterUsers.add(newmodel2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Item was clicked", Toast.LENGTH_SHORT).show();
                Log.e("TAG PRITISH", "On Item Click was pressed");
            }
        });


        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        customAdapterUsers.releaseMediaPlayerObject();
    }
}

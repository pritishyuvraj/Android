package com.example.pritish.fragmenttransition;

import android.content.Context;
import android.os.PersistableBundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int mStackLevel = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.fragment1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragmentToStack();
            }
        });
    }

    void addFragmentToStack(){
        mStackLevel++;

//        Instantiate a new fragment
        Fragment newFragment = CountingFragment.newInstance(mStackLevel);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fragment_slide_left_exit, R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit);
        ft.replace(R.id.simple_fragment, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static class CountingFragment extends Fragment{
        int mNum;

        static CountingFragment newInstance(int num){
            CountingFragment f = new CountingFragment();

//            Supply num input as an argument
            Bundle args = new Bundle();

            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }


        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container,
                                 Bundle savedInstanceState) {
//            return super.onCreateView(inflater, container, savedInstanceState);

            View v = inflater.inflate(R.layout.fragment_stack, container, false);
            View tv = v.findViewById(R.id.withText);
            ( (TextView) tv).setText("Fragment #" + mNum);
            tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));
            return v;
        }
    }
}

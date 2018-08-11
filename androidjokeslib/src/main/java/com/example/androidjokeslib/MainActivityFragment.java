package com.example.androidjokeslib;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by minga on 8/8/2018.
 */

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke, container, false);

        Intent intent= getActivity ().getIntent ();
        String jokeStr = intent.getStringExtra (JokeActivity.EXTRA_JOKE);
        TextView jokeTv = (TextView ) root.findViewById (R.id.jokes_tv);
        if (jokeStr != null & jokeStr.length ()!=0){
            jokeTv.setText (jokeStr);
        }


        return root;
    }
}

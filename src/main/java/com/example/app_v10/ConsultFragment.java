package com.example.app_v10;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ConsultFragment extends Fragment {

    private TextView door, window, light ;
    private ImageView doorStatus, windowStatus, lightStatus ;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public ConsultFragment() {

    }


    public static ConsultFragment newInstance(String param1, String param2) {
        ConsultFragment fragment = new ConsultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_consult, container, false);

        /* === door section === */
        door = view.findViewById(R.id.door) ;
       doorStatus = view.findViewById(R.id.doorStatus);

        if(State.doorOpen) {
            door.setText("Opened");
            doorStatus.setImageResource(R.drawable.online_dot);
        }
        else{
            door.setText("Closed");
            doorStatus.setImageResource(R.drawable.offline_dot);
        }



        /* === Window section === */
        window = view.findViewById(R.id.window) ;
        windowStatus = view.findViewById(R.id.windowStatus);

        if(State.windowsOpen) {
            window.setText("Opened");
            windowStatus.setImageResource(R.drawable.online_dot);
        }
        else{
            window.setText("Closed");
            windowStatus.setImageResource(R.drawable.offline_dot);
        }


        /* === Light Section === */

        light = view.findViewById(R.id.light);
        lightStatus = view.findViewById(R.id.lightStatus) ;

        if(State.lightsOn) {
            light.setText("Opened");
            lightStatus.setImageResource(R.drawable.online_dot);
        }
        else{
            light.setText("Closed");
            lightStatus.setImageResource(R.drawable.offline_dot);
        }


        return view ;
    }
}
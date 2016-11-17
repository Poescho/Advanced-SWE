package com.example.poescho.parkokassoquittomat.Parkscheinausgabe;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.poescho.parkokassoquittomat.Helpers.TimePickerFragment;
import com.example.poescho.parkokassoquittomat.Main;
import com.example.poescho.parkokassoquittomat.R;

import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class Parkscheinausgabe extends Fragment{
    private static final String ARG_EXAMPLE = "constant";
    private String example_data;


    public Parkscheinausgabe()
  {

  }

    public static Parkscheinausgabe newInstance(String example_argument)
    {
        Parkscheinausgabe parkscheinausgabe = new Parkscheinausgabe();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        parkscheinausgabe.setArguments(args);
        return parkscheinausgabe;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        example_data = getArguments().getString(ARG_EXAMPLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parkscheinausgabe,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(Main.parkschein != null)
        {
            setParkschein(Main.parkschein);
        }
    }


    //onClick Method
    public Parkschein createParkschein(View view)
    {
        Parkschein parkschein = new Parkschein();
        setParkschein(parkschein);
        return parkschein;
    }



    private void setParkschein(Parkschein parkschein)
    {
        View currentView = getView();
        TextView txtUhrzeit = (TextView)currentView.findViewById(R.id.txt_uhrzeit);
        String time = String.valueOf(parkschein.getHour())
        + ":" +String.valueOf(parkschein.getMinute());
        txtUhrzeit.setText(time);

        TextView txtDatum = (TextView)currentView.findViewById(R.id.txt_datum);
        String date =String.valueOf(parkschein.getDay())
                + "." +String.valueOf(parkschein.getMonth()+1)
                + "." +String.valueOf(parkschein.getYear());
        txtDatum.setText(date);

        RelativeLayout parkscheinLayout = (RelativeLayout)currentView.findViewById(R.id.parkschein);
        Button bearbeiteBtn = (Button) currentView.findViewById(R.id.btn_bearbeite);

        if(parkscheinLayout.getVisibility() != View.VISIBLE || bearbeiteBtn.getVisibility() != View.VISIBLE)
        {
            parkscheinLayout.setVisibility(View.VISIBLE);
            bearbeiteBtn.setVisibility(View.VISIBLE);
        }
    }



}
  
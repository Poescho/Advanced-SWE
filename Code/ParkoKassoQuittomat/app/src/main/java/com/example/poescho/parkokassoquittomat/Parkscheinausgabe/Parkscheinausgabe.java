package com.example.poescho.parkokassoquittomat.Parkscheinausgabe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poescho.parkokassoquittomat.R;

import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class Parkscheinausgabe extends Fragment{
    private static final String ARG_EXAMPLE = "constant";
    private String example_data;
    public Parkschein parkschein;

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
        Log.i("Fragment created with ", example_data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parkscheinausgabe,container,false);
    }

    //onClick Method
    public Parkschein createParkschein(View view)
    {
        parkschein = new Parkschein();

        TextView txtUhrzeit = (TextView)getView().findViewById(R.id.txt_uhrzeit);
        SimpleDateFormat localTimeFormat = new SimpleDateFormat("HH:mm:ss");
        localTimeFormat.setTimeZone(TimeZone.getDefault());
        String time = localTimeFormat.format(parkschein.date);
        txtUhrzeit.setText(time);

        TextView txtDatum = (TextView)getView().findViewById(R.id.txt_datum);
        SimpleDateFormat localDateFormat = new SimpleDateFormat("dd.mm.yyyy");
        String date = localDateFormat.format(parkschein.date);
        txtDatum.setText(date);


        return parkschein;
    }



}

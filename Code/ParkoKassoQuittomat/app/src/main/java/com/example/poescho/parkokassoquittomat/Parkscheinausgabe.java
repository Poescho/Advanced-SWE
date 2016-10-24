package com.example.poescho.parkokassoquittomat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Parkscheinausgabe extends Fragment {
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
        Log.i("Fragment created with ", example_data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parkscheinausgabe,container,false);
    }
}

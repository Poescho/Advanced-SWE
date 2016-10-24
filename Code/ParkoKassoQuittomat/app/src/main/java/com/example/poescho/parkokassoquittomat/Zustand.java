package com.example.poescho.parkokassoquittomat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Zustand extends Fragment {
    private static final String ARG_EXAMPLE = "constant";
    private String example_data;

    public Zustand()
  {

  }

    public static Zustand newInstance(String example_argument)
    {
        Zustand zustand = new Zustand();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        zustand.setArguments(args);
        return zustand;
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
        return inflater.inflate(R.layout.fragment_zustand,container,false);
    }
}

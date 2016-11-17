package com.example.poescho.parkokassoquittomat.Kassenautomat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poescho.parkokassoquittomat.Helpers.Observer;
import com.example.poescho.parkokassoquittomat.Parkscheinausgabe.Parkschein;
import com.example.poescho.parkokassoquittomat.R;


public class Kassenautomat extends Fragment implements Observer{
    private static final String ARG_EXAMPLE = "constant";
    private String example_data;

    public  Kassenautomat()
  {

  }

    public static Kassenautomat newInstance(String example_argument)
    {
        Kassenautomat kassenautomat = new Kassenautomat();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        kassenautomat.setArguments(args);
        return kassenautomat;
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
        return inflater.inflate(R.layout.fragment_kassenautomat,container,false);
    }

    //Observer Method
    @Override
    public void update(Parkschein parkschein) {
        System.out.println("Kassenautomat update");

    }
}

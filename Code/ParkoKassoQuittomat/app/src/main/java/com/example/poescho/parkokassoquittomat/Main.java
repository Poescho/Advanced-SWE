package com.example.poescho.parkokassoquittomat;


import android.app.DialogFragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.poescho.parkokassoquittomat.Helpers.DatePickerFragment;
import com.example.poescho.parkokassoquittomat.Helpers.TimePickerFragment;
import com.example.poescho.parkokassoquittomat.Helpers.ViewPagerAdapter;
import com.example.poescho.parkokassoquittomat.Kassenautomat.Kassenautomat;
import com.example.poescho.parkokassoquittomat.Parkscheinausgabe.Parkschein;
import com.example.poescho.parkokassoquittomat.Parkscheinausgabe.Parkscheinausgabe;
import com.example.poescho.parkokassoquittomat.Quittungsuebersicht.Quittungsuebersicht;
import com.example.poescho.parkokassoquittomat.Zustand.Zustand;

import java.io.FileNotFoundException;


public class Main extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Parkscheinausgabe parkscheinausgabe;
    private Kassenautomat kassenautomat;

    private static Context activityContext;
    public static Parkschein parkschein;


    public static Context getActivityContext() {
        return activityContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityContext = this.getApplicationContext();



        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Resources res = getResources();

        Drawable parking = res.getDrawable(R.drawable.ic_parking);
        Drawable pay = res.getDrawable(R.drawable.ic_pay);
        Drawable state = res.getDrawable(R.drawable.ic_state);
        Drawable quittung = res.getDrawable(R.drawable.ic_quittung);


        tabLayout.getTabAt(0).setIcon(parking);
        tabLayout.getTabAt(1).setIcon(pay);
        tabLayout.getTabAt(2).setIcon(state);
        tabLayout.getTabAt(3).setIcon(quittung);


        parkschein = Parkschein.loadParkschein(activityContext);

        if(parkschein != null)
        {
            registerParkscheinObservers();
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        parkscheinausgabe = Parkscheinausgabe.newInstance("this is data for the Parkscheinausgabe");
        adapter.addFragment(parkscheinausgabe,"Parkscheinausgabe");

        kassenautomat = Kassenautomat.newInstance("this is data for the Kassenautomat");
        adapter.addFragment(kassenautomat,"Kasse");

        adapter.addFragment(Zustand.newInstance("this is data for the Zustand"),"Zustand");
        adapter.addFragment(Quittungsuebersicht.newInstance("this is data for the Quittungsuebersicht"),"Quittungen");


        viewPager.setAdapter(adapter);



    }

    //onClick Method
    public void createParkschein(View view)
    {
        parkschein = parkscheinausgabe.createParkschein(view);
        registerParkscheinObservers();
        parkschein.saveParkschein(activityContext);
    }

    public void registerParkscheinObservers()
    {
        parkschein.register(parkscheinausgabe);
        parkschein.register(kassenautomat);
    }

    public void showDatepicker(View view)
    {
        DialogFragment newDateFragment = new DatePickerFragment();
        newDateFragment.show(getFragmentManager(),"datePicker");
    }
}

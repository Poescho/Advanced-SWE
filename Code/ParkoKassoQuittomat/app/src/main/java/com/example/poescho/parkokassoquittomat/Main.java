package com.example.poescho.parkokassoquittomat;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.poescho.parkokassoquittomat.Helpers.ViewPagerAdapter;
import com.example.poescho.parkokassoquittomat.Kassenautomat.Kassenautomat;
import com.example.poescho.parkokassoquittomat.Parkscheinausgabe.Parkschein;
import com.example.poescho.parkokassoquittomat.Parkscheinausgabe.Parkscheinausgabe;
import com.example.poescho.parkokassoquittomat.Quittungsuebersicht.Quittungsuebersicht;
import com.example.poescho.parkokassoquittomat.Zustand.Zustand;


public class Main extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Parkscheinausgabe parkscheinausgabe;
    private Parkschein parkschein;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        parkscheinausgabe = Parkscheinausgabe.newInstance("this is data for the Parkscheinausgabe");
        adapter.addFragment(parkscheinausgabe,"Parkscheinausgabe");

        adapter.addFragment(Kassenautomat.newInstance("this is data for the Kassenautomat"),"Kasse");
        adapter.addFragment(Zustand.newInstance("this is data for the Zustand"),"Zustand");
        adapter.addFragment(Quittungsuebersicht.newInstance("this is data for the Quittungsuebersicht"),"Quittungen");


        viewPager.setAdapter(adapter);
    }

    //onClick Method
    public void createParkschein(View view)
    {
      parkschein = parkscheinausgabe.createParkschein(view);
        System.out.println(parkschein.date.toString());
    }
}

package com.example.poescho.parkokassoquittomat;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class Main extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout ;




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
        adapter.addFragment(Parkscheinausgabe.newInstance("this is data for the Parkscheinausgabe"),"Parkscheinausgabe");
        adapter.addFragment(Kassenautomat.newInstance("this is data for the Kassenautomat"),"Kasse");
        adapter.addFragment(Zustand.newInstance("this is data for the Zustand"),"Zustand");
        adapter.addFragment(Quittungsuebersicht.newInstance("this is data for the Quittungsuebersicht"),"Quittungen");


        viewPager.setAdapter(adapter);
    }
}

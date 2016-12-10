package com.example.michael.kassenautomat_dhbw;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.fragments.mainKassenautomat.FragmentKassenautomat;
import com.example.michael.kassenautomat_dhbw.fragments.maintain.FragmentMaintain;
import com.example.michael.kassenautomat_dhbw.fragments.one.FragmentAutomat;
import com.example.michael.kassenautomat_dhbw.fragments.settings.FragmentSettings;
import com.example.michael.kassenautomat_dhbw.fragments.three.FragmentUserInformation;
import com.example.michael.kassenautomat_dhbw.fragments.two.FragmentCoinList;
import com.example.michael.kassenautomat_dhbw.fragments.two.FragmentTicketList;
import com.example.michael.kassenautomat_dhbw.helpers.ViewPagerAdapter;
import com.example.michael.kassenautomat_dhbw.util.DefaultValuesHandler;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;
import com.example.michael.kassenautomat_dhbw.util.Util;

import java.lang.Override;

public class MainActivity extends AppCompatActivity implements OnButtonClickedCallback {

    private KassenautomatContext kassenautomatContext;
    private Long lastPaidTicketId = null;

   // private FragmentToolbarDefault fragmentToolbarDefault = new FragmentToolbarDefault();
   private FragmentAutomat fragmentAutomat = new FragmentAutomat();
    private FragmentTicketList fragmentTicketList = new FragmentTicketList();
    private FragmentCoinList fragmentCoinList = new FragmentCoinList();
    private FragmentUserInformation fragmentUserInformation = new FragmentUserInformation();
    private FragmentKassenautomat kassenautomat = new FragmentKassenautomat(fragmentAutomat, fragmentTicketList, fragmentUserInformation);
    private FragmentMaintain fragmentMaintain = FragmentMaintain.newInstance(kassenautomatContext);
    private FragmentSettings fragmentSettings = new FragmentSettings();

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private int DEBUG_VARIABLE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        kassenautomatContext = new KassenautomatContext(MainActivity.this, fragmentManager);
        DefaultValuesHandler.initializeDefaults(kassenautomatContext);

        fragmentMaintain.kassenautomatContext = kassenautomatContext;
        fragmentSettings.kassenautomatContext = kassenautomatContext;

        //fragmentAutomat.setTicketToPay(null);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Resources res = getResources();

        Drawable parking = res.getDrawable(R.drawable.ic_parking);
       Drawable state = res.getDrawable(R.drawable.ic_state);
        Drawable quittung = res.getDrawable(R.drawable.ic_quittung);


       tabLayout.getTabAt(0).setIcon(parking);
        tabLayout.getTabAt(1).setIcon(state);
        tabLayout.getTabAt(2).setIcon(quittung);

        /*FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        {
            fragmentTransaction.add(R.id.activity_main_container_one, fragmentAutomat);
            fragmentTransaction.add(R.id.activity_main_container_two, fragmentTicketList);
            fragmentTransaction.add(R.id.activity_main_container_three, fragmentUserInformation);
        }
        fragmentTransaction.commit();*/

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(kassenautomat,"Kassenautomat");

        adapter.addFragment(fragmentMaintain,"Maintain");

        adapter.addFragment(fragmentSettings,"Settings");


        viewPager.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPayForTicket(int moneyValue) {
        fragmentAutomat.showToPayValue(moneyValue);
    }

    @Override
    public void setTextOfDisplay(int stringId) {
        fragmentAutomat.setTextOfDisplay(stringId);
    }

    @Override
    public void updateTicketList() {
        fragmentTicketList.updateTicketList();
    }

    @Override
    public void updateCoinList() {
        fragmentCoinList.updateCoinList();
    }


    @Override
    public void returnToTicketList() {
        FragmentManager fragmentManager = kassenautomat.getKassenautomatFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        {
           fragmentTransaction.replace(R.id.kassen_main_container_two, fragmentTicketList);
        }

        fragmentTransaction.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();

        kassenautomatContext.getDatabaseConnection().freeDatabase();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        kassenautomatContext = new KassenautomatContext(MainActivity.this, getSupportFragmentManager());
    }

    @Override
    public void onBackPressed() {
        if(fragmentAutomat.hasTicketToPay()) {
            endPayment(fragmentAutomat.getCentsToPay());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void endPayment(int payback) {

        Util.giveNickelback(payback, kassenautomatContext);
        returnToTicketList();
        fragmentAutomat.setTicketToPay(null);
    }

    @Override
    public void endPayment() {
        endPayment(fragmentAutomat.getCentsToPay());
    }

    @Override
    public void startPayment(Ticket ticket) {
        if (ticket.isPaid()) {
            fragmentAutomat.setTextOfDisplay(R.string.ticket_was_already_paid);
        } else {
            if (ticket.isValid()) {
                fragmentAutomat.setTicketToPay(ticket);

                FragmentManager fragmentManager = kassenautomat.getKassenautomatFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                {
                   fragmentTransaction.replace(R.id.kassen_main_container_two, fragmentCoinList);
                }

                fragmentTransaction.commit();
            } else {
                fragmentAutomat.setTextOfDisplay(R.string.ticket_was_not_valid);
            }
        }
        updateTicketList();
    }

    @Override
    public KassenautomatContext getKassenautomatContext() {
        return kassenautomatContext;
    }

    public void setLastPaidTicketId(long id) {
        this.lastPaidTicketId = id;
    }

    public long getLastPaidTicketId() {
        return lastPaidTicketId;
    }
}

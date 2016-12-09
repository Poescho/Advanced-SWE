package com.example.michael.kassenautomat_dhbw;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.fragments.mainKassenautomat.Fragment_Kassenautomat;
import com.example.michael.kassenautomat_dhbw.fragments.maintain.MaintainFragment;
import com.example.michael.kassenautomat_dhbw.fragments.one.FragmentOneShowAutomata;
import com.example.michael.kassenautomat_dhbw.fragments.settings.SettingsDialog;
import com.example.michael.kassenautomat_dhbw.fragments.three.FragmentThreeUserInformation;
import com.example.michael.kassenautomat_dhbw.fragments.two.FragmentTwoCoinsList;
import com.example.michael.kassenautomat_dhbw.fragments.two.FragmentTwoShowTicketList;
import com.example.michael.kassenautomat_dhbw.helpers.ViewPagerAdapter;
import com.example.michael.kassenautomat_dhbw.util.DefaultValuesHandler;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;
import com.example.michael.kassenautomat_dhbw.util.Util;

import java.lang.Override;

public class MainActivity extends AppCompatActivity implements OnButtonClickedCallback {

    private KassenautomatContext kassenautomatContext;
    private Long lastPaidTicketId = null;

   // private FragmentToolbarDefault fragmentToolbarDefault = new FragmentToolbarDefault();
   private FragmentOneShowAutomata fragmentOneShowAutomata = new FragmentOneShowAutomata();
    private FragmentTwoShowTicketList fragmentTwoShowTicketList = new FragmentTwoShowTicketList();
    private FragmentTwoCoinsList fragmentTwoCoinsList = new FragmentTwoCoinsList();
    private FragmentThreeUserInformation fragmentThreeUserInformation = new FragmentThreeUserInformation();
    private Fragment_Kassenautomat kassenautomat = new Fragment_Kassenautomat(fragmentOneShowAutomata,fragmentTwoShowTicketList,fragmentThreeUserInformation);
    private MaintainFragment maintainFragment = MaintainFragment.newInstance(kassenautomatContext);
    private SettingsDialog settingsDialog = new SettingsDialog();

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

        maintainFragment.kassenautomatContext = kassenautomatContext;
        settingsDialog.kassenautomatContext = kassenautomatContext;

        //fragmentOneShowAutomata.setTicketToPay(null);
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
            fragmentTransaction.add(R.id.activity_main_container_one, fragmentOneShowAutomata);
            fragmentTransaction.add(R.id.activity_main_container_two, fragmentTwoShowTicketList);
            fragmentTransaction.add(R.id.activity_main_container_three, fragmentThreeUserInformation);
        }
        fragmentTransaction.commit();*/

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(kassenautomat,"Kassenautomat");

        adapter.addFragment(maintainFragment,"Maintain");

        adapter.addFragment(settingsDialog,"Settings");


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
        fragmentOneShowAutomata.showToPayValue(moneyValue);
    }

    @Override
    public void setTextOfDisplay(int stringId) {
        fragmentOneShowAutomata.setTextOfDisplay(stringId);
    }

    @Override
    public void updateTicketList() {
        fragmentTwoShowTicketList.updateTicketList();
    }

    @Override
    public void updateCoinList() {
        fragmentTwoCoinsList.updateCoinList();
    }


    @Override
    public void returnToTicketList() {
        DEBUG_VARIABLE++;
        System.out.println("returnToTicketList");
        FragmentManager fragmentManager = kassenautomat.getKassenautomatFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        {
           fragmentTransaction.replace(R.id.kassen_main_container_two, fragmentTwoShowTicketList);
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
        if(fragmentOneShowAutomata.hasTicketToPay()) {
            endPayment(fragmentOneShowAutomata.getCentsToPay());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void endPayment(int payback) {

        Util.giveNickelback(payback, kassenautomatContext);
        returnToTicketList();
        fragmentOneShowAutomata.setTicketToPay(null);
    }

    @Override
    public void endPayment() {
        endPayment(fragmentOneShowAutomata.getCentsToPay());
    }

    @Override
    public void startPayment(Ticket ticket) {
        if (ticket.isPaid()) {
            fragmentOneShowAutomata.setTextOfDisplay(R.string.ticket_was_already_paid);
        } else {
            if (ticket.isValid()) {
                fragmentOneShowAutomata.setTicketToPay(ticket);

                FragmentManager fragmentManager = kassenautomat.getKassenautomatFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                {
                   fragmentTransaction.replace(R.id.kassen_main_container_two, fragmentTwoCoinsList);
                }

                fragmentTransaction.commit();
            } else {
                fragmentOneShowAutomata.setTextOfDisplay(R.string.ticket_was_not_valid);
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

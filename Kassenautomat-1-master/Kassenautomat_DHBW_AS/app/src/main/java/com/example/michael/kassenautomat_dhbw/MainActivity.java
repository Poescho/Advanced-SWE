package com.example.michael.kassenautomat_dhbw;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.fragments.one.FragmentOneShowAutomata;
import com.example.michael.kassenautomat_dhbw.fragments.three.FragmentThreeUserInformation;
import com.example.michael.kassenautomat_dhbw.fragments.toolbar.FragmentToolbarDefault;
import com.example.michael.kassenautomat_dhbw.fragments.two.FragmentTwoCoinsList;
import com.example.michael.kassenautomat_dhbw.fragments.two.FragmentTwoShowTicketList;
import com.example.michael.kassenautomat_dhbw.util.DefaultValuesHandler;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;
import com.example.michael.kassenautomat_dhbw.util.Util;

import java.lang.Override;

public class MainActivity extends AppCompatActivity implements OnButtonClickedCallback {

    private KassenautomatContext kassenautomatContext;
    private Long lastPaidTicketId = null;

    private FragmentToolbarDefault fragmentToolbarDefault = new FragmentToolbarDefault();
    private FragmentOneShowAutomata fragmentOneShowAutomata = new FragmentOneShowAutomata();
    private FragmentTwoCoinsList fragmentTwoCoinsList = new FragmentTwoCoinsList();
    private FragmentTwoShowTicketList fragmentTwoShowTicketList = new FragmentTwoShowTicketList();
    private FragmentThreeUserInformation fragmentThreeUserInformation = new FragmentThreeUserInformation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        kassenautomatContext = new KassenautomatContext(MainActivity.this, fragmentManager);
        DefaultValuesHandler.initializeDefaults(kassenautomatContext);

        fragmentOneShowAutomata.setTicketToPay(null);


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        {
            fragmentTransaction.add(R.id.activity_main_container_toolbar, fragmentToolbarDefault);
            fragmentTransaction.add(R.id.activity_main_container_one, fragmentOneShowAutomata);
            fragmentTransaction.add(R.id.activity_main_container_two, fragmentTwoShowTicketList);
            fragmentTransaction.add(R.id.activity_main_container_three, fragmentThreeUserInformation);
        }
        fragmentTransaction.commit();

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
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        {
            fragmentTransaction.replace(R.id.activity_main_container_two, fragmentTwoShowTicketList);
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

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                {
                    fragmentTransaction.replace(R.id.activity_main_container_two, fragmentTwoCoinsList);
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

package com.example.michael.kassenautomat_dhbw.fragments.mainKassenautomat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.dialogs.MyFragment;
import com.example.michael.kassenautomat_dhbw.fragments.one.FragmentOneShowAutomata;
import com.example.michael.kassenautomat_dhbw.fragments.three.FragmentThreeUserInformation;
import com.example.michael.kassenautomat_dhbw.fragments.two.FragmentTwoCoinsList;
import com.example.michael.kassenautomat_dhbw.fragments.two.FragmentTwoShowTicketList;


public class Fragment_Kassenautomat extends MyFragment  {

    private FragmentOneShowAutomata fragmentOneShowAutomata;
    private FragmentThreeUserInformation fragmentThreeUserInformation;
    private FragmentTwoShowTicketList fragmentTwoShowTicketList;

    public Fragment_Kassenautomat()
    {

    }
    public Fragment_Kassenautomat(FragmentOneShowAutomata fragmentOneShowAutomata, FragmentTwoShowTicketList fragmentTwoShowTicketList, FragmentThreeUserInformation fragmentThreeUserInformation) {
        this.fragmentTwoShowTicketList = fragmentTwoShowTicketList;
        this.fragmentOneShowAutomata = fragmentOneShowAutomata;
        this.fragmentThreeUserInformation = fragmentThreeUserInformation;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        {
            System.out.println("onCreate: Kassenautomat");
            transaction.add(R.id.kassen_main_container_one, this.fragmentOneShowAutomata);
            transaction.add(R.id.kassen_main_container_two, this.fragmentTwoShowTicketList);
            transaction.add(R.id.kasseny_main_container_three, this.fragmentThreeUserInformation);

        }
        transaction.commit();
    }

    public FragmentManager getKassenautomatFragmentManager()
    {
        return getChildFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_kassenautomat, container, false);
    }
}

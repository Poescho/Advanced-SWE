package com.example.michael.kassenautomat_dhbw.fragments.mainKassenautomat;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.dialogs.MyFragment;
import com.example.michael.kassenautomat_dhbw.fragments.one.FragmentAutomat;
import com.example.michael.kassenautomat_dhbw.fragments.three.FragmentUserInformation;
import com.example.michael.kassenautomat_dhbw.fragments.two.FragmentTicketList;


public class FragmentKassenautomat extends MyFragment  {

    private FragmentAutomat fragmentAutomat;
    private FragmentUserInformation fragmentUserInformation;
    private FragmentTicketList fragmentTicketList;

    public FragmentKassenautomat()
    {

    }
    public void setFragmentUserInformation(FragmentUserInformation fragmentUserInformation) {
        this.fragmentUserInformation = fragmentUserInformation;
    }

    public void setFragmentAutomat(FragmentAutomat fragmentAutomat) {
        this.fragmentAutomat = fragmentAutomat;
    }

    public void setFragmentTicketList(FragmentTicketList fragmentTicketList) {
        this.fragmentTicketList = fragmentTicketList;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        {
            transaction.add(R.id.kassen_main_container_one, this.fragmentAutomat);
            transaction.add(R.id.kassen_main_container_two, this.fragmentTicketList);
            transaction.add(R.id.kasseny_main_container_three, this.fragmentUserInformation);

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

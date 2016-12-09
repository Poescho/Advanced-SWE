package com.example.michael.kassenautomat_dhbw.fragments.two;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.michael.kassenautomat_dhbw.list_adapter.CustomListAdapter;
import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.dialogs.MyFragment;

import java.util.List;

public class FragmentTwoShowTicketList extends MyFragment {

    View view;
    ListView list;
    CustomListAdapter customListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub


        List<Ticket> allTickets = mCallback.getKassenautomatContext().getDatabaseConnection().getTicketsFromUser(mCallback.getKassenautomatContext().getCurrentUser().getId());

        view = inflater.inflate(R.layout.fragment_two_ticket_list, container, false);

        customListAdapter = new CustomListAdapter(mCallback.getKassenautomatContext().getContext(), allTickets, mCallback);


        list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(customListAdapter);
        return view;
    }

    public void updateTicketList() {
       List<Ticket> allTickets = mCallback.getKassenautomatContext().getDatabaseConnection().getTicketsFromUser(mCallback.getKassenautomatContext().getCurrentUser().getId());
        customListAdapter.clear();
        customListAdapter.addAll(allTickets);
    }
}

package com.example.michael.kassenautomat_dhbw.fragments.two;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.datatypes.Quittung;
import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.dialogs.MyFragment;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;
import com.example.michael.kassenautomat_dhbw.list_adapter.CustomListAdapter;
import com.example.michael.kassenautomat_dhbw.list_adapter.QuittungsListAdapter;

import java.util.LinkedList;
import java.util.List;

public class FragmentQuittungsList extends MyFragment {

    View view;
    ListView list;
    QuittungsListAdapter quittungsListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        List<Ticket> allTickets = mCallback.getKassenautomatContext().getDatabaseConnection().getTicketsFromUser(mCallback.getKassenautomatContext().getCurrentUser().getId());

        LinkedList<Quittung> allQuittungs = new LinkedList<Quittung>();
        for(Ticket ticket : allTickets){
            try {
                allQuittungs.add(ticket.getQuittung(mCallback.getKassenautomatContext().getDatabaseConnection()));
            } catch (DbException e) {
                e.printStackTrace();
            }
        }

        view = inflater.inflate(R.layout.fragment_two_ticket_list, container, false);

        quittungsListAdapter = new QuittungsListAdapter(mCallback.getKassenautomatContext().getContext(), allQuittungs, mCallback);


        list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(quittungsListAdapter);
        return view;
    }

    public void updateQuittungsList() throws DbException {
        List<Ticket> allTickets = mCallback.getKassenautomatContext().getDatabaseConnection().getTicketsFromUser(mCallback.getKassenautomatContext().getCurrentUser().getId());

        LinkedList<Quittung> allQuittungs = new LinkedList<Quittung>();
        for(Ticket ticket : allTickets){
            try {
                allQuittungs.add(ticket.getQuittung(mCallback.getKassenautomatContext().getDatabaseConnection()));
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        quittungsListAdapter.clear();
        quittungsListAdapter.addAll(allQuittungs);
    }
}

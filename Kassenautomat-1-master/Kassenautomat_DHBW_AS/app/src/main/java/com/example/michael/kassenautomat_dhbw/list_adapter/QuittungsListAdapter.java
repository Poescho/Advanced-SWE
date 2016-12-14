package com.example.michael.kassenautomat_dhbw.list_adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.michael.kassenautomat_dhbw.OnButtonClickedCallback;
import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.datatypes.Quittung;
import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.dialogs.EditTicketDialog;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;
import com.example.michael.kassenautomat_dhbw.fragments.one.FragmentAutomat;
import com.example.michael.kassenautomat_dhbw.util.TicketDragShadow;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Luca on 13.12.2016.
 */

public class QuittungsListAdapter extends MyAdapter<Quittung> {

    public QuittungsListAdapter(Context context, List<Quittung> quittungs, OnButtonClickedCallback mCallback) {
        super(context, R.layout.list_quittungs, quittungs, mCallback);
    }

    public View getView(final int position, View view, ViewGroup parent) {

        System.out.println(position + " " + getCount());

        final View rowView = ((Activity) context).getLayoutInflater().inflate(R.layout.list_quittungs, null, true);

        //LayoutInflater inflater = context.getLayoutInflater();
        //View rowView=inflater.inflate(R.layout.list_ticket_row, null, true);

        TextView txtParkdauer = (TextView) rowView.findViewById(R.id.parkdauer);
        TextView txtShowDate = (TextView) rowView.findViewById(R.id.showDate);
        TextView txtPreis = (TextView) rowView.findViewById(R.id.preis);

        ImageButton iBtnDel = (ImageButton) rowView.findViewById(R.id.del);

        final LinearLayout linLayBackground = (LinearLayout) rowView.findViewById(R.id.list_ticket_row_background);

        final String date = DateFormat.getDateInstance().format(new Date(getItem(position).getTimestamp()));

        txtParkdauer.setText("Parkdauer: " + getItem(position).getDauer());
        txtShowDate.setText("Datum: " + getItem(position).getTimestamp());
        txtPreis.setText("Preis: "+ getItem(position).getPrice());

        iBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mCallback.getKassenautomatContext().getDatabaseConnection().deleteQuittung(getItem(position).getId());
                    remove(getItem(position));
                } catch (DbException e) {
                    e.printStackTrace();
                }
                mCallback.setTextOfDisplay(R.string.quittung_deleted);
                try {
                    mCallback.updateQuittungsList();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });

        return rowView;
    }
}

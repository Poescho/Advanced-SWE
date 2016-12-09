package com.example.michael.kassenautomat_dhbw.list_adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.michael.kassenautomat_dhbw.OnButtonClickedCallback;
import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.dialogs.EditTicketDialog;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;
import com.example.michael.kassenautomat_dhbw.fragments.one.FragmentOneShowAutomata;
import com.example.michael.kassenautomat_dhbw.util.TicketDragShadow;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class CustomListAdapter extends MyAdapter<Ticket> {


    public CustomListAdapter(Context context, List<Ticket> tickets, OnButtonClickedCallback mCallback) {
        super(context, R.layout.list_ticket_row, tickets, mCallback);
    }

    public View getView(final int position, View view, ViewGroup parent) {

        System.out.println(position + " " + getCount());

        final View rowView = ((Activity) context).getLayoutInflater().inflate(R.layout.list_ticket_row, null, true);

        //LayoutInflater inflater = context.getLayoutInflater();
        //View rowView=inflater.inflate(R.layout.list_ticket_row, null, true);

        TextView txtShowID = (TextView) rowView.findViewById(R.id.showTicketID);
        TextView txtShowDate = (TextView) rowView.findViewById(R.id.showDate);
        TextView txtShowInfo = (TextView) rowView.findViewById(R.id.showInfo);

        ImageButton iBtnDel = (ImageButton) rowView.findViewById(R.id.del);
        ImageButton iBtnPay = (ImageButton) rowView.findViewById(R.id.pay);
        ImageButton iBtnInvalid = (ImageButton) rowView.findViewById(R.id.invalid);
        ImageButton iBtnEdit = (ImageButton) rowView.findViewById(R.id.edit);

        final LinearLayout linLayBackground = (LinearLayout) rowView.findViewById(R.id.list_ticket_row_background);


        //Click listener for pay button and background.
        View.OnClickListener payClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(mCallback.getKassenautomatContext().getAutomata().getParkCoins() <= 0) {
                    mCallback.setTextOfDisplay(R.string.no_more_park_coins);
                    return;
                }
                System.out.println("Bezahlen");
                mCallback.startPayment(getItem(position));
            }
        };



        final String date = DateFormat.getDateInstance().format(new Date(getItem(position).getTimestamp()));
        String valid;
        String paid;
        if(getItem(position).isValid()){
            valid = "gültig";
        } else {
            valid = "ungültig";
        }
        if(getItem(position).isPaid()){
            paid = "bezahlt";
        } else {
            paid = "unbezahlt";
        }


        txtShowID.setText("ID:" + getItem(position).getId() + "");
        txtShowDate.setText("Datum:" + date);
        txtShowInfo.setText("Status: " + valid + " / " + paid);


        iBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mCallback.getKassenautomatContext().getDatabaseConnection().deleteTicket(getItem(position).getId());
                    remove(getItem(position));
                } catch (DbException e) {
                    e.printStackTrace();
                }
                mCallback.setTextOfDisplay(R.string.ticket_deleted);
                mCallback.updateTicketList();
            }
        });

        iBtnPay.setOnClickListener(payClickListener);

        iBtnInvalid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mCallback.getKassenautomatContext().getCurrentUser().setTicketInvalid(getItem(position), mCallback.getKassenautomatContext().getDatabaseConnection());
                } catch (DbException e) {
                    e.printStackTrace();
                }

                mCallback.setTextOfDisplay(R.string.ticket_now_invalid);
                mCallback.updateTicketList();
            }
        });

        iBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTicketDialog dialog = new EditTicketDialog();
                Bundle bundle = EditTicketDialog.getBundle(getItem(position).getId());
                dialog.setArguments(bundle);
                dialog.setmCallback(mCallback);
                dialog.show(mCallback.getKassenautomatContext().getFragmentManager(), "Edit dialog");
                mCallback.updateTicketList();
            }
        });


        linLayBackground.setOnClickListener(payClickListener);
        linLayBackground.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData dragData = ClipData.newPlainText(FragmentOneShowAutomata.TICKET_DRAG_DESC.getLabel(), getItem(position).getId() + "");

                TicketDragShadow coinDragShadow = new TicketDragShadow(linLayBackground);

                v.startDrag(dragData, coinDragShadow, null, 0);

                return true;
            }
        });


        return rowView;
    };
}






























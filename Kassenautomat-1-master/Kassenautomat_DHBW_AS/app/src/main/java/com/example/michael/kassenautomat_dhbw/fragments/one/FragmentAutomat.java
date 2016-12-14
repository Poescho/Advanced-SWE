package com.example.michael.kassenautomat_dhbw.fragments.one;

import android.content.ClipDescription;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.michael.kassenautomat_dhbw.MainActivity;
import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.datatypes.Automata;
import com.example.michael.kassenautomat_dhbw.datatypes.Money;
import com.example.michael.kassenautomat_dhbw.datatypes.Quittung;
import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.datatypes.User;
import com.example.michael.kassenautomat_dhbw.dialogs.MyFragment;
import com.example.michael.kassenautomat_dhbw.dialogs.TextDialog;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;
import com.example.michael.kassenautomat_dhbw.fragments.mainKassenautomat.FragmentKassenautomat;
import com.example.michael.kassenautomat_dhbw.fragments.maintain.FragmentMaintain;
import com.example.michael.kassenautomat_dhbw.fragments.three.FragmentUserInformation;
import com.example.michael.kassenautomat_dhbw.fragments.two.FragmentCoinList;
import com.example.michael.kassenautomat_dhbw.fragments.two.FragmentQuittungsList;
import com.example.michael.kassenautomat_dhbw.fragments.two.FragmentTicketList;
import com.example.michael.kassenautomat_dhbw.util.DefaultValuesHandler;

import java.sql.Struct;
import java.util.Date;
import java.util.Random;


/**
 * Created by administrator on 06.04.16.
 */
public class FragmentAutomat extends MyFragment {

    private Ticket ticketToPay;
    private int centsToPay;
    private View view;

    private Button iBtnReset;

    public static final ClipDescription COIN_DRAG_DESC = new ClipDescription("COIN_DRAG_DESC", new String[] {ClipDescription.MIMETYPE_TEXT_PLAIN});
    public static final ClipDescription TICKET_DRAG_DESC = new ClipDescription("TICKET_DRAG_DESC", new String[] {ClipDescription.MIMETYPE_TEXT_PLAIN});

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {



        view = inflater.inflate(R.layout.fragment_one_automata2, container, false);

        final TextView txtDisplay = (TextView)view.findViewById(R.id.fragment_one_show_automata_text_view_display2);
        iBtnReset = (Button) view.findViewById(R.id.btn_automat_zurücksetzen);
        final Button iBtnTakeTicket = (Button)view.findViewById(R.id.btn_ticket_lösen);
        final Button toggle = (Button) view.findViewById(R.id.btn_toggle);



        iBtnTakeTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mCallback.getKassenautomatContext().getCurrentUser().takeTicket(mCallback.getKassenautomatContext().getDatabaseConnection());
                    mCallback.updateTicketList();
                } catch (DbException e) {
                    e.printStackTrace();
                }

                txtDisplay.setText(R.string.ticket_taken);
                mCallback.updateTicketList();
            }
        });


        iBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.setTextOfDisplay(R.id.reset);
                mCallback.endPayment(centsToPay);
            }
        });

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggle.getText().equals("Quittungen anzeigen")){
                    mCallback.changeToQuittungsList();
                } else if(toggle.getText().equals("Tickets anzeigen")){
                    mCallback.returnToTicketList();
                }
            }
        });

        return view;


    }

    public static long getCentPriceForDuration(long minutes, long hours, long days) {
        if(days > 0) {
            return DefaultValuesHandler.getBasePriceDay(mCallback.getKassenautomatContext()) + days * DefaultValuesHandler.getPricePerDay(mCallback.getKassenautomatContext());
        } else if (hours > 0) {
            return DefaultValuesHandler.getBasePriceHour(mCallback.getKassenautomatContext()) + hours * DefaultValuesHandler.getPricePerHour(mCallback.getKassenautomatContext());
        } else {
            return DefaultValuesHandler.getBasePrice(mCallback.getKassenautomatContext()) + minutes * DefaultValuesHandler.getPricePerMinute(mCallback.getKassenautomatContext());
        }
    }

    public void showToPayValue(int paidCents) {

        Random random = new Random();
        random.setSeed(new Date().getTime() * 2);
        if(random.nextInt(10) == random.nextInt(10)) {
            mCallback.setTextOfDisplay(R.string.fake_coin);
            return;
        }

        TextView txtDisplay = (TextView)view.findViewById(R.id.fragment_one_show_automata_text_view_display2);


        Automata automata = mCallback.getKassenautomatContext().getAutomata();
        User user = mCallback.getKassenautomatContext().getCurrentUser();
        Money userMoney = user.getMoney();
        Money automataMoney = automata.getMoney();


        switch (paidCents) {
            case 5:
                if(userMoney.getFiveCent() == 0) {
                    return;
                }
                automataMoney.increaseFiveCent();
                userMoney.decreaseFiveCent();
                break;

            case 10:
                if(userMoney.getTenCent() == 0) {
                    return;
                }
                automataMoney.increaseTenCent();
                userMoney.decreaseTenCent();
                break;

            case 20:
                if(userMoney.getTwentyCent() == 0) {
                    return;
                }
                automataMoney.increaseTwentyCent();
                userMoney.decreaseTwentyCent();
                break;

            case 50:
                if(userMoney.getFiftyCent() == 0) {
                    return;
                }
                automataMoney.increaseFiftyCent();
                userMoney.decreaseFiftyCent();
                break;

            case 100:
                if(userMoney.getOneEuro() == 0) {
                    return;
                }
                automataMoney.increaseOneEuro();
                userMoney.decreaseOneEuro();
                break;

            case 200:
                if(userMoney.getTwoEuro() == 0) {
                    return;
                }
                automataMoney.increaseTwoEuro();
                userMoney.decreaseTwoEuro();
                break;

            default:
                return;
        }

        user.setMoney(userMoney);
        automata.setMoney(automataMoney);


        try {
            mCallback.getKassenautomatContext().updateCurrentUser(user);
            mCallback.getKassenautomatContext().updateAutomata(automata);
        } catch (DbException e) {
            e.printStackTrace();
        }



        mCallback.updateCoinList();

        centsToPay = centsToPay - paidCents;
        if(centsToPay > 0) {
            refreshOutput();
        } else {

            random.setSeed(new Date().getTime());

            if(random.nextInt(10) == random.nextInt(10)) {
                txtDisplay.setText(R.string.park_coin_stuck);
                iBtnReset.callOnClick();
                return;
            }

            user.addParkCoin();

            txtDisplay.setText("Ticket wurde bezahlt." + "\n\nWechselgeld: " + Math.abs(centsToPay/100) + "," + Math.abs(centsToPay % 100) + "€");
            automata.setParkCoins(automata.getParkCoins() - 1);

            try {
                mCallback.getKassenautomatContext().updateAutomata(automata);
            } catch (DbException e) {
                e.printStackTrace();
            }

            ticketToPay.setPaid(1);
            try {
                mCallback.getKassenautomatContext().getDatabaseConnection().updateTicket(ticketToPay);
            } catch (DbException e) {
                e.printStackTrace();
            }

            try {
                mCallback.getKassenautomatContext().updateCurrentUser(user);
            } catch (DbException e) {
                e.printStackTrace();
            }

            FragmentMaintain.update();
            mCallback.setLastPaidTicketId(ticketToPay.getId());
            mCallback.endPayment(centsToPay);
        }
    }

    private void refreshOutput() {
        TextView txtDisplay = (TextView)view.findViewById(R.id.fragment_one_show_automata_text_view_display2);
        String textToDisplay = "Ticket bezahlen: \n" + centsToPay/100 + "," + centsToPay % 100 + "€";
        txtDisplay.setText(textToDisplay);
    }

    public void setTextOfDisplay(int stringId) {
        TextView txtDisplay = (TextView)view.findViewById(R.id.fragment_one_show_automata_text_view_display2);

        txtDisplay.setText(stringId);
    }

    public void setTextOfDisplayString(String text) {
        TextView txtDisplay = (TextView)view.findViewById(R.id.fragment_one_show_automata_text_view_display2);

        txtDisplay.setText(text);
    }


    public void setTicketToPay(Ticket ticketToPay) {
        this.ticketToPay = ticketToPay;

        if(ticketToPay != null) {
          long milliSeconds = System.currentTimeMillis() - ticketToPay.getTimestamp();
                long seconds = milliSeconds / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;


                centsToPay = (int) getCentPriceForDuration(minutes, hours, days);
                if (centsToPay < 0) {
                    centsToPay = 0;
                }

                refreshOutput();
            }


    }

    public int getCentsToPay() {
        return centsToPay;
    }

    public boolean hasTicketToPay() {
        return ticketToPay != null;
    }

}

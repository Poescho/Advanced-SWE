package com.example.michael.kassenautomat_dhbw;

import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;

/**
 * Created by nathalie on 15.05.16.
 */
public interface OnButtonClickedCallback {
    void onPayForTicket(int moneyValue);
    void setTextOfDisplay(int stringId);
    void updateTicketList();
    void updateQuittungsList() throws DbException;
    void updateCoinList();
    void returnToTicketList();
    void changeToQuittungsList();
    void endPayment(int payback);
    void endPayment();
    void startPayment(Ticket ticket);
    KassenautomatContext getKassenautomatContext();
    void setLastPaidTicketId(long id);
    long getLastPaidTicketId();
}

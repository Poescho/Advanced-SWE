package com.example.michael.kassenautomat_dhbw.datatypes;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.michael.kassenautomat_dhbw.database.DatabaseConnection;
import com.example.michael.kassenautomat_dhbw.database.tables.TableTicket;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;
import com.example.michael.kassenautomat_dhbw.fragments.one.FragmentAutomat;

/**
 * Created by Michael on 19.04.2016.
 *
 * Plain old data structure.
 * Will hold a ticket, that contains a long id, long userId, long timestamp and int paid.
 *
 * id -> Id of the ticket in the database
 * userId -> Id of the user that owns the ticket
 * timestamp -> Creation date
 * paid -> false / 0 => not paid; true / !=0 => paid
 */
public class Ticket {

    private long id;
    private long userId;
    private long timestamp;
    private int valid;
    private int paid;


    public Ticket(long id, long userId, long timestamp, int valid, int paid) {
        this.id = id;
        this.userId = userId;
        this.timestamp = timestamp;
        this.valid = valid;
        this.paid = paid;
    }

    public Ticket(long id, long userId, long timestamp, boolean valid, boolean paid) {
        this.id = id;
        this.userId = userId;
        this.timestamp = timestamp;
        this.valid = valid ? 1 : 0;
        this.paid = paid ? 1 : 0;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(TableTicket.DB_COLUMN_ID, id);
        cv.put(TableTicket.DB_COLUMN_USER_ID, userId);
        cv.put(TableTicket.DB_COLUMN_DATE, timestamp);
        cv.put(TableTicket.DB_COLUMN_VALID, valid);
        cv.put(TableTicket.DB_COLUMN_PAID, paid);
        return cv;
    }

    public static ContentValues getContentValues(long userId, long timestamp, int valid, int paid) {
        ContentValues cv = new ContentValues();
        cv.put(TableTicket.DB_COLUMN_USER_ID, userId);
        cv.put(TableTicket.DB_COLUMN_DATE, timestamp);
        cv.put(TableTicket.DB_COLUMN_VALID, valid);
        cv.put(TableTicket.DB_COLUMN_PAID, paid);
        return cv;
    }

    public Quittung takeQuittung(DatabaseConnection databaseConnection) throws DbException {
        return databaseConnection.addQuittung(this.id, System.currentTimeMillis(), this.timestamp, getPrice(this), getDuration());
    }

    public Quittung getQuittung(DatabaseConnection databaseConnection) throws DbException {
        return databaseConnection.getQuittung(this.id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid != 0;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public boolean isPaid() {
        return paid != 0;
    }

    private long getDuration() { return this.timestamp - System.currentTimeMillis(); }

    private long getPrice(Ticket ticket) {
        long centsToPay;

        if(ticket != null) {
            long milliSeconds = System.currentTimeMillis() - ticket.getTimestamp();
            long seconds = milliSeconds / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;


            centsToPay = (int) FragmentAutomat.getCentPriceForDuration(minutes, hours, days);
            if (centsToPay < 0) {
                return centsToPay = 0;
            }

            return centsToPay;
        }
        return 0;
    }


}

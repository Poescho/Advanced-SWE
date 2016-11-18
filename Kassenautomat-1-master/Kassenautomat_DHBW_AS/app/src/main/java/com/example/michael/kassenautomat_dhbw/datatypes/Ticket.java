package com.example.michael.kassenautomat_dhbw.datatypes;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.michael.kassenautomat_dhbw.database.tables.TableTicket;

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
}

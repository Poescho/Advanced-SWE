package com.example.michael.kassenautomat_dhbw.datatypes;

import android.content.ContentValues;

import com.example.michael.kassenautomat_dhbw.database.tables.TableQuittung;

/**
 * Created by Luca on 13.12.2016.
 */

public class Quittung {
    private long id;
    private long ticketId;
    private long timestamp;
    private long timestamp_ticket;
    private long price;
    private long dauer;

    public Quittung(long id, long ticketId, long timestamp, long timestamp_ticket, long price, long dauer) {
        this.id = id;
        this.ticketId = ticketId;
        this.timestamp = timestamp;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(TableQuittung.DB_COLUMN_ID, id);
        cv.put(TableQuittung.DB_COLUMN_TICKET_ID, ticketId);
        cv.put(TableQuittung.DB_COLUMN_DATE, timestamp);
        cv.put(TableQuittung.DB_COLUMN_DATE_TICKET, timestamp_ticket);
        cv.put(TableQuittung.DB_COLUMN_PRICE, price);
        cv.put(TableQuittung.DB_COLUMN_DAUER, dauer);
        return cv;
    }

    public static ContentValues getContentValues(long ticketId, long timestamp, long timestamp_ticket, long price, long dauer) {
        ContentValues cv = new ContentValues();
        cv.put(TableQuittung.DB_COLUMN_TICKET_ID, ticketId);
        cv.put(TableQuittung.DB_COLUMN_DATE, timestamp);
        cv.put(TableQuittung.DB_COLUMN_DATE_TICKET, timestamp_ticket);
        cv.put(TableQuittung.DB_COLUMN_PRICE, price);
        cv.put(TableQuittung.DB_COLUMN_DAUER, dauer);
        return cv;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTicketIdId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp_ticket() {
        return timestamp_ticket;
    }

    public void setTimestamp_ticket(long timestamp_ticket) {
        this.timestamp_ticket = timestamp_ticket;
    }

    public long getPrice() { return price; }

    public void setPrice(long price) { this.price = price; }

    public long getDauer() { return dauer; }

    public void setDauer(long dauer) { this.dauer = dauer; }

}

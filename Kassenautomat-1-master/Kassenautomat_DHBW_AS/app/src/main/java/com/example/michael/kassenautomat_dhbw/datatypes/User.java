package com.example.michael.kassenautomat_dhbw.datatypes;

import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.design.widget.TabLayout;

import com.example.michael.kassenautomat_dhbw.database.DatabaseConnection;
import com.example.michael.kassenautomat_dhbw.database.tables.TableUser;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;
import com.example.michael.kassenautomat_dhbw.util.DefaultValuesHandler;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by Michael on 16.04.2016.
 *
 * Plain old data structure
 */
public class User {

    private long id;
    private Money money;
    private long moneybankId;
    private long parkCoins;

    public User(long id, Money money, long moneybankId, long parkCoins) {
        this.id = id;
        this.money = money;
        this.moneybankId = moneybankId;
        this.parkCoins = parkCoins;
    }

    public  ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(TableUser.DB_COLUMN_ID, id);
        cv.put(TableUser.DB_COLUMN_MONEYBANK_ID, moneybankId);
        cv.put(TableUser.DB_COLUMN_PARK_COINS, parkCoins);
        return cv;
    }

    public static ContentValues getContentValues(long moneybankId) {
        ContentValues cv = new ContentValues();
        cv.put(TableUser.DB_COLUMN_MONEYBANK_ID, moneybankId);
        cv.put(TableUser.DB_COLUMN_PARK_COINS, DefaultValuesHandler.DEFAULT_PARK_COINS_USER);
        return cv;
    }


    public void setTicketInvalid(Ticket ticket, DatabaseConnection databaseConnection) throws DbException {
        Ticket newTicket = new Ticket(ticket.getId(), this.id, ticket.getTimestamp(), false, ticket.isPaid());
        databaseConnection.updateTicket(newTicket);
    }

    public void setTicketPaid(Ticket ticket, DatabaseConnection databaseConnection) throws DbException {
        Ticket newTicket = new Ticket(ticket.getId(), this.id, ticket.getTimestamp(), ticket.isValid(), true);
        databaseConnection.updateTicket(newTicket);
    }

    public Ticket takeTicket(DatabaseConnection databaseConnection) throws DbException {
        return databaseConnection.addTicket(this.id, System.currentTimeMillis(), true, false);
    }



    //Getters and Setters
    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMoneybankId() {
        System.out.println("USER MONEYBANK: " + moneybankId);
        return moneybankId;
    }

    public void setMoneybankId(long moneybankId) {
        this.moneybankId = moneybankId;
    }

    public void setParkCoins(long parkCoins) {
        this.parkCoins = parkCoins;
    }

    public long getParkCoins() {
        return parkCoins;
    }

    public void addParkCoin() {
        this.parkCoins = this.parkCoins + 1;
    }
}

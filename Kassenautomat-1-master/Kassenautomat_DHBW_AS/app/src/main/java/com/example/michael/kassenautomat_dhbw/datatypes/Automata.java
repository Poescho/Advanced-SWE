package com.example.michael.kassenautomat_dhbw.datatypes;

import android.content.ContentValues;

import com.example.michael.kassenautomat_dhbw.database.tables.TableAutomata;


/**
 * Created by Michael on 14.04.2016.
 */
public class Automata {

    private long moneybankId;
    private long id;
    private long parkCoins;
    private Money money;

    public Automata(long id, long moneybankId, long parkCoins, Money money) {
        this.money = money;
        this.moneybankId = moneybankId;
        this.id = id;
        this.parkCoins = parkCoins;
    }

    public static ContentValues getContentValues(long moneybankId, long parkCoins) {
        ContentValues cv = new ContentValues();
        cv.put(TableAutomata.DB_COLUMN_MONEYBANK_ID, moneybankId);
        cv.put(TableAutomata.DB_COLUMN_PARK_COINS, parkCoins);
        return cv;
    }


    public long getMoneybankId() {
        System.out.println("AUTOMATA MONEYBANK: " + moneybankId);
        return moneybankId;
    }

    public long getId() {
        return id;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public void setParkCoins(long parkCoins) {
        this.parkCoins = parkCoins;
    }

    public long getParkCoins() {
        return parkCoins;
    }
}

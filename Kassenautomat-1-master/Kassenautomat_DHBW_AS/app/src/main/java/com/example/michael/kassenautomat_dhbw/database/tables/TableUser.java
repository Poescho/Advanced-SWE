package com.example.michael.kassenautomat_dhbw.database.tables;


/**
 * Created by Michael on 07.04.2016.
 *
 *
 * Contains the constants for the table "User" in the database,
 * like table name and sql statement to create the table.
 */
public class TableUser {

    public static final String DB_TABLE_NAME = "User";

    public static final String DB_COLUMN_ID = "_id";
    public static final String DB_COLUMN_MONEYBANK_ID = "MoneybankID";
    public static final String DB_COLUMN_PARK_COINS = "ParkCoins";

    public static final String TABLE_CREATE_STATEMENT = "create table " + DB_TABLE_NAME
            + "(" + DB_COLUMN_ID + " integer primary key autoincrement, "
            + DB_COLUMN_MONEYBANK_ID + " integer not null,"
            + DB_COLUMN_PARK_COINS + " integer not null)";

    public static final String TABLE_DROP_STATEMENT = "drop table " + DB_TABLE_NAME;

    private TableUser() {}
}

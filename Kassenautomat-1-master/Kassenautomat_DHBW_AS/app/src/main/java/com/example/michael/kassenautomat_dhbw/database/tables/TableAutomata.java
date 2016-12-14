package com.example.michael.kassenautomat_dhbw.database.tables;

/**
 * Contains the constants for the table "Automata" in the database,
 * like table name and sql statement to create the table.
 */
public class TableAutomata {

    public static final String DB_TABLE_NAME = "Automata";

    public static final String DB_COLUMN_ID = "_id";
    public static final String DB_COLUMN_MONEYBANK_ID = "MoneybankID";
    public static final String DB_COLUMN_PARK_COINS = "ParkCoins";

    public static final String TABLE_CREATE_STATEMENT = "create table " + DB_TABLE_NAME
            + "(" + DB_COLUMN_ID + " integer primary key, "
            + DB_COLUMN_MONEYBANK_ID + " integer,"
            + DB_COLUMN_PARK_COINS + " integer)";

    public static final String TABLE_DROP_STATEMENT = "drop table " + DB_TABLE_NAME;

    private TableAutomata() {}
}

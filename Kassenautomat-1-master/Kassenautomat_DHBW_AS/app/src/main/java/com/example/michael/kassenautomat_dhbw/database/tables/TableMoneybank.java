package com.example.michael.kassenautomat_dhbw.database.tables;

import android.content.ContentValues;

import com.example.michael.kassenautomat_dhbw.datatypes.Money;

/**
 * Created by Michael on 11.04.2016.
 *
 * Contains the constants for the table "Moneybank" in the database,
 * like table name and sql statement to create the table.
 *
 * getContentValue will return the content values of an Money object, that can be
 * used for querying the database.
 */
public class TableMoneybank {

    public static final String DB_TABLE_NAME = "Moneybank";

    public static final String DB_COLUMN_ID = "_id";
    public static final String DB_COLUMN_TWO_EURO = "twoEuro";
    public static final String DB_COLUMN_ONE_EURO = "oneEuro";
    public static final String DB_COLUMN_FIFTY_CENT = "fiftyCent";
    public static final String DB_COLUMN_TWENTY_CENT = "twentyCent";
    public static final String DB_COLUMN_TEN_CENT = "tenCent";
    public static final String DB_COLUMN_FIVE_CENT = "fiveCent";

    public static final String TABLE_CREATE_STATEMENT = "create table " + DB_TABLE_NAME
            + "(" + DB_COLUMN_ID + " integer primary key autoincrement, "
            + DB_COLUMN_TWO_EURO + " integer not null, "
            + DB_COLUMN_ONE_EURO + " integer not null, "
            + DB_COLUMN_FIFTY_CENT + " integer not null, "
            + DB_COLUMN_TWENTY_CENT + " integer not null, "
            + DB_COLUMN_TEN_CENT + " integer not null, "
            + DB_COLUMN_FIVE_CENT + " integer not null)";

    public static final String TABLE_DROP_STATEMENT = "drop table " + DB_TABLE_NAME;

    private TableMoneybank() {}
}

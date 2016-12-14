package com.example.michael.kassenautomat_dhbw.database.tables;

/**
 * Contains the constants for the table "Automata" in the database,
 * like table name and sql statement to create the table.
 */

public class TableQuittung {
    public static final String DB_TABLE_NAME = "Quittung";

    public static final String DB_COLUMN_ID = "_id";
    public static final String DB_COLUMN_TICKET_ID = "UserId";
    public static final String DB_COLUMN_DATE = "Date";
    public static final String DB_COLUMN_DATE_TICKET = "Ticket Date";
    public static final String DB_COLUMN_PRICE = "Price";
    public static final String DB_COLUMN_DAUER = "Dauer";

    public static final String TABLE_CREATE_STATEMENT = "create table " + DB_TABLE_NAME
            + "(" + DB_COLUMN_ID + " integer primary key not null,"
            + DB_COLUMN_TICKET_ID + " integer not null,"
            + DB_COLUMN_DATE + " integer not null,"
            + DB_COLUMN_DATE_TICKET + " integer not null,"
            + DB_COLUMN_PRICE + " integer not null,"
            + DB_COLUMN_DAUER + " integer not null)";

    public static final String TABLE_DROP_STATEMENT = "drop table " + DB_TABLE_NAME;

    private TableQuittung() {}
}

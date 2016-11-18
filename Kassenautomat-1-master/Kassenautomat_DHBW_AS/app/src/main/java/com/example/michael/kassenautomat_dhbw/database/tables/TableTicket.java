package com.example.michael.kassenautomat_dhbw.database.tables;

/**
 * Created by Michael on 19.04.2016.
 *
 *
 * Contains the constants for the table "Ticket" in the database,
 * like table name and sql statement to create the table.
 */
public class TableTicket {

    public static final String DB_TABLE_NAME = "Ticket";

    public static final String DB_COLUMN_ID = "_id";
    public static final String DB_COLUMN_USER_ID = "UserId";
    public static final String DB_COLUMN_DATE = "Date";
    public static final String DB_COLUMN_VALID = "Valid";
    public static final String DB_COLUMN_PAID = "Paid";

    public static final String TABLE_CREATE_STATEMENT = "create table " + DB_TABLE_NAME
            + "(" + DB_COLUMN_ID + " integer primary key not null,"
            + DB_COLUMN_USER_ID + " integer not null,"
            + DB_COLUMN_DATE + " integer not null,"
            + DB_COLUMN_VALID + " integer not null,"
            + DB_COLUMN_PAID + " integer not null)";

    public static final String TABLE_DROP_STATEMENT = "drop table " + DB_TABLE_NAME;

    private TableTicket() {}
}

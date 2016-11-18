package com.example.michael.kassenautomat_dhbw.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.michael.kassenautomat_dhbw.database.tables.TableAutomata;
import com.example.michael.kassenautomat_dhbw.database.tables.TableMoneybank;
import com.example.michael.kassenautomat_dhbw.database.tables.TableTicket;
import com.example.michael.kassenautomat_dhbw.database.tables.TableUser;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;

/**
 * Created by Michael on 07.04.2016.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    Context context;
    String databaseName = STD_DATABASE_NAME;
    boolean dropDatabase = false;

    public static final String STD_DATABASE_NAME = "kassenautomat";
    public static final int DATABASE_VERSION = 58;
    public static final String SHARED_PREFS_DB_VERSION = "SHARED_PREFS_DB_VERSION";



    public MySQLiteOpenHelper(Context context) {
        super(context, STD_DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.databaseName = STD_DATABASE_NAME;
    }

    public MySQLiteOpenHelper(Context context, String databaseName, boolean dropDatabase) {
        super(context, databaseName, null, DATABASE_VERSION);
        this.context = context;
        this.databaseName = databaseName;
        this.dropDatabase = dropDatabase;
    }

    /**
     * Will execute all create statements for every table
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("ON CREATE DATABASE");

        db.execSQL(TableUser.TABLE_CREATE_STATEMENT);
        db.execSQL(TableMoneybank.TABLE_CREATE_STATEMENT);
        db.execSQL(TableAutomata.TABLE_CREATE_STATEMENT);
        db.execSQL(TableTicket.TABLE_CREATE_STATEMENT);
    }

    /**
     * Will drop the database if newVersion is bigger then oldVersion
     * After that it will create the database anew.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        System.out.println("OldVersion: " + oldVersion + "   NewVersion: " + newVersion);
        if((oldVersion < newVersion) || dropDatabase) {

            db.beginTransaction();
            System.out.println("Update db: '" + databaseName + "'");
            //if(!context.deleteDatabase(databaseName)) {
                System.out.println("Database not deleted");
                try {
                    db.execSQL(TableUser.TABLE_DROP_STATEMENT);
                    System.out.println("Dropped table User");
                } catch (SQLException e) {
                    System.out.println("Exception User");
                    e.printStackTrace();
                }

                try {
                    db.execSQL(TableMoneybank.TABLE_DROP_STATEMENT);
                    System.out.println("Dropped table Moneybank");
                } catch (SQLException e) {
                    System.out.println("Exception Moneybank");
                    e.printStackTrace();
                }

                try {
                    db.execSQL(TableAutomata.TABLE_DROP_STATEMENT);
                    System.out.println("Dropped table Automata");
                } catch (SQLException e) {
                    System.out.println("Exception Automata");
                    e.printStackTrace();
                }

                try {
                    db.execSQL(TableTicket.TABLE_DROP_STATEMENT);
                    System.out.println("Dropped table Ticket");
                } catch (SQLException e) {
                    System.out.println("Exception Ticket");
                    e.printStackTrace();
                }
            //} else {
            //    System.out.println("Database deleted");
            //}

            db.setTransactionSuccessful();
            db.endTransaction();
            onCreate(db);
        }

        context.getSharedPreferences(KassenautomatContext.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putInt(SHARED_PREFS_DB_VERSION, newVersion)
                .commit();
    }

    /**
     * For debugging or tearDowns after a unit test.
     * @param name Name of the database to be deleted.
     */
    public void deleteDatabase(String name) {
        context.deleteDatabase(name);
    }
}

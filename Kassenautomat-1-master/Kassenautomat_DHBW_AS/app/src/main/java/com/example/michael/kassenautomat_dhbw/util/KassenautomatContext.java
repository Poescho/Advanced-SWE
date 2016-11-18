package com.example.michael.kassenautomat_dhbw.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.example.michael.kassenautomat_dhbw.database.DatabaseConnection;
import com.example.michael.kassenautomat_dhbw.database.MySQLiteOpenHelper;
import com.example.michael.kassenautomat_dhbw.datatypes.Automata;
import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.datatypes.User;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;



/**
 * Created by Michael on 10.05.2016.
 */
public class KassenautomatContext {


    public static final long VERSION_PREFS = 39;
    public static final String VERSIONS_PREFS_KEY = "VERSIONS_PREFS_KEY";
    public static final String PREFERENCE_NAME = "KassenautomatSharedPreferences";

    private Context context;
    private DatabaseConnection databaseConnection;
    private User currentUser;
    private Automata automata;

    private FragmentManager fragmentManager;


    public KassenautomatContext(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;

        if(context != null) {
            databaseConnection = new DatabaseConnection(context);

            SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();


            try {
                if (prefs.getLong(VERSIONS_PREFS_KEY, 0) >= VERSION_PREFS && prefs.getInt(MySQLiteOpenHelper.SHARED_PREFS_DB_VERSION, 0) >= MySQLiteOpenHelper.DATABASE_VERSION) {
                    this.automata = databaseConnection.getAutomata();
                    this.currentUser = databaseConnection.getUser();
                } else {
                    this.automata = databaseConnection.addAutomata(this);
                    this.currentUser = databaseConnection.addUser(DefaultValuesHandler.getDefaultUserMoney());
                    editor.putLong(VERSIONS_PREFS_KEY, VERSION_PREFS);
                }
            } catch (DbException e) {
                e.printStackTrace();
            }

            if (automata == null) {
                Toast.makeText(context, "Automata is null.", Toast.LENGTH_LONG).show();
            }


            editor.apply();
        }
    }


    public Automata updateAutomata(Automata automata) throws DbException {
        this.automata = databaseConnection.updateAutomata(automata);
        return automata;
    }

    public User updateCurrentUser(User user) throws DbException {
        this.currentUser = databaseConnection.updateUser(user);
        return currentUser;
    }

    public User resetUser() throws DbException {

        User user = databaseConnection.resetUser(currentUser);

        currentUser = user;
        return user;
    }


    public Automata getAutomata() { return automata; }

    public Context getContext() {
        return context;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }
}

package com.example.michael.kassenautomat_dhbw.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.michael.kassenautomat_dhbw.datatypes.Automata;
import com.example.michael.kassenautomat_dhbw.util.DefaultValuesHandler;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;
import com.example.michael.kassenautomat_dhbw.database.tables.TableAutomata;
import com.example.michael.kassenautomat_dhbw.database.tables.TableUser;
import com.example.michael.kassenautomat_dhbw.database.tables.TableMoneybank;
import com.example.michael.kassenautomat_dhbw.database.tables.TableTicket;
import com.example.michael.kassenautomat_dhbw.datatypes.Money;
import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.datatypes.User;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 07.04.2016.
 *
 * Class creates a database and manages the queries on the database in various methods.
 *
 * The top level is there to handle the exceptions.
 * Be aware that some methods may call private methods which could throw exceptions too,
 * so do not implement solutions for an exception without really checking what went wrong.
 */
public class DatabaseConnection {

    MySQLiteOpenHelper database;
    SQLiteDatabase writeDatabase;

    /**
     * @param context Is there to save the database in the right place. Meaning the context of the application.
     */
    public DatabaseConnection(Context context) {
        database = new MySQLiteOpenHelper(context);
        writeDatabase = database.getWritableDatabase();
    }


    /**
     * This constructor will connect to a database with the given name.
     * If it does not exist, then the database will be created.
     * This is mostly used for tests and the like.
     * @param context Is there to save the database in the right place. Meaning the context of the application.
     * @param databaseName The name of the database to be created and connected to.
     * @param dropDatabase If true then the database will forcefully be deleted and created anew.
     */
    public DatabaseConnection(Context context, String databaseName, boolean dropDatabase) {
        database = new MySQLiteOpenHelper(context, databaseName, dropDatabase);
        writeDatabase = database.getWritableDatabase();
    }

    //GET-METHODS
    /**
     * @param id The id for the user. This can either be a customer or a worker.
     * @return A money object with all the values from the row in the database for the user with the corresponding id.
     * @throws DbException If the database is not reachable, or not 6 columns are returned (which is quiet unlikely)
     */
    public Money getMoneyFromUser(long id) throws DbException{

        Cursor c =  writeDatabase.query(TableUser.DB_TABLE_NAME + "," + TableMoneybank.DB_TABLE_NAME,
                new String[]{TableMoneybank.DB_TABLE_NAME + "." + TableMoneybank.DB_COLUMN_ID,
                TableUser.DB_TABLE_NAME + "." + TableUser.DB_COLUMN_ID,
                TableMoneybank.DB_COLUMN_TWO_EURO, TableMoneybank.DB_COLUMN_ONE_EURO, TableMoneybank.DB_COLUMN_FIFTY_CENT,
                TableMoneybank.DB_COLUMN_TWENTY_CENT, TableMoneybank.DB_COLUMN_TEN_CENT, TableMoneybank.DB_COLUMN_FIVE_CENT },
                TableUser.DB_TABLE_NAME + "." + TableUser.DB_COLUMN_ID + " = " + id + " and " +
                        TableMoneybank.DB_TABLE_NAME + "." + TableMoneybank.DB_COLUMN_ID + " = " + id,
                null, null, null, null);
        if(c.getCount() > 0) {
            c.moveToFirst();
            Money money = new Money(c.getInt(2), c.getInt(3),  c.getInt(4),  c.getInt(5),  c.getInt(6),  c.getInt(7));
            c.close();
            return money;
        }
        throw new DbException("No user with id " + id + " found.");
    }

    private Money getMoney(long id) throws DbException {
        Cursor c =  writeDatabase.query(TableMoneybank.DB_TABLE_NAME,
                new String[]{TableMoneybank.DB_COLUMN_ID,
                        TableMoneybank.DB_COLUMN_TWO_EURO, TableMoneybank.DB_COLUMN_ONE_EURO, TableMoneybank.DB_COLUMN_FIFTY_CENT,
                        TableMoneybank.DB_COLUMN_TWENTY_CENT, TableMoneybank.DB_COLUMN_TEN_CENT, TableMoneybank.DB_COLUMN_FIFTY_CENT },
                TableMoneybank.DB_COLUMN_ID + " = " + id,
                null, null, null, null);
        if(c.getCount() > 0) {
            c.moveToFirst();
            Money money = new Money(c.getInt(1),  c.getInt(2),  c.getInt(3),  c.getInt(4),  c.getInt(5), c.getInt(6));
            c.close();
            return money;
        }
        throw new DbException("No moneybank row with id " + id + " found.");
    }

    /**
     * @return The only customer. There is only one, so no id has to be given.
     * @throws DbException If the database is not reachable, or no customers exist.
     */
    public User getUser() throws DbException{

        long userId, moneybankId, parkCoins;
        Money customerMoney;

        try {
            userId = getCustomerId();
            moneybankId = getMoneybankIdFromUser(userId);
            customerMoney = getMoney(moneybankId);
            parkCoins = getParkCoinsFromUser(userId);
        } catch (DbException e) {
            throw e;
        }


        return new User(userId, customerMoney, moneybankId, parkCoins);
    }

    /**
     * @return The only customer. There is only one, so no id has to be given.
     * @throws DbException If the database is not reachable, or no customers exist.
     */
    public User getUser(long userId) throws DbException{

        long moneybankId, parkCoins;
        Money customerMoney;

        try {
            moneybankId = getMoneybankIdFromUser(userId);
            customerMoney = getMoney(moneybankId);
            parkCoins = getParkCoinsFromUser(userId);
        } catch (DbException e) {
            throw e;
        }


        return new User(userId, customerMoney, moneybankId, parkCoins);
    }

    /**
     * @param id From the user whose tickets shall be received.
     * @return A list of tickets with all his tickets. If he has no tickets, the list is empty, but not null.
     */
    public List<Ticket> getTicketsFromUser(long id) {
        List<Ticket> ticketList = new ArrayList<>();

        Cursor c = writeDatabase.query(TableTicket.DB_TABLE_NAME,
                new String[] { TableTicket.DB_COLUMN_ID, TableTicket.DB_COLUMN_USER_ID, TableTicket.DB_COLUMN_DATE, TableTicket.DB_COLUMN_VALID, TableTicket.DB_COLUMN_PAID },
                TableTicket.DB_COLUMN_USER_ID + " = " + id,
                null, null, null, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            Ticket newTicket = new Ticket(c.getLong(0), c.getLong(1), c.getLong(2), c.getInt(3), c.getInt(4));
            ticketList.add(newTicket);
            c.moveToNext();
        }
        c.close();
        return ticketList;
    }

    /**
     * @param id of a ticket which should be received.
     * @return A ticket object that was saved in the database.
     * @throws DbException If the database is unreachable or no ticket was found.
     */
    public Ticket getTicket(long id) throws DbException {
        Cursor c = writeDatabase.query(TableTicket.DB_TABLE_NAME,
                new String[]{TableTicket.DB_COLUMN_ID, TableTicket.DB_COLUMN_USER_ID, TableTicket.DB_COLUMN_DATE, TableTicket.DB_COLUMN_VALID, TableTicket.DB_COLUMN_PAID},
                TableTicket.DB_COLUMN_ID + " = " + id,
                null, null, null, null);

        if(c.getCount() > 0) {
            c.moveToFirst();
            Ticket ticket = new Ticket(c.getLong(0), c.getLong(1), c.getLong(2), c.getInt(3), c.getInt(4));
            c.close();
            return ticket;
        }
        c.close();
        throw new DbException("No ticket with id " + id + " found.");
    }


    public Automata getAutomata() throws DbException {
        Cursor c = writeDatabase.query(TableAutomata.DB_TABLE_NAME,
                new String[]{TableAutomata.DB_COLUMN_ID, TableAutomata.DB_COLUMN_MONEYBANK_ID, TableAutomata.DB_COLUMN_PARK_COINS},
                null, null, null, null, null);

        long id, moneybankId, parkCoins;

        c.moveToFirst();
        if(c.getCount() == 1) {
            id = c.getLong(0);
            moneybankId = c.getLong(1);
            parkCoins = c.getLong(2);
            c.close();
        } else {
            c.close();

            throw new DbException("The amount of automatas is not one but " + c.getCount() + ". That is bad.");
        }

        Money money = getMoney(moneybankId);

        return new Automata(id, moneybankId, parkCoins, money);

    }




    //ADD-METHODS



    /**
     * This method should not be called from outside. Will it is private so this should be a given.
     * @param money The money object to store in the database
     * @return The id for the stored money object
     * @throws DbException If the database is not reachable.
     */
    private long addMoneybank(Money money) throws DbException {
        long moneybankId = writeDatabase.insert(TableMoneybank.DB_TABLE_NAME,
                null,
                Money.getContentValues(money));

        if(moneybankId == -1) {
            throw new DbException("Couldn´t insert into " + TableMoneybank.DB_TABLE_NAME + ".");
        }

        System.out.println("MONEYBANKID: " + moneybankId);

        return moneybankId;
    }

    /**
     * This method should not be called from outside. Will it is private so this should be a given.
     * @param moneybankId The moneybankId for the user that should be stored.
     * @return The id of the stored user object
     * @throws DbException If the database is not reachable.
     */
    private long addUser(long moneybankId) throws DbException {

        writeDatabase.beginTransaction();
        long userId = -1;
        try {
            userId = writeDatabase.insert(TableUser.DB_TABLE_NAME,
                    null,
                    User.getContentValues(moneybankId));
            writeDatabase.setTransactionSuccessful();
        } finally {
            writeDatabase.endTransaction();
        }

        if(userId == -1) {
            throw new DbException("Couldn´t insert into " + TableUser.DB_TABLE_NAME + " with moneybankId " + moneybankId + ".");
        }

        return userId;
    }

    /**
     * Adds a new customer. There is only one, so this should only be called one time at the first
     * start of the application.
     * @return The added customer.
     * @throws DbException If the database is not reachable.
     */
    public User addUser(Money money) throws DbException{

        long moneybankId = addMoneybank(money);
        long userId = addUser(moneybankId);
        ContentValues cv = User.getContentValues(userId);

        writeDatabase.beginTransaction();
        long ret = -1;
        try {
            ret = writeDatabase.insert(TableUser.DB_TABLE_NAME,
                    null,
                    cv);
            writeDatabase.setTransactionSuccessful();
            } finally {
            writeDatabase.endTransaction();
        }



        if(ret == -1) {
            throw new DbException("Couldn´t insert into " + TableUser.DB_TABLE_NAME + ".");
        }
        writeDatabase.beginTransaction();
        return new User(userId, money, moneybankId, cv.getAsLong(TableUser.DB_COLUMN_PARK_COINS));
    }

    /**
     * Will not accept a ticket object, because a fully fledged ticket object has an id, which is only
     * known after adding it to the database.
     * @param userId Id of the user that owns the ticket.
     * @param timestamp Timestamp for the ticket
     * @param paid Whether the ticket is paid or not. {@link TableTicket} constants
     * @return A fully fledged ticket with id and everything.
     * @throws DbException If the database is not reachable.
     */
    public Ticket addTicket(long userId, long timestamp, int valid, int paid) throws DbException{

        long ret = writeDatabase.insert(TableTicket.DB_TABLE_NAME,
                null,
                Ticket.getContentValues(userId, timestamp, valid, paid));

        if(ret == -1) {
            throw new DbException("Couldn´t insert into " + TableTicket.DB_TABLE_NAME + ".");
        }

        return new Ticket(ret, userId, timestamp, valid, paid);
    }

    public Ticket addTicket(long userId, long timestamp, boolean valid, boolean paid) throws DbException {
        int iPaid = paid ? 1 : 0;
        int iValid = valid ? 1 : 0;
        return addTicket(userId, timestamp, iValid, iPaid);
    }


    public Automata addAutomata(KassenautomatContext kassenautomatContext) throws DbException {

        writeDatabase.beginTransaction();

        try {
            long moneybankId = addMoneybank(DefaultValuesHandler.getDefaultAutomataMoney(kassenautomatContext));

            long automataId = writeDatabase.insert(TableAutomata.DB_TABLE_NAME,
                    null,
                    Automata.getContentValues(moneybankId, DefaultValuesHandler.DEFAULT_PARK_COINS));

            if(automataId == -1) {
                throw new DbException("Could not insert into table automata with moneybankId " + moneybankId + ". Roll back changes in moneybank table.");
            }
        } catch (DbException e) {
            writeDatabase.endTransaction();
            throw e;
        } finally {
            writeDatabase.setTransactionSuccessful();
        }

        writeDatabase.endTransaction();
        return getAutomata();
    }


    //UPDATE-METHODS

    /**
     * Updates the customer with the id in the object to the values in the object.
     * @param user The customer to update and the values to which to update.
     * @return Whether the update failed or not.
     * @throws DbException If the database is reachable.
     */
    public User updateUser(User user) throws DbException{

        writeDatabase.beginTransaction();

        try {
//            Cursor c = writeDatabase.query(TableUser.DB_TABLE_NAME,
//                    new String[]{TableUser.DB_COLUMN_MONEYBANK_ID},
//                    null,
//                    null, null, null, null);
//
//            if (!c.moveToFirst()) {
//                throw new DbException("Couldn´t read moneybankId for customer. SQL: tables => " + TableUser.DB_TABLE_NAME + " columns => " + TableUser.DB_COLUMN_MONEYBANK_ID + " where => " + TableUser.DB_COLUMN_ID + " = " + user.getId());
//            }

//            long moneybankId = c.getLong(0);


//            c.close();

            long moneybankId = user.getMoneybankId();

            int affectedRows = writeDatabase.update(TableMoneybank.DB_TABLE_NAME,
                    Money.getContentValues(user.getMoney()),
                    TableMoneybank.DB_COLUMN_ID + " = " + moneybankId,
                    null);

            if(affectedRows > 1) {
                throw new DbException("More rows affected for primary key " + moneybankId + " in table " + TableMoneybank.DB_TABLE_NAME + ". Something is now terribly wrong...");
            } else if (affectedRows <= 0) {
                throw new DbException("No rows were affected for primary key " + moneybankId + " in table " + TableMoneybank.DB_TABLE_NAME + ". Something is now terribly wrong...");
            }

            affectedRows = writeDatabase.update(TableUser.DB_TABLE_NAME,
                    user.getContentValues(),
                    TableUser.DB_COLUMN_ID + " = " + user.getId(),
                    null);

            if(affectedRows > 1) {
                throw new DbException("More rows affected for primary key " + user.getId() + " in table " + TableUser.DB_TABLE_NAME + ". Something is now terribly wrong...");
            } else if (affectedRows <= 0) {
                throw new DbException("No rows were affected for primary key " + user.getId() + " in table " + TableUser.DB_TABLE_NAME + ". Something is now terribly wrong...");
            }


        } catch (DbException e) {
            writeDatabase.endTransaction();
            throw e;
        } finally {
            writeDatabase.setTransactionSuccessful();
        }

        writeDatabase.endTransaction();
        return getUser(user.getId());
    }


    /**
     * Updates the ticket with the id in the object to the values in the object.
     * @param ticket The customer to update and the values to which to update.
     * @return Whether the update failed or not.
     * @throws DbException If the database is reachable.
     */
    public Ticket updateTicket(Ticket ticket) throws DbException{
        writeDatabase.beginTransaction();


        int ret = writeDatabase.update(TableTicket.DB_TABLE_NAME,
                ticket.getContentValues(),
                TableTicket.DB_COLUMN_ID + " = " + ticket.getId(),
                null);

        if(ret == 1) {
            writeDatabase.setTransactionSuccessful();
        } else {
            writeDatabase.endTransaction();
            throw new DbException("Could not update ticket with id " + ticket.getId());
        }
        writeDatabase.endTransaction();

        return getTicket(ticket.getId());
    }


    public Automata updateAutomata(Automata automata) throws DbException {
        writeDatabase.beginTransaction();

        int ret = writeDatabase.update(TableMoneybank.DB_TABLE_NAME,
                Money.getContentValues(automata.getMoney()),
                TableMoneybank.DB_COLUMN_ID + " = " + automata.getMoneybankId(),
                null);

        if(ret > 1) {
            writeDatabase.endTransaction();
            throw new DbException("More then one row was affected while updating row with id " + automata.getMoneybankId() + ". Changes are rolled back.");
        } else if (ret <= 0) {
            writeDatabase.endTransaction();
            throw new DbException("Less then one row was affected while updating row with id " + automata.getMoneybankId() + ". Changes are rolled back.");
        }

        writeDatabase.setTransactionSuccessful();
        writeDatabase.endTransaction();

        return automata;
    }



    //DELETE FUNCTIONS
    public boolean deleteTicket(long id) throws DbException {
        int ret = writeDatabase.delete(TableTicket.DB_TABLE_NAME,
                TableTicket.DB_COLUMN_ID + " = " + id, null);
        if(ret == 0) {
            return false;
        } else if(ret == 1) {
            return true;
        }
        if(ret > 1) {
            throw new DbException("More then one was affected while deleting a ticket with id " + id + ". " + ret + " rows were affected.");
        } else {
            throw new DbException("Less then 0 rows were affected while deleting a ticket. Most likely a failure in the database. Rows affected: " + ret);
        }
    }

    public User resetUser(User user) throws DbException {
        List<Ticket> ticketList = getTicketsFromUser(user.getId());

        for(Ticket ticket : ticketList) {
            deleteTicket(ticket.getId());
        }


        Money money = DefaultValuesHandler.getDefaultUserMoney();

        user.setMoney(money);
        user.setParkCoins(0);

        updateUser(user);

        return user;
    }


    /**
     * Helper functions for internal use.
     * Dont use these on the outside. It wont break anything, but it is not intended and save.
     */
    //HELPER FUNCTIONS
    private long getCustomerId() throws DbException{
        Cursor c = writeDatabase.query(TableUser.DB_TABLE_NAME,
                new String[]{TableUser.DB_COLUMN_ID},
                null, null, null, null, null);

        if(c.getCount() <= 0) {
            throw new DbException("Could not retrieve CustomerId");
        }

        c.moveToFirst();
        long customerId = c.getLong(0);
        c.close();

        return customerId;
    }

    private long getMoneybankIdFromUser(long userId) throws DbException {
        Cursor c = writeDatabase.query(TableUser.DB_TABLE_NAME,
                new String[] { TableUser.DB_COLUMN_MONEYBANK_ID, TableUser.DB_COLUMN_ID},
                       TableUser.DB_COLUMN_ID + " = " + userId,
                null, null, null, null);

        if(c.getCount() <= 0) {
            throw new DbException("Could not retrieve MoneybankId from User " + userId + ".");
        }

        c.moveToFirst();
        long moneybankId = c.getLong(0);
        c.close();

        return moneybankId;
    }

    private long getUserIdFromTicket(long ticketId) throws DbException {
        Cursor c = writeDatabase.query(TableTicket.DB_TABLE_NAME,
                new String[] { TableTicket.DB_COLUMN_USER_ID },
                TableTicket.DB_TABLE_NAME + "." + TableTicket.DB_COLUMN_ID + " = " + ticketId,
                null, null, null, null);

        if(c.getCount() <= 0) {
            throw new DbException("Could not retrieve UserId from Ticket " + ticketId + ".");
        }

        c.moveToFirst();
        long userId = c.getLong(0);
        c.close();

        return userId;
    }

    private long getParkCoinsFromUser(long userId) throws DbException {
        Cursor c = writeDatabase.query(TableUser.DB_TABLE_NAME,
                new String[]{TableUser.DB_TABLE_NAME + "." + TableUser.DB_COLUMN_PARK_COINS},
                TableUser.DB_TABLE_NAME + "." + TableUser.DB_COLUMN_ID + " = " + userId,
                null, null, null, null);

        if (c.getCount() <= 0) {
            throw new DbException("Could not retrieve ParkCoins from User " + userId + ".");
        }

        c.moveToFirst();
        long parkCoins = c.getLong(0);
        c.close();

        return parkCoins;
    }


    //DEBUG METHODS
    /**
     * Prints all table names that are currently in the database.
     * Only for debugging or likes of it.
     */
    public void printTableNames() {
        Cursor c = writeDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        List<String> tables = new ArrayList();
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                tables.add(c.getString(0));
                c.moveToNext();
            }
        }
        c.close();

        for(String table : tables) {
            c = writeDatabase.rawQuery("SELECT sql FROM sqlite_master WHERE tbl_name = '" + table + "' AND type = 'table'", null);
            if (c.moveToFirst()) {
                System.out.println(table);
                while ( !c.isAfterLast() ) {
                    System.out.println(c.getString(0));
                    c.moveToNext();
                }
            }
            c.close();
        }
    }

    /**
     * Only for debugging or to delete test databases.
     * @param name Name of the database to be deleted.
     */
    public void deleteDatabase(String name) {
        database.deleteDatabase(name);
    }

    public void freeDatabase() {
        database.close();
        writeDatabase.close();
    }

    public MySQLiteOpenHelper getDatabase() {
        return database;
    }

    public SQLiteDatabase getWriteDatabase() {
        return writeDatabase;
    }

    public SQLiteDatabase getReadDatabase() {
        return writeDatabase;
    }
}

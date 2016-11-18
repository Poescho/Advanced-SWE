package com.example.michael.kassenautomat_dhbw;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.michael.kassenautomat_dhbw.database.DatabaseConnection;
import com.example.michael.kassenautomat_dhbw.datatypes.Automata;
import com.example.michael.kassenautomat_dhbw.datatypes.Money;
import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.datatypes.User;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Michael on 26.04.2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class DatabaseTestJUnit4 {

    private DatabaseConnection connection;
    private static final String DATABASE_NAME = "Database_Test";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setUp() throws Exception {
        System.out.println("SET UP");
        Context context = mActivityRule.getActivity().getApplication().getApplicationContext();
        assertNotNull(context);
        connection = new DatabaseConnection(context, DATABASE_NAME, true);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("TEAR DOWN");
        connection.deleteDatabase(DATABASE_NAME);
    }


    @Test
    public void testAddTicket() throws Exception {
        User user = connection.addUser(new Money(0,0,0,0,0,0));
        Ticket ticket = connection.addTicket(user.getId(), 0, false, false);
        assertNotNull(ticket);
    }

    @Test
    public void testDeleteTicket() throws Exception {
        User user = connection.addUser(new Money(0,0,0,0,0,0));
        Ticket ticket = connection.addTicket(user.getId(), 0, false, false);
        assertNotNull(ticket);
        boolean deleted = connection.deleteTicket(ticket.getId());
        assertTrue(deleted);
    }

    @Test
    public void testUpdateTicket() throws Exception {
        User user = connection.addUser(new Money(0,0,0,0,0,0));
        Ticket ticket = connection.addTicket(user.getId(), 0, false, false);
        assertNotNull(ticket);
        Ticket ticket1 = connection.updateTicket(ticket);
        assertNotNull(ticket);
    }


    @Test
    public void testGetTicket() throws Exception {
        User user = connection.addUser(new Money(0,0,0,0,0,0));
        Ticket ticket = connection.addTicket(user.getId(), 0, false, false);
        Ticket ticketGet = connection.getTicket(ticket.getId());
        assertEquals(ticket.getId(), ticketGet.getId());
        assertEquals(ticket.getTimestamp(), ticketGet.getTimestamp());
        assertEquals(ticket.getUserId(), ticketGet.getUserId());
        assertEquals(ticket.isPaid(), ticketGet.isPaid());
        assertEquals(ticket.isValid(), ticketGet.isValid());
    }


    @Test
    public void testGetTicketsFromUser() throws Exception {
        User user = connection.addUser(new Money(0,0,0,0,0,0));
        assertNotNull(user);
        List<Ticket> setTickets = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            Ticket ticket = connection.addTicket(user.getId(), System.currentTimeMillis(), false, false);
            assertNotNull(ticket);
            setTickets.add(ticket);
            Thread.sleep(50);
        }

        boolean idFitted = false;
        List<Ticket> tickets = connection.getTicketsFromUser(user.getId());
        for(Ticket ticket : tickets) {
            for(Ticket setTicket : setTickets) {
                if(ticket.getId() == setTicket.getId()) {
                    idFitted = true;
                    assertEquals(ticket.getUserId(),    setTicket.getUserId());
                    assertEquals(ticket.getTimestamp(), setTicket.getTimestamp());
                    assertEquals(ticket.isValid(),      setTicket.isValid());
                    assertEquals(ticket.isPaid(),       setTicket.isValid());
                }
            }
            assertTrue(idFitted);
            idFitted = false;
        }
    }



    @Test
    public void testUpdateUser() throws Exception {
        User user = connection.addUser(new Money(0,0,0,0,0,0));
        User user1 = connection.updateUser(user);
        assertNotNull(user1);
    }

//    @Test
//    public void testGetUser() throws Exception {
//        User user = connection.addUser(new Money(0,0,0,0,0,0));
//        User userGot = connection.getUser(user.getId());
//        assertNotNull(userGot);
//        assertTrue(user.equals(userGot));
//    }


    @Test
    public void testDeleteUser() throws Exception {
        User user = connection.addUser(new Money(0,0,0,0,0,0));
        assertNotNull(user);

//        assertTrue(connection.deleteUser(user.getId()));
    }




//    @Test
//    public void testAddAutomata() throws Exception {
//        KassenautomatContext context = new KassenautomatContext(null, null);
//        Automata automata = connection.addAutomata(context, new Money(0,0,0,0,0,0), 100);
//
//        Automata getAutomata = connection.getAutomata(context);
//
//    }

    @Test
    public void testLikeFuckingEverything() throws Exception {

        User user = connection.addUser(new Money(0,0,0,0,0,0));
        User user2 = connection.addUser(new Money(0,0,0,0,0,0));
        assertTrue(user.equals(connection.getUser()));
        User user1 = connection.getUser();
        assertTrue(user.equals(user1));

        user.setMoney(new Money(1,1,1,1,1,1));
        assertNotNull(connection.updateUser(user));

        user.setMoney(new Money(1,1,1,1,1,1));
        assertNotNull(connection.updateUser(user));


        Ticket ticket = connection.addTicket(user.getId(), System.currentTimeMillis(), false, false);
        assertNotNull(ticket);

        ticket.setPaid(1);
        assertNotNull(connection.updateTicket(ticket));

        Ticket ticket1 = connection.addTicket(user.getId(), System.currentTimeMillis(), false, false);
        assertNotNull(ticket1);

        //assertTrue(connection.deleteUser(user.getId()));
        assertTrue(connection.deleteTicket(ticket.getId()));
        assertTrue(connection.deleteTicket(ticket1.getId()));
    }
}

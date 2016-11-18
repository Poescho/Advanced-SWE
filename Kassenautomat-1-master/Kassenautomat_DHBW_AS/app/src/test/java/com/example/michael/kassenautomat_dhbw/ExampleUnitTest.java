package com.example.michael.kassenautomat_dhbw;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.example.michael.kassenautomat_dhbw.database.DatabaseConnection;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends ApplicationTestCase{


    public ExampleUnitTest(Class applicationClass) {
        super(applicationClass);
    }

    @SmallTest
    public void testAddition() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @SmallTest
    public void testAddCustomer() throws Exception {
        DatabaseConnection connection = new DatabaseConnection(getContext());
        if(connection.getDatabase() == null) {
            throw new Exception();
        }
    }
}
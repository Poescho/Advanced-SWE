package com.example.michael.kassenautomat_dhbw;

        import android.test.mock.MockContext;
        import android.test.InstrumentationTestCase;

        import com.example.michael.kassenautomat_dhbw.database.DatabaseConnection;


        import org.junit.After;
        import org.junit.Before;


/**
 * Created by Michael on 26.04.2016.
 */

public class DatabaseTest extends InstrumentationTestCase {

    private DatabaseConnection connection;
    private static final String DATABASE_NAME = "Database_Test";

    @Before
    public void setUp() throws Exception {
        super.setUp();

        MockContext context = new MockContext();
        assertNotNull(context);
        connection = new DatabaseConnection(context, DATABASE_NAME, true);
    }


}
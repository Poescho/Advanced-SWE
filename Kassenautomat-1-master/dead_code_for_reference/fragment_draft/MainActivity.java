package dead_code_for_reference.fragment_draft;

import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    /*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.toolbar_content, new FragmentToolbarCustomer());
        ft.replace(R.id.content_main, new FragmentContentMainCustomer());
        ft.commit();



        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

/*
        final FragmentChanger fragmentChanger = new FragmentChanger(getFragmentManager());


        Button btn = (Button)findViewById(R.id.btnChange);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentChanger.changeFragment();
            }
        });
*/

        /*


        DatabaseConnection connection = new DatabaseConnection(getApplicationContext());
        TextView text = (TextView)findViewById(R.id.text);

        //connection.printTableNames();

        try {
            long id = connection.addWorker("TestUser1234");
            text.setText(connection.getWorkerName(id));
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "My Text", Toast.LENGTH_LONG).show();
            Intent settingsActivity = new Intent(getApplicationContext(), com.example.michael.kassenautomat_dhbw.SettingsActivity.class);
            startActivity(settingsActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}

package pl.dawidfiruzek.snookerassistant;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StartActivity extends ActionBarActivity {


    private ListView drawerList;
    private ArrayAdapter<String> adapter;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new StartFragment())
                    .commit();
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.start_activity_drawer_layout);

        // setting navigation drawer items
        drawerList = (ListView) findViewById(R.id.start_activity_left_drawer_list);
        String[] drawerArray = getResources().getStringArray(R.array.start_activity_navigation_drawer);
        List<String>  drawerArrayList = new ArrayList<String>(Arrays.asList(drawerArray));

        adapter = new ArrayAdapter<String>(
                this,
                // TODO  change item in listView (icon + text)
                android.R.layout.simple_list_item_1,
                drawerArrayList);
        drawerList.setAdapter(adapter);

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.start_activity_drawer_open,
                R.string.start_activity_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(getSupportActionBar().getTitle().toString());
                supportInvalidateOptionsMenu();
            }

            @Override
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(getSupportActionBar().getTitle().toString());
                supportInvalidateOptionsMenu();
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO set onclick listeners for items
                Toast.makeText(getApplicationContext(), "This is DUPA!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if(! drawerLayout.isDrawerOpen(drawerList)) {
            drawerLayout.openDrawer(drawerList);
        }
        else    drawerLayout.closeDrawers();
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(drawerList)){
            drawerLayout.closeDrawers();
        }
        else super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate navigation drawer toggle
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

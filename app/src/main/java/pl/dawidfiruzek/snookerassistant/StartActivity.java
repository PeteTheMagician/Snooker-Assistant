package pl.dawidfiruzek.snookerassistant;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class StartActivity extends ActionBarActivity {

    public static final String TAG = "Snooker Assistant";

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<NavigationDrawerItem> mNavItems = new ArrayList<NavigationDrawerItem>();

    private String[] mNavDrawerTitles;
    private String[] mNavDrawerSubtitles;
    private TypedArray mNavDrawerIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new StartFragment())
                    .commit();
        }

        mNavDrawerTitles = getResources().getStringArray(R.array.start_activity_navDrawer_titles);
        mNavDrawerSubtitles = getResources().getStringArray(R.array.start_activity_navDrawer_subtitles);
        mNavDrawerIcons = getResources().obtainTypedArray(R.array.start_activity_navDrawer_icons);

        for(int i =0; i < mNavDrawerTitles.length; ++i){
            mNavItems.add(new NavigationDrawerItem(mNavDrawerTitles[i], mNavDrawerSubtitles[i], mNavDrawerIcons.getResourceId(i,-1)));
        }

        mNavDrawerIcons.recycle();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.start_activity_drawer_layout);

        mDrawerList = (ListView) findViewById (R.id.start_activity_left_drawer_list);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.start_activity_drawer_open,
                R.string.start_activity_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
            }

            @Override
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                supportInvalidateOptionsMenu();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onClickNavDrawerItem(i);
            }
        });
    }

    private void onClickNavDrawerItem(int position){
//        android.support.v4.app.Fragment fragment = null;

        switch (position){
            // Home
            case 0:
//                fragment = new StartFragment();
                getSupportFragmentManager().popBackStack(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                        .replace(R.id.container, new StartFragment())
                        .commit();
                break;
            // Settings
            case 1:
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, new SettingsFragment())
//                        .addToBackStack(null)
//                        .commit();
                break;
            // About
            case 2:
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                        .replace(R.id.container, new AboutFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            default:
                Log.e(TAG, "Unexpected Navigation Drawer item ID");
                break;
        }

//        if (fragment != null){
//            Toast.makeText(getApplicationContext(), "This is DUPA!", Toast.LENGTH_SHORT).show();
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, fragment).commit();
//        }
//        else Log.e(TAG, "Error in creating fragment");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if(! mDrawerLayout.isDrawerOpen(mDrawerList)) {
            mDrawerLayout.openDrawer(mDrawerList);
        }
        else    mDrawerLayout.closeDrawers();
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(mDrawerList)){
            mDrawerLayout.closeDrawers();
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
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

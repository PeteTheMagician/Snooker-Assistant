package pl.dawidfiruzek.snookerassistant;


import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    private ListView drawerList;
    private ArrayAdapter<String> adapter;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private String activityTitle;

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_start, container, false);

        ((ActionBarActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((ActionBarActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

        drawerLayout = (DrawerLayout) rootView.findViewById(R.id.start_activity_drawer_layout);
        activityTitle = getActivity().getTitle().toString();

//        setting navigation drawer items
        drawerList = (ListView) rootView.findViewById(R.id.start_activity_left_drawer_list);
        String[] drawerArray = rootView.getResources().getStringArray(R.array.start_activity_navigation_drawer);
        List<String> drawerArrayList = new ArrayList<String>(Arrays.asList(drawerArray));
        adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                drawerArrayList);
        drawerList.setAdapter(adapter);

        drawerToggle = new ActionBarDrawerToggle (getActivity(),drawerLayout,android.R.drawable.button_onoff_indicator_on, R.string.start_activity_drawer_open, R.string.start_activity_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("Dupa Opened");
                getActivity().supportInvalidateOptionsMenu();

            }

            @Override
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("Dupa closed");
                getActivity().supportInvalidateOptionsMenu();
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

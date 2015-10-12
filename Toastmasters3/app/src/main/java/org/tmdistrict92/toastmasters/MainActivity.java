package org.tmdistrict92.toastmasters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {


    public final static String urlForNotification = "com.district92.toastmasters.urlnotification";
    public final static String titleForNotification = "com.district92.toastmasters.titleForNotification";
    static MainActivity mainActivity;
    int fragmentPosition;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun) {
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();
            Intent intentToSelectDistrict = new Intent(this, DistrictSelectionActivity.class);
            startActivity(intentToSelectDistrict);
        }
        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        mViewPager.setCurrentItem(1);
        mViewPager.setOffscreenPageLimit(3);
    }

    public static MainActivity getInstance () {
        return mainActivity;
    }

    @Override
    public void onBackPressed () {
        finish();
        System.exit(0);
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
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (item.getItemId()) {
            case R.id.admin:
                Intent adminLoginIntent = new Intent(this, LoginActivity.class);
                startActivity(adminLoginIntent);
                return true;
            case R.id.settings:
                Intent settingsIntent = new Intent(this, DistrictSelectionActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.deleteIcon:
                if (fragmentPosition == 0) {
                    SharedPreferences counter = getSharedPreferences("counter", Context.MODE_PRIVATE);
                    Set<String> counterSet = counter.getStringSet("counterSet", new HashSet<String>());
                    SharedPreferences.Editor editor = counter.edit();
                    counterSet.clear();
                    editor.putStringSet("counterSet", counterSet);
                    editor.commit();
                    finish();
                    startActivity(getIntent());
                    return true;
                }
                if (fragmentPosition == 1) {
                    SharedPreferences message = getSharedPreferences("message", Context.MODE_PRIVATE);
                    Set<String> messageSet = message.getStringSet("messageSet", new HashSet<String>());
                    SharedPreferences.Editor editor = message.edit();
                    messageSet.clear();
                    editor.putStringSet("messageSet", messageSet);
                    editor.commit();
                    finish();
                    startActivity(getIntent());
                    return true;
                }
                if (fragmentPosition == 2) {
                    SharedPreferences timer = getSharedPreferences("timer", Context.MODE_PRIVATE);
                    Set<String> timerSet = timer.getStringSet("timerSet", new HashSet<String>());
                    SharedPreferences.Editor editor = timer.edit();
                    timerSet.clear();
                    editor.putStringSet("timerSet", timerSet);
                    editor.commit();
                    finish();
                    startActivity(getIntent());
                    return true;
                }
            default: return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
//        Snackbar snackBar = Snackbar.make(mViewPager, R.string.snack_bar, Snackbar.LENGTH_SHORT);
//        if (tab.getPosition() == 0) {
//            snackBar.show();
//            mViewPager.setCurrentItem(tab.getPosition());
//        }
//        else
//        {
        int currentTab = tab.getPosition();
            if (currentTab == 0) {
                fragmentPosition = 0;
            }
        if (currentTab == 1) {
            fragmentPosition = 1;
        }
        if (currentTab == 2 ) {
            fragmentPosition = 2;
        }
            mViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0) {
                return new ahCounterFragment();
            }
            if (position == 1) {
                return new notificationFragment();
            }
            if (position == 2) {
                return new timerFragment();
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public static class timerFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        public timerFragment() {
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_timer_report, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.timerReportListView);
            SharedPreferences timer = this.getActivity().getSharedPreferences("timer", Context.MODE_PRIVATE);
            Set<String> timerSet = timer.getStringSet("timerSet", new HashSet<String>());
            ArrayList<String> listOfData = new ArrayList<String>(timerSet);
            if (listOfData.isEmpty()) {
                TextView textView = new TextView(getActivity());
                textView.setTextSize(25);
                textView.setText("No Timer Reports");
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
            else {
                ArrayAdapter<String> dataToBeDisplayed = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOfData);
                listView.setAdapter(dataToBeDisplayed);
                return rootView;
            }

        }
    }

    public void floatingActionButtonforTimer (View view) {
        Intent intentFromTimer = new Intent(this, TimerSelection.class);
        startActivity(intentFromTimer);
    }

    public static class ahCounterFragment extends Fragment {



        private static final String ARG_SECTION_NUMBER = "section_number";

        public ahCounterFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_data_save, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.dataSaveListView);
            SharedPreferences counter = this.getActivity().getSharedPreferences("counter", Context.MODE_PRIVATE);
            Set<String> counterSet = counter.getStringSet("counterSet", new HashSet<String>());
            ArrayList<String> listOfData = new ArrayList<String>(counterSet);
            if (listOfData.isEmpty()) {
                TextView textView = new TextView(getActivity());
                textView.setTextSize(25);
                textView.setText("No Ah Counter Reports");
                textView.setGravity(Gravity.CENTER);
                return textView;
            } else {
                ArrayAdapter<String> dataToBeDisplayed = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOfData);
                listView.setAdapter(dataToBeDisplayed);
                return rootView;
            }
        }
    }

    public void floatingActionButton (View view) {
        Intent intentFromAhReport = new Intent(this, AhCounter.class);
        startActivity(intentFromAhReport);
    }


    public static class notificationFragment extends Fragment {

        Intent intentForWebViewFromNotifications;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public notificationFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_notification_list, container, false);
            intentForWebViewFromNotifications = new Intent(getActivity(), WebViewForNotification.class);
            ListView listView = (ListView) rootView.findViewById(R.id.listViewOfNotifications);
            SharedPreferences message = this.getActivity().getSharedPreferences("message", Context.MODE_PRIVATE);
            Set<String> messageSet = message.getStringSet("messageSet", new HashSet<String>());
            List<String> messageArray = new ArrayList<String>(messageSet);
            List<String> messageToDisplay = new ArrayList<String>();
            final List<String> titleToDisplay = new ArrayList<String>();
            final List<String> urlToConnect = new ArrayList<String>();
            for (int i = 0; i < messageArray.size(); i++) {
                String jsonData = messageArray.get(i);
                JSONObject json;
                try {
                    json = new JSONObject(jsonData);
                    titleToDisplay.add(json.getString("title"));
                    messageToDisplay.add(json.getString("title") + "\n Message: \n" + json.getString("alert"));
                    urlToConnect.add(json.getString("url"));
                } catch (JSONException e) {

                }
            }
            ArrayAdapter<String> adaptorForNotifications = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, messageToDisplay);
            listView.setAdapter(adaptorForNotifications);
            if (messageToDisplay.isEmpty()) {
                TextView textView = new TextView(getActivity());
                textView.setTextSize(25);
                textView.setText("No Notifications");
                textView.setGravity(Gravity.CENTER);
                return textView;
            } else {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        intentForWebViewFromNotifications.putExtra(urlForNotification, urlToConnect.get(position));
                        intentForWebViewFromNotifications.putExtra(titleForNotification, titleToDisplay.get(position));
                        startActivity(intentForWebViewFromNotifications);
                    }
                });
                return rootView;

            }
        }
    }
}


package org.tmdistrict92.toastmasters;

import java.util.Locale;

import android.content.Intent;
import android.support.design.widget.Snackbar;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import models.AllNotifications;
import models.ahCounterReport;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    public final static String greenTimer = "com.district92.toastmasters.greenTime";
    public final static String TimerTitle = "com.district92.toastmasters.timerTitle";
    public final static String urlForNotification = "com.district92.toastmasters.urlnotification";
    public final static String titleForNotification = "com.district92.toastmasters.titleForNotification";
    Button crutchButton;
    Button fillerButton;
    Button pauseButton;
    int crutch = 0, filler = 0, pause = 0;

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
                return new timerFragment();
            }
            if (position == 2) {
                return new notificationFragment();
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

        Intent intentFromSelectProjectActivity;
        Intent intentForAdvancedProjects;
        Intent intentForReport;

        private static final String ARG_SECTION_NUMBER = "section_number";
        public timerFragment() {
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_timer_selection, container, false);
            super.onCreate(savedInstanceState);
            intentFromSelectProjectActivity = new Intent(getActivity(), speechTimer.class);
            intentForAdvancedProjects = new Intent(getActivity(), AdvancedSpeechesTimerSetting.class);
            intentForReport = new Intent(getActivity(), TimerReport.class);
            final String[] listOfProjects = {"Project 1", "Project 2 - 9", "Project 10", "Table Topics", "Evaluation", "Advanced Speeches", "View Report"};
            ListView projectSelectListView = (ListView) rootView.findViewById(R.id.timerSelectionListView);
            ArrayAdapter<String> clubStringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOfProjects);
            projectSelectListView.setAdapter(clubStringArrayAdapter);
            projectSelectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int positionOfProject, long id) {
                    int greenTime = 4;
                    switch (positionOfProject) {
                        case 0:
                            greenTime = 4;
                            callTheTimer(greenTime, listOfProjects[0]);
                            break;
                        case 1:
                            greenTime = 5;
                            callTheTimer(greenTime, listOfProjects[1]);
                            break;
                        case 2:
                            greenTime = 8;
                            callTheTimer(greenTime, listOfProjects[2]);
                            break;
                        case 3:
                            greenTime = 1;
                            callTheTimer(greenTime, listOfProjects[3]);
                            break;
                        case 4:
                            greenTime = 2;
                            callTheTimer(greenTime, listOfProjects[4]);
                            break;
                        case 5:
                            startActivity(intentForAdvancedProjects);
                            break;
                        case 6:
                            startActivity(intentForReport);
                            break;
                    }

                }


            });
            return rootView;
        }
        public void callTheTimer (int green, String title) {
            intentFromSelectProjectActivity.putExtra(greenTimer, green);
            intentFromSelectProjectActivity.putExtra(TimerTitle, title);
            startActivity(intentFromSelectProjectActivity);
        }
    }

    public static class ahCounterFragment extends Fragment {



        private static final String ARG_SECTION_NUMBER = "section_number";

        public ahCounterFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_ah_counter, container, false);
            // ArrayList<String> dataForDisplay = new ArrayList<String>();
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
           // RelativeLayout parent = (RelativeLayout) rootView.findViewById(R.id.parentRelativeLayoutofAhCounter);
            //Snackbar.make(rootView, "Tap on the numbers to count", Snackbar.LENGTH_LONG).show();
            return rootView;
        }


    }

    public void crutchIncrement(View view) {
        ++crutch;
        crutchButton = (Button) findViewById(R.id.crutchWordId);
        crutchButton.setText(" " + crutch);
    }

    public void fillerIncrement(View view) {
        ++filler;
        fillerButton = (Button) findViewById(R.id.fillerIncrementId);
        fillerButton.setText(" " + filler);
    }

    public void pauseIncrement(View view) {
        ++pause;
        pauseButton = (Button) findViewById(R.id.pauseIncrementId);
        pauseButton.setText(" " + pause);
    }

    public void ahCounterResetButton(View view) {
        crutch = filler = pause = 0;
        ((EditText) findViewById(R.id.enterName)).setText("");
        crutchButton = (Button) findViewById(R.id.crutchWordId);
        fillerButton = (Button) findViewById(R.id.fillerIncrementId);
        pauseButton = (Button) findViewById(R.id.pauseIncrementId);
        crutchButton.setText("0");
        fillerButton.setText("0");
        pauseButton.setText("0");
    }

    public void ahCounterSaveButton(View view) {
        EditText nameFromActivity = (EditText) findViewById(R.id.enterName);
        String name = nameFromActivity.getText().toString();
        ahCounterReport.setDataToArray(name + " \n Crutch Words:" + crutch + " \n Filler Words:" + filler + "\n Unwanted Pauses:" + pause);
        // dataForDisplay.add(name);
        Intent forDataSaveActivity = new Intent(this, DataSaveActivity.class);
        // forDataSaveActivity.putStringArrayListExtra(dataToDisplay, dataForDisplay);
        startActivity(forDataSaveActivity);
    }

    public void goToReport (View view) {
        Intent goToReport = new Intent(this, DataSaveActivity.class);
        startActivity(goToReport);
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
            ArrayAdapter<String> adaptorForNotifications = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, AllNotifications.getDataFromNotificationMessage());
            listView.setAdapter(adaptorForNotifications);
            if (AllNotifications.getDataFromNotificationMessage().isEmpty()) {
                TextView textView = new TextView(getActivity());
                textView.setTextSize(25);
                textView.setText("No Notifications");
                textView.setGravity(Gravity.CENTER);
                return textView;
            } else {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        intentForWebViewFromNotifications.putExtra(urlForNotification, AllNotifications.getUrlsForNotifications(position));
                      //  intentForWebViewFromNotifications.putExtra(titleForNotification, AllNotifications.getDataFromNotificaitons(position));
                        startActivity(intentForWebViewFromNotifications);
                    }
                });
                return rootView;
            }
        }
        }
}


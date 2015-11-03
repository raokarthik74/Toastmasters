package com.karthikravindrarao.ToastDirector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.parse.ParseInstallation;
import com.parse.ParsePush;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {


    public final static String urlForNotification = "com.district92.toastmasters.urlnotification";
    public final static String titleForNotification = "com.district92.toastmasters.titleForNotification";
    public final static String urlForWebView = "com.district92.toastmasters.urlForWebView";
    static MainActivity mainActivity;
    static Context mainActivityContext;
    int fragmentPosition;
    boolean onStopped = false;
    static boolean onResumed;

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
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mainActivityContext = getApplicationContext();
//        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
//        if (isFirstRun) {
//            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();
//            Intent intentToSelectDistrict = new Intent(this, DistrictSelectionActivity.class);
//            startActivity(intentToSelectDistrict);
//        }
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

    public static Context getMainActivityContext () {
        return mainActivityContext;
    }

    @Override
    public void onBackPressed () {
        finish();
        System.exit(0);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (onStopped){
            onStopped = false;
            getInstance().recreate();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        onStopped = true;
    }

    @Override
    public void onResume (){
        super.onResume();
        onResumed = true;
    }
    @Override
    public void onPause (){
        super.onPause();
        onResumed = false;
    }

    public static boolean ifOnResumed () {
        return onResumed;
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
                return new conferenceFragment();
            }
            if (position == 1) {
                return new districtFragment();
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

    public static class districtFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        public districtFragment() {
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_district, container, false);
                return rootView;
        }
    }

    public void openAwardsPage (View view) {
        Intent intentToOpenAwards = new Intent(this,AwardsListView.class);
        startActivity(intentToOpenAwards);
    }

    public void openNewsLetterPage (View view) {
        Intent intentToNewsLetter = new Intent(this,webView.class);
        intentToNewsLetter.putExtra(urlForWebView, "http://www.tmdistrict92.org/tmdistrict92/index.php/toastmasters-media/newsletters/district-newsletters");
        startActivity(intentToNewsLetter);
    }

    public void openAhCounter (View view) {
        Intent intentToAhCounter = new Intent(this,ahCounterReportActivity.class);
        startActivity(intentToAhCounter);
    }

    public void openTimer (View view) {
        Intent intentToTimer = new Intent(this,timerReportActivity.class);
        startActivity(intentToTimer );
    }

    public void openEvents (View view) {
        Intent intentToEvents = new Intent(this,EventsListView.class);
        startActivity(intentToEvents);
    }

    public void openBlog (View view) {
        Intent intentToBlog = new Intent(this,webView.class);
        intentToBlog.putExtra(urlForWebView, "http://www.tmdistrict92.org/tmdistrict92/index.php/toastmasters-media/newsletters/district-newsletters");
        startActivity(intentToBlog);
    }

    public void openAboutDistrict (View view) {
        Intent intentToAbout = new Intent(this,webView.class);
        intentToAbout.putExtra(urlForWebView, "http://www.tmdistrict92.org/");
        startActivity(intentToAbout);
    }

    public static class conferenceFragment extends Fragment {


        private static final String ARG_SECTION_NUMBER = "section_number";

        public conferenceFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_conference, container, false);
            SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = null;
            try {
                date = dformat.parse("28-06-2016");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            CountDownTimer cdt = new CountDownTimer(date.getTime(), 1000) {

                public void onTick(long millisUntilFinished) {
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(millisUntilFinished);
                    ((TextView) rootView.findViewById(R.id.countDown)).setText("COUNTDOWN\n Month : Day : Hour : Minute : Second\n" + getDate(millisUntilFinished, "MM : FF : hh : mm : ss"));
//                    ((TextView) rootView.findViewById(R.id.conDays)).setText(c.get(Calendar.DAY_OF_MONTH));
//                    ((TextView) rootView.findViewById(R.id.conHours)).setText(c.get(Calendar.HOUR));
//                    ((TextView) rootView.findViewById(R.id.conMinutes)).setText(c.get(Calendar.MINUTE));
//                    ((TextView) rootView.findViewById(R.id.conSeconds)).setText(c.get(Calendar.SECOND));
                }

                public void onFinish() {
                    ((TextView) rootView.findViewById(R.id.countDown)).setText("WELCOME \n TO CORONATION");

                }
            }.start();
            return rootView;
        }

        public static String getDate(long milliSeconds, String dateFormat)
        {
            // Create a DateFormatter object for displaying date in specified format.
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

            // Create a calendar object that will convert the date and time value in milliseconds to date.
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            return formatter.format(calendar.getTime());
        }
    }

    public void openMapVenue (View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.co.in/maps/place/Bengaluru+Marriott+Hotel+Whitefield/@12.979522,77.7259913,17z/data=!3m1!4b1!4m2!3m1!1s0x3bae11f15591198b:0x6f7d6b166b01e4e3"));
        startActivity(intent);
    }

    public void chooseConferenceNotifications (View view) {
        Intent intentToNotifications = new Intent(this, ChooseNotifications.class);
        startActivity(intentToNotifications);
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
            Map<String, Integer> indexOfArrayList = new HashMap<String, Integer>();;
            final List<String> titleToDisplay = new ArrayList<String>();
            final List<String> urlToConnect = new ArrayList<String>();
            for (int i = 0; i < messageArray.size(); i++) {
                String jsonData = messageArray.get(i);
                JSONObject json;
                try {
                    json = new JSONObject(jsonData);
                    titleToDisplay.add(json.getString("title"));
                    messageToDisplay.add(json.getString("dateAndTime") + "\n" +json.getString("title") + "\nMessage:\t" + json.getString("alert"));
                    urlToConnect.add(json.getString("url"));
                } catch (JSONException e) {

                }
            }
            for (int i = 0; i< messageToDisplay.size(); i++) {
                indexOfArrayList.put(messageToDisplay.get(i), i);
            }
            Collections.sort(messageToDisplay, Collections.reverseOrder());
            final List<String> sortedTitleToDisplay = new ArrayList<String>();
            final List<String> sortedUrlToConnect = new ArrayList<String>();
            for (int i = 0; i< messageToDisplay.size(); i++) {
                sortedTitleToDisplay.add(titleToDisplay.get(indexOfArrayList.get(messageToDisplay.get(i))));
                sortedUrlToConnect.add(urlToConnect.get(indexOfArrayList.get(messageToDisplay.get(i))));
            }
            FloatingActionButton fabdelete = (FloatingActionButton) rootView.findViewById(R.id.fabnotificationdelete);
            TextView noNotificaiton = (TextView) rootView.findViewById(R.id.nonotification);
            ArrayAdapter<String> adaptorForNotifications = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, messageToDisplay);
            listView.setAdapter(adaptorForNotifications);
            if (messageToDisplay.isEmpty()) {
//                TextView textView = new TextView(getActivity());
//                textView.setTextSize(25);
//                textView.setText("No Notifications");
//                textView.setGravity(Gravity.CENTER);
                fabdelete.setVisibility(View.INVISIBLE);
                noNotificaiton.setVisibility(View.VISIBLE);
                return rootView;
            } else {
                fabdelete.setVisibility(View.VISIBLE);
                noNotificaiton.setVisibility(View.INVISIBLE);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        intentForWebViewFromNotifications.putExtra(urlForNotification, sortedUrlToConnect.get(position));
                        intentForWebViewFromNotifications.putExtra(titleForNotification, sortedTitleToDisplay.get(position));
                        startActivity(intentForWebViewFromNotifications);
                    }
                });
                return rootView;

            }
        }
    }

    public void floatingActionButtonforNotification (View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Warning !");
        alertDialog.setMessage("Are you sure you want to delete all the Notifications ?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences message = getSharedPreferences("message", Context.MODE_PRIVATE);
                        // Set<String>messageSet = message.getStringSet("messageSet", new HashSet<String>());
                        SharedPreferences.Editor messageEditor = message.edit();
                        //messageSet.clear();
                        // messageEditor.putStringSet("messageSet", messageSet);
                        messageEditor.remove("messageSet");
                        messageEditor.apply();
                        getInstance().recreate();
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        alertDialog.show();
    }
}


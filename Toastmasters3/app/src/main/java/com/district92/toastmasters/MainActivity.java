package com.district92.toastmasters;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import models.rolesModel;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    public final static String positionOfSelectionOfObjectFromClubFragment = "com.district92.toastmasters.positionOfClub";
    public final static String positionOfSelectionOfObjectFromLeadershipFragment = "com.district92.toastmasters.positionOfLeadership";
    public final static String positionOfSelectionOfObjectFromEducationFragment = "com.district92.toastmasters.positionOfEducation";
    public final static String titleOfEducationDetailPage = "com.district92.toastmasters.positionOfEducation.listOfEducationItems";
    public final static String mymessage = "com.district92.toastmasters.positionOfEducation.message";
    public final static String titleOfLeadershipDetailPage = "com.district92.toastmasters.titleOfLeadership";
    public final static String roleTitleintent = "com.district92.toastmasters.roleTitle";
    public final static String roleDataintent = "com.district92.toastmasters.roleData";


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
            case R.id.aboutToastmasters:
                Intent aboutToastmastersIntent = new Intent(this, aboutToastmastersActivity.class);
                startActivity(aboutToastmastersIntent);
                return true;
            case R.id.findClub:
                Intent findClubIntent = new Intent (this, ClubSearchActivity.class);
                startActivity(findClubIntent);
                return true;
            case R.id.dcpPoints:
                Intent DCPPoint = new Intent (this, dcpPoints.class);
                startActivity(DCPPoint);
                return true;
            default: return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        Snackbar snackBar = Snackbar.make(mViewPager, R.string.snack_bar, Snackbar.LENGTH_SHORT);
        if (tab.getPosition() == 1) {
            snackBar.show();
            mViewPager.setCurrentItem(tab.getPosition());
        }
        else
        {
            mViewPager.setCurrentItem(tab.getPosition());
            snackBar.dismiss();
        }
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
                return new clubFragment();
            }
            if (position == 1) {
                return new educationFragment();
            }
            if (position == 2) {
                return new leadershipFragment();
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

    public static class clubFragment extends Fragment {



        private ListView clubListView;
        String[] listOfClubItems = {"Timer", "Ah-Counter", "Word For The Day", "Grammarian", "Toastmaster", "Topics Master", "General Evaluator", "Individual Evaluator"};

        private static final String ARG_SECTION_NUMBER = "section_number";

        public clubFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_club, container, false);
            clubListView = (ListView)rootView.findViewById(R.id.listView_club);
            ArrayAdapter<String> clubStringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOfClubItems);
            clubListView.setAdapter(clubStringArrayAdapter);
            clubListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int positionOfClub, long id) {
                    //Toast.makeText(getActivity(), listStrings[position], Toast.LENGTH_LONG).show();
                   // Snackbar.make(view, listStrings[position], Snackbar.LENGTH_LONG).show();
                    switch(positionOfClub) {
                        case 0 :
                        Intent intentFromClubFragment = new Intent(getActivity(), timerSelectionActivity.class);
                        intentFromClubFragment.putExtra(positionOfSelectionOfObjectFromClubFragment, positionOfClub);
                        startActivity(intentFromClubFragment);
                            break;
                        case 1 :
                            Intent intentForAhCounter = new Intent(getActivity(), AhCounterSelection.class);
                            startActivity(intentForAhCounter);
                            break;
                        case 2 :
                            Intent intentForWordForTheDay = new Intent(getActivity(), NewWord.class);
                            startActivity(intentForWordForTheDay);
                            break;
                        default :
                            Intent intentForRemainingRoles = new Intent(getActivity(), roleDetail.class);
                            intentForRemainingRoles.putExtra(positionOfSelectionOfObjectFromClubFragment, positionOfClub-3);
                            intentForRemainingRoles.putExtra(roleTitleintent, listOfClubItems[positionOfClub]);
                            rolesModel dataToSetText = new rolesModel();
                            intentForRemainingRoles.putExtra(roleDataintent,dataToSetText.allTheDataForRoleDetail(positionOfClub-3));
                            startActivity(intentForRemainingRoles);
                            break;
                    }
                }
            });
            return rootView;
        }

        public void startFindClubActivity (View view){

        }
    }
    public static class educationFragment extends Fragment {



        private ListView educationListView;
        String[] listOfEducationItems = {"1 - The Ice Breaker", "2 - Organize Your Speech", "3 - Get To The Point", "4 - How To Say It", "5 - Your Body Speaks",
        "6 - Vocal Variety", "7 - Research Your Topic", "8 - Get Comfortable With visual Aids", "9 - Persuade With Power", "10 - Inspire Your Audience",
                "AC - The Entertaining Speaker",
                "AC - Speaking to Inform",
                "AC - Public Relations",
                "AC - Facilitating Discussion",
                "AC - Specialty Speeches",
                "AC - Speeches by Management",
                "AC - The Professional Speaker",
                "AC - Technical Presentations",
                "AC - Persuasive Speaking",
                "AC - Communicating on Video",
                "AC - Storytelling" ,
                "AC - Interpretive Reading" ,
                "AC - Interpersonal Communication" ,
                "AC - Special Occasion Speeches",
                "AC - Humorously Speaking" };

        private static final String ARG_SECTION_NUMBER = "section_number";

        public educationFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_education, container, false);
            educationListView = (ListView)rootView.findViewById(R.id.education_listview);
            ArrayAdapter<String> educationStringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOfEducationItems);
            educationListView.setAdapter(educationStringArrayAdapter);
            // Snackbar.make(activity_main,R.string.snack_bar, Snackbar.LENGTH_LONG).show();
            educationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int positionOfEducation, long id) {
                    //Toast.makeText(getActivity(), listStrings[position], Toast.LENGTH_LONG).show();
                     // Snackbar.make(view, "AC - Advanced Communication Track", Snackbar.LENGTH_LONG).show();
                    Intent intentFromEducationFragment = new Intent(getActivity(), educationDetailActivity.class);
                    intentFromEducationFragment.putExtra(positionOfSelectionOfObjectFromEducationFragment, positionOfEducation);
                    intentFromEducationFragment.putExtra(titleOfEducationDetailPage, listOfEducationItems[positionOfEducation]);
                    startActivity(intentFromEducationFragment);
                }
            });
            return rootView;
        }
    }
    public static class leadershipFragment extends Fragment {



        private ListView leadershipListView;
        String[] listOfLeadershipItems = {"President", "VP - Education", "VP - Membership", "VP - Public Relations", "Secretary", "Treasurer", "Sergeant-at-Arms"};

        private static final String ARG_SECTION_NUMBER = "section_number";

        public leadershipFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_leadership, container, false);
            leadershipListView = (ListView)rootView.findViewById(R.id.leadership_listView);
            ArrayAdapter<String> leadershipStringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOfLeadershipItems);
            leadershipListView.setAdapter(leadershipStringArrayAdapter);
            leadershipListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int positionOfLeadership, long id) {
                    Intent intentFromLeadershipFragment = new Intent(getActivity(), LeadershipDetailActivity.class);
                    intentFromLeadershipFragment.putExtra(positionOfSelectionOfObjectFromLeadershipFragment, positionOfLeadership);
                    intentFromLeadershipFragment.putExtra(titleOfEducationDetailPage, listOfLeadershipItems[positionOfLeadership]);
                    startActivity(intentFromLeadershipFragment);
                }
            });
            return rootView;
        }
    }
}

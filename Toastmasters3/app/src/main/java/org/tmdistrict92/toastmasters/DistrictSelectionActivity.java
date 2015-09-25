//package org.tmdistrict92.toastmasters;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//
//import com.parse.ParseInstallation;
//import com.parse.ParsePush;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DistrictSelectionActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setTitle("Select Your District");
//        setContentView(R.layout.activity_district_selection);
//        addItemsToSpinner();
//    }
//
//    public void addItemsToSpinner () {
//        List<String> itemsToDistrictSpinner = new ArrayList<String>();
//        Spinner districtSpinner = (Spinner) findViewById(R.id.spinnerForDistrictSelection);
////        itemsToDistrictSpinner.add("DISTRICT-2");
////        itemsToDistrictSpinner.add("DISTRICT-9");
////        itemsToDistrictSpinner.add("DISTRICT-15");
////        itemsToDistrictSpinner.add("DISTRICT-21");
////        itemsToDistrictSpinner.add("DISTRICT-26");
////        itemsToDistrictSpinner.add("DISTRICT-32");
////        itemsToDistrictSpinner.add("DISTRICT-96");
////        itemsToDistrictSpinner.add("DISTRICT-4");
////        itemsToDistrictSpinner.add("DISTRICT-7");
////        itemsToDistrictSpinner.add("DISTRICT-33");
////        itemsToDistrictSpinner.add("DISTRICT-39");
////        itemsToDistrictSpinner.add("DISTRICT-49");
////        itemsToDistrictSpinner.add("DISTRICT-57");
////        itemsToDistrictSpinner.add("DISTRICT-16");
////        itemsToDistrictSpinner.add("DISTRICT-22");
////        itemsToDistrictSpinner.add("DISTRICT-23");
////        itemsToDistrictSpinner.add("DISTRICT-25");
////        itemsToDistrictSpinner.add("DISTRICT-50");
////        itemsToDistrictSpinner.add("DISTRICT-55");
////        itemsToDistrictSpinner.add("DISTRICT-56");
////        itemsToDistrictSpinner.add("DISTRICT-6");
////        itemsToDistrictSpinner.add("DISTRICT-19");
////        itemsToDistrictSpinner.add("DISTRICT-24");
////        itemsToDistrictSpinner.add("DISTRICT-42");
////        itemsToDistrictSpinner.add("DISTRICT-64");
////        itemsToDistrictSpinner.add("DISTRICT-78");
////        itemsToDistrictSpinner.add("DISTRICT-8");
////        itemsToDistrictSpinner.add("DISTRICT-11");
////        itemsToDistrictSpinner.add("DISTRICT-30");
////        itemsToDistrictSpinner.add("DISTRICT-35");
////        itemsToDistrictSpinner.add("DISTRICT-40");
////        itemsToDistrictSpinner.add("DISTRICT-43");
////        itemsToDistrictSpinner.add("DISTRICT-54");
////        itemsToDistrictSpinner.add("DISTRICT-63");
////        itemsToDistrictSpinner.add("DISTRICT-10");
////        itemsToDistrictSpinner.add("DISTRICT-28");
////        itemsToDistrictSpinner.add("DISTRICT-60");
////        itemsToDistrictSpinner.add("DISTRICT-61");
////        itemsToDistrictSpinner.add("DISTRICT-62");
////        itemsToDistrictSpinner.add("DISTRICT-86");
////        itemsToDistrictSpinner.add("DISTRICT-13");
////        itemsToDistrictSpinner.add("DISTRICT-18");
////        itemsToDistrictSpinner.add("DISTRICT-27");
////        itemsToDistrictSpinner.add("DISTRICT-29");
////        itemsToDistrictSpinner.add("DISTRICT-36");
////        itemsToDistrictSpinner.add("DISTRICT-37");
////        itemsToDistrictSpinner.add("DISTRICT-38");
////        itemsToDistrictSpinner.add("DISTRICT-66");
////        itemsToDistrictSpinner.add("DISTRICT-14");
////        itemsToDistrictSpinner.add("DISTRICT-44");
////        itemsToDistrictSpinner.add("DISTRICT-47");
////        itemsToDistrictSpinner.add("DISTRICT-48");
////        itemsToDistrictSpinner.add("DISTRICT-58");
////        itemsToDistrictSpinner.add("DISTRICT-68");
////        itemsToDistrictSpinner.add("DISTRICT-77");
////        itemsToDistrictSpinner.add("DISTRICT-81");
////        itemsToDistrictSpinner.add("DISTRICT-84");
////        itemsToDistrictSpinner.add("DISTRICT-31");
////        itemsToDistrictSpinner.add("DISTRICT-45");
////        itemsToDistrictSpinner.add("DISTRICT-46");
////        itemsToDistrictSpinner.add("DISTRICT-53");
////        itemsToDistrictSpinner.add("DISTRICT-65");
////        itemsToDistrictSpinner.add("DISTRICT-83");
////        itemsToDistrictSpinner.add("DISTRICT-1");
////        itemsToDistrictSpinner.add("DISTRICT-3");
////        itemsToDistrictSpinner.add("DISTRICT-5");
////        itemsToDistrictSpinner.add("DISTRICT-12");
////        itemsToDistrictSpinner.add("DISTRICT-34");
////        itemsToDistrictSpinner.add("DISTRICT-52");
////        itemsToDistrictSpinner.add("DISTRICT-20");
////        itemsToDistrictSpinner.add("DISTRICT-59");
////        itemsToDistrictSpinner.add("DISTRICT-71");
////        itemsToDistrictSpinner.add("DISTRICT-74");
////        itemsToDistrictSpinner.add("DISTRICT-79");
////        itemsToDistrictSpinner.add("DISTRICT-91");
////        itemsToDistrictSpinner.add("DISTRICT-94");
////        itemsToDistrictSpinner.add("DISTRICT-95");
////        itemsToDistrictSpinner.add("DISTRICT-17");
////        itemsToDistrictSpinner.add("DISTRICT-69");
////        itemsToDistrictSpinner.add("DISTRICT-70");
////        itemsToDistrictSpinner.add("DISTRICT-72");
////        itemsToDistrictSpinner.add("DISTRICT-73");
////        itemsToDistrictSpinner.add("DISTRICT-90");
//        itemsToDistrictSpinner.add("DISTRICT-92");
// //       itemsToDistrictSpinner.add("DISTRICT-41");
////        itemsToDistrictSpinner.add("DISTRICT-67");
////        itemsToDistrictSpinner.add("DISTRICT-76");
// //       itemsToDistrictSpinner.add("DISTRICT-82");
////        itemsToDistrictSpinner.add("DISTRICT-85");
////        itemsToDistrictSpinner.add("DISTRICT-89");
////        itemsToDistrictSpinner.add("DISTRICT-93");
////        itemsToDistrictSpinner.add("DISTRICT-98");
////        itemsToDistrictSpinner.add("DISTRICT-51");
////        itemsToDistrictSpinner.add("DISTRICT-75");
////        itemsToDistrictSpinner.add("DISTRICT-80");
////        itemsToDistrictSpinner.add("DISTRICT-87");
////        itemsToDistrictSpinner.add("DISTRICT-97");
//        ArrayAdapter<String> districtArrayAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemsToDistrictSpinner);
//        districtArrayAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        districtSpinner.setAdapter(districtArrayAdaptor);
//    }
//
//    public void districtDoneButton (View view) {
//        String districtValue = ((Spinner) findViewById(R.id.spinnerForDistrictSelection)).getSelectedItem().toString();
//        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
//        installation.remove("channels");
//            ParsePush.subscribeInBackground(districtValue);
//        Intent intentBackToMainActivity = new Intent(this, MainActivity.class);
//        startActivity(intentBackToMainActivity);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_district_selection, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}

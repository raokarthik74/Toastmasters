package com.karthikravindrarao.TM_District_92;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class storeActivity extends AppCompatActivity {

    public final static String urlForWebViewStore = "com.district92.toastmasters.urlForWebViewStore";

    Intent intentToWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Store");
        setContentView(R.layout.activity_store);
        intentToWebView = new Intent(this, webView.class);
    }
    public void howToOrder (View view) {
        Intent intentToTextDisplay = new Intent(this, textDisplayActivity.class);
        intentToTextDisplay.putExtra(MainActivity.textForDisplay, "DISTRICT 92 STORE\n" +
                "District 92 has decided to pilot a new service model for the benefit of its members and clubs; thus, came up with the idea of the new District 92 Store. Pre-order and pre-pay what you want. The district will then order the merchandise from the Toastmasters International Shop (TI Shop) and have your order ready for pick-up or shipped to you at your convenience. You save significantly on international shipping charges, credit card fees, and custom duties! \n" +
                "\n" +
                "How to Place your Order?\n" +
                "We are offering the TI Shop merchandise listed in our Product List document available on this page. If an item you want is not listed, contact our D92-Logistics Manager (d92logisticsmanager@gmail.com) and we will add the item to our inventory. Once you have made your selection, it’s time to place the order. To place an order, please complete and submit our Order Request Form given below. Our team will get in touch with you, within 2-3 business days, with the final price for your order (includes international shipping and customs duty and pre-payment instructions.\n" +
                " \n" +
                "Pre-payment:\n" +
                "The District will place one single bulk order by the 10th of every month. Therefore, we request that pre-payments must clear the district’s bank account by the 5th of every month. After this date, all unpaid order requests will either be cancelled or pushed to the following month.\n" +
                " \n" +
                "Pickup / Shipment:\n" +
                "If the District has your order in stock, we will coordinate with you for immediate pick-up or shipping. If your request needs to be ordered in, then depending on your order, it’ll take approximately 10-20 business days to arrive in India. Once your order has arrived, our team will notify you. You can either choose to arrange for a local pick-up with our team or have the order shipped to you at an additional cost. Please indicate your choice of delivery in the Order Request Form.\n" +
                " \n" +
                "Changes, Cancellations, & Refunds:\n" +
                "To cancel or change an existing order, please contact our team. In the event of cancellation of an already pre-paid order, we can refund 100% only if the cancellation is submitted prior to the 5th of the month.\n" +
                " \n" +
                "Contact:\n" +
                "For any queries, please contact D92-Logistics Manager, Vineeth Shiyal, at d92logisticsmanager@gmail.com. ");
        startActivity(intentToTextDisplay);
    }
    public void listOfItems (View view) {
        intentToWebView.putExtra(MainActivity.urlForWebView, "https://docs.google.com/spreadsheets/d/1K1Dm6oWJ1IEnAVDIVf_tjajv7IdEIfsE_nWoNgLSsYU/edit#gid=44957060");
        startActivity(intentToWebView);
    }
    public void placeOrder (View view) {
        intentToWebView.putExtra(MainActivity.urlForWebView, "https://docs.google.com/forms/d/1npZ0mq8gbF0kAGOKvru7AvyLCTco_R0IwIK4sQZ-ajI/viewform?embedded=true");
        startActivity(intentToWebView);
    }
    public void contactEmail (View view) {
        Intent send = new Intent(Intent.ACTION_SENDTO);
        String uriText = "mailto:" + Uri.encode("d92logisticsmanager@gmail.com") +
                "?subject=" + Uri.encode("") +
                "&body=" + Uri.encode("");
        Uri uri = Uri.parse(uriText);
        send.setData(uri);
        startActivity(Intent.createChooser(send, ""));
    }

    @Override
     public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

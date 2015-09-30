package models;

import java.util.ArrayList;

/**
 * Created by Karthik on 9/25/15.
 */
public class AllNotifications {

    //public static ArrayList<String> notificationsForDisplay = new ArrayList<String>();
    public static ArrayList<String> notificationsForDisplayMessage = new ArrayList<String>();
    public static ArrayList<String> UrlsForNotifications = new ArrayList<String>();

//    public static void setDataToNotificaitons (String name) {
//        notificationsForDisplay.add(name);
//    }
//
//    public static String getDataFromNotificaitons (int position) {
//        return notificationsForDisplay.get(position);
//    }

    public static void setDataToNotificationMessage (String name) {
        notificationsForDisplayMessage.add(name);
    }

    public static ArrayList<String> getDataFromNotificationMessage () {
        return notificationsForDisplayMessage;
    }

    public static void resetNotificationList () {
       // notificationsForDisplay.clear();
        notificationsForDisplayMessage.clear();
    }

    public static void setUrlsForNotifications (String name) {
        UrlsForNotifications.add(name);
    }
    public static String getUrlsForNotifications (int position) {
        return UrlsForNotifications.get(position);
    }

    public static void resetUrlsForNotifications () {
        UrlsForNotifications.clear();
    }
}

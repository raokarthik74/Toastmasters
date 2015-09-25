package models;

import java.util.ArrayList;

/**
 * Created by Karthik on 9/25/15.
 */
public class AllNotifications {

    public static ArrayList<String> notificationsForDisplay = new ArrayList<String>();
    public static ArrayList<String> UrlsForNotifications = new ArrayList<String>();

    public static void setDataToNotoficaitons (String name) {
        notificationsForDisplay.add(name);
    }

    public static ArrayList<String> getDataFromNotificaitons () {
        return notificationsForDisplay;
    }

    public static void resetNotoficationList () {
        notificationsForDisplay.clear();
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

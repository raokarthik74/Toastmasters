package models;

import java.util.ArrayList;

/**
 * Created by Karthik on 9/18/15.
 */
public class ahCounterReport {

    public static ArrayList<String> dataForDisplay = new ArrayList<String>();

    public static void setDataToArray (String name) {
        dataForDisplay.add(name);
    }
    public static ArrayList<String> getDataFromArray () {
        return dataForDisplay;
    }

    public static void resetArrayList () {
        dataForDisplay.clear();
    }
}

package models;

import java.util.ArrayList;

/**
 * Created by Karthik on 9/18/15.
 */
public class TimerReportModel {
    public static ArrayList<String> dataForDisplayForTimer = new ArrayList<String>();

    public static void setDataToTimerArray (String name) {
        dataForDisplayForTimer.add(name);
    }
    public static ArrayList<String> getDataFromTimerArray () {
        return dataForDisplayForTimer;
    }

    public static void resetArrayListOFTimer () {
        dataForDisplayForTimer.clear();
    }
}

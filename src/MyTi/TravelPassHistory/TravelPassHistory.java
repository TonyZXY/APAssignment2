package MyTi.TravelPassHistory;

import java.util.ArrayList;

import MyTi.TravelPass.TravelPass;
/**
 * Project APAssignment2
 * Created by TonyZheng on 24/9/16.
 */
public class TravelPassHistory {
    public String getId() {
        return id;
    }

    public TravelPass getTravelPass() {
        return travelPass;
    }

    String id;
    TravelPass travelPass;

    public TravelPassHistory(String id, TravelPass travelPass) {
        this.id = id;
        this.travelPass = travelPass;
    }

    public static ArrayList getTravelPassHistory() {
        return travelPassHistory;
    }

    public static ArrayList<TravelPassHistory> travelPassHistory = new ArrayList<>();

}

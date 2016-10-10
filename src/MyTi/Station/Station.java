package MyTi.Station;

/**
 * Project APAssignment2
 * Created by TonyZheng on 24/9/16.
 */
public class Station {
    public String getStationName() {
        return stationName;
    }

    private String stationName;

    public int getZone() {
        return zone;
    }

    private int zone;

    public Station(String StationName, int Zone) {
        this.stationName = StationName;
        this.zone = Zone;
    }
}

package MyTi.TravelPass;

import java.util.Calendar;
/**
 * Project APAssignment2
 * Created by TonyZheng on 24/9/16.
 */
public class TravelPass {
    //    Date date;
    private Calendar calendar;
    private char Duration;
    private int zone;

    public String getStartName() {
        return startName;
    }

    public String getEndName() {
        return endName;
    }

    public char getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    private double price;

    private String startName;
    private String endName;
    private char type;

    public TravelPass(Calendar calendar, char duration, int zone, String startName, String endName, char type,double price) {
        this.startName = startName;
        this.endName = endName;
        this.type = type;
//        this.date = date;
        this.calendar = calendar;
        this.Duration = duration;
        this.zone = zone;
        this.price=price;
    }

    public TravelPass(Calendar calendar, char duration, int zone, String startName, char type,double price) {
        this.startName = startName;
        this.Duration = duration;
        this.zone = zone;
        this.type = type;
        this.calendar = calendar;
        this.price=price;
    }


    public Calendar getCalendar() {
        return calendar;
    }

    public char getDuration() {
        return Duration;
    }

    public int getZone() {
        return zone;
    }

}

package MyTi.TravelPass;

import java.util.Calendar;
/**
 * Project APAssignment2
 * Created by TonyZheng on 24/9/16.
 */
public class TravelPass {
    //    Date date;
    private Calendar calendar;

    public int getTicketType() {
        return ticketType;
    }

    private int ticketType;

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

    public TravelPass(Calendar calendar, int ticketType, String startName, String endName, char type,double price) {
        this.startName = startName;
        this.endName = endName;
        this.type = type;
//        this.date = date;
        this.calendar = calendar;
        this.ticketType = ticketType;
        this.price=price;
    }

    public TravelPass(Calendar calendar, int ticketType, String startName, char type,double price) {
        this.startName = startName;
        this.ticketType = ticketType;
        this.type = type;
        this.calendar = calendar;
        this.price=price;
    }


    public Calendar getCalendar() {
        return calendar;
    }

}

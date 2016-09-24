package MyTi.TopUpHistory;

import java.util.Calendar;
import java.util.Date;
/**
 * Project APAssignment2
 * Created by TonyZheng on 24/9/16.
 */
public class TopUpHistory {
    public double getBalance() {
        return balance;
    }

    public Date getDate() {
        return date.getTime();
    }

    private double balance;
    private Calendar date;

    public TopUpHistory(double balance, Calendar date) {
        this.balance = balance;
        this.date = date;
    }
}

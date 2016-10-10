package MyTi;

import MyTi.TopUpHistory.TopUpHistory;
import MyTi.TravelPass.TravelPass;

import java.util.ArrayList;
/**
 * Project APAssignment2
 * Created by TonyZheng on 24/9/16.
 */
public class Users {
    private String id;
    private String userName;
    private String email;
    private double balance;
    private char type;

    public ArrayList<TravelPass> getHistory() {
        return history;
    }

    private ArrayList<TravelPass> history = new ArrayList<TravelPass>();

    public ArrayList<TopUpHistory> getTopUpHistories() {
        return topUpHistories;
    }

    private ArrayList<TopUpHistory> topUpHistories = new ArrayList<TopUpHistory>();

    public Users(String id, String userName, String email, double balance, char type) { //this add new user is for test.
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.balance = balance;
        this.type = type;
    }

    public Users(String id, String userName, String email, char type) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.balance = 0.0;
        this.type = type;
    }


    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void topUp(double amount) {
        balance = balance + amount;
    }

    public void purchase(double amount) {
        balance = balance - amount;
    }

    public char getType() {
        return type;
    }

    public int historySize() {
        return history.size();
    }

    public int topUpHistorySize() {
        return topUpHistories.size();
    }
}

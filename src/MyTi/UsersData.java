package MyTi;

import MyTi.TopUpHistory.TopUpHistory;

import java.util.*;

import MyTi.Station.*;

/**
 * Project APAssignment2
 * Created by TonyZheng on 24/9/16.
 */
public class UsersData {
    // this is users data storage
    static HashMap<String, Users> users = new HashMap<>();

    public static void addNewUser(String id, String userName, String email, double balance, char type) {
        users.put(id, new Users(id, userName, email, balance, type));
        userNames.add(id);
    }//this add method is for test.

    public static void addNewUser(String id, String userName, String email, char type) {
        users.put(id, new Users(id, userName, email, type));
        userNames.add(id);
    }

    private static ArrayList<String> userNames = new ArrayList<>();

    public ArrayList getUserNames() {
        return userNames;
    }
/*    public static double checkUserID(String ID){
        String id = ID;
        double balance ;
        try{
            balance = users.get(id).getBalance();
        }catch (Exception e){
            balance = -1;
        }
        return balance;
    }*/

    static double checkUserID(String ID) {
        double balance;
        try {
            balance = users.get(ID).getBalance();
        } catch (Exception e) {
            balance = -1;
        }
        return balance;
    }

    static boolean checkUser(String id) {
        boolean valid = false;
        if (users.containsKey(id)) {
            valid = true;
        }
        return valid;
    }

    static boolean checkStation(String stationName) {
        boolean valid = false;
        if (station.containsKey(stationName)) {
            valid = true;
        }
        return valid;
    }

    public static double getJrate() {
        return Jrate;
    }

    public static double getSrate() {
        return Srate;
    }

    public static double getArate() {
        return Arate;
    }

    public static double getSSrate() {
        return SSrate;
    }

    public static void setJrate(double jrate) {
        Jrate = jrate;
    }

    public static void setSrate(double srate) {
        Srate = srate;
    }

    public static void setArate(double arate) {
        Arate = arate;
    }

    public static void setSSrate(double SSrate) {
        UsersData.SSrate = SSrate;
    }

    private static double Jrate = 0.5;
    private static double Srate = 0.5;
    private static double Arate = 1.0;
    private static double SSrate = 0.0;


    static double getRate(String ID) { //here is rate setting
        double rate = 1;
        char type = users.get(ID).getType();
        Calendar date = Calendar.getInstance();
        if (type == 'A') {
            rate = Arate;
        }
        if (type == 'C') {
            rate = Jrate;
        }
        if (type == 'S') {
            rate = Srate;
//        }if(type =='C'&&date.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
//            rate = 0.0;
        }
        if (type == 'S' && date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            rate = SSrate;
        }
        return rate;
    }

    public static double getRateDB(char type){
        double rate = 1;
        Calendar date = Calendar.getInstance();
        if (type == 'A') {
            rate = Arate;
        }
        if (type == 'C') {
            rate = Jrate;
        }
        if (type == 'S') {
            rate = Srate;
//        }if(type =='C'&&date.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
//            rate = 0.0;
        }
        if (type == 'S' && date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            rate = SSrate;
        }
        return rate;
    }

//    static Hashmap<String,> history = new HashMap<>();

    // this is stations data storage
    static HashMap<String, Station> station = new HashMap<>();
    static ArrayList<String> stationsName = new ArrayList<>();

    public void addStation(String name, int zone) {
        station.put(name, new Station(name, zone));
        stationsName.add(name + "            " + zone);
    }

//    public void travelHistory(String id, double balance) {
//
//    }

    static void topUp(String id, double balance) {
        users.get(id).topUp(balance);
        Calendar date = Calendar.getInstance();
        users.get(id).getTopUpHistories().add(new TopUpHistory(balance, date));
    }

    static boolean checkAdmin(String username, String password) {
        String name = "Admin";
        String pass = "Admin";
        boolean valid = false;
        if (Objects.equals(name, username) && Objects.equals(pass, password)) {
            valid = true;
        }
        return valid;
    }
}

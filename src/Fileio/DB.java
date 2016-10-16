package Fileio;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import MyTi.MyTiSystem;
import MyTi.TravelPass.TravelPass;
import org.hsqldb.Server;

/**
 * Project APAssignment2
 * Created by TonyZheng on 27/9/16.
 */
public class DB {
    public static void startDB() {
        Server hsqlServer = null;
        Connection connection = null;
        ResultSet rs = null;
        hsqlServer = new Server();
        hsqlServer.setLogWriter(null);
        hsqlServer.setSilent(true);
        hsqlServer.setDatabaseName(0, "TestDB");
        hsqlServer.setDatabasePath(0, "file:MYDB");
        hsqlServer.start();

        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            connection.prepareStatement("DROP table station if EXISTS ;").execute();
            connection.prepareStatement("DROP table users if EXISTS ;").execute();
            connection.prepareStatement("DROP table travelpass if EXISTS ;").execute();
            connection.prepareStatement("DROP table history if EXISTS ;").execute();
            connection.prepareStatement("DROP table topup if EXISTS ;").execute();
            connection.prepareStatement("DROP table topuphistory if EXISTS ;").execute();
            connection.prepareStatement("DROP TABLE travelpasshistory if EXISTS ;").execute();
            connection.prepareStatement("CREATE table station (name VARCHAR (20), zone INTEGER );").execute();
            connection.prepareStatement("CREATE TABLE users (userID VARCHAR (20), userName VARCHAR (40),balance FLOAT , email VARCHAR (50),tickettype CHAR,PRIMARY key (userID));").execute();
            connection.prepareStatement("CREATE TABLE travelpass(passid VARCHAR (20),pricetype INTEGER ,price FLOAT ,tickettype char,time TIMESTAMP ,PRIMARY key (passid));").execute();
            connection.prepareStatement("CREATE TABLE history(passid VARCHAR (20),userid VARCHAR (20),PRIMARY key (passid,userid),FOREIGN KEY (passid) REFERENCES travelpass(passid),FOREIGN KEY (userid) REFERENCES users(userid));").execute();
            connection.prepareStatement("CREATE TABLE topup(topupid VARCHAR (20),balance FLOAT ,topuptime TIMESTAMP ,PRIMARY key(topupid));").execute();
            connection.prepareStatement("CREATE TABLE topuphistory(userid VARCHAR (20),topupid VARCHAR (20),PRIMARY KEY (userid,topupid),FOREIGN key(userid) REFERENCES users(userid),FOREIGN KEY (topupid) REFERENCES topup(topupid));").execute();
            connection.prepareStatement("CREATE TABLE travelpasshistory(historyid VARCHAR (20),passid VARCHAR (20),stationfrom VARCHAR (20),stationto VARCHAR(20),traveltime TIMESTAMP,primary KEY (historyid),FOREIGN KEY (passid) REFERENCES travelpass(passid));").execute();
            Statement insertStation = connection.createStatement();
            insertStation.addBatch("insert into station values ('Central', 1);");
            insertStation.addBatch("insert into station values ('Flagstaff', 1);");
            insertStation.addBatch("INSERT into station values ('Richmond',1);");
            insertStation.addBatch("insert into station values ('Lilydale',2);");
            insertStation.addBatch("insert into station values ('Epping',2);");
            insertStation.executeBatch();
            connection.prepareStatement("insert into users values ('lc','Lawrence Cavedon',0,'lawrence.cavedon@rmit.edu.au','A');").execute();
            connection.prepareStatement("insert into users values ('abc','abcde',40.0,'abcdefg','C');").execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    static int travelPassID = 10000000;
    static int topUpID = 10000000;
    static int historyid = 10000000;

    public static void addTravelPassDB(String usersID, int priceType, char type, Calendar date, double price) {
        try {
            Connection addTravel = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Timestamp ts = new Timestamp(date.getTimeInMillis());
            double rate = MyTi.UsersData.getRateDB(type);
            String statement = "insert into travelpass values ( '" + travelPassID + "','" + priceType + "','" + price * rate + "','" + type + "','" + ts + "');";
            addTravel.prepareStatement(statement).execute();
            String statement2 = "insert into history values ('" + travelPassID + "','" + usersID + "');";
            addTravel.prepareStatement(statement2).execute();
            travelPassID++;
            addTravel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTravelPassHistoryDB(String passid, String stationFrom, String stationTo) {
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        try {
            Connection APH = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            String statement = "insert into travelpasshistory values('" + historyid + "','" + passid + "','" + stationFrom + "','" + stationTo + "','" + ts + "');";
            APH.prepareStatement(statement).execute();
            historyid++;
            APH.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addNewUserDB(String userID, String userName, double balance, String email, char type) {
        try {
            Connection addUser = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            String statement = "insert into users ( '" + userID + "','" + userName + "','" + balance + "','" + email + "','" + type + "');";
            addUser.prepareStatement(statement).execute();
            addUser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewUserDB(String userID, String userName, String email, char type) {
        try {
            Connection addUser = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            String statement = "insert into users('" + userID + "','" + userName + "','" + 0 + "','" + email + "','" + type + "');";
            addUser.prepareStatement(statement).execute();
            addUser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPassid(String id) {
        String passid = null;
        try {
            Connection getpassid = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Statement st = getpassid.createStatement();
            ResultSet rs = st.executeQuery("SELECT max(passid) from history where userid ='" + id + "';");
            while (rs.next()) {
                passid = rs.getString(1);
            }
            getpassid.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return passid;
    }

    public static double getUserBalanceDB(String userID) {
        double balance = -1;
        try {
            Connection getBalance = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            String statement = "select balance from users where userid = '" + userID + "';";
            ResultSet balanceGetter = getBalance.createStatement().executeQuery(statement);
            while (balanceGetter.next()) {
                balance = balanceGetter.getDouble(1);
            }
            getBalance.close();
        } catch (Exception e) {
            e.printStackTrace();
            balance = -1;
        }
        return balance;
    }

    public static char getUserTypeDB(String userID) {
        char type = 'A';
        try {
            Connection getType = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            String statement = "Select type from users where userid = '" + userID + "';";
            ResultSet typeGetter = getType.createStatement().executeQuery(statement);
            while (typeGetter.next()) {
                type = typeGetter.getString(1).charAt(0);
            }
            getType.close();
        } catch (Exception e) {
            e.printStackTrace();
            type = 'A';
        }
        return type;
    }

    public static void topUp(String id, double amount) {
        double balance = 0;
        try {
            Connection topUp = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Statement st = topUp.createStatement();
            ResultSet rs = st.executeQuery("select balance from users where userid = '" + id + "';");
            while (rs.next()) {
                balance = rs.getDouble(1);
            }
            double addBalance = balance + amount;
            String statement1 = "UPDATE users SET balance=" + "'" + addBalance + "'" + " where userid =" + "'" + id + "';";
            st.executeUpdate(statement1);
            Calendar calendar = Calendar.getInstance();
            Timestamp ts = new Timestamp(calendar.getTimeInMillis());
            String statement2 = "insert into topup('" + topUpID + "','" + balance + "','" + ts + "');";
            String statement3 = "insert into topuphistory('" + id + "','" + topUpID + "');";
            st.execute(statement2);
            st.execute(statement3);
            topUpID++;
            topUp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void topUpReport(String id) {
        try {
            Connection tpr = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Statement st = tpr.createStatement();
            ResultSet rs = st.executeQuery("SELECT topupid FROM topuphistory WHERE userid ='" + id + "' NATURAL JOIN topup;");
            System.out.println("TopUp history is here");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
            tpr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TravelPass getTravelPass(String id) {
        TravelPass pass = null;
        try {
            double price = 0;
            int type = 0;
            Timestamp time = null;
            char pricetype = 0;
            Calendar date = Calendar.getInstance();
            Connection tpg = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Statement st = tpg.createStatement();
            ResultSet rs = st.executeQuery("Select travelpass.price,travelpass.tickettype,travelpass.time,travelpass.pricetype FROM history,travelpass WHERE userid='" + id + "' and select max(passid);");
            while (rs.next()) {
                price = rs.getDouble(1);
                type = rs.getInt(2);
                time = rs.getTimestamp(3);
                pricetype = rs.getString(4).charAt(0);
                date.setTimeInMillis(time.getTime());
            }
            pass = new TravelPass(date, type, null, pricetype, price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pass;
    }

    public static ArrayList getStation() {
        ArrayList<String> stations = new ArrayList<>();
        try {
            Connection gs = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Statement st = gs.createStatement();
            ResultSet rs = st.executeQuery("SELECT name From stations;");
            while (rs.next()) {
                stations.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stations;
    }

    public static int getStationZone(String station) {
        int zone = 0;
        try {
            Connection gsz = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Statement st = gsz.createStatement();
            ResultSet rs = st.executeQuery("Select zone FROM stations WHERE name = '" + station + "';");
            while (rs.next()) {
                zone = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zone;
    }

    public static void travelReport(String id) {
        try {
            Connection tr = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Statement st = tr.createStatement();
            ResultSet rs = st.executeQuery("SELECT passid FROM history WHERE userid ='" + id + "' NATURAL JOIN travelpass;");
            System.out.println("travelPass history is here");
            while (rs.next()) {
                System.out.print(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
            }
            tr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList allTravelReport() {
        ArrayList<String> report = new ArrayList<>();
        try {
            Connection tr = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Statement st = tr.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM travelpasshistory;");
            while (rs.next()) {
                report.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }

    public static void purchase(String ID, double amount, char type) {
        double balance = 0;
        try {
            Connection purchase = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Statement st = purchase.createStatement();
            ResultSet rs = st.executeQuery("select balance from users where userid = '" + ID + "';");
            while (rs.next()) {
                balance = rs.getDouble(1);
            }
            double rate = MyTi.UsersData.getRateDB(type);
            double addBalance = balance - amount * rate;
            String statement1 = "UPDATE users SET balance=" + "'" + addBalance + "'" + " where userid =" + "'" + ID + "';";
            st.executeUpdate(statement1);
            purchase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRecentTravelPass(String id) {
        ArrayList<String> travelPass = new ArrayList<>();
        try {
            Connection travelPassGetter = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Statement st = travelPassGetter.createStatement();
            ResultSet rs = st.executeQuery("Select passid FROM history where userid = '" + id + "'and (SELECT max(passid) where history) natural JOIN travelpass;");
            while (rs.next()) {
                travelPass.add(rs.getString(1));
                travelPass.add(rs.getString(2));
                travelPass.add(rs.getString(3));
            }
            travelPassGetter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean checkValidTicket(String userID) {
        boolean valid = false;
        return valid;
    }

    public static ArrayList getStationStartReportDB() {
        ArrayList<String> stationFromReport = new ArrayList<>();
        try {
            Connection stationPassGetter = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Statement st = stationPassGetter.createStatement();
            String statement = "SELECT stationfrom FROM travelpasshistory;";
            ResultSet rs = st.executeQuery(statement);
            while (rs.next()) {
                stationFromReport.add(rs.getString(1));
            }
            stationPassGetter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stationFromReport;
    }

    public static ArrayList getStationTOReportDB() {
        ArrayList<String> stationToReport = new ArrayList<>();
        try {
            Connection stationPassGetter = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Statement st = stationPassGetter.createStatement();
            String statement = "SELECT stationto FROM travelpasshistory;";
            ResultSet rs = st.executeQuery(statement);
            while (rs.next()) {
                stationToReport.add(rs.getString(1));
            }
            stationPassGetter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stationToReport;
    }


    public static void purchaseTravelPass(String ID, int ticketType) {
        double amount = MyTiSystem.getPrice(ticketType);
        Calendar date = Calendar.getInstance();
        char type = getUserTypeDB(ID);
        purchase(ID, amount, type);
        addTravelPassDB(ID, ticketType, type, date, amount);
    }

    public static void orderTravelPass(String ID, int ticketType, Calendar date) {
        double amount = MyTiSystem.getPrice(ticketType);
        char type = getUserTypeDB(ID);
        purchase(ID, amount, type);
        addTravelPassDB(ID, ticketType, type, date, amount);
    }

//    public ArrayList selectTicketHistory(String userID){
//
//    }
//
//    public ArrayList selectTopUpHistory(String userID){
//
//    }

    public static void main(String[] args) {
        startDB();
    }
}

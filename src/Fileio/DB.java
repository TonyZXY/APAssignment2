package Fileio;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;

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
            connection.prepareStatement("CREATE table station (name VARCHAR (20), zone INTEGER );").execute();
            connection.prepareStatement("CREATE TABLE users (userID VARCHAR (20), userName VARCHAR (40),balance FLOAT , email VARCHAR (50),tickettype CHAR,PRIMARY key (userID));").execute();
            connection.prepareStatement("CREATE TABLE travelpass(passid VARCHAR (20),pricetype INTEGER ,price FLOAT ,tickettype char,time TIMESTAMP ,PRIMARY key (passid));").execute();
            connection.prepareStatement("CREATE TABLE history(passid VARCHAR (20),userid VARCHAR (20),PRIMARY key (passid,userid),FOREIGN KEY (passid) REFERENCES travelpass(passid),FOREIGN KEY (userid) REFERENCES users(userid));").execute();
            connection.prepareStatement("CREATE TABLE topup(topupid VARCHAR (20),balance FLOAT ,topuptime TIMESTAMP ,PRIMARY key(topupid));").execute();
            connection.prepareStatement("CREATE TABLE topuphistory(userid VARCHAR (20),topupid VARCHAR (20),PRIMARY KEY (userid,topupid),FOREIGN key(userid) REFERENCES users(userid),FOREIGN KEY (topupid) REFERENCES topup(topupid));").execute();
            Statement insertStation = connection.createStatement();
            insertStation.addBatch("insert into station values ('Central', 1);");
            //我是全世界最美最可爱的优娜娜，我是来捣乱的，我也不知道我写的是什么j8玩意儿，棒棒。
            insertStation.addBatch("insert into station values ('Flagstaff', 1);");
            insertStation.addBatch("INSERT into station values ('Richmond',1);");
            insertStation.addBatch("insert into station values ('Lilydale',2);");
            insertStation.addBatch("insert into station values ('Epping',2);");
            //安胡拉阿巴克！真主万岁！猪肉好吃！
            insertStation.executeBatch();
            connection.prepareStatement("insert into users values ('lc','Lawrence Cavedon',0,'lawrence.cavedon@rmit.edu.au','A');").execute();
            connection.prepareStatement("insert into users values ('abc','abcde',40.0,'abcdefg','C');").execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    int travelPassID = 10000000;
    int topUpID = 10000000;

    public void addTravelPassDB(String usersID, int priceType, char type, Calendar date, double price) {
        try {
            Connection addTravel = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
            Timestamp ts = new Timestamp(date.getTimeInMillis());
            String statement = "insert into travelpass values ( '" + travelPassID + "','" + priceType + "','" + price + "','" + type + "','" + ts + "');";
            addTravel.prepareStatement(statement).execute();
            String statement2 = "insert into history values ('" + travelPassID + "','" + usersID + "');";
            addTravel.prepareStatement(statement2).execute();
            travelPassID++;
            addTravel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewUserDB(String userID, String userName, double balance, String email, char type) {
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

    public void topUp(String id, double amount) {
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

    public void travelReport(String id) {
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


    public boolean checkValidTicket(String userID) {
        boolean valid = false;
        return valid;
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

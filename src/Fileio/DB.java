package Fileio;

import java.sql.*;
import java.util.Calendar;

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
//            connection.prepareStatement("DROP table barcodes if EXISTS ,").execute();
            connection.prepareStatement("CREATE table station (name VARCHAR (20), zone INTEGER );").execute();
            connection.prepareStatement("CREATE TABLE users (userID VARCHAR (20), userName VARCHAR (40),balance double, email VARCHAR (50),type CHAR,PRIMARY key (userID));").execute();
            connection.prepareStatement("CREATE TABLE travelpass(passid VARCHAR (20),zone INTEGER ,price DOUBLE ,duration char,type char,time DATA ,PRIMARY key (passid));").execute();
            connection.prepareStatement("CREATE TABLE history(passid VARCHAR (20),userid VARCHAR (20),PRIMARY key (passid,userid),FOREIGN KEY (passid) REFERENCES travelpass(passid),FOREIGN KEY (userid) REFERENCES users(userid))").execute();
            Statement insertStation = connection.createStatement();
            insertStation.addBatch("insert into station values ('Central', 1);");
            insertStation.addBatch("insert into station values ('Flagstaff', 1);");
            insertStation.addBatch("INSERT into station values ('Richmond',1)");
            insertStation.addBatch("insert into station values ('Lilydale',2)");
            insertStation.addBatch("insert into station values ('Epping',2)");
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

    public void addTravelPassDB(String usersID, int zone, char type, char duration, Calendar date,double price){
        try{
            Connection addTrivel = DriverManager.getConnection("jdbc:hsqldb:TestDB","sa","123");
            String statement = "insert into travelpass values ( '"+travelPassID+"','"+zone+"','"+price+"','"+duration+"','"+type+"','"+date.getTime()+"');";
            addTrivel.prepareStatement(statement).execute();
            String statment2 = "insert into history values ('"+travelPassID+"','"+usersID+"');";
            addTrivel.prepareStatement(statment2).execute();
            travelPassID++;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addNewUserDB(String userID,String userName,double balance,String email,char type){
        try{
            Connection addUser = DriverManager.getConnection("jdbc:hsqldb:TestDB","sa","123");
            String statement = "insert into users ( '"+userID+"','"+userName+"','"+balance+"','"+email+"','"+type+"';)";
            addUser.prepareStatement(statement).execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addNewUserDB(String userID,String userName,String email,char type){
        try{
            Connection addUser = DriverManager.getConnection("jdbc:hsqldb:TestDB","sa","123");
            String statement = "insert into users('"+userID+"','"+userName+"','"+0+"','"+email+"','"+type+"';)";
            addUser.prepareStatement(statement).execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getUserBalanceDB(String userID){
        try{
            Connection getBalance = DriverManager.getConnection("jdbc:hsqldb:TestDB","sa","123");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

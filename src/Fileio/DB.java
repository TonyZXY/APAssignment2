package Fileio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.Server;

/**
 * Project APAssignment2
 * Created by TonyZheng on 27/9/16.
 */
public class DB {
    public static void main(String[] args) {
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
            connection.prepareStatement("CREATE TABLE travelpass(passid VARCHAR (20),zone INTEGER ,price DOUBLE ,type char,time DATA ,PRIMARY key (passid));").execute();
            connection.prepareStatement("CREATE TABLE history(passid VARCHAR (20),userid VARCHAR (20),PRIMARY key (passid,userid),FOREIGN KEY (passid) REFERENCES travelpass(passid),FOREIGN KEY (userid) REFERENCES users(userid))").execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }
}

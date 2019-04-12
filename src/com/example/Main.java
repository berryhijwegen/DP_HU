package com.example;

import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException {
	    String dbUrl = "jdbc:oracle:thin:@localhost:1521:";
		String host = "localhost";
	    String user = "BERRY";
	    String pass = "123";
	    String serviceName = "XEPDB1";

	    Connection myConn = DriverManager.getConnection(String.format("jdbc:oracle:thin:%s/%s@//%s:1521/%s", user, pass, host, serviceName));

	    Statement myStmt = myConn.createStatement();

	    ResultSet myRs = myStmt.executeQuery("select * from AFDELINGEN where LOCATIE like '%EN%'");

	    while(myRs.next()){
	    	System.out.println(myRs.getString("locatie"));
		}
    }
}

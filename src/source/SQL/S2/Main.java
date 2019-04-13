package source.SQL.S2;

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

	    // Create
		myStmt.executeQuery("INSERT INTO INSCHRIJVINGEN (CURSIST, CURSUS, BEGINDATUM)" +
				"VALUES (7900, 'JAV', TO_DATE('01-02-2000', 'DD-MM-YYYY'))");

		myStmt.executeQuery("INSERT INTO CURSUSSEN " +
								 "VALUES ('ADP', 'Adaptive Programming', 'ALG', 1)");
		myStmt.executeQuery("INSERT INTO UITVOERINGEN " +
								 "VALUES ('ADP', TO_DATE('16-04-2019', 'DD-MM-YYYY'), 7566, 'UTRECHT')");
		myStmt.executeQuery("INSERT INTO INSCHRIJVINGEN (CURSIST, CURSUS, BEGINDATUM)" +
								 "VALUES (7900, 'ADP', TO_DATE('16-04-2019', 'DD-MM-YYYY'))");

		// Read
		ResultSet cursisten = myStmt.executeQuery("SELECT CURSIST FROM INSCRHIJVINGEN");
	    while(cursisten.next()){
	    	System.out.println(cursisten.getString("locatie"));
		}

	    // Update
		myStmt.executeQuery("UPDATE INSCHRIJVINGEN " +
								 "SET EVALUATIE = 4 "  +
								 "WHERE CURSIST = 7900");

	    // Delete
		myStmt.executeQuery("DELETE FROM INSCHRIJVINGEN " +
								 "WHERE EVALUATIE < 3");
    }
}

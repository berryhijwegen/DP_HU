package source.inleverOpdrachten.Persistence.P1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleBaseDao {
    protected void getConnection() throws SQLException {
        String host = "localhost";
        String user = "BERRY";
        String pass = "123";
        String serviceName = "XEPDB1";
        Connection myConn = DriverManager.getConnection(String.format("jdbc:oracle:thin:%s/%s@//%s:1521/%s", user, pass, host, serviceName));

    }

    public void closeConnection(){

    }
}

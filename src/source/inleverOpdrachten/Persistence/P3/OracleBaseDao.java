package source.inleverOpdrachten.Persistence.P3;
// Berry Hijwegen
// 1738601
// Persistentie inleveropdracht 1

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleBaseDao {
    private String DB_host;
    private String DB_user;
    private String DB_pass;
    private String DB_serviceName;
    private Connection connection;

    public OracleBaseDao(){
        DB_host = "localhost";
        DB_user = "OVCASUS";
        DB_pass = "123";
        DB_serviceName = "XEPDB1";

    }
    protected Connection getConnection() throws SQLException {
        System.out.println(String.format("jdbc:oracle:thin:%s/%s@//%s:1521/%s", DB_user, DB_pass, DB_host, DB_serviceName));
        connection = DriverManager.getConnection(String.format("jdbc:oracle:thin:%s/%s@//%s:1521/%s", DB_user, DB_pass, DB_host, DB_serviceName));
        return connection;

    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}

package source.voorbereidendeOpdrachten.Persistence.P1;

import java.sql.*;
public class CRUD {
    public static void main(String[] args) throws SQLException {
        String host = "localhost";
        String user = "BERRY";
        String pass = "123";
        String serviceName = "XEPDB1";
        Connection myConn = DriverManager.getConnection(String.format("jdbc:oracle:thin:%s/%s@//%s:1521/%s", user, pass, host, serviceName));
        Statement myStmt = myConn.createStatement();


        ResultSet cursussen_sql = myStmt.executeQuery("SELECT * FROM CURSUSSEN WHERE OMSCHRIJVING LIKE '%A%'");
        while(cursussen_sql.next()){
            System.out.println(cursussen_sql.getString("omschrijving"));
        }

        ResultSet AfdelingLocaties = myStmt.executeQuery("SELECT * FROM CURSUSSEN WHERE CODE = 'ERM'");
        while(AfdelingLocaties.next()){
            System.out.println(AfdelingLocaties.getString("omschrijving"));
        }

//        myStmt.executeQuery("INSERT INTO CURSUSSEN " +
//                "VALUES ('ADP', 'Adaptive Programming', 'ALG', 1)");

        PreparedStatement pstmt = myConn.prepareStatement("UPDATE CURSUSSEN SET LENGTE = ? WHERE CODE = ?");
        pstmt.setInt(1, 3);
        pstmt.setString(2, "PMT");
        pstmt.executeUpdate();

        pstmt = myConn.prepareStatement("SELECT * FROM CURSUSSEN WHERE CODE = ?");
        pstmt.setString(1, "JAV");
        pstmt.executeUpdate();

        pstmt = myConn.prepareStatement("DELETE FROM CURSUSSEN WHERE LENGTE = ?");
        pstmt.setInt(1, 3);
        pstmt.executeUpdate();

        myStmt.close();
        myConn.close();
    }
}

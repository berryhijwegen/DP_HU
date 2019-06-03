package source.inleverOpdrachten.Persistence.P2;
// Berry Hijwegen
// 1738601
// Persistentie inleveropdracht 1

import java.sql.*;
import java.util.ArrayList;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao {

    public ReizigerOracleDaoImpl(){
    }


    @Override
    public Reiziger save(Reiziger reiziger){
        OracleBaseDao obd = new OracleBaseDao();
        try (Connection conn = obd.getConnection()){
            ResultSet rs = conn.createStatement().executeQuery("SELECT MAX(reizigerid) FROM reiziger");
            rs.next();
            int nextID = rs.getInt(1) + 1;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO reiziger VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, nextID);
            pstmt.setString(2, reiziger.getVoorletters());
            pstmt.setString(3, reiziger.getTussenvoegsel());
            pstmt.setString(4, reiziger.getAchternaam());
            pstmt.setDate(5, reiziger.getGeboortedatum());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return reiziger;
    }

    @Override
    public Reiziger update(Reiziger reiziger){
        try (Connection conn = getConnection()){
            PreparedStatement pstmt = conn.prepareStatement("UPDATE reiziger " +
                                                            "SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, gebortedatum = ? " +
                                                            "WHERE reizigersid = ?");
            pstmt.setInt(5, reiziger.getReizigerID());
            pstmt.setString(1, reiziger.getVoorletters());
            pstmt.setString(2, reiziger.getTussenvoegsel());
            pstmt.setString(3, reiziger.getAchternaam());
            pstmt.setDate(4, reiziger.getGeboortedatum());

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return reiziger;
    }

    @Override
    public boolean delete(Reiziger reiziger){
        boolean result = false;
        try (Connection conn = getConnection()){
            Statement stmt = conn.createStatement();
            stmt.executeQuery("DELETE FROM REIZIGER WHERE reizigerid = " + reiziger.getReizigerID());
            result = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ArrayList<Reiziger> findAll(){
        ArrayList<Reiziger> tempReizigers = new ArrayList<Reiziger>();
        try (Connection conn = getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM REIZIGER");
            while(rs.next()){
                Reiziger reiziger = new Reiziger();
                reiziger.setReizigerID(rs.getInt(1));
                reiziger.setVoorletters(rs.getString(2));
                reiziger.setTussenvoegsel(rs.getString(3));
                reiziger.setAchternaam(rs.getString(4));
                reiziger.setGeboortedatum(rs.getDate(5));
                tempReizigers.add(reiziger);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tempReizigers;
    }

    @Override
    public ArrayList<Reiziger> findByGBdatum(String GBdatum) {
        ArrayList<Reiziger> tempReizigers = new ArrayList<Reiziger>();
        try (Connection conn = getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reiziger WHERE gebortedatum = TO_DATE('"+ GBdatum + "', 'YYYY-MM-DD')");
            while(rs.next()){
                Reiziger reiziger = new Reiziger();
                reiziger.setReizigerID(rs.getInt(1));
                reiziger.setVoorletters(rs.getString(2));
                reiziger.setTussenvoegsel(rs.getString(3));
                reiziger.setAchternaam(rs.getString(4));
                reiziger.setGeboortedatum(rs.getDate(5));
                tempReizigers.add(reiziger);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tempReizigers;
    }

    public Reiziger findByID(int id){
        Reiziger reiziger = new Reiziger();
        boolean reizigerFound = false;
        try (Connection conn = getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reiziger WHERE reizigerid = " + id);
            while(rs.next()){
                reizigerFound = true;
                reiziger.setReizigerID(rs.getInt(1));
                reiziger.setVoorletters(rs.getString(2));
                reiziger.setTussenvoegsel(rs.getString(3));
                reiziger.setAchternaam(rs.getString(4));
                reiziger.setGeboortedatum(rs.getDate(5));

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return reizigerFound ? reiziger : null;
    }

}

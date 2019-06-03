package source.inleverOpdrachten.Persistence.P2;

import java.sql.*;
import java.util.ArrayList;

public class OVChipkaartOracleDaoImpl extends OracleBaseDao implements OVChipkaartDAO  {
    public OVChipkaart save(OVChipkaart ovChipkaart){
        OracleBaseDao obd = new OracleBaseDao();
        try (Connection conn = obd.getConnection()){
            ResultSet rs = conn.createStatement().executeQuery("SELECT MAX(kaartnummer) FROM ov_chipkaart");
            rs.next();
            int nextID = rs.getInt(1) + 1;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ov_chipkaart VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, nextID);
            pstmt.setDate(2, ovChipkaart.getGeldigTot());
            pstmt.setInt(3, ovChipkaart.getKlasse());
            pstmt.setFloat(4, ovChipkaart.getSaldo());
            pstmt.setInt(5, ovChipkaart.getEigenaar().getReizigerID());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return ovChipkaart;
    }

    public OVChipkaart update(OVChipkaart ovChipkaart){
        try (Connection conn = getConnection()){
            PreparedStatement pstmt = conn.prepareStatement("UPDATE ov_chipkaart " +
                    "SET geldigtot = ?, klasse = ?, saldo = ?, reizigerid = ? " +
                    "WHERE kaartnummer = ?");
            pstmt.setInt(5, ovChipkaart.getKaartNummer());
            pstmt.setDate(1, ovChipkaart.getGeldigTot());
            pstmt.setInt(2, ovChipkaart.getKlasse());
            pstmt.setFloat(3, ovChipkaart.getSaldo());
            pstmt.setInt(4, ovChipkaart.getEigenaar().getReizigerID());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return ovChipkaart;
    }

    public boolean delete(OVChipkaart ovChipkaart){
        boolean result = false;
        try (Connection conn = getConnection()){
            Statement stmt = conn.createStatement();
            stmt.executeQuery("DELETE FROM ov_chipkaart WHERE kaartnummer = " + ovChipkaart.getKaartNummer());
            result = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<OVChipkaart> findByReiziger(Reiziger reiziger){
        ArrayList<OVChipkaart> tempOVchipkaarten = new ArrayList<OVChipkaart>();
        try (Connection conn = getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ov_chipkaart WHERE reizigerid = " + reiziger.getReizigerID());
            while(rs.next()){
                OVChipkaart ovChipkaart = new OVChipkaart();
                ovChipkaart.setKaartNummer(rs.getInt(1));
                ovChipkaart.setGeldigTot(rs.getDate(2));
                ovChipkaart.setKlasse(rs.getInt(3));
                ovChipkaart.setSaldo(rs.getFloat(4));
                ovChipkaart.setEigenaar(reiziger);
                tempOVchipkaarten.add(ovChipkaart);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tempOVchipkaarten;
    }

    public ArrayList<OVChipkaart> findAll() throws SQLException {
        ArrayList<OVChipkaart> tempOVchipkaarten = new ArrayList<OVChipkaart>();
        try (Connection conn = getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM REIZIGER");
            while(rs.next()){
                OVChipkaart ovChipkaart = new OVChipkaart();
                ovChipkaart.setKaartNummer(rs.getInt(1));
                ovChipkaart.setGeldigTot(rs.getDate(2));
                ovChipkaart.setKlasse(rs.getInt(3));
                ovChipkaart.setSaldo(rs.getFloat(4));
                ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
                ovChipkaart.setEigenaar(rodi.findByID(rs.getInt(5)));
                tempOVchipkaarten.add(ovChipkaart);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tempOVchipkaarten;
    }
}

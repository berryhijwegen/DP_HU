package source.inleverOpdrachten.Persistence.P3;

import java.sql.*;
import java.util.ArrayList;

public class OVChipkaartOracleDaoImpl extends OracleBaseDao implements OVChipkaartDAO {
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
            pstmt.setDouble(4, ovChipkaart.getSaldo());
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
            pstmt.setDouble(3, ovChipkaart.getSaldo());
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
                ovChipkaart.setSaldo(rs.getDouble(4));
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM ov_chipkaart");
            while(rs.next()){
                OVChipkaart ovChipkaart = new OVChipkaart();
                ovChipkaart.setKaartNummer(rs.getInt(1));
                ovChipkaart.setGeldigTot(rs.getDate(2));
                ovChipkaart.setKlasse(rs.getInt(3));
                ovChipkaart.setSaldo(rs.getDouble(4));
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

    public boolean addProductToOVChipkaart(Product product, OVChipkaart ovChipkaart) {
        boolean result = false;
        OracleBaseDao obd = new OracleBaseDao();
        try (Connection conn = obd.getConnection()){
            ResultSet rs = conn.createStatement().executeQuery("SELECT MAX(ovproductid) FROM ov_chipkaart_product");
            rs.next();
            int nextID = rs.getInt(1) + 1;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ov_chipkaart_product VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, nextID);
            pstmt.setInt(2, ovChipkaart.getKaartNummer());
            pstmt.setInt(3, product.getProductNummer());
            pstmt.setString(4, "actief");
            pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public OVChipkaart findByKaartnummer(int kaartNummer){
        OVChipkaart ovChipkaart = new OVChipkaart();
        boolean ovChipkaartFound = false;
        try (Connection conn = getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ov_chipkaart WHERE kaartnummer = " + kaartNummer);
            while(rs.next()){
                ovChipkaartFound = true;
                ovChipkaart.setKaartNummer(rs.getInt(1));
                ovChipkaart.setGeldigTot(rs.getDate(2));
                ovChipkaart.setKlasse(rs.getInt(3));
                ovChipkaart.setSaldo(rs.getDouble(4));
                ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
                ovChipkaart.setEigenaar(rodi.findByID(rs.getInt(5)));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ovChipkaartFound ? ovChipkaart : null;
    }
}

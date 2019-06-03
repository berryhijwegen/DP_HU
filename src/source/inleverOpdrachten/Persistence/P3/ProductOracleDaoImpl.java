package source.inleverOpdrachten.Persistence.P3;
// Berry Hijwegen
// 1738601
// Persistentie inleveropdracht 1

import java.sql.*;
import java.util.ArrayList;

public class ProductOracleDaoImpl extends OracleBaseDao implements ProductDAO {

    @Override
    public Product save(Product product){
        OracleBaseDao obd = new OracleBaseDao();
        try (Connection conn = obd.getConnection()){
            ResultSet rs = conn.createStatement().executeQuery("SELECT MAX(productnummer) FROM product");
            rs.next();
            int nextID = rs.getInt(1) + 1;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO product VALUES (?, ?, ?, ?)");
            pstmt.setInt(1, nextID);
            pstmt.setString(2, product.getProductNaam());
            pstmt.setString(3, product.getBeschrijving());
            pstmt.setDouble(4, product.getPrijs());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public Product update(Product product){
        try (Connection conn = getConnection()){
            PreparedStatement pstmt = conn.prepareStatement("UPDATE reiziger " +
                    "SET productnaam = ?, beschrijving = ?, prijs = ? " +
                    "WHERE reizigersid = ?");
            pstmt.setInt(4, product.getProductNummer());
            pstmt.setString(1, product.getProductNaam());
            pstmt.setString(2, product.getBeschrijving());
            pstmt.setDouble(3, product.getPrijs());

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public ArrayList<Product> findAll() throws SQLException {
        ArrayList<Product> tempProducten = new ArrayList<Product>();
        try (Connection conn = getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product");
            while(rs.next()){
                Product product = new Product();
                product.setProductNummer(rs.getInt(1));
                product.setProductNaam(rs.getString(2));
                product.setBeschrijving(rs.getString(3));
                product.setPrijs(rs.getDouble(4));
                product.setOvChipkaart(getOVChipkaartOfProduct(product));
                tempProducten.add(product);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tempProducten;
    }

    @Override
    public boolean delete(Product product){
        boolean result = false;
        try (Connection conn = getConnection()){
            Statement stmt = conn.createStatement();
            stmt.executeQuery("DELETE FROM product WHERE productnummer = " + product.getProductNummer());
            result = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Product findByProductNummer(int productNummer){
        Product product = new Product();
        boolean productFound = false;
        try (Connection conn = getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE productnummer = " + productNummer);
            while(rs.next()){
                productFound = true;
                product.setProductNummer(rs.getInt(1));
                product.setProductNaam(rs.getString(2));
                product.setBeschrijving(rs.getString(3));
                product.setPrijs(rs.getDouble(4));
                product.setOvChipkaart(getOVChipkaartOfProduct(product));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return productFound ? product : null;
    }

    @Override
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
            System.out.println(ovChipkaart.getKaartNummer());
            System.out.println(product.getProductNummer());
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

    public OVChipkaart getOVChipkaartOfProduct(Product product){
        OVChipkaart ovChipkaart = new OVChipkaart();
        boolean ovChipkaartFound = false;
        try (Connection conn = getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT kaartnummer, productnummer FROM ov_chipkaart_product WHERE productnummer = " + product.getProductNummer());
            while(rs.next()){
                ovChipkaartFound = true;
                OVChipkaartOracleDaoImpl ovdao = new OVChipkaartOracleDaoImpl();
                ovChipkaart = ovdao.findByKaartnummer(rs.getInt(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ovChipkaartFound ? ovChipkaart : null;
    }
}

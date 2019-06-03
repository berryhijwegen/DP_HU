package source.inleverOpdrachten.Persistence.P3;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws ParseException, SQLException {
        ReizigerOracleDaoImpl rdao = new ReizigerOracleDaoImpl();
        OVChipkaartOracleDaoImpl ovdao = new OVChipkaartOracleDaoImpl();
        Reiziger henk = new Reiziger();
        henk.setVoorletters("H");
        henk.setTussenvoegsel("");
        henk.setAchternaam("Jensen");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = formatter.parse("1999-12-12");
        henk.setGeboortedatum(new java.sql.Date(utilDate.getTime()));

        // Save Henk
        rdao.save(henk);

        // Haal Henk op uit de database met zijn ID
        henk = rdao.findByGBdatum("1999-12-12").get(0);

        OVChipkaart ovchipkaartVanHenk = new OVChipkaart();
        ovchipkaartVanHenk.setGeldigTot(new java.sql.Date(formatter.parse("2022-01-01").getTime()));
        ovchipkaartVanHenk.setKlasse(1);
        ovchipkaartVanHenk.setSaldo(99.12);
        ovchipkaartVanHenk.setEigenaar(henk);

        // Save OV Chipkaart van Henk
        ovdao.save(ovchipkaartVanHenk);
        ovchipkaartVanHenk = ovdao.findByReiziger(henk).get(0);
        Product productVanHenk = new Product();
        productVanHenk.setProductNaam("Studentenreisproduct");
        productVanHenk.setBeschrijving("Test");
        productVanHenk.setPrijs(33.45);
        productVanHenk.setOvChipkaart(ovchipkaartVanHenk);
        ProductOracleDaoImpl proddao = new ProductOracleDaoImpl();
        proddao.save(productVanHenk);

        // get with Nummmer
        productVanHenk = proddao.findAll().get(proddao.findAll().size() -1);
        proddao.addProductToOVChipkaart(productVanHenk, ovchipkaartVanHenk);

//        ovdao.delete(ovdao.findAll().get(ovdao.findAll().size()-1));
//        rdao.delete(rdao.findAll().get(rdao.findAll().size()-1));
    }
}

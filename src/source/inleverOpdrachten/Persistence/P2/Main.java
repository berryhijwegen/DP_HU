package source.inleverOpdrachten.Persistence.P2;
// Berry Hijwegen
// 1738601
// Persistentie inleveropdracht 1

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ReizigerOracleDaoImpl DAO = new ReizigerOracleDaoImpl();

        // Find all reizigers
        List<Reiziger> alleReizigers = DAO.findAll();
        for (Reiziger reiziger : alleReizigers) {
            System.out.println(reiziger);
        }
        System.out.println();

        Reiziger reizigerHenk = new Reiziger();
        reizigerHenk.setVoorletters("H");
        reizigerHenk.setAchternaam("Harmsen");
        reizigerHenk.setGeboortedatum(Date.valueOf("2001-11-11"));

        // Create reiziger
        System.out.println("Saved user: " + DAO.save(reizigerHenk));

        System.out.println();

        alleReizigers = DAO.findAll();
        for (Reiziger reiziger : alleReizigers) {
            System.out.println(reiziger);
        }

        System.out.println();

        // Delete reiziger
        System.out.println("User deleted: " + DAO.delete(DAO.findByGBdatum("2001-11-11").get(0)));
        System.out.println();

        alleReizigers = DAO.findAll();
        for (Reiziger reiziger : alleReizigers) {
            System.out.println(reiziger);
        }

        // Create ov-chipkaart
        Reiziger aReiziger = DAO.findByID(2);
        OVChipkaart ovChipkaart = new OVChipkaart();
        ovChipkaart.setKaartNummer(0);
        ovChipkaart.setGeldigTot(new Date(System.currentTimeMillis()));
        ovChipkaart.setSaldo((float) 25.01);
        ovChipkaart.setKlasse(1);
        ovChipkaart.setEigenaar(aReiziger);

        OVChipkaartOracleDaoImpl oodi = new OVChipkaartOracleDaoImpl();
        oodi.save(ovChipkaart);
    }
}

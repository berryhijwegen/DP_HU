package source.inleverOpdrachten.Persistence.P1;


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

        // Create reiziger
        System.out.println("Saved user: " + DAO.save(new Reiziger("Klaas", Date.valueOf("1940-07-31"))));

        System.out.println();

        // Update reiziger
        Reiziger reizigerJan = DAO.findAll().get(0);
        reizigerJan.setNaam("Harm");
        DAO.update(reizigerJan);

        alleReizigers = DAO.findAll();
        for (Reiziger reiziger : alleReizigers) {
            System.out.println(reiziger);
        }

        System.out.println();

        // Delete reiziger
        System.out.println("User deleted: " + DAO.delete(DAO.findByGBdatum("1963-07-31").get(0)));

        System.out.println();

        alleReizigers = DAO.findAll();
        for (Reiziger reiziger : alleReizigers) {
            System.out.println(reiziger);
        }
    }
}

package source.inleverOpdrachten.Persistence.P1;
// Berry Hijwegen
// 1738601
// Persistentie inleveropdracht 1

import java.util.List;

public interface ReizigerDao {
    List<Reiziger> findAll();

    List<Reiziger> findByGBdatum(String GBdatum) throws Exception;

    Reiziger save(Reiziger reiziger);

    Reiziger update(Reiziger reiziger);

    boolean delete(Reiziger reiziger);

    void closeConnection();
}

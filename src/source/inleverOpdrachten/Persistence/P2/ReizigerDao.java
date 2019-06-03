package source.inleverOpdrachten.Persistence.P2;
// Berry Hijwegen
// 1738601
// Persistentie inleveropdracht 1

import java.util.List;

public interface ReizigerDao {
    Reiziger save(Reiziger reiziger);

    List<Reiziger> findAll();

    List<Reiziger> findByGBdatum(String GBdatum) throws Exception;

    Reiziger update(Reiziger reiziger);

    boolean delete(Reiziger reiziger);

    Reiziger findByID(int id);
}

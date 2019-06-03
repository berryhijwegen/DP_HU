package source.inleverOpdrachten.Persistence.P3;
// Berry Hijwegen
// 1738601
// Persistentie inleveropdracht 1

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {
    OVChipkaart save(OVChipkaart ovChipkaart);

    List<OVChipkaart> findAll() throws SQLException;

    List<OVChipkaart> findByReiziger(Reiziger reiziger) throws Exception;

    OVChipkaart update(OVChipkaart ovChipkaart);

    boolean delete(OVChipkaart ovChipkaart);

    boolean addProductToOVChipkaart(Product product, OVChipkaart ovChipkaart);
}

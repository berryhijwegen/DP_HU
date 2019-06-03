package source.inleverOpdrachten.Persistence.P3;
// Berry Hijwegen
// 1738601
// Persistentie inleveropdracht 1

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    Product save(Product product);

    List<Product> findAll() throws SQLException;

    Product update(Product product);

    boolean delete(Product product);

    Product findByProductNummer(int productNummer);

    boolean addProductToOVChipkaart(Product product, OVChipkaart ovChipkaart);

    OVChipkaart getOVChipkaartOfProduct(Product product);
}

package source.inleverOpdrachten.Persistence.P1;

import java.sql.Date;
import java.util.ArrayList;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao {
    private ArrayList<Reiziger> reizigers;

    public ReizigerOracleDaoImpl(){
        reizigers = new ArrayList<>();
        reizigers.add(new Reiziger("Jan", Date.valueOf("1997-03-31")));
        reizigers.add(new Reiziger("Kees", Date.valueOf("1992-03-06")));
        reizigers.add(new Reiziger("Piet", Date.valueOf("1963-07-31")));
    }

    @Override
    public ArrayList<Reiziger> findAll(){
        return reizigers;
    }

    @Override
    public ArrayList<Reiziger> findByGBdatum(String GBdatum) throws Exception {
        java.sql.Date GBdatumDate = java.sql.Date.valueOf(GBdatum);
        ArrayList<Reiziger> reizigersToReturn = new ArrayList<>();
        for(Reiziger reiziger : reizigers){
            if(GBdatumDate.equals(reiziger.getGbdatum())){
                reizigersToReturn.add(reiziger);
            }
        }
        return reizigersToReturn;
    }

    @Override
    public Reiziger save(Reiziger reiziger){
        reizigers.add(reiziger);
        return reiziger;
    }

    @Override
    public Reiziger update(Reiziger reiziger){
        for (Reiziger r : reizigers) {
            if(r.equals(reiziger)){
                reizigers.set(reizigers.indexOf(r), reiziger);
            }
        }
        return reiziger;
    }

    @Override
    public boolean delete(Reiziger reiziger){
        boolean result = false;
        if(reizigers.contains(reiziger)){
            reizigers.remove(reiziger);
            result = true;
        }
        return result;
    }
}

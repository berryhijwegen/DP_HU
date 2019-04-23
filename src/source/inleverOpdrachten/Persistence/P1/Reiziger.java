package source.inleverOpdrachten.Persistence.P1;

import java.sql.Date;

public class Reiziger {
    private String naam;
    private Date gbdatum;

    public Reiziger(String nm, Date gbdtm){
        naam = nm;
        gbdatum = gbdtm;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String nm){
        naam = nm;
    }

    public Date getGbdatum(){
        return gbdatum;
    }

    public void setGbdatum(Date gbdtm){
        gbdatum = gbdtm;
    }

    public String toString(){
        return String.format("Reiziger %s heeft geboortedatum %s.", naam, gbdatum);
    }
}

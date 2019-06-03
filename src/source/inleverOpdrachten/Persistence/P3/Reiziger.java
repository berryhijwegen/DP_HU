package source.inleverOpdrachten.Persistence.P3;
// Berry Hijwegen
// 1738601
// Persistentie inleveropdracht 1

import java.sql.Date;
import java.util.ArrayList;

public class Reiziger {
    private int reizigerID;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private ArrayList<OVChipkaart> ovchipkaarten;

    public Reiziger(){
        reizigerID = 0;
        voorletters = tussenvoegsel = achternaam = null;
        geboortedatum = null;
        ovchipkaarten = new ArrayList<OVChipkaart>();
    }

    public int getReizigerID() {
        return reizigerID;
    }

    public void setReizigerID(int reizigerID) {
        this.reizigerID = reizigerID;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public ArrayList<OVChipkaart> getOvchipkaarten(){
        return ovchipkaarten;
    }

    public boolean voegOvcipkaartToe(OVChipkaart ovChipkaart){
        return false;
    }

    public boolean verwijderOvcipkaart(OVChipkaart ovChipkaart){
        return false;
    }

    public String getNaam(){
        return voorletters + tussenvoegsel + achternaam;
    }

    public String toString(){
        return String.format("%s. %s, %s", voorletters, achternaam, geboortedatum);
    }
}
package source.inleverOpdrachten.Persistence.P3;

import java.sql.Date;
import java.util.ArrayList;

public class OVChipkaart {
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private Double saldo;
    private Reiziger eigenaar;
    private ArrayList<Product> alleproducten = new ArrayList<Product>();

    public ArrayList<Product> getAlleproducten() {
        return alleproducten;
    }

    public void setAlleproducten(ArrayList<Product> alleproducten) {
        this.alleproducten = alleproducten;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(Reiziger eigenaar) {
        this.eigenaar = eigenaar;
    }

    public String toString(){
        return null;
    }
}

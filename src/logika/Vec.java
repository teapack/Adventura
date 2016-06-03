package logika;

import java.util.Objects;

/**
 * Třída reprezentující jednotlivé věci, které se ve hře vyskytují.
 * 
 * Z této třídy dědí třídy Brneni, Klic, Lektvar, OdemykatelnaVec, Zbran.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160504
 */
public class Vec {
    private String nazev;
    private double hmotnost;
    private boolean prenositelnost;
    private boolean viditelnost;
    private int cena;
    
    /**
     * Konstruktor, pomocí kterého budu v programu vytvářet instance věcí.
     * 
     * @param nazev název věci
     * @param hmotnost hmotnost věci
     * @param prenositelnost přenositelnost věci (ano = true, ne = false)
     * @param viditelnost jestli je věc viditelná
     */
    public Vec(String nazev, double hmotnost, boolean prenositelnost,
            boolean viditelnost) {
        this.nazev = nazev;
        this.hmotnost = hmotnost;
        this.prenositelnost = prenositelnost;
        this.viditelnost = viditelnost;
    }
    
    /**
     * Getter na název věci.
     * 
     * @return název věci
     */
    public String getNazev() {
        return nazev;
    }
    
    /**
     * Getter na hmotnost věci.
     * 
     * @return hmotnost věci
     */
    public double getHmotnost() {
        return hmotnost;
    }

    /**
     * Getter na přenositelnost věci.
     * 
     * @return true, když je věc přenositelná; jinak false
     */
    public boolean isPrenositelnost() {
        return prenositelnost;
    }
    
    /**
     * Setter na viditelnost věci.
     * 
     * @param viditelnost true, když má věc být viditelná, jinak false
     */
    public void setViditelnost (boolean viditelnost) {
        this.viditelnost = viditelnost;
    }
    
    /**
     * Getter na viditelnost věci.
     * 
     * @return true, když je věc viditelná; jinak false
     */
    public boolean isViditelnost() {
        return viditelnost;
    }
    
    /**
     * Setter na nastavení ceny věci.
     * 
     * @param cena nastavovaná cena
     */
    public void setCena(int cena) {
        this.cena = cena;
    }
    
    /**
     * Getter na vrácení ceny věci.
     * 
     * @return cena věci
     */
    public int getCena() {
        return cena;
    }
    
    /**
     * Metoda která řeší vypisování věci.
     * 
     * @return zpráva obsahující info o věci
     */
    @Override
    public String toString() {
        return "Název: " + nazev
                + ", hmotnost: " + hmotnost + "kg\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nazev);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vec other = (Vec) obj;
        if (!Objects.equals(this.nazev, other.nazev)) {
            return false;
        }
        return true;
    }
}
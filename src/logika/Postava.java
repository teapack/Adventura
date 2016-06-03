package logika;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Objects;

/**
 * Třída reprezentující jednotlivé postavy, které se ve hře vyskytují.
 * 
 * Tato třída je součástí jednoducé textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160504
 */
public class Postava {
    private String jmeno;
    private double zdravi;
    private double sila;
    private double brneni;
    private Inventar inventar;
    private double MAX_ZDRAVI;
    
    /**
     * Konstruktor, pomocí kterého se budou v programu vytvářet instance postav.
     * 
     * @param jmeno jméno postavy
     * @param zdravi zdraví postavy
     * @param sila síla postavy
     * @param brneni hodnota brnění postavy
     */
    public Postava(String jmeno, double zdravi, double sila, double brneni) {
        this.jmeno = jmeno;
        this.zdravi = zdravi;
        this.sila = sila;
        this.brneni = brneni;
        inventar = new Inventar();
    }
    
    /**
     * Getter na jméno postavy.
     * 
     * @return jméno postavy
     */
    public String getJmeno() {
        return jmeno;
    }
    
    /**
     * Nastaví zdraví postavy.
     * 
     * @param zdravi nastavované zdraví
     */
    public void setZdravi(double zdravi) {
        this.zdravi = zdravi;
    }
    
    /**
     * Getter na zdraví postavy.
     * 
     * @return zdraví postavy
     */
    public double getZdravi() {
        return zdravi;
    }
    
    /**
     * Nastaví sílu postavy.
     * 
     * @param sila nastavovaná síla
     */
    public void setSila(double sila) {
        this.sila = sila;
    }
    
    /**
     * Getter na sílu postavy.
     * 
     * @return síla postavy
     */
    public double getSila() {
        return sila;
    }
    
    /**
     * Nastaví brnění postavy.
     * 
     * @param brneni nastavované brnění.
     */
    public void setBrneni(double brneni) {
        this.brneni = brneni;
    }
    
    /**
     * Getter na hodnotu brnění postavy.
     * 
     * @return brnění postavy
     */
    public double getBrneni() {
        return brneni;
    }
    
    /**
     * Metoda, která nastaví inventář postavy.
     * 
     * @param inventar nastavovaný inventář postavy
     */
    public void setInventar(Inventar inventar) {
        this.inventar = inventar;
    }
    
    /**
     * Metoda, která vrací odkaz na inventář postavy.
     * 
     * @return inventar inventář postavy
     */
    public Inventar getInventar() {
        return inventar;
    }
    
    /**
     * Metoda, která "vysype" obsah inventáře postavy na zem, když je postava
     * zabita.
     * 
     * @return inventar inventář zabíjené postavy
     */
    public Collection<Vec> dropInventar() {
        return inventar.getSeznamVeci();
    }
    
    /**
     * Setter na maximální zdraví postavy.
     * 
     * @param MAX_ZDRAVI nastavované maximální zdraví postavy
     */
    public void setMAX_ZDRAVI(double MAX_ZDRAVI) {
        this.MAX_ZDRAVI = MAX_ZDRAVI;
    }
    
    /**
     * Getter na maximální zdraví postavy.
     * 
     * @return maximální zdraví postavy
     */
    public double getMAX_ZDRAVI() {
        return MAX_ZDRAVI;
    }
    
    /**
     * Metoda pro výpis postavy a jejích atributů.
     * 
     * @return info o postavě a jejích atributech
     */
    @Override
    public String toString() {
        // Formátuji zdraví, abych se zbavil zaokrouhlovací chyby, která vzniká
        // v důsledku toho, jak jsou čísla v počítači reprezentována.
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return "Postava: " + jmeno
                + ", zdraví: " + df.format(zdravi)
                + ", síla: " + (sila-1) + " - " + (sila+1)
                + ", brnění: " + brneni;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.jmeno);
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
        final Postava other = (Postava) obj;
        if (!Objects.equals(this.jmeno, other.jmeno)) {
            return false;
        }
        return true;
    }
}
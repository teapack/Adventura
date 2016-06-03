package logika;

/**
 * Třída Brneni reprezentuje brnění, která se ve hře vyskytují.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160514
 */
public class Brneni extends Vec {
    private double brneni;
    
    /**
     * Konstruktor, pomocí kterého budu vytvářet instance brnění.
     * 
     * @param nazev název brnění
     * @param hmotnost hmotnost brnění
     * @param prenositelnost určuje, jestli je brnění přenositelné (true), nebo
     *                       ne (false)
     * @param viditelnost určuje, jestli je brnění v daném prostoru viditelné
     *                    (true), nebo ne (false)
     * @param brneni hodnota brnění
     */
    public Brneni(String nazev, double hmotnost, boolean prenositelnost,
            boolean viditelnost, double brneni) {
        super(nazev, hmotnost, prenositelnost, viditelnost);
        this.brneni = brneni;
    }
    
    /**
     * Setter na hodnotu brnění brnění.
     * 
     * @param brneni hodnota brnění, která se brnění nastaví
     */
    public void setBrneni(double brneni) {
        this.brneni = brneni;
    }
    
    /**
     * Getter na hodnotu brnění, kterou brnění hráči přidá.
     * 
     * @return hodnota brnění, která se hráči přičte, má-li brnění v inventáři
     */
    public double getBrneni() {
        return brneni;
    }
    
    /**
     * Metoda která řeší vypisování věci.
     * 
     * @return zpráva obsahující info o věci
     */
    @Override
    public String toString() {
        return "Název: " + super.getNazev()
                + ", brnění: +" + brneni*100
                + "%, hmotnost: " + super.getHmotnost() + "kg\n";
    }
}
package logika;

/**
 * Třída Lektvar představuje lektvary, které se ve hře vyskytují.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160515
 */
public class Lektvar extends Vec {
    private double zdravi;
    
    /**
     * Konstruktor, pomocí kterého budu vytvářet instance lektvarů.
     * 
     * @param nazev název lektvaru
     * @param hmotnost hmotnost lektvaru
     * @param prenositelnost určuje, jestli je lektvar přenositelný (true), nebo
     *                       ne (false)
     * @param viditelnost určuje, jestli je lektvar v daném prostoru viditelný
     *                    (true), nebo ne (false)
     * @param zdravi zdraví, které lektvar po použití přidá (případně ubere,
     *               kdyby to byl nějaký jed)
     */
    public Lektvar(String nazev, double hmotnost, boolean prenositelnost,
            boolean viditelnost, double zdravi) {
        super(nazev, hmotnost, prenositelnost, viditelnost);
        this.zdravi = zdravi;
    }
    
    /**
     * Setter na zdraví, které lektvar po použití dodá.
     * 
     * @param zdravi nastavované zdraví
     */
    public void setZdravi(double zdravi) {
        this.zdravi = zdravi;
    }
    
    /**
     * Getter na zdraví, které lektvar po použití dodá.
     * 
     * @return hodnota zdraví, které lektvar po použití dodá
     */
    public double getZdravi() {
        return zdravi;
    }
    
    /**
     * Metoda která řeší vypisování věci.
     * 
     * @return zpráva obsahující info o věci
     */
    @Override
    public String toString() {
        return "Název: " + super.getNazev()
                + ", zdraví: " + zdravi
                + ", hmotnost: " + super.getHmotnost() + "kg\n";
    }
}
package logika;

/**
 * Třída Zbran reprezentuje jednotlivé zbraně, které se ve hře vyskytují.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160514
 */
public class Zbran extends Vec {
    private double sila;
    
    /**
     * Konstruktor, pomocí kterého budu vytvářet instance třídy Zbran.
     * 
     * @param nazev název zbraně
     * @param hmotnost hmotnost zbraně
     * @param prenositelnost jestli je zbraň přenositelná (ano = true,
     *                       ne = false)
     * @param viditelnost jestli je zbraň viditelná
     * @param sila síla zbraně
     */
    public Zbran(String nazev, double hmotnost, boolean prenositelnost,
            boolean viditelnost, double sila) {
        super(nazev, hmotnost, prenositelnost, viditelnost);
        this.sila = sila;
    }
    
    /**
     * Setter na sílu, kterou zbraň hráči přidá.
     * 
     * @param sila nastavovaná síla
     */
    public void setSila(double sila) {
        this.sila = sila;
    }
    
    /**
     * Getter na sílu, kterou zbraň hráči přidá.
     * 
     * @return síla, která se hráči přičte, má-li zbraň ve svém inventáři
     */
    public double getSila() {
        return sila;
    }
    
    /**
     * Metoda která řeší vypisování věci.
     * 
     * @return zpráva obsahující info o zbrani
     */
    @Override
    public String toString() {
        return "Název: " + super.getNazev()
                + ", síla: +" + sila
                + ", hmotnost: " + super.getHmotnost() + "kg\n";
    }
}
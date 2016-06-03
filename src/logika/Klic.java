package logika;

/**
 * Třída Klic reprezentuje věci, které jsou zároveň klíče.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160514
 */
public class Klic extends Vec {
    private int id;
    
    /**
     * Konstruktor, pomocí kterého budu vytvářet instance klíčů.
     * 
     * @param nazev název klíče
     * @param hmotnost hmotnost klíče
     * @param prenositelnost určuje, jestli je klíč přenositelný (true), nebo
     *                       ne (false)
     * @param viditelnost určuje, jestli je klíč v daném prostoru viditelný
     *                    (true), nebo ne (false)
     * @param id id klíče (dá se přirovnat ke "vzoru" klíče ze skutečného světa)
     */
    public Klic(String nazev, double hmotnost,
            boolean prenositelnost, boolean viditelnost, int id) {
        super(nazev, hmotnost, prenositelnost, viditelnost);
        this.id = id;
    }
    
    /**
     * Getter na zjištění id klíče.
     * 
     * @return id klíče
     */
    public int getId() {
        return id;
    }
}
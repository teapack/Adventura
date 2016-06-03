package logika;

/**
 * Třída OdemykatelnaVec reprezentuje věci, které jsou zamykatelné (a tedy i
 * odemykatelné).
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160514
 */
public class OdemykatelnaVec extends Vec {
    private int id;
    
    /**
     * Konstruktor, pomocí kterého budu vytvářet instance odemykatelných věcí.
     * 
     * @param nazev název odemykatelné věci
     * @param hmotnost hmotnost odemykatelné věci
     * @param prenositelnost určuje, zda je věc přenositelná (true), nebo ne
     *                       (false)
     * @param viditelnost určuje, zda je věc v daném prostoru viditelná (true),
     *                    nebo ne (false)
     * @param id id odemykatelné věci (dá se přirovnat ke "vzoru" zámku ze
     *           skutečného světa)
     */
    public OdemykatelnaVec(String nazev, double hmotnost,
            boolean prenositelnost, boolean viditelnost, int id) {
        super(nazev, hmotnost, prenositelnost, viditelnost);
        this.id = id;
    }
    
    /**
     * Getter na zjištění id odemykatelné věci.
     * 
     * @return id odemykatelné věci
     */
    public int getId() {
        return id;
    }
}
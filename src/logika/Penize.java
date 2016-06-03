package logika;

/**
 * Třída Penize reprezentuje peníze, které se ve hře vyskytují.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160521
 */
public class Penize extends Vec {
    private int hodnota;
    
    /**
     * Konstruktor, pomocí kterého budu ve hře vytvářet instance třídy Penize.
     * 
     * @param nazev název (název bankovky, počtu zlaťáků, ...)
     * @param hmotnost hmotnost
     * @param prenositelnost jestli jsou peníze přenositelné (true), nebo ne
     *                       (false)
     * @param viditelnost jestli jsou peníze v daném prostoru viditelné (true),
     *                    nebo ne (false)
     */
    public Penize(String nazev, double hmotnost, boolean prenositelnost,
            boolean viditelnost, int hodnota) {
        super(nazev, hmotnost, prenositelnost, viditelnost);
        this.hodnota = hodnota;
    }
    
    /**
     * Getter na hodnotu peněz.
     * 
     * @return hodnota peněz
     */
    public int getHodnota() {
        return hodnota;
    }
}
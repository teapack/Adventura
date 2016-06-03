package logika;

/**
 * Třída UkolNakup reprezentuje úkoly, které se ve hře vyskytují, a jejichž
 * podstata tkví v nakoupení nějaké věci.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160522
 */
public class UkolNakup extends Ukol {
    /**
     * Konstruktor, pomocí kterého budu v programu vytvářet instance třídy
     * UkolNakup.
     * 
     * @param zadavatel postava, která úkol hráči zadala
     * @param predmetUkolu čeho/koho se úkol týká (například "medved", nebo
     *                     "kovoveBrneni"
     * @param pozadovaneVeci požadované věci
     * @param odmena true, dostane-li hráč za splnění úkolu nějakou odměnu;
     *               jinak false
     */
    public UkolNakup(MluviciPostava zadavatel, Object predmetUkolu,
            Object pozadovaneVeci, boolean odmena) {
        super(zadavatel, predmetUkolu, pozadovaneVeci, odmena);
    }
}
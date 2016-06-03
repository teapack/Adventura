package logika;

/**
 * Třída Ukol reprezentuje úkoly, které hráče ve hře potkají.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160516
 */
public class Ukol {
    private MluviciPostava zadavatel;
    private Object predmetUkolu;
    private Object pozadovaneVeci;
    private boolean odmena;

    /**
     * Konstruktor třídy Ukol.
     * 
     * @param zadavatel postava, která úkol hráči zadala
     * @param predmetUkolu čeho/koho se úkol týká (například "medved", nebo
     *                     "kovoveBrneni"
     * @param pozadovaneVeci požadované věci
     * @param odmena true, dostane-li hráč za splnění úkolu nějakou odměnu;
     *               jinak false
     */
    public Ukol(MluviciPostava zadavatel, Object predmetUkolu,
            Object pozadovaneVeci, boolean odmena) {
        this.zadavatel = zadavatel;
        this.predmetUkolu = predmetUkolu;
        this.pozadovaneVeci = pozadovaneVeci;
        this.odmena = odmena;
    }
    
    /**
     * Getter na zadavatele úkolu.
     * 
     * @return odkaz na zadavatele úkolu
     */
    public MluviciPostava getZadavatel() {
        return zadavatel;
    }
    
    /**
     * Getter na předmět úkolu.
     * 
     * @return předmět úkolu
     */
    public Object getPredmetUkolu() {
        return predmetUkolu;
    }
    
    /**
     * Getter na požadované věci.
     * 
     * @return požadované věci
     */
    public Object getPozadovaneVeci() {
        return pozadovaneVeci;
    }
    
    /**
     * Getter na boolean odmena.
     * 
     * @return true, dostane-li hráč za daný úkol nějakou odměnu, jinak false
     */
    public boolean isOdmena() {
        return odmena;
    }
}
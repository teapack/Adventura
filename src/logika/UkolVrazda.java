package logika;

/**
 * Třída UkolVrazda reprezentuje úkoly, které se ve hře vyskytují, a jejichž
 * podstata tkví v zabití nějaké postavy.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160522
 */

public class UkolVrazda extends Ukol {
    private Vec vybava;
    private Vec moznaVybava;
    private Vec odmena;
    
    /**
     * Konstruktor, pomocí kterého budu v programu vytvářet instance třídy
     * UkolVrazda.
     * 
     * @param zadavatel postava, která úkol hráči zadala
     * @param predmetUkolu čeho/koho se úkol týká (například "medved", nebo
     *                     "kovoveBrneni"
     * @param pozadovaneVeci požadované věci
     * @param odmena true, dostane-li hráč za splnění úkolu nějakou odměnu;
     *               jinak false
     * @param vybava výbava, kterou hráč při přijetí mise dostane
     * @param moznaVybava výbava, kterou hráč při přijetí mise dostane, má-li u
     *                    sebe požadované věci
     */
    public UkolVrazda(MluviciPostava zadavatel, Object predmetUkolu,
            Object pozadovaneVeci, boolean odmena, Vec vybava,
            Vec moznaVybava) {
        super(zadavatel, predmetUkolu, pozadovaneVeci, odmena);
        this.vybava = vybava;
        this.moznaVybava = moznaVybava;
    }
    
    /**
     * Getter na výbavu, kterou hráč při přijetí úkolu dostane.
     * 
     * @return odkaz na výbavu, kterou hráč při přijetí úkolu dostane
     */
    public Vec getVybava() {
        return vybava;
    }
    
    /**
     * Getter na výbavu, kterou hráč při přijetí úkolu dostane, má-li u sebe
     * požadované věci.
     * 
     * @return odkaz na možnou výbavu
     */
    public Vec getMoznaVybava() {
        return moznaVybava;
    }
    
    /**
     * Setter na odměnu, kterou hráč po splnění přijatého úkolu dostane.
     * 
     * @param odmena odměna, kterou hráč po splnění přijatého úkolu dostane
     */
    public void setOdmena(Vec odmena) {
        this.odmena = odmena;
    }
    
    /**
     * Getter na odměnu, kterou hráč po splnění přijatého úkolu dostane.
     * 
     * @return odkaz na odměnu, kterou hráč po splnění přijatého úkolu dostane
     */
    public Vec getOdmena() {
        return odmena;
    }
}
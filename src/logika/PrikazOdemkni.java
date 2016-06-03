package logika;

import java.util.Collection;
import java.util.HashSet;

/**
 * Třída PrikazOdemkni implementuje pro hru příkaz "odemkni".
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160508
 */
public class PrikazOdemkni implements IPrikaz {
    private static final String NAZEV = "odemkni";
    private HerniPlan herniPlan;
    private Inventar inventar;
    private Collection<Klic> seznamKlicu;
    
    /**
     * Konstruktor třídy.
     * 
     * @param herniPlan odtud beru informace o stavu herního plánu
     */
    public PrikazOdemkni(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
        this.inventar = herniPlan.getHrac().getInventar();
        seznamKlicu = new HashSet<>();
    }
    
    /**
     * Provádí příkaz "odemkni".
     * 
     * @param parametry co se má použít
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám odemknout?";
        }
        Prostor aktualni = herniPlan.getAktualniProstor();
        String nazevOdemykane = parametry[0];
        Vec odemykana = aktualni.odeberVec(nazevOdemykane);
        if (odemykana == null) {
            return "To tu není!";
        }
        if (odemykana instanceof OdemykatelnaVec) {
            seznamKlicu = inventar.getSeznamKlicu();
        }
        if (seznamKlicu.isEmpty()) { // Hráč nemá v inventáři žádné klíče.
            aktualni.vlozVec(odemykana);
            return "Nemáš u sebe žádný klíč!";
        }
        for (Klic v : seznamKlicu) {
            OdemykatelnaVec odemykanaVec = (OdemykatelnaVec) odemykana;
            if (v.getId() == odemykanaVec.getId()) {
                // Hráč má u sebe správný klíč.
                aktualni.zviditelniNeviditelneVeci();
                return "Odemknuto.\n" + aktualni.popisVeci();
                //return "Odemknuto.\n" + aktualni.dlouhyPopis();
            }
        }
        // Hráč u sebe nemá potřebný klíč, kterým by se dala věc odemknout.
        aktualni.vlozVec(odemykana);
        seznamKlicu.clear();
        return "Nemáš u sebe správný klíč.";
    }
    
    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     * 
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
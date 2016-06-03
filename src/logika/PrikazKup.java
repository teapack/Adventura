package logika;

import java.util.Collection;

/**
 * Třída PrikazKup implementuje pro hru příkaz "kup".
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160516
 */
public class PrikazKup implements IPrikaz {
    private static final String NAZEV = "kup";
    private HerniPlan herniPlan;
    private Postava hrac;
    private Inventar inventar;
    
    /**
    * Konstruktor třídy.
    * 
    * @param herniPlan herní plán
    */
    public PrikazKup(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
        hrac = herniPlan.getHrac();
        inventar = hrac.getInventar();
    }
    
    /**
     * Provádí příkaz "kup".
     * 
     * @param parametry co se má koupit
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        // Nejsou zadané žádné parametry.
        if (parametry.length == 0) {
            return "Co mám koupit?";
        }
        String nazevKupovaneho = parametry[0];
        Prostor aktualni = herniPlan.getAktualniProstor();
        Vec kupovana = aktualni.odeberVec(nazevKupovaneho);
        // Kupovaná věc neexistuje.
        if (kupovana == null) {
            return "To tu není!";
        } else if (!kupovana.isViditelnost()) { // Věc není viditelná.
            aktualni.vlozVec(kupovana);
            return "To tu není!";
        } else if (kupovana.getCena() == 0) { // Věc je "zadarmo", nemusí se
            // kupovat.
            aktualni.vlozVec(kupovana);
            return "Věc " + kupovana.getNazev() + " nic nestojí, prostě ji"
                    + " seber.";
        } else {
            // Věc se dá koupit.
            int cena = kupovana.getCena();
            int hracovyPenize = inventar.getHodnotaPenez();
            Collection<Penize> seznamPenez = inventar.getSeznamPenez();
            aktualni.vlozVec(kupovana);
            if (cena > hracovyPenize) {
                // Hráč nemá dost peněz na nákup kupované věci.
                return "Nemáš na to dost peněz, troubo!";
            } else if (cena == hracovyPenize) {
                // Hráč má přesně tolik peněz, kolik je potřeba na zakoupení
                // kupované věci.
                for (Penize penize : seznamPenez) {
                    // Z hráčova inventáře se odeberou všechny peníze.
                    inventar.odeberVec(penize);
                    inventar.setHmotnost(inventar.getHmotnost() - penize
                            .getHmotnost());
                    inventar.setHodnotaPenez(inventar.getHodnotaPenez() - penize
                            .getHodnota());
                }
                kupovana.setCena(0);
                nastavUkoly(kupovana);
                return "Koupeno. Nyní můžeš věc sebrat.\n"
                        + aktualni.popisVeci();
            } else {
                // Hráč má víc peněz, než kolik je potřeba k zakoupení kupované
                // věci. V tomto případě se hráči všechny peníze odečtou, a
                // vytvoří se nové peníze (rozdíl mezi hráčovými penězi a cenou
                // kupované věci), a tyto peníze se přidají do hráčova
                // inventáře.
                int rozdil = hracovyPenize - cena;
                Penize noveHracovyPenize = new Penize(rozdil + "_zlataku",
                        (double) rozdil / 1000, true, true, rozdil);
                for (Penize penize : seznamPenez) {
                    // Z hráčova inventáře se odeberou všechny peníze.
                    inventar.odeberVec(penize);
                    inventar.setHmotnost(inventar.getHmotnost() - penize
                            .getHmotnost());
                    inventar.setHodnotaPenez(inventar.getHodnotaPenez() - penize
                            .getHodnota());
                }
                // Do hráčova inventáře se vrátí peníze, které dostane nazpátek.
                inventar.vlozVec(noveHracovyPenize);
                inventar.setHodnotaPenez(rozdil);
                inventar.setHmotnost(inventar.getHmotnost()
                        + (double) rozdil / 1000);
                kupovana.setCena(0);
                nastavUkoly(kupovana);
                return "Koupeno. Nyní můžeš věc sebrat.\n"
                        + aktualni.popisVeci();
            }
        }
    }
    
    /**
     * Jsou-li nějaké úkoly, které po hráči vyžadují nákup nějaké věci, a je-li
     * kupovaná věc předmětem toho úkolu, tato metoda zařídí, že se úkol označí
     * jako splěný.
     * 
     * @param kupovana kupovaná věc
     */
    private void nastavUkoly(Vec kupovana) {
        for (Ukol u : herniPlan.getSeznamUkolu()) {
            if (u instanceof UkolNakup &&
                    u.getPredmetUkolu().equals(kupovana)) {
                // Hráč splnil nějaký úkol.
                herniPlan.getSeznamUkolu().remove(u);
                u.getZadavatel().setUkolSplnen(true);
            }
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     * 
     * (Tato metoda je pro každý příkaz stejná.)
     * 
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
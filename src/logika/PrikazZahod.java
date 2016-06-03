package logika;

/**
 * Třída PrikazZahod implementuje pro hru příkaz zahod.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160507
 */
public class PrikazZahod implements IPrikaz {
    private static final String NAZEV = "zahod";
    private HerniPlan herniPlan;
    private Inventar inventar;
    
    /**
     * Konstruktor třídy.
     * 
     * @param herniPlan odtud beru informace o stavu herního plánu
     */
    public PrikazZahod(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
        this.inventar = herniPlan.getHrac().getInventar();
    }
    
    /**
     * Provádí příkaz "zahod".
     * 
     * @param parametry co se má zahodit
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        // Nejsou zadané žádné parametry.
        if (parametry.length == 0) {
            return "Co mám zahodit?";
        }
        String nazevZahazovaneho = parametry[0];
        Prostor aktualni = herniPlan.getAktualniProstor();
        Vec zahazovana = inventar.najdiVec(nazevZahazovaneho);
        // Zahazovaná věc není v inventáři.
        if (zahazovana == null) {
            return "To v inventáři nemáš!";
        } else { // Zahazovaná věc je v inventáři.
            if (inventar.odeberVec(zahazovana)) {
                inventar.setHmotnost(inventar.getHmotnost() -
                        zahazovana.getHmotnost());
                aktualni.vlozVec(zahazovana);
                nastavHracovyParametry(zahazovana);
                return "Zahozena věc " + zahazovana.getNazev() + ".";
            }
            // Zahazování věci z nějakého důvodu selhalo.
            return "Ouha, něco se nepovedlo.";
        }
    }
    
    /**
     * Odebere parametry zahazované věci hráčově postavě.
     * 
     * @param zahazovana zahazovaná věc
     */
    private void nastavHracovyParametry(Vec zahazovana) {
        Postava hrac = herniPlan.getHrac();
        if (zahazovana instanceof Zbran) {
            Zbran zahazovanaZbran = (Zbran) zahazovana;
            // Přidá hráči sílu sbírané věci.
            hrac.setSila(hrac.getSila() - zahazovanaZbran.getSila());
        }
        if (zahazovana instanceof Brneni) {
            Brneni zahazovaneBrneni = (Brneni) zahazovana;
            // Přidá hráči brnění sbírané věci.
            hrac.setBrneni(hrac.getBrneni() - zahazovaneBrneni.getBrneni());
        }
        if (zahazovana instanceof Penize) {
            Penize zahazovanePenize = (Penize) zahazovana;
            hrac.getInventar().setHodnotaPenez(hrac.getInventar()
                    .getHodnotaPenez() - zahazovanePenize.getHodnota());
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
package logika;

/**
 * Třída PrikazMluv implementuje pro hru příkaz "mluv".
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160514
 */
public class PrikazMluv implements IPrikaz {
    private static final String NAZEV = "mluv";
    private HerniPlan herniPlan;
    private Inventar inventar;

    /**
     * Konstruktor třídy.
     * 
     * @param herniPlan odtud beru informace o stavu herního plánu
     */
    public PrikazMluv(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
        this.inventar = herniPlan.getHrac().getInventar();
    }
    
    /**
     * Provádí příkaz "mluv".
     * 
     * @param parametry s kým se má mluvit
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        // Nejsou zadané žádné parametry.
        if (parametry.length == 0) {
            return "S kým mám mluvit?";
        }
        String jmenoPostavy = parametry[0];
        Prostor aktualni = herniPlan.getAktualniProstor();
        Postava oslovena = aktualni.odeberPostavu(jmenoPostavy);
        // Oslovená postava neexistuje.
        if (oslovena == null) {
            return "Taková postava tu není!";
        } else if (!(oslovena instanceof MluviciPostava)) { // Postava nemůže mluvit.
            aktualni.vlozPostavu(oslovena);
            return "S touto postavou nemůžeš mluvit.";
        } else {
            aktualni.vlozPostavu(oslovena);
            MluviciPostava oslovenaPostava = (MluviciPostava) oslovena;
            setPozadovaneVeci(oslovenaPostava);
            return oslovenaPostava.getZprava(herniPlan,
                    oslovenaPostava.isUkolZadan(),
                    oslovenaPostava.isUkolSplnen(),
                    oslovenaPostava.isPozadovaneVeci(),
                    oslovenaPostava.isOdmenaVyplacena());
        }
    }
    
    /**
     * Metoda setPozadovaneVeci nastaví postavě, se kterou hráč mluví, parametr
     * pozadovaneVeci, podle kterého (mimo jiné) se pak postava rozhodne, co
     * hráči řekne.
     * 
     * @param postava oslovená postava
     */
    private void setPozadovaneVeci(MluviciPostava postava) {
        for (Ukol u : herniPlan.getSeznamUkolu()) {
            if (u.getZadavatel().equals(postava)) {
                if (u.getPozadovaneVeci() instanceof Vec) {
                    // Jestliže jsou věci požadované ke splnění úkolu
                    // instancí třídy věc, provede se toto:
                    Vec v = (Vec) u.getPozadovaneVeci();
                    // Oslovená postava si zjistí, jestli má hráč v
                    // inventáři požadované věci.
                    postava.setPozadovaneVeci(inventar.odeberVec(v));
                    inventar.vlozVec(v);
                } else if (u.getPozadovaneVeci() instanceof Integer) {
                    // Jinak pokud jsou věci požadované ke splnění úkolu
                    // instancí třídy Integer (v tomto případě jsou
                    // požadovanými věcmi peníze (přesná částka), provede se
                    // toto:
                    int i = (Integer) u.getPozadovaneVeci();
                    // Kontrola, jestli má hráč u sebe dostatek peněz.
                    postava.setPozadovaneVeci(inventar.getHodnotaPenez() >= i);
                }
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
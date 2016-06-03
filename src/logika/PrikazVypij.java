package logika;

/**
 * Třída PrikazVypij implementuje pro hru příkaz "vypij".
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160522
 */
public class PrikazVypij implements IPrikaz {
    private static final String NAZEV = "vypij";
    private HerniPlan herniPlan;
    private Inventar inventar;

    /**
     * Konstruktor třídy.
     * 
     * @param herniPlan odtud beru informace o stavu herního plánu
     */
    public PrikazVypij(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
        this.inventar = herniPlan.getHrac().getInventar();
    }
    
    /**
     * Provádí příkaz "vypij".
     * 
     * @param parametry co se má sebrat
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        // Nejsou zadané žádné parametry.
        if (parametry.length == 0) {
            return "Co mám vypít?";
        }
        String nazevLektvaru = parametry[0];
        Vec lektvar = inventar.najdiVec(nazevLektvaru);
        // Lektvar neexistuje.
        if (lektvar == null) {
            return "To v inventáři nemáš!";
        } else if (!(lektvar instanceof Lektvar)) {
            // Věc, kterou chce hráč vypít, není letkvar.
            return nazevLektvaru.substring(0, 1).toUpperCase() + nazevLektvaru
                    .substring(1) + " se nedá vypít, moulo!";
        } else {
            // Vše je v pořádku, lektvar je v hráčově inventáři.
            Lektvar l = (Lektvar) lektvar;
            inventar.odeberVec(lektvar);
            inventar.setHmotnost(inventar.getHmotnost()-lektvar.getHmotnost());
            return "Vypito. Lektvar ti přidal " + upravZdravi(l) + " zdraví. " +
                    "Nyní máš " + herniPlan.getHrac().getZdravi() + " zdraví.";
        }
    }
    
    /**
     * Upravuje zdraví hráče po vypití lektvaru.
     * 
     * @param lektvar vypitý lektvar
     * @return kolik zdraví se hráči přičetlo
     */
    private double upravZdravi(Lektvar lektvar) {
        Postava hrac = herniPlan.getHrac();
        double vraceno = hrac.getZdravi() + lektvar.getZdravi();
        hrac.setZdravi(vraceno);
        if (hrac.getZdravi() >= hrac.getMAX_ZDRAVI()) {
            // Hráč má po vypití lektvaru vyšší zdraví, než je maximální
            // možné zdraví. V tomto případě se jeho zdraví sníží na maximální
            // možnou hodnotu.
            vraceno = lektvar.getZdravi() - (hrac.getZdravi() - hrac
                    .getMAX_ZDRAVI());
            hrac.setZdravi(hrac.getMAX_ZDRAVI());
            return vraceno;
        }
        return lektvar.getZdravi();
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
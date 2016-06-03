package logika;

/**
 * Třída PrikazInventar implementuje pro hru příkaz "inventar".
 * 
 * Příkaz "inventar" vypíše seznam věcí v hráčově inventáři a jejich atributy.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160520
 */
public class PrikazInventar implements IPrikaz {
    private static final String NAZEV = "inventar";
    private HerniPlan herniPlan;
    private Inventar inventar;

    /**
     * Konstruktor třídy.
     * 
     * @param herniPlan odtud beru informace o stavu herního plánu
     */
    public PrikazInventar(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
        this.inventar = herniPlan.getHrac().getInventar();
    }
    
    /**
     * Provádí příkaz "inventar" (vypíše seznam věcí v inventáři a jejich
     * atributy.
     * 
     * @param parametry nejsou očekávané žádné parametry
     * @return seznam věcí v inventáři a jejich atributy
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length > 0) {
            return "Inventář co? Nechápu, proč jsi zadal druhé slovo!";
        } else {
            inventar = herniPlan.getHrac().getInventar();
            return inventar.toString();
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
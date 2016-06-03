package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Jarmila Pavlickova, Luboš Pavlíček, Vratislav Jindra
 * @version pro školní rok 2015/2016
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    
    /**
    * Konstruktor třídy.
    * 
    * @param plan herní plán, ve kterém se bude ve hře "chodit"
    */
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }
    
    /**
     * Provádí příkaz "jdi".
     * 
     * Zkouší se vyjít do zadaného prostoru. Pokud prostor existuje, vstoupí se
     * do nového prostoru. Pokud zadaný sousední prostor (východ) není, vypíše
     * se chybové hlášení.
     * 
     * @param parametry jméno prostoru (východu), do kterého se má jít
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // Pokud chybí druhé slovo (sousední prostor), tak:
            return "Kam mám jít? Musíš zadat jméno východu!";
        }
        String smer = parametry[0];
        // Zkoušíme přejít do sousedního prostoru:
        Prostor sousedniProstor = plan.getAktualniProstor()
                .vratSousedniProstor(smer);
        if (sousedniProstor == null || !sousedniProstor.isViditelnost()) {
            return "Tam se odsud jít nedá!";
        } else {
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        }
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
package logika;

/**
 * Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Jarmila Pavlickova, Luboš Pavlíček, Vratislav Jindra
 * @version pro školní rok 2015/2016
 */
class PrikazNapoveda implements IPrikaz {
    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;
    
    /**
     * Konstruktor třídy.
     * 
     * @param platnePrikazy seznam příkazů, které je možné ve hře použít,
     *                      aby je nápověda mohla zobrazit uživateli
     */
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    
    /**
     * Vrací základní nápovědu po zadání příkazu "napoveda".
     * 
     * Nyní se vypisuje vcelku primitivní zpráva a seznam dostupných příkazů.
     * 
     * @param parametry nejsou očekávané žádné parametry, ale nevadí když se
     *                  nějaké zadají
     * @return nápověda ke hře
     */
    @Override
    public String proved(String... parametry) {
        return "Tvým úkolem je zabít draka Bucifala\n"
        + "ať to stojí co to stojí.\n"
        + "\n"
        + "Můžeš zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
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
package logika;

/**
 * Třída PrikazKonec implementuje pro hru příkaz konec.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Jarmila Pavlickova, Vratislav Jindra
 * @version pro školní rok 2015/2016
 */
class PrikazKonec implements IPrikaz {
    private static final String NAZEV = "konec";
    private Hra hra;
    
    /**
     * Konstruktor třídy.
     * 
     * @param hra odkaz na hru, která má být příkazem konec ukončena
     */
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }
    
    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda
     * setKonecHry(true), jinak pokračuje např. při zadání "konec a".
     * 
     * @param parametry zde žádné parametry očekávané nejsou
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length > 0) {
            return "Konec co? Nechápu, proč jsi zadal druhé slovo!";
        } else {
            hra.setKonecHry(true);
            return "Dík, že sis zahrál.";
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
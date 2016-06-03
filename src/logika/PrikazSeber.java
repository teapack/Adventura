package logika;

/**
 * Třída PrikazSeber implementuje pro hru příkaz "seber".
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160428
 */
public class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private HerniPlan herniPlan;
    private Inventar inventar;

    /**
     * Konstruktor třídy.
     * 
     * @param herniPlan odtud beru informace o stavu herního plánu
     */
    public PrikazSeber(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
        this.inventar = herniPlan.getHrac().getInventar();
    }
    
    /**
     * Provádí příkaz "seber".
     * 
     * @param parametry co se má sebrat
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        // Nejsou zadané žádné parametry.
        if (parametry.length == 0) {
            return "Co mám sebrat?";
        }
        String nazevSbiraneho = parametry[0];
        Prostor aktualni = herniPlan.getAktualniProstor();
        Vec sbirana = aktualni.odeberVec(nazevSbiraneho);
        // Sbíraná věc neexistuje.
        if (sbirana == null) {
            return "To tu není!";
        } else if (!sbirana.isViditelnost()) { // Věc není viditelná.
            aktualni.vlozVec(sbirana);
            return "To tu není!";
        } else if (!sbirana.isPrenositelnost()) { // Věc není přenositelná.
            aktualni.vlozVec(sbirana);
            return "To neuzvedneš.";
        } else if (sbirana.getCena() > 0) { // Věc nelze sebrat, protože její
            // cena je větší než nula; hráč si ji musí koupit.
            aktualni.vlozVec(sbirana);
            return "Nejdřív si to musíš koupit!";
        } else if (inventar.getHmotnost() + sbirana.getHmotnost() > inventar.
                getMAX_HMOTNOST()) { // Hráč věc neunese.
            aktualni.vlozVec(sbirana);
            return "To neuneseš, se sbíranou věcí bys u sebe měl "
                    + (inventar.getHmotnost() + sbirana.getHmotnost())
                    + "kg.\nTvoje maximální nosnost je "
                    + inventar.getMAX_HMOTNOST()
                    + "kg. Zkus něco vyhodit.";
        } else { // Hráč věc unese.
            if (inventar.vlozVec(sbirana)) {
                nastavHracovyParametry(sbirana);
                inventar.setHmotnost(inventar.getHmotnost() +
                        sbirana.getHmotnost());
                return "Sebrána věc " + sbirana.getNazev() + ".";
            }
            // Vkládání věci do inventáře z nějakého důvodu selhalo.
            return "Ouha, něco se nepovedlo.";
        }
    }
    
    /**
     * Nastavuje parametry sbírané věci hráči.
     * 
     * @param sbirana sbíraná věc
     */
    private void nastavHracovyParametry(Vec sbirana) {
        Postava hrac = herniPlan.getHrac();
        if (sbirana instanceof Zbran) {
            Zbran sbiranaZbran = (Zbran) sbirana;
            // Přidá hráči sílu sbírané věci.
            hrac.setSila(hrac.getSila() + sbiranaZbran.getSila());
        }
        if (sbirana instanceof Brneni) {
            Brneni sbiraneBrneni = (Brneni) sbirana;
            // Přidá hráči brnění sbírané věci.
            hrac.setBrneni(hrac.getBrneni() + sbiraneBrneni.getBrneni());
        }
        if (sbirana instanceof Penize) {
            Penize sbiranePenize = (Penize) sbirana;
            hrac.getInventar().setHodnotaPenez(hrac.getInventar()
                    .getHodnotaPenez() + sbiranePenize.getHodnota());
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
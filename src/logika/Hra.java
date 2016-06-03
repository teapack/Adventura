package logika;

/**
 * Třída Hra - třída představující logiku adventury.
 * 
 * Toto je hlavní třída logiky aplikace. Tato třída vytváří instanci třídy
 * HerniPlan, která inicializuje mistnosti hry a vytváří seznam platných příkazů
 * a instance tříd provádějící jednotlivé příkazy.
 * Vypisuje uvítací a ukončovací text hry.
 * Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 * 
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Vratislav Jindra
 * @version pro školní rok 2015/2016
 */
public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy; // Obsahuje seznam přípustných příkazů.
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    
    /**
     * Vytváří hru a inicializuje prostory, věci, postavy (prostřednictvím
     * třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZahod(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazInventar(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazUtok(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazOdemkni(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKup(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVypij(herniPlan));
    }
    
    /**
     * Vrátí úvodní zprávu pro hráče.
     */
    @Override
    public String vratUvitani() {
        return "Vítej!\n" +
                "Toto je příběh o zemi sužované zlým drakem Bucifalem.\n" +
                "Napiš 'napoveda', pokud si nevíš rady, jak hrát dál.\n" +
                "\n" +
                herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     * Vrátí závěrečnou zprávu pro hráče.
     */
    @Override
    public String vratEpilog() {
        if (herniPlan.vyhra()) { // Hráč vyhrál.
            return "Vyhrál jsi, gratuluji!";
        } else if (herniPlan.getHrac().getZdravi() <= 0) { // Hráč umřel.
            return "Umřel jsi. Game over.";
        }
        // Hra byla ukončena příkazem konec.
        return "Hra byla ukončena příkazem konec.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
    @Override
    public boolean konecHry() {
        return konecHry;
    }
    
    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo
     * příkazu a další parametry.
     * 
     * Pak otestuje zda příkaz je klíčovým slovem, např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     * 
     * @param radek text, který zadal uživatel jako příkaz do hry
     * @return řetězec, který se má vypsat na obrazovku
     */
    @Override
    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        for (int i = 0; i < parametry.length; i++){
           	parametry[i] = slova[i + 1];
        }
        String textKVypsani;
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
        } else {
            textKVypsani = "Nevím co tím myslíš. Tento příkaz neznám.";
        }
        return textKVypsani;
    }
    
    /**
     * Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     * mohou ji použít i další implementace rozhraní Prikaz.
     * 
     * @param konecHry hodnota false = konec hry, true = hra pokračuje
     */
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     * 
     * @return odkaz na herní plán
     */
    @Override
    public HerniPlan getHerniPlan(){
        return herniPlan;
    }
}
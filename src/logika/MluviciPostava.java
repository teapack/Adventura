package logika;

/**
 * Třída MluviciPostava představuje jednotlivé postavy ve hře, které jsou
 * schopné mluvit.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160514
 */
public class MluviciPostava extends Postava {
    private boolean ukolZadan;
    private boolean ukolSplnen;
    private boolean pozadovaneVeci;
    private boolean odmenaVyplacena;
    private String[] zpravy;
    private HerniPlan herniPlan;
    private Inventar hracuvInventar;
    
    /**
     * Konstruktor, pomocí kterého budu vytvářet instance mluvících postav.
     * 
     * @param jmeno jméno postavy
     * @param zdravi zdraví postavy
     * @param sila síla postavy
     * @param brneni brnění postavy
     * @param herniPlan odkaz na herní plán, ze kterého mluvící postava zjistí
     *                  odkaz na svůj úkol
     */
    public MluviciPostava(String jmeno, double zdravi, double sila,
            double brneni, HerniPlan herniPlan) {
        super(jmeno, zdravi, sila, brneni);
        this.herniPlan = herniPlan;
        hracuvInventar = herniPlan.getHrac().getInventar();
    }
    
    /**
     * Setter na datový atribut ukolZadan.
     * 
     * @param ukolZadan pokud byl postavou hráči úkol zadán, nastaví se sem
     *                  true, jinak false
     */
    public void setUkolZadan(boolean ukolZadan) {
        this.ukolZadan = ukolZadan;
    }
    
    /**
     * Getter na datový atribut ukolZadan.
     * 
     * @return true, byl-li postavou hráči zadán úkol, jinak false
     */
    public boolean isUkolZadan() {
        return ukolZadan;
    }
    
    /**
     * Setter na datový atribut ukolSplnen.
     * 
     * @param ukolSplnen pokud hráč úkol splnil (ať už byl zadaný nebo ne),
     *                   nastaví se sem true, pokud úkol ještě splněn nebyl,
     *                   nastaví se sem false
     */
    public void setUkolSplnen(boolean ukolSplnen) {
        this.ukolSplnen = ukolSplnen;
    }
    
    /**
     * Getter na datový atribut ukolSplnen.
     * 
     * @return true, pokud hráč úkol splnil, jinak false
     */
    public boolean isUkolSplnen() {
        return ukolSplnen;
    }
    
    /**
     * Setter na datový atribut pozadovaneVeci.
     * 
     * @param pozadovaneVeci pokud má hráč u sebe požadované věci ke splnění
     *                       úkolu, nastaví se sem true, jinak false
     */
    public void setPozadovaneVeci(boolean pozadovaneVeci) {
        this.pozadovaneVeci = pozadovaneVeci;
    }
    
    /**
     * Getter na datový atribut pozadovaneVeci.
     * 
     * @return true, má-li hráč věci požadované postavou ke splnění úkolu, jinak
     *         false
     */
    public boolean isPozadovaneVeci() {
        return pozadovaneVeci;
    }
    
    /**
     * Setter na datový atribut odmenaVyplacena.
     * 
     * @param odmenaVyplacena byla-li hráči postavou po splnění úkolu vyplacena
     *                        odměna, nastaví se sem true, jinak false
     */
    public void setOdmenaVyplacena(boolean odmenaVyplacena) {
        this.odmenaVyplacena = odmenaVyplacena;
    }
    
    /**
     * Getter na datový atribut odmenaVyplacena.
     * 
     * @return true, byla-li hráči postavou po splnění úkolu vyplacena odměna,
     *         jinak false
     */
    public boolean isOdmenaVyplacena() {
        return odmenaVyplacena;
    }
    
    /**
     * Metoda, která nastaví zprávu, kterou bude postava říkat hráči, když s
     * postavou bude chtít hráč mluvit.
     * 
     * @param zpravy zprávy, které postava může říct hráči
     */
    public void setZpravy(String[] zpravy) {
        this.zpravy = zpravy;
    }
    
    /**
     * Metoda, která vrací zprávu, kterou postava řekne hráči, když s ní hráč
     * chce promluvit.
     * 
     * @param herniPlan odkaz na herní plán
     * @param ukolZadan true, když už byl hráči postavou zadán úkol, jinak false
     * @param ukolSplnen true, když postava úkol splnila, jinak false
     * @param pozadovaneVeci true, když má postava věci, které jsou ke splnění
     *                       úkolu požadované, jinak false
     * @param odmenaVyplacena true, když hráč po splnění úkolu obdržel odměnu,
     *                        jinak false
     * @return zpráva, kterou postava řekne hráči
     */
    public String getZprava(HerniPlan herniPlan, boolean ukolZadan,
            boolean ukolSplnen, boolean pozadovaneVeci,
            boolean odmenaVyplacena) {
        if (!ukolZadan && ukolSplnen) {
            // Hráč splnil úkol, aniž by ho k tomu postava vyzvala - ztrácí tak
            // možnost získat od postavy odměnu (protože postava hráči ani
            // žádnou odměnu nemohla nabídnout).
            return zpravy[0];
        } else if (!ukolZadan && pozadovaneVeci) {
            // Hráč úkol ještě nesplnil, úkol mu ani nebyl zadán, a hráč má u
            // sebe v inventáři požadované věci -> dostane od postavy zadání
            // úkolu.
            setUkolZadan(true);
            if (vycarujVybavu()) {
                // Když se vyčarovala nějaká výbava, vypíše se i popis věcí v
                // aktuálním prostoru.
                vycarujMoznouVybavu();
                return zpravy[1] + "\n" + herniPlan.getAktualniProstor()
                        .popisVeci();
            } else {
                return zpravy[1];
            }
        } else if (!ukolZadan) {
            // Hráč úkol ještě nesplnil, úkol mu ani nebyl zadán, a hráč u sebe
            // v inventáři nemá požadované věci -> dostane od postavy zadání
            // úkolu (trochu odlišné od toho, kdyby měl všechny požadované
            // věci).
            setUkolZadan(true);
            if (vycarujVybavu()) {
                // Když se vyčarovala nějaká výbava, vypíše se i popis věcí v
                // aktuálním prostoru.
                return zpravy[2] + "\n" + herniPlan.getAktualniProstor()
                        .popisVeci();
            } else {
                return zpravy[2];
            }
        } else if (!ukolSplnen) {
            // Hráčovi už byl úkol zadán, ale ještě nebyl splněn -> zpráva hráče
            // znovu vyzve ke splnění úkolu.
            return zpravy[3];
        } else if (!odmenaVyplacena) {
            // Hráč úkol spnil, jen ještě nedostal odměnu -> hráč od postavy
            // dostane odměnu.
            setOdmenaVyplacena(true);
            if (vyplatOdmenu()) {
                return zpravy[4] + "\n" + herniPlan.getAktualniProstor()
                        .popisVeci();
            }
            return zpravy[4];
        }
        // Univerzální zpráva, která se hráči vypíše po splnění úkolu, ať už byl
        // úkol zadán, nebo ne.
        return zpravy[0];
    }
    
    /**
     * Metoda, která do aktuálního prostoru vloží odměnu za daný úkol.
     */
    private boolean vyplatOdmenu() {
        for (Ukol u : herniPlan.getSeznamUkolu()) {
            if (u.getZadavatel().equals(this) && u.isOdmena() && u instanceof
                    UkolVrazda) {
                UkolVrazda ukolVrazda = (UkolVrazda) u;
                this.getInventar().odeberVec(ukolVrazda.getOdmena());
                herniPlan.getAktualniProstor().vlozVec(ukolVrazda.getOdmena());
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metoda, pomocí které postava vyčaruje výbavu, kterou postava slíbila, že
     * hráči ke splnění úkolu dodá, má-li hráč u sebe potřebné věci.
     * 
     * @return true, když se vyčarovala nějaká výbava, jinak false
     */
    private boolean vycarujMoznouVybavu() {
        for (Ukol u : herniPlan.getSeznamUkolu()) {
            if (u.getZadavatel().equals(this) && u instanceof UkolVrazda) {
                UkolVrazda ukolVrazda = (UkolVrazda) u;
                Vec odebiranaVec = (Vec) ukolVrazda.getPozadovaneVeci();
                hracuvInventar.odeberVec(odebiranaVec);
                if (odebiranaVec instanceof Zbran) {
                    // Věc odebíraná z hráčova inventáře je zbraň, hráčova síla
                    // se tedy musí snížit.
                    herniPlan.getHrac().setSila(herniPlan.getHrac().getSila() -
                            ((Zbran) odebiranaVec).getSila());
                } else if (odebiranaVec instanceof Brneni) {
                    // Věc odebíraná z hráčova inventáře je brnění, hráčovo
                    // brnění se tedy musí snížit.
                    herniPlan.getHrac().setBrneni(herniPlan.getHrac()
                            .getBrneni() - ((Brneni) odebiranaVec).getBrneni());
                }
                hracuvInventar.setHmotnost(hracuvInventar.getHmotnost() -
                        odebiranaVec.getHmotnost());
                herniPlan.getAktualniProstor().vlozVec(ukolVrazda
                        .getMoznaVybava());
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metoda, pomocí které postava vyčaruje výbavu, kterou postava slíbila, že
     * hráči ke splnění úkolu dodá.
     * 
     * @return true, když se nějaká výbava vyčarovala, jinak false
     */
    private boolean vycarujVybavu() {
        for (Ukol u : herniPlan.getSeznamUkolu()) {
            if (u.getZadavatel().equals(this) && u instanceof UkolVrazda) {
                UkolVrazda ukolVrazda = (UkolVrazda) u;
                herniPlan.getAktualniProstor().vlozVec(ukolVrazda.getVybava());
                return true;
            }
        }
        return false;
    }
}
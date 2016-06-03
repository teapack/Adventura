package logika;

import java.util.Random;

/**
 * Třída PrikazUtok implementuje pro hru příkaz "utok".
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160507
 */
public class PrikazUtok implements IPrikaz {
    private static final String NAZEV = "utok";
    private HerniPlan herniPlan;
    private static final int MAX_UTOK = 1;
    private static final int MIN_UTOK = -1;
    private Hra hra;

    /**
     * Konstruktor třídy.
     * 
     * @param herniPlan odtud beru informace o stavu herního plánu
     * @param hra odkaz na hru, která bude ukončena, klesne-li hráčovo zdraví
     *            na nebo pod nulu
     */
    public PrikazUtok(HerniPlan herniPlan, Hra hra) {
        this.herniPlan = herniPlan;
        this.hra = hra;
    }
    
    /**
     * Provádí příkaz "utok".
     * 
     * @param parametry jméno postavy na kterou se má útočit
     * @return informace o stavu zdraví hráče a druhé postavy
     */
    @Override
    public String proved(String... parametry) {
        // Když se nezadají žádné parametry, provede se toto:
        if (parametry.length == 0) {
            return "Na koho mám zaútočit?";
        }
        // Když se nějaké parametry zadají, zjistí se, jestli je to existující
        // postava.
        String jmenoZabijenePostavy = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Postava zabijena = aktualniProstor.odeberPostavu(jmenoZabijenePostavy);
        Postava hrac = herniPlan.getHrac();
        if (zabijena == null) { // Není to platná postava.
            return "Taková postava tu není!";
        } else { // Je to platná postava, provede se útok.
            aktualniProstor.vlozPostavu(zabijena);
            upravZdraviPostav(zabijena, hrac);
            if (hrac.getZdravi() <= 0) { // Hráč umřel, hra končí.
                hra.setKonecHry(true);
                return hrac.toString();
            } else if (zabijena.getZdravi() <= 0) { // Zabíjená postava zemřela.
                aktualniProstor.odeberPostavu(jmenoZabijenePostavy);
                System.out.println(hrac.toString());
                // Zviditelní se neviditelné východy.
                aktualniProstor.zviditelniNeviditelneVychody();
                // Mrtvá postava "dropne" věci, které měla v inventáři.
                zabijena.dropInventar().stream().forEach(vec ->
                        aktualniProstor.vlozVec(vec));
                /*for (Vec v : zabijena.dropInventar()) {
                    aktualniProstor.vlozVec(v);
                }*/
                // Existuje-li úkol, aby hráč zabil nějakou postavu, splní se.
                for (Ukol u : herniPlan.getSeznamUkolu()) {
                    if (u instanceof UkolVrazda &&
                            u.getPredmetUkolu().equals(zabijena)) {
                        //herniPlan.getSeznamUkolu().remove(u);
                        u.getZadavatel().setUkolSplnen(true);
                    }
                }
                System.out.println("Juchů, zabil jsi postavu " +
                        jmenoZabijenePostavy + ".");
                if (herniPlan.vyhra()) {
                    // Hráč vyhrál.
                    hra.setKonecHry(true);
                    return "";
                }
                return aktualniProstor.dlouhyPopis();
            } else { // Hráč i zabíjená postava žijí.
                return zabijena.toString() + "\n" + hrac.toString();
            }
        }
    }
    
    /**
     * Tato metoda nastaví zdraví hráče a postavy při úspěšném provedení příkazu
     * utok.
     * 
     * @param zabijena postava, na kterou hráč příkazem utok útočí
     * @param hrac postava hráče
     */
    private void upravZdraviPostav(Postava zabijena, Postava hrac) {
        Random generator = new Random();
        if (zabijena.getBrneni() >= 1) {
            // Hráč nemůže zabíjenou postavu nijak zranit, protože zabíjená
            // postava má brnění, které pohltí celou sílu hráčova útoku.
        } else {
            zabijena.setZdravi(zabijena.getZdravi()
                    - (hrac.getSila()
                            + generator.nextInt(MAX_UTOK
                                    + 1 // MAX_UTOK se do toho nezapočítává
                                    - MIN_UTOK)
                            + MIN_UTOK)
                            * (1 - zabijena.getBrneni()));
        }
        if (hrac.getBrneni() >= 1) {
            // Postava hráče nijak nezraní, protože hráčovo brnění je příliš
            // dobré, a pohltí celou sílu útoku postavy, na kterou hráč
            // zaútočil.
        } else {
            hrac.setZdravi(hrac.getZdravi()
                    - (zabijena.getSila()
                            + generator.nextInt(MAX_UTOK
                                    + 1
                                    - MIN_UTOK)
                            + MIN_UTOK)
                            * (1 - hrac.getBrneni()));
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
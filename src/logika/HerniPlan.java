package logika;

import java.util.HashSet;
import java.util.Set;

/**
 * Class HerniPlan představuje mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 * Dále vytváří věci a postavy, které se pak umisťují do různých prostorů.
 * V této třídě také existuje hráčův inventář.
 * 
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Vratislav Jindra
 * @version pro školní rok 2015/2016
 */
public class HerniPlan {
    private Prostor aktualniProstor;
    private Postava hrac;
    private Postava drak;
    private MluviciPostava trpaslik;
    private MluviciPostava prodavac;
    private Set<Ukol> seznamUkolu;
    
    /**
     * Konstruktor, který vytváří jednotlivé prostory, propojuje je pomocí
     * východů, a umístí do nich postavy a věci.
     */
    public HerniPlan() {
        seznamUkolu = new HashSet<>();
        zalozProstoryHry();
    }
    
    /**
     * Vytváří jednotlivé prostory a propojuje je pomocí východů, do prostor
     * umisťuje věci a postavy.
     * 
     * Jako výchozí aktuální prostor nastaví pláž.
     */
    private void zalozProstoryHry() {
        // Vytváření jednotlivých prostor.
        Prostor plaz = new Prostor("plaz","pláž, na které ses vylodil", true);
        Prostor lod = new Prostor("lod", "loď, kterou jsi připlul", true);
        Prostor les = new Prostor("les","les, ve kterém lze potkat vlka", true);
        Prostor mytina = new Prostor("mytina","mýtina, na kterou byl vyhnán "
                + "trpaslík", false);
        Prostor hlubokyLes = new Prostor("hluboky_les","temný les, do kterého " 
                + "se usídlil medvěd", true);
        Prostor chaloupka = new Prostor("chaloupka","trpaslíkova chaloupka",
                false);
        Prostor louka = new Prostor("louka","louka, na které lze potkat lva",
                true);
        Prostor obchod = new Prostor("obchod","obchod s brněním", false);
        Prostor jeskyne = new Prostor("jeskyne","dračí jeskyně, ve které žije "
                + "hrozivý drak Bucifal", false);
        
        // Přiřazování průchodů mezi prostory (sousedící prostory).
        plaz.setVychod(lod);
        lod.setVychod(plaz);
        plaz.setVychod(les);
        les.setVychod(plaz);
        les.setVychod(mytina);
        mytina.setVychod(les);
        mytina.setVychod(hlubokyLes);
        hlubokyLes.setVychod(mytina);
        hlubokyLes.setVychod(chaloupka);
        chaloupka.setVychod(hlubokyLes);
        mytina.setVychod(louka);
        louka.setVychod(mytina);
        louka.setVychod(obchod);
        obchod.setVychod(louka);
        louka.setVychod(jeskyne);
        
        // Hra začíná na pláži.
        aktualniProstor = plaz;
        
        // Vytváření jednotlivých postav.
        hrac = new Postava("hrac", 20, 1, 0);
        hrac.setMAX_ZDRAVI(20);
        Postava vlk = new Postava("vlk", 5, 2, 0);
        trpaslik = new MluviciPostava("trpaslik", 15, 1, 0, this);
        Postava medved = new Postava("medved", 10, 4, 0);
        Postava lev = new Postava("lev", 10, 5, 0);
        prodavac = new MluviciPostava("prodavac", 20, 1, 1, this);
        drak = new Postava("drak", 40, 9, 0.2);
        
        // Přidávání postav do prostorů.
        les.vlozPostavu(vlk);
        mytina.vlozPostavu(trpaslik);
        hlubokyLes.vlozPostavu(medved);
        louka.vlozPostavu(lev);
        obchod.vlozPostavu(prodavac);
        jeskyne.vlozPostavu(drak);
        
        // Vytváření jednotlivých věcí.
        // Klíč na pláži. Poslední parametr je unikátní id, podle kterého
        // poznám, že tento klíč odemyká věc se stejným id - v tomto případě
        // truhlu (na lodi). Id klíče (a věci, kterou klíč odemyká), se dá
        // přirovnat ke "vzoru" na klíči a v zámku.
        Klic klic = new Klic("klic", 0.1, true, true, 1);
        // Zamknutá truhla na lodi. Poslední parametr musí být roven parametru,
        // který je u klíče.
        OdemykatelnaVec truhla = new OdemykatelnaVec("truhla", 60, false, true,
                1);
        // Nůž v zamknuté truhle na lodi, objeví se až po odemknutí truhly.
        Zbran nuz = new Zbran("nuz", 0.5, true, false, 4);
        // Kožené brnění vyčarované trpaslíkem (objeví se jen po přijetí
        // trpaslíkovy mise.
        Brneni kozeneBrneni = new Brneni("kozene_brneni", 20, true, true, 0.5);
        // Nůž, který trpaslík kouzlem přeměnil na meč (objeví se až po přijetí
        // trpaslíkovy mise). Po vytvoření meče hráči zmizí nůž.
        Zbran mec = new Zbran("mec", 10, true, true, 8);
        // Peníze v trpaslíkově chaloupce; peníze, které u sebe má trpaslík.
        Penize stoZlataku = new Penize("100_zlataku", 0.1, true, true, 100);
        // Peníze, které hráč může dostat jako odměnu za splnění trpaslíkova
        // úkolu; má je u sebe v inventáři trpaslík.
        Penize dveSteZlataku = new Penize("200_zlataku", 0.2, true, true, 200);
        // Nelze jen tak sebrat, musí se koupit za 400 zlaťáků.
        Brneni kovoveBrneni = new Brneni("kovove_brneni", 30, true, true, 0.8);
        kovoveBrneni.setCena(400);
        // Objeví se jen při zabití trpaslíka; lektvar doplní 15 zdraví.
        Lektvar lektvarZdravi = new Lektvar("lektvar_zdravi", 0.2, true, true,
                15);
        // Nesebratelné nebo sebratelné (ale příliš těžké nebo zbytečné) věci,
        // které do hry přidávám jen pro ilustraci, že to vyžadovalo zadání.
        Vec kormidlo = new Vec("kormidlo", 30, false, true);
        Vec zidle = new Vec("zidle", 4, true, true);
        Vec stul = new Vec("stul", 16, true, true);
        Vec strom = new Vec("strom", 700, false, true);
        Vec parez = new Vec("parez", 80, false, true);
        
        // Přidávání věcí do prostorů.
        plaz.vlozVec(klic);
        lod.vlozVec(truhla);
        lod.vlozVec(nuz);
        chaloupka.vlozVec(stoZlataku);
        obchod.vlozVec(kovoveBrneni);
        // Přidávání zbytečných věcí.
        lod.vlozVec(kormidlo);
        les.vlozVec(strom);
        mytina.vlozVec(parez);
        hlubokyLes.vlozVec(strom);
        hlubokyLes.vlozVec(strom);
        chaloupka.vlozVec(zidle);
        chaloupka.vlozVec(stul);
        
        // Přidávání věcí do trpaslíkova inventáře.
        Inventar trpaslikuvInventar = trpaslik.getInventar();
        trpaslikuvInventar.vlozVec(stoZlataku);
        trpaslikuvInventar.vlozVec(dveSteZlataku);
        trpaslikuvInventar.vlozVec(lektvarZdravi);
        
        // Nastavování zpráv mluveníschopným postavám.
        String[] trpaslikovyZpravy = new String[5];
        trpaslikovyZpravy[0] =
"Díky za zabití medvěda, kvůli kterému jsem nemohl domů do své chaloupky.\n" +
"Hodně štěstí při souboji s drakem Bucifalem.";
        trpaslikovyZpravy[1] =
"Ahoj. Mé jméno je trpaslík Šmudla. Bydlím v chaloupce za hlubokým lesem.\n" +
"Sem na mýtinu mě vyhnal zlý medvěd, který se v hlubokém lese před mou\n" +
"chaloupkou usídlil. Vidím, že jdeš na výpravu zabít draka Bucifala. Když mi\n"+
"pomůžeš, pomůžu i já tobě. Zabij medvěda, který blokuje cestu do mé\n" +
"chaloupky, a já se ti štědře odměním. Na zabití medvěda ti vyčaruji kožené\n" +
"brnění, a přeměním tvůj nůž na meč - brnění i meč ti pak přijdou vhod i při\n"+
"souboji s Bucifalem. Až medvěda zabiješ, přijď za mnou, a jako odměnu ode\n" +
"mě dostaneš ještě 200 zlaťáků. Hodně štěstí!";
        trpaslikovyZpravy[2] =
"Ahoj. Mé jméno je trpaslík Šmudla. Bydlím v chaloupce za hlubokým lesem.\n" +
"Sem na mýtinu mě vyhnal zlý medvěd, který se v hlubokém lese před mou\n" +
"chaloupkou usídlil. Vidím, že jdeš na výpravu zabít draka Bucifala. Když mi\n"+
"pomůžeš, pomůžu i já tobě. Zabij medvěda, který blokuje cestu do mé\n" +
"chaloupky, a já se ti štědře odměním. Na zabití medvěda ti vyčaruji kožené\n" +
"brnění, které ti pak přijde vhod i při souboji s Bucifalem. Až medvěda\n" +
"zabiješ, přijď za mnou, a jako odměnu ode mě dostaneš ještě 200 zlaťáků.\n" +
"Hodně štěstí!";
        trpaslikovyZpravy[3] = "Přijď za mnou až medvěda zabiješ.";
        trpaslikovyZpravy[4] =
"Vidím, že jsi zabil medvěda! Děkuji, tady je tvá slíbená odměna, 200 zlaťáků.";
        trpaslik.setZpravy(trpaslikovyZpravy);
        String[] prodavacovyZpravy = new String[5];
        prodavacovyZpravy[0] = "Díky za nákup brnění.";
        prodavacovyZpravy[1] =
"Ahoj. Jak sis určitě všiml, jsem prodavač brnění. Předpokládám, že to ty\n" +
"jsi ten hrdina, který nás chce osvobodit od nadvlády zlého draka Bucifala.\n" +
"Vidím že u sebe máš dost peněz na koupi kovového brnění, které by se ti při\n"+
"souboji s drakem určitě hodilo. Je mnohem lepší než jakékoliv jiné brnění,\n" +
"které v této zemi můžeš sehnat - je schopné pohltit až " + kovoveBrneni.getBrneni()*100 + "% útočné síly\n" +
"tvých protivníků. Použij příkaz >kup kovove_brneni<, já si od tebe vezmu\n" +
kovoveBrneni.getCena() + " zlaťáků, a ty budeš moct odejít s brněním, ve kterém budeš téměř\n" +
"neporazitelný!";
        prodavacovyZpravy[2] =
"Ahoj. Jak sis určitě všiml, jsem prodavač brnění. Předpokládám, že to ty\n" +
"jsi ten hrdina, který nás chce osvobodit od nadvlády zlého draka Bucifala.\n" +
"Mám u sebe přesně to, co při souboji s drakem potřebuješ - kovové brnění,\n" +
"které pohltí až " + kovoveBrneni.getBrneni()*100 + "% útočné síly tvých protivníků. Není to ale zadarmo, bude\n"+
"tě to stát " + kovoveBrneni.getCena() + " zlaťáků. Slyšel jsem, že trpaslík Šmudla má u sebe vždy\n" +
"peněz víc než dost. Ale pozor, je to známý škrt. Jen tak ti své peníze\n" +
"určitě chtít dát nebude.";
        prodavacovyZpravy[3] =
"Budeš-li si chtít kovové brnění koupit, použij příkaz >kup kovove_brneni<.\n" +
"Já ti pak brnění nechám výměnou za " + kovoveBrneni.getCena() + " zlaťáků.";
        prodavacovyZpravy[4] =
"Děkuji za nákup brnění. Stálo tě hodně peněz, a všemi milovaného hodného\n" +
"trpaslíka Šmudlu to stálo život. Styď se. Kéž by tě drak Bucifal roztrhal\n" +
"na kousky.";
        prodavac.setZpravy(prodavacovyZpravy);
        
        // Tvorba vedlejších úkolů, které hráč může ve hře splnit.
        UkolVrazda trpaslikuvUkol = new UkolVrazda(trpaslik, medved, nuz, true,
                kozeneBrneni, mec);
        trpaslikuvUkol.setOdmena(dveSteZlataku);
        UkolNakup prodavacuvUkol = new UkolNakup(prodavac, kovoveBrneni, 400,
                false);
        seznamUkolu.add(trpaslikuvUkol);
        seznamUkolu.add(prodavacuvUkol);
    }
    
    /**
     * Metoda vrací odkaz na aktuální prostor, ve kterém se hráč právě nachází.
     * 
     * @return aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi
     * prostory.
     * 
     * @param prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    
    /**
     * Tato metoda určuje jestli hráč vyhrál.
     * 
     * @return true nebo false na základě toho, jestli je drak mrtvý nebo ne
     */
    public boolean vyhra() {
        return drak.getZdravi() <= 0;
    }
    
    /**
     * Metoda, která vrací odkaz na postavu hráče.
     * 
     * @return odkaz na postavu hráče
     */
    public Postava getHrac() {
        return hrac;
    }
    
    /**
     * Metoda, která vrací odkaz na seznam úkolů.
     * 
     * @return seznam úkolů
     */
    public Set<Ukol> getSeznamUkolu() {
        return seznamUkolu;
    }
}
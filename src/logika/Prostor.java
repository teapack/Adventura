package logika;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * "Prostor" reprezentuje jedno místo (místnost, prostor,...) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 * 
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Vratislav Jindra
 * @version pro školní rok 2015/2016 LS
 */
public class Prostor {
    private String nazev;
    private String popis;
    private Set<Prostor> vychody; // Obsahuje sousední místnosti.
    // Seznam věcí bude reprezentován ArrayListem (viz konstruktor), a ne
    // HashMapou, protože chci mít ve hře povolené "duplikáty". Například
    // 100 zlaťáků může být na zemi dvakrát, a jsou to prakticky stejné věci;
    // a je nesmysl je nazývat různě jen proto, abych je mohl do HashMapy vložit
    // s různými klíči - radši je tedy nechám pojmenované stejně (vždy to jsou
    // věci s názvem 100_zlataku), a vložím je do ArrayListu, kterému nevadí
    // stejné záznamy. Příklad ze skutečného světa jsou například dvě stokoruny,
    // které můžou ležet na zemi, a jsou prakticky totožné - liší se možná
    // nějakým výrobním číslem, ale jejich název, totiž to, přes co k nim můžu
    // přistupovat, je vždy stejný - stokoruna.
    private List<Vec> veci;
    // Seznam postav už může být reprezentovaný HashMapou, protože
    private Map<String,Postava> postavy;
    // Jestli je prostor (respektive vchod do tohoto prostoru) viditelný, nebo
    // ne.
    private boolean viditelnost;
    
    /**
     * Vytvoření prostoru se zadaným popisem, např. "pláž", "loď", "les s vlkem"
     * 
     * @param nazev název prostoru, jednoznačný identifikátor, jedno slovo nebo
     *              víceslovný název bez mezer
     * @param popis popis prostoru
     * @param viditelnost jestli je prostor (respektive vchod do tohoto
     *                    prostoru) viditelný, nebo ne
     */
    public Prostor(String nazev, String popis, boolean viditelnost) {
        this.nazev = nazev;
        this.popis = popis;
        this.viditelnost = viditelnost;
        vychody = new HashSet<>();
        veci = new ArrayList<>();
        postavy = new HashMap<>();
    }
    
    /**
     * Definuje východ z prostoru (sousední/vedlejší prostor).
     * 
     * Vzhledem k tomu, že je použit Set pro uložení východů, může být sousední
     * prostor uveden pouze jednou (tj. nelze mít dvoje dveře do stejné sousední
     * místnosti). Druhé zadání stejného prostoru tiše přepíše předchozí zadání
     * (neobjeví se žádné chybové hlášení). Lze zadat též cestu z prostoru do
     * sebe sama.
     * 
     * @param vedlejsi prostor, který sousedí s aktualním prostorem
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }
    
    /**
     * Metoda equals pro porovnání dvou prostorů.
     * 
     * Překrývá se metoda equals ze třídy Object. Dva prostory jsou shodné,
     * pokud mají stejný název. Tato metoda je důležitá z hlediska správného
     * fungování seznamu východů (Set).
     * 
     * Bližší popis metody equals je u třídy Object.
     * 
     * @param o objekt, který se má porovnávat s aktuálním
     * @return true, pokud má zadaný prostor stejný název; jinak vrací false
     */
    @Override
    public boolean equals(Object o) {
        // Porovnáváme zda se nejedná o dva odkazy na stejnou instanci.
        if (this == o) {
            return true;
        }
        // Porovnáváme jakého typu je parametr.
        if (!(o instanceof Prostor)) {
            return false; // Pokud parametr není typu Prostor, vrátíme false.
        }
        // Přetypujeme parametr na typ Prostor .
        Prostor druhy = (Prostor) o;
        // Metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        // Vrátí true pro stejné názvy i v případě, že jsou oba názvy null,
        // jinak vrátí false.
        return (java.util.Objects.equals(this.nazev, druhy.nazev));
    }
    
    /**
     * Metoda hashCode vrací číselný identifikátor instance, který se používá
     * pro optimalizaci ukladání v dynamických datových strukturách.
     * 
     * Při překrytí metody equals je potřeba překrýt i metodu hashCode. Podrobný
     * popis pravidel pro vytváření metody hashCode je u metody hashCode ve
     * třídě Object.
     * 
     * @return vysledek výsledek spočítaný metodou hashCode()
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
    
    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru).
     * 
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;
    }
    
    /**
     * Vrací "dlouhý" popis prostoru.
     * 
     * Tento popis může vypadat následovně: Jsi v prostoru vstupní hala budovy
     * VŠE na Jižním měste. Východy: chodba bufet ucebna
     * 
     * @return dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        for (Prostor sousedni : vychody) {
            if (!sousedni.isViditelnost()) {
                // Je-li aspoň jeden ze sousedních prostorů neviditelný
                // (= zablokovaný), vypíše se zpráva, která obsahuje i seznam
                // zablokovaných východů.
                return "Jsi v prostoru " + popis + ".\n"
                        + popisVychodu() + "\n"
                        + popisZablokovanychVychodu() + "\n"
                        + popisVeci() + "\n"
                        + popisPostav();
            }
        }
        // Není-li žádný ze sousedních prostorů neviditelný (= zablokovaný),
        // vrácená zpráva neobsahuje seznam zablokovaných východů.
        return "Jsi v prostoru " + popis + ".\n"
                + popisVychodu() + "\n"
                + popisVeci() + "\n"
                + popisPostav();
    }
    
    /**
     * Vrací textový řetězec, který popisuje sousední východy.
     * 
     * Například: "Východy: hala ".
     * 
     * @return popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "Východy:";
        for (Prostor sousedni : vychody) {
            if (sousedni.isViditelnost()) {
                vracenyText += " " + sousedni.getNazev();
            }
        }
        return vracenyText;
    }
    
    /**
     * Vrací textový řetězec, který popisuje zablokované sousední východy.
     * 
     * Například "Zablokované východy: mytina ".
     * 
     * @return popis zablokovaných východů - názvů sousedních prostorů
     */
    private String popisZablokovanychVychodu() {
        String vracenyText = "Zablokované východy ";
        for (String jmeno : postavy.keySet()) {
            vracenyText += "(" +jmeno + " ti blokuje cestu dál):";
        }
        for (Prostor sousedni : vychody) {
            if (!sousedni.isViditelnost()) {
                vracenyText += " " + sousedni.getNazev();
            }
        }
        return vracenyText;
    }
    
    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr.
     * 
     * Pokud prostor s udaným jménem nesousedí s aktuálním prostorem, vrací se
     * hodnota null.
     * 
     * @param nazevSouseda jméno sousedního prostoru (východu)
     * @return prostor, který se nachází za příslušným východem, nebo hodnota
     *         null, pokud prostor zadaného jména není sousedem
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor> hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if (hledaneProstory.isEmpty()){
            return null;
        } else {
            return hledaneProstory.get(0);
        }
    }
    
    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * 
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     * 
     * @return nemodifikovatelná kolekce prostorů (východů), se kterými tento
     *         prostor sousedí
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    /**
     * Metoda pro vkládání věcí do prostoru.
     * 
     * @param vec vkládaná věc
     */
    public void vlozVec(Vec vec) {
        veci.add(vec);
        //seznamVeci.put(vec.getNazev(), vec);
    }
    
    /**
     * Metoda pro odebírání věcí z prostoru.
     * 
     * @param nazev název věci, kterou chci z prosotru odebrat
     * @return odebíraná věc (případně null, pokud věc v prostoru neexistuje)
     */
    public Vec odeberVec(String nazev) {
        Optional<Vec> vracenaVec = veci
                .stream()
                .filter(vec -> vec.getNazev().equals(nazev))
                .findFirst();
        if (vracenaVec.isPresent() && veci.remove(vracenaVec.get())) {
            return vracenaVec.get();
        } else {
            return null;
        }
    }
    
    /**
     * Tato metoda vypíše seznam věcí v daném prostoru.
     * 
     * Na tuto metodu nemusím psát test. Když věc není hned vidět, je třeba k ní
     * přidat i atribut viditelnosti. V tomto porovnávání bych pak musel
     * zjistit, jestli je viditelná nebo jestli ne.
     * 
     * @return seznam věcí v daném prostoru
     */
    public String popisVeci() {
        String vracenyText = "Věci:   ";
        boolean jsouTamVeci = false;
        for (Vec v : veci) {
            if (v.isViditelnost()) {
                vracenyText += " " + v.getNazev();
                jsouTamVeci = true;
            }
        }
        if (jsouTamVeci == false) {
            return "Žádné věci zde nejsou.";
        }
        return vracenyText;
    }
    
    /**
     * Metoda pro vkládání postav do prostoru.
     * 
     * @param postava vkládaná postava
     */
    public void vlozPostavu(Postava postava) {
        postavy.put(postava.getJmeno(), postava);
    }
    
    /**
     * Metoda pro odebírání postav z prostoru.
     * 
     * @param jmeno jméno postavy, kterou chci z prosotru odebrat
     * @return odebíraná postava (případně null, pokud postava neexistuje)
     */
    public Postava odeberPostavu(String jmeno) {
        return postavy.remove(jmeno);
    }
    
    /**
     * Tato metoda vypíše seznam postav v daném prostoru.
     * 
     * Na tuto metodu nemusím psát test.
     * 
     * @return seznam postav v daném prostoru
     */
    private String popisPostav() {
        String vracenyText = "Postavy:";
        for (String jmeno : postavy.keySet()) {
            vracenyText += " " + jmeno;
        }
        return vracenyText;
    }
    
    /**
     * Getter na postavy.
     * 
     * @return postavy v daném prostoru
     */
    public Map<String, Postava> getPostavy() {
        return postavy;
    }
    
    /**
     * Metoda pro zviditelnění neviditelných věcí.
     */
    public void zviditelniNeviditelneVeci() {
        veci.stream().forEach(vec -> vec.setViditelnost(true));
    }
    
    /**
     * Metoda pro zviditelnění neviditelných východů.
     */
    public void zviditelniNeviditelneVychody() {
        vychody.stream().forEach(prostor -> prostor.setViditelnost(true));
    }
    
    /**
     * Metoda pro nastavení viditelnosti prostoru (vchodu do tohoto prostoru).
     * 
     * @param viditelnost nastavovaná viditelnost prostoru
     */
    public void setViditelnost(boolean viditelnost) {
        this.viditelnost = viditelnost;
    }
    
    /**
     * Metoda pro zjištění, zda je daný prostor (vchod do tohoto prostoru)
     * viditelný, nebo ne.
     * 
     * @return viditelnost vrací true, když je vchod do tohoto prostoru
     *                     viditelný, jinak false
     */
    public boolean isViditelnost() {
        return viditelnost;
    }
}
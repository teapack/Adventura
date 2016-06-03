package logika;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Třída, která reprezentuje hráčův inventář.
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author Vratislav Jindra
 * @version 20160504
 */
public class Inventar {
    private List<Vec> seznamVeci;
    private List<Klic> seznamKlicu;
    private List<Penize> seznamPenez;
    private int hodnotaPenez;
    private double hmotnost;
    private final double MAX_HMOTNOST = 45;
    
    /**
     * Konstruktor, který vytváří instance inventářů.
     * 
     * Inventář  je seznam věcí, které má postava u sebe. Tento seznam je
     * reprezentovaný ArrayListem.
     */
    public Inventar() {
        this.seznamVeci = new ArrayList<>();
        this.seznamKlicu = new ArrayList<>();
        this.seznamPenez = new ArrayList<>();
    }
    
    /**
     * Metoda pro vkládání věcí do inventáře.
     * 
     * @param vec věc, která se má vložit do inventáře
     * @return true, když se vkládání věci do inventáře povedlo, jinak false
     */
    public boolean vlozVec(Vec vec) {
        return seznamVeci.add(vec);
    }
    
    /**
     * Metoda pro odebírání věcí z inventáře. Tato metoda zároveň odebere
     * hmotnost věci z celkové hmotnosti iventáře.
     * 
     * @param vec věc, která se má odebrat z inventáře
     * @return true, když se odebírání věci z inventáře povedlo, jinak false
     */
    public boolean odeberVec(Vec vec) {
        return seznamVeci.remove(vec);
    }
    
    /**
     * Metoda, která vrací seznam všech věcí v inventáři.
     * 
     * @return seznam věcí v inventáři
     */
    public Collection<Vec> getSeznamVeci() {
        return seznamVeci;
    }
    
    /**
     * Metoda, která vrací seznam klíčů v inventáři.
     * 
     * @return seznam klíčů v inventáři
     */
    public Collection<Klic> getSeznamKlicu() {
        seznamKlicu.clear();
        seznamVeci
                .stream()
                .filter(vec -> (vec instanceof Klic))
                .map(vec -> (Klic) vec)
                .forEach(klic -> seznamKlicu.add(klic));
        return seznamKlicu;
    }
    
    /**
     * Metoda, která vrací seznam "peněz" v inventáři.
     * 
     * @return seznam peněz v inventáři
     */
    public Collection<Penize> getSeznamPenez() {
        seznamPenez.clear();
        seznamVeci
                .stream()
                .filter(vec -> (vec instanceof Penize))
                .map (vec -> (Penize) vec)
                .forEach(penize -> seznamPenez.add(penize));
        return seznamPenez;
    }
    
    /**
     * Setter na nastavení hodnoty peněz v inventáři.
     * 
     * @param hodnotaPenez hodnota peněz v inventáři.
     */
    public void setHodnotaPenez(int hodnotaPenez) {
        this.hodnotaPenez = hodnotaPenez;
    }
    
    /**
     * Getter na hodnotu peněz v inventáři.
     * 
     * @return hodnota peněz v inventáři
     */
    public int getHodnotaPenez() {
        return hodnotaPenez;
    }
    
    /**
     * Metoda pro prohledávání inventáře.
     * 
     * @param nazev název hledané věci
     * @return hledaná věc (pokud hledaná věc neexistuje, vrací se null)
     */
    public Vec najdiVec(String nazev) {
        Optional<Vec> vraceno = seznamVeci
                .stream()
                .filter(vec -> vec.getNazev().equals(nazev))
                .findFirst();
        if (vraceno.isPresent()) {
            return vraceno.get();
        }
        return null;
    }
    
    /**
     * Metoda pro nastavení hmotnosti inventáře.
     * 
     * Volá se při braní, zahazování věcí, atd.
     * 
     * @param hmotnost nová hmotnost inventáře
     */
    public void setHmotnost(double hmotnost) {
        this.hmotnost = hmotnost;
    }
    
    /**
     * Metoda pro zjišťování součtu hmotností všech věcí v hráčově inventáři.
     * 
     * @return hmotnost celého inventáře
     */
    public double getHmotnost() {
        return hmotnost;
    }    
    
    /**
     * Getter na maximální hmotnost inventáře.
     * 
     * @return maximální hmotnost inventáře
     */
    public double getMAX_HMOTNOST() {
        return MAX_HMOTNOST;
    }
    
    /**
     * Metoda pro vypsání věcí v inventáři.
     * 
     * @return seznam věcí v inventáři
     */
    @Override
    public String toString() {
        // Musím naformátovat hmotnost, protože při sčítání desetinných čísel
        // typu double (hmotnosti jedotlivých věcí v inventáři) dochází
        // v důsledku reprezentace čísel v počítačí (floating point)
        // k zaokrouhlovací chybě.
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return "Seznam věcí v inventáři:\n " + seznamVeci
                + ".\nHmotnost inventáře: " + df.format(hmotnost)
                + "kg, maximální hmotnost: " + MAX_HMOTNOST
                + "kg.\n"
                + "Hodnota zlaťáků v inventáři: " + hodnotaPenez;
    }
}
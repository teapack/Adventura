package uiText;

import java.util.Scanner;
import logika.IHra;
import java.io.*;

/**
 * Class TextoveRozhrani vytváří instanci třídy Hra, která představuje logiku
 * aplikace.
 *
 * Toto je uživatelského rozhraní aplikace Adventura. Čte vstup zadaný
 * uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na
 * konzoli.
 * 
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Vratislav Jindra
 * @version pro školní rok 2015/2016
 */
public class TextoveRozhrani {
    private IHra hra;
    
    /**
     * Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }
    
    /**
     * Hlavní metoda hry, která spustí novou hru.
     * 
     * Vypíše úvodní text a pak opakuje čtení a zpracování příkazu od hráče do
     * konce hry (dokud metoda konecHry() z logiky nevrátí hodnotu true).
     * Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani());
        // Základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.
        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }
        System.out.println(hra.vratEpilog());
    }
    
    /**
     * Hlavní metoda hry, která spustí hru ze souboru.
     * 
     * Název souboru se zadá jako parametr při spouštění hry.
     * 
     * @param nazevSouboru název souboru, ze kterého se hra spouští
     */
    public void hrajZeSouboru(String nazevSouboru) {
        // Parametrem u try se řeší zavírání readeru.
        try (BufferedReader br = new BufferedReader(
                new FileReader(nazevSouboru))) {
            // Základní cyklus programu - opakovaně se čtou příkazy
            // a poté se provádejí do konce hry.
            System.out.println(hra.vratUvitani());
            String radek = br.readLine();
            while (!hra.konecHry() && radek != null) {
                // Ty hvězdičky tam jsou jen pro debugování (abych věděl,
                // co se mi načetlo ze souboru).
                System.out.println("*" + radek + "*");
                System.out.println(hra.zpracujPrikaz(radek));
                radek = br.readLine();
            }
            System.out.println(hra.vratEpilog());
        } catch (FileNotFoundException ex) {
            System.err.println("Soubor " + nazevSouboru + " nenalezen: " + ex);
        } catch (IOException ex) {
            System.err.println("Problém se vstupem: " + ex);
        }
    }
    
    /**
     * Metoda přečte příkaz z příkazového řádku.
     * 
     * @return přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("> ");
        return scanner.nextLine();
    }
}
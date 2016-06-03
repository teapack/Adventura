package main;

import logika.*;
import uiText.TextoveRozhrani;

/**
 * Třída Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám
 * a rozšiřování.
 *
 * @author Jarmila Pavlíčková, Vratislav Jindra
 * @version ZS 2015/2016
 */
public class Start {
    /**
     * Metoda, jejímž prostřednictvím se spouští celá aplikace.
     *
     * @param args parametry příkazového řádku
     */
    public static void main(String[] args) {
        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        if (args.length == 0) {
            ui.hraj();
        } else {
            ui.hrajZeSouboru(args[0]);
        }
    }
    
    /**
     * Pokud nechci, aby ve třídě byl konstruktor, musím ho (paradoxně)
     * vytvořit, a musí být privátní.
     */
    private Start() {
    }
}
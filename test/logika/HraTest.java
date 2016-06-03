package logika;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková, Vratislav Jindra
 * @version  pro školní rok 2015/2016
 */
public class HraTest {
    private Hra hra;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    // Datové atributy (statické i instancí)
    
    // Konstruktory a tovární metody
    // Testovací třída vystačí s prázdným implicitním konstruktorem
    
    // Příprava a úklid přípravku
    
    /**
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra = new Hra();
    }
    
    /**
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }
    
    // Soukromé metody používané v testovacích metodách
    
    // Vlastní testovací metody
    
    /**
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     * Snad to nemusím přehánět a nemusím testovat každou věc a každou postavu.
     * Při tvorbě rozsáhlejšího programu je přece nesmysl testovat úplně
     * všechno. Například ani nevidím důvod, proč bych měl testovat, jestli hra
     * skončila, když přejdu do jiného prostoru - vzhledem k tomu, že jako
     * podmínku výhry mám zdraví draka, a ne to, že má hráč být v nějakém
     * prostoru, je trochu zbytečné testovat výhru po přejití do každé
     * místnosti.
     */
    @Test
    public void testPrubehHry() {
        assertEquals("plaz", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
        hra.zpracujPrikaz("jdi lod");
        assertFalse(hra.konecHry());
        assertEquals("lod", hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("seber nuz");
        assertEquals(0, hra.getHerniPlan().getHrac().getInventar()
                .getSeznamVeci().size());
        hra.getHerniPlan().getAktualniProstor().zviditelniNeviditelneVeci();
        hra.zpracujPrikaz("seber nuz");
        assertEquals(1, hra.getHerniPlan().getHrac().getInventar()
                .getSeznamVeci().size());
        hra.zpracujPrikaz("jdi plaz");
        assertFalse(hra.konecHry());
        assertEquals("plaz", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
        hra.zpracujPrikaz("jdi les");
        assertFalse(hra.konecHry());
        assertEquals("les", hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi mytina");
        assertFalse(hra.konecHry());
        assertEquals("les", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
        hra.getHerniPlan().getAktualniProstor().zviditelniNeviditelneVychody();
        hra.zpracujPrikaz("jdi mytina");
        assertFalse(hra.konecHry());
        assertEquals("mytina", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
        hra.zpracujPrikaz("konec");
        assertEquals(true, hra.konecHry());
    }
    
    /**
     * Test of vratUvitani method, of class Hra.
     */
    @Test
    public void testVratUvitani() {
        System.out.println("vratUvitani");
        Hra instance = new Hra();
        String expResult = "Vítej!\n" +
                "Toto je příběh o zemi sužované zlým drakem Bucifalem.\n" +
                "Napiš 'napoveda', pokud si nevíš rady, jak hrát dál.\n" +
                "\n" +
                hra.getHerniPlan().getAktualniProstor().dlouhyPopis();
        String result = instance.vratUvitani();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of vratEpilog method, of class Hra.
     */
    @Test
    public void testVratEpilog() {
        System.out.println("vratEpilog");
        Hra instance = new Hra();
        String expResult = "Hra byla ukončena příkazem konec.";
        String result = instance.vratEpilog();
        assertEquals(expResult, result);
        instance.zpracujPrikaz("jdi les");
        instance.getHerniPlan().getAktualniProstor()
                .zviditelniNeviditelneVychody();
        instance.zpracujPrikaz("jdi mytina");
        instance.zpracujPrikaz("jdi louka");
        instance.getHerniPlan().getAktualniProstor()
                .zviditelniNeviditelneVychody();
        instance.zpracujPrikaz("jdi jeskyne");
        instance.getHerniPlan().getHrac().setZdravi(500000);
        instance.getHerniPlan().getHrac().setSila(500000);
        expResult = "Vyhrál jsi, gratuluji!";
        assertNotEquals(expResult, result);
        instance.zpracujPrikaz("utok drak");
        result = instance.vratEpilog();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of konecHry method, of class Hra.
     */
    @Test
    public void testKonecHry() {
        System.out.println("konecHry");
        Hra instance = new Hra();
        boolean result = instance.konecHry();
        assertFalse(result);
        instance.zpracujPrikaz("konec");
        result = instance.konecHry();
        assertTrue(result);
    }
    
    /**
     * Test of zpracujPrikaz method, of class Hra.
     */
    @Test
    public void testZpracujPrikaz() {
        System.out.println("zpracujPrikaz");
        String radek = "";
        Hra instance = new Hra();
        String expResult = "Nevím co tím myslíš. Tento příkaz neznám.";
        String result = instance.zpracujPrikaz(radek);
        assertEquals(expResult, result);
        result = instance.zpracujPrikaz("seber klic");
        expResult = "Sebrána věc klic.";
        assertEquals(expResult, result);
        assertNotEquals("Sebrána věc klic....", result);
    }
    
    /**
     * Test of setKonecHry method, of class Hra.
     */
    @Test
    public void testSetKonecHry() {
        System.out.println("setKonecHry");
        boolean konecHry = false;
        Hra instance = new Hra();
        assertFalse(instance.konecHry());
        instance.setKonecHry(true);
        assertTrue(instance.konecHry());
    }
    
    /**
     * Test of getHerniPlan method, of class Hra.
     */
    @Test
    public void testGetHerniPlan() {
        System.out.println("getHerniPlan");
        Hra instance = new Hra();
        HerniPlan result = instance.getHerniPlan();
        assertNotNull(result);
    }
}
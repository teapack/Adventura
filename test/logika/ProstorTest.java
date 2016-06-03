package logika;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 * 
 * @author    Jarmila Pavlíčková, Vratislav Jindra
 * @version   pro skolní rok 2015/2016
 */
public class ProstorTest {
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
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public void testLzeProjit() {		
        Prostor prostor1 = new Prostor("hala",
                "vstupní hala budovy VŠE na Jižním městě", true);
        Prostor prostor2 = new Prostor("bufet",
                "bufet, kam si můžete zajít na svačinku", true);
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }
    
    /**
     * Test of vlozVec method, of class Prostor.
     */
    @Test
    public void testVlozVec() {
        logika.Prostor prostor1 = new logika.Prostor(null, null, true);
        logika.Vec vec1 = new logika.Vec("a", 0.1, true, true);
        logika.Vec vec2 = new logika.Vec("b", 0.2, true, true);
        assertNotNull(prostor1);
        prostor1.vlozVec(vec1);
        assertEquals("Věci:    a", prostor1.popisVeci());
        prostor1.vlozVec(vec1);
        prostor1.vlozVec(vec2);
        assertEquals("Věci:    a a b", prostor1.popisVeci());
    }
    
    /**
     * Test of setVychod method, of class Prostor.
     */
    @Test
    public void testSetVychod() {
        System.out.println("setVychod");
        logika.Prostor prostor1 = new logika.Prostor("plaz", "a", true);
        logika.Prostor prostor2 = new logika.Prostor("les", "b", true);
        prostor1.setVychod(prostor2);
        assertEquals(prostor1.getNazev(),
                hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi les");
        assertNotEquals(prostor1.getNazev(),
                hra.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(prostor2.getNazev(),
                hra.getHerniPlan().getAktualniProstor().getNazev());
    }
    
    /**
     * Test of getNazev method, of class Prostor.
     */
    @Test
    public void testGetNazev() {
        System.out.println("getNazev");
        Prostor instance = new Prostor("a", null, true);
        String expResult = "a";
        String result = instance.getNazev();
        assertNotEquals("b", result);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of dlouhyPopis method, of class Prostor.
     */
    @Test
    public void testDlouhyPopis() {
        System.out.println("dlouhyPopis");
        String expResult = "Jsi v prostoru pláž, na které ses vylodil.\n"
                + "Východy: lod\n"
                + "Věci:    klic\n"
                + "Postavy:";
        Prostor prostor = new Prostor("plaz", "pláž, na které ses vylodil",
                true);
        Prostor prostor2 = new Prostor("lod", null, true);
        Vec v = new Vec("klic", 0, true, true);
        prostor.setVychod(prostor2);
        prostor.vlozVec(v);
        String result = prostor.dlouhyPopis();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of vratSousedniProstor method, of class Prostor.
     */
    @Test
    public void testVratSousedniProstor() {
        System.out.println("vratSousedniProstor");
        Prostor prostor = new Prostor("a", null, true);
        Prostor expResult = new Prostor("b", null, true);
        prostor.setVychod(expResult);
        Prostor result = prostor.vratSousedniProstor("b");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getVychody method, of class Prostor.
     */
    @Test
    public void testGetVychody() {
        System.out.println("getVychody");
        Prostor a = new Prostor("a", null, true);
        Prostor b = new Prostor("b", null, true);
        Prostor c = new Prostor("c", null, true);
        Prostor d = new Prostor("d", null, false);
        Collection<Prostor> expResult = new HashSet<>();
        expResult.add(b);
        expResult.add(c);
        expResult.add(d);
        Collection<Prostor> result = new HashSet<>();
        assertNotNull(result);
        a.setVychod(b);
        a.setVychod(c);
        a.setVychod(d);
        result = a.getVychody();
        assertEquals(3, result.size());
    }
    
    /**
     * Test of odeberVec method, of class Prostor.
     */
    @Test
    public void testOdeberVec() {
        System.out.println("odeberVec");
        String nazev = "nejakaVec";
        Prostor prostor = new Prostor("a", null, true);
        Vec expResult = null;
        Vec result = prostor.odeberVec(nazev);
        assertNull(result);
        Vec vec = new Vec("nejakaVec", 0, true, true);
        prostor.vlozVec(vec);
        assertEquals(vec, prostor.odeberVec(nazev));
        assertNull(prostor.odeberVec(nazev));
    }
    
    /**
     * Test of popisVeci method, of class Prostor.
     */
    @Test
    public void testPopisVeci() {
        System.out.println("popisVeci");
        Prostor p = new Prostor("a", null, true);
        Vec v1 = new Vec("a", 0, true, true);
        Vec v2 = new Vec("b", 0, true, true);
        Vec v3 = new Vec("a", 0, true, true);
        String expResult = "Věci:   ";
        String result = p.popisVeci();
        assertEquals(expResult, result);
        p.vlozVec(v1);
        p.vlozVec(v2);
        p.vlozVec(v3);
        expResult = "Věci:    a b a";
        result = p.popisVeci();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of vlozPostavu method, of class Prostor.
     */
    @Test
    public void testVlozPostavu() {
        System.out.println("vlozPostavu");
        Postava postava = new Postava("a", 0, 0, 0);
        Prostor prostor = new Prostor("b", null, true);
        prostor.vlozPostavu(postava);
        prostor.vlozPostavu(postava);
        Postava p = new Postava("a", 1, 1, 1);
        prostor.vlozPostavu(p);
        assertEquals(1, prostor.getPostavy().size());
    }
    
    /**
     * Test of odeberPostavu method, of class Prostor.
     */
    @Test
    public void testOdeberPostavu() {
        System.out.println("odeberPostavu");
        String jmeno = "a";
        Postava postava = new Postava(jmeno, 0, 0, 0);
        Prostor prostor = new Prostor("b", null, true);
        prostor.vlozPostavu(postava);
        Postava result = prostor.odeberPostavu(jmeno);
        assertEquals(postava, result);
        assertNull(prostor.odeberPostavu(jmeno));
        assertNull(prostor.odeberPostavu("b"));
    }
    
    /**
     * Test of getPostavy method, of class Prostor.
     */
    @Test
    public void testGetPostavy() {
        System.out.println("getPostavy");
        Prostor instance = new Prostor("a", null, true);
        Postava p1 = new Postava("a", 0, 0, 0);
        Postava p2 = new Postava("b", 0, 0, 0);
        Postava p3 = new Postava("b", 1, 0, 0);
        Map<String, Postava> result = instance.getPostavy();
        assertEquals(0, result.size());
        instance.vlozPostavu(p3);
        instance.vlozPostavu(p2);
        instance.vlozPostavu(p1);
        result = instance.getPostavy();
        assertEquals(2, result.size());
    }
    
    /**
     * Test of zviditelniNeviditelneVeci method, of class Prostor.
     */
    @Test
    public void testZviditelniNeviditelneVeci() {
        System.out.println("zviditelniNeviditelneVeci");        
        Prostor p = new Prostor("a", null, true);
        Vec v1 = new Vec("a", 0, true, false);
        Vec v2 = new Vec("b", 0, true, false);
        Vec v3 = new Vec("a", 0, true, true);
        String expResult = "Věci:   ";
        String result = p.popisVeci();
        assertEquals(expResult, result);
        p.vlozVec(v1);
        p.vlozVec(v2);
        p.vlozVec(v3);
        expResult = "Věci:    a";
        result = p.popisVeci();
        assertEquals(expResult, result);
        p.zviditelniNeviditelneVeci();
        expResult = "Věci:    a b a";
        result = p.popisVeci();
        assertEquals(expResult, result);        
    }
    
    /**
     * Test of zviditelniNeviditelneVychody method, of class Prostor.
     */
    @Test
    public void testZviditelniNeviditelneVychody() {
        System.out.println("zviditelniNeviditelneVychody");
        hra.zpracujPrikaz("jdi les");
        hra.zpracujPrikaz("jdi mytina");
        assertEquals("les", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
        hra.getHerniPlan().getAktualniProstor().zviditelniNeviditelneVychody();
        hra.zpracujPrikaz("jdi mytina");
        assertEquals("mytina", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
        
    }
    
    /**
     * Test of setViditelnost method, of class Prostor.
     */
    @Test
    public void testSetViditelnost() {
        System.out.println("setViditelnost");
        assertEquals("plaz", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
        hra.zpracujPrikaz("jdi les");
        assertEquals("les", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
        hra.zpracujPrikaz("jdi plaz");
        assertEquals("plaz", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
        hra.getHerniPlan().getAktualniProstor().vratSousedniProstor("les")
                .setViditelnost(false);
        hra.zpracujPrikaz("jdi les");
        assertNotEquals("les", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
        assertEquals("plaz", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
    }
    
    /**
     * Test of isViditelnost method, of class Prostor.
     */
    @Test
    public void testIsViditelnost() {
        System.out.println("isViditelnost");
        Prostor p1 = new Prostor("a", null, true);
        Prostor p2 = new Prostor("a", null, false);
        assertTrue(p1.isViditelnost());
        assertFalse(p2.isViditelnost());
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testovací třída pro třídu Inventar.
 * 
 * Gettery, settery, equals, hashCode a toString metody (z velké části)
 * netestuji.
 * 
 * @author Vratislav Jindra
 * @version 20160522
 */
public class InventarTest {
    private Hra hra;
    
    public InventarTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        hra = new Hra();
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of vlozVec method, of class Inventar.
     */
    @Test
    public void testVlozVec() {
        System.out.println("vlozVec");
        Vec v1 = new Vec("a", 0, true, true);
        Vec v2 = new Vec("b", 0, true, true);
        Vec v3 = new Vec("c", 0, false, false);
        Vec v4 = new Vec("a", 0, true, true);
        Inventar instance = new Inventar();
        boolean expResult = true;
        boolean result = instance.vlozVec(v1);
        assertEquals(expResult, result);
        assertTrue(instance.vlozVec(v2));
        assertTrue(instance.vlozVec(v3));
        assertTrue(instance.vlozVec(v4));
        assertTrue(instance.vlozVec(v1));
        assertEquals(5, instance.getSeznamVeci().size());
    }
    
    /**
     * Test of odeberVec method, of class Inventar.
     */
    @Test
    public void testOdeberVec() {
        System.out.println("odeberVec");
        Inventar instance = new Inventar();
        Vec v1 = new Vec("a", 0, true, true);
        assertEquals(0, instance.getSeznamVeci().size());
        instance.vlozVec(v1);
        assertEquals(1, instance.getSeznamVeci().size());
        instance.odeberVec(v1);
        assertEquals(0, instance.getSeznamVeci().size());
    }
    
    /**
     * Test of getSeznamVeci method, of class Inventar.
     */
    @Test
    public void testGetSeznamVeci() {
        System.out.println("getSeznamVeci");
        Vec v1 = new Vec("a", 0, true, true);
        Vec v2 = new Vec("b", 0, true, true);
        Vec v3 = new Vec("c", 0, false, false);
        Vec v4 = new Vec("a", 0, true, true);
        Inventar instance = new Inventar();
        assertTrue(instance.vlozVec(v2));
        assertTrue(instance.vlozVec(v3));
        assertTrue(instance.vlozVec(v4));
        assertTrue(instance.vlozVec(v1));
        assertEquals(4, instance.getSeznamVeci().size());
    }
    
    /**
     * Test of getSeznamKlicu method, of class Inventar.
     */
    @Test
    public void testGetSeznamKlicu() {
        System.out.println("getSeznamKlicu");
        Vec v1 = new Vec("a", 0, true, true);
        Klic v2 = new Klic("b", 0, true, true, 1);
        Vec v3 = new Vec("c", 0, false, false);
        Klic v4 = new Klic("a", 0, true, true, 5);
        Inventar instance = new Inventar();
        assertTrue(instance.vlozVec(v2));
        assertTrue(instance.vlozVec(v3));
        assertTrue(instance.vlozVec(v4));
        assertTrue(instance.vlozVec(v1));
        assertEquals(2, instance.getSeznamKlicu().size());
    }
    
    /**
     * Test of getSeznamPenez method, of class Inventar.
     */
    @Test
    public void testGetSeznamPenez() {
        System.out.println("getSeznamPenez");
        Vec v1 = new Vec("a", 0, true, true);
        Penize v2 = new Penize("b", 0, true, true, 1);
        Vec v3 = new Vec("c", 0, false, false);
        Penize v4 = new Penize("a", 0, true, true, 5);
        Inventar instance = new Inventar();
        assertTrue(instance.vlozVec(v2));
        assertTrue(instance.vlozVec(v3));
        assertTrue(instance.vlozVec(v4));
        assertTrue(instance.vlozVec(v1));
        assertEquals(2, instance.getSeznamPenez().size());
    }
    
    /**
     * Test of setHodnotaPenez method, of class Inventar.
     */
    @Test
    public void testSetHodnotaPenez() {
        System.out.println("setHodnotaPenez");
        Penize v4 = new Penize("a", 0, true, true, 200);
        Inventar instance = new Inventar();
        instance.vlozVec(v4);
        assertEquals(0, instance.getHodnotaPenez());
        instance.setHodnotaPenez(200);
        assertEquals(200, instance.getHodnotaPenez());
    }
    
    /**
     * Test of getHodnotaPenez method, of class Inventar.
     */
    @Test
    public void testGetHodnotaPenez() {
        System.out.println("getHodnotaPenez");
        Penize v4 = new Penize("a", 0, true, true, 200);
        Inventar instance = new Inventar();
        instance.vlozVec(v4);
        assertEquals(0, instance.getHodnotaPenez());
        instance.setHodnotaPenez(200);
        assertEquals(200, instance.getHodnotaPenez());
    }
    
    /**
     * Test of najdiVec method, of class Inventar.
     */
    @Test
    public void testNajdiVec() {
        System.out.println("najdiVec");
        Vec v1 = new Vec("a", 0, true, true);
        Klic v2 = new Klic("b", 0, true, true, 1);
        Vec v3 = new Vec("c", 0, false, false);
        Klic v4 = new Klic("a", 0, true, true, 5);
        Inventar instance = new Inventar();
        assertTrue(instance.vlozVec(v2));
        assertTrue(instance.vlozVec(v3));
        assertTrue(instance.vlozVec(v4));
        assertTrue(instance.vlozVec(v1));
        assertEquals(v4, instance.najdiVec("a"));
        assertEquals(v2, instance.najdiVec("b"));
        assertNotEquals(v1, instance.najdiVec("a"));
    }
    
    /**
     * Test of setHmotnost method, of class Inventar.
     */
    @Test
    public void testSetHmotnost() {
        System.out.println("setHmotnost");
        Inventar instance = new Inventar();
        double result = instance.getHmotnost();
        double expResult = 0;
        double delta = 0.0001;
        assertEquals(expResult, result, delta);
        instance.setHmotnost(60);
        expResult = 60;
        result = instance.getHmotnost();
        assertEquals(expResult, result, delta);
    }
    
    /**
     * Test of getHmotnost method, of class Inventar.
     */
    @Test
    public void testGetHmotnost() {
        System.out.println("getHmotnost");
        Inventar instance = new Inventar();
        double result = instance.getHmotnost();
        double expResult = 0;
        double delta = 0.0001;
        assertEquals(expResult, result, delta);
        instance.setHmotnost(60);
        expResult = 60;
        result = instance.getHmotnost();
        assertEquals(expResult, result, delta);
    }
    
    /**
     * Test of getMAX_HMOTNOST method, of class Inventar.
     */
    @Test
    public void testGetMAX_HMOTNOST() {
        System.out.println("getMAX_HMOTNOST");
        assertEquals(45, hra.getHerniPlan().getHrac().getInventar()
                .getMAX_HMOTNOST(), 0.0001);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testovací třída pro třídu HerniPlan.
 * 
 * Gettery, settery, equals, hashCode a toString metody (z velké části)
 * netestuji.
 * 
 * @author Vratislav Jindra
 * @version 20160522
 */
public class HerniPlanTest {
    private Hra hra;
    
    public HerniPlanTest() {
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
     * Test of getAktualniProstor method, of class HerniPlan.
     */
    @Test
    public void testGetAktualniProstor() {
        System.out.println("getAktualniProstor");
        assertNotNull(hra.getHerniPlan().getAktualniProstor());
        assertEquals("plaz", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
    }
    
    /**
     * Test of setAktualniProstor method, of class HerniPlan.
     */
    @Test
    public void testSetAktualniProstor() {
        System.out.println("setAktualniProstor");
        assertEquals("plaz", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
        Prostor p = new Prostor("a", null, true);
        hra.getHerniPlan().setAktualniProstor(p);
        assertEquals("a", hra.getHerniPlan().getAktualniProstor()
                .getNazev());
        assertEquals(p, hra.getHerniPlan().getAktualniProstor());
    }
    
    /**
     * Test of vyhra method, of class HerniPlan.
     */
    @Test
    public void testVyhra() {
        System.out.println("vyhra");
        assertFalse(hra.getHerniPlan().vyhra());
    }
    
    /**
     * Test of getHrac method, of class HerniPlan.
     */
    @Test
    public void testGetHrac() {
        System.out.println("getHrac");
        HerniPlan instance = new HerniPlan();
        Postava expResult = new Postava("hrac", 20, 1, 0);
        instance.getAktualniProstor().vlozPostavu(expResult);
        Postava result = instance.getHrac();
        assertEquals(expResult, result);
        assertEquals(expResult, hra.getHerniPlan().getHrac());
    }
    
    /**
     * Test of getSeznamUkolu method, of class HerniPlan.
     */
    @Test
    public void testGetSeznamUkolu() {
        System.out.println("getSeznamUkolu");
        assertEquals(2, hra.getHerniPlan().getSeznamUkolu().size());
    }
}
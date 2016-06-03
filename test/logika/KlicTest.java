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
 * Testovací třída pro třídu Klic.
 * 
 * Gettery, settery, equals, hashCode a toString metody (z velké části)
 * netestuji.
 * 
 * @author Vratislav Jindra
 * @version 20160522
 */
public class KlicTest {
    private Hra hra;
    
    public KlicTest() {
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
     * Test of getId method, of class Klic.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Klic k = (Klic) hra.getHerniPlan().getAktualniProstor()
                .odeberVec("klic");
        assertEquals(1, k.getId(), 0.1);
    }
}
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
 * Testovací třída pro třídu OdemykatelnaVec.
 * 
 * Gettery, settery, equals, hashCode a toString metody (z velké části)
 * netestuji.
 * 
 * @author Vratislav Jindra
 * @version 20160522
 */
public class OdemykatelnaVecTest {
    private Hra hra;
    
    public OdemykatelnaVecTest() {
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
     * Test of getId method, of class OdemykatelnaVec.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        hra.zpracujPrikaz("jdi lod");
        OdemykatelnaVec ov = (OdemykatelnaVec) hra.getHerniPlan()
                .getAktualniProstor().odeberVec("truhla");
        assertEquals(1, ov.getId(), 0.1);
    }
}
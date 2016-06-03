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
 * Testovací třída pro třídu MluviciPostava.
 * 
 * Gettery, settery, equals, hashCode a toString metody (z velké části)
 * netestuji.
 * 
 * @author Vratislav Jindra
 * @version 20160522
 */
public class MluviciPostavaTest {
    private Hra hra;
    
    public MluviciPostavaTest() {
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
     * Test of getZprava method, of class MluviciPostava.
     */
    @Test
    public void testGetZprava() {
        System.out.println("getZprava");
        HerniPlan herniPlan = hra.getHerniPlan();
        String[] zpravy = new String[5];
        zpravy[0] = "ukolZadan = false, ukolSplnen = true, pozadovaneVeci = XXXX, odmenaVyplacena = XXXX"
                + "nebo ukolZadan = true, ukolSplnen = true, pozadovaneVeci = XXXX, odmenaVyplacena = true";
        zpravy[1] = "ukolZadan = false, ukolSplnen = false, pozadovaneVeci = true, odmenaVyplacena = false";
        zpravy[2] = "ukolZadan = false, ukolSplnen = false, pozadovaneVeci = false, odmenaVyplacena = false";
        zpravy[3] = "ukolZadan = true, ukolSplnen = false, pozadovaneVeci = XXXX, odmenaVyplacena = false";
        zpravy[4] = "ukolZadan = true, ukolSplnen = true, pozadovaneVeci = XXXX, odmenaVyplacena = false";
        boolean ukolZadan = false;
        boolean ukolSplnen = false;
        boolean pozadovaneVeci = false;
        boolean odmenaVyplacena = false;
        MluviciPostava mluviciPostava = new MluviciPostava("a", 0, 0, 0,
                herniPlan);
        mluviciPostava.setZpravy(zpravy);
        String expResult = zpravy[2];
        String result = mluviciPostava.getZprava(herniPlan, ukolZadan,
                ukolSplnen, pozadovaneVeci, odmenaVyplacena);
        assertEquals(expResult, result);
        
        pozadovaneVeci = true;
        expResult = zpravy[1];
        result = mluviciPostava.getZprava(herniPlan, ukolZadan, ukolSplnen,
                pozadovaneVeci, odmenaVyplacena);
        assertEquals(expResult, result);
        
        ukolZadan = true;
        expResult = zpravy[3];
        result = mluviciPostava.getZprava(herniPlan, ukolZadan, ukolSplnen,
                pozadovaneVeci, odmenaVyplacena);
        assertEquals(expResult, result);
        
        ukolSplnen = true;
        expResult = zpravy[4];
        result = mluviciPostava.getZprava(herniPlan, ukolZadan, ukolSplnen,
                pozadovaneVeci, odmenaVyplacena);
        assertEquals(expResult, result);
        
        odmenaVyplacena = true;
        expResult = zpravy[0];
        result = mluviciPostava.getZprava(herniPlan, ukolZadan, ukolSplnen,
                pozadovaneVeci, odmenaVyplacena);
        assertEquals(expResult, result);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testovací třída pro třídu Postava.
 * 
 * Gettery, settery, equals, hashCode a toString metody (z velké části)
 * netestuji.
 * 
 * @author Vratislav Jindra
 * @version 20160522
 */
public class PostavaTest {
    
    public PostavaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of dropInventar method, of class Postava.
     */
    @Test
    public void testDropInventar() {
        System.out.println("dropInventar");
        Postava instance = new Postava("a", 0, 0, 0);
        Vec vec = new Vec("b", 1, true, true);
        Klic klic = new Klic("c", 1, true, true, 1);
        instance.getInventar().vlozVec(vec);
        instance.getInventar().vlozVec(klic);
        instance.getInventar().vlozVec(vec);
        Collection<Vec> expResult = new ArrayList<>();
        expResult.add(vec);
        expResult.add(klic);
        expResult.add(vec);
        Collection<Vec> result = instance.dropInventar();
        assertEquals(expResult, result);
    }
}
package com.biblio_scientifique.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Dimension;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class NdarrayTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void arrangeNominalCase(){
        Ndarray test = Ndarray.arrange(15); 
        System.out.println(test);
        assertEquals(test.getSize(), 15);
        assertEquals(test.getNdim(), 1);
        assertEquals(test.getShape(), new Dimension(1, 15));

    }
}


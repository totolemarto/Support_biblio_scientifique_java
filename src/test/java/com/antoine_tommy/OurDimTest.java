package com.antoine_tommy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
class OurDimTest {

    @Test
    void testCOnstructSet() {
        OurDim shape = new OurDim(2, 3);
        assertEquals(2, shape.getNbRows());
        assertEquals(3, shape.getNbCols());
    }

    @Test
    void testCOnstructGet() {
        OurDim shape = new OurDim(2, 3);
        assertEquals(2, shape.getNbRows());
        assertEquals(3, shape.getNbCols());
        shape.setNbRows(1);
        shape.setNbCols(4);
        assertEquals(1, shape.getNbRows());
        assertEquals(4, shape.getNbCols());
    }

    @Test
    void testEquals(){
        OurDim shape = new OurDim(2, 3);
        assertFalse(shape.equals(null));
        assertFalse(shape.equals(Float.valueOf(3)));
        assertTrue(shape.equals(shape));
        OurDim other = new OurDim(1, 3);
        assertFalse(shape.equals(other));
        other.setNbRows(2);
        other.setNbCols(1);
        assertFalse(shape.equals(other));
        other.setNbCols(3);
        assertTrue(shape.equals(other));
    }
}

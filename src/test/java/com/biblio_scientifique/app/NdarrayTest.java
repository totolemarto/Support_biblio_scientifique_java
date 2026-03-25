package com.biblio_scientifique.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
class NdarrayTest {

    @Test
    void createsArrayWithExplicitConstructorValues() {
        Dimension shape = new Dimension(2, 3);
        Ndarray ndarray = new Ndarray(2, shape, 6);

        assertEquals(2, ndarray.getNdim());
        assertEquals(shape, ndarray.getShape());
        assertEquals(6, ndarray.getSize());
    }

    @Test
    void createsOneDimensionalArrayWithEmptyShapeAndNoSizeWhenUsingNdimConstructor() {
        Ndarray ndarray = new Ndarray(1);

        assertEquals(1, ndarray.getNdim());
        assertEquals(new Dimension(0, 0), ndarray.getShape());
        assertEquals(0, ndarray.getSize());
    }

    @Test
    void createsDefaultArrayWithZeroAndNullFields() {
        Ndarray ndarray = new Ndarray();

        assertEquals(0, ndarray.getNdim());
        assertEquals(0, ndarray.getSize());
        assertNull(ndarray.getShape());
    }

    @Test
    void updatesFieldsWithSettersIncludingNullShape() {
        Ndarray ndarray = new Ndarray();

        ndarray.setNdim(3);
        ndarray.setSize(12);
        ndarray.setShape(null);

        assertEquals(3, ndarray.getNdim());
        assertEquals(12, ndarray.getSize());
        assertNull(ndarray.getShape());
    }

    @Test
    void toStringContainsDataSectionWhenArrayIsCreatedWithNdimConstructor() {
        Ndarray ndarray = new Ndarray(1);

        assertTrue(ndarray.toString().contains("data=[]"));
    }

    @Test
    void toStringContainsNullDataForConstructorsWithoutDataInitialization() {
        Ndarray defaultArray = new Ndarray();
        Ndarray explicitArray = new Ndarray(2, new Dimension(1, 2), 2);

        assertTrue(defaultArray.toString().contains("data=null"));
        assertTrue(explicitArray.toString().contains("data=null"));
    }

    @Test 
    void arrayConstructorWithNull() {
        float parameters[] = {1, 2, 3};
        Ndarray my_array = Ndarray.array(parameters);
        for (int i = 0; i < my_array.get_values(0, 0).size(); i++){
            assertEquals(parameters[i], my_array.get_values(0, 0).get(i));
        }
        assertEquals(3, my_array.get_values(0, 0).size());
    }
}


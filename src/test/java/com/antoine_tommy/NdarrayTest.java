package com.antoine_tommy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
class NdarrayTest {

    @Test
    void createsArrayWithExplicitConstructorValues() {
        OurDim shape = new OurDim(2, 3);
        Ndarray ndarray = new Ndarray(2, shape, 6);

        assertEquals(2, ndarray.getNdim());
        assertEquals(shape, ndarray.getShape());
        assertEquals(6, ndarray.getSize());
    }

    @Test
    void createsOneOurDimalArrayWithEmptyShapeAndNoSizeWhenUsingNdimConstructor() {
        Ndarray ndarray = new Ndarray(1);

        assertEquals(1, ndarray.getNdim());
        assertEquals(new OurDim(1, 0), ndarray.getShape());
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
        assertTrue(ndarray.toString().contains("data=[[]]"));
    }

    @Test
    void toStringContainsNullDataForConstructorsWithoutDataInitialization() {
        Ndarray defaultArray = new Ndarray();
        Ndarray explicitArray = new Ndarray(2, new OurDim(1, 2), 2);

        assertTrue(defaultArray.toString().contains("data=[]"));
        assertTrue(explicitArray.toString().contains("data=["));
    }

    @Test 
    void arrayConstructorWithNull() {
        float[] parameters = {1, 2, 3};
        Ndarray myArray = Ndarray.array(parameters);
        for (int i = 0; i < myArray.getValues(0, 0).size(); i++){
            assertEquals(parameters[i], myArray.getValues(0, 0).get(i));
        }
        assertEquals(3, myArray.getValues(0, 0).size());
    }

    @Test
    void zeroConstructor(){
        Ndarray tmp = Ndarray.zeros(new OurDim(0,0));
        assertEquals(0, tmp.getNdim());
        assertEquals(0, tmp.getSize());
        assertEquals(new OurDim(0, 0), tmp.getShape());
    }
}


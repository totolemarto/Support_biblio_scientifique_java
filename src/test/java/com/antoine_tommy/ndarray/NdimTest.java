package com.antoine_tommy.ndarray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NdimTest {

    @Test
    void createsNdimWithPositiveValue() {
        Ndim ndim = new Ndim(3);

        assertEquals(3, ndim.getDim());
    }

    @Test
    void createsDefaultNdimWithValueOne() {
        Ndim ndim = new Ndim();

        assertEquals(1, ndim.getDim());
    }

    @Test
    void throwsIllegalArgumentExceptionWhenNdimIsZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Ndim(0));

        assertTrue(exception.getMessage().contains("positive integer"));
    }

    @Test
    void throwsIllegalArgumentExceptionWhenNdimIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Ndim(-1));

        assertTrue(exception.getMessage().contains("positive integer"));
    }

    @Test
    void equalsAndHashCodeMatchForSameNdim() {
        Ndim ndim = new Ndim(2);

        assertEquals(ndim, ndim);
        assertEquals(ndim.hashCode(), ndim.hashCode());
        assertNotEquals(0, ndim.hashCode());
    }

    @Test
    void equalsAndHashCodeMatchForSameNdimValue() {
        Ndim first = new Ndim(2);
        Ndim second = new Ndim(2);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void hashCodeIsDifferentForDifferentNdimValues() {
        Ndim first = new Ndim(2);
        Ndim second = new Ndim(3);

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void equalsReturnsFalseForNullDifferentClassAndDifferentValue() {
        Ndim ndim = new Ndim(2);

        assertNotEquals(null, ndim);
        assertNotEquals("2", ndim);
        assertNotEquals(ndim, new Ndim(3));
    }

    @Test
    void toStringReturnsReadableRepresentationWithNdimValue() {
        Ndim ndim = new Ndim(4);

        assertEquals("Ndim{dim=4}", ndim.toString());
    }
}


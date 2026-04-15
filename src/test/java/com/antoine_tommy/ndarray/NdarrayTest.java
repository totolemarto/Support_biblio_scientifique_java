package com.antoine_tommy.ndarray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NdarrayTest {

    @Test
    void createsArrayWithDefaultDoubleTypeAndZeroValues() {
        Ndarray array = new Ndarray(new Shape(2, 2));

        assertEquals(new Ndim(2), array.getNdim());
        assertEquals(new Shape(2, 2), array.getShape());
        assertEquals(Dtype.DOUBLE, array.getDtype());
        assertEquals(4, array.size());
        assertArrayEquals(new Object[]{0.0d, 0.0d, 0.0d, 0.0d}, array.toFlatArray());
    }

    @Test
    void createsArrayFromFlatDataAndConvertsValuesToRequestedType() {
        Ndarray array = new Ndarray(new Object[]{1, 2.2, "3"}, new Shape(3), Dtype.INT);

        assertArrayEquals(new Object[]{1, 2, 3}, array.toFlatArray());
    }

    @Test
    void constructorThrowsWhenDataLengthDoesNotMatchShapeSize() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Ndarray(new Object[]{1, 2, 3}, new Shape(2, 2), Dtype.INT)
        );

        assertTrue(exception.getMessage().contains("Data length does not match shape size"));
    }

    @Test
    void getAndSetUseRowMajorIndexing() {
        Ndarray array = new Ndarray(new Shape(2, 3), Dtype.INT);

        array.set(5, 0, 1);
        array.set(8, 1, 2);

        assertEquals(5, array.get(0, 1));
        assertEquals(8, array.get(1, 2));
        assertArrayEquals(new Object[]{0, 5, 0, 0, 0, 8}, array.toFlatArray());
    }

    @Test
    void setThrowsWhenValueCannotBeConvertedToDtype() {
        Ndarray array = new Ndarray(new Shape(2), Dtype.INT);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> array.set("abc", 0));

        assertTrue(exception.getMessage().contains("Cannot convert value"));
    }

    @Test
    void indexingThrowsForWrongNumberOfIndicesAndOutOfBounds() {
        Ndarray array = new Ndarray(new Shape(2, 2), Dtype.DOUBLE);

        assertThrows(IllegalArgumentException.class, () -> array.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(2, 0));
    }

    @Test
    void reshapeReturnsNewArrayWithSameDataAndDifferentShape() {
        Ndarray array = new Ndarray(new Object[]{1, 2, 3, 4}, new Shape(2, 2), Dtype.INT);

        Ndarray reshaped = array.reshape(new Shape(4, 1));

        assertEquals(new Shape(4, 1), reshaped.getShape());
        assertEquals(Dtype.INT, reshaped.getDtype());
        assertArrayEquals(new Object[]{1, 2, 3, 4}, reshaped.toFlatArray());
        assertNotEquals(array.getShape(), reshaped.getShape());
    }

    @Test
    void reshapeThrowsWhenRequestedShapeHasDifferentSize() {
        Ndarray array = new Ndarray(new Shape(2, 2), Dtype.FLOAT);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> array.reshape(new Shape(3, 2)));

        assertTrue(exception.getMessage().contains("Cannot reshape"));
    }

    @Test
    void astypeConvertsValuesAndReturnsNewArrayWithTargetType() {
        Ndarray source = new Ndarray(new Object[]{1, 0, 2}, new Shape(3), Dtype.INT);

        Ndarray converted = source.astype(Dtype.BOOLEAN);

        assertEquals(Dtype.BOOLEAN, converted.getDtype());
        assertArrayEquals(new Object[]{true, false, true}, converted.toFlatArray());
        assertEquals(Dtype.INT, source.getDtype());
        assertArrayEquals(new Object[]{1, 0, 2}, source.toFlatArray());
    }

    @Test
    void equalsHashCodeAndToStringReflectArrayContent() {
        Ndarray first = new Ndarray(new Object[]{1.0, 2.0}, new Shape(2), Dtype.DOUBLE);
        Ndarray second = new Ndarray(new Object[]{1.0, 2.0}, new Shape(2), Dtype.DOUBLE);
        Ndarray third = new Ndarray(new Object[]{1.0, 3.0}, new Shape(2), Dtype.DOUBLE);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, third);
        assertTrue(first.toString().contains("Ndarray{"));
    }
}


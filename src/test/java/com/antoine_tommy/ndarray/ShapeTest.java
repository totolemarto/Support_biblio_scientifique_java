package com.antoine_tommy.ndarray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShapeTest {

    @Test
    void createsShapeWithProvidedDimensions() {
        Shape shape = new Shape(3, 4, 5);

        assertArrayEquals(new int[]{3, 4, 5}, shape.getDimensions());
        assertThrows(IndexOutOfBoundsException.class, () -> shape.getDimension(-1));
        assertEquals(3, shape.getDimension(0));
        assertEquals(4, shape.getDimension(1));
        assertEquals(5, shape.getDimension(2));
        assertThrows(IndexOutOfBoundsException.class, () -> shape.getDimension(3));
    }

    @Test
    void createsDefaultShapeWithSingleDimensionOfOne() {
        Shape shape = new Shape();

        assertArrayEquals(new int[]{1}, shape.getDimensions());
        assertThrows(IndexOutOfBoundsException.class, () -> shape.getDimension(-1));
        assertEquals(1, shape.getDimension(0));
        assertThrows(IndexOutOfBoundsException.class, () -> shape.getDimension(1));
    }

    @Test
    void createsShapeWithEmptyVarargsThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Shape(new int[]{}));

        assertTrue(exception.getMessage().contains("At least one dimension must be specified"));
    }

    @Test
    void throwsIllegalArgumentExceptionWhenAnyDimensionIsZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Shape(2, 0, 4));

        assertTrue(exception.getMessage().contains("positive integers"));
    }

    @Test
    void throwsIllegalArgumentExceptionWhenAnyDimensionIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Shape(2, -1, 4));

        assertTrue(exception.getMessage().contains("positive integers"));
    }

    @Test
    void throwsIndexOutOfBoundsExceptionForNegativeDimensionIndex() {
        Shape shape = new Shape(2, 3);

        assertThrows(IndexOutOfBoundsException.class, () -> shape.getDimension(-1));
    }

    @Test
    void throwsIndexOutOfBoundsExceptionWhenDimensionIndexEqualsLength() {
        Shape shape = new Shape(2, 3);

        assertThrows(IndexOutOfBoundsException.class, () -> shape.getDimension(2));
    }

    @Test
    void keepsInternalDimensionsUnchangedWhenConstructorInputArrayIsMutated() {
        int[] source = {2, 3, 4};
        Shape shape = new Shape(source);

        source[1] = 99;

        assertArrayEquals(new int[]{2, 3, 4}, shape.getDimensions());
    }

    @Test
    void returnsDefensiveCopyFromGetDimensions() {
        Shape shape = new Shape(7, 8);
        int[] dimensions = shape.getDimensions();

        dimensions[0] = 99;

        assertArrayEquals(new int[]{7, 8}, shape.getDimensions());
    }

    @Test
    void equalsAndHashCodeMatchForSameDimensionsInSameOrder() {
        Shape first = new Shape(2, 3, 4);
        Shape second = new Shape(2, 3, 4);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(0, first.hashCode());
    }

    @Test
    void equalsReturnsFalseForNullDifferentClassDifferentLengthAndDifferentOrder() {
        Shape shape = new Shape(2, 3, 4);

        assertNotEquals(null, shape);
        assertNotEquals("[2, 3, 4]", shape);
        assertNotEquals(new Shape(2, 3), shape);
        assertNotEquals(new Shape(4, 3, 2), shape);
    }
}


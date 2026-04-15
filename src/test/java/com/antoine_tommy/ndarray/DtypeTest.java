package com.antoine_tommy.ndarray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DtypeTest {

    @Test
    void getSizeInBytesReturnsExpectedValueForEachSupportedType() {
        assertEquals(Integer.BYTES, Dtype.INT.getSizeInBytes());
        assertEquals(Float.BYTES, Dtype.FLOAT.getSizeInBytes());
        assertEquals(Double.BYTES, Dtype.DOUBLE.getSizeInBytes());
        assertEquals(1, Dtype.BOOLEAN.getSizeInBytes());
        assertEquals(-1, Dtype.STRING.getSizeInBytes());
    }

    @Test
    void toStringReturnsLowercaseTypeNames() {
        assertEquals("int", Dtype.INT.toString());
        assertEquals("float", Dtype.FLOAT.toString());
        assertEquals("double", Dtype.DOUBLE.toString());
        assertEquals("boolean", Dtype.BOOLEAN.toString());
        assertEquals("string", Dtype.STRING.toString());
    }

    @Test
    void fromStringParsesSupportedTypeNamesRegardlessOfCase() {
        assertEquals(Dtype.INT, Dtype.fromString("int"));
        assertEquals(Dtype.FLOAT, Dtype.fromString("FlOaT"));
        assertEquals(Dtype.DOUBLE, Dtype.fromString("DOUBLE"));
        assertEquals(Dtype.BOOLEAN, Dtype.fromString("Boolean"));
        assertEquals(Dtype.STRING, Dtype.fromString("string"));
    }

    @Test
    void fromStringThrowsIllegalArgumentExceptionForUnknownType() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Dtype.fromString("number"));

        assertTrue(exception.getMessage().contains("Unknown data type"));
    }

    @Test
    void fromStringThrowsNullPointerExceptionWhenInputIsNull() {
        assertThrows(NullPointerException.class, () -> Dtype.fromString(null));
    }
}

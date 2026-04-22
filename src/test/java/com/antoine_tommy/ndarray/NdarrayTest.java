package com.antoine_tommy.ndarray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

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
        Shape shape = new Shape(2, 2);
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Ndarray(new Object[]{1, 2, 3}, shape, Dtype.INT)
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

        Shape shape = new Shape(3, 2);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> array.reshape(shape));

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
        Ndarray fourth = new Ndarray(new Object[]{1.0, 2.0}, new Shape(2, 1), Dtype.DOUBLE);
        Ndarray fifth = new Ndarray(new Object[]{1.0}, new Shape(1, 1), Dtype.DOUBLE);

        assertEquals(first, second);
        assertEquals(first, first);
        assertNotEquals(first, new Object());
        assertNotEquals(first, fourth);
        assertNotEquals(first, fifth);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, third);
        assertTrue(first.toString().contains("Ndarray{"));
    }

    @Test
    void bigDimCreateOverflow(){
        Shape shape = new Shape((int) Math.pow(2, 31), (int) Math.pow(2, 31));
        assertThrows(IllegalArgumentException.class, () -> new Ndarray(shape, Dtype.INT));
    }

    @Test
    void testConstructDifferentTypeFloat() {
        Ndarray arrayFloat = new Ndarray(new Shape(2, 2), Dtype.FLOAT);

        arrayFloat.set(1F, 1, 1); 
        assertEquals(1F, arrayFloat.get(1, 1));

        // Weird cases
        arrayFloat.set(true, 1, 1); 
        arrayFloat.set(false, 1, 0); 
        assertEquals(1.0F, arrayFloat.get(1, 1));
        assertEquals(0.0F, arrayFloat.get(1, 0));

        arrayFloat.set("42", 1, 1); 
        assertEquals(42F, arrayFloat.get(1, 1));

        Object object = new Object();
        assertThrows(IllegalArgumentException.class, () -> arrayFloat.set(object, 1, 1));

        assertThrows(IllegalArgumentException.class, () -> arrayFloat.set("HEY", 1, 1)); 
        assertEquals(42F, arrayFloat.get(1, 1));

    }

    @Test
    void testConstructDifferentTypeInt() {
        Ndarray arrayInt = new Ndarray(new Shape(2, 2), Dtype.INT);

        arrayInt.set(1, 1, 1); 
        assertEquals( 1, arrayInt.get(1, 1));

        // Weird cases
        arrayInt.set(true, 1, 1); 
        arrayInt.set(false, 1, 0); 
        assertEquals(1, arrayInt.get(1, 1));
        assertEquals(0, arrayInt.get(1, 0));

        arrayInt.set("42", 1, 1); 
        assertEquals(42, arrayInt.get(1, 1));

        Object object = new Object();
        assertThrows(IllegalArgumentException.class, () -> arrayInt.set(object, 1, 1));

        assertThrows(IllegalArgumentException.class, () -> arrayInt.set("HEY", 1, 1)); 
        assertEquals(42, arrayInt.get(1, 1));

    }

    @Test
    void testConstructDifferentTypeDouble() {
        Ndarray arrayDouble = new Ndarray(new Shape(2, 2), Dtype.DOUBLE);

        arrayDouble.set(1F, 1, 1); 
        assertEquals(1d, arrayDouble.get(1, 1));

        // Weird cases
        arrayDouble.set(true, 1, 1); 
        arrayDouble.set(false, 1, 0); 
        assertEquals(1.0d, arrayDouble.get(1, 1));
        assertEquals(0.0d, arrayDouble.get(1, 0));

        arrayDouble.set("42", 1, 1); 
        assertEquals(42d, arrayDouble.get(1, 1));

        Object object = new Object();
        assertThrows(IllegalArgumentException.class, () -> arrayDouble.set(object, 1, 1));

        assertThrows(IllegalArgumentException.class, () -> arrayDouble.set("HEY", 1, 1)); 
        assertEquals(42d, arrayDouble.get(1, 1));

    }

    @Test
    void testConstructDifferentTypeString() {
        Ndarray arrayString = new Ndarray(new Shape(2, 2), Dtype.STRING);
        arrayString.set("HEY", 1, 1); 
        assertEquals("HEY", arrayString.get(1, 1));
    }


    @Test
    void testConstructDifferentTypeBool() {
        Ndarray arrayBool = new Ndarray(new Shape(2, 2), Dtype.BOOLEAN);
        arrayBool.set(true, 1, 1); 
        assertEquals(true, arrayBool.get(1, 1));
        arrayBool.set(false, 1, 1); 
        assertEquals(false, arrayBool.get(1, 1));

        arrayBool.set(1, 1, 1); 
        assertEquals(true, arrayBool.get(1, 1));
        arrayBool.set(0, 1, 1); 
        assertEquals(false, arrayBool.get(1, 1));

        arrayBool.set("true", 1, 1); 
        assertEquals(true, arrayBool.get(1, 1));
        arrayBool.set("false", 1, 1); 

        Object object = new Object();
        assertThrows(IllegalArgumentException.class, () -> arrayBool.set("HEY", 1, 1)); 
        assertThrows(IllegalArgumentException.class, () -> arrayBool.set(object, 1, 1));

    }

    @Test
    void getSizeInBytesReturnsExpectedValueForEachSupportedType() {
        Ndarray arrayInt= new Ndarray(new Shape(2, 2), Dtype.INT);
        assertEquals(Integer.BYTES, arrayInt.getItemSizeInBytes());
        Ndarray arrayFloat = new Ndarray(new Shape(2, 2), Dtype.FLOAT);
        assertEquals(Float.BYTES, arrayFloat.getItemSizeInBytes());
        Ndarray arrayDouble = new Ndarray(new Shape(2, 2), Dtype.DOUBLE);
        assertEquals(Double.BYTES, arrayDouble.getItemSizeInBytes());
        Ndarray arrayBool = new Ndarray(new Shape(2, 2), Dtype.BOOLEAN);
        assertEquals(1, arrayBool.getItemSizeInBytes());
        Ndarray arrayString= new Ndarray(new Shape(2, 2), Dtype.STRING);
        assertEquals(-1, arrayString.getItemSizeInBytes());
    }

    @Test 
    void testOperationAllTypesNumerical(){
        for (Dtype type : Dtype.values()){
            if (type.equals(Dtype.STRING) || type.equals(Dtype.BOOLEAN)){
                continue;
            }
            Ndarray arrayInt1 = new Ndarray(new Shape(1, 2), type);
            Ndarray arrayInt2 = new Ndarray(new Shape(1, 2), type);
            arrayInt1.set(2, 0, 0);
            arrayInt1.set(3, 0, 1);
            arrayInt2.set(2, 0, 0);
            arrayInt2.set(3, 0, 1);
            assertEquals(new Ndarray(new Object[]{4, 6}, new Shape(1, 2), type), arrayInt1.add(arrayInt2));

            assertEquals(new Ndarray(new Object[]{0, 0}, new Shape(1, 2), type), arrayInt1.subtract(arrayInt2));
            assertEquals(new Ndarray(new Object[]{4, 9}, new Shape(1, 2), type), arrayInt1.multiply(arrayInt2));
            assertEquals(new Ndarray(new Object[]{1, 1}, new Shape(1, 2), type), arrayInt1.divide(arrayInt2));

            arrayInt1.addInPlace(arrayInt2);
            assertEquals(arrayInt1, new Ndarray(new Object[]{4, 6}, new Shape(1, 2), type));
            arrayInt1 = new Ndarray(new Shape(1, 2), type);
            arrayInt1.set(2, 0, 0);
            arrayInt1.set(3, 0, 1);
            arrayInt1.subtractInPlace(arrayInt2);
            assertEquals(arrayInt1, new Ndarray(new Object[]{0, 0}, new Shape(1, 2), type));

            arrayInt1 = new Ndarray(new Shape(1, 2), type);
            arrayInt1.set(2, 0, 0);
            arrayInt1.set(3, 0, 1);
            arrayInt1.multiplyInPlace(arrayInt2);
            assertEquals(arrayInt1, new Ndarray(new Object[]{4, 9}, new Shape(1, 2), type));

            arrayInt1 = new Ndarray(new Shape(1, 2), type);
            arrayInt1.set(2, 0, 0);
            arrayInt1.set(3, 0, 1);
            arrayInt1.divideInPlace(arrayInt2);
            assertEquals(arrayInt1, new Ndarray(new Object[]{1, 1}, new Shape(1, 2), type));

            final Ndarray arrayInt3 = new Ndarray(new Shape(1, 2), type);
            assertThrows(IllegalArgumentException.class, () -> arrayInt3.addInPlace(null));

            final Ndarray arrayInt4 = new Ndarray(new Shape(3, 2), type);
            assertThrows(IllegalArgumentException.class, () -> arrayInt3.addInPlace(arrayInt4));

            final Ndarray arrayInt5 = new Ndarray(new Shape(1, 2), Dtype.BOOLEAN);
            assertThrows(IllegalArgumentException.class, () -> arrayInt3.addInPlace(arrayInt5));
        }
    }

    @Test
    void testAranngeBasic(){
        Ndarray arrayInt = Ndarray.arrange(5);

        assertEquals(arrayInt, new Ndarray( new Object[]{0,1,2,3,4}, new Shape(5), Dtype.INT));
        Ndarray arrayInt2 = Ndarray.arrange(5, 1);
        assertEquals(arrayInt, arrayInt2);

        Ndarray arrayInt3 = Ndarray.arrange(0, 5, 1);
        assertEquals(arrayInt, arrayInt3);

    }


    @Test
    void testOperationString(){
        Ndarray arrayString1 = new Ndarray(new Shape(1, 2), Dtype.STRING);
        Ndarray arrayString2 = new Ndarray(new Shape(1, 2), Dtype.STRING);
        arrayString1.set("a", 0, 0);
        arrayString1.set("b", 0, 1);
        arrayString2.set("b", 0, 0);
        arrayString2.set("a", 0, 1);
        assertEquals(arrayString1.add(arrayString2), new Ndarray(new Object[]{"ab", "ba"}, new Shape(1, 2), Dtype.STRING));
        assertThrows(IllegalArgumentException.class, () -> arrayString1.divide(arrayString2));
        assertThrows(IllegalArgumentException.class, () -> arrayString1.subtract(arrayString2));
        assertThrows(IllegalArgumentException.class, () -> arrayString1.multiply(arrayString2));
        assertThrows(IllegalArgumentException.class, () -> arrayString1.subtractInPlace(arrayString2));
        assertThrows(IllegalArgumentException.class, () -> arrayString1.multiplyInPlace(arrayString2));
        assertThrows(IllegalArgumentException.class, () -> arrayString1.divideInPlace(arrayString2));
        assertNotEquals(arrayString1, new Ndarray(new Object[]{"ab", "ba"}, new Shape(1, 2), Dtype.STRING));
        arrayString1.addInPlace(arrayString2);
        assertEquals(arrayString1, new Ndarray(new Object[]{"ab", "ba"}, new Shape(1, 2), Dtype.STRING));
    }

    @Test
    void testNumpyString(){
        Ndarray arrayString1 = new Ndarray(new Shape(1, 2), Dtype.STRING);
        arrayString1.set("a", 0, 0);
        arrayString1.set("b", 0, 1);
        String result = arrayString1.toNumpyString();
        assertEquals("[[a b ]]\n", result);

        Ndarray arrayString2 = new Ndarray(new Shape(2, 2), Dtype.STRING);
        arrayString2.set("b", 0, 0);
        arrayString2.set("a", 0, 1);

        arrayString2.set("a", 1, 0);
        arrayString2.set("b", 1, 1);

        result = arrayString2.toNumpyString();
        assertEquals("[[b a ]\n[a b ]]\n", result);
    }

    @Test
    void testArrayConstructor(){
        ArrayList<Object> values = new ArrayList<Object>();
        values.add(1f);
        values.add(2f);
        Ndarray arrayString1 = Ndarray.ndarray(values);
        String result = arrayString1.toNumpyString();
        assertEquals("[1.0 2.0 ]\n", result);

        Shape shape = new Shape(1, 2);
        arrayString1 = Ndarray.ndarray(values, shape);
        result = arrayString1.toNumpyString();
        assertEquals("[[1.0 2.0 ]]\n", result);

        ArrayList<Object> valuesString = new ArrayList<Object>();
        valuesString.add("a");
        valuesString.add("b");
        arrayString1 = Ndarray.ndarray(valuesString, shape, Dtype.STRING);
        result = arrayString1.toNumpyString();
        assertEquals("[[a b ]]\n", result);
    }

    @Test
    void testZeroConstructor(){
        Shape shape = new Shape(1, 2);
        Ndarray array= Ndarray.zeros(shape);
        String result = array.toNumpyString();
        assertEquals("[[0.0 0.0 ]]\n", result);

        array= Ndarray.zeros(shape, Dtype.INT);
        result = array.toNumpyString();
        assertEquals("[[0 0 ]]\n", result);
    }

}

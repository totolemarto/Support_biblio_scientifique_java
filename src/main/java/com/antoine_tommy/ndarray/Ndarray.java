package com.antoine_tommy.ndarray;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

/**
 * A n-dimensional array structure inspired by NumPy arrays.
 * <p>
 * The data is stored in a flat row-major buffer and accessed through n-dimensional indices.
 */
public class Ndarray {
    private final Ndim ndim;
    private final Shape shape;
    private final Dtype dtype;
    private final int size;
    private final Object[] data;

    /**
     * Creates a new array with the given shape and DOUBLE dtype.
     *
     * @param shape target shape
     */
    public Ndarray(Shape shape) {
        this(shape, Dtype.DOUBLE);
    }

    /**
     * Creates a new array with the given shape and dtype.
     *
     * @param shape target shape
     * @param dtype target dtype
     */
    public Ndarray(Shape shape, Dtype dtype) {
        this.shape = Objects.requireNonNull(shape, "shape cannot be null");
        this.dtype = Objects.requireNonNull(dtype, "dtype cannot be null");
        this.ndim = new Ndim(shape.getDimensions().length);
        this.size = computeSize(shape);
        this.data = new Object[size];

        Object defaultValue = defaultValueFor(dtype);
        Arrays.fill(this.data, defaultValue);
    }

    /**
     * Creates an array from an existing flat buffer.
     *
     * @param data flat row-major data
     * @param shape target shape
     * @param dtype target dtype
     */
    public Ndarray(Object[] data, Shape shape, Dtype dtype) {
        this.shape = Objects.requireNonNull(shape, "shape cannot be null");
        this.dtype = Objects.requireNonNull(dtype, "dtype cannot be null");
        Object[] source = Objects.requireNonNull(data, "data cannot be null");

        this.ndim = new Ndim(shape.getDimensions().length);
        this.size = computeSize(shape);
        if (source.length != size) {
            throw new IllegalArgumentException("Data length does not match shape size. Expected " + size + " but got " + source.length + '.');
        }

        this.data = new Object[size];
        for (int i = 0; i < source.length; i++) {
            this.data[i] = convertValue(source[i], dtype);
        }
    }

    /**
     * Returns the number of dimensions of the array.
     *
     * @return the number of dimensions
     */
    public Ndim getNdim() {
        return ndim;
    }

    /**
     * Returns the shape of the array.
     *
     * @return the shape of the array
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Returns the data type of the array.
     *
     * @return the data type of the array
     */
    public Dtype getDtype() {
        return dtype;
    }

    /**
     * Returns the size in bytes of a single item in the array based on its dtype.
     *
     * @return the size in bytes of a single item
     */
    public int getItemSizeInBytes() {
        return dtype.getSizeInBytes();
    }

    /**
     * Returns the total number of elements in the array.
     *
     * @return the total number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Returns a defensive copy of the internal row-major flat data.
     *
     * @return flat data clone
     */
    public Object[] toFlatArray() {
        return data.clone();
    }

    /**
     * Gets an element from n-dimensional coordinates.
     *
     * @param indices n-dimensional indices
     * @return typed value
     */
    public Object get(int... indices) {
        return data[flatIndex(indices)];
    }

    /**
     * Sets an element at n-dimensional coordinates.
     *
     * @param value value to store
     * @param indices n-dimensional indices
     */
    public void set(Object value, int... indices) {
        data[flatIndex(indices)] = convertValue(value, dtype);
    }

    /**
     * Returns a new array with the same data but another shape.
     *
     * @param newShape target shape
     * @return reshaped array copy
     */
    public Ndarray reshape(Shape newShape) {
        Shape targetShape = Objects.requireNonNull(newShape, "newShape cannot be null");
        if (computeSize(targetShape) != size) {
            throw new IllegalArgumentException("Cannot reshape array of size " + size + " to shape with different size.");
        }
        return new Ndarray(this.data.clone(), targetShape, dtype);
    }

    /**
     * Returns a new array converted to another dtype.
     *
     * @param targetDtype dtype to convert to
     * @return converted array copy
     */
    public Ndarray astype(Dtype targetDtype) {
        Dtype target = Objects.requireNonNull(targetDtype, "targetDtype cannot be null");
        Object[] converted = new Object[size];
        for (int i = 0; i < size; i++) {
            converted[i] = convertValue(data[i], target);
        }
        return new Ndarray(converted, shape, target);
    }

    /**
     * Print the array in Numpy-like format.
     *
     * @return a string representation of the array
     */
    public String toNumpyString() {
        // TODO
        return "";
    }

    private int flatIndex(int... indices) {
        int[] dims = shape.getDimensions();
        if (indices == null || indices.length != dims.length) {
            throw new IllegalArgumentException("Expected " + dims.length + " indices, got " + (indices == null ? 0 : indices.length) + '.');
        }

        int flatIndex = 0;
        int stride = 1;
        for (int i = dims.length - 1; i >= 0; i--) {
            int index = indices[i];
            if (index < 0 || index >= dims[i]) {
                throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for axis " + i + " with size " + dims[i] + '.');
            }
            flatIndex += index * stride;
            stride *= dims[i];
        }
        return flatIndex;
    }

    private static int computeSize(Shape shape) {
        long product = 1L;
        for (int dim : shape.getDimensions()) {
            product *= dim;
            if (product > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Shape is too large.");
            }
        }
        return (int) product;
    }

    private static Object defaultValueFor(Dtype dtype) {
        return switch (dtype) {
            case INT -> 0;
            case FLOAT -> 0.0f;
            case DOUBLE -> 0.0d;
            case BOOLEAN -> false;
            case STRING -> "";
        };
    }

    private static Object convertValue(Object value, Dtype target) {
        Objects.requireNonNull(value, "value cannot be null");

        try {
            return switch (target) {
                case INT -> toInt(value);
                case FLOAT -> toFloat(value);
                case DOUBLE -> toDouble(value);
                case BOOLEAN -> toBoolean(value);
                case STRING -> String.valueOf(value);
            };
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException(
                "Cannot convert value '" + value + "' (" + value.getClass().getSimpleName() + ") to " + target + ".",
                exception
            );
        }
    }

    private static Integer toInt(Object value) {
        if (value instanceof Number number) {
            return number.intValue();
        }
        if (value instanceof Boolean bool) {
            return bool ? 1 : 0;
        }
        if (value instanceof String text) {
            return Integer.parseInt(text);
        }
        throw new IllegalArgumentException("Unsupported conversion to int.");
    }

    private static Float toFloat(Object value) {
        if (value instanceof Number number) {
            return number.floatValue();
        }
        if (value instanceof Boolean bool) {
            return bool ? 1.0f : 0.0f;
        }
        if (value instanceof String text) {
            return Float.parseFloat(text);
        }
        throw new IllegalArgumentException("Unsupported conversion to float.");
    }

    private static Double toDouble(Object value) {
        if (value instanceof Number number) {
            return number.doubleValue();
        }
        if (value instanceof Boolean bool) {
            return bool ? 1.0d : 0.0d;
        }
        if (value instanceof String text) {
            return Double.parseDouble(text);
        }
        throw new IllegalArgumentException("Unsupported conversion to double.");
    }

    private static Boolean toBoolean(Object value) {
        if (value instanceof Boolean bool) {
            return bool;
        }
        if (value instanceof Number number) {
            return number.doubleValue() != 0.0d;
        }
        if (value instanceof String text) {
            String normalized = text.trim().toLowerCase(Locale.ROOT);
            if ("true".equals(normalized)) {
                return true;
            }
            if ("false".equals(normalized)) {
                return false;
            }
            throw new IllegalArgumentException("String value must be 'true' or 'false'.");
        }
        throw new IllegalArgumentException("Unsupported conversion to boolean.");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Ndarray other)) {
            return false;
        }
        return size == other.size
            && ndim.equals(other.ndim)
            && shape.equals(other.shape)
            && dtype == other.dtype
            && Arrays.equals(data, other.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ndim, shape, dtype, size);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "Ndarray{"
            + "ndim=" + ndim.getDim()
            + ", shape=" + Arrays.toString(shape.getDimensions())
            + ", dtype=" + dtype
            + ", data=" + Arrays.toString(data)
            + '}';
    }
}


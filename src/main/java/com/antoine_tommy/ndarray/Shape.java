package com.antoine_tommy.ndarray;

/**
 * This class represents the shape of an array, which is defined by its dimensions and the size of each dimension.
 * The shape is typically represented as a list of integers, where each integer corresponds to the size of a dimension.
 * For example, a 2D array with 3 rows and 4 columns would have a shape of [3, 4].
 * The shape is used to determine how data is organized in memory and how it can be accessed.
 */
public class Shape {
    /** The dimensions of the array, represented as an array of integers. Each integer corresponds to the size of a dimension. */
    private final int[] dimensions;

    /**
     * Constructs a Shape object with the specified dimensions.
     *
     * @param dimensions an array of integers representing the size of each dimension
     * @throws IllegalArgumentException if any dimension is not a positive integer
     */
    public Shape(int... dimensions) {
        for (int dim : dimensions) {
            if (dim <= 0) {
                throw new IllegalArgumentException("All dimensions must be positive integers.");
            }
        }
        if (dimensions.length == 0) {
            throw new IllegalArgumentException("At least one dimension must be specified.");
        }

        this.dimensions = dimensions.clone(); // Clone to prevent external modification
    }

    /**
     * Constructs a Shape object with a default dimension of 1.
     * This constructor is useful when the shape is not specified, and it defaults to a 1D shape.
     */
    public Shape() {
        this(1); // Default to a 1D shape if no dimensions are specified
    }

    /**
     * Returns the dimensions of the array as an array of integers.
     *
     * @return an array of integers representing the size of each dimension
     */
    public int[] getDimensions() {
        return dimensions.clone(); // Return a clone to prevent external modification
    }

    /**
     * Returns the size of a specific dimension given its index.
     *
     * @param index the index of the dimension to retrieve
     * @return the size of the specified dimension
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public int getDimension(int index) {
        if (index < 0 || index >= dimensions.length) {
            throw new IndexOutOfBoundsException("Dimension index out of bounds.");
        }
        return dimensions[index];
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Shape other = (Shape) obj;
        if (dimensions.length != other.dimensions.length) {
            return false;
        }
        for (int i = 0; i < dimensions.length; i++) {
            if (dimensions[i] != other.dimensions[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int dim : dimensions) {
            result = 31 * result + Integer.hashCode(dim);
        }
        return result;
    }
}

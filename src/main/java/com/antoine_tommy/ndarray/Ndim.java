package com.antoine_tommy.ndarray;

/**
 * This class represents the number of dimensions of an array.
 * It must be a positive integer, and it is used to determine the shape of the array.
 */
public class Ndim {
    /** The number of dimensions of the array. It must be a positive integer. */
    private final int dim;

    /**
     * Constructs an Ndim object with the specified number of dimensions.
     *
     * @param dim the number of dimensions, must be a positive integer
     * @throws IllegalArgumentException if dim is not a positive integer
     */
    public Ndim(int dim) {
        if (dim <= 0) {
            throw new IllegalArgumentException("Number of dimensions must be a positive integer.");
        }
        this.dim = dim;
    }

    /**
     * Constructs an Ndim object with a default number of dimensions (1).
     * This constructor is useful when the number of dimensions is not specified.
     */
    public Ndim() {
        this(1); // Default to 1 dimension if not specified
    }

    /**
     * Returns the number of dimensions of the array.
     *
     * @return the number of dimensions
     */
    public int getDim() {
        return dim;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ndim other = (Ndim) obj;
        return dim == other.dim;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(dim);
    }

    @Override
    public String toString() {
        return "Ndim{" + "dim=" + dim + '}';
    }
}

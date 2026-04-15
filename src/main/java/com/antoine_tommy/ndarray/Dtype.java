package com.antoine_tommy.ndarray;

/**
 * This enum represents the data type of the elements in an array.
 * It can be used to specify the type of data that an array can hold, such as integers, floats, or doubles.
 * The Dtype enum can be extended to include more data types as needed.
 */
public enum Dtype {
    INT,
    FLOAT,
    DOUBLE,
    BOOLEAN,
    STRING;

    /**
     * Returns the size in bytes of the data type.
     *
     * @return the size in bytes of the data type
     */
    public int getSizeInBytes() {
        return switch (this) {
            case INT -> Integer.BYTES;
            case FLOAT -> Float.BYTES;
            case DOUBLE -> Double.BYTES;
            case BOOLEAN -> 1; // Size of boolean is typically 1 byte
            case STRING -> -1; // Size of string is variable, so we return -1 to indicate that it is not fixed
        };
    }

    /**
     * Returns a string representation of the data type.
     *
     * @return a string representation of the data type
     */
    @Override
    public String toString() {
        return switch (this) {
            case INT -> "int";
            case FLOAT -> "float";
            case DOUBLE -> "double";
            case BOOLEAN -> "boolean";
            case STRING -> "string";
        };
    }

    /**
     * Parses a string to return the corresponding Dtype enum value.
     *
     * @param type the string representation of the data type
     * @return the corresponding Dtype enum value
     * @throws IllegalArgumentException if the string does not match any Dtype
     */
    public static Dtype fromString(String type) {
        return switch (type.toLowerCase()) {
            case "int" -> INT;
            case "float" -> FLOAT;
            case "double" -> DOUBLE;
            case "boolean" -> BOOLEAN;
            case "string" -> STRING;
            default -> throw new IllegalArgumentException("Unknown data type: " + type);
        };
    }
}

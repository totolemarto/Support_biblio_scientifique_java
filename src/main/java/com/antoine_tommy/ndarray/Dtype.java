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
     * Returns the corresponding Java class for the data type.
     *
     * @return the corresponding Java class for the data type
     */
    public Class<?> getJavaClass() {
        return switch (this) {
            case INT -> Integer.class;
            case FLOAT -> Float.class;
            case DOUBLE -> Double.class;
            case BOOLEAN -> Boolean.class;
            case STRING -> String.class;
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

    public static Object operator(Dtype dtype, Operator operator, Object a, Object b) {
        if (dtype == Dtype.INT) {
            int intA = (int) a;
            int intB = (int) b;
            return switch (operator) {
                case ADD -> intA + intB;
                case MINUS -> intA - intB;
                case MULTIPLY -> intA * intB;
                case DIVIDE -> intA / intB;
            };
        } else if (dtype == Dtype.FLOAT) {
            float floatA = (float) a;
            float floatB = (float) b;
            return switch (operator) {
                case ADD -> floatA + floatB;
                case MINUS -> floatA - floatB;
                case MULTIPLY -> floatA * floatB;
                case DIVIDE -> floatA / floatB;
            };
        } else if (dtype == Dtype.DOUBLE) {
            double doubleA = (double) a;
            double doubleB = (double) b;
            return switch (operator) {
                case ADD -> doubleA + doubleB;
                case MINUS -> doubleA - doubleB;
                case MULTIPLY -> doubleA * doubleB;
                case DIVIDE -> doubleA / doubleB;
            };
        } else if (dtype == Dtype.STRING) {
            String stringA = (String) a;
            String stringB = (String) b;
            return switch (operator) {
                case ADD -> stringA + stringB;
                default -> throw new IllegalArgumentException("Unsupported operator for string data type: " + operator);
            };
        } else {
            throw new IllegalArgumentException("Unsupported data type for operator: " + dtype);
        }
    }
}

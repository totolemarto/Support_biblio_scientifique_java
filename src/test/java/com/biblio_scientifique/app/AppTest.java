package com.biblio_scientifique.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
class AppTest {

    @Test
    void constructorTest() {
        assertDoesNotThrow(App::new);
    }

    @Test
    void printsHelloWorldWhenCalledWithNoArguments() {
        String output = captureMainOutput(new String[0]);
        assertEquals("Hello World!" + System.lineSeparator(), output);
    }

    @Test
    void printsHelloWorldWhenArgumentsAreNull() {
        assertDoesNotThrow(() -> {
            String output = captureMainOutput(null);
            assertEquals("Hello World!" + System.lineSeparator(), output);
        });
    }

    @Test
    void ignoresProvidedArgumentsAndPrintsHelloWorld() {
        String output = captureMainOutput(new String[] { "--name", "toto" });
        assertEquals("Hello World!" + System.lineSeparator(), output);
    }

    private String captureMainOutput(String[] args) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try (PrintStream printStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8)) {
            System.setOut(printStream);
            App.main(args);
        } finally {
            System.setOut(originalOut);
        }
        return outputStream.toString(StandardCharsets.UTF_8);
    }
}

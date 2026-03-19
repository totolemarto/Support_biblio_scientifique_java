package com.biblio_scientifique.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
class AppTest {

    private static final class CapturingHandler extends Handler {
        private final List<LogRecord> records = new ArrayList<>();

        @Override
        public void publish(LogRecord record) {
            records.add(record);
        }

        @Override
        public void flush() {
            // no-op
        }

        @Override
        public void close() {
            // no-op
        }
    }

    private static List<LogRecord> invokeMainAndCaptureLogs(String[] args) {
        Logger appLogger = Logger.getLogger(App.class.getName());
        CapturingHandler handler = new CapturingHandler();
        boolean previousUseParentHandlers = appLogger.getUseParentHandlers();

        appLogger.setUseParentHandlers(false);
        appLogger.addHandler(handler);

        try {
            App.main(args);
            return handler.records;
        } finally {
            appLogger.removeHandler(handler);
            appLogger.setUseParentHandlers(previousUseParentHandlers);
        }
    }

    @Test
    void constructorTest() {
        assertDoesNotThrow(App::new);
    }

    @Test
    void mainLogsHelloWorldWhenArgsAreEmpty() {
        List<LogRecord> records = assertDoesNotThrow(() -> invokeMainAndCaptureLogs(new String[0]));

        assertEquals(1, records.size());
        assertEquals(Level.INFO, records.getFirst().getLevel());
        assertEquals("Hello World!\n", records.getFirst().getMessage());
    }

    @Test
    void mainLogsHelloWorldWhenArgsAreNull() {
        List<LogRecord> records = assertDoesNotThrow(() -> invokeMainAndCaptureLogs(null));

        assertEquals(1, records.size());
        assertEquals(Level.INFO, records.getFirst().getLevel());
        assertEquals("Hello World!\n", records.getFirst().getMessage());
    }

    @Test
    void mainIgnoresProvidedArgumentsAndStillLogsHelloWorld() {
        List<LogRecord> records = assertDoesNotThrow(() -> invokeMainAndCaptureLogs(new String[] { "alpha", "beta" }));

        assertEquals(1, records.size());
        assertEquals(Level.INFO, records.getFirst().getLevel());
        assertEquals("Hello World!\n", records.getFirst().getMessage());
    }

    @Test
    void mainCanBeCalledMultipleTimesAndLogsEachInvocation() {
        Logger appLogger = Logger.getLogger(App.class.getName());
        CapturingHandler handler = new CapturingHandler();
        boolean previousUseParentHandlers = appLogger.getUseParentHandlers();

        appLogger.setUseParentHandlers(false);
        appLogger.addHandler(handler);

        try {
            assertDoesNotThrow(() -> App.main(new String[0]));
            assertDoesNotThrow(() -> App.main(new String[] { "x" }));

            assertEquals(2, handler.records.size());
            assertEquals("Hello World!\n", handler.records.get(0).getMessage());
            assertEquals("Hello World!\n", handler.records.get(1).getMessage());
        } finally {
            appLogger.removeHandler(handler);
            appLogger.setUseParentHandlers(previousUseParentHandlers);
        }
    }
}

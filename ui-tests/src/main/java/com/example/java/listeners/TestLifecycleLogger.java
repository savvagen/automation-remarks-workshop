package com.example.java.listeners;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.TestInstance.*;

@TestInstance(Lifecycle.PER_CLASS)
public interface TestLifecycleLogger {

    static final Logger LOG = Logger.getLogger(TestLifecycleLogger.class.getName());


    @BeforeAll
    default void beforeAllTests(TestInfo testInfo) {
        LOG.info("Running "  + testInfo.getTestClass().toString());
        LOG.info("Before all tests");
    }

    @AfterAll
    default void afterAllTests(TestInfo testInfo) {
        LOG.info("Finished "  + testInfo.getTestClass().toString());
    }

    @BeforeEach
    default void beforeEachTest(TestInfo testInfo) {
        LOG.info(() -> String.format("About to execute [%s]",
                testInfo.getDisplayName()));
    }

    @AfterEach
    default void afterEachTest(TestInfo testInfo) {
        LOG.info(() -> String.format("Finished executing [%s]",
                testInfo.getDisplayName()));
    }

}

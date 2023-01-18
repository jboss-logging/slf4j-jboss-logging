/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2020 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Supplier;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.jboss.slf4j.JBossLoggerAdapter;
import org.jboss.slf4j.JBossMDCAdapter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public class LoggerTestCase {
    private static final java.util.logging.Logger ROOT = java.util.logging.Logger.getLogger("");
    private static final QueueHandler HANDLER = new QueueHandler();
    private static final Collection<Handler> CURRENT_HANDLERS = new ArrayList<Handler>();

    @BeforeAll
    public static void configureLogManager() {
        // By default JBoss Logging should choose JUL as a log manager since no log manager has been defined
        final Handler[] currentHandlers = ROOT.getHandlers();
        if (currentHandlers != null) {
            for (Handler handler : currentHandlers) {
                CURRENT_HANDLERS.add(handler);
                ROOT.removeHandler(handler);
            }
        }
        ROOT.addHandler(HANDLER);
    }

    @AfterAll
    public static void cleanup() {
        ROOT.removeHandler(HANDLER);
        for (Handler handler : CURRENT_HANDLERS) {
            ROOT.addHandler(handler);
        }
    }

    @AfterEach
    public void clearHandler() {
        HANDLER.reset();
    }

    @Test
    public void testLogger() {
        final Logger logger = LoggerFactory.getLogger(LoggerTestCase.class);
        Assertions.assertTrue(logger instanceof JBossLoggerAdapter,
                expectedTypeMessage(JBossLoggerAdapter.class, logger.getClass()));

        // Ensure the logger logs something
        final String testMsg = "This is a test message";
        logger.info(testMsg);
        LogRecord record = HANDLER.messages.poll();
        Assertions.assertNotNull(record);
        Assertions.assertEquals(testMsg, record.getMessage(), "Expected message not found.");

        // Test a formatted message
        logger.info("This is a test formatted {}", "message");
        record = HANDLER.messages.poll();
        Assertions.assertNotNull(record);
        Assertions.assertEquals("This is a test formatted message", record.getMessage(), "Expected formatted message not found.");
    }

    @Test
    public void testLoggerWithExceptions() {
        final Logger logger = LoggerFactory.getLogger(LoggerTestCase.class);

        final RuntimeException e = new RuntimeException("Test exception");
        final String testMsg = "This is a test message";
        logger.info(testMsg, e);
        LogRecord record = HANDLER.messages.poll();
        Assertions.assertNotNull(record);
        Assertions.assertEquals(testMsg, record.getMessage(), "Expected message not found.");
        Assertions.assertEquals(e, record.getThrown(), "Cause is different from the expected cause");

        // Test format with the last parameter being the throwable which should set be set on the record
        logger.info("This is a test formatted {}", "message", e);
        record = HANDLER.messages.poll();
        Assertions.assertNotNull(record);
        Assertions.assertEquals("This is a test formatted message", record.getMessage(), "Expected formatted message not found.");
        Assertions.assertEquals(e, record.getThrown(), "Cause is different from the expected cause");

    }

    @Test
    public void testMDC() {
        Assertions.assertSame(MDC.getMDCAdapter()
                .getClass(), JBossMDCAdapter.class, expectedTypeMessage(JBossMDCAdapter.class, MDC.getMDCAdapter()
                .getClass()));
        final String key = Long.toHexString(System.currentTimeMillis());
        MDC.put(key, "value");
        Assertions.assertEquals("value", MDC.get(key), "MDC value should be \"value\"");
        Assertions.assertEquals("value", org.jboss.logging.MDC.get(key), "MDC value should be \"value\"");
    }

    private static Supplier<String> expectedTypeMessage(final Class<?> expected, final Class<?> found) {
        return () -> String.format("Expected type %s but found type %s", expected.getName(), found.getName());
    }

    private static class QueueHandler extends Handler {
        final BlockingDeque<LogRecord> messages = new LinkedBlockingDeque<LogRecord>();

        @Override
        public void publish(final LogRecord record) {
            messages.add(record);
        }

        @Override
        public void flush() {
        }

        @Override
        public void close() throws SecurityException {
            messages.clear();
        }

        void reset() {
            messages.clear();
            setLevel(Level.ALL);
        }
    }
}

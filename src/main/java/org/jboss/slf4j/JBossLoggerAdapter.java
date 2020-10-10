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
package org.jboss.slf4j;

import org.jboss.logmanager.ExtLogRecord;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

/**
 * A wrapper over {@link org.jboss.logmanager.Logger org.jboss.logmanager.Logger}
 * in conformance with the {@link Logger} interface.
 * <p/>
 * Adapted from the corresponding slf4j-log4j adapter.
 *
 * @author <a href="mailto:dimitris@jboss.org">Dimitris Andreadis</a>
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 * @version <tt>$Revision: 2784 $</tt>
 */
public final class JBossLoggerAdapter extends MarkerIgnoringBase implements LocationAwareLogger {
    private static final long serialVersionUID = -1855332334983449117L;

    final org.jboss.logmanager.Logger logger;

    private static final String LOGGER_FQCN = JBossLoggerAdapter.class.getName();

    // package access so that only JBossLoggerFactory be able to create one.
    JBossLoggerAdapter(org.jboss.logmanager.Logger jbossLogger) {
        this.logger = jbossLogger;
        this.name = logger.getName();
    }

    @Override
    public void log(Marker marker, String fqcn, int level, String message, Object[] argArray, Throwable t) {
        FormattingTuple result = MessageFormatter.arrayFormat(message, argArray);
        switch (level) {
            case LocationAwareLogger.TRACE_INT:
                log(JDKLevel.TRACE, fqcn, result.getMessage(), t, argArray);
                break;

            case LocationAwareLogger.DEBUG_INT:
                log(JDKLevel.DEBUG, fqcn, result.getMessage(), t, argArray);
                break;

            case LocationAwareLogger.INFO_INT:
                log(JDKLevel.INFO, fqcn, result.getMessage(), t, argArray);
                break;

            case LocationAwareLogger.WARN_INT:
                log(JDKLevel.WARN, fqcn, result.getMessage(), t, argArray);
                break;

            case LocationAwareLogger.ERROR_INT:
                log(JDKLevel.ERROR, fqcn, result.getMessage(), t, argArray);
                break;

            default:
                throw new IllegalStateException("Level number " + level + " is not recognized.");
        }
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isLoggable(JDKLevel.TRACE);
    }

    @Override
    public void trace(final String msg) {
        log(JDKLevel.TRACE, LOGGER_FQCN, msg);
    }

    @Override
    public void trace(final String format, final Object arg) {
        if (isTraceEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
            log(JDKLevel.TRACE, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arg);
        }
    }

    @Override
    public void trace(final String format, final Object arg1, final Object arg2) {
        if (isTraceEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
            log(JDKLevel.TRACE, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arg1, arg2);
        }
    }

    @Override
    public void trace(final String format, final Object... arguments) {
        if (isTraceEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, arguments);
            log(JDKLevel.TRACE, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arguments);
        }
    }

    @Override
    public void trace(final String msg, final Throwable t) {
        if (isTraceEnabled()) {
            log(JDKLevel.TRACE, LOGGER_FQCN, msg, t);
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isLoggable(JDKLevel.DEBUG);
    }

    @Override
    public void debug(final String msg) {
        if (isDebugEnabled()) {
            log(JDKLevel.DEBUG, LOGGER_FQCN, msg);
        }
    }

    @Override
    public void debug(final String format, final Object arg) {
        if (isDebugEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
            log(JDKLevel.DEBUG, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arg);
        }
    }

    @Override
    public void debug(final String format, final Object arg1, final Object arg2) {
        if (isDebugEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
            log(JDKLevel.DEBUG, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arg1, arg2);
        }
    }

    @Override
    public void debug(final String format, final Object... arguments) {
        if (isDebugEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, arguments);
            log(JDKLevel.DEBUG, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arguments);
        }
    }

    @Override
    public void debug(final String msg, final Throwable t) {
        if (isDebugEnabled()) {
            log(JDKLevel.DEBUG, LOGGER_FQCN, msg, t);
        }
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isLoggable(JDKLevel.INFO);
    }

    @Override
    public void info(final String msg) {
        if (isInfoEnabled()) {
            log(JDKLevel.INFO, LOGGER_FQCN, msg);
        }
    }

    @Override
    public void info(final String format, final Object arg) {
        if (isInfoEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
            log(JDKLevel.INFO, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arg);
        }
    }

    @Override
    public void info(final String format, final Object arg1, final Object arg2) {
        if (isInfoEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
            log(JDKLevel.INFO, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arg1, arg2);
        }
    }

    @Override
    public void info(final String format, final Object... arguments) {
        if (isInfoEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, arguments);
            log(JDKLevel.INFO, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arguments);
        }
    }

    @Override
    public void info(final String msg, final Throwable t) {
        if (isInfoEnabled()) {
            log(JDKLevel.INFO, LOGGER_FQCN, msg, t);
        }
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isLoggable(JDKLevel.WARN);
    }

    @Override
    public void warn(final String msg) {
        if (isWarnEnabled()) {
            log(JDKLevel.WARN, LOGGER_FQCN, msg);
        }
    }

    @Override
    public void warn(final String format, final Object arg) {
        if (isWarnEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
            log(JDKLevel.WARN, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arg);
        }
    }

    @Override
    public void warn(final String format, final Object... arguments) {
        if (isWarnEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, arguments);
            log(JDKLevel.WARN, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arguments);
        }
    }

    @Override
    public void warn(final String format, final Object arg1, final Object arg2) {
        if (isWarnEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
            log(JDKLevel.WARN, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arg1, arg2);
        }
    }

    @Override
    public void warn(final String msg, final Throwable t) {
        if (isWarnEnabled()) {
            log(JDKLevel.WARN, LOGGER_FQCN, msg, t);
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isLoggable(JDKLevel.ERROR);
    }

    @Override
    public void error(final String msg) {
        if (isErrorEnabled()) {
            log(JDKLevel.ERROR, LOGGER_FQCN, msg);
        }
    }

    @Override
    public void error(final String format, final Object arg) {
        if (isErrorEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
            log(JDKLevel.ERROR, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arg);
        }
    }

    @Override
    public void error(final String format, final Object arg1, final Object arg2) {
        if (isErrorEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
            log(JDKLevel.ERROR, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arg1, arg2);
        }
    }

    @Override
    public void error(final String format, final Object... arguments) {
        if (isErrorEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, arguments);
            log(JDKLevel.ERROR, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable(), arguments);
        }
    }

    @Override
    public void error(final String msg, final Throwable t) {
        if (isErrorEnabled()) {
            log(JDKLevel.ERROR, LOGGER_FQCN, msg, t);
        }
    }

    private void log(final java.util.logging.Level level, final String fqcn, final String message) {
        log(level, fqcn, message, null);
    }

    private void log(final java.util.logging.Level level, final String fqcn, final String message, final Throwable t) {
        logger.log(fqcn, level, message, t);
    }

    private void log(final java.util.logging.Level level, final String fqcn, final String message, final Throwable t, Object... args) {
        logger.log(fqcn, level, message, ExtLogRecord.FormatStyle.NO_FORMAT, args, t);
    }
}

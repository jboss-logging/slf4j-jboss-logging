/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.slf4j;

import org.jboss.logging.Logger.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

/**
 * A wrapper over {@link org.jboss.logging.Logger org.jboss.logging.Logger}
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

    final org.jboss.logging.Logger logger;

    private static final String LOGGER_FQCN = JBossLoggerAdapter.class.getName();

    // package access so that only JBossLoggerFactory be able to create one.
    JBossLoggerAdapter(org.jboss.logging.Logger logger) {
        this.logger = logger;
        this.name = logger.getName();
    }

    @Override
    public void log(Marker marker, String fqcn, int level, String message, Object[] argArray, Throwable t) {
        FormattingTuple result = MessageFormatter.arrayFormat(message, argArray);
        switch (level) {
            case LocationAwareLogger.TRACE_INT:
                logger.trace(fqcn, result.getMessage(), t);
                break;

            case LocationAwareLogger.DEBUG_INT:
                logger.debug(fqcn, result.getMessage(), t);
                break;

            case LocationAwareLogger.INFO_INT:
                logger.info(fqcn, result.getMessage(), t);
                break;

            case LocationAwareLogger.WARN_INT:
                logger.warn(fqcn, result.getMessage(), t);
                break;

            case LocationAwareLogger.ERROR_INT:
                logger.error(fqcn, result.getMessage(), t);
                break;

            default:
                throw new IllegalStateException("Level number " + level + " is not recognized.");
        }
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(final String msg) {
        log(Level.TRACE, LOGGER_FQCN, msg, null);
    }

    @Override
    public void trace(final String format, final Object arg) {
        if (logger.isTraceEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
            log(Level.TRACE, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void trace(final String format, final Object arg1, final Object arg2) {
        if (logger.isTraceEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
            log(Level.TRACE, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void trace(final String format, final Object... arguments) {
        if (logger.isTraceEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, arguments);
            log(Level.TRACE, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void trace(final String msg, final Throwable t) {
        if (logger.isTraceEnabled()) {
            log(Level.TRACE, LOGGER_FQCN, msg, t);
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(final String msg) {
       if (logger.isDebugEnabled()) {
           log(Level.DEBUG, LOGGER_FQCN, msg, null);
       }
    }

    @Override
    public void debug(final String format, final Object arg) {
        if (logger.isDebugEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
            log(Level.DEBUG, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void debug(final String format, final Object arg1, final Object arg2) {
        if (logger.isDebugEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
            log(Level.DEBUG, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void debug(final String format, final Object... arguments) {
        if (logger.isDebugEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, arguments);
            log(Level.DEBUG, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void debug(final String msg, final Throwable t) {
        if (logger.isDebugEnabled()) {
            log(Level.DEBUG, LOGGER_FQCN, msg, t);
        }
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(final String msg) {
        if (logger.isInfoEnabled()) {
            log(Level.INFO, LOGGER_FQCN, msg, null);
        }
    }

    @Override
    public void info(final String format, final Object arg) {
        if (logger.isInfoEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
            log(Level.INFO, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void info(final String format, final Object arg1, final Object arg2) {
        if (logger.isInfoEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
            log(Level.INFO, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void info(final String format, final Object... arguments) {
        if (logger.isInfoEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, arguments);
            log(Level.INFO, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void info(final String msg, final Throwable t) {
        if (logger.isInfoEnabled()) {
            log(Level.INFO, LOGGER_FQCN, msg, t);
        }
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isEnabled(Level.WARN);
    }

    @Override
    public void warn(final String msg) {
        if (isWarnEnabled()) {
            log(Level.WARN, LOGGER_FQCN, msg, null);
        }
    }

    @Override
    public void warn(final String format, final Object arg) {
        if (isWarnEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
            log(Level.WARN, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void warn(final String format, final Object... arguments) {
        if (isWarnEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, arguments);
            log(Level.WARN, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void warn(final String format, final Object arg1, final Object arg2) {
        if (isWarnEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
            log(Level.WARN, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void warn(final String msg, final Throwable t) {
        if (isWarnEnabled()) {
            log(Level.WARN, LOGGER_FQCN, msg, t);
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isEnabled(Level.ERROR);
    }

    @Override
    public void error(final String msg) {
        if (isErrorEnabled()) {
            log(Level.ERROR, LOGGER_FQCN, msg, null);
        }
    }

    @Override
    public void error(final String format, final Object arg) {
        if (isErrorEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
            log(Level.ERROR, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void error(final String format, final Object arg1, final Object arg2) {
        if (isErrorEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
            log(Level.ERROR, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void error(final String format, final Object... arguments) {
        if (isErrorEnabled()) {
            final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, arguments);
            log(Level.ERROR, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
        }
    }

    @Override
    public void error(final String msg, final Throwable t) {
        if (isErrorEnabled()) {
            log(Level.ERROR, LOGGER_FQCN, msg, t);
        }
    }

    private void log(final org.jboss.logging.Logger.Level level, final String fqcn, final Object message, final Throwable t) {
        logger.log(level, fqcn, message, t);
    }
}

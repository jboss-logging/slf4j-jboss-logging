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

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

/**
 * A wrapper over {@link org.jboss.logging.Logger org.jboss.logging.Logger}
 * in conformance with the {@link Logger} interface.
 *
 * Adapted from the corresponding slf4j-log4j adapter.
 *
 * @author <a href="mailto:dimitris@jboss.org">Dimitris Andreadis</a>
 * @version <tt>$Revision: 2784 $</tt>
 */
public final class JBossLoggerAdapter extends MarkerIgnoringBase
   implements LocationAwareLogger
{
   private static final long serialVersionUID = -1855332334983449117L;

   final org.jboss.logging.Logger logger;

   private static final String LOGGER_FQCN = JBossLoggerAdapter.class.getName();

   // package access so that only JBossLoggerFactory be able to create one.
   JBossLoggerAdapter(org.jboss.logging.Logger logger)
   {
      this.logger = logger;
   }

   public String getName()
   {
      return logger.getName();
   }

   public boolean isTraceEnabled()
   {
      return logger.isTraceEnabled();
   }

   public void trace(String msg)
   {
      logger.trace(LOGGER_FQCN, msg, null);
   }

   public void trace(String format, Object arg)
   {
      if (logger.isTraceEnabled())
      {
         final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
         log(org.jboss.logging.Logger.Level.TRACE, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
      }
   }

   public void trace(String format, Object arg1, Object arg2)
   {
      if (logger.isTraceEnabled())
      {
         final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
         log(org.jboss.logging.Logger.Level.TRACE, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
      }
   }

   public void trace(String format, Object[] argArray)
   {
      if (logger.isTraceEnabled())
      {
         final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, argArray);
         log(org.jboss.logging.Logger.Level.TRACE, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
      }
   }

   public void trace(String msg, Throwable t)
   {
      logger.trace(LOGGER_FQCN, msg, t);
   }

   @SuppressWarnings("deprecation")
   public boolean isDebugEnabled()
   {
      return logger.isDebugEnabled();
   }

   public void debug(String msg)
   {
      logger.debug(LOGGER_FQCN, msg, null);
   }

   @SuppressWarnings("deprecation")
   public void debug(String format, Object arg)
   {
      if (logger.isDebugEnabled())
      {
         final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
         log(org.jboss.logging.Logger.Level.DEBUG, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
      }
   }

   @SuppressWarnings("deprecation")
   public void debug(String format, Object arg1, Object arg2)
   {
      if (logger.isDebugEnabled())
      {
         final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
         log(org.jboss.logging.Logger.Level.DEBUG, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
      }
   }

   @SuppressWarnings("deprecation")
   public void debug(String format, Object[] argArray)
   {
      if (logger.isDebugEnabled())
      {
         final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, argArray);
         log(org.jboss.logging.Logger.Level.DEBUG, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
      }
   }

   public void debug(String msg, Throwable t)
   {
      logger.debug(LOGGER_FQCN, msg, t);
   }

   @SuppressWarnings("deprecation")
   public boolean isInfoEnabled()
   {
      return logger.isInfoEnabled();
   }

   public void info(String msg)
   {
      logger.info(LOGGER_FQCN, msg, null);
   }

   @SuppressWarnings("deprecation")
   public void info(String format, Object arg)
   {
      if (logger.isInfoEnabled())
      {
         final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
         log(org.jboss.logging.Logger.Level.INFO, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
      }
   }

   @SuppressWarnings("deprecation")
   public void info(String format, Object arg1, Object arg2)
   {
      if (logger.isInfoEnabled())
      {
         final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
         log(org.jboss.logging.Logger.Level.INFO, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
      }
   }

   @SuppressWarnings("deprecation")
   public void info(String format, Object[] argArray)
   {
      if (logger.isInfoEnabled())
      {
         final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, argArray);
         log(org.jboss.logging.Logger.Level.INFO, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
      }
   }

   public void info(String msg, Throwable t)
   {
      logger.info(LOGGER_FQCN, msg, t);
   }

   public boolean isWarnEnabled()
   {
      // not supported by jboss-logging-spi
      return true;
   }

   public void warn(String msg)
   {
      logger.warn(LOGGER_FQCN, msg, null);
   }

   public void warn(String format, Object arg)
   {
      final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
      log(org.jboss.logging.Logger.Level.WARN, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
   }

   public void warn(String format, Object arg1, Object arg2)
   {
      final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
      log(org.jboss.logging.Logger.Level.WARN, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
   }

   public void warn(String format, Object[] argArray)
   {
      final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, argArray);
      log(org.jboss.logging.Logger.Level.WARN, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
   }

   public void warn(String msg, Throwable t)
   {
      logger.warn(LOGGER_FQCN, msg, t);
   }

   public boolean isErrorEnabled()
   {
      // not supported by jboss-logging-spi
      return true;
   }

   public void error(String msg)
   {
      logger.error(LOGGER_FQCN, msg, null);
   }

   public void error(String format, Object arg)
   {
      final FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
      log(org.jboss.logging.Logger.Level.ERROR, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
   }

   public void error(String format, Object arg1, Object arg2)
   {
      final FormattingTuple formattingTuple = MessageFormatter.format(format, arg1, arg2);
      log(org.jboss.logging.Logger.Level.ERROR, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
   }

   public void error(String format, Object[] argArray)
   {
      final FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, argArray);
      log(org.jboss.logging.Logger.Level.ERROR, LOGGER_FQCN, formattingTuple.getMessage(), formattingTuple.getThrowable());
   }

   public void error(String msg, Throwable t)
   {
      logger.error(LOGGER_FQCN, msg, t);
   }

   @SuppressWarnings("unused")
   public void log(final Marker marker, final String callerFQCN, final int level, final String msg, final Throwable t)
   {
      switch(level)
      {
         case LocationAwareLogger.TRACE_INT:
            logger.trace(callerFQCN, msg, t);
            break;

         case LocationAwareLogger.DEBUG_INT:
            logger.debug(callerFQCN, msg, t);
            break;

         case LocationAwareLogger.INFO_INT:
            logger.info(callerFQCN, msg, t);
            break;

         case LocationAwareLogger.WARN_INT:
            logger.warn(callerFQCN, msg, t);
            break;

         case LocationAwareLogger.ERROR_INT:
            logger.error(callerFQCN, msg, t);
            break;

         default:
            throw new IllegalStateException("Level number " + level + " is not recognized.");
      }
   }

   @SuppressWarnings("unused")
   public void log(final Marker marker, final String callerFQCN, final int level, final String msg, final Object[] argArray, final Throwable t)
   {
      FormattingTuple result = MessageFormatter.arrayFormat(msg, argArray);
      switch(level)
      {
         case LocationAwareLogger.TRACE_INT:
            logger.trace(callerFQCN, result.getMessage(), t);
            break;

         case LocationAwareLogger.DEBUG_INT:
            logger.debug(callerFQCN, result.getMessage(), t);
            break;

         case LocationAwareLogger.INFO_INT:
            logger.info(callerFQCN, result.getMessage(), t);
            break;

         case LocationAwareLogger.WARN_INT:
            logger.warn(callerFQCN, result.getMessage(), t);
            break;

         case LocationAwareLogger.ERROR_INT:
            logger.error(callerFQCN, result.getMessage(), t);
            break;

         default:
            throw new IllegalStateException("Level number " + level + " is not recognized.");
      }
   }

   private void log(final org.jboss.logging.Logger.Level level, final String fqcn, final Object message, final Throwable t) {
      logger.log(level, fqcn, message, t);
   }
}

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
package org.slf4j.impl;

import org.jboss.slf4j.JBossLoggerFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * This implementation is bound to {@link JBossLoggerFactory}.
 *
 * @author <a href="mailto:dimitris@jboss.org">Dimitris Andreadis</a>
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 * @version <tt>$Revision: 2784 $</tt>
 */
public class StaticLoggerBinder implements LoggerFactoryBinder
{
   /**
    * Declare the version of the SLF4J API this implementation is compiled against.
    * The value of this field is usually modified with each release.
    */
   // to avoid constant folding by the compiler, this field must *not* be final
   public static String REQUESTED_API_VERSION = "1.7.21";

   public static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

   public static StaticLoggerBinder getSingleton() {
       return SINGLETON;
   }

   private static final String loggerFactoryClassStr = JBossLoggerFactory.class.getName();

   private final ILoggerFactory loggerFactory;

   private StaticLoggerBinder()
   {
      loggerFactory = new JBossLoggerFactory();
   }

   public ILoggerFactory getLoggerFactory()
   {
      return loggerFactory;
   }

   public String getLoggerFactoryClassStr()
   {
      return loggerFactoryClassStr;
   }
}

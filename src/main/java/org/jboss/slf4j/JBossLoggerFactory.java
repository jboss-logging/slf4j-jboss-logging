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

import java.util.HashMap;
import java.util.Map;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * JBossLoggerFactory is an implementation of {@link ILoggerFactory} returning
 * the appropriate named {@link JBossLoggerAdapter} instance.
 * 
 * Adapted from the corresponding slf4j-log4j adapter.
 * 
 * @author <a href="mailto:dimitris@jboss.org">Dimitris Andreadis</a>
 * @version <tt>$Revision: 2784 $</tt>
 */
public class JBossLoggerFactory implements ILoggerFactory
{
   /** JBossLoggerAdapter cache */
   Map loggerMap;

   public JBossLoggerFactory()
   {
      loggerMap = new HashMap();
   }

   /**
    * @see org.slf4j.ILoggerFactory#getLogger(java.lang.String)
    */
   public Logger getLogger(String name)
   {
      Logger slf4jLogger = null;
    
      // protect against concurrent access of loggerMap
      synchronized (this)
      {
         slf4jLogger = (Logger) loggerMap.get(name);
        
         // no logger found
         if (slf4jLogger == null)
         {
            // create a new jboss logger
            org.jboss.logging.Logger jbossLogger;
            jbossLogger = org.jboss.logging.Logger.getLogger(name);
            
            // wrap it with an adapter
            slf4jLogger = new JBossLoggerAdapter(jbossLogger);
        
            // put it in the map
            loggerMap.put(name, slf4jLogger);
         }
      }
      return slf4jLogger;
   }
}

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

import java.util.Map;

import org.jboss.logging.MDC;
import org.slf4j.spi.MDCAdapter;

/**
 * Adapted from the corresponding slf4j-log4j adapter.
 * 
 * @author <a href="mailto:dimitris@jboss.org">Dimitris Andreadis</a>
 * @version <tt>$Revision: 2784 $</tt>
 */
public class JBossMDCAdapter implements MDCAdapter
{

   public void clear()
   {
      Map map = MDC.getMap();

      if (map != null)
         map.clear();
   }

   public String get(String key)
   {
      return (String) MDC.get(key);
   }

   public void put(String key, String val)
   {
      MDC.put(key, val);
   }

   public void remove(String key)
   {
      MDC.remove(key);
   }

}

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

   public Map getCopyOfContextMap()
   {
      return MDC.getMap();
   }

   public void setContextMap(final Map contextMap)
   {
      clear();
      for (Map.Entry entry : ((Map<?,?>)contextMap).entrySet()) {
         final Object key = entry.getKey();
         final Object value = entry.getValue();
         if (key != null) {
            put(String.valueOf(key), value == null ? null : String.valueOf(value));
         }
      }
   }
}

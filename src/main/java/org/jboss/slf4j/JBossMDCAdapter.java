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

import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.MDC;
import org.slf4j.helpers.BasicMDCAdapter;

/**
 * Adapted from the corresponding slf4j-log4j adapter.
 * 
 * @author <a href="mailto:dimitris@jboss.org">Dimitris Andreadis</a>
 * @version <tt>$Revision: 2784 $</tt>
 */
public class JBossMDCAdapter extends BasicMDCAdapter
{

   @Override
   public void clear()
   {
      Map map = MDC.getMap();

      if (map != null)
         map.clear();
   }

   @Override
   public String get(String key)
   {
      return (String) MDC.get(key);
   }

   @Override
   public void put(String key, String val)
   {
      MDC.put(key, val);
   }

   @Override
   public void remove(String key)
   {
      MDC.remove(key);
   }

   @Override
   public Map<String, String> getCopyOfContextMap() {
      Map<String, Object> source = MDC.getMap();
      if (source == null) {
         // Complies to MDCAdapter.getCopyOfContextMap() javadoc.
         // We don't return an emtpy map.
         return null;
      }
      Map<String, String> copyMap = new HashMap<>(source.size());
      source.forEach((k, v) -> copyMap.put(k, String.valueOf(v)));
      return copyMap;
   }

   @Override
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

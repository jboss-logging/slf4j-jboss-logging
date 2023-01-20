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

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * JBossLoggerFactory is an implementation of {@link ILoggerFactory} returning
 * the appropriate named {@link JBossLoggerAdapter} instance.
 * <p>
 * Adapted from the corresponding slf4j-log4j adapter.
 * </p>
 *
 * @author <a href="mailto:dimitris@jboss.org">Dimitris Andreadis</a>
 * @version <tt>$Revision: 2784 $</tt>
 */
public class JBossLoggerFactory implements ILoggerFactory {
    /**
     * JBossLoggerAdapter cache
     */
    Map<String, Logger> loggerMap;

    public JBossLoggerFactory() {
        loggerMap = new HashMap<>();
    }

    /**
     * @see org.slf4j.ILoggerFactory#getLogger(java.lang.String)
     */
    public Logger getLogger(String name) {
        Logger slf4jLogger = null;

        // protect against concurrent access of loggerMap
        synchronized (this) {
            slf4jLogger = loggerMap.get(name);

            // no logger found
            if (slf4jLogger == null) {
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

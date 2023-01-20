/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2023 Red Hat, Inc.
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
@Deprecated(forRemoval = true)
public class StaticLoggerBinder implements LoggerFactoryBinder {
    /**
     * Declare the version of the SLF4J API this implementation is compiled against.
     * The value of this field is usually modified with each release.
     */
    // to avoid constant folding by the compiler, this field must *not* be final
    public static String REQUESTED_API_VERSION = "2.0.6";

    public static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

    public static StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    private static final String loggerFactoryClassStr = JBossLoggerFactory.class.getName();

    private final ILoggerFactory loggerFactory;

    private StaticLoggerBinder() {
        loggerFactory = new JBossLoggerFactory();
    }

    public ILoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

    public String getLoggerFactoryClassStr() {
        return loggerFactoryClassStr;
    }
}

/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
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

package org.jboss.as.clustering.infinispan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.infinispan.AdvancedCache;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.lifecycle.ComponentStatus;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.remoting.transport.Address;
import org.junit.After;
import org.junit.Test;
import org.wildfly.clustering.infinispan.spi.CacheContainer;

/**
 * Unit test for {@link DefaultCacheContainer}.
 *
 * @author Paul Ferraro
 */
public class DefaultCacheContainerTestCase {
    private final BatcherFactory batcherFactory = mock(BatcherFactory.class);
    private final EmbeddedCacheManager manager = mock(EmbeddedCacheManager.class);
    private final CacheContainer subject = new DefaultCacheContainer(this.manager, this.batcherFactory);

    @Test public void getCache(){@SuppressWarnings("unchecked") AdvancedCache<Object, Object> cache=mock(AdvancedCache.class);when(this.manager.getCache("other")).thenReturn(cache);when(cache.getAdvancedCache()).thenReturn(cache);Cache<Object, Object> result=this.subject.getCache("other");assertNotSame(cache,result);assertEquals(result,cache);assertSame(this.subject,result.getCacheManager());}
}

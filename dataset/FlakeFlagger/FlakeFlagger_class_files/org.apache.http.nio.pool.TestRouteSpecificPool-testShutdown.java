/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.http.nio.pool;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;

import junit.framework.Assert;

import org.apache.http.concurrent.BasicFuture;
import org.apache.http.nio.reactor.IOSession;
import org.apache.http.nio.reactor.SessionRequest;
import org.apache.http.pool.PoolEntry;
import org.junit.Test;
import org.mockito.Mockito;

public class TestRouteSpecificPool {

    static class LocalPoolEntry extends PoolEntry<String, IOSession> {

        public LocalPoolEntry(final String route, final IOSession conn) {
            super(null, route, conn);
        }

    }

    static class LocalRoutePool extends RouteSpecificPool<String, IOSession, LocalPoolEntry> {

        public LocalRoutePool() {
            super("whatever");
        }

        @Override
        protected LocalPoolEntry createEntry(final String route, final IOSession session) {
            return new LocalPoolEntry(route, session);
        }

        @Override
        protected void closeEntry(final LocalPoolEntry entry) {
            IOSession session = entry.getConnection();
            session.close();
        }

    };

    @Test
    public void testShutdown() throws Exception {
        LocalRoutePool pool = new LocalRoutePool();
        IOSession session1 = Mockito.mock(IOSession.class);
        SessionRequest sessionRequest1 = Mockito.mock(SessionRequest.class);
        Mockito.when(sessionRequest1.getSession()).thenReturn(session1);
        BasicFuture<LocalPoolEntry> future1 = new BasicFuture<LocalPoolEntry>(null);
        pool.addPending(sessionRequest1, future1);
        IOSession session2 = Mockito.mock(IOSession.class);
        SessionRequest sessionRequest2 = Mockito.mock(SessionRequest.class);
        Mockito.when(sessionRequest2.getSession()).thenReturn(session2);
        BasicFuture<LocalPoolEntry> future2 = new BasicFuture<LocalPoolEntry>(null);
        pool.addPending(sessionRequest2, future2);
        IOSession session3 = Mockito.mock(IOSession.class);
        SessionRequest sessionRequest3 = Mockito.mock(SessionRequest.class);
        Mockito.when(sessionRequest3.getSession()).thenReturn(session3);
        BasicFuture<LocalPoolEntry> future3 = new BasicFuture<LocalPoolEntry>(null);
        pool.addPending(sessionRequest3, future3);

        LocalPoolEntry entry1 = pool.completed(sessionRequest1, session1);
        Assert.assertNotNull(entry1);
        LocalPoolEntry entry2 = pool.completed(sessionRequest2, session2);
        Assert.assertNotNull(entry2);

        pool.free(entry1, true);

        Assert.assertEquals(3, pool.getAllocatedCount());
        Assert.assertEquals(1, pool.getAvailableCount());
        Assert.assertEquals(1, pool.getLeasedCount());
        Assert.assertEquals(1, pool.getPendingCount());

        pool.shutdown();

        Assert.assertEquals(0, pool.getAllocatedCount());
        Assert.assertEquals(0, pool.getAvailableCount());
        Assert.assertEquals(0, pool.getLeasedCount());
        Assert.assertEquals(0, pool.getPendingCount());

        Mockito.verify(sessionRequest3).cancel();
        Mockito.verify(session2).close();
        Mockito.verify(session1).close();
    }

}

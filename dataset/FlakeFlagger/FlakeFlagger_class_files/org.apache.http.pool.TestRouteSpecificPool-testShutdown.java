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
package org.apache.http.pool;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.http.HttpConnection;
import org.junit.Test;
import org.mockito.Mockito;

public class TestRouteSpecificPool {

    private static final String ROUTE = "whatever";

    static class LocalPoolEntry extends PoolEntry<String, HttpConnection> {

        public LocalPoolEntry(final String route, final HttpConnection conn) {
            super(null, route, conn);
        }

    }

    static class LocalRoutePool extends RouteSpecificPool<String, HttpConnection, LocalPoolEntry> {

        public LocalRoutePool() {
            super(ROUTE);
        }

        @Override
        protected LocalPoolEntry createEntry(final HttpConnection conn) {
            return new LocalPoolEntry(getRoute(), conn);
        }

        @Override
        protected void closeEntry(LocalPoolEntry entry) {
            HttpConnection conn = entry.getConnection();
            try {
                conn.close();
            } catch (IOException ignore) {
            }
        }

    };

    @SuppressWarnings("unchecked")
    @Test
    public void testShutdown() throws Exception {
        LocalRoutePool pool = new LocalRoutePool();
        HttpConnection conn1 = Mockito.mock(HttpConnection.class);
        LocalPoolEntry entry1 = pool.add(conn1);
        HttpConnection conn2 = Mockito.mock(HttpConnection.class);
        LocalPoolEntry entry2 = pool.add(conn2);

        PoolEntryFuture<LocalPoolEntry> future1 = Mockito.mock(PoolEntryFuture.class);
        pool.queue(future1);

        Assert.assertNotNull(entry1);
        Assert.assertNotNull(entry2);

        pool.free(entry1, true);

        Assert.assertEquals(2, pool.getAllocatedCount());
        Assert.assertEquals(1, pool.getAvailableCount());
        Assert.assertEquals(1, pool.getLeasedCount());
        Assert.assertEquals(1, pool.getPendingCount());

        pool.shutdown();

        Assert.assertEquals(0, pool.getAllocatedCount());
        Assert.assertEquals(0, pool.getAvailableCount());
        Assert.assertEquals(0, pool.getLeasedCount());
        Assert.assertEquals(0, pool.getPendingCount());

        Mockito.verify(future1).cancel(true);
        Mockito.verify(conn2).close();
        Mockito.verify(conn1).close();
    }

}

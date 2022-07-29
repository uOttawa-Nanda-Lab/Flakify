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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import junit.framework.Assert;

import org.apache.http.HttpConnection;
import org.junit.Test;
import org.mockito.Mockito;

public class TestConnPool {

    private static final int GRACE_PERIOD = 10000;

    interface HttpConnectionFactory {

        HttpConnection create(String route) throws IOException;

    }

    static class LocalPoolEntry extends PoolEntry<String, HttpConnection> {

        public LocalPoolEntry(final String route, final HttpConnection conn) {
            super(null, route, conn);
        }

    }

    static class LocalConnPool extends AbstractConnPool<String, HttpConnection, LocalPoolEntry> {

        private final HttpConnectionFactory connFactory;

        public LocalConnPool(
                final HttpConnectionFactory connFactory,
                int defaultMaxPerRoute, int maxTotal) {
            super(defaultMaxPerRoute, maxTotal);
            this.connFactory = connFactory;
        }

        @Override
        protected HttpConnection createConnection(final String route) throws IOException {
            return this.connFactory.create(route);
        }

        @Override
        protected LocalPoolEntry createEntry(final String route, final HttpConnection conn) {
            return new LocalPoolEntry(route, conn);
        }

        @Override
        protected void closeEntry(final LocalPoolEntry entry) {
            HttpConnection conn = entry.getConnection();
            try {
                conn.close();
            } catch (IOException ignore) {
            }
        }

    }

    @Test
    public void testEmptyPool() throws Exception {
        HttpConnectionFactory connFactory = Mockito.mock(HttpConnectionFactory.class);
        LocalConnPool pool = new LocalConnPool(connFactory, 2, 10);
        pool.setDefaultMaxPerRoute(5);
        pool.setMaxPerRoute("somehost", 3);
        PoolStats totals = pool.getTotalStats();
        Assert.assertEquals(0, totals.getAvailable());
        Assert.assertEquals(0, totals.getLeased());
        Assert.assertEquals(10, totals.getMax());
        PoolStats stats = pool.getStats("somehost");
        Assert.assertEquals(0, stats.getAvailable());
        Assert.assertEquals(0, stats.getLeased());
        Assert.assertEquals(3, stats.getMax());
        Assert.assertEquals("[leased: []][available: []][pending: []]", pool.toString());
    }

    static class GetPoolEntryThread extends Thread {

        private final Future<LocalPoolEntry> future;
        private final long time;
        private final TimeUnit tunit;

        private volatile LocalPoolEntry entry;
        private volatile Exception ex;

        GetPoolEntryThread(final Future<LocalPoolEntry> future, final long time, final TimeUnit tunit) {
            super();
            this.future = future;
            this.time = time;
            this.tunit = tunit;
            setDaemon(true);
        }

        GetPoolEntryThread(final Future<LocalPoolEntry> future) {
            this(future, 1000, TimeUnit.SECONDS);
        }

        @Override
        public void run() {
            try {
                this.entry = this.future.get(this.time, this.tunit);
            } catch (Exception ex) {
                this.ex = ex;
            }
        }

        public boolean isDone() {
            return this.future.isDone();
        }

        public LocalPoolEntry getEntry() {
            return this.entry;
        }

        public Exception getException() {
            return this.ex;
        }

    }

}

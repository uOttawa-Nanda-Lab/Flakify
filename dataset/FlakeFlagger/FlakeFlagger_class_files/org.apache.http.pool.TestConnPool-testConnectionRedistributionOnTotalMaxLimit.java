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

    @Test
    public void testConnectionRedistributionOnTotalMaxLimit() throws Exception {
        HttpConnectionFactory connFactory = Mockito.mock(HttpConnectionFactory.class);

        HttpConnection conn1 = Mockito.mock(HttpConnection.class);
        HttpConnection conn2 = Mockito.mock(HttpConnection.class);
        HttpConnection conn3 = Mockito.mock(HttpConnection.class);
        Mockito.when(connFactory.create(Mockito.eq("somehost"))).thenReturn(conn1, conn2, conn3);

        HttpConnection conn4 = Mockito.mock(HttpConnection.class);
        HttpConnection conn5 = Mockito.mock(HttpConnection.class);
        Mockito.when(connFactory.create(Mockito.eq("otherhost"))).thenReturn(conn4, conn5);

        LocalConnPool pool = new LocalConnPool(connFactory, 2, 10);
        pool.setMaxPerRoute("somehost", 2);
        pool.setMaxPerRoute("otherhost", 2);
        pool.setMaxTotal(2);

        Future<LocalPoolEntry> future1 = pool.lease("somehost", null);
        GetPoolEntryThread t1 = new GetPoolEntryThread(future1);
        t1.start();
        Future<LocalPoolEntry> future2 = pool.lease("somehost", null);
        GetPoolEntryThread t2 = new GetPoolEntryThread(future2);
        t2.start();

        t1.join(GRACE_PERIOD);
        Assert.assertTrue(future1.isDone());
        LocalPoolEntry entry1 = t1.getEntry();
        Assert.assertNotNull(entry1);
        t2.join(GRACE_PERIOD);
        Assert.assertTrue(future2.isDone());
        LocalPoolEntry entry2 = t2.getEntry();
        Assert.assertNotNull(entry2);

        Future<LocalPoolEntry> future3 = pool.lease("otherhost", null);
        GetPoolEntryThread t3 = new GetPoolEntryThread(future3);
        t3.start();
        Future<LocalPoolEntry> future4 = pool.lease("otherhost", null);
        GetPoolEntryThread t4 = new GetPoolEntryThread(future4);
        t4.start();

        Assert.assertFalse(t3.isDone());
        Assert.assertFalse(t4.isDone());

        Mockito.verify(connFactory, Mockito.times(2)).create(Mockito.eq("somehost"));
        Mockito.verify(connFactory, Mockito.never()).create(Mockito.eq("otherhost"));

        PoolStats totals = pool.getTotalStats();
        Assert.assertEquals(0, totals.getAvailable());
        Assert.assertEquals(2, totals.getLeased());

        pool.release(entry1, true);
        pool.release(entry2, true);

        t3.join(GRACE_PERIOD);
        Assert.assertTrue(future3.isDone());
        LocalPoolEntry entry3 = t3.getEntry();
        Assert.assertNotNull(entry3);
        t4.join(GRACE_PERIOD);
        Assert.assertTrue(future4.isDone());
        LocalPoolEntry entry4 = t4.getEntry();
        Assert.assertNotNull(entry4);

        Mockito.verify(connFactory, Mockito.times(2)).create(Mockito.eq("somehost"));
        Mockito.verify(connFactory, Mockito.times(2)).create(Mockito.eq("otherhost"));

        totals = pool.getTotalStats();
        Assert.assertEquals(0, totals.getAvailable());
        Assert.assertEquals(2, totals.getLeased());

        Future<LocalPoolEntry> future5 = pool.lease("somehost", null);
        GetPoolEntryThread t5 = new GetPoolEntryThread(future5);
        t5.start();
        Future<LocalPoolEntry> future6 = pool.lease("otherhost", null);
        GetPoolEntryThread t6 = new GetPoolEntryThread(future6);
        t6.start();

        pool.release(entry3, true);
        pool.release(entry4, true);

        t5.join(GRACE_PERIOD);
        Assert.assertTrue(future5.isDone());
        LocalPoolEntry entry5 = t5.getEntry();
        Assert.assertNotNull(entry5);
        t6.join(GRACE_PERIOD);
        Assert.assertTrue(future6.isDone());
        LocalPoolEntry entry6 = t6.getEntry();
        Assert.assertNotNull(entry6);

        Mockito.verify(connFactory, Mockito.times(3)).create(Mockito.eq("somehost"));
        Mockito.verify(connFactory, Mockito.times(2)).create(Mockito.eq("otherhost"));

        totals = pool.getTotalStats();
        Assert.assertEquals(0, totals.getAvailable());
        Assert.assertEquals(2, totals.getLeased());

        pool.release(entry5, true);
        pool.release(entry6, true);

        totals = pool.getTotalStats();
        Assert.assertEquals(2, totals.getAvailable());
        Assert.assertEquals(0, totals.getLeased());
    }

}

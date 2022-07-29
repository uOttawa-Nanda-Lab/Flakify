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
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.http.concurrent.BasicFuture;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOSession;
import org.apache.http.nio.reactor.SessionRequest;
import org.apache.http.nio.reactor.SessionRequestCallback;
import org.apache.http.pool.PoolEntry;
import org.apache.http.pool.PoolStats;
import org.junit.Test;
import org.mockito.Mockito;

public class TestNIOConnPool {

    static class LocalPoolEntry extends PoolEntry<String, IOSession> {

        public LocalPoolEntry(final String route, final IOSession conn) {
            super(null, route, conn);
        }

    }

    static class LocalSessionPool extends AbstractNIOConnPool<String, IOSession, LocalPoolEntry> {

        public LocalSessionPool(
                final ConnectingIOReactor ioreactor, int defaultMaxPerRoute, int maxTotal) {
            super(ioreactor, defaultMaxPerRoute, maxTotal);
        }

        @Override
        protected SocketAddress resolveRemoteAddress(final String route) {
            return InetSocketAddress.createUnresolved(route, 80);
        }

        @Override
        protected SocketAddress resolveLocalAddress(final String route) {
            return InetSocketAddress.createUnresolved(route, 80);
        }

        @Override
        protected IOSession createConnection(final String route, final IOSession session) {
            return session;
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

    }

    @Test public void testStatefulConnectionRedistributionOnPerRouteMaxLimit() throws Exception{IOSession iosession1=Mockito.mock(IOSession.class);SessionRequest sessionRequest1=Mockito.mock(SessionRequest.class);Mockito.when(sessionRequest1.getAttachment()).thenReturn("somehost");Mockito.when(sessionRequest1.getSession()).thenReturn(iosession1);IOSession iosession2=Mockito.mock(IOSession.class);SessionRequest sessionRequest2=Mockito.mock(SessionRequest.class);Mockito.when(sessionRequest2.getAttachment()).thenReturn("somehost");Mockito.when(sessionRequest2.getSession()).thenReturn(iosession2);IOSession iosession3=Mockito.mock(IOSession.class);SessionRequest sessionRequest3=Mockito.mock(SessionRequest.class);Mockito.when(sessionRequest3.getAttachment()).thenReturn("somehost");Mockito.when(sessionRequest3.getSession()).thenReturn(iosession3);ConnectingIOReactor ioreactor=Mockito.mock(ConnectingIOReactor.class);Mockito.when(ioreactor.connect(Mockito.eq(InetSocketAddress.createUnresolved("somehost",80)),Mockito.any(SocketAddress.class),Mockito.any(),Mockito.any(SessionRequestCallback.class))).thenReturn(sessionRequest1,sessionRequest2,sessionRequest3);LocalSessionPool pool=new LocalSessionPool(ioreactor,2,10);pool.setMaxPerRoute("somehost",2);pool.setMaxTotal(2);Future<LocalPoolEntry> future1=pool.lease("somehost",null);Future<LocalPoolEntry> future2=pool.lease("somehost",null);Mockito.verify(ioreactor,Mockito.times(2)).connect(Mockito.eq(InetSocketAddress.createUnresolved("somehost",80)),Mockito.any(SocketAddress.class),Mockito.any(),Mockito.any(SessionRequestCallback.class));pool.requestCompleted(sessionRequest1);pool.requestCompleted(sessionRequest2);Assert.assertTrue(future1.isDone());LocalPoolEntry entry1=future1.get();Assert.assertNotNull(entry1);Assert.assertTrue(future2.isDone());LocalPoolEntry entry2=future2.get();Assert.assertNotNull(entry2);PoolStats totals=pool.getTotalStats();Assert.assertEquals(0,totals.getAvailable());Assert.assertEquals(2,totals.getLeased());Assert.assertEquals(0,totals.getPending());entry1.setState("some-stuff");pool.release(entry1,true);entry2.setState("some-stuff");pool.release(entry2,true);Future<LocalPoolEntry> future3=pool.lease("somehost","some-stuff");Future<LocalPoolEntry> future4=pool.lease("somehost","some-stuff");Assert.assertTrue(future1.isDone());LocalPoolEntry entry3=future3.get();Assert.assertNotNull(entry3);Assert.assertTrue(future4.isDone());LocalPoolEntry entry4=future4.get();Assert.assertNotNull(entry4);Mockito.verify(ioreactor,Mockito.times(2)).connect(Mockito.eq(InetSocketAddress.createUnresolved("somehost",80)),Mockito.any(SocketAddress.class),Mockito.any(),Mockito.any(SessionRequestCallback.class));pool.release(entry3,true);pool.release(entry4,true);totals=pool.getTotalStats();Assert.assertEquals(2,totals.getAvailable());Assert.assertEquals(0,totals.getLeased());Assert.assertEquals(0,totals.getPending());Future<LocalPoolEntry> future5=pool.lease("somehost","some-other-stuff");Assert.assertFalse(future5.isDone());Mockito.verify(ioreactor,Mockito.times(3)).connect(Mockito.eq(InetSocketAddress.createUnresolved("somehost",80)),Mockito.any(SocketAddress.class),Mockito.any(),Mockito.any(SessionRequestCallback.class));Mockito.verify(iosession1).close();Mockito.verify(iosession2,Mockito.never()).close();totals=pool.getTotalStats();Assert.assertEquals(1,totals.getAvailable());Assert.assertEquals(0,totals.getLeased());Assert.assertEquals(1,totals.getPending());}

}

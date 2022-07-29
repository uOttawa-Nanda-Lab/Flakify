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
package org.apache.http.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import junit.framework.Assert;

import org.junit.Test;

public class TestBasicFuture {

    @Test public void testAsyncFailed() throws Exception{final BasicFuture<Object> future=new BasicFuture<Object>(null);final Exception boom=new Exception();Thread t=new Thread(){@Override public void run(){try {Thread.sleep(100);future.failed(boom);} catch (InterruptedException ex){}}};t.setDaemon(true);t.start();try {future.get(60,TimeUnit.SECONDS);} catch (ExecutionException ex){Assert.assertSame(boom,ex.getCause());}Assert.assertTrue(future.isDone());Assert.assertFalse(future.isCancelled());}

}

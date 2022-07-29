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

    @Test public void testCompletedWithTimeout() throws Exception{BasicFutureCallback<Object> callback=new BasicFutureCallback<Object>();BasicFuture<Object> future=new BasicFuture<Object>(callback);Assert.assertFalse(future.isDone());Object result=new Object();Exception boom=new Exception();future.completed(result);future.failed(boom);Assert.assertTrue(callback.isCompleted());Assert.assertSame(result,callback.getResult());Assert.assertFalse(callback.isFailed());Assert.assertNull(callback.getException());Assert.assertFalse(callback.isCancelled());Assert.assertSame(result,future.get(1,TimeUnit.MILLISECONDS));Assert.assertTrue(future.isDone());Assert.assertFalse(future.isCancelled());}

}

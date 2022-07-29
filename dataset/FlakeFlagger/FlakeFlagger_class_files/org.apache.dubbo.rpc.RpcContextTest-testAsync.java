/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.rpc;

import org.apache.dubbo.common.URL;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RpcContextTest {

    @Test
    public void testAsync() {

        CompletableFuture<Object> future = new CompletableFuture<>();
        AsyncContext asyncContext = new AsyncContextImpl(future);

        RpcContext rpcContext = RpcContext.getContext();
        Assert.assertFalse(rpcContext.isAsyncStarted());

        rpcContext.setAsyncContext(asyncContext);
        Assert.assertFalse(rpcContext.isAsyncStarted());

        RpcContext.startAsync();
        Assert.assertTrue(rpcContext.isAsyncStarted());

        asyncContext.write(new Object());
        Assert.assertTrue(future.isDone());

        rpcContext.stopAsync();
        Assert.assertTrue(rpcContext.isAsyncStarted());
    }

}

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
    public void testAddress() {
        RpcContext context = RpcContext.getContext();
        context.setLocalAddress("127.0.0.1", 20880);
        Assert.assertTrue(context.getLocalAddress().getPort() == 20880);
        Assert.assertEquals("127.0.0.1:20880", context.getLocalAddressString());

        context.setRemoteAddress("127.0.0.1", 20880);
        Assert.assertTrue(context.getRemoteAddress().getPort() == 20880);
        Assert.assertEquals("127.0.0.1:20880", context.getRemoteAddressString());

        context.setRemoteAddress("127.0.0.1", -1);
        context.setLocalAddress("127.0.0.1", -1);
        Assert.assertTrue(context.getRemoteAddress().getPort() == 0);
        Assert.assertTrue(context.getLocalAddress().getPort() == 0);
        Assert.assertEquals("127.0.0.1", context.getRemoteHostName());
        Assert.assertEquals("127.0.0.1", context.getLocalHostName());
    }

}

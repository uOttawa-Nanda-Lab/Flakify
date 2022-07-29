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

package org.apache.dubbo.remoting.exchange.support;

import org.apache.dubbo.remoting.Channel;
import org.apache.dubbo.remoting.TimeoutException;
import org.apache.dubbo.remoting.exchange.Request;
import org.apache.dubbo.remoting.handler.MockedChannel;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultFutureTest {

    private static final AtomicInteger index = new AtomicInteger();

    @Test public void newFuture(){DefaultFuture future=defaultFuture(3000);Assert.assertNotNull("new future return null",future);}

    /**
     * mock a default future
     */
    private DefaultFuture defaultFuture(int timeout) {
        Channel channel = new MockedChannel();
        Request request = new Request(index.getAndIncrement());
        return DefaultFuture.newFuture(channel, request, timeout);
    }

}
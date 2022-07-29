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
package org.apache.dubbo.remoting.handler;

import org.apache.dubbo.remoting.ExecutionException;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.exchange.Request;
import org.apache.dubbo.remoting.exchange.Response;
import org.apache.dubbo.remoting.transport.dispatcher.connection.ConnectionOrderedChannelHandler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;


public class ConnectChannelHandlerTest extends WrappedChannelHandlerTest {

    @Before
    public void setUp() throws Exception {
        handler = new ConnectionOrderedChannelHandler(new BizChannelHander(true), url);
    }

    @Test(expected=ExecutionException.class) public void test_Received_InvokeInExecuter() throws RemotingException{handler=new ConnectionOrderedChannelHandler(new BizChannelHander(false),url);ThreadPoolExecutor executor=(ThreadPoolExecutor)getField(handler,"SHARED_EXECUTOR",1);executor.shutdown();executor=(ThreadPoolExecutor)getField(handler,"executor",1);executor.shutdown();handler.received(new MockedChannel(),"");}
}
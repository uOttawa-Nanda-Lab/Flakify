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
package org.apache.dubbo.rpc.filter;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcResult;
import org.apache.dubbo.rpc.support.DemoService;
import org.apache.dubbo.rpc.support.Type;
import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * CompatibleFilterTest.java
 */
public class CompatibleFilterFilterTest {
    private Filter compatibleFilter = new CompatibleFilter();
    private Invocation invocation;
    private Invoker invoker;

    @Test public void testInvokerNonJsonNonPojoSerialization(){invocation=mock(Invocation.class);given(invocation.getMethodName()).willReturn("echo");given(invocation.getParameterTypes()).willReturn(new Class<?>[]{String.class});given(invocation.getArguments()).willReturn(new Object[]{"hello"});invoker=mock(Invoker.class);given(invoker.isAvailable()).willReturn(true);given(invoker.getInterface()).willReturn(DemoService.class);RpcResult result=new RpcResult();result.setValue(new String[]{"High"});given(invoker.invoke(invocation)).willReturn(result);URL url=URL.valueOf("test://test:11/test?group=dubbo&version=1.1");given(invoker.getUrl()).willReturn(url);Result filterResult=compatibleFilter.invoke(invoker,invocation);assertArrayEquals(new String[]{"High"},(String[])filterResult.getValue());}
}
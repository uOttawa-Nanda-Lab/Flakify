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
package org.apache.dubbo.rpc.cluster.support;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.RpcInvocation;
import org.apache.dubbo.rpc.RpcResult;
import org.apache.dubbo.rpc.cluster.Directory;
import org.apache.dubbo.rpc.cluster.directory.StaticDirectory;
import org.apache.dubbo.rpc.protocol.AbstractInvoker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * FailoverClusterInvokerTest
 *
 */
@SuppressWarnings("unchecked")
public class FailoverClusterInvokerTest {
    private List<Invoker<FailoverClusterInvokerTest>> invokers = new ArrayList<Invoker<FailoverClusterInvokerTest>>();
    private int retries = 5;
    private URL url = URL.valueOf("test://test:11/test?retries=" + retries);
    private Invoker<FailoverClusterInvokerTest> invoker1 = mock(Invoker.class);
    private Invoker<FailoverClusterInvokerTest> invoker2 = mock(Invoker.class);
    private RpcInvocation invocation = new RpcInvocation();
    private Directory<FailoverClusterInvokerTest> dic;
    private Result result = new RpcResult();

    /**
     * @throws java.lang.Exception
     */

    @Before
    public void setUp() throws Exception {

        dic = mock(Directory.class);

        given(dic.getUrl()).willReturn(url);
        given(dic.list(invocation)).willReturn(invokers);
        given(dic.getInterface()).willReturn(FailoverClusterInvokerTest.class);
        invocation.setMethodName("method1");

        invokers.add(invoker1);
        invokers.add(invoker2);
    }


    @Test public void testInvokeWithRuntimeException(){given(invoker1.invoke(invocation)).willThrow(new RuntimeException());given(invoker1.isAvailable()).willReturn(true);given(invoker1.getUrl()).willReturn(url);given(invoker1.getInterface()).willReturn(FailoverClusterInvokerTest.class);given(invoker2.invoke(invocation)).willThrow(new RuntimeException());given(invoker2.isAvailable()).willReturn(true);given(invoker2.getUrl()).willReturn(url);given(invoker2.getInterface()).willReturn(FailoverClusterInvokerTest.class);FailoverClusterInvoker<FailoverClusterInvokerTest> invoker=new FailoverClusterInvoker<FailoverClusterInvokerTest>(dic);try {invoker.invoke(invocation);fail();} catch (RpcException expected){assertEquals(0,expected.getCode());assertFalse(expected.getCause() instanceof RpcException);}}

    public static interface Demo {
    }

    public static class MockInvoker<T> extends AbstractInvoker<T> {
        URL url;
        boolean available = true;
        boolean destoryed = false;
        Result result;
        RpcException exception;
        Callable<?> callable;

        public MockInvoker(Class<T> type, URL url) {
            super(type, url);
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public void setException(RpcException exception) {
            this.exception = exception;
        }

        public void setCallable(Callable<?> callable) {
            this.callable = callable;
        }

        @Override
        protected Result doInvoke(Invocation invocation) throws Throwable {
            if (callable != null) {
                try {
                    callable.call();
                } catch (Exception e) {
                    throw new RpcException(e);
                }
            }
            if (exception != null) {
                throw exception;
            } else {
                return result;
            }
        }
    }

    public class MockDirectory<T> extends StaticDirectory<T> {
        public MockDirectory(URL url, List<Invoker<T>> invokers) {
            super(url, invokers);
        }

        @Override
        protected List<Invoker<T>> doList(Invocation invocation) throws RpcException {
            return new ArrayList<Invoker<T>>(super.doList(invocation));
        }
    }
}
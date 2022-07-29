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
package org.apache.dubbo.rpc.cluster.router.condition;


import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcInvocation;
import org.apache.dubbo.rpc.cluster.Router;
import org.apache.dubbo.rpc.cluster.router.MockInvoker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConditionRouterTest {

    private URL SCRIPT_URL = URL.valueOf("condition://0.0.0.0/com.foo.BarService");

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    private URL getRouteUrl(String rule) {
        return SCRIPT_URL.addParameterAndEncoded(Constants.RULE_KEY, rule);
    }

    @Test public void testRoute_methodRoute(){Invocation invocation=new RpcInvocation("getFoo",new Class<?>[0],new Object[0]);Router router=new ConditionRouterFactory().getRouter(getRouteUrl("methods=getFoo => host = 1.2.3.4"));boolean matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService?methods=setFoo,getFoo,findFoo"),invocation);Assert.assertEquals(true,matchWhen);matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService?methods=getFoo"),invocation);Assert.assertEquals(true,matchWhen);Router router2=new ConditionRouterFactory().getRouter(getRouteUrl("methods=getFoo & host!=1.1.1.1 => host = 1.2.3.4"));matchWhen=((ConditionRouter)router2).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService?methods=getFoo"),invocation);Assert.assertEquals(false,matchWhen);Router router3=new ConditionRouterFactory().getRouter(getRouteUrl("methods=getFoo & host=1.1.1.1 => host = 1.2.3.4"));matchWhen=((ConditionRouter)router3).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService?methods=getFoo"),invocation);Assert.assertEquals(true,matchWhen);List<Invoker<String>> invokers=new ArrayList<Invoker<String>>();Invoker<String> invoker1=new MockInvoker<String>(URL.valueOf("dubbo://10.20.3.3:20880/com.foo.BarService"));Invoker<String> invoker2=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));Invoker<String> invoker3=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));invokers.add(invoker1);invokers.add(invoker2);invokers.add(invoker3);Router router4=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " & methods = getFoo => " + " host = 10.20.3.3").addParameter(Constants.FORCE_KEY,String.valueOf(true)));List<Invoker<String>> filteredInvokers1=router4.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),invocation);Assert.assertEquals(1,filteredInvokers1.size());Router router5=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " & methods = unvalidmethod => " + " host = 10.20.3.3").addParameter(Constants.FORCE_KEY,String.valueOf(true)));List<Invoker<String>> filteredInvokers2=router5.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),invocation);Assert.assertEquals(3,filteredInvokers2.size());}

}
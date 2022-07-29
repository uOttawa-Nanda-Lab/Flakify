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

    @Test public void testRoute_matchFilter(){List<Invoker<String>> invokers=new ArrayList<Invoker<String>>();Invoker<String> invoker1=new MockInvoker<String>(URL.valueOf("dubbo://10.20.3.3:20880/com.foo.BarService?default.serialization=fastjson"));Invoker<String> invoker2=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));Invoker<String> invoker3=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));invokers.add(invoker1);invokers.add(invoker2);invokers.add(invoker3);Router router1=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => " + " host = 10.20.3.3").addParameter(Constants.FORCE_KEY,String.valueOf(true)));Router router2=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => " + " host = 10.20.3.* & host != 10.20.3.3").addParameter(Constants.FORCE_KEY,String.valueOf(true)));Router router3=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => " + " host = 10.20.3.3  & host != 10.20.3.3").addParameter(Constants.FORCE_KEY,String.valueOf(true)));Router router4=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => " + " host = 10.20.3.2,10.20.3.3,10.20.3.4").addParameter(Constants.FORCE_KEY,String.valueOf(true)));Router router5=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => " + " host != 10.20.3.3").addParameter(Constants.FORCE_KEY,String.valueOf(true)));Router router6=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => " + " serialization = fastjson").addParameter(Constants.FORCE_KEY,String.valueOf(true)));List<Invoker<String>> filteredInvokers1=router1.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());List<Invoker<String>> filteredInvokers2=router2.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());List<Invoker<String>> filteredInvokers3=router3.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());List<Invoker<String>> filteredInvokers4=router4.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());List<Invoker<String>> filteredInvokers5=router5.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());List<Invoker<String>> filteredInvokers6=router6.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());Assert.assertEquals(1,filteredInvokers1.size());Assert.assertEquals(0,filteredInvokers2.size());Assert.assertEquals(0,filteredInvokers3.size());Assert.assertEquals(1,filteredInvokers4.size());Assert.assertEquals(2,filteredInvokers5.size());Assert.assertEquals(1,filteredInvokers6.size());}

}
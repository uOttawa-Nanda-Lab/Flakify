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

    @Test public void testRoute_matchWhen(){Invocation invocation=new RpcInvocation();Router router=new ConditionRouterFactory().getRouter(getRouteUrl(" => host = 1.2.3.4"));boolean matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);Assert.assertEquals(true,matchWhen);router=new ConditionRouterFactory().getRouter(getRouteUrl("host = 2.2.2.2,1.1.1.1,3.3.3.3 => host = 1.2.3.4"));matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);Assert.assertEquals(true,matchWhen);router=new ConditionRouterFactory().getRouter(getRouteUrl("host = 2.2.2.2,1.1.1.1,3.3.3.3 & host !=1.1.1.1 => host = 1.2.3.4"));matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);Assert.assertEquals(false,matchWhen);router=new ConditionRouterFactory().getRouter(getRouteUrl("host !=4.4.4.4 & host = 2.2.2.2,1.1.1.1,3.3.3.3 => host = 1.2.3.4"));matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);Assert.assertEquals(true,matchWhen);router=new ConditionRouterFactory().getRouter(getRouteUrl("host !=4.4.4.* & host = 2.2.2.2,1.1.1.1,3.3.3.3 => host = 1.2.3.4"));matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);Assert.assertEquals(true,matchWhen);router=new ConditionRouterFactory().getRouter(getRouteUrl("host = 2.2.2.2,1.1.1.*,3.3.3.3 & host != 1.1.1.1 => host = 1.2.3.4"));matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);Assert.assertEquals(false,matchWhen);router=new ConditionRouterFactory().getRouter(getRouteUrl("host = 2.2.2.2,1.1.1.*,3.3.3.3 & host != 1.1.1.2 => host = 1.2.3.4"));matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);Assert.assertEquals(true,matchWhen);}

}
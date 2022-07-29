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
package org.apache.dubbo.registry.dubbo;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.utils.LogUtil;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.registry.NotifyListener;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.RegistryFactory;
import org.apache.dubbo.registry.integration.RegistryDirectory;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.RpcInvocation;
import org.apache.dubbo.rpc.cluster.Router;
import org.apache.dubbo.rpc.cluster.loadbalance.LeastActiveLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;
import org.apache.dubbo.rpc.cluster.router.script.ScriptRouter;
import org.apache.dubbo.rpc.cluster.router.script.ScriptRouterFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptEngineManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.fail;

@SuppressWarnings({"rawtypes", "unchecked"})
public class RegistryDirectoryTest {

    private static boolean isScriptUnsupported = new ScriptEngineManager().getEngineByName("javascript") == null;
    RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
    Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    String service = DemoService.class.getName();
    RpcInvocation invocation = new RpcInvocation();
    URL noMeaningUrl = URL.valueOf("notsupport:/" + service + "?refer=" + URL.encode("interface=" + service));
    URL SERVICEURL = URL.valueOf("dubbo://127.0.0.1:9091/" + service + "?lazy=true&side=consumer");
    URL SERVICEURL2 = URL.valueOf("dubbo://127.0.0.1:9092/" + service + "?lazy=true&side=consumer");
    URL SERVICEURL3 = URL.valueOf("dubbo://127.0.0.1:9093/" + service + "?lazy=true&side=consumer");
    URL SERVICEURL_DUBBO_NOPATH = URL.valueOf("dubbo://127.0.0.1:9092" + "?lazy=true&side=consumer");

    @Before
    public void setUp() {
    }

    private RegistryDirectory getRegistryDirectory(URL url) {
        RegistryDirectory registryDirectory = new RegistryDirectory(URL.class, url);
        registryDirectory.setProtocol(protocol);
        // asert empty
        List invokers = registryDirectory.list(invocation);
        Assert.assertEquals(0, invokers.size());
        Assert.assertEquals(false, registryDirectory.isAvailable());
        return registryDirectory;
    }

    private RegistryDirectory getRegistryDirectory() {
        return getRegistryDirectory(noMeaningUrl);
    }

    /**
	 * Test override disables a specified service provider through enable=false It is expected that a specified service provider can be disable.
	 */@Test public void testNofity_To_Decrease_provider(){RegistryDirectory registryDirectory=getRegistryDirectory();invocation=new RpcInvocation();List<URL> durls=new ArrayList<URL>();durls.add(SERVICEURL.setHost("10.20.30.140"));durls.add(SERVICEURL.setHost("10.20.30.141"));registryDirectory.notify(durls);List<Invoker<?>> invokers=registryDirectory.list(invocation);Assert.assertEquals(2,invokers.size());durls=new ArrayList<URL>();durls.add(SERVICEURL.setHost("10.20.30.140"));registryDirectory.notify(durls);List<Invoker<?>> invokers2=registryDirectory.list(invocation);Assert.assertEquals(1,invokers2.size());Assert.assertEquals("10.20.30.140",invokers.get(0).getUrl().getHost());durls=new ArrayList<URL>();durls.add(URL.valueOf("empty://0.0.0.0?" + Constants.DISABLED_KEY + "=true&" + Constants.CATEGORY_KEY + "=" + Constants.CONFIGURATORS_CATEGORY));registryDirectory.notify(durls);List<Invoker<?>> invokers3=registryDirectory.list(invocation);Assert.assertEquals(1,invokers3.size());}

    

    // mock protocol

    enum Param {
        MORGAN,
    }

    private static interface DemoService {
    }

    private static class MockRegistry implements Registry {

        CountDownLatch latch;
        boolean destroyWithError;

        public MockRegistry(CountDownLatch latch) {
            this.latch = latch;
        }

        public MockRegistry(boolean destroyWithError) {
            this.destroyWithError = destroyWithError;
        }

        @Override
        public void register(URL url) {

        }

        @Override
        public void unregister(URL url) {

        }

        @Override
        public void subscribe(URL url, NotifyListener listener) {

        }

        @Override
        public void unsubscribe(URL url, NotifyListener listener) {
            if (latch != null) latch.countDown();
        }

        @Override
        public List<URL> lookup(URL url) {
            return null;
        }

        public URL getUrl() {
            return null;
        }

        @Override
        public boolean isAvailable() {
            return true;
        }

        @Override
        public void destroy() {
            if (destroyWithError) {
                throw new RpcException("test exception ignore.");
            }
        }
    }
}

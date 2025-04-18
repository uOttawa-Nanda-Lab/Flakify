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
import org.apache.dubbo.registry.NotifyListener;
import org.apache.dubbo.registry.RegistryFactory;
import org.apache.dubbo.registry.integration.RegistryProtocol;
import org.apache.dubbo.registry.support.AbstractRegistry;
import org.apache.dubbo.remoting.exchange.ExchangeClient;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.cluster.support.FailfastCluster;
import org.apache.dubbo.rpc.protocol.AbstractInvoker;
import org.apache.dubbo.rpc.protocol.dubbo.DubboInvoker;
import org.apache.dubbo.rpc.protocol.dubbo.DubboProtocol;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * RegistryProtocolTest
 *
 */
public class RegistryProtocolTest {

    static {
        SimpleRegistryExporter.exportIfAbsent(9090);
    }

    final String service = "org.apache.dubbo.registry.protocol.DemoService:1.0.0";
    final String serviceUrl = "dubbo://127.0.0.1:9453/" + service + "?notify=true&methods=test1,test2&side=con&side=consumer";
    final URL registryUrl = URL.valueOf("registry://127.0.0.1:9090/");
    final private Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();

    @Test public void testNotifyOverride() throws Exception{URL newRegistryUrl=registryUrl.addParameter(Constants.EXPORT_KEY,serviceUrl);Invoker<RegistryProtocolTest> invoker=new MockInvoker<RegistryProtocolTest>(RegistryProtocolTest.class,newRegistryUrl);Exporter<?> exporter=protocol.export(invoker);RegistryProtocol rprotocol=RegistryProtocol.getRegistryProtocol();NotifyListener listener=getListener(rprotocol);List<URL> urls=new ArrayList<URL>();urls.add(URL.valueOf("override://0.0.0.0/?timeout=1000"));urls.add(URL.valueOf("override://0.0.0.0/" + service + "?timeout=100"));urls.add(URL.valueOf("override://0.0.0.0/" + service + "?x=y"));listener.notify(urls);assertEquals(true,exporter.getInvoker().isAvailable());assertEquals("100",exporter.getInvoker().getUrl().getParameter("timeout"));assertEquals("y",exporter.getInvoker().getUrl().getParameter("x"));exporter.unexport();destroyRegistryProtocol();}


    private void destroyRegistryProtocol() {
        Protocol registry = RegistryProtocol.getRegistryProtocol();
        registry.destroy();
    }

    private NotifyListener getListener(RegistryProtocol protocol) throws Exception {
        return protocol.getOverrideListeners().values().iterator().next();
    }

    static class MockInvoker<T> extends AbstractInvoker<T> {
        public MockInvoker(Class<T> type, URL url) {
            super(type, url);
        }

        @Override
        protected Result doInvoke(Invocation invocation) throws Throwable {
            //do nothing
            return null;
        }
    }

    static class MockRegistry extends AbstractRegistry {

        public MockRegistry(URL url) {
            super(url);
        }

        @Override
        public boolean isAvailable() {
            return true;
        }
    }

}

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
package org.apache.dubbo.registry.support;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.registry.NotifyListener;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AbstractRegistryTest
 */
public class AbstractRegistryTest {

    private URL testUrl;
    private URL mockUrl;
    private NotifyListener listener;
    private AbstractRegistry abstractRegistry;
    private boolean notifySuccess;
    private Map<String, String> parametersConsumer = new LinkedHashMap<>();

    @Before
    public void init() {
        URL url = URL.valueOf("dubbo://192.168.0.2:2233");
        testUrl = URL.valueOf("http://192.168.0.3:9090/registry?check=false&file=N/A&interface=com.test");
        mockUrl = new URL("dubbo", "192.168.0.1", 2200);

        parametersConsumer.put("application", "demo-consumer");
        parametersConsumer.put("category", "consumer");
        parametersConsumer.put("check", "false");
        parametersConsumer.put("dubbo", "2.0.2");
        parametersConsumer.put("interface", "org.apache.dubbo.demo.DemoService");
        parametersConsumer.put("methods", "sayHello");
        parametersConsumer.put("pid", "1676");
        parametersConsumer.put("qos.port", "333333");
        parametersConsumer.put("side", "consumer");
        parametersConsumer.put("timestamp", String.valueOf(System.currentTimeMillis()));

        // init the object
        abstractRegistry = new AbstractRegistry(url) {
            @Override
            public boolean isAvailable() {
                return false;
            }
        };
        // init notify listener
        listener = urls -> notifySuccess = true;
        // notify flag
        notifySuccess = false;
    }

    /**
	 * Test method for  {@link org.apache.dubbo.registry.support.AbstractRegistry#notify(List)} .
	 */@Test public void testNotify() throws Exception{final AtomicReference<Boolean> notified=new AtomicReference<Boolean>(false);NotifyListener listner1=urls->notified.set(Boolean.TRUE);URL url1=new URL("dubbo","192.168.0.1",2200,parametersConsumer);abstractRegistry.subscribe(url1,listner1);NotifyListener listner2=urls->notified.set(Boolean.TRUE);URL url2=new URL("dubbo","192.168.0.2",2201,parametersConsumer);abstractRegistry.subscribe(url2,listner2);NotifyListener listner3=urls->notified.set(Boolean.TRUE);URL url3=new URL("dubbo","192.168.0.3",2202,parametersConsumer);abstractRegistry.subscribe(url3,listner3);List<URL> urls=new ArrayList<>();urls.add(url1);urls.add(url2);urls.add(url3);abstractRegistry.notify(url1,listner1,urls);Map<URL, Map<String, List<URL>>> map=abstractRegistry.getNotified();Assert.assertThat(true,Matchers.equalTo(map.containsKey(url1)));Assert.assertThat(false,Matchers.equalTo(map.containsKey(url2)));Assert.assertThat(false,Matchers.equalTo(map.containsKey(url3)));}

    private List<URL> getList() {
        List<URL> list = new ArrayList<>();
        URL url1 = new URL("dubbo", "192.168.0.1", 1000);
        URL url2 = new URL("dubbo", "192.168.0.2", 1001);
        URL url3 = new URL("dubbo", "192.168.0.3", 1002);
        list.add(url1);
        list.add(url2);
        list.add(url3);
        return list;
    }
}

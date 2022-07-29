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
package org.apache.dubbo.registry.multicast;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.registry.NotifyListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MulticastSocket;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MulticastRegistryTest {

    private String service = "org.apache.dubbo.test.injvmServie";
    private URL registryUrl = URL.valueOf("multicast://239.255.255.255/");
    private URL serviceUrl = URL.valueOf("dubbo://" + NetUtils.getLocalHost() + "/" + service
            + "?methods=test1,test2");
    private URL adminUrl = URL.valueOf("dubbo://" + NetUtils.getLocalHost() + "/*");
    private URL consumerUrl = URL.valueOf("subscribe://" + NetUtils.getLocalHost() + "/" + service + "?arg1=1&arg2=2");
    private MulticastRegistry registry = new MulticastRegistry(registryUrl);

    @Before
    public void setUp() {
        registry.register(serviceUrl);
    }

    /**
     * Test method for
     * {@link org.apache.dubbo.registry.multicast.MulticastRegistry#subscribe(URL url, org.apache.dubbo.registry.NotifyListener)}
     * .
     */
    @Test
    public void testSubscribe() {
        // verify listener
        registry.subscribe(consumerUrl, new NotifyListener() {
            @Override
            public void notify(List<URL> urls) {
                assertEquals(serviceUrl.toFullString(), urls.get(0).toFullString());

                Map<URL, Set<NotifyListener>> subscribed = registry.getSubscribed();
                assertEquals(consumerUrl, subscribed.keySet().iterator().next());
            }
        });
    }

}

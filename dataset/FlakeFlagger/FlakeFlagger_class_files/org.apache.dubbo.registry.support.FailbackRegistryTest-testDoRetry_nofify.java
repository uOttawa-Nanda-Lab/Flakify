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
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.registry.NotifyListener;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class FailbackRegistryTest {
    static String service;
    static URL serviceUrl;
    static URL registryUrl;
    MockRegistry registry;
    private int FAILED_PERIOD = 200;
    private int sleeptime = 100;
    private int trytimes = 5;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        service = "org.apache.dubbo.test.DemoService";
        serviceUrl = URL.valueOf("remote://127.0.0.1/demoservice?method=get");
        registryUrl = URL.valueOf("http://1.2.3.4:9090/registry?check=false&file=N/A").addParameter(Constants.REGISTRY_RETRY_PERIOD_KEY, String.valueOf(FAILED_PERIOD));
    }

    @Test public void testDoRetry_nofify() throws Exception{final AtomicInteger count=new AtomicInteger(0);NotifyListener listner=new NotifyListener(){@Override public void notify(List<URL> urls){count.incrementAndGet();if (count.get() == 1l){throw new RuntimeException("test exception please ignore");}}};registry=new MockRegistry(registryUrl,new CountDownLatch(0));registry.subscribe(serviceUrl.setProtocol(Constants.CONSUMER_PROTOCOL).addParameters(CollectionUtils.toStringMap("check","false")),listner);assertEquals(1,count.get());for (int i=0;i < trytimes;i++){System.out.println("failback notify retry ,times:" + i);if (count.get() == 2)break;Thread.sleep(sleeptime);}assertEquals(2,count.get());}


    private static class MockRegistry extends FailbackRegistry {
        CountDownLatch latch;
        private boolean bad = false;

        /**
         * @param url
         */
        public MockRegistry(URL url, CountDownLatch latch) {
            super(url);
            this.latch = latch;
        }

        /**
         * @param bad the bad to set
         */
        public void setBad(boolean bad) {
            this.bad = bad;
        }

        @Override
        protected void doRegister(URL url) {
            if (bad) {
                throw new RuntimeException("can not invoke!");
            }
            //System.out.println("do doRegister");
            latch.countDown();

        }

        @Override
        protected void doUnregister(URL url) {
            if (bad) {
                throw new RuntimeException("can not invoke!");
            }
            //System.out.println("do doUnregister");
            latch.countDown();

        }

        @Override
        protected void doSubscribe(URL url, NotifyListener listener) {
            if (bad) {
                throw new RuntimeException("can not invoke!");
            }
            //System.out.println("do doSubscribe");
            super.notify(url, listener, Arrays.asList(new URL[]{serviceUrl}));
            latch.countDown();
        }

        @Override
        protected void doUnsubscribe(URL url, NotifyListener listener) {
            if (bad) {
                throw new RuntimeException("can not invoke!");
            }
            //System.out.println("do doUnsubscribe");
            latch.countDown();
        }

        @Override
        protected void retry() {
            super.retry();
            if (bad) {
                throw new RuntimeException("can not invoke!");
            }
            //System.out.println("do retry");
            latch.countDown();
        }

        @Override
        public boolean isAvailable() {
            return true;
        }

    }
}

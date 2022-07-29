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
package org.apache.dubbo.rpc.protocol.dubbo;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.protocol.dubbo.support.ProtocolUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExplicitCallbackTest {

    protected Exporter<IDemoService> exporter = null;
    protected Exporter<IHelloService> hello_exporter = null;
    protected Invoker<IDemoService> reference = null;
    protected URL serviceURL = null;
    protected URL consumerUrl = null;
    // ============================A gorgeous line of segmentation================================================
    IDemoService demoProxy = null;

    void referService() {
        demoProxy = (IDemoService) ProtocolUtils.refer(IDemoService.class, consumerUrl);
    }

    @Before
    public void setUp() {
    }

    @Test public void TestCallbackNormal() throws Exception{initOrResetUrl(1,10000000);initOrResetService();final AtomicInteger count=new AtomicInteger(0);demoProxy.xxx(new IDemoCallback(){public String yyy(String msg){System.out.println("Recived callback: " + msg);count.incrementAndGet();return "ok";}},"other custom args",10,100);System.out.println("Async...");assertCallbackCount(10,100,count);destroyService();}

    private void assertCallbackCount(int runs, int sleep, AtomicInteger count) throws InterruptedException {
        int last = count.get();
        for (int i = 0; i < runs; i++) {
            if (last > runs) break;
            Thread.sleep(sleep * 2);
            System.out.println(count.get() + "  " + last);
            Assert.assertTrue(count.get() > last);
            last = count.get();
        }
        // has one sync callback
        Assert.assertEquals(runs + 1, count.get());
    }

    interface IDemoCallback {
        String yyy(String msg);
    }

    interface IHelloService {
        public String sayHello();
    }

    interface IDemoService {
        public String get();

        public int getCallbackCount();

        public void xxx(IDemoCallback callback, String arg1, int runs, int sleep);

        public void xxx2(IDemoCallback callback);

        public void unxxx2(IDemoCallback callback);
    }

    class HelloServiceImpl implements IHelloService {
        public String sayHello() {
            return "hello";
        }

    }

    class DemoServiceImpl implements IDemoService {
        private List<IDemoCallback> callbacks = new ArrayList<IDemoCallback>();
        private volatile Thread t = null;
        private volatile Lock lock = new ReentrantLock();

        public String get() {
            return "ok";
        }

        public void xxx(final IDemoCallback callback, String arg1, final int runs, final int sleep) {
            callback.yyy("Sync callback msg .This is callback data. arg1:" + arg1);
            Thread t = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < runs; i++) {
                        String ret = callback.yyy("server invoke callback : arg:" + System.currentTimeMillis());
                        System.out.println("callback result is :" + ret);
                        try {
                            Thread.sleep(sleep);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t.setDaemon(true);
            t.start();
            System.out.println("xxx invoke complete");
        }

        public int getCallbackCount() {
            return callbacks.size();
        }

        public void xxx2(IDemoCallback callback) {
            if (!callbacks.contains(callback)) {
                callbacks.add(callback);
            }
            startThread();
        }

        private void startThread() {
            if (t == null || callbacks.size() == 0) {
                try {
                    lock.lock();
                    t = new Thread(new Runnable() {
                        public void run() {
                            while (callbacks.size() > 0) {
                                try {
                                    List<IDemoCallback> callbacksCopy = new ArrayList<IDemoCallback>(callbacks);
                                    for (IDemoCallback callback : callbacksCopy) {
                                        try {
                                            callback.yyy("this is callback msg,current time is :" + System.currentTimeMillis());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            callbacks.remove(callback);
                                        }
                                    }
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    t.setDaemon(true);
                    t.start();
                } finally {
                    lock.unlock();
                }
            }
        }

        public void unxxx2(IDemoCallback callback) {
            if (!callbacks.contains(callback)) {
                throw new IllegalStateException("callback instance not found");
            }
            callbacks.remove(callback);
        }
    }
}
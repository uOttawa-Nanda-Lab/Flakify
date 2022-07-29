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
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.StaticContext;
import org.apache.dubbo.rpc.protocol.dubbo.support.ProtocolUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ImplicitCallBackTest {

    protected Exporter<IDemoService> exporter = null;
    protected Invoker<IDemoService> reference = null;

    protected URL serviceURL = null;
    protected URL consumerUrl = null;
    Method onReturnMethod;
    Method onThrowMethod;
    Method onInvokeMethod;
    NofifyImpl notify = new NofifyImpl();
    //================================================================================================
    IDemoService demoProxy = null;

    @Before
    public void setUp() throws SecurityException, NoSuchMethodException {
        onReturnMethod = Nofify.class.getMethod("onreturn", new Class<?>[]{Person.class, Integer.class});
        onThrowMethod = Nofify.class.getMethod("onthrow", new Class<?>[]{Throwable.class, Integer.class});
        onInvokeMethod = Nofify.class.getMethod("oninvoke", new Class<?>[]{Integer.class});
    }

    void referService() {
        demoProxy = (IDemoService) ProtocolUtils.refer(IDemoService.class, consumerUrl);
    }

    

    //================================================================================================

    @Test public void test_Sync_Onreturn() throws Exception{initOrResetUrl(false);initImplicitCallBackURL_onlyOnreturn();initOrResetService();int requestId=2;Person ret=demoProxy.get(requestId);Assert.assertEquals(requestId,ret.getId());for (int i=0;i < 10;i++){if (!notify.ret.containsKey(requestId)){Thread.sleep(200);} else {break;}}Assert.assertEquals(requestId,notify.ret.get(requestId).getId());destroyService();}

    interface Nofify {
        public void onreturn(Person msg, Integer id);

        public void onthrow(Throwable ex, Integer id);

        public void oninvoke(Integer id);
    }

    interface IDemoService {
        public Person get(int id);
    }

    public static class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        private int id;
        private String name;
        private int age;

        public Person(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + "]";
        }
    }

    class NofifyImpl implements Nofify {
        public List<Integer> inv = new ArrayList<Integer>();
        public Map<Integer, Person> ret = new HashMap<Integer, Person>();
        public Map<Integer, Throwable> errors = new HashMap<Integer, Throwable>();
        public boolean exd = false;

        public void onreturn(Person msg, Integer id) {
            System.out.println("onNotify:" + msg);
            ret.put(id, msg);
        }

        public void onthrow(Throwable ex, Integer id) {
            errors.put(id, ex);
//            ex.printStackTrace();
        }

        public void oninvoke(Integer id) {
            inv.add(id);
        }
    }

    class NormalDemoService implements IDemoService {
        public Person get(int id) {
            return new Person(id, "charles", 4);
        }
    }

    class ExceptionDemoExService implements IDemoService {
        public Person get(int id) {
            throw new RuntimeException("request persion id is :" + id);
        }
    }
}
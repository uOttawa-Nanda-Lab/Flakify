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
package org.apache.dubbo.config;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.beanutil.JavaBeanAccessor;
import org.apache.dubbo.common.beanutil.JavaBeanDescriptor;
import org.apache.dubbo.common.beanutil.JavaBeanSerializeUtil;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.serialize.Serialization;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.config.api.DemoException;
import org.apache.dubbo.config.api.DemoService;
import org.apache.dubbo.config.api.User;
import org.apache.dubbo.config.provider.impl.DemoServiceImpl;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * GenericServiceTest
 */
public class GenericServiceTest {
    @Test
    public void testGeneric() {
        DemoService server = new DemoServiceImpl();
        ProxyFactory proxyFactory = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
        Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
        URL url = URL.valueOf("dubbo://127.0.0.1:5342/" + DemoService.class.getName() + "?version=1.0.0");
        Exporter<DemoService> exporter = protocol.export(proxyFactory.getInvoker(server, DemoService.class, url));
        Invoker<DemoService> invoker = protocol.refer(DemoService.class, url);

        GenericService client = (GenericService) proxyFactory.getProxy(invoker, true);
        Object result = client.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"haha"});
        Assert.assertEquals("hello haha", result);

        org.apache.dubbo.rpc.service.GenericService newClient = (org.apache.dubbo.rpc.service.GenericService) proxyFactory.getProxy(invoker, true);
        Object res = newClient.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"hehe"});
        Assert.assertEquals("hello hehe", res);
        invoker.destroy();
        exporter.unexport();
    }

    @Test
    public void testGeneric2() {
        DemoService server = new DemoServiceImpl();
        ProxyFactory proxyFactory = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
        Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
        URL url = URL.valueOf("dubbo://127.0.0.1:5342/" + DemoService.class.getName() + "?version=1.0.0&generic=true");
        Exporter<DemoService> exporter = protocol.export(proxyFactory.getInvoker(server, DemoService.class, url));
        Invoker<GenericService> invoker = protocol.refer(GenericService.class, url);

        GenericService client = proxyFactory.getProxy(invoker, true);
        Object result = client.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"haha"});
        Assert.assertEquals("hello haha", result);

        Invoker<DemoService> invoker2 = protocol.refer(DemoService.class, url);

        GenericService client2 = (GenericService) proxyFactory.getProxy(invoker2, true);
        Object result2 = client2.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"haha"});
        Assert.assertEquals("hello haha", result2);

        invoker.destroy();
        exporter.unexport();
    }
    
    @Test
    public void testGenericServiceException() {
        ServiceConfig<GenericService> service = new ServiceConfig<GenericService>();
        service.setApplication(new ApplicationConfig("generic-provider"));
        service.setRegistry(new RegistryConfig("N/A"));
        service.setProtocol(new ProtocolConfig("dubbo", 29581));
        service.setInterface(DemoService.class.getName());
        service.setRef(new GenericService() {

            public Object $invoke(String method, String[] parameterTypes, Object[] args)
                    throws GenericException {
                if ("sayName".equals(method)) {
                    return "Generic " + args[0];
                }
                if ("throwDemoException".equals(method)) {
                    throw new GenericException(DemoException.class.getName(), "Generic");
                }
                if ("getUsers".equals(method)) {
                    return args[0];
                }
                return null;
            }
        });
        service.export();
        try {
            ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>();
            reference.setApplication(new ApplicationConfig("generic-consumer"));
            reference.setInterface(DemoService.class);
            reference.setUrl("dubbo://127.0.0.1:29581?generic=true&timeout=3000");
            DemoService demoService = reference.get();
            try {
                // say name
                Assert.assertEquals("Generic Haha", demoService.sayName("Haha"));
                // get users
                List<User> users = new ArrayList<User>();
                users.add(new User("Aaa"));
                users = demoService.getUsers(users);
                Assert.assertEquals("Aaa", users.get(0).getName());
                // throw demo exception
                try {
                    demoService.throwDemoException();
                    Assert.fail();
                } catch (DemoException e) {
                    Assert.assertEquals("Generic", e.getMessage());
                }
            } finally {
                reference.destroy();
            }
        } finally {
            service.unexport();
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGenericReferenceException() {
        ServiceConfig<DemoService> service = new ServiceConfig<DemoService>();
        service.setApplication(new ApplicationConfig("generic-provider"));
        service.setRegistry(new RegistryConfig("N/A"));
        service.setProtocol(new ProtocolConfig("dubbo", 29581));
        service.setInterface(DemoService.class.getName());
        service.setRef(new DemoServiceImpl());
        service.export();
        try {
            ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
            reference.setApplication(new ApplicationConfig("generic-consumer"));
            reference.setInterface(DemoService.class);
            reference.setUrl("dubbo://127.0.0.1:29581?scope=remote&timeout=3000");
            reference.setGeneric(true);
            GenericService genericService = reference.get();
            try {
                List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
                Map<String, Object> user = new HashMap<String, Object>();
                user.put("class", "org.apache.dubbo.config.api.User");
                user.put("name", "actual.provider");
                users.add(user);
                users = (List<Map<String, Object>>) genericService.$invoke("getUsers", new String[]{List.class.getName()}, new Object[]{users});
                Assert.assertEquals(1, users.size());
                Assert.assertEquals("actual.provider", users.get(0).get("name"));
            } finally {
                reference.destroy();
            }
        } finally {
            service.unexport();
        }
    }

    @Test
    public void testGenericSerializationJava() throws Exception {
        ServiceConfig<DemoService> service = new ServiceConfig<DemoService>();
        service.setApplication(new ApplicationConfig("generic-provider"));
        service.setRegistry(new RegistryConfig("N/A"));
        service.setProtocol(new ProtocolConfig("dubbo", 29581));
        service.setInterface(DemoService.class.getName());
        DemoServiceImpl ref = new DemoServiceImpl();
        service.setRef(ref);
        service.export();
        try {
            ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
            reference.setApplication(new ApplicationConfig("generic-consumer"));
            reference.setInterface(DemoService.class);
            reference.setUrl("dubbo://127.0.0.1:29581?scope=remote&timeout=3000");
            reference.setGeneric(Constants.GENERIC_SERIALIZATION_NATIVE_JAVA);
            GenericService genericService = reference.get();
            try {
                String name = "kimi";
                ByteArrayOutputStream bos = new ByteArrayOutputStream(512);
                ExtensionLoader.getExtensionLoader(Serialization.class)
                        .getExtension("nativejava").serialize(null, bos).writeObject(name);
                byte[] arg = bos.toByteArray();
                Object obj = genericService.$invoke("sayName", new String[]{String.class.getName()}, new Object[]{arg});
                Assert.assertTrue(obj instanceof byte[]);
                byte[] result = (byte[]) obj;
                Assert.assertEquals(ref.sayName(name), ExtensionLoader.getExtensionLoader(Serialization.class)
                        .getExtension("nativejava").deserialize(null, new ByteArrayInputStream(result)).readObject().toString());

                // getUsers
                List<User> users = new ArrayList<User>();
                User user = new User();
                user.setName(name);
                users.add(user);
                bos = new ByteArrayOutputStream(512);
                ExtensionLoader.getExtensionLoader(Serialization.class)
                        .getExtension("nativejava").serialize(null, bos).writeObject(users);
                obj = genericService.$invoke("getUsers",
                        new String[]{List.class.getName()},
                        new Object[]{bos.toByteArray()});
                Assert.assertTrue(obj instanceof byte[]);
                result = (byte[]) obj;
                Assert.assertEquals(users,
                        ExtensionLoader.getExtensionLoader(Serialization.class)
                                .getExtension("nativejava")
                                .deserialize(null, new ByteArrayInputStream(result))
                                .readObject());

                // echo(int)
                bos = new ByteArrayOutputStream(512);
                ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava")
                        .serialize(null, bos).writeObject(Integer.MAX_VALUE);
                obj = genericService.$invoke("echo", new String[]{int.class.getName()}, new Object[]{bos.toByteArray()});
                Assert.assertTrue(obj instanceof byte[]);
                Assert.assertEquals(Integer.MAX_VALUE,
                        ExtensionLoader.getExtensionLoader(Serialization.class)
                                .getExtension("nativejava")
                                .deserialize(null, new ByteArrayInputStream((byte[]) obj))
                                .readObject());

            } finally {
                reference.destroy();
            }
        } finally {
            service.unexport();
        }
    }

    @Test
    public void testGenericInvokeWithBeanSerialization() throws Exception {
        ServiceConfig<DemoService> service = new ServiceConfig<DemoService>();
        service.setApplication(new ApplicationConfig("bean-provider"));
        service.setInterface(DemoService.class);
        service.setRegistry(new RegistryConfig("N/A"));
        DemoServiceImpl impl = new DemoServiceImpl();
        service.setRef(impl);
        service.setProtocol(new ProtocolConfig("dubbo", 29581));
        service.export();
        ReferenceConfig<GenericService> reference = null;
        try {
            reference = new ReferenceConfig<GenericService>();
            reference.setApplication(new ApplicationConfig("bean-consumer"));
            reference.setInterface(DemoService.class);
            reference.setUrl("dubbo://127.0.0.1:29581?scope=remote&timeout=3000");
            reference.setGeneric(Constants.GENERIC_SERIALIZATION_BEAN);
            GenericService genericService = reference.get();
            User user = new User();
            user.setName("zhangsan");
            List<User> users = new ArrayList<User>();
            users.add(user);
            Object result = genericService.$invoke("getUsers", new String[]{ReflectUtils.getName(List.class)}, new Object[]{JavaBeanSerializeUtil.serialize(users, JavaBeanAccessor.METHOD)});
            Assert.assertTrue(result instanceof JavaBeanDescriptor);
            JavaBeanDescriptor descriptor = (JavaBeanDescriptor) result;
            Assert.assertTrue(descriptor.isCollectionType());
            Assert.assertEquals(1, descriptor.propertySize());
            descriptor = (JavaBeanDescriptor) descriptor.getProperty(0);
            Assert.assertTrue(descriptor.isBeanType());
            Assert.assertEquals(user.getName(), ((JavaBeanDescriptor) descriptor.getProperty("name")).getPrimitiveProperty());
        } finally {
            if (reference != null) {
                reference.destroy();
            }
            service.unexport();
        }
    }

    @Test
    public void testGenericImplementationWithBeanSerialization() throws Exception {
        final AtomicReference reference = new AtomicReference();
        ServiceConfig<GenericService> service = new ServiceConfig<GenericService>();
        service.setApplication(new ApplicationConfig("bean-provider"));
        service.setRegistry(new RegistryConfig("N/A"));
        service.setProtocol(new ProtocolConfig("dubbo", 29581));
        service.setInterface(DemoService.class.getName());
        service.setRef(new GenericService() {

            public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
                if ("getUsers".equals(method)) {
                    GenericParameter arg = new GenericParameter();
                    arg.method = method;
                    arg.parameterTypes = parameterTypes;
                    arg.arguments = args;
                    reference.set(arg);
                    return args[0];
                }
                if ("sayName".equals(method)) {
                    return null;
                }
                return args;
            }
        });
        service.export();
        ReferenceConfig<DemoService> ref = null;
        try {
            ref = new ReferenceConfig<DemoService>();
            ref.setApplication(new ApplicationConfig("bean-consumer"));
            ref.setInterface(DemoService.class);
            ref.setUrl("dubbo://127.0.0.1:29581?scope=remote&generic=bean&timeout=3000");
            DemoService demoService = ref.get();
            User user = new User();
            user.setName("zhangsan");
            List<User> users = new ArrayList<User>();
            users.add(user);
            List<User> result = demoService.getUsers(users);
            Assert.assertEquals(users.size(), result.size());
            Assert.assertEquals(user.getName(), result.get(0).getName());

            GenericParameter gp = (GenericParameter) reference.get();
            Assert.assertEquals("getUsers", gp.method);
            Assert.assertEquals(1, gp.parameterTypes.length);
            Assert.assertEquals(ReflectUtils.getName(List.class), gp.parameterTypes[0]);
            Assert.assertEquals(1, gp.arguments.length);
            Assert.assertTrue(gp.arguments[0] instanceof JavaBeanDescriptor);
            JavaBeanDescriptor descriptor = (JavaBeanDescriptor) gp.arguments[0];
            Assert.assertTrue(descriptor.isCollectionType());
            Assert.assertEquals(ArrayList.class.getName(), descriptor.getClassName());
            Assert.assertEquals(1, descriptor.propertySize());
            descriptor = (JavaBeanDescriptor) descriptor.getProperty(0);
            Assert.assertTrue(descriptor.isBeanType());
            Assert.assertEquals(User.class.getName(), descriptor.getClassName());
            Assert.assertEquals(user.getName(), ((JavaBeanDescriptor) descriptor.getProperty("name")).getPrimitiveProperty());
            Assert.assertNull(demoService.sayName("zhangsan"));
        } finally {
            if (ref != null) {
                ref.destroy();
            }
            service.unexport();
        }
    }

    protected static class GenericParameter {

        String method;

        String[] parameterTypes;

        Object[] arguments;
    }

}

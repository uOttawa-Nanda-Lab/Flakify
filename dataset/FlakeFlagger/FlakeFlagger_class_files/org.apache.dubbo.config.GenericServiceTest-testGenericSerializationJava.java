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

    @Test public void testGenericSerializationJava() throws Exception{ServiceConfig<DemoService> service=new ServiceConfig<DemoService>();service.setApplication(new ApplicationConfig("generic-provider"));service.setRegistry(new RegistryConfig("N/A"));service.setProtocol(new ProtocolConfig("dubbo",29581));service.setInterface(DemoService.class.getName());DemoServiceImpl ref=new DemoServiceImpl();service.setRef(ref);service.export();try {ReferenceConfig<GenericService> reference=new ReferenceConfig<GenericService>();reference.setApplication(new ApplicationConfig("generic-consumer"));reference.setInterface(DemoService.class);reference.setUrl("dubbo://127.0.0.1:29581?scope=remote&timeout=3000");reference.setGeneric(Constants.GENERIC_SERIALIZATION_NATIVE_JAVA);GenericService genericService=reference.get();try {String name="kimi";ByteArrayOutputStream bos=new ByteArrayOutputStream(512);ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava").serialize(null,bos).writeObject(name);byte[] arg=bos.toByteArray();Object obj=genericService.$invoke("sayName",new String[]{String.class.getName()},new Object[]{arg});Assert.assertTrue(obj instanceof byte[]);byte[] result=(byte[])obj;Assert.assertEquals(ref.sayName(name),ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava").deserialize(null,new ByteArrayInputStream(result)).readObject().toString());List<User> users=new ArrayList<User>();User user=new User();user.setName(name);users.add(user);bos=new ByteArrayOutputStream(512);ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava").serialize(null,bos).writeObject(users);obj=genericService.$invoke("getUsers",new String[]{List.class.getName()},new Object[]{bos.toByteArray()});Assert.assertTrue(obj instanceof byte[]);result=(byte[])obj;Assert.assertEquals(users,ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava").deserialize(null,new ByteArrayInputStream(result)).readObject());bos=new ByteArrayOutputStream(512);ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava").serialize(null,bos).writeObject(Integer.MAX_VALUE);obj=genericService.$invoke("echo",new String[]{int.class.getName()},new Object[]{bos.toByteArray()});Assert.assertTrue(obj instanceof byte[]);Assert.assertEquals(Integer.MAX_VALUE,ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava").deserialize(null,new ByteArrayInputStream((byte[])obj)).readObject());}  finally {reference.destroy();}}  finally {service.unexport();}}

    protected static class GenericParameter {

        String method;

        String[] parameterTypes;

        Object[] arguments;
    }

}

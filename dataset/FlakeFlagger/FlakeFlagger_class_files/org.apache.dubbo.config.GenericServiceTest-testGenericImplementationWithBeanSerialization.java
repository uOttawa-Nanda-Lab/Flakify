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

    @Test public void testGenericImplementationWithBeanSerialization() throws Exception{final AtomicReference reference=new AtomicReference();ServiceConfig<GenericService> service=new ServiceConfig<GenericService>();service.setApplication(new ApplicationConfig("bean-provider"));service.setRegistry(new RegistryConfig("N/A"));service.setProtocol(new ProtocolConfig("dubbo",29581));service.setInterface(DemoService.class.getName());service.setRef(new GenericService(){public Object $invoke(String method,String[] parameterTypes,Object[] args) throws GenericException{if ("getUsers".equals(method)){GenericParameter arg=new GenericParameter();arg.method=method;arg.parameterTypes=parameterTypes;arg.arguments=args;reference.set(arg);return args[0];}if ("sayName".equals(method)){return null;}return args;}});service.export();ReferenceConfig<DemoService> ref=null;try {ref=new ReferenceConfig<DemoService>();ref.setApplication(new ApplicationConfig("bean-consumer"));ref.setInterface(DemoService.class);ref.setUrl("dubbo://127.0.0.1:29581?scope=remote&generic=bean&timeout=3000");DemoService demoService=ref.get();User user=new User();user.setName("zhangsan");List<User> users=new ArrayList<User>();users.add(user);List<User> result=demoService.getUsers(users);Assert.assertEquals(users.size(),result.size());Assert.assertEquals(user.getName(),result.get(0).getName());GenericParameter gp=(GenericParameter)reference.get();Assert.assertEquals("getUsers",gp.method);Assert.assertEquals(1,gp.parameterTypes.length);Assert.assertEquals(ReflectUtils.getName(List.class),gp.parameterTypes[0]);Assert.assertEquals(1,gp.arguments.length);Assert.assertTrue(gp.arguments[0] instanceof JavaBeanDescriptor);JavaBeanDescriptor descriptor=(JavaBeanDescriptor)gp.arguments[0];Assert.assertTrue(descriptor.isCollectionType());Assert.assertEquals(ArrayList.class.getName(),descriptor.getClassName());Assert.assertEquals(1,descriptor.propertySize());descriptor=(JavaBeanDescriptor)descriptor.getProperty(0);Assert.assertTrue(descriptor.isBeanType());Assert.assertEquals(User.class.getName(),descriptor.getClassName());Assert.assertEquals(user.getName(),((JavaBeanDescriptor)descriptor.getProperty("name")).getPrimitiveProperty());Assert.assertNull(demoService.sayName("zhangsan"));}  finally {if (ref != null){ref.destroy();}service.unexport();}}

    protected static class GenericParameter {

        String method;

        String[] parameterTypes;

        Object[] arguments;
    }

}

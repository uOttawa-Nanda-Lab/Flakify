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

    @Test public void testGenericInvokeWithBeanSerialization() throws Exception{ServiceConfig<DemoService> service=new ServiceConfig<DemoService>();service.setApplication(new ApplicationConfig("bean-provider"));service.setInterface(DemoService.class);service.setRegistry(new RegistryConfig("N/A"));DemoServiceImpl impl=new DemoServiceImpl();service.setRef(impl);service.setProtocol(new ProtocolConfig("dubbo",29581));service.export();ReferenceConfig<GenericService> reference=null;try {reference=new ReferenceConfig<GenericService>();reference.setApplication(new ApplicationConfig("bean-consumer"));reference.setInterface(DemoService.class);reference.setUrl("dubbo://127.0.0.1:29581?scope=remote&timeout=3000");reference.setGeneric(Constants.GENERIC_SERIALIZATION_BEAN);GenericService genericService=reference.get();User user=new User();user.setName("zhangsan");List<User> users=new ArrayList<User>();users.add(user);Object result=genericService.$invoke("getUsers",new String[]{ReflectUtils.getName(List.class)},new Object[]{JavaBeanSerializeUtil.serialize(users,JavaBeanAccessor.METHOD)});Assert.assertTrue(result instanceof JavaBeanDescriptor);JavaBeanDescriptor descriptor=(JavaBeanDescriptor)result;Assert.assertTrue(descriptor.isCollectionType());Assert.assertEquals(1,descriptor.propertySize());descriptor=(JavaBeanDescriptor)descriptor.getProperty(0);Assert.assertTrue(descriptor.isBeanType());Assert.assertEquals(user.getName(),((JavaBeanDescriptor)descriptor.getProperty("name")).getPrimitiveProperty());}  finally {if (reference != null){reference.destroy();}service.unexport();}}

    protected static class GenericParameter {

        String method;

        String[] parameterTypes;

        Object[] arguments;
    }

}

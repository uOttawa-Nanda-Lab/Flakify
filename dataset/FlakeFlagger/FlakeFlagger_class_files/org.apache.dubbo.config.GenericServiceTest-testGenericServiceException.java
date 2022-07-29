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

    @Test public void testGenericServiceException(){ServiceConfig<GenericService> service=new ServiceConfig<GenericService>();service.setApplication(new ApplicationConfig("generic-provider"));service.setRegistry(new RegistryConfig("N/A"));service.setProtocol(new ProtocolConfig("dubbo",29581));service.setInterface(DemoService.class.getName());service.setRef(new GenericService(){public Object $invoke(String method,String[] parameterTypes,Object[] args) throws GenericException{if ("sayName".equals(method)){return "Generic " + args[0];}if ("throwDemoException".equals(method)){throw new GenericException(DemoException.class.getName(),"Generic");}if ("getUsers".equals(method)){return args[0];}return null;}});service.export();try {ReferenceConfig<DemoService> reference=new ReferenceConfig<DemoService>();reference.setApplication(new ApplicationConfig("generic-consumer"));reference.setInterface(DemoService.class);reference.setUrl("dubbo://127.0.0.1:29581?generic=true&timeout=3000");DemoService demoService=reference.get();try {Assert.assertEquals("Generic Haha",demoService.sayName("Haha"));List<User> users=new ArrayList<User>();users.add(new User("Aaa"));users=demoService.getUsers(users);Assert.assertEquals("Aaa",users.get(0).getName());try {demoService.throwDemoException();Assert.fail();} catch (DemoException e){Assert.assertEquals("Generic",e.getMessage());}}  finally {reference.destroy();}}  finally {service.unexport();}}

    protected static class GenericParameter {

        String method;

        String[] parameterTypes;

        Object[] arguments;
    }

}

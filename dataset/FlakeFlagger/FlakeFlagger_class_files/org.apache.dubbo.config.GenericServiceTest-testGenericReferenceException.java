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

    @SuppressWarnings("unchecked") @Test public void testGenericReferenceException(){ServiceConfig<DemoService> service=new ServiceConfig<DemoService>();service.setApplication(new ApplicationConfig("generic-provider"));service.setRegistry(new RegistryConfig("N/A"));service.setProtocol(new ProtocolConfig("dubbo",29581));service.setInterface(DemoService.class.getName());service.setRef(new DemoServiceImpl());service.export();try {ReferenceConfig<GenericService> reference=new ReferenceConfig<GenericService>();reference.setApplication(new ApplicationConfig("generic-consumer"));reference.setInterface(DemoService.class);reference.setUrl("dubbo://127.0.0.1:29581?scope=remote&timeout=3000");reference.setGeneric(true);GenericService genericService=reference.get();try {List<Map<String, Object>> users=new ArrayList<Map<String, Object>>();Map<String, Object> user=new HashMap<String, Object>();user.put("class","org.apache.dubbo.config.api.User");user.put("name","actual.provider");users.add(user);users=(List<Map<String, Object>>)genericService.$invoke("getUsers",new String[]{List.class.getName()},new Object[]{users});Assert.assertEquals(1,users.size());Assert.assertEquals("actual.provider",users.get(0).get("name"));}  finally {reference.destroy();}}  finally {service.unexport();}}

    protected static class GenericParameter {

        String method;

        String[] parameterTypes;

        Object[] arguments;
    }

}

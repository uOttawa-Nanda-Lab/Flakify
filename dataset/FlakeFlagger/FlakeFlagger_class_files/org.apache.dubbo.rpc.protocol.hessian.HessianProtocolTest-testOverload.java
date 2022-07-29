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
package org.apache.dubbo.rpc.protocol.hessian;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.beanutil.JavaBeanDescriptor;
import org.apache.dubbo.common.beanutil.JavaBeanSerializeUtil;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.serialize.ObjectInput;
import org.apache.dubbo.common.serialize.ObjectOutput;
import org.apache.dubbo.common.serialize.Serialization;
import org.apache.dubbo.common.serialize.nativejava.NativeJavaSerialization;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.ProxyFactory;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.protocol.hessian.HessianServiceImpl.MyException;

import org.apache.dubbo.rpc.service.GenericService;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * HessianProtocolTest
 */
public class HessianProtocolTest {

    @Test public void testOverload(){HessianServiceImpl server=new HessianServiceImpl();Assert.assertFalse(server.isCalled());ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();URL url=URL.valueOf("hessian://127.0.0.1:5342/" + HessianService.class.getName() + "?version=1.0.0&hessian.overload.method=true&hessian2.request=false");Exporter<HessianService> exporter=protocol.export(proxyFactory.getInvoker(server,HessianService.class,url));Invoker<HessianService> invoker=protocol.refer(HessianService.class,url);HessianService client=proxyFactory.getProxy(invoker);String result=client.sayHello("haha");Assert.assertEquals("Hello, haha",result);result=client.sayHello("haha",1);Assert.assertEquals("Hello, haha. ",result);invoker.destroy();exporter.unexport();}

}

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
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.ProxyFactory;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.protocol.dubbo.support.DemoService;
import org.apache.dubbo.rpc.protocol.dubbo.support.DemoServiceImpl;
import org.apache.dubbo.rpc.protocol.dubbo.support.NonSerialized;
import org.apache.dubbo.rpc.protocol.dubbo.support.RemoteService;
import org.apache.dubbo.rpc.protocol.dubbo.support.RemoteServiceImpl;
import org.apache.dubbo.rpc.protocol.dubbo.support.Type;
import org.apache.dubbo.rpc.service.EchoService;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * <code>ProxiesTest</code>
 */

public class DubboProtocolTest {
    private Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    private ProxyFactory proxy = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();

    @Test public void testDubboProtocolWithMina() throws Exception{DemoService service=new DemoServiceImpl();protocol.export(proxy.getInvoker(service,DemoService.class,URL.valueOf("dubbo://127.0.0.1:9010/" + DemoService.class.getName()).addParameter(Constants.SERVER_KEY,"mina")));service=proxy.getProxy(protocol.refer(DemoService.class,URL.valueOf("dubbo://127.0.0.1:9010/" + DemoService.class.getName()).addParameter(Constants.CLIENT_KEY,"mina")));for (int i=0;i < 10;i++){assertEquals(service.enumlength(new Type[]{}),Type.Lower);assertEquals(service.getSize(null),-1);assertEquals(service.getSize(new String[]{"","",""}),3);}Map<String, String> map=new HashMap<String, String>();map.put("aa","bb");for (int i=0;i < 10;i++){Set<String> set=service.keys(map);assertEquals(set.size(),1);assertEquals(set.iterator().next(),"aa");service.invoke("dubbo://127.0.0.1:9010/" + DemoService.class.getName() + "","invoke");}service=proxy.getProxy(protocol.refer(DemoService.class,URL.valueOf("dubbo://127.0.0.1:9010/" + DemoService.class.getName() + "?client=mina")));StringBuffer buf=new StringBuffer();for (int i=0;i < 1024 * 32 + 32;i++)buf.append('A');System.out.println(service.stringLength(buf.toString()));EchoService echo=proxy.getProxy(protocol.refer(EchoService.class,URL.valueOf("dubbo://127.0.0.1:9010/" + DemoService.class.getName() + "?client=mina")));for (int i=0;i < 10;i++){assertEquals(echo.$echo(buf.toString()),buf.toString());assertEquals(echo.$echo("test"),"test");assertEquals(echo.$echo("abcdefg"),"abcdefg");assertEquals(echo.$echo(1234),1234);}}
}
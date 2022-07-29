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
package org.apache.dubbo.rpc.protocol.injvm;


import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.ProxyFactory;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * <code>ProxiesTest</code>
 */

public class InjvmProtocolTest {

    static{
        InjvmProtocol injvm = InjvmProtocol.getInjvmProtocol();
    }

    private Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    private ProxyFactory proxy = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
    private List<Exporter<?>> exporters = new ArrayList<Exporter<?>>();

    @Test public void testIsInjvmRefer() throws Exception{DemoService service=new DemoServiceImpl();URL url=URL.valueOf("injvm://127.0.0.1/TestService").addParameter(Constants.INTERFACE_KEY,DemoService.class.getName());Exporter<?> exporter=protocol.export(proxy.getInvoker(service,DemoService.class,url));exporters.add(exporter);url=url.setProtocol("dubbo");assertTrue(InjvmProtocol.getInjvmProtocol().isInjvmRefer(url));url=url.addParameter(Constants.GROUP_KEY,"*").addParameter(Constants.VERSION_KEY,"*");assertTrue(InjvmProtocol.getInjvmProtocol().isInjvmRefer(url));url=URL.valueOf("fake://127.0.0.1/TestService").addParameter(Constants.SCOPE_KEY,Constants.SCOPE_LOCAL);assertTrue(InjvmProtocol.getInjvmProtocol().isInjvmRefer(url));url=URL.valueOf("fake://127.0.0.1/TestService").addParameter(Constants.LOCAL_PROTOCOL,true);assertTrue(InjvmProtocol.getInjvmProtocol().isInjvmRefer(url));url=URL.valueOf("fake://127.0.0.1/TestService").addParameter(Constants.SCOPE_KEY,Constants.SCOPE_REMOTE);assertFalse(InjvmProtocol.getInjvmProtocol().isInjvmRefer(url));url=URL.valueOf("fake://127.0.0.1/TestService").addParameter(Constants.GENERIC_KEY,true);assertFalse(InjvmProtocol.getInjvmProtocol().isInjvmRefer(url));}

}
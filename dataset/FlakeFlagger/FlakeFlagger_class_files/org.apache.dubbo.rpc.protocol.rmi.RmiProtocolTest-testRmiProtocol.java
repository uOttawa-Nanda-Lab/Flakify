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
package org.apache.dubbo.rpc.protocol.rmi;


import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.ProxyFactory;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.service.EchoService;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RmiProtocolTest {
    private Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    private ProxyFactory proxy = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();

    @Test public void testRmiProtocol() throws Exception{{DemoService service=new DemoServiceImpl();Exporter<?> rpcExporter=protocol.export(proxy.getInvoker(service,DemoService.class,URL.valueOf("rmi://127.0.0.1:9001/TestService")));service=proxy.getProxy(protocol.refer(DemoService.class,URL.valueOf("rmi://127.0.0.1:9001/TestService")));assertEquals(service.getSize(null),-1);assertEquals(service.getSize(new String[]{"","",""}),3);Object result=service.invoke("rmi://127.0.0.1:9001/TestService","invoke");assertEquals("rmi://127.0.0.1:9001/TestService:invoke",result);rpcExporter.unexport();}{RemoteService remoteService=new RemoteServiceImpl();Exporter<?> rpcExporter=protocol.export(proxy.getInvoker(remoteService,RemoteService.class,URL.valueOf("rmi://127.0.0.1:9002/remoteService")));remoteService=proxy.getProxy(protocol.refer(RemoteService.class,URL.valueOf("rmi://127.0.0.1:9002/remoteService")));remoteService.getThreadName();for (int i=0;i < 100;i++){String say=remoteService.sayHello("abcd");assertEquals("hello abcd@" + RemoteServiceImpl.class.getName(),say);}rpcExporter.unexport();}}

    public static interface NonStdRmiInterface {
        void bark();
    }

	/*@Test
    public void testRpcInvokerGroup() throws Exception
	{
		DemoService service = new DemoServiceImpl();
		RpcUtils.export("demo://127.0.0.1:9030/org.apache.dubbo.rpc.TestService",DemoService.class,service);
		RpcUtils.export("dubbo://127.0.0.1:9031/TestService",DemoService.class,service);
		RpcUtils.export("rmi://127.0.0.1:9032/org.apache.dubbo.rpc.TestService",DemoService.class,service);
		RpcUtils.export("rmi://127.0.0.1:9033/org.apache.dubbo.rpc.TestService",DemoService.class,service);

		service = RpcUtils.createProxy(DemoService.class,
				new String[]{
					"demo://127.0.0.1:9030/org.apache.dubbo.rpc.TestService?weight=20",
					"dubbo://127.0.0.1:9031/TestService?weight=20",
					"rmi://127.0.0.1:9032/org.apache.dubbo.rpc.TestService",
				});
		assertEquals(service.getSize(null), -1);
		assertEquals(service.getSize(new String[]{"","",""}), 3);

		// cast to EchoService
		EchoService echo = RpcUtils.createProxy(EchoService.class,
				new String[]{
			"demo://127.0.0.1:9030/org.apache.dubbo.rpc.TestService?weight=20",
			"dubbo://127.0.0.1:9031/TestService?weight=20",
			"rmi://127.0.0.1:9032/org.apache.dubbo.rpc.TestService",
		});
		assertEquals(echo.$echo("test"), "test");
		assertEquals(echo.$echo("abcdefg"), "abcdefg");
		assertEquals(echo.$echo(1234), 1234);
	}*/

	/*public void testForkInvoke() throws Exception
	{
		DemoService service = new DemoServiceImpl();
		protocol.export(proxy.createInvoker("dubbo://127.0.0.1:9040/TestService", DemoService.class, service);
		protocol.export(proxy.createInvoker("dubbo://127.0.0.1:9041/TestService", DemoService.class, service);
		protocol.export(proxy.createInvoker("rmi://127.0.0.1:9042/org.apache.dubbo.rpc.TestService", DemoService.class, service);
		protocol.export(proxy.createInvoker("rmi://127.0.0.1:9043/org.apache.dubbo.rpc.TestService", DemoService.class, service);

		RpcInvokerGroup group = Proxies.createInvoker(DemoService.class, new String[]{
			"dubbo://127.0.0.1:9040/TestService",
			"dubbo://127.0.0.1:9041/TestService",
			"rmi://127.0.0.1:9042/org.apache.dubbo.rpc.TestService",
			"rmi://127.0.0.1:9043/org.apache.dubbo.rpc.TestService",
		});
		group.getMethodSettings("echo").setFork(true);
		group.getMethodSettings("echo").setForkInvokeCallback(new ForkInvokeCallback(){
			public Object merge(RpcInvocation invocation, RpcResult[] results) throws Throwable
			{
				System.out.println("merge result begin:");
				for( RpcResult result : results )
				{
					if( result.hasException() )
						System.out.println("exception:"+result.getException().getMessage());
					else
						System.out.println("result:"+result.getResult());
				}
				System.out.println("merge result end:");
				return "aaaa";
			}
		});

		service = proxy.createProxy(protocol.refer(DemoService.class, group);
		service.echo("test");

		// cast to EchoService
		EchoService echo = proxy.createProxy(protocol.refer(EchoService.class, group);
		assertEquals(echo.$echo("test"), "test");
		assertEquals(echo.$echo("abcdefg"), "abcdefg");
		assertEquals(echo.$echo(1234), 1234);
	}*/

}
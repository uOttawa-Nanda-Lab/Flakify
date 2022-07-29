package org.apache.dubbo.rpc.proxy.jdk;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class JdkProxyFactoryTest {
@Test public void testGetInvoker() throws Exception {
  URL url=URL.valueOf("test://test:11/test?group=dubbo&version=1.1");
  DemoService origin=new org.apache.dubbo.rpc.support.DemoServiceImpl();
  Invoker<DemoService> invoker=factory.getInvoker(new DemoServiceImpl(),DemoService.class,url);
  Assert.assertEquals(invoker.getInterface(),DemoService.class);
  Assert.assertEquals(invoker.invoke(new RpcInvocation("echo",new Class[]{String.class},new Object[]{"aa"})).getValue(),origin.echo("aa"));
}

}
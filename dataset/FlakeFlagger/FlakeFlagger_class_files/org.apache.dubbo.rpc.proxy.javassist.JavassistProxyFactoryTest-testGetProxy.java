package org.apache.dubbo.rpc.proxy.javassist;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class JavassistProxyFactoryTest {
@Test public void testGetProxy() throws Exception {
  URL url=URL.valueOf("test://test:11/test?group=dubbo&version=1.1");
  Invoker<DemoService> invoker=new MyInvoker<>(url);
  DemoService proxy=factory.getProxy(invoker);
  Assert.assertNotNull(proxy);
  Assert.assertTrue(Arrays.asList(proxy.getClass().getInterfaces()).contains(DemoService.class));
  Assert.assertEquals(invoker.invoke(new RpcInvocation("echo",new Class[]{String.class},new Object[]{"aa"})).getValue(),proxy.echo("aa"));
}

}
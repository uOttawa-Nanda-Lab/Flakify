package org.apache.dubbo.cache.filter;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CacheFilterTest {
@Test public void testNonArgsMethod(){
  invocation.setMethodName("echo");
  invocation.setParameterTypes(new Class<?>[]{});
  invocation.setArguments(new Object[]{});
  cacheFilter.invoke(invoker,invocation);
  RpcResult rpcResult1=(RpcResult)cacheFilter.invoke(invoker1,invocation);
  RpcResult rpcResult2=(RpcResult)cacheFilter.invoke(invoker2,invocation);
  Assert.assertEquals(rpcResult1.getValue(),rpcResult2.getValue());
  Assert.assertEquals(rpcResult1.getValue(),"value");
}

}
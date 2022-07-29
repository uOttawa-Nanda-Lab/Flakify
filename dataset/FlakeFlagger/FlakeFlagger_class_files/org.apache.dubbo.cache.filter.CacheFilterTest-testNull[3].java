package org.apache.dubbo.cache.filter;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CacheFilterTest {
@Test public void testNull(){
  invocation.setMethodName("echo1");
  invocation.setParameterTypes(new Class<?>[]{String.class});
  invocation.setArguments(new Object[]{"arg3"});
  cacheFilter.invoke(invoker4,invocation);
  RpcResult rpcResult1=(RpcResult)cacheFilter.invoke(invoker1,invocation);
  RpcResult rpcResult2=(RpcResult)cacheFilter.invoke(invoker2,invocation);
  Assert.assertEquals(rpcResult1.getValue(),"value1");
  Assert.assertEquals(rpcResult2.getValue(),"value1");
}

}
package org.apache.dubbo.cache.filter;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CacheFilterTest {
@Test public void testException(){
  invocation.setMethodName("echo1");
  invocation.setParameterTypes(new Class<?>[]{String.class});
  invocation.setArguments(new Object[]{"arg2"});
  cacheFilter.invoke(invoker3,invocation);
  RpcResult rpcResult=(RpcResult)cacheFilter.invoke(invoker2,invocation);
  Assert.assertEquals(rpcResult.getValue(),"value2");
}

}
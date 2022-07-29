package org.apache.dubbo.remoting.transport.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CodecAdapterTest {
@Test public void test_Decode_Error_Request_Object() throws IOException {
  byte[] header=new byte[]{MAGIC_HIGH,MAGIC_LOW,(byte)0xe2,20,0,0,0,0,0,0,0,0,0,0,0,0};
  Person person=new Person();
  byte[] request=getRequestBytes(person,header);
  byte[] badbytes=new byte[]{-1,-2,-3,-4,-3,-4,-3,-4,-3,-4,-3,-4};
  System.arraycopy(badbytes,0,request,21,badbytes.length);
  Request obj=(Request)decode(request);
  Assert.assertEquals(true,obj.isBroken());
  Assert.assertEquals(true,obj.getData() instanceof Throwable);
}

}
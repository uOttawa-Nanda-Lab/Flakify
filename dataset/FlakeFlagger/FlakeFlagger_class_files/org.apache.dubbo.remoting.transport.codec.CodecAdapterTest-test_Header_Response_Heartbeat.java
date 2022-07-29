package org.apache.dubbo.remoting.transport.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CodecAdapterTest {
@Test public void test_Header_Response_Heartbeat() throws IOException {
  byte[] header=new byte[]{MAGIC_HIGH,MAGIC_LOW,0x02,20,0,0,0,0,0,0,0,0,0,0,0,0};
  Person person=new Person();
  byte[] request=getRequestBytes(person,header);
  Response obj=(Response)decode(request);
  Assert.assertEquals(20,obj.getStatus());
  Assert.assertEquals(person,obj.getResult());
  System.out.println(obj);
}

}
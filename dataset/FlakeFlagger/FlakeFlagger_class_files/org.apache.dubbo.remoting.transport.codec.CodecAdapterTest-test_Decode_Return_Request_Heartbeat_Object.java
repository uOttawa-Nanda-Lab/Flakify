package org.apache.dubbo.remoting.transport.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CodecAdapterTest {
@Test public void test_Decode_Return_Request_Heartbeat_Object() throws IOException {
  byte[] header=new byte[]{MAGIC_HIGH,MAGIC_LOW,(byte)0xe2,20,0,0,0,0,0,0,0,0,0,0,0,0};
  byte[] request=getRequestBytes(null,header);
  Request obj=(Request)decode(request);
  Assert.assertEquals(null,obj.getData());
  Assert.assertEquals(true,obj.isTwoWay());
  Assert.assertEquals(true,obj.isHeartbeat());
  Assert.assertEquals(Version.getProtocolVersion(),obj.getVersion());
  System.out.println(obj);
}

}
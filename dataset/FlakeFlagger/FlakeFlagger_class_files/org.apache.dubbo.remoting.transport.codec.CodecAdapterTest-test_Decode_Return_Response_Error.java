package org.apache.dubbo.remoting.transport.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CodecAdapterTest {
@Test public void test_Decode_Return_Response_Error() throws IOException {
  byte[] header=new byte[]{MAGIC_HIGH,MAGIC_LOW,2,90,0,0,0,0,0,0,0,0,0,0,0,0};
  String errorString="encode request data error ";
  byte[] request=getRequestBytes(errorString,header);
  Response obj=(Response)decode(request);
  Assert.assertEquals(90,obj.getStatus());
  Assert.assertEquals(errorString,obj.getErrorMessage());
}

}
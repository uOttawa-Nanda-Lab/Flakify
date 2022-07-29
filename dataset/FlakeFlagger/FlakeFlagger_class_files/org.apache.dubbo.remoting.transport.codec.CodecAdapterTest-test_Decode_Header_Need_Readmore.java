package org.apache.dubbo.remoting.transport.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CodecAdapterTest {
@Test public void test_Decode_Header_Need_Readmore() throws IOException {
  byte[] header=new byte[]{MAGIC_HIGH,MAGIC_LOW,0,0,0,0,0,0,0,0,0};
  testDecode_assertEquals(header,TelnetCodec.DecodeResult.NEED_MORE_INPUT);
}

}
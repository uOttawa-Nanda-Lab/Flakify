package org.apache.dubbo.remoting.transport.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CodecAdapterTest {
@Test public void test_Decode_Error_MagicNum() throws IOException {
  HashMap<byte[],Object> inputBytes=new HashMap<byte[],Object>();
  inputBytes.put(new byte[]{0},TelnetCodec.DecodeResult.NEED_MORE_INPUT);
  inputBytes.put(new byte[]{MAGIC_HIGH,0},TelnetCodec.DecodeResult.NEED_MORE_INPUT);
  inputBytes.put(new byte[]{0,MAGIC_LOW},TelnetCodec.DecodeResult.NEED_MORE_INPUT);
  for (  byte[] input : inputBytes.keySet()) {
    testDecode_assertEquals(assemblyDataProtocol(input),inputBytes.get(input));
  }
}

}
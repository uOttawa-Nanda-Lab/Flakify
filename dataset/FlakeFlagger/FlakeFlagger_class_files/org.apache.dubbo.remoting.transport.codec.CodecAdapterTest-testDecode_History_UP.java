package org.apache.dubbo.remoting.transport.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CodecAdapterTest {
@Test() public void testDecode_History_UP() throws IOException {
  AbstractMockChannel channel=getServerSideChannel(url);
  testDecode_assertEquals(channel,UP,Codec2.DecodeResult.NEED_MORE_INPUT,null);
  String request1="aaa\n";
  Object expected1="aaa";
  testDecode_assertEquals(channel,request1,expected1,null);
  testDecode_assertEquals(channel,UP,Codec2.DecodeResult.NEED_MORE_INPUT,expected1);
}

}
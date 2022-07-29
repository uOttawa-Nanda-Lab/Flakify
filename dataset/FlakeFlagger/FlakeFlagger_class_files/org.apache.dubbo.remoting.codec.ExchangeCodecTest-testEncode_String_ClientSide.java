package org.apache.dubbo.remoting.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ExchangeCodecTest {
@Test public void testEncode_String_ClientSide() throws IOException {
  testEecode_assertEquals("aaa","aaa\r\n".getBytes(),false);
}

}
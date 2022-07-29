package org.apache.dubbo.remoting.transport.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CodecAdapterTest {
@Test public void testDecode_String_WithEnter() throws IOException {
  testDecode_assertEquals("aaa\n","aaa");
}

}
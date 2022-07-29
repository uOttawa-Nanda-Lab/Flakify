package org.apache.dubbo.remoting.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ExchangeCodecTest {
@Test public void testDecode_String_NoEnter() throws IOException {
  testDecode_assertEquals("aaa",Codec2.DecodeResult.NEED_MORE_INPUT);
}

}
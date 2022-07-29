package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class DirectChannelBufferTest {
@Test public void testToByteBuffer1(){
  byte[] value=new byte[buffer.capacity()];
  random.nextBytes(value);
  buffer.clear();
  buffer.writeBytes(value);
  assertEquals(ByteBuffer.wrap(value),buffer.toByteBuffer());
}

}
package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class DirectChannelBufferTest {
@Test public void testToByteBuffer2(){
  byte[] value=new byte[buffer.capacity()];
  random.nextBytes(value);
  buffer.clear();
  buffer.writeBytes(value);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    assertEquals(ByteBuffer.wrap(value,i,BLOCK_SIZE),buffer.toByteBuffer(i,BLOCK_SIZE));
  }
}

}
package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ByteBufferBackedChannelBufferTest {
@Test public void testRandomByteAccess(){
  for (int i=0; i < buffer.capacity(); i++) {
    byte value=(byte)random.nextInt();
    buffer.setByte(i,value);
  }
  random.setSeed(seed);
  for (int i=0; i < buffer.capacity(); i++) {
    byte value=(byte)random.nextInt();
    assertEquals(value,buffer.getByte(i));
  }
}

}
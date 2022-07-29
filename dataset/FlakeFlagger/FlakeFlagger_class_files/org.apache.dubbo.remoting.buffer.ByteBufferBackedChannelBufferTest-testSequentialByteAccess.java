package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ByteBufferBackedChannelBufferTest {
@Test public void testSequentialByteAccess(){
  buffer.writerIndex(0);
  for (int i=0; i < buffer.capacity(); i++) {
    byte value=(byte)random.nextInt();
    assertEquals(i,buffer.writerIndex());
    assertTrue(buffer.writable());
    buffer.writeByte(value);
  }
  assertEquals(0,buffer.readerIndex());
  assertEquals(buffer.capacity(),buffer.writerIndex());
  assertFalse(buffer.writable());
  random.setSeed(seed);
  for (int i=0; i < buffer.capacity(); i++) {
    byte value=(byte)random.nextInt();
    assertEquals(i,buffer.readerIndex());
    assertTrue(buffer.readable());
    assertEquals(value,buffer.readByte());
  }
  assertEquals(buffer.capacity(),buffer.readerIndex());
  assertEquals(buffer.capacity(),buffer.writerIndex());
  assertFalse(buffer.readable());
  assertFalse(buffer.writable());
}

}
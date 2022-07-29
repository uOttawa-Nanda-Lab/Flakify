package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ByteBufferBackedChannelBufferTest {
@Test public void testSequentialByteArrayTransfer2(){
  byte[] value=new byte[BLOCK_SIZE * 2];
  buffer.writerIndex(0);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(value);
    assertEquals(0,buffer.readerIndex());
    assertEquals(i,buffer.writerIndex());
    int readerIndex=random.nextInt(BLOCK_SIZE);
    buffer.writeBytes(value,readerIndex,BLOCK_SIZE);
  }
  random.setSeed(seed);
  byte[] expectedValue=new byte[BLOCK_SIZE * 2];
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(expectedValue);
    int valueOffset=random.nextInt(BLOCK_SIZE);
    assertEquals(i,buffer.readerIndex());
    assertEquals(CAPACITY,buffer.writerIndex());
    buffer.readBytes(value,valueOffset,BLOCK_SIZE);
    for (int j=valueOffset; j < valueOffset + BLOCK_SIZE; j++) {
      assertEquals(expectedValue[j],value[j]);
    }
  }
}

}
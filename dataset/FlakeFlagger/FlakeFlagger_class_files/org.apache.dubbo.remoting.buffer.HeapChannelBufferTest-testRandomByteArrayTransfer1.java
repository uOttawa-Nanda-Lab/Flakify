package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HeapChannelBufferTest {
@Test public void testRandomByteArrayTransfer1(){
  byte[] value=new byte[BLOCK_SIZE];
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(value);
    buffer.setBytes(i,value);
  }
  random.setSeed(seed);
  byte[] expectedValueContent=new byte[BLOCK_SIZE];
  ChannelBuffer expectedValue=wrappedBuffer(expectedValueContent);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(expectedValueContent);
    buffer.getBytes(i,value);
    for (int j=0; j < BLOCK_SIZE; j++) {
      assertEquals(expectedValue.getByte(j),value[j]);
    }
  }
}

}
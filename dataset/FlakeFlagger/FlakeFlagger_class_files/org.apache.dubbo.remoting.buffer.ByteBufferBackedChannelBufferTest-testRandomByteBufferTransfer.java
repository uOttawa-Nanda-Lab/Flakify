package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ByteBufferBackedChannelBufferTest {
@Test public void testRandomByteBufferTransfer(){
  ByteBuffer value=ByteBuffer.allocate(BLOCK_SIZE * 2);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(value.array());
    value.clear().position(random.nextInt(BLOCK_SIZE));
    value.limit(value.position() + BLOCK_SIZE);
    buffer.setBytes(i,value);
  }
  random.setSeed(seed);
  ByteBuffer expectedValue=ByteBuffer.allocate(BLOCK_SIZE * 2);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(expectedValue.array());
    int valueOffset=random.nextInt(BLOCK_SIZE);
    value.clear().position(valueOffset).limit(valueOffset + BLOCK_SIZE);
    buffer.getBytes(i,value);
    assertEquals(valueOffset + BLOCK_SIZE,value.position());
    for (int j=valueOffset; j < valueOffset + BLOCK_SIZE; j++) {
      assertEquals(expectedValue.get(j),value.get(j));
    }
  }
}

}
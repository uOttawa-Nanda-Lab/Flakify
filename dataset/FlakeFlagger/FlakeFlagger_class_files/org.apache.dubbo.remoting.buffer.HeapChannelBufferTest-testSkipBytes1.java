package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HeapChannelBufferTest {
@Test public void testSkipBytes1(){
  buffer.setIndex(CAPACITY / 4,CAPACITY / 2);
  buffer.skipBytes(CAPACITY / 4);
  assertEquals(CAPACITY / 4 * 2,buffer.readerIndex());
  try {
    buffer.skipBytes(CAPACITY / 4 + 1);
    fail();
  }
 catch (  IndexOutOfBoundsException e) {
  }
  assertEquals(CAPACITY / 4 * 2,buffer.readerIndex());
}

}
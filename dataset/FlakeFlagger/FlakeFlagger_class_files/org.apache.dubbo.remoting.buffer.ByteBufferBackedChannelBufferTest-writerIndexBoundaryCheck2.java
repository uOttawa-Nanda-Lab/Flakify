package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ByteBufferBackedChannelBufferTest {
@Test(expected=IndexOutOfBoundsException.class) public void writerIndexBoundaryCheck2(){
  try {
    buffer.writerIndex(CAPACITY);
    buffer.readerIndex(CAPACITY);
  }
 catch (  IndexOutOfBoundsException e) {
    fail();
  }
  buffer.writerIndex(buffer.capacity() + 1);
}

}
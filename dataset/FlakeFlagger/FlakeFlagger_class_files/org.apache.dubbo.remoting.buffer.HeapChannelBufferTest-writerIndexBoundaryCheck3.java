package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HeapChannelBufferTest {
@Test(expected=IndexOutOfBoundsException.class) public void writerIndexBoundaryCheck3(){
  try {
    buffer.writerIndex(CAPACITY);
    buffer.readerIndex(CAPACITY / 2);
  }
 catch (  IndexOutOfBoundsException e) {
    fail();
  }
  buffer.writerIndex(CAPACITY / 4);
}

}
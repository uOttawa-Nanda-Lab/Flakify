package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HeapChannelBufferTest {
@Test(expected=IndexOutOfBoundsException.class) public void readerIndexBoundaryCheck3(){
  try {
    buffer.writerIndex(CAPACITY / 2);
  }
 catch (  IndexOutOfBoundsException e) {
    fail();
  }
  buffer.readerIndex(CAPACITY * 3 / 2);
}

}
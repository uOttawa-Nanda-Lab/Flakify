package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class DirectChannelBufferTest {
@Test(expected=IndexOutOfBoundsException.class) public void readerIndexBoundaryCheck2(){
  try {
    buffer.writerIndex(buffer.capacity());
  }
 catch (  IndexOutOfBoundsException e) {
    fail();
  }
  buffer.readerIndex(buffer.capacity() + 1);
}

}
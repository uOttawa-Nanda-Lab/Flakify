package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class DirectChannelBufferTest {
@Test public void readerIndexBoundaryCheck4(){
  buffer.writerIndex(0);
  buffer.readerIndex(0);
  buffer.writerIndex(buffer.capacity());
  buffer.readerIndex(buffer.capacity());
}

}
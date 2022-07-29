package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ByteBufferBackedChannelBufferTest {
@Test public void initialState(){
  assertEquals(CAPACITY,buffer.capacity());
  assertEquals(0,buffer.readerIndex());
}

}
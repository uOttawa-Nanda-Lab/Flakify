package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ByteBufferBackedChannelBufferTest {
@Test(expected=IndexOutOfBoundsException.class) public void getByteBoundaryCheck1(){
  buffer.getByte(-1);
}

}
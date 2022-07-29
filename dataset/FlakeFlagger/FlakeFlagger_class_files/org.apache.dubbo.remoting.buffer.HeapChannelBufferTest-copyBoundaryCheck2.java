package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HeapChannelBufferTest {
@Test(expected=IndexOutOfBoundsException.class) public void copyBoundaryCheck2(){
  buffer.copy(0,buffer.capacity() + 1);
}

}
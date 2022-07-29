package org.apache.dubbo.remoting.buffer;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class DynamicChannelBufferTest {
@Test(expected=IndexOutOfBoundsException.class) public void setIndexBoundaryCheck3(){
  buffer.setIndex(0,CAPACITY + 1);
}

}
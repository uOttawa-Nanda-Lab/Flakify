package me.prettyprint.cassandra.serializers;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class StringSerializerTest {
@Test public void test(){
  StringSerializer e=new StringSerializer();
  Assert.assertEquals(str,e.fromByteBuffer(e.toByteBuffer(str)));
}

}
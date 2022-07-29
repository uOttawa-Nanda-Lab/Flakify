package org.apache.dubbo.common.serialize.serialization;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class Hessian2SerializationTest {
@Test public void test_StringArrayList() throws Exception {
  List<String> args=new ArrayList<String>(Arrays.asList(new String[]{"1","b"}));
  assertObject(args);
}

}
package org.apache.dubbo.common.serialize.serialization;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class KyroSerializationTest {
@Test public void test_StringSPersonMap() throws Exception {
  Map<String,SerializablePerson> args=new HashMap<String,SerializablePerson>();
  args.put("1",new SerializablePerson());
  assertObject(args);
}

}
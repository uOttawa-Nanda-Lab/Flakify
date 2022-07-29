package org.apache.dubbo.common.serialize.serialization;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class JavaSerializationTest {
@Test public void test_Bool_Multi() throws Exception {
  boolean[] array=new boolean[100];
  for (int i=0; i < array.length; i++) {
    array[i]=random.nextBoolean();
  }
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  for (  boolean b : array) {
    objectOutput.writeBool(b);
  }
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  for (  boolean b : array) {
    assertEquals(b,deserialize.readBool());
  }
  try {
    deserialize.readBool();
    fail();
  }
 catch (  IOException expected) {
  }
}

}
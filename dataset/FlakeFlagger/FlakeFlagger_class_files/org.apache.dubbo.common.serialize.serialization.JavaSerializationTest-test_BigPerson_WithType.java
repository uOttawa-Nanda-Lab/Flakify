package org.apache.dubbo.common.serialize.serialization;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class JavaSerializationTest {
@Test public void test_BigPerson_WithType() throws Exception {
  assertObjectWithType(bigPerson,BigPerson.class);
}

}
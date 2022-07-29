package org.apache.dubbo.common.serialize.serialization;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class Hessian2SerializationTest {
@Test public void test_BigDecimal_withType() throws Exception {
  assertObjectWithType(new BigDecimal("23423434234234234.341274832341234235"),BigDecimal.class);
}

}